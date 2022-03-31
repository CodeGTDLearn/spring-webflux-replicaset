@echo off
echo ===========================================================================
echo                        1) DOCKER-COMPOSE: Starts...
echo ===========================================================================

echo ===========================================================================
echo                         2) DOCKER-COMPOSE: Maven
echo ===========================================================================
cd ..
call mvn clean package -DskipTests

echo ===========================================================================
echo                        3) DOCKER-COMPOSE: Cleaning
echo ===========================================================================
cd docker
call clean.bat
docker scan --version --json --group-issues

set parameter1=%1
echo ===========================================================================
echo       4) DOCKER-COMPOSE: Uping the Compose-Service(s): %parameter1%
echo ===========================================================================
if %parameter1%==standalone (docker-compose -f compose-standalone.yml up --build --force-recreate)
if %parameter1%==replicaset (docker-compose -f compose-replicaset.yml   up --build --force-recreate)

echo ===========================================================================
echo                     5) DOCKER-COMPOSE: ...Ending
echo ===========================================================================