//STEP 1. Import required packages
import java.sql.*;

public class practice {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/ShawarmaPOS";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "admin";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Please wait while we connect to the database.");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("The connection has been successfully established.");
      
      //STEP 4: Execute a query
      System.out.println("Creating table in given database...");
      stmt = conn.createStatement();
      
      String sql = "CREATE TABLE MENU " +
                   "(itemnumber INTEGER not NULL, " +
                   " name VARCHAR(255), " + 
                   " description VARCHAR(255), " + 
                   " price DOUBLE, " + 
                   " PRIMARY KEY ( itemnumber ))"; 

      stmt.executeUpdate(sql);
      System.out.println("The table has been successfuly created.");
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("This program will now exit.");
}//end main
}//end JDBCExample