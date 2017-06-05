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
	
	public String askText(String[] arguments) {
		String answer = (String) JOptionPane.showInputDialog(this, arguments[1], "", JOptionPane.PLAIN_MESSAGE, null, null, null);
		System.out.println(answer);
		
		return answer;
	}
	
	public String options(String[] arguments) {
		String[] options = null;
		
		if(arguments.length <= 2)
			return null;
		
		options = new String[arguments.length - 2];
		for(int i=2; i < arguments.length; ++i)
			options[i-2] = arguments[i];
		
		String answer = (String) JOptionPane.showInputDialog(this, arguments[1], "", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		System.out.println(answer);
		
		return answer;
	}
}
