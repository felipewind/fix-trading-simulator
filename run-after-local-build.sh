#!/bin/bash

printf "=== Executing Docker Compose after local Build to start the application ===\n"
printf "\n"

function ctrl_c() {
printf "\n"
printf '==========================================================================\n'
printf '============= EXECUTIN DOCKER COMPOSE DOWN AFTER CTRL+C ==================\n'
printf '==========================================================================\n'
printf "\n"
docker-compose -f ./docker-compose.yml down
exit
}
trap ctrl_c INT

docker-compose -f ./docker-compose.yml up --build

ctrl_c