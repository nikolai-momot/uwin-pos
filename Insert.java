import java.sql.*;
import java.util.Calendar;
 
/**
 * A Java MySQL PreparedStatement INSERT example.
 * Demonstrates the use of a SQL INSERT statement against a
 * MySQL database, called from a Java program, using a
 * Java PreparedStatement.
 * 
 * Created by Alvin Alexander, <a href="http://devdaily.com" title="http://devdaily.com">http://devdaily.com</a>
 */
public class Insert


{
	static int Itemnumber;
	static String name;
	static String description;
	static Double price;
	
 
  public static void main(String[] args)
  {
    try
    {
      // create a mysql database connection
      String myDriver = "org.gjt.mm.mysql.Driver";
      String myUrl = "jdbc:mysql://localhost/ShawarmaPOS";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "");
     
      // create a sql date object so we can use it in our INSERT statement
      Calendar calendar = Calendar.getInstance();
      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
 
      // the mysql insert statement
      String query = " insert into menu (itemnumber, name, description, price)"
        + " values (?, ?, ?, ?)";
 
      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setInt (1, Itemnumber);
      preparedStmt.setString (2, name);
      preparedStmt.setString   (3, description);
      preparedStmt.setDouble(4, price);
      
      // execute the preparedstatement
      preparedStmt.execute();
       
      conn.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
  }
}