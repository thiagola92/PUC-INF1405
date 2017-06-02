package server.window;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	
	private JLabel boardLabel = new JLabel("test");
	
	public BoardPanel() {
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
