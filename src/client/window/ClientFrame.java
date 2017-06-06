package client.window;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ClientFrame extends JFrame {
	
	private JPanel panel = new JPanel(new BorderLayout());

	private BoardPanel boardPanel = new BoardPanel();
	private OtherPanel otherPanel = new OtherPanel();
	private PlayerPanel playerPanel = new PlayerPanel();

	public ClientFrame() {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		otherPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		playerPanel.setBorder(BorderFactory.createLineBorder(Color.green));
		
		panel.add(boardPanel, BorderLayout.PAGE_START);
		panel.add(otherPanel, BorderLayout.CENTER);
		panel.add(playerPanel, BorderLayout.PAGE_END);
		
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
