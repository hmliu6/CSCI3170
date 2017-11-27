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
      do{
        System.out.print("Enter Your Choice: ");
        Scanner scan = new Scanner(System.in);
        input = scan.nextInt();
        scan.close();
      } while(input > 0 && input < 5);
      if(input == 1)
        admin_operation(conn);
      else if(input == 2)
        user_operation(conn);
      else if(input == 3)
        librarian_operation(conn);
      else if(input == 4){
        System.out.println("GoodBye!!!");
        System.exit(1);
      }
      else
        main_menu(conn);
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
      do{
        System.out.print("Enter Your Choice: ");
        Scanner scan = new Scanner(System.in);
        input = scan.nextInt();
        scan.close();
      } while(input > 0 && input < 6);
      if(input == 1)
        System.exit(1);
      else if(input == 2)
        System.exit(1);
      else if(input == 3)
        System.exit(1);
      else if(input == 4)
        System.exit(1);
      else if(input == 5)
        main_menu(conn);
    }

    public static void user_operation(Connection conn){
      int input;
      System.out.println("\n-----Operations for library user menu-----");
      System.out.println("What kinds of operations would you like to perform?");
      System.out.println("1. Search for books");
      System.out.println("2. Shoe checkout records of a user");
      System.out.println("3. Return to the main menu");
      do{
        System.out.print("Enter Your Choice: ");
        Scanner scan = new Scanner(System.in);
        input = scan.nextInt();
        scan.close();
      } while(input > 0 && input < 4);
      if(input == 1)
        bookSearch(conn);
      else if(input == 2)
        showUserRecord(conn);
      else if(input == 3)
        main_menu(conn);
      else
        user_operation(conn);
    }

    public static void librarian_operation(Connection conn){
      int input;
      System.out.println("\n-----Operations for librarian menu-----");
      System.out.println("What kinds of operations would you like to perform?");
      System.out.println("1. Book Borrowing");
      System.out.println("2. Book Returning");
      System.out.println("3. List all un-returned book copies which are checked out within a period");
      System.out.println("4. Return to the main menu");
      do{
        System.out.print("Enter Your Choice: ");
        Scanner scan = new Scanner(System.in);
        input = scan.nextInt();
        scan.close();
      } while(input > 0 && input < 5);
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

    public static void bookSearch(Connection conn){
      int input;
      System.out.println("Choose the search criteria:");
      System.out.println("1. Call Number");
      System.out.println("2. Title");
      System.out.println("3. Author");
      do{
        System.out.print("Choose the search criteria: ");
        Scanner scan = new Scanner(System.in);
        input = scan.nextInt();
        scan.close();
      } while(input > 0 && input < 4);
      try{
        Statement stmt = conn.createStatement();
        stmt.close();
      }
      catch (Exception exp){
        System.out.println("Exception: " + exp.getMessage());
      }
    }

    public static void showUserRecord(Connection conn){
      System.out.print("Enter the User ID: ");
      Scanner scan = new Scanner(System.in);
      String input = scan.nextLine();
      scan.close();
    }
}
