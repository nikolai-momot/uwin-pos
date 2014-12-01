package Project.java;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
public class Login extends JFrame implements ActionListener {

	
	private JButton login;
	private String[] description = { "Cashier", "Manager" };
	private MainFrame manager;
	private MainFrame cashier;
	private JComboBox loginOption = new JComboBox();
	private JPanel p, p2;
	private JPanel Mpanel, Gpanel;
	private JLabel Pwd, usr;
	private JTextField l;
	
	public Login(String title) {
		super(title);
		setSize(330,200);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLayout(new BorderLayout());
		setLocationRelativeTo(null);
 
 
		 Mpanel = new JPanel();
		 getContentPane().add(Mpanel);
		 Gpanel = new JPanel(new GridBagLayout());
		 
	     Mpanel.add(Gpanel);
	     GridBagConstraints c = new GridBagConstraints();
		
	     usr = new JLabel("User: ");
	     c.insets = new Insets(20,0,0,180);
	     c.gridx = 1;
	     c.gridy = 2;
	     Gpanel.add(usr, c);
		for (int i = 0; i < 2; i++)
			loginOption.addItem(description[i]);
		 c.insets = new Insets(20, 50,0,0);
		 c.gridx = 1;
	     c.gridy = 2;
	     loginOption.setPreferredSize(new Dimension(145, 25));
	     Gpanel.add(loginOption, c);
	     
	     Pwd = new JLabel("Password: ");
	     c.insets = new Insets(27,0,0,185);
	     c.gridx = 1;
	     c.gridy = 3;
	     Gpanel.add(Pwd, c);
	     /*change to Jpassword later*/
	     l = new JPasswordField(15);
	     c.insets = new Insets(27,52,0,0);
	     c.gridx = 1;
	     c.gridy = 3;
	     Gpanel.add(l, c);
	     
	     login = new JButton("Login");
	     c.insets = new Insets(30, 40,0,0);
	     c.gridx = 1;
	     c.gridy = 4;
	     login.setPreferredSize(new Dimension(100, 25));
	     Gpanel.add(login, c);
	     
		
		login.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println(l.getText());
				if( loginOption.getSelectedIndex() == 0 && Database.CheckPass(l.getText())==1){
					System.out.println(loginOption.getSelectedIndex());
					cashier = new CashierFrame("Cashier Frame" );
					cashier.setVisible(true);
					Close();
					//CashierFrame.setVisible(true);
				}
				else if(loginOption.getSelectedIndex() != 0 && Database.CheckPass(l.getText())==2){
					 manager = new ManagerFrame("Manager Frame" );
					 manager.setVisible(true);
					 Close();
				}
			}
		});
		
		login.addActionListener(new ActionListener(){
		    
			public void actionPerformed(ActionEvent e){
			}
			
		});		
	}

	public void Close(){
		 super.dispose();		
	 }
		
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}	
}
