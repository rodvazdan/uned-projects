#include <errno.h>
#include <signal.h>
#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <time.h>
#include <unistd.h>

enum Action {
  kAttack,
  kDefend
};

// Búfer de mensajes para almacenar el mensaje que se envía o recibe de la cola.
struct msgbuf {
  long type;
  pid_t pid;
  char status[2];
};

char estado[2] = "OK";

int randomlyChooseAction() {
  return rand() % 2;
}

void updateStatus (const char *new_status) {
  strcpy(estado, new_status);
}

void indefenso() {
  printf("El hijo %d ha sido emboscado mientras realizaba un ataque.\n", getpid());
  updateStatus("KO");
}

void defensa() {
  printf("El hijo %d ha repelido un ataque.\n", getpid());
  updateStatus("OK");
}

void setCustomHandler (void (*sighandler_t)(int)) {
  struct sigaction new_action;

  new_action.sa_handler = sighandler_t;
  sigemptyset(&new_action.sa_mask);
  new_action.sa_flags = 0;
  sigaction(SIGUSR1, &new_action, NULL);

  printf("[Debug] Hijo %d ha instalado manejador para SIGUSR1\n", getpid());
}

void sleepInMilliseconds (long milliseconds) {
  struct timespec ts;

  ts.tv_sec = milliseconds / 1000;
  ts.tv_nsec = (milliseconds % 1000) * 1000000;

  /*int res;
  do {
    res = nanosleep(&ts, &ts);
  } while (res);*/

  do {} while (nanosleep(&ts, &ts);
}

bool isValid (pid_t pid) {
  return (pid != 0 && pid != getpid());
}

pid_t randomlyChooseValidPid (/*int *lista*/) {
  /*pid_t pid;

  for (int i = 0; i < numberOfPids; i++) {
    if (isValid(lista[i])) {
      pid = lista[i];
      break;
    }
  }

  return pid;*/

  return getpid();
}

void attackPid (pid_t pid) {
  if (kill(pid, SIGUSR1) == 0) {
    printf("Atacando al proceso %d\n", pid);
  } else {
    if (errno == ESRCH) {
      fprintf(stderr, "E: the pid %d does not exist.\n", pid);
    }
  }
}

int sendMessage (int msg_id, struct msgbuf *msg) {
  int length = sizeof(msg) - sizeof(msg->type);

  int result;
  if ((result = msgsnd(msg_id, msg, length, IPC_NOWAIT)) == -1) {
    perror("msgsnd");
  } else {
    printf("[Debug] Child %d sent message to message queue %d: %d%s\n", getpid(), msg_id, msg->pid, msg->status);
  }

  return result;
}

int getMessageQueue (key_t ipc_key) {
  int result;

  if ((result = msgget(ipc_key, IPC_CREAT)) == -1) {
    perror("msgget");
  } else {
    printf("[Debug] Message queue %d restored by child %d\n", result, getpid());
  }

  return result;
}

int getSharedMemorySegment (key_t ipc_key) {
  int result = shmget(ipc_key, /*size * */sizeof(int), IPC_CREAT);
  if (result == -1) {
    perror("shmget");
  } else {
    printf("[Debug] Shared memory segment %d created\n", result);
  }
  return result;
}

int *attachSegment (int shm_id) {
  int result = shmat(shm_id, 0, 0);
  if (result == -1) {
    perror("shmat");
  } else {
    printf("[Debug] Shared memory segment %d was attached to address %p\n", shm_id, result);
  }
  return result;
}

void setupMessage (struct msgbuf *msg) {
  msg->type = 0;
  msg->pid = getpid();
  strcpy(msg->status, estado);
}

unsigned long mix(unsigned long a, unsigned long b, unsigned long c) {
  a=a-b;  a=a-c;  a=a^(c >> 13);
  b=b-c;  b=b-a;  b=b^(a << 8);
  c=c-a;  c=c-b;  c=c^(b >> 13);
  a=a-b;  a=a-c;  a=a^(c >> 12);
  b=b-c;  b=b-a;  b=b^(a << 16);
  c=c-a;  c=c-b;  c=c^(b >> 5);
  a=a-b;  a=a-c;  a=a^(c >> 3);
  b=b-c;  b=b-a;  b=b^(a << 10);
  c=c-a;  c=c-b;  c=c^(b >> 15);
  
  return c;
}

int main (int argc, char *argv[]) {
  srand(mix(clock(), time(NULL), getpid()));

  int barrera[2];

  // restablecimiento de los mecanismos IPC
  key_t ipc_key = (int) strtol(argv[1], NULL, 10);
  
  int msg_id = getMessageQueue(ipc_key);
  int shm_id = getSharedMemorySegment(ipc_key);

  int *lista = attachSegment(shm_id);

  // fase de preparación
  int action = randomlyChooseAction();

  switch (action) {
    case kAttack:
      setCustomHandler(indefenso);
      sleepInMilliseconds(100);
      break;
    case kDefend:
      setCustomHandler(defensa);
      sleepInMilliseconds(200);
      break;
  }

  // fase de ataque
  if (action == kAttack) {
    pid_t pid = randomlyChooseValidPid(lista);
    attackPid(pid);
    sleepInMilliseconds(100);
  }

  // Se envía un mensaje al proceso padre que contiene el PID
  // y el estado del proceso hijo actual.
  struct msgbuf msg;
  setupMessage(&msg);
  sendMessage(msg_id, &msg);

  // TODO: esperar en la barrera

  return 0; 
}
