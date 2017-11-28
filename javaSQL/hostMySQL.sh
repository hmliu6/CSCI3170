#!/bin/sh

docker run -d -v ~/Desktop/mysql:/var/lib/mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=test centurylink/mysql
