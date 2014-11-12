package pos;

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

public abstract class MainFrame extends JFrame implements ActionListener,ListSelectionListener{
	private JButton confirmOrder;
	private JButton clearSelection;
	private JButton logout;
	private JButton discount;
	private JScrollPane listScroller;
	private ListSelectionModel selectionMode;
	private String[] discountOptions = { " ", "employee", "manager" };
	private JComboBox discountList = new JComboBox();
	private JTextField subTotal, total;
	private JLabel label, label2;
	private JTable table;
	private Object[][] theOrder;
	
	private int j, i;
	public MainFrame(String title) {
		super(title);
		setSize(500,250);
		setResizable(false);
		setLayout(new FlowLayout());
		theOrder = new Object[20][20];
		j = 0;
		i = 0;
		logout = new JButton("Logout");
		add(logout);
		String[] columnNames = {"Food item", "Price"};
		
		Object[][] data = {
			    {"food", new Double(5.00)},
			    {"more food", new Double(3.00)},
			    {"lots of food", new Double(2.00)},
			    {"not so much food", new Double(20.00)},
			    {"kind of food", new Double(10.00)}
			};
		table = new JTable(data, columnNames);
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
	        	String total1;//bug here
	        	int count = 0;
	        	total1 = table.getValueAt(table.getSelectedRow(), 1).toString();
	        	System.out.println("i: " + i + " | j: " + j);
	        	while(count < 2 ){
	        		theOrder[i][j] = table.getValueAt(table.getSelectedRow(), 0);
	        		j++;
	        		theOrder[i][j] = table.getValueAt(table.getSelectedRow(), 1);
	        	}
	        	i++;
	        	subTotal.setText(total1);
	        }
	    });
		confirmOrder = new JButton("Confirm Order");
		confirmOrder.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				PayFrame pay = new PayFrame("Pay Frame", theOrder);
				pay.setVisible(true);
			}
		});
		add(confirmOrder);
		clearSelection = new JButton("Clear Selection");
		clearSelection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				subTotal.setText("");
				table.removeRowSelectionInterval(0, table.getRowCount() - 1);
				i = 0;
				j = 0;
			}
		});
		
		add(clearSelection);
		discount = new JButton("Discount");
		discount.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		add(discount);
		
		for (int i = 0; i < 3; i++)
			discountList.addItem(discountOptions[i]);
		add(discountList);
		
	}	
	
}
