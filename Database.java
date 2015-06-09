package Project.java;

import java.sql.*;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;


public class Database {
	
	final static String USER = "root";
	final static String PASS = "admin";
    static String myDriver = "org.gjt.mm.mysql.Driver";
    static String myUrl = "jdbc:mysql://localhost/ShawarmaPOS";
	static int Itemnumber;
	static String name;
	static String description;
	static Double price;
	static int[] IDs = new int[50];
	static Date date;
	static Date time;
	
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
    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }
    
    static java.sql.Time getCurrentTime(){
    	java.util.Date date = new java.util.Date();
        return new java.sql.Time(date.getTime());
    }
    public static void LogRecipt(double subtotal,double total, double tip){
  	   
    	try
        {	
          // create a mysql database connection
          String myDriver = "org.gjt.mm.mysql.Driver";
          String myUrl = "jdbc:mysql://localhost/ShawarmaPOS";
          Class.forName(myDriver);
          Connection conn = DriverManager.getConnection(myUrl, USER, PASS);
          
          // the mysql insert statement
          String query = " insert into receipt (date, time, subtotal, tip, total)"
            + " values (?, ?, ?, ?, ?)";
          
          
     
          // create the mysql insert preparedstatement
          PreparedStatement preparedStmt = conn.prepareStatement(query);
          
          preparedStmt.setDate (1, getCurrentDate());
          preparedStmt.setTime(2, getCurrentTime());
          preparedStmt.setDouble(3, subtotal);
          preparedStmt.setDouble(4, tip);
          preparedStmt.setDouble(5, total);
          
          
          
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
    
    public static void PrintReceiptLog(){
	  	try
	    {
	      // create our mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver";
	      String myUrl = "jdbc:mysql://localhost/ShawarmaPOS";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, USER, PASS);
	       
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT * FROM receipt";
	 
	      // create the java statement
	      Statement st = conn.createStatement();
	       
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      String t = Database.getCurrentTime().toString();
	      String log ="===============Recieps Log " + t.replaceAll(":",";")+"===============\n";
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	int OID = rs.getInt("orderid");
	        Date date = rs.getDate("date");
	        Time time = rs.getTime("time");
	        Double subtotal = rs.getDouble("subtotal");
	        Double tip = rs.getDouble("tip");
	        Double total = rs.getDouble("total"); 
	    
	        
	        // print the results to pdf
	        log += "\nOrder ID: " + OID + " Date: " + date + " " + time + " Subtotal: $"
	        + MainFrame.money.format(subtotal) + " Tip: $" + MainFrame.money.format(tip) + " Total: $" + MainFrame.money.format(total); 
	        PdfMaker.createPDF("Reciept Log " + t.replaceAll(":",";") + ".pdf", log);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	  }
    
	
	public static void AddItem(String i, String n, String p){
		try{
		  Connection connect = DriverManager.getConnection(myUrl, USER, PASS);
		     
		  	
		  	String enter = i + "," + n + "," + "Food," + p;
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
	}catch (Exception e){
		System.err.println(e.getMessage());
		if(e.getMessage().matches("Duplicate entry.*")){
			AddItem(Integer.toString((Itemnumber+1)),n,p);
		}
	    //System.err.println("An exception has been found:");
	    //System.err.println(e.getMessage());
	}
}
	public static void RemoveItem(String i){
		int item = Integer.parseInt(i);
		
		 try
		    {
		      Connection connect = DriverManager.getConnection(myUrl, USER, PASS);
		      		       
		      String query = "delete from menu where itemnumber = ?";
		      PreparedStatement preparedStmt = connect.prepareStatement(query);
		      preparedStmt.setInt(1, item);
		      preparedStmt.execute();
		      connect.close();
		    }
		 
		    catch (Exception e){
		      System.err.println("An exception has been found:");
		      System.err.println(e.getMessage());
		    }
	}
	
	
	public static DefaultTableModel BuildTable(){
		DefaultTableModel model = new DefaultTableModel(); 
		model.addColumn("ID");
	    model.addColumn("Food Item");
	    model.addColumn("Price");
		try{
			Connection connect = DriverManager.getConnection(myUrl, USER, PASS);
		       
		      // our SQL SELECT query. 
		      // if you only need a few columns, specify them by name instead of using "*"
		      String query = "SELECT * FROM menu";
		 
		      // create the java statement
		      Statement st = connect.createStatement();
		       
		      // execute the query, and get a java resultset
		      ResultSet rs = st.executeQuery(query);
		    ArrayList<String> idlist = new ArrayList<String>();
			ArrayList<String> namelist = new ArrayList<String>();
			ArrayList<String> pricelist = new ArrayList<String>();			     

		 while ( rs.next() ) {
			 idlist.add(rs.getString("Itemnumber"));
			 namelist.add(rs.getString("name"));
			 pricelist.add(rs.getString("price"));			           
		 }    
		 String [] itemnumberArray = new String [namelist.size()];
		 idlist.toArray(itemnumberArray);
		 String [] itemnameArray = new String [namelist.size()];
		 namelist.toArray(itemnameArray);
		 String [] priceArray = new String [pricelist.size()];
		 pricelist.toArray(priceArray);
		 
		 for(int i=0;i<namelist.size();i++){
			   IDs[i] = Integer.parseInt(itemnumberArray[i]);
		       model.addRow(new Object[]{itemnumberArray[i], itemnameArray[i], priceArray[i]});
		 }
		 
		}catch (Exception e){
		      System.err.println("An exception has been found:");
		      System.err.println(e.getMessage());
		    }	
		return model;
	}
	
	public static int CheckPass(String password){
		if(password.equals("abc123"))
			return 1;
		else if(password.equals("admin123"))
			return 2;
		else
			return 0;
	}
	
	/*The Following functions are for testing and debugging*/
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
	
	
	public static void SelectMenu(){//Output the menu
		
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
	public static void SelectReceiptLog(){
	  	try
	    {
	      // create our mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver";
	      String myUrl = "jdbc:mysql://localhost/ShawarmaPOS";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, USER, PASS);
	       
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT * FROM receipt";
	 
	      // create the java statement
	      Statement st = conn.createStatement();
	       
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	       
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	int OID = rs.getInt("orderid");
	        Date date = rs.getDate("date");
	        Time time = rs.getTime("time");
	        Double subtotal = rs.getDouble("subtotal");
	        Double tip = rs.getDouble("tip");
	        Double total = rs.getDouble("total");
	        
	    
	        
	        // print the results
	        System.out.format("%s - %s at %s: Subtotal: $%.2f Tip: $%.2f Total: $%.2f\n", OID, date, time, subtotal, tip, total);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	  }
	
	
	public static void main(String[] args) {
	Connect();
	//Insert();
	//Delete();
	//SelectMenu();
	SelectReceiptLog();
	}
}
