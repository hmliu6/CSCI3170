/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author homanliu
 */
public class JavaSQL {



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException{
        // TODO code application logic here
        // Connection conn = null;
        // try {
        //     conn = DriverManager.getConnection("jdbc:mysql://appsrvdb.cse.cuhk.edu.hk/CSCI3170S10?" + 
        //                                        "user=CSCI3170S10&password=csci3170Project!");
        // } catch (SQLException ex) {
        //     // handle any errors
        //     System.out.println("SQLException: " + ex.getMessage());
        //     System.out.println("SQLState: " + ex.getSQLState());
        //     System.out.println("VendorError: " + ex.getErrorCode());
        load_data_category();
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

    /* load test data for table: category */
    public static void load_data_category()throws FileNotFoundException{

    File file = new File("category.txt");
    Scanner sc = new Scanner(file);
    while (sc.hasNextLine())
      System.out.println(sc.nextLine());
    }

    /* load test data for table: User */
    public static void load_data_user(){

    }

     /* load test data for tables: Book , copy and Author*/
    public static void load_data_book_and_author(){

    }


    /* load test data for table: Checkout_record */
    public static void load_data_checkout_record(){

    }

    
}
