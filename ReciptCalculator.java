package pos;



//import java.sql.*;
public class ReciptCalculator {
	//Set up things to access the SQL database
	/*static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/STUDENTS";	
	static final String USER = "root";
	static final String PASS = "admin";
	*/
	private float price = 0;
	
	public String runningTotal (float total){
		System.out.println("price: " + price);
		price += total;
		String total1 = Float.toString(price);
		return total1;
	}
	public void resetPrice(float total){
		price = total;
	}
	
	//Adds 13% tax if not taxExempt
	public double calculateTax(double subtotal, boolean taxExempt){
		double total=0.0;
		if(!taxExempt){
			total = subtotal*1.13;			
		}else{
			total = subtotal;			
		}
		return total;
	}
	
	//Adds percentage to tip
	public double CalculateTip(double subtotal,int percentage){
		double total=subtotal*(1+percentage);
		return total;
	}
	//Adds amount to tip
		public double CalculateTip(double subtotal,double tip){
			double total=subtotal+tip;
			return total;
		}
	
	//Apply discount percentage
		public double CalculateDiscount(double subtotal, int percentage){
			double total=subtotal-(subtotal*percentage);
			return total;
		}
	//Apply discount Amount
		public double CalculateDiscount(double subtotal, double ammountOff){
			double total=subtotal-ammountOff;
			return total;
		}
	
}
