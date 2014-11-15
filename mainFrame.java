package GUI;

import java.util.*;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public abstract class mainFrame extends JFrame implements ActionListener,ListSelectionListener{
	private JButton confirmOrder;
	private JButton clearSelection;
	private JButton logout;
	private JButton discount;
	private JList<String> theMenu;
	private JScrollPane listScroller;
	private DefaultListModel<String> menuItems; 
	private ListSelectionModel selectionMode;
	private String[] discountOptions = { " ", "employee", "manager" };
	private JComboBox discountList = new JComboBox();
	private JTextField subTotal, total;
	private JLabel label, label2;
	
	public mainFrame(String title) {
		super(title);
		
		setSize(500,250);
		setResizable(false);
		setLayout(new FlowLayout());
		
		logout = new JButton("Logout");
		add(logout);
		String[] columnNames = {"First Name", "Price"};
		
		Object[][] data = {
			    {"Kathy", new Double(5.00)},
			    {"John", new Double(3.00)},
			    {"Sue", new Double(2.00)},
			    {"Jane", new Double(20.00)},
			    {"Joe", new Double(10.00)}
			};
		final JTable table = new JTable(data, columnNames);
		add(table);
		listScroller = new JScrollPane(table);
		listScroller.setPreferredSize(new Dimension(450, 80));
		add(listScroller);
		label = new JLabel("Subtotal");
		add(label);
		subTotal = new JTextField(30);
		add(subTotal);
//		theMenu.addListSelectionListener(new ListSelectionListener(){
//
//			public void valueChanged(ListSelectionEvent e) {
//				
//				
//			}
//		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	float total1 = 0;//bug here
	        	total1 += (double) table.getValueAt(table.getSelectedRow(), 1);
	        	subTotal.setText(String.format("%.2f", total));
	        }
	    });
		confirmOrder = new JButton("Confirm Order");
		confirmOrder.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());
				
			}
		});
		add(confirmOrder);
		clearSelection = new JButton("Clear Selection");
		
		
		add(clearSelection);
		discount = new JButton("Discount");
		add(discount);
		
		for (int i = 0; i < 3; i++)
			discountList.addItem(discountOptions[i]);
		add(discountList);
		
	}	
	
}
