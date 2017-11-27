### Connect to CSE CUHK environment

The connection is only establishable in `linux1.cse.cuhk.edu.hk`

1. Copy whole file to cse machine
```bash
scp -r javaSQL/ {CSE USERNAME}@gw.cse.cuhk.edu.hk:{DIRECTORY}
```

2. Modify `JavaSQL.java`

```java
String url = "jdbc:mysql://appsrvdb.cse.cuhk.edu.hk/CSCI3170S10";
String username = "CSCI3170S10";
String password = "csci3170Project!";
// With equal port number and password in above
```

3. `make`

4. Run Java application
```bash
java -cp .:jdbc.jar JavaSQL
```

### Create Local MySQL environment

Using Docker and create own MySQL server for testing

1. Install Docker
2. Pull Docker image

```bash
docker pull centurylink/mysql
```

3. Run Docker and create container

```bash
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=test centurylink/mysql
# Port number = 3306
# username = root, password = test
```
OR
```bash
bash javaSQL/hostMySQL.sh
# By default, localhost:3306 , username = root , password = test
```

To kill container after testing, check container ID by `docker container ls`

Then, kill container `docker stop ${CONTAINER_ID}`

### Running Java in local environment

1. Modify `JavaSQL.java`

```java
String url = "jdbc:mysql://localhost:3306";
String username = "root";
String password = "test";
// With equal port number and password in above
```

2. `make`
3. `java JavaSQL`
