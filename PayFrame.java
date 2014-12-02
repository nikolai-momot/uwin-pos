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
				String tiptype="", reciept="";
				/****Calculate Tip****/
				if(tip.getText().matches(".*%") || tip.getText().matches("%.*")){
					tipammount = (double) MainFrame.calc.CalculateTip(Integer.parseInt(tip.getText().replaceAll("[^\\d.]", "")));
					tiptype = " (" + tip.getText().replaceAll("[^\\d.]", "") + "%)";
				}else if(tip.getText() != null){
					tipammount = (float) MainFrame.calc.CalculateTip(Double.parseDouble(tip.getText()));
				}
				reciept+="==============Reciept==============\n";
				/*Use this to print to .pdf*/
				for(int i=0;i<length;i++){
					try{
						if(objects[i][0].toString() != null){
						reciept+=((i+1) + ". " + objects[i][0].toString());
						for(int j=0;j<(50-(objects[i][0].toString().length()));j++){	
						reciept+=(".");}
						reciept+=("$" + objects[i][1].toString() + "\n");
						}
					}catch (Exception e1){
					}
					
				}
				reciept+=("\n\nSubtotal.........................$" + total.getText());
				reciept+=("\nTax...................................$" + tax.getText());
				if(tip.getText()!= null){
				reciept+=("\nTip...................................$" + MainFrame.money.format(tipammount) + tiptype);
				reciept+=("\nTotal..............................$" + MainFrame.money.format(MainFrame.calc.calculateTax(MainFrame.calc.getTotal(),MainFrame.calc.taxExempt) + tipammount));
				}else{
				reciept+=("\nTip...................................$0.00");
				reciept+=("\nTotal...............................$" + MainFrame.money.format(MainFrame.calc.calculateTax(MainFrame.calc.getTotal(),MainFrame.calc.taxExempt)));}
				reciept+=("\n=====================================");
				/* Done printing to .pdf */
				String time = Database.getCurrentTime().toString();
				PdfMaker.createPDF("Your Reciept " + time.replaceAll(":",";") + ".pdf", reciept);
				Database.LogRecipt(Double.parseDouble(total.getText()),(MainFrame.calc.calculateTax(MainFrame.calc.getTotal(),MainFrame.calc.taxExempt) + tipammount), tipammount);
				Close();
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
