package Project.java;
import java.util.*;
import java.text.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.text.DecimalFormat;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public abstract class MainFrame extends JFrame implements ActionListener,ListSelectionListener{
	
	private JButton confirmOrder;
	private JButton clearSelection;
	private JButton logout;
	private JLabel discount;
	private JScrollPane listScroller;
	private ListSelectionModel selectionMode;
	private String[] discountOptions = { "Select", "None", "Worker", "Student" };
	private JComboBox discountList;
	private JTextField subTotal, total, tax;
	private JLabel label, label2, label3;
	private JTable table;
	private Object[][] theOrder;
	private double ttl, subtl, tx, disct;
	private int j, i;
	public JPanel Mpanel, Gpanel;
	static ReciptCalculator calc;
	private boolean flag; 
	private float currentItem, price;
	private float p;
	private String L1= " ", L2 = " "; //to save the label names
	private boolean numofcomponents; //to help distinguish no of components for each 
	private JButton addItem;
	private JButton removeItem;
	private String items;
	static DecimalFormat money = new DecimalFormat("#.##");

	
/*our Goal is to make the class as generic as possible*/	
	public MainFrame(String title, String m, String k, boolean f) {
		super(title);
		L1 += m;
		L2 += k;
		numofcomponents = f;
		CreateView();
		
		setSize(670,560);
		setResizable(false);
		//panel = new JPanel();
		setLocationRelativeTo(null);
	
        
	}	

	public Object[][] setOrder( Object[][] theOrder ){
		j = 0;
		theOrder[i][j] = table.getValueAt(table.getSelectedRow(), 0);
    	j++;
    	theOrder[i][j] = table.getValueAt(table.getSelectedRow(), 1);
    	i++;
		return theOrder;
		
	}
	
	public void clearOrder(){
		subTotal.setText("");
		for( int x = 0 ; x <= i; x ++)
			for(int y = 0 ; y <= j ; y ++)
				theOrder[x][y] = null;
		calc.resetPrice(0);
		
		flag = false;
		table.removeRowSelectionInterval(0, table.getRowCount() -1);
		
		currentItem = 0;
		flag = true;
		i = j = 0;
	}
	
	
	
	private void CreateView(){
	System.out.println(" " + L1 + " " + " "+ L2);
     Mpanel = new JPanel();
     currentItem = 0;
     flag = true;
     calc = new ReciptCalculator();
     /*this line makes the panel to be the container for each Gui component*/
     getContentPane().add(Mpanel);
     
     /*this is used to group each of Gui together into a panel*/
     Gpanel = new JPanel(new GridBagLayout());
     //adds the gpanel to the main container called mpanel
     Mpanel.add(Gpanel);
     //sets the constraint for the layout 
     GridBagConstraints c = new GridBagConstraints();
     
		theOrder = new Object[20][20];
		ttl = 0.0;
		subtl = 0.0;
		tx= 0.0;
		disct = 0.0;
		
		/*a layout that allows flexible alignment of components*/
		c.gridx = 0;
	    c.gridy = 0;
	    c.anchor = GridBagConstraints.LINE_START;
	    logout = new JButton("Logout");
	     
	    logout.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
				Login frame = new Login("Login");
				frame.setVisible(true);
				Close();	
			}
		});
	   Gpanel.add(logout, c);
         
	   String[] columnNames = {"ID", "Food item", "Price"};
		
		Object[][] data = {
			    {"1","Chicken Sandwich", new Double(5.00)},
			    {"2","French Fries", new Double(3.00)},
			    {"3","Soft Drinks", new Double(2.00)},
			    {"4","Water", new Double(20.00)},
			    {"5","Ice Cream", new Double(5.99)},
			    {"6","Pizza slice", new Double(11.00)},
			    {"7","Tossed Salad", new Double(12.90)},
			    {"8","House Salad", new Double(6.07)},
			    {"9","Garlic cheese bread", new Double(25.50)},
			    {"10","Special Sandwich", new Double(14.00)},
			    {"11","Cheddar Biscuit", new Double(15.00)},
			    {"12","Veggie Sandwich", new Double(30.00)},
			    {"13","Egg Omelet", new Double(21.00)},
			    {"14","Home Fries", new Double(24.99)},
			    {"15","Whole Grain toast", new Double(10.90)}
		     };
		table = new JTable(data, columnNames);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		c.gridx = 0;
		c.gridy = 1;
		table.setPreferredSize(new Dimension(450, 260));
		Gpanel.add(table, c);
		//add(panel);
		//System.out.println(" " + data.length);
		listScroller = new JScrollPane(table);
		c.gridx = 0;
		c.gridy = 1;
		listScroller.setPreferredSize(new Dimension(450, 260));
		Gpanel.add(listScroller, c);
		
		label = new JLabel(L1);
		c.insets = new Insets(40, 0, 0, 0); //specifies the bottom distance
		c.gridx = 0;
		c.gridy = 2;		
		Gpanel.add(label, c);
		subTotal = new JTextField(33);
		c.insets = new Insets(40, 75, 0, 0);
		c.gridx = 0;
		c.gridy = 2;		
		Gpanel.add(subTotal, c);
		
		label2 = new JLabel(L2);
		c.insets = new Insets(46, 0, 0, 0); //specifies the bottom distance
		c.gridx = 0;
		c.gridy = 3;		
		Gpanel.add(label2, c);
		total = new JTextField(33);
		c.insets = new Insets(46, 75, 0, 0);
		c.gridx = 0;
		c.gridy = 3;
		Gpanel.add(total, c);
		
		 table.setSelectionMode(selectionMode.MULTIPLE_INTERVAL_SELECTION);
		 table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {
		        	if(!(event.getValueIsAdjusting())){
		        		if( flag ){
		        			theOrder = setOrder(theOrder);
				        		if(numofcomponents == true){ /*set the fields of the cashier frame to the appropriate menu items*/
				        			currentItem = Float.parseFloat(table.getValueAt(table.getSelectedRow(), 2).toString());	
				        			calc.addtoTotal(currentItem);
						        	subTotal.setText(money.format(calc.getTotal()));
						        	total.setText(money.format(calc.calculateTax(calc.getTotal(),calc.taxExempt)));
				        		 }
				        	   else if(numofcomponents == false){ /*if it's the manager frame display the food name for the sub-total field*/
				        		   String m = (table.getValueAt(table.getSelectedRow(), 1).toString()); /* to get food item name i.e 1st row / 2nd column */
				        		   subTotal.setText(" " + m);
				        		   price = Float.parseFloat((table.getValueAt(table.getSelectedRow(), 2).toString())); /* to get food price i.e 1st row / 3rd column */
				        		   total.setText(" " + price);
				        	}
		        		}
		        		
		        	}
		        }
		    });
		 
		 /*to show the confirm order, clear list and discount components iff it's the cashier frame*/
	    if(numofcomponents == true){
	    	
				confirmOrder = new JButton("Order!");
				
				c.insets = new Insets(0, 45, 130, 0); // specifies the bottom distance 
				c.gridx = 1;
				c.gridy = 1;
				
				confirmOrder.addActionListener( new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						String[] title = {"Food", "Price"};
						PayFrame pay = new PayFrame(title, theOrder);
						pay.taxfunc(currentItem);
						pay.setVisible(true);
					}
				});
				
					Gpanel.add(confirmOrder, c);	
				
					clearSelection = new JButton("Clear List");
				c.insets = new Insets(0, 37, 20, 0); //specifies the bottom distance
				c.gridx = 1;
				c.gridy = 1;
				c.ipadx = 1/2;
				clearSelection.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						clearOrder();
					}
				});
				
				Gpanel.add(clearSelection, c);		
				
				discount = new JLabel("DISCOUNT");
					c.insets = new Insets(60, 50, 0, 0);
					c.gridx = 1;
					c.gridy = 1;
					Gpanel.add(discount, c);
				 
					
					
					discountList = new JComboBox();
					c.insets = new Insets(109, 45, 0, 0);
					c.gridx = 1;
					c.gridy = 1;
					
					/*use to add items to the combo box*/
				for (int i = 0; i <discountOptions.length; i++)
					discountList.addItem(discountOptions[i]);
				
				discountList.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						String newPrice;
						if(discountList.getSelectedItem() == "Student")
					total.setText(money.format(calc.CalculateDiscount(5)));
				else if(discountList.getSelectedItem() == "Worker")
							total.setText(money.format(calc.CalculateDiscount(15)));
					}
					
				});
				 Gpanel.add(discountList, c);
	     
	   } /*END OF IF*/
	    else if(numofcomponents == false){ /*when it's the manager the frame*/
	    	
	    	addItem = new JButton("Add item");
	    	c.insets = new Insets(0, 45, 130, 0); // specifies the bottom distance 
			c.gridx = 1;
			c.gridy = 1;
			c.ipadx = 5;
			
			addItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					
				}
				
			});
			Gpanel.add(addItem, c);
			
			removeItem = new JButton("Remove item");
			c.insets = new Insets(26, 37, 20, 0); //specifies the bottom distance
			c.gridx = 1;
			c.gridy = 1;
			c.ipadx = 2;
			removeItem.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e) {
			 /*Algorithm: we use tables 
			  * */		
			  	 
				 
				 
			 }
				
			});
			
			Gpanel.add(removeItem, c);
			
		  }
	}	

	public void Close(){
		 super.dispose();		
	}
	
}


