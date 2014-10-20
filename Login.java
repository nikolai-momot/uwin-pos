package Project.java;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Login extends JFrame implements ActionListener {

	
	private JButton login;
	private String[] description = { "employee", "manager" };
	private mainFrame manager;
	private mainFrame cashier;
	private JComboBox loginOption = new JComboBox();
	
	public Login(String title) {
		super(title);
		setSize(200,150);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		

		for (int i = 0; i < 2; i++)
			loginOption.addItem(description[i]);
		add(loginOption);
		
		login = new JButton("Login");
		login.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if( loginOption.getSelectedIndex() == 0){
					System.out.println(loginOption.getSelectedIndex());
					cashier = new CashierFrame("Cashier Frame" );
					cashier.setVisible(true);
					//CashierFrame.setVisible(true);
				}
				else{
					 manager = new ManagerFrame("Manager Frame" );
					 manager.setVisible(true);
				}
			}
		});
		add(login, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}	
}
