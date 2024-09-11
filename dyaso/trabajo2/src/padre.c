#include <errno.h>
#include <fcntl.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <unistd.h>

/*
 * Búfer de mensajes para almacenar el mensaje que se envía o recibe
 * de la cola de mensajes.
 */
struct msgbuf {
  long type;
  pid_t pid;
  char status[2];
};

void print_error_and_exit (const char* error_message);

int main (int argc, char* argv[]) {
  if (argc != 2) {
    printf("Usage: %s <número de hijos>\n", argv[0]);
    exit(1);
  }

  /* Se obtiene una nueva clave IPC. */
  key_t ipc_key = ftok(argv[0], 'x');

  if (ipc_key == -1) {
    perror("ftok");
    exit(1);
  }

  /* Se crea una nueva cola de mensajes. */
  int msgid = msgget(ipc_key, IPC_CREAT|0600);

  if (msgid == -1) {
    perror("msgget");
    exit(1);
  }

  /*
   * Se obtiene la estructura de datos que contiene información acerca de
   * la cola de mensajes.
   */
  struct msqid_ds buf;

  if (msgctl(msgid, IPC_STAT, &buf) == -1) {
    perror("msgctl");
  }

  /* Se establece el tamaño en bytes de la cola de mensajes. */
  buf.msq_qbytes = (msglen_t) strtol(argv[1], NULL, 10);

  if (msgctl(msgid, IPC_SET, &buf) == -1) {
    perror("msgctl");
    exit(1);
  }

  /* Se crea una nueva región de memoria compartida. */
  int segment_size = (int) strtol(argv[1], NULL, 10);
  int shmid = shmget(ipc_key, segment_size * sizeof(segment_size)/*sizeof(int)*/, IPC_CREAT|0600);

  if (shmid == -1) {
    perror("shmget");
    exit(1);
  }

  /* Se adjunta la región de memoria compartida a 'lista'. */
  int* lista = shmat(shmid, 0, 0);

  if (lista == -1) {
    perror("shmat");
    exit(1);
  }

  /* Se obtiene un nuevo semáforo. */
  int semid = semget(ipc_key, 1, IPC_CREAT|0600);

  if (semid == -1) {
    perror("semget");
    exit(1);
  }

  /* Se crea una tubería sin nombre. */
  int barrera[2];

  if (pipe(barrera) == -1) {
    perror("pipe");
    exit(1);
  }

  /* Se crean 'argv[1]' procesos hijo. */
  int children = (int) strtol(argv[1], NULL, 10);

  for (int i = 0; i < children; ++i) {
    pid_t child_pid = fork();

    if (child_pid == -1) {
      perror("fork");
    } else if (child_pid == 0) {
      /* Cada proceso hijo ejecutará el fichero HIJO. */
      size_t ipc_key_length = snprintf(NULL, 0, "%d", ipc_key) + 1;
      char* ipc_key_pointer = malloc(ipc_key_length);

      if (ipc_key_pointer == NULL) {
        perror("malloc");
        exit(1);
      }

      snprintf(ipc_key_pointer, ipc_key_length, "%d", ipc_key);
      execl("./HIJO", "./HIJO", ipc_key, barrera, (char*) NULL);
      free(ipc_key_pointer);
    } else {
      printf("Iniciando ronda de ataques\n");
    }
  }

  /* Se elimina la cola de mensajes identificada por 'msgid'. */
  if (msgctl(msgid, IPC_RMID, 0) == -1) {
    perror("msgctl");
  }

  /* Se libera la región de memoria compartida identificada por 'shmid'. */
  if (shmctl(shmid, IPC_RMID, 0) == -1) {
    perror("shmctl");
  }

  /* Se elimina el semáforo identificado por 'semid'. */
  if (semctl(semid, IPC_RMID, 0) == -1) {
    perror("semctl");
  }

  /* Se cierran los dos extremos de la tubería 'barrera'. */
  close(barrera[0]);
  close(barrera[1]);

  /* Se escribe el resultado del combate en el fichero FIFO 'resultado'. */
  const char* fifo_filename = "resultado";
  int fifo_fd = open(fifo_filename, O_WRONLY);

  if (fifo_fd == -1) {
    perror("open");
    exit(1);
  }

  const char fifo_msg[] = "empate";

  if (write(fifo_fd, fifo_msg, sizeof(fifo_msg) - 1) == -1) {
    perror("write");
  }

  if (close(fifo_fd) == -1) {
    perror("close");
  }

  /* Se demuestra que los mecanismos IPC usados han sido liberados. */
  const char* command_format = "ipcs -q -s";
  size_t command_length = snprintf(NULL, 0, command_format) + 1;
  char* command = malloc(command_length);

  if (command == NULL) {
    perror("malloc");
    exit(1);
  }
  
  snprintf(command, command_length, command_format);
  system(command);
  free(command);

  return 0;
}
