package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;

public class ManagerFrame extends MainFrame  {
	private JButton addItem;
	private JButton removeItem;
	
	public ManagerFrame(String title) {
		super(title);
		GridBagConstraints c = new GridBagConstraints();
		addItem = new JButton("Add item");
		addItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
		c.insets = new Insets(40, -350, 0, 0); //specifies the bottom distance
		c.gridx = 0;
		c.gridy = 4;
		Gpanel.add(addItem, c);
		
		removeItem = new JButton("Remove item");
		removeItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
		c.insets = new Insets(40, -650, 0, 0); //specifies the bottom distance
		c.gridx = 1;
		c.gridy = 4;
		Gpanel.add(removeItem, c);
		
	}
	
	
	public void valueChanged(ListSelectionEvent arg0) {
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
