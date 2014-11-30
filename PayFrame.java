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
	private JTextField total, tax, tip;
	private JLabel ttl, tx, tp; 
	private JScrollPane listScroller;
	private JPanel Mpanel, Gpanel;
	
	
	
	
	public PayFrame(String[] title, Object[][] objects) {
		
		
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
		
		ttl = new JLabel("TOTAL");
		c.insets = new Insets(30, 0, 0, 410);
		c.gridx = 0;
		c.gridy = 2;
		Gpanel.add(ttl, c);
		total = new JTextField(5);
		c.insets = new Insets(30, -78, 0, 0);
		c.gridx = 1;
		c.gridy = 2;
		Gpanel.add(total, c);
		
		tx = new JLabel("TAX");
		c.insets = new Insets(30, 0, 0, 420);
		c.gridx = 0;
		c.gridy = 3;
		Gpanel.add(tx, c);
		tax = new JTextField(5);
		c.insets = new Insets(30, -78, 0, 0);
		c.gridx = 1;
		c.gridy = 3;
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
		
		pay = new JButton("Pay");
		c.insets = new Insets(36, 0, 0, 200);
		c.gridx = 0;
		c.gridy = 5;
		Gpanel.add(pay, c);
		
		cancel = new JButton("Cancel");
		c.insets = new Insets(36, -300, 0, 0);
		c.gridx = 1;
		c.gridy = 5;
		Gpanel.add(cancel, c);
		/*cancelOrder = new JButton("Cancel");
		add(cancelOrder);*/
		total.setText(MainFrame.calc.getTotalString());
		tax.setText(Float.toString((float)MainFrame.calc.calculateTax(MainFrame.calc.getTotal(),MainFrame.calc.taxExempt) - MainFrame.calc.getTotal()));
		
	}
	
	public void taxfunc(float t){
		//total.setText(" " + t);
		//tax.setText("13%");
	}
	public void CreateView(){
		
	     	
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
