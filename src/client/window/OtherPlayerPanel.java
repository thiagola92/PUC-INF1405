package client.window;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.window.cards.EquipmentPanel;
import lang.Language;

/**
 * SOON
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
@SuppressWarnings("serial")
public class OtherPlayerPanel extends JPanel {

	private JLabel boardLabel;
	
	private JButton nextPlayerButton;
	private JButton backPlayerButton;

	private JLabel otherPlayerLabel;
	private ArrayList<String> otherPlayerStatus;
	
	private EquipmentPanel otherPlayerEquipment;
	
	public OtherPlayerPanel() {
		this.setLayout(new BorderLayout());

		this.boardLabel = new JLabel("---");
		this.nextPlayerButton = new JButton("Next");
		this.backPlayerButton = new JButton("Back");
		this.otherPlayerLabel = new JLabel("");
		this.otherPlayerStatus = new ArrayList<String>();
		this.otherPlayerEquipment = new EquipmentPanel();

		this.add(boardLabel, BorderLayout.PAGE_START);
		this.add(backPlayerButton, BorderLayout.LINE_START);
		this.add(nextPlayerButton, BorderLayout.LINE_END);
		this.add(otherPlayerLabel, BorderLayout.CENTER);
		this.add(otherPlayerEquipment, BorderLayout.PAGE_END);
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
	
	public void updateOtherPlayer(ArrayList<String> otherPlayerInfo) {
		
	}

}
