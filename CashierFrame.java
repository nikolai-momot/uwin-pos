package Project.java;

import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionEvent;

public class CashierFrame extends MainFrame {
	private MainFrame com;
	
	public CashierFrame(String title){
		super(title,  "SUBTOTAL: ", "TOTAL: ", true);
	
	}
	
	public void actionPerformed(ActionEvent arg0) {
	}

	public void valueChanged(ListSelectionEvent e) {
		
	}
}