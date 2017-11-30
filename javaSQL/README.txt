CSCI3170 Group Project

Group Number:   8

Group Members:  LIU Ho Man,     1155077343
                KWOK Tung Lim,  1155079818
                WONG Tsz Hin,   1155079510

List of files:  JavaSQL.java    -   The main program to communicate with MySQL server
                Makefile        -   Complie Java source code

Methods of compilation and execution:

Before compilation:
    - Modify JavaSQL.java to work on other MySQL server environment
    - In Line 25-27,
        String url = "jdbc:mysql://appsrvdb.cse.cuhk.edu.hk/CSCI3170S10";
        String username = "CSCI3170S10";
        String password = "csci3170Project!";
      Change the above parameters to other environment
    - In Line 34,
        stmt.executeUpdate("use CSCI3170S10;");
      We assumed that we are using existing database

For compilation:
    - The jdbc.jar file should be placed in the same directory

For execution:
    - java -cp .:jdbc.jar JavaSQL