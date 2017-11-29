#!/bin/sh

#create docker 
docker run -d -v ~/Desktop/mysql:/var/lib/mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=test centurylink/mysql

#docker images
#$docker container ls

#exec docker
#$docker exec -it {CONTAINER ID} bash
#$docker exec -it {9b56} bash

#running java program
#$java -cp .:jdbc.jar JavaSQL