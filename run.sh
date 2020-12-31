#!/bin/bash

printf "====== Executing Docker Compose with Build to start the application ====\n"
printf "\n"

printf "========== Building local Exchange Project with local Maven ============\n"
printf "\n"
cd exchange-back-end/
mvn package -Dquarkus.package.type=fast-jar
cd ..
printf "\n"
printf "============ Exchange Project builded successfully by  Maven ============\n"
printf "\n\n\n"

printf "=========== Building local Broker Project with local Maven ==============\n"
printf "\n"
cd broker-back-end/
mvn package -Dquarkus.package.type=fast-jar
cd ..
printf "\n"
printf "============= Broker Project builded successfully by  Maven ==============\n"
printf "\n\n\n"

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