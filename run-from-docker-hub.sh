#!/bin/bash

printf "===== Executing Docker Compose with images from DockerHub ====\n"
printf "\n"

function ctrl_c() {
printf "\n"
printf '==========================================================================\n'
printf '============= EXECUTIN DOCKER COMPOSE DOWN AFTER CTRL+C ==================\n'
printf '==========================================================================\n'
printf "\n"
docker-compose -f ./docker-compose-docker-hub.yml down
exit
}
trap ctrl_c INT

docker-compose -f ./docker-compose-docker-hub.yml up --build

ctrl_c