package pos;


public class Item {
	private String name;
	private String desc;
	private double price;	
	
	public Item(String n,String d,double p){
		name = n;
		desc = d;
		price = p;	
	}
	
	//Getters
	public String getName(){
		return this.name;
	}
	public String getDesc(){
		return this.desc;
	}
	public double getPrice(){
		return this.price;
	}
	//Setters
	public void setName(String n){
		this.name = n;		
	}
	public void setDesc(String d){
		this.desc = d;		
	}
	public void setPrice(double p){
		this.price = p;		
	}
	
}
