package server.window;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import lang.Language;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	
	private JLabel boardLabel;
	
	public BoardPanel() {
		this.boardLabel = new JLabel("---");
		
		this.add(boardLabel);
	}
	
	public void updateBoardInfo(ArrayList<String> boardInfo) {
		String label = "<html>";
		
		for(String info: boardInfo) 
			label += info + Language.SEPARATOR;
		
		label += "</html>";
		
		boardLabel.setText(label);
	}

}
