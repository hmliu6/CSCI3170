// Java Program to illustrate reading from FileReader
// using Scanner Class reading entire File
// without using loop
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Date;

import java.text.DateFormat;

public class LoadData
{
public static void main(String[] args) throws FileNotFoundException
{
   librarian_operation();
}

public static void librarian_operation(){
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
        bookBorrowing();
      else if(input == 2)
        System.exit(1);
      else if(input == 3)
        System.exit(1);
      else if(input == 4)
        System.exit(1);
      else
        librarian_operation();
    }

    /* librarian function 1 : book borrowing */
    public static void bookBorrowing(){
      /* Input user info: user id, book info: call number and copy nubmer*/
      String userID;
      String call_number;
      int copy_number;
      Scanner scan = new Scanner(System.in);
      System.out.print("Enter The User ID: ");
      userID = scan.nextLine();
      System.out.print("Enter The Call Number: ");
      call_number = scan.nextLine();
      System.out.print("Enter The Copy Number: ");
      copy_number = scan.nextInt();

      /* Check the availablity of the book with callNumber and copyNumber */
      String sqlStatement_check;
      PreparedStatement pstmt_check;

      sqlStatement_check = "SELECT * FROM " + 
                         "checkout_record WHERE " +
                         "call_number = ? AND " +
                         "copy_number = ? AND " +
                         "return_date <> NULL;";
      pstmt_check = conn.prepareStatement(sqlStatement_check);
      pstmt_check.setString(1, call_number);
      pstmt_check.setString(2, copy_number);
      ResultSet rs_check = pstmt_check.execute();
      /* If the result is empty, borrow the book, otherwise do nothing and show message */
      if(!rs_check.next()){
        /* Borrow the book */
        String sqlStatement_borrow;
        PreparedStatement pstmt_borrow;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        /* Insert record */
        sqlStatement_borrow = "INSERT INTO checkout_record (call_number, copy_number, user_id, checkout_date, return_date) VALUES (?, ?, ?, ?, NULL)";
        pstmt_borrow = conn.prepareStatement(sqlStatement_borrow);
        pstmt_borrow.setString(1, call_number);
        pstmt_borrow.setString(2, copy_number);
        pstmt_borrow.setString(3, userID);
        pstmt_borrow.setString(4, dateFormat.format(date));

        /* execute SQL */
        if(pstmt_borrow.execute()){
          /* Informative message of successfully checkout */
          System.out.println("Book checkout performed successfully!!" );
        }else{
          System.out.println("Book checkout failed to perform!!" );
        }
      }else{
        /* The book has been borrowed! */
        System.out.println("[Error]: The Book (Call Number: "+ call_number+" , Copy Number: "+copy_number+") has been borrowed!" );
      }

    }

    /* librarian function 2 : book returning */
    public static void bookReturning(){
      /* Input user info: user id, book info: call number and copy nubmer*/
      String userID;
      String call_number;
      int copy_number;
      Scanner scan = new Scanner(System.in);
      System.out.print("Enter The User ID: ");
      userID = scan.nextLine();
      System.out.print("Enter The Call Number: ");
      call_number = scan.nextLine();
      System.out.print("Enter The Copy Number: ");
      copy_number = scan.nextInt();

      /* Check the existence of the checkout record with the user info and book info */
      String sqlStatement_check;
      PreparedStatement pstmt_check;

      sqlStatement_check = "SELECT * FROM " + 
                         "checkout_record WHERE " +
                         "user_id = ? AND "+
                         "call_number = ? AND " +
                         "copy_number = ? AND " +
                         "return_date == NULL;";
      pstmt_check = conn.prepareStatement(sqlStatement_check);
      pstmt_check.setString(1, userID);
      pstmt_check.setString(2, call_number);
      pstmt_check.setString(3, copy_number);
      
      ResultSet rs_check = pstmt_check.execute();
      /* If the result is NOT empty, return the book, otherwise do nothing and show message */
      if(rs_check.next()){
        /* return the book */
        String sqlStatement_return;
        PreparedStatement pstmt_return;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        /* Update table */
        sqlStatement_return = "UPDATE checkout_record "+
                            "SET return_date = ? "+
                            "WHERE user_id = ? AND "+
                            "call_number = ? AND "+
                            "copy_number = ?;";
        pstmt_return = conn.prepareStatement(sqlStatement_return);
        pstmt_return.setString(1, dateFormat.format(date));
        pstmt_return.setString(2, userID);
        pstmt_return.setString(3, call_number);
        pstmt_return.setString(4, copy_number);

        /* execute SQL */
        if(pstmt_return.execute()){
          /* Informative message of successfully checkout */
          System.out.println("Book returning performed successfull!!!" );
        }else{
          System.out.println("Book returning failed to perform!!" );
        }
      }else{
        /* The book has been return or some reason we cannot find the record! */
        System.out.println("[Error]: Cannot found such record!" );
      }
    }

    /* librarian function 3 : list all un-returned book copies */
    public static void listUnreturnedBooks(){
      /* Input the start date and end date for */
      String startDate;
      String endDate;
      Scanner scan = new Scanner(System.in);
      System.out.print("Type in the starting date [dd/mm/YYYY]: ");
      startDate = scan.nextLine();
      System.out.print("Type in the ending date [dd/mm/YYYY]: ");
      endDate = scan.nextLine();

      /* Get unreturn data 
      checkout_record (user_id,   call_number,   copy_number,    checkout_date,  return_date) */
      String sqlStatement_unreturn;
      PreparedStatement pstmt_unreturn;
      sqlStatement_unreturn = "SELECT user_id, call_number, copy_number, checkout_date FROM " + 
                           "checkout_record WHERE " +
                           "checkout_date BETWEEN ? AND ?)"+
                           "AND return_date == NULL "+
                           "ORDER BY checkout_date DESC;";
      pstmt_unreturn = conn.prepareStatement(sqlStatement_unreturn);
      pstmt_unreturn.setString(1, startDate);
      pstmt_unreturn.setString(2, endDate);

      /* Printing the result to console */
      ResultSet rs_unreturn = pstmt_unreturn.execute();
      System.out.println("| User ID | Call Number | Copy Number | Checkout Date |");
      Boolean hasResult = false;
      String result_userID = "", result_callNubmer = "", result_checkoutDate = "";
      int result_copyNumber;
      while( rs_unreturn.next() ){
        hasResult = true;
        result_userID = rs_unreturn.getString();
        result_callNubmer = rs_unreturn.getString();
        result_copyNumber = rs_unreturn.getInt();
        result_checkoutDate = rs_unreturn.getString();
        System.out.println("| " + result_userID + " | " + result_callNubmer + " | " + result_copyNumber + " | " + result_checkoutDate + " | ";
      if(!hasResult)
        throw new Exception("no output");
      else
        System.out.println("| " + result_userID + " | " + result_callNubmer + " | " + result_copyNumber + " | " + result_checkoutDate + " | ";
      }

      


          /* Checking SQL */
    // SELECT * from checkout_record where call_number = '?' AND copy_number = '?' AND return_date != NULL;
    /* 
    Test Data List:

    book.txt : { call_number , copy_number , title, arthur_name ,publish_date}
    category.txt : { category_id , loan_period , max_books }
    checkout.txt : { call_number, copy_number, user_id , checkout_date, return_date) }
    user.txt :{ user_id , user_name , user_address , category_id }

    Relational  Schema:
    category    (id,    loan_period,    max_books)
    user    (id,    name, address,  category_id)
    book    (call_number,   title,  publish_date)
    copy    (call_number,   copy_number)
    checkout_record (user_id,   call_number,   copy_number,    checkout_date,  return_date)
    author (name,   call_number)

    */
    }

/* 
    Test Data List:

    book.txt : { call_number , copy_number , title, arthur_name ,publish_date}
    category.txt : { category_id , loan_period , max_books }
    checkout.txt : { call_number, copy_number, user_id , checkout_date, return_date) }
    user.txt :{ user_id , user_name , user_address , category_id }

    Relational  Schema:
    category    (id,    loan_period,    max_books)
    user    (id,    name, address,  category_id)
    book    (call_number,   title,  publish_date)
    copy    (call_number,   copy_number)
    checkout_record (user_id,   call_number,   copy_number,    checkout_date,  return_date)
    author (name,   call_number)

    */



    /* 
    Example 

      System.out.println("Inserting records into the table...");
      stmt = conn.createStatement();
      
      String sql = "INSERT INTO Registration " +
                   "VALUES (100, 'Zara', 'Ali', 18)";
      stmt.executeUpdate(sql);
      sql = "INSERT INTO Registration " +
                   "VALUES (101, 'Mahnaz', 'Fatma', 25)";
      stmt.executeUpdate(sql);
      sql = "INSERT INTO Registration " +
                   "VALUES (102, 'Zaid', 'Khan', 30)";
      stmt.executeUpdate(sql);
      sql = "INSERT INTO Registration " +
                   "VALUES(103, 'Sumit', 'Mittal', 28)";
      stmt.executeUpdate(sql);
      System.out.println("Inserted records into the table...");
     */


   //  /* load test data for table: category */
   // public static void load_data_category() throws FileNotFoundException {

   //  File file = new File("category.txt");
   //  Scanner sc = new Scanner(file);
   //  String[] data; 
   //  data = new data[3]; 
   //  int counter = 0;

   //  PreparedStatement ps = conn.prepareStatement("INSERT INTO category (id, loan_period, max_books) VALUES(?, ?, ?);");

   //  while (sc.hasNextLine()){
   //      sc.nextLine();
   //      sc.split("\t");
   //      while(sc != null){
   //          data[counter++] = sc;
   //      }
   //      ps.setString(data[0],data[1],data[2]);
   //  }
    
   //  ps.execute();
   //  return;
   //  }

   //  /* load test data for table: User */
   //  public static void load_data_user() throws FileNotFoundException {

   //  File file = new File("user.txt");
   //  Scanner sc = new Scanner(file);
   //  while (sc.hasNextLine())
   //    System.out.println(sc.nextLine());
 

   //  }

   //   /* load test data for tables: Book , copy and Author*/
   //  public static void load_data_book_and_author() throws FileNotFoundException {
   //  File file = new File("book.txt");
   //  Scanner sc = new Scanner(file);
   //  while (sc.hasNextLine())
   //    System.out.println(sc.nextLine());
   //  }



   //  /* load test data for table: Checkout_record */
   //      /* load test data for table: Checkout_record */
   //  public static void load_data_checkout_record() throws FileNotFoundException {
   //  File file = new File("checkout.txt");
   //  Scanner sc = new Scanner(file);
   //  while (sc.hasNextLine())
   //    System.out.println(sc.nextLine());

   //  }
    
}
