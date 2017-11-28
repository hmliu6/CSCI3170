import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;


public class JavaSQL {
    public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306";
        String username = "root";
        String password = "test";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            stmt.executeQuery("use project");
            main_menu(conn);
            conn.close();
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch (Exception exp){
          if(exp != null)
            System.out.println("Exception: " + exp.getMessage());
        }
    }

    public static void main_menu(Connection conn){
      int input;
      System.out.println("\n-----Main Menu-----");
      System.out.println("What kinds of operations would you like to perform?");
      System.out.println("1. Operations for administrator");
      System.out.println("2. Operations for library user");
      System.out.println("3. Operations for librarian");
      System.out.println("4. Exit this program");
      Scanner scan = new Scanner(System.in);
      do{
        System.out.print("Enter Your Choice: ");
        input = scan.nextInt();
      } while(input < 1 || input > 4);
      if(input == 1)
        admin_operation(conn);
      else if(input == 2)
        user_operation(conn);
      else if(input == 3)
        librarian_operation(conn);
      else if(input == 4){
        System.out.println("GoodBye!!!");
      }
    }

    public static void admin_operation(Connection conn){
      int input;
      System.out.println("\n-----Operations for administrator menu-----");
      System.out.println("What kinds of operations would you like to perform?");
      System.out.println("1. Create all tables");
      System.out.println("2. Delete all tables");
      System.out.println("3. Load Data");
      System.out.println("4. Show number of records in each table");
      System.out.println("5. Return to the main menu");
      Scanner scan = new Scanner(System.in);
      do{
        System.out.print("Enter Your Choice: ");
        input = scan.nextInt();
      } while(input < 1 || input > 5);
      if(input == 1)
        System.exit(1);
      else if(input == 2)
        dropAllTable(conn);
      else if(input == 3)
        LoadData(conn);
      else if(input == 4)
        showAllTable(conn);
      else if(input == 5)
        main_menu(conn);
    }

    /* Load ALL test data for database */
    public static void LoadData(Connection conn){
      try{
      System.out.println("Processing...\n");
      loadDataCategory(conn);
      loadDataUser(conn);
      loadDataBookAndAuthor(conn);
      loadDataCheckoutRecord(conn);
      System.out.println("Data are loaded successfully!\n");}
      catch(Exception ex){
        System.out.println("Error: " + ex);
      }
      return;
    }
    /* load testdata for table: Category */
    public static void loadDataCategory(Connection conn) throws Exception {
      File file = new File("category.txt");
      Scanner scan = new Scanner(file);

      PreparedStatement pstmt = conn.prepareStatement("INSERT INTO category (id, loan_period, max_books) VALUES(?, ?, ?);");
      while (scan.hasNextLine()){
        String data = scan.nextLine();
        String[] result = data.split("\t");
        for(int i=0; i<result.length; i++)
          pstmt.setString(i+1, result[0]);
      }
      pstmt.execute();
      System.out.println("Data of Category have been loaded successfully!\n");
      return;
    }

    /* load test data for table: User */
    public static void loadDataUser(Connection conn) throws Exception {
      File file = new File("user.txt");
      Scanner scan = new Scanner(file);

      PreparedStatement pstmt = conn.prepareStatement("INSERT INTO category (id, name, address, category_id) VALUES(?, ?, ?, ?);");
      while (scan.hasNextLine()){
        String data = scan.nextLine();
        String[] result = data.split("\t");
        for(int i=0; i<result.length; i++)
          pstmt.setString(i+1, result[0]);
      }
      pstmt.execute();
      System.out.println("Data of User have been loaded successfully!\n");
      return;
    }

    /* load test data for tables: Book , Copy and Author*/
    public static void loadDataBookAndAuthor(Connection conn) throws Exception {
      File file = new File("book.txt");
      Scanner scan = new Scanner(file);

      PreparedStatement ps_book = conn.prepareStatement("INSERT INTO category (call_number, title, publish_date) VALUES(?, ?, ?);");
      PreparedStatement ps_copy = conn.prepareStatement("INSERT INTO copy (call_number, copy_number) VALUES(?,?);");
      PreparedStatement ps_author = conn.prepareStatement("INSERT INTO author(name, call_number) VALUES(?,?);");
      

      }

    /* load test data for table: Checkout_record */
    public static void loadDataCheckoutRecord(Connection conn) throws Exception {
      File file = new File("checkout.txt");
      Scanner scan = new Scanner(file);
      while (scan.hasNextLine())
        System.out.println(scan.nextLine());

      }

    public static void dropAllTable(Connection conn){
      System.out.println("Processing...");
      String[] tables = {"category", "user", "book", "copy", "checkout_record", "author"};
      String sqlStatement = "DROP DATABASE IF EXISTS  ";
      try{
        for(int i=0; i<tables.length; i++){
          String temp = sqlStatement + tables[i];
          PreparedStatement pstmt = conn.prepareStatement(temp);
          ResultSet rs = pstmt.executeQuery();
        }
        System.out.println("Done! Database is removed!");
      }
      catch (Exception ex){
        System.out.println("Error: " + ex);
      }
    }

    public static void showAllTable(Connection conn){
      System.out.println("\nNumber of records in each table: ");
      String[] tables = {"category", "user", "book", "copy", "checkout_record", "author"};
      String sqlStatement = "SELECT COUNT(*) FROM ";
      try{
        for(int i=0; i<tables.length; i++){
          String temp = sqlStatement + tables[i];
          PreparedStatement pstmt = conn.prepareStatement(temp);
          ResultSet rs = pstmt.executeQuery();
          // Move cursor to data
          rs.next();
          int count = rs.getInt("count(*)");
          System.out.println("\033[3m" + tables[i] + "\033[0m" + ": " + count);
        }
      }
      catch (Exception ex){
        System.out.println("Error: " + ex);
      }
    }

    public static void user_operation(Connection conn){
      int input;
      System.out.println("\n-----Operations for library user menu-----");
      System.out.println("What kinds of operations would you like to perform?");
      System.out.println("1. Search for books");
      System.out.println("2. Show checkout records of a user");
      System.out.println("3. Return to the main menu");
      Scanner scan = new Scanner(System.in);
      do{
        System.out.print("Enter Your Choice: ");
        input = scan.nextInt();
      } while(input < 1 || input > 3);
      if(input == 1)
        bookSearch(conn);
      else if(input == 2)
        showUserRecord(conn);
      else if(input == 3)
        main_menu(conn);
      else
        user_operation(conn);
    }

    public static void bookSearch(Connection conn){
      int input;
      String searchKey, callNumber;
      System.out.println("Choose the search criteria:");
      System.out.println("1. Call Number");
      System.out.println("2. Title");
      System.out.println("3. Author");
      Scanner scan = new Scanner(System.in);
      // Get searching criteria
      do{
        System.out.print("Choose the search criteria: ");
        input = scan.nextInt();
      } while(input < 1 || input > 3);
      Boolean hasResult = false;
      try{
        System.out.print("Type in the search keyword: ");
        Scanner keyword = new Scanner(System.in);
        String sqlStatement;
        PreparedStatement pstmt;
        // Build SQL statement
        if(input == 1){
          callNumber = keyword.nextLine();
          sqlStatement = "SELECT * FROM " + 
                         "book, copy, author WHERE " + 
                         "book.call_number = copy.call_number AND " + 
                         "book.call_number = author.call_number AND " + 
                         "author.call_number = copy.call_number AND " +
                         "book.call_number = ?";
          pstmt = conn.prepareStatement(sqlStatement);
          pstmt.setString(1, callNumber);
        }
        else if(input == 2){
          searchKey = keyword.nextLine();
          sqlStatement = "SELECT * FROM " + 
                         "book, copy, author WHERE " + 
                         "book.call_number = copy.call_number AND " + 
                         "book.call_number = author.call_number AND " + 
                         "author.call_number = copy.call_number AND " +
                         "book.title LIKE ?";
          pstmt = conn.prepareStatement(sqlStatement);
          searchKey = "%" + searchKey + "%";
          pstmt.setString(1, searchKey);
        }
        else{
          searchKey = keyword.nextLine();
          sqlStatement = "SELECT * FROM " + 
                         "book, copy, author WHERE " + 
                         "book.call_number = copy.call_number AND " + 
                         "book.call_number = author.call_number AND " + 
                         "author.call_number = copy.call_number AND " +
                         "author.name LIKE ?";
          pstmt = conn.prepareStatement(sqlStatement);
          searchKey = "%" + searchKey + "%";
          pstmt.setString(1, searchKey);
        }
        // Parse Output
        ResultSet rs = pstmt.executeQuery();
        System.out.println("| Call Number | Title | Author |  Available Copies |");
        int copyResult = 0;
        String titleResult = "", authorResult = "", callResult = "";
        while( rs.next() ){
          hasResult = true;
          String callTemp = rs.getString("call_number");
          if(callTemp.equals(callResult)){
            authorResult = authorResult + ", " + rs.getString("name");
          }
          else{
            if(!callResult.equals(""))
              System.out.println("| " + callResult + " | " + titleResult + " | " + authorResult + " | " + copyResult + "  |");
            callResult = callTemp;
            titleResult = rs.getString("title");
            authorResult = rs.getString("name");
            copyResult = rs.getInt("copy_number");
          }
        }
        if(!hasResult)
          throw new Exception("no output");
        else
          System.out.println("| " + callResult + " | " + titleResult + " | " + authorResult + " | " + copyResult + "  |");
      }
      catch (Exception exp){
        System.out.println("[Error]: An matching search record is not found. The input does not exist in database.");
      }
    }

    public static void showUserRecord(Connection conn){
      String userID;
      try{
        System.out.print("Enter the User ID: ");
        Scanner scan = new Scanner(System.in);
        String sqlStatement;
        PreparedStatement pstmt;
        // Build SQL statement
        userID = scan.nextLine();
        sqlStatement = "SELECT * FROM " + 
                       "book, copy, author, checkout_record WHERE " + 
                       "book.call_number = copy.call_number AND " + 
                       "book.call_number = author.call_number AND " + 
                       "author.call_number = copy.call_number AND " +
                       "checkout_record.call_number = book.call_number AND " + 
                       "checkout_record.user_id = ?";
        System.out.println(sqlStatement);
        pstmt = conn.prepareStatement(sqlStatement);
        pstmt.setString(1, userID);

        // Parse Output
        ResultSet rs = pstmt.executeQuery();
        System.out.println("| Call Number | Copy Number | Title | Author | Check-out | Returned? |");
        Boolean hasResult = false;
        int copyResult = 0;
        String titleResult = "", authorResult = "", callResult = "", checkoutResult = "", returnResult = "";
        while( rs.next() ){
          hasResult = true;
          String callTemp = rs.getString("call_number");
          if(callTemp.equals(callResult)){
            authorResult = authorResult + ", " + rs.getString("name");
          }
          else{
            if(!callResult.equals(""))
              System.out.println("| " + callResult + " | " + copyResult + " | " + titleResult + " | " + authorResult + " | " + checkoutResult + " | " + returnResult + "  |");
            callResult = callTemp;
            copyResult = rs.getInt("copy_number");
            titleResult = rs.getString("title");
            authorResult = rs.getString("name");
            checkoutResult = rs.getString("checkout_date");
            returnResult = rs.getString("return_date");
            if(returnResult.equals(""))
              returnResult = "No";
            else
              returnResult = "Yes";
          }
        }
      if(!hasResult)
        throw new Exception("no output");
      else
        System.out.println("| " + callResult + " | " + copyResult + " | " + titleResult + " | " + authorResult + " | " + checkoutResult + " | " + returnResult + "  |");
      }
      catch (Exception exp){
        System.out.println("[Error]: An matching search record is not found. The input does not exist in database.");
      }
    }

    public static void librarian_operation(Connection conn){
      int input;
      System.out.println("\n-----Operations for librarian menu-----");
      System.out.println("What kinds of operations would you like to perform?");
      System.out.println("1. Book Borrowing");
      System.out.println("2. Book Returning");
      System.out.println("3. List all un-returned book copies which are checked out within a period");
      System.out.println("4. Return to the main menu");
      Scanner scan = new Scanner(System.in);
      do{
        System.out.print("Enter Your Choice: ");
        input = scan.nextInt();
      } while(input < 1 || input > 4);
      if(input == 1)
        System.exit(1);
      else if(input == 2)
        System.exit(1);
      else if(input == 3)
        System.exit(1);
      else if(input == 4)
        main_menu(conn);
      else
        librarian_operation(conn);
    }


}
