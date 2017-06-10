package server.window;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel that show the board informations.
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
	
	public void updateBoardInfo(ArrayList<String> boardInfo) {
		String label = "<html>";
		
		for(String info: boardInfo) 
			label += info + " <br/> ";
		
		label += "</html>";
		
		boardLabel.setText(label);
	}

}
