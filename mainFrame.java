package Project.java;

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
	
	private JButton confirmOrder, clearSelection, logout;
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
	public static ReciptCalculator calc;
	private boolean flag; 
	private float currentItem;
	public GridBagConstraints c;
	//private Double[] prices = {5.00, 3.00, 2.00, 20.00, 5.99, 11.00, 12.99, 6.0};
	
	public MainFrame(String title) {
		super(title);
		
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
         
	   String[] columnNames = {"Food item", "Price"};
/**************This Data needs to be drawn from the database**************************/
		Object[][] data = {
			    {"Chicken Sandwich", new Double(5.00)},
			    {"French Fries", new Double(3.00)},
			    {"Soft Drinks", new Double(2.00)},
			    {"Water", new Double(20.00)},
			    {"Ice Cream", new Double(5.99)},
			    {"Pizza slice", new Double(11.00)},
			    {"Tossed Salad", new Double(12.90)},
			    {"House Salad", new Double(6.07)},
			    {"Garlic cheese bread", new Double(25.50)},
			    {"Special Sandwich", new Double(14.00)},
			    {"Cheddar Biscuit", new Double(15.00)},
			    {"Veggie Sandwich", new Double(30.00)},
			    {"Egg Omelet", new Double(21.00)},
			    {"Home Fries", new Double(24.99)},
			    {"Whole Grain toast", new Double(10.90)}
		     };
/*************************************************************************************/
		table = new JTable(data, columnNames);
		c.gridx = 0;
		c.gridy = 1;
		table.setPreferredSize(new Dimension(450, 260));
		Gpanel.add(table, c);
		//add(panel);
		listScroller = new JScrollPane(table);
		c.gridx = 0;
		c.gridy = 1;
		listScroller.setPreferredSize(new Dimension(450, 260));
		Gpanel.add(listScroller, c);
		
		label = new JLabel("SUBTOTAL: ");
		c.insets = new Insets(40, 0, 0, 0); //specifies the bottom distance
		c.gridx = 0;
		c.gridy = 2;		
		Gpanel.add(label, c);
		subTotal = new JTextField(33);
		c.insets = new Insets(40, 75, 0, 0);
		c.gridx = 0;
		c.gridy = 2;		
		Gpanel.add(subTotal, c);
		
		label2 = new JLabel("TOTAL: ");
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
				        	currentItem = Float.parseFloat(table.getValueAt(table.getSelectedRow(), 1).toString());
				        	calc.addtoTotal(currentItem);
				        	subTotal.setText(calc.getTotalString());
				        	total.setText(calc.calculateTaxString(calc.getTotal(),calc.taxExempt));
		        		}
		        		
		        	}
		        }
		    });
		confirmOrder = new JButton("Order!");
		
		c.insets = new Insets(0, 45, 130, 0); // specifies the bottom distance 
		c.gridx = 1;
		c.gridy = 1;
		
		confirmOrder.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String[] title = {"Food", "Price"};
				PayFrame pay = new PayFrame(title, theOrder);
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
				if(discountList.getSelectedItem() == "Student"){					
					total.setText(calc.CalculateDiscountString(5)); //In this context, 5 means 5% off.
				}else if(discountList.getSelectedItem() == "Worker"){
					total.setText(calc.CalculateDiscountString(15));
				}
			}
			
		});
	     Gpanel.add(discountList, c);
	}	

	public void Close(){
		 super.dispose();		
	}
	
}


