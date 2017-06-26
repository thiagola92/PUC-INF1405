package client.window;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import lang.Language;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	private JLabel boardLabel;
	
	public BoardPanel() {
		this.boardLabel = new JLabel("---");
		
		this.add(boardLabel, BorderLayout.PAGE_START);
	}
	
	/**
	 * Update the board information.
	 * <br>It will receive an array like "BOARD|Game ended|false|Number of players|2|...."
	 * <br>And get the information that the player needs to know.
	 * @param boardInfo		The array with information about the board
	 */
	public void updateBoard(ArrayList<String> boardInfo) {
		String board = "<html>";

		//Starting from the second because the first is "BOARD" and doesn't give us any information
		for(int i = 1; i < boardInfo.size(); i=i+2) {
			board += boardInfo.get(i) + ": <font color=\"blue\">";
			board += boardInfo.get(i+1) + "</font>" + Language.SEPARATOR;
		}
		board+="</html>";
		
		boardLabel.setText(board);
	}

}
