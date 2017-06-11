package client.window;

import javax.swing.JLabel;
import javax.swing.JPanel;

import client.Translator;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	
	private JLabel boardLabel;
	
	public BoardPanel(Translator translator) {
		this.boardLabel = new JLabel("");
		
		this.add(boardLabel);
	}
	
}
