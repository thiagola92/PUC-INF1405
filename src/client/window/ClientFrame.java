package client.window;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ClientFrame extends JFrame {

	public ClientFrame() {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		this.pack();
	}
	
	public String options(String[] arguments) {
		String X = (String) JOptionPane.showInputDialog(this, "Options", "", JOptionPane.PLAIN_MESSAGE, null, arguments, arguments[0]);
		System.out.println(X);
		
		return X;
	}
}
