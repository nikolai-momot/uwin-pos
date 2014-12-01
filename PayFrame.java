package Project.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PayFrame extends JFrame implements ActionListener{
	private JButton pay;
	private JButton cancel;
	private JTable theOrder;
	private JLabel total, tax;
	private JTextField tip;
	private JLabel ttl, tx, tp; 
	private JScrollPane listScroller;
	private JPanel Mpanel, Gpanel;
	private float t;
	private double tipammount;
	
	
	
	
	public PayFrame(String[] title, final Object[][] objects, String discount, final int length) {
		
		
        super(title[0]);
        CreateView();
		
		setSize(670, 560);
		setResizable(false);
		//panel = new JPanel();
		setLocationRelativeTo(null);


		 Mpanel = new JPanel();
		 getContentPane().add(Mpanel);
		 Gpanel = new JPanel(new GridBagLayout());
		 
	     Mpanel.add(Gpanel);
	     GridBagConstraints c = new GridBagConstraints();
		
		theOrder = new JTable(objects, title);
		c.gridx = 0;
		c.gridy = 1;
		theOrder.setPreferredSize(new Dimension(450, 260));
		Gpanel.add(theOrder, c);
		//add(panel);
		listScroller = new JScrollPane(theOrder);
		c.gridx = 0;
		c.gridy = 1;
		listScroller.setPreferredSize(new Dimension(450, 260));
		Gpanel.add(listScroller, c);
		
		
		
		tx = new JLabel("TAX");
		c.insets = new Insets(30, 0, 0, 420);
		c.gridx = 0;
		c.gridy = 2;
		Gpanel.add(tx, c);
		tax = new JLabel("13%");
		c.insets = new Insets(30, -78, 0, 0);
		c.gridx = 1;
		c.gridy = 2;
		Gpanel.add(tax, c);
		
		tp = new JLabel("TIP");
		c.insets = new Insets(30, 0, 0, 420);
		c.gridx = 0;
		c.gridy = 4;
		Gpanel.add(tp, c);
	 	tip = new JTextField(7);
		c.insets = new Insets(30, -80, 0, 0);
		c.gridx = 1;
		c.gridy = 4;
		Gpanel.add(tip, c);
		
		ttl = new JLabel("TOTAL");
		c.insets = new Insets(30, 0, 0, 410);
		c.gridx = 0;
		c.gridy = 3;
		Gpanel.add(ttl, c);
		total = new JLabel(" " + t);
		c.insets = new Insets(30, -78, 0, 0);
		c.gridx = 1;
		c.gridy = 3;
		Gpanel.add(total, c);
		
		pay = new JButton("Pay");
		c.insets = new Insets(36, 0, 0, 200);
		c.gridx = 0;
		c.gridy = 5;
		Gpanel.add(pay, c);
		
		cancel = new JButton("Cancel");
		c.insets = new Insets(36, -300, 0, 0);
		c.gridx = 1;
		c.gridy = 5;
		
		if(discount.equals("Student")){
			total.setText(MainFrame.money.format(MainFrame.calc.CalculateDiscount(5)));
		}else if(discount.equals("Worker")){
					total.setText(MainFrame.money.format(MainFrame.calc.CalculateDiscount(15)));
		}else{
		total.setText(MainFrame.money.format(MainFrame.calc.getTotal()));
		}
		tax.setText(MainFrame.money.format(MainFrame.calc.calculateTax(MainFrame.calc.getTotal(),MainFrame.calc.taxExempt) - MainFrame.calc.getTotal()));
		
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Close();
			}
			
		});
		
		Gpanel.add(cancel, c);
		
		pay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String tiptype="";
				/****Calculate Tip****/
				if(tip.getText().matches(".*%") || tip.getText().matches("%.*")){
					tipammount = (double) MainFrame.calc.CalculateTip(Integer.parseInt(tip.getText().replaceAll("[^\\d.]", "")));
					tiptype = " (" + tip.getText().replaceAll("[^\\d.]", "") + "%)";
				}else if(tip.getText() != null){
					tipammount = (float) MainFrame.calc.CalculateTip(Double.parseDouble(tip.getText()));
				}
				
				/*code to trigger receipt from database here*/
				System.out.println("=========Recipt=========\n");
				for(int i=0;i<length;i++){
					if(objects[i][0].toString() != null){
					System.out.print((i+1) + ". " + objects[i][0].toString());
					for(int j=0;j<(30-(objects[i][0].toString().length()));j++){	
					System.out.print(".");}
					System.out.print("$" + objects[i][1].toString() + "\n");
					}
				}
				System.out.println("\n\nSubtotal.........................$" + total.getText());
				System.out.println("Tax..............................$" + tax.getText());
				if(tip.getText()!= null){
				System.out.println("Tip..............................$" + MainFrame.money.format(tipammount) + tiptype);
				System.out.println("\nTotal............................$" + MainFrame.money.format(MainFrame.calc.calculateTax(MainFrame.calc.getTotal(),MainFrame.calc.taxExempt) + tipammount));
				}else{
				System.out.println("Tip..............................$0.00");
				System.out.println("\nTotal............................$" + MainFrame.money.format(MainFrame.calc.calculateTax(MainFrame.calc.getTotal(),MainFrame.calc.taxExempt)));}
				System.out.println("=======================");
			}
			
		});
		
		
	}
	
	
	public void taxfunc(float t){
		//total.setText(" " + t);
		//tax.setText("13%");
	}
	public void CreateView(){
		
	     	
	}
	public void Close(){
		 super.dispose();		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*public static void main(String[] args){
	 PayFrame v = new PayFrame();
	  v.setVisible(true);
	}*/
	
}
