package client.window;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.Translator;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	
	private JLabel boardLabel;
	
	private JButton nextTurn;
	
	public BoardPanel(Translator translator) {
		this.setLayout(new FlowLayout());
		
		this.boardLabel = new JLabel("");
		this.nextTurn = new JButton("Next turn");
		
		this.nextTurn.addMouseListener(new NextTurnListener(translator));
		
		this.add(boardLabel, nextTurn);
		this.add(nextTurn);
	}
	
}
