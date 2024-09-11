#!/bin/bash
#
# Autor: Vázquez Rodríguez, Daniel
#
set -x

readonly PROCESS_LISTS=('procesos' 'procesos_servicio' 'procesos_periodicos')

readonly LOG_FILE='Biblia.txt'

readonly LOCK_FILE='SanPedro'

readonly APOCALYPSE_FILE='Apocalipsis'

readonly HELL_DIR='Infierno'


function main() {
  read_processes_until_apocalypse
  terminate_all_processes
  clear_structures
  terminate_itself
}

function read_processes_until_apocalypse() {
  while apocalypse_file_not_exists; do
    sleep 1
    terminate_processes_in_hell_or_not_running
  done
}

function terminate_all_processes() {
  log '--------------- Apocalipsis ---------------'
  
  for process_list in "${PROCESS_LISTS[@]}"; do
    local pid

    while IFS= read -r pid_and_command; do
      pid=$(get_pid $pid_and_command $process_list)
      terminate_process_tree "$pid"
      delete_process_in_process_list "$pid" "$process_list"
      log "El proceso $pid ha terminado."
    done < "$process_list"
  done

  log 'Se acabó el mundo.'
}

function clear_structures() {
  remove_process_lists
  remove_lock_file
  remove_apocalypse_file
  remove_hell_dir
}

function terminate_itself() {
  kill $$
}

function apocalypse_file_not_exists() {
  [ ! -f "${APOCALYPSE_FILE}" ]
}

function terminate_processes_in_hell_or_not_running() {
  for process_list in "${PROCESS_LISTS[@]}"; do
    local pid

    while IFS= read -r pid_and_command; do
      pid=$(get_pid "$pid_and_command" "$process_list")

      if pid_file_in_hell "$pid"; then
        terminate_process_tree "$pid"
        delete_process_in_process_list "$pid" "$process_list"
        remove_pid_file_in_hell "$pid"
        log "El proceso $pid ha terminado."
      else
        if [ "$process_list" = procesos ]; then
          if process_not_running "$pid"; then
            delete_process_in_process_list "$pid" "$process_list"
            log "El proceso $pid ha terminado."
          fi
        elif [ "$process_list" = procesos_servicio ]; then
          if process_not_running "$pid"; then
            restart_process "$pid_and_command" && new_pid=$!
            update_pid_in_process_list "$pid" "$new_pid" "$process_list"
            log "El proceso $pid resucita con pid $new_pid."
          fi
        elif [ "$process_list" = procesos_periodicos ]; then
          if period_exceeded "$pid_and_command" && process_not_running "$pid"; then
            restart_process "$pid_and_command" && new_pid=$!
            update_pid_in_process_list "$pid" "$new_pid" "$process_list"
            reset_timer "$new_pid"
            log "El proceso $pid se ha reencarnado en el pid $new_pid."
          else
            increase_timer "$pid_and_command"
          fi
        fi
      fi
    done < "$process_list"
  done
}

function get_pid() {
  local pid_and_command
  local process_list

  pid_and_command="$1"
  process_list="$2"

  if [ "$process_list" = "${PROCESS_LISTS[2]}" ]; then
    echo "$pid_and_command" | cut -d " " -f 3 | xargs
  else
    echo "$pid_and_command" | cut -d " " -f 1 | xargs
  fi
}

function pid_file_in_hell() {
  local pid="$1"

  for file in "${HELL_DIR}"/*; do
    [ "$file" = "${HELL_DIR}"/"${pid}" ] && return 0
  done

  return 1
}

function terminate_process_tree() {
  local pid="$1"
  kill $(pstree -p ${pid} | grep -E -o '[0-9]+') 2> /dev/null
}

function delete_process_in_process_list() {
  #{ flock -x 9
    #grep -v "$1" "$2" > tmp
    #mv tmp "$2"
  #} 9> "${LOCK_FILE}"

  local pid_and_command
  local process_list

  pid_and_command="$1"
  process_list="$2"

  { flock -x 9
    sed -i "/$pid_and_command/d" "$process_list"
  } 9> "$LOCK_FILE"

  #flock -x "$LOCK_FILE" -c "sed -i \"/$pid_and_command/d\" $process_list"
}

function remove_pid_file_in_hell() {
  rm "${HELL_DIR}"/"$1"
}

function process_not_running() {
  ! kill -0 "$1" 2> /dev/null
}

function restart_process() {
  cmmnd=$(echo $1 | cut -d "'" -f 2)
  bash -c "$cmmnd" &
}

function update_pid_in_process_list() {
  local pid="$1"
  local new_pid="$2"
  local process_list="$3"

  { flock -x 9
    sed -i "s/$pid/$new_pid/" "$process_list"
  } 9> "$LOCK_FILE"

  #flock -x "$LOCK_FILE" -c "sed -i \"s/$pid/$new_pid/\" $process_list"
}

function period_exceeded() {
  local line
  local timer
  local execution_period

  line="$1"
  timer=$(echo $line | cut -d " " -f 1)
  execution_period=$(echo $line | cut -d " " -f 2)

  (( timer + 1 >= execution_period ))
}

function reset_timer() {
  local new_pid
  local line_with_new_pid
  local update_timer_on_line

  new_pid="$1"
  line_with_new_pid=$(grep $new_pid "${PROCESS_LISTS[2]}")
  updated_timer_on_line=$(echo $line_with_new_pid | awk '{ $1=0 }1')

  { flock -x 9
    sed -i "s/$line_with_new_pid/$updated_timer_on_line/" "${PROCESS_LISTS[2]}"
  } 9> $LOCK_FILE

  #flock -x "$LOCK_FILE" -c "sed -i \"s/$line_with_new_pid/$updated_timer_on_line/\" ${PROCESS_LISTS[2]}"
}

function increase_timer() {
  local line
  local updated_timer_on_line

  line="$1"
  updated_timer_on_line=$(echo $line | awk '{ $1=$1+1 }1')

  { flock -x 9
    sed -i "s/$line/$updated_timer_on_line/" "${PROCESS_LISTS[2]}"
  } 9> $LOCK_FILE

  #flock -x "$LOCK_FILE" -c "sed -i \"s/$line/$updated_timer_on_line/\" ${PROCESS_LISTS[2]}"
}

function remove_process_lists() {
  for process_list in "${PROCESS_LISTS[@]}"; do
    rm "${process_list}"
  done
}

function remove_lock_file() {
  rm "${LOCK_FILE}" 2> /dev/null
}

function remove_apocalypse_file() {
  rm "${APOCALYPSE_FILE}" 2> /dev/null
}

function remove_hell_dir() {
  if [ -d "${HELL_DIR}" ]; then
    rm "${HELL_DIR}"/* 2> /dev/null
    rmdir "${HELL_DIR}"
  fi
}

function log() {
  append_to_file "$(date +%T) $1" "${LOG_FILE}"
}

function append_to_file() {
  flock -x "${LOCK_FILE}" -c "echo \"$1\" >> $2"
}


main
