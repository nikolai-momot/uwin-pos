package Project.java;



public class ReciptCalculator {

	public boolean taxExempt;
	private float GrandTotal = 0;
	
	public static void main(String[] args) {
		   
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
	public double CalculateTip(int percentage){
		double total=GrandTotal*((double)percentage/100);
		return total;
	}
	//Adds amount to tip
		public double CalculateTip(double tip){
			double total = tip;
			return total;
		}
	
	//Apply discount percentage
		public double CalculateDiscount(int percentage){
			double discount = (100-((double)percentage))/100;
			double total=GrandTotal*discount;
			return total;
		}
	//Apply discount Amount
		public double CalculateDiscount(double ammountOff){
			double total=GrandTotal-ammountOff;
			return total;
		}
		public void resetPrice(float total){
			GrandTotal = total;
		}
		
		
		public void addtoTotal (float total){
			GrandTotal += total;
			//System.out.println("RECIPT CALCULATOR. GrandTotal: " + GrandTotal);	
		}
		public float getTotal(){ //Give the current total
			return GrandTotal;
		}
		public String getTotalString(){ //Give the total as a string
			return Float.toString(GrandTotal);
		}
	
}
