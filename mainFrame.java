package Project.java;

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
	 	
		menuItems = new DefaultListModel<String>();
		menuItems.addElement("Item               Price");
		menuItems.addElement("Pasta     $15.99");
		menuItems.addElement("Burgers");
		menuItems.addElement("Fries");
		menuItems.addElement("Hommus");
		menuItems.addElement("Salad");
		menuItems.addElement("Pizza");
		menuItems.addElement("Poutine");
		menuItems.addElement("Cereal     $27.9");
		
		theMenu = new JList<String>(menuItems);
		add(theMenu);
		theMenu.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		theMenu.setLayoutOrientation(JList.VERTICAL);
		theMenu.setVisibleRowCount(-1);
		listScroller = new JScrollPane(theMenu);
		listScroller.setPreferredSize(new Dimension(450, 80));
		add(listScroller);
		label = new JLabel("Subtotal");
		add(label);
		subTotal = new JTextField(30);
		add(subTotal);
		label2 = new JLabel("Total");
		add(label2);
		total = new JTextField(30);
		add(total);
		
		confirmOrder = new JButton("Confirm Order");
		confirmOrder.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				PayFrame pay = new PayFrame("Pay Frame", theMenu.getSelectedValues() );
				pay.setVisible(true);
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
