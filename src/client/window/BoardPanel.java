package client.window;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The panel that show every information from the board.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	
	private JLabel boardLabel;
	
	public BoardPanel() {
		this.boardLabel = new JLabel("");
		
		this.add(boardLabel);
	}
	
}
