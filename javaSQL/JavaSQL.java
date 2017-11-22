import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
            testfunc(conn);
            main_menu();
            conn.close();
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch (Exception exp){
          System.out.println("Exception: " + exp.getMessage());
        }
    }

    public static void main_menu(){
      System.out.println("\n-----Main Menu-----");
      System.out.println("What kinds of operations would you like to perform?");
      System.out.println("1. Operations for administrator");
      System.out.println("2. Operations for library user");
      System.out.println("3. Operations for librarian");
      System.out.println("4. Exit this program");
      System.out.print("Enter Your Choice: ");
      Scanner scan = new Scanner(System.in);
      int input = scan.nextInt();
      if(input == 1)
        admin_operation();
      else if(input == 2)
        user_operation();
      else if(input == 3)
        librarian_operation();
      else if(input == 4){
        System.out.println("GoodBye!!!");
        System.exit(1);
      }
      else
        main_menu();
    }

    public static void admin_operation(){
      System.out.println("\n-----Operations for administrator menu-----");
      System.out.println("What kinds of operations would you like to perform?");
      System.out.println("1. Create all tables");
      System.out.println("2. Delete all tables");
      System.out.println("3. Load Data");
      System.out.println("4. Show number of records in each table");
      System.out.println("5. Return to the main menu");
      System.out.print("Enter Your Choice: ");
      Scanner scan = new Scanner(System.in);
      int input = scan.nextInt();
      if(input == 1)
        System.exit(1);
      else if(input == 2)
        System.exit(1);
      else if(input == 3)
        System.exit(1);
      else if(input == 4)
        System.exit(1);
      else if(input == 5)
        main_menu();
      else
        admin_operation();
    }

    public static void user_operation(){
      System.out.println("\n-----Operations for library user menu-----");
      System.out.println("What kinds of operations would you like to perform?");
      System.out.println("1. Search for books");
      System.out.println("2. Shoe checkout records of a user");
      System.out.println("3. Return to the main menu");
      System.out.print("Enter Your Choice: ");
      Scanner scan = new Scanner(System.in);
      int input = scan.nextInt();
      if(input == 1)
        System.exit(1);
      else if(input == 2)
        System.exit(1);
      else if(input == 3)
        main_menu();
      else
        user_operation();
    }

    public static void librarian_operation(){
      System.out.println("\n-----Operations for administrator menu-----");
      System.out.println("What kinds of operations would you like to perform?");
      System.out.println("1. Book Borrowing");
      System.out.println("2. Book Returning");
      System.out.println("3. List all un-returned book copies which are checked out within a period");
      System.out.println("4. Return to the main menu");
      System.out.print("Enter Your Choice: ");
      Scanner scan = new Scanner(System.in);
      int input = scan.nextInt();
      if(input == 1)
        System.exit(1);
      else if(input == 2)
        System.exit(1);
      else if(input == 3)
        System.exit(1);
      else if(input == 4)
        main_menu();
      else
        librarian_operation();
    }

    public static void testfunc(Connection conn){
      try{
        Statement stmt = conn.createStatement();
      }
      catch (Exception exp){
        System.out.println("Exception: " + exp.getMessage());
      }
    }
}
