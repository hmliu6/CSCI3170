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
docker run -d -v {LOCAL DIR}:/var/lib/mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=test centurylink/mysql
# Port number = 3306
# username = root, password = test
# {LOCAL DIR}: To store mysql database permanent
# By mounting local directory to docker volume
```
OR
```bash
bash javaSQL/hostMySQL.sh
# By default, localhost:3306 , username = root , password = test
# Need to create mysql/ in Desktop
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
3. `java -cp .:jdbc.jar JavaSQL`


### Progress ###
Testing

1. Operations for administrator menu
	- [ ] 1.1 Create all tables
	- [ ] 1.2 Delete all tables
	- [ ] 1.3 Load Data
	- [x] 1.4 Show number of records in each table
	- [ ] 1.5 Return to the main menu

2. Operations for library user menu
	- [ ] 2.1 Search for books
	- [ ] 2.2 Show checkout records of a user
	- [ ] 2.3 Return to the main menu

3. Operations for librarian menu
	- [ ] 3.1 Book Borrowing
	- [ ] 3.2 Book Returning
	- [ ] 3.3 List all un-returned book copies which are checked out within a period
	- [ ] 3.4 Return to the main menu


