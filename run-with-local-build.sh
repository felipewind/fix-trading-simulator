#!/bin/bash

printf "========== Executing Docker Compose with Build to start the application =========\n"
printf "\n"


printf "========== Building local Exchange Back End Project with local Maven ============\n"
printf "\n"
cd exchange-back-end/
./mvnw package
cd ..
printf "\n"
printf "============ Exchange Back End Project builded successfully by  Maven =============\n"
printf "\n\n\n"


printf "=========== Building local Broker Back End Project with local Maven ===============\n"
printf "\n"
cd broker-back-end/
./mvnw package
cd ..
printf "\n"
printf "============= Broker Back End Project builded successfully by  Maven ==============\n"
printf "\n\n\n"


printf "======== Building local Exchange Front End Project with local Angular/NPM =========\n"
printf "\n"
cd exchange-front-end/
npm install
ng build --prod
cd ..
printf "\n"
printf "========== Exchange Front End Project builded successfully by Angular/NPM =========\n"
printf "\n\n\n"


printf "========= Building local Broker Front End Project with local Angular/NPM ==========\n"
printf "\n"
cd broker-front-end/
npm install
ng build --prod
cd ..
printf "\n"
printf "=========== Broker Front End Project builded successfully by Angular/NPM ===========\n"
printf "\n\n\n"


function ctrl_c() {
printf "\n"
printf '===================================================================================\n'
printf '================= EXECUTIN DOCKER COMPOSE DOWN AFTER CTRL+C =======================\n'
printf '===================================================================================\n'
printf "\n"
docker-compose -f ./docker-compose.yml down
exit
}
trap ctrl_c INT

docker-compose -f ./docker-compose.yml up --build

ctrl_c