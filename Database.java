package Project.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class Database {
	
	final static String USER = "root";
	final static String PASS = "admin";
    static String myDriver = "org.gjt.mm.mysql.Driver";
    static String myUrl = "jdbc:mysql://localhost/ShawarmaPOS";
	static int Itemnumber;
	static String name;
	static String description;
	static Double price;
	
	public static void Connect(){//code that connects to the database, in this case, a local one
		
		try{
	      Class.forName(myDriver);
	}	
		catch (Exception e)
	    {
	      System.err.println("An Exception has been found: ");
	      System.err.println(e.getMessage());
	    }
	}
	
	public static void Insert(){
		try{
		  Connection connect = DriverManager.getConnection(myUrl, USER, PASS);
		     
		  	System.out.println("Please enter what you wish to insert, split by ,: (eg: 1,chicken shawarma,chicken wrap,5.99)");
		  	Scanner in = new Scanner(System.in);
		  	String enter = in.nextLine();

		  	String tokenize[] = enter.split(",");
		  	Itemnumber = Integer.parseInt(tokenize[0]);
		  	name = tokenize[1];
		  	description = tokenize[2];
		  	price = Double.parseDouble(tokenize[3]);
		      
		  
		      String query = " insert into menu (itemnumber, name, description, price)"
		        + " values (?, ?, ?, ?)";
		 
		    
		      PreparedStatement preparedStmt = connect.prepareStatement(query);
		      preparedStmt.setInt (1, Itemnumber);
		      preparedStmt.setString (2, name);
		      preparedStmt.setString   (3, description);
		      preparedStmt.setDouble(4, price);
		      preparedStmt.execute();
		       
		      connect.close();
	}
		    catch (Exception e){
		      System.err.println("An exception has been found:");
		      System.err.println(e.getMessage());
		    }
}
	
	public static void Select(){
		
		try{
		Connection connect = DriverManager.getConnection(myUrl, USER, PASS);
	       
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT * FROM menu";
	 
	      // create the java statement
	      Statement st = connect.createStatement();
	       
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	     
	      // iterate through the java resultset
	      while (rs.next())
	      {
	        int itemnumber = rs.getInt("itemnumber");
	        String name = rs.getString("name");
	        String description = rs.getString("description");
	        Double price = rs.getDouble("price");
	    
	        
	        // print the results
	        System.out.format("(%s)- %s - %s: [%s]\n", itemnumber, name, description, price);
	      }
	      st.close();
		}
		
		catch (Exception e){
	      System.err.println("An exception has been found:");
	      System.err.println(e.getMessage());
	    }	
		

	}
	
	public static void Delete(){
		int item = 0;
		
		 try
		    {
		      Connection connect = DriverManager.getConnection(myUrl, USER, PASS);
		      
			  System.out.println("Enter the Item Number of the item to delete:");
			  Scanner in = new Scanner(System.in);
			  item = in.nextInt();
		       
		      String query = "delete from menu where itemnumber = ?";
		      PreparedStatement preparedStmt = connect.prepareStatement(query);
		      preparedStmt.setInt(1, item);
		      preparedStmt.execute();
		      connect.close();
		      in.close();
		    }
		 
		    catch (Exception e){
		      System.err.println("An exception has been found:");
		      System.err.println(e.getMessage());
		    }
	}
	
	
	
	public static void main(String[] args) {
	Connect();
	//Insert();
	//Select();
	//Delete();
	Select();
	}
}
