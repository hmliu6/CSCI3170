// Java Program to illustrate reading from FileReader
// using Scanner Class reading entire File
// without using loop
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadData
{
public static void main(String[] args) throws FileNotFoundException
{
    String st = "hi ";

    st += "hihi ";
    System.out.println(st);

	// load_data_category();
	// load_data_user();
	// load_data_book_and_author();
	// load_data_checkout_record();
    
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


    /* load test data for table: category */
    public static void load_data_category() throws FileNotFoundException {
    
    File file = new File("category.txt");
    Scanner sc = new Scanner(file);
    String data_string;

    while (sc.hasNextLine())
        System.out.println(sc.nextLine());
    }

    /* load test data for table: User */
    public static void load_data_user() throws FileNotFoundException {

    File file = new File("user.txt");
    Scanner sc = new Scanner(file);
    while (sc.hasNextLine())
      System.out.println(sc.nextLine());
 

    }

     /* load test data for tables: Book , copy and Author*/
    public static void load_data_book_and_author() throws FileNotFoundException {
    File file = new File("book.txt");
    Scanner sc = new Scanner(file);
    while (sc.hasNextLine())
      System.out.println(sc.nextLine());
    }



    /* load test data for table: Checkout_record */
        /* load test data for table: Checkout_record */
    public static void load_data_checkout_record() throws FileNotFoundException {
    File file = new File("checkout.txt");
    Scanner sc = new Scanner(file);
    while (sc.hasNextLine())
      System.out.println(sc.nextLine());

    }
    
}
