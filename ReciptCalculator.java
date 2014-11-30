package Project.java;

import java.text.DecimalFormat;

public class ReciptCalculator {

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
		double total=GrandTotal*(1+(percentage/100));
		return total;
	}
	//Adds amount to tip
		public double CalculateTip(double tip){
			double total=GrandTotal+tip;
			return total;
		}
	
	//Apply discount percentage
		public double CalculateDiscount(int percentage){
			double total=GrandTotal-(GrandTotal*(percentage/100));
			return total;
		}
	//Apply discount Amount
		public double CalculateDiscount(double ammountOff){
			double total=GrandTotal-ammountOff;
			return total;
		}
/*****************Same Functions but Returning Strings********************/	
			public String calculateTaxString(double subtotal, boolean taxExempt){
				return Float.toString((float) calculateTax(subtotal,taxExempt));
			}
			public String CalculateTipString(int percentage){
				return Float.toString((float)(CalculateTip(percentage)));
			}
			public String CalculateTipString(double tip){				
				return Float.toString((float)(CalculateTip(tip)));
			}
			public String CalculateDiscountString(int percentage){
				return Float.toString((float)(CalculateDiscount(percentage)));
			}
			public String CalculateDiscountString(double ammountOff){
				return Float.toString((float)(CalculateDiscount(ammountOff)));
			}
		
/**************************************************************************/
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
