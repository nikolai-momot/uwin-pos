package GUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PayFrame extends JFrame implements ActionListener{
	private JButton pay;
	private JButton cancelOrder;
	private JTable theOrder;
	private JTextField total;
	private JScrollPane listScroller;
	
	public PayFrame(String[] title, Object[][] objects) {
		super(title[0]);
		setSize(500,200);
		setResizable(false);
		setLayout(new FlowLayout());
		
		theOrder = new JTable(objects, title);
		add(theOrder);
		listScroller = new JScrollPane(theOrder);
		listScroller.setPreferredSize(new Dimension(450, 80));
		add(listScroller);
		total = new JTextField(30);
		total.setText("  ");
		add(total);
		pay = new JButton("Pay");
		add(pay);
		cancelOrder = new JButton("Cancel");
		add(cancelOrder);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}	
}
