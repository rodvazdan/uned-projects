#!/bin/bash
#
# Autor: Vázquez Rodríguez, Daniel
# Fecha: Nov 09, 2023
#
#set -x

readonly SCRIPT_NAME='Fausto.sh'

readonly DAEMON_NAME='Demonio.sh'

readonly PROCESS_LISTS=('procesos' 'procesos_servicio' 'procesos_periodicos')

readonly LOG_FILE='Biblia.txt'

readonly LOCK_FILE='SanPedro'

readonly APOCALYPSE_FILE='Apocalipsis'

readonly HELL_DIR='Infierno'


function main() {
  if daemon_is_not_running; then
    restart_structures
    invoke_daemon && daemonpid=$!
    log 'El demonio ha sido creado.'
  fi

  if command_syntax_is_valid "$@"; then
    execute_given_command "$@"
  else
    exit 1
  fi
}

function daemon_is_not_running() {
  ! pgrep -x "${DAEMON_NAME}" > /dev/null
}

function restart_structures() {
  clear_process_lists
  initialize_log_file
  remove_apocalypse_file_if_exists
  clear_hell_dir
}

function invoke_daemon() {
  nohup ./"${DAEMON_NAME}" > /dev/null 2>&1 &
}

function command_syntax_is_valid() {
  local option="$1"
  local argc=$#

  [ $argc -eq 0 ] && { print_usage; return 1; }

  case "$option" in
    list|help|end)
      if [ $argc -ne 1 ]; then
        print_error "Número de argumentos incorrecto para la orden '$option'."
        return 1
      fi
      ;;
    run|run-service|stop)
      if [ $argc -ne 2 ]; then
        print_error "Número de argumentos incorrecto para la orden '$option'."
        return 1
      fi
      ;;
    run-periodic)
      if [ $argc -ne 3 ]; then
        print_error "Número de argumentos incorrecto para la orden '$option'."
        return 1
      fi
      ;;
    *)
      print_error "Orden $option no reconocida." \
        "Consulta las órdenes disponibles con '${SCRIPT_NAME} help'."
      return 1
      ;;
  esac

  return 0
}

function execute_given_command() {
  case "$1" in
    run) execute_command_only_once "$2" ;;
    run-service) execute_command_as_a_service "$2" ;;
    run-periodic) execute_command_periodically "$2" "$3" ;;
    list) print_all_processes ;;
    help) print_usage ;;
    stop) mark_process_to_kill "$2" ;;
    end) notify_daemon_to_kill_all_processes ;;
  esac
}

function clear_process_lists() {
  for list in "${PROCESS_LISTS[@]}"; do
    true > "${list}"
  done
}

function initialize_log_file() {
  true > "${LOG_FILE}"
  log '--------------- Génesis ---------------'
}

function remove_apocalypse_file_if_exists() {
  rm "${APOCALYPSE_FILE}" 2> /dev/null
}

function clear_hell_dir() {
  mkdir "${HELL_DIR}" 2> /dev/null || rm "${HELL_DIR}"/* 2> /dev/null
}

function execute_command_only_once() {
  run_command "$1" && pid=$!
  append_to_file "$pid '$1'" "${PROCESS_LISTS[0]}"
  log "El proceso $pid '$1' ha nacido."
}

function execute_command_as_a_service() {
  run_command "$1" && pid=$!
  append_to_file "$pid '$1'" "${PROCESS_LISTS[1]}"
  log "El proceso $pid '$1' ha nacido."
}

function execute_command_periodically() {
  run_command "$2" && pid=$!
  append_to_file "0 $1 $pid '$2'" "${PROCESS_LISTS[2]}"
  log "El proceso $pid '$2' ha nacido."
}

function print_all_processes() {
  # Imprime información acerca de todos los procesos en ejecución.
  for list in "${PROCESS_LISTS[@]}"; do
    if [ -s "${list}" ]; then
      print_process_list_title "${list}"
      #read_process_list "${list}"
      read_file "${list}"
    fi
  done
}

function print_usage() {
  echo "Sintaxis: ${SCRIPT_NAME} [OPCIÓN] [ARGUMENTOS]"
  echo
  echo 'Opciones disponibles:'
  echo '  help                                 Muestra esta ayuda.'
  echo '  list                                 Lista información de procesos.'
  echo '  run [COMANDO]                        Ejecuta [COMANDO].'
  echo '  run-service [COMANDO]                Ejecuta [COMANDO] como un servicio.'
  echo '  run-periodic [FRECUENCIA] [COMANDO]  Ejecuta [COMANDO] periódicamente.'
  echo '  stop [PID]                           Detiene el proceso con pid [PID].'
  echo '  end                                  Termina la ejecución de todos los procesos.'
}

function mark_process_to_kill() {
  if process_exists_in_process_lists "$1"; then
    mark_process "$1"
  else
    print_error "El PID $1 no corresponde a ningún proceso." \
      "Consulte los procesos en ejecución con la opción '${SCRIPT_NAME} list'"
  fi

  local pid="$1"

  if process_exists_in_process_lists "$pid"; then
    mark_process "$pid"
  else
    print_error "El PID "$pid" no corresponde a ningún proceso." \
      "Consulte los procesos en ejecución con la opción '$SCRIPT_NAME list'"
  fi
}

function notify_daemon_to_kill_all_processes() {
  touch "${APOCALYPSE_FILE}"
}

function run_command() {
  bash -c "$1" &
}

function print_process_list_title() {
  if [ "$1" == 'procesos' ]; then
    echo '***** Procesos normales *****'
  elif [ "$1" == 'procesos_servicio' ]; then
    echo '***** Procesos servicio *****'
  elif [ "$1" == 'procesos_periodicos' ]; then
    echo '***** Procesos periódicos *****'
  fi
}

function process_exists_in_process_lists() {
  for process_list in "${PROCESS_LISTS[@]}"; do
    read_file "${process_list}" | tr ' ' '\n' | grep -F -x "$1" > /dev/null 2>&1
    [ $? -eq 0 ] && return 0
  done

  return 1
}

function read_file() {
  local file="$1"

  { flock -s 9
    while IFS= read -r line; do
      echo "${line}"
    done < "${file}"
  } 9> "${LOCK_FILE}"
}

function mark_process() {
  touch "$HELL_DIR"/"$pid"
}

function log() {
  #local current_time
  #current_time=$(date +%T)
  append_to_file "$(date +%T) $1" "${LOG_FILE}"
}

function append_to_file() {
  flock -x "${LOCK_FILE}" -c "echo \"$1\" >> $2"
}

function print_error() {
  echo "Error: $*" >&2
}

main "$@"
