import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JavaSQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
        String url = "jdbc:mysql://appsrvdb.cse.cuhk.edu.hk/CSCI3170S10";
        String username = "CSCI3170S10";
        String password = "csci3170Project!";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            conn.close();
            System.out.println("FUCK YOU");
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

}
