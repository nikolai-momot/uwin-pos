package Project.java;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;

public class ManagerFrame extends mainFrame  {
	private JButton addItem;
	private JButton removeItem;
	public ManagerFrame(String title) {
		super(title);
		addItem = new JButton("Add an item");
		add(addItem);
		removeItem = new JButton("Remove an item");
		add(removeItem);
	}
	
	public void actionPerformed(ActionEvent arg0) {
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
	}
}
