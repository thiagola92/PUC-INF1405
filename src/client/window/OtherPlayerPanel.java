package client.window;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	
	private int lookingPlayer = 0;
	
	public OtherPlayerPanel() {
		this.setLayout(new BorderLayout());

		this.boardLabel = new JLabel("---");
		this.nextPlayerButton = new JButton("Next");
		this.backPlayerButton = new JButton("Back");
		this.otherPlayerLabel = new JLabel("");
		this.otherPlayerStatus = new ArrayList<String>();
		this.otherPlayerEquipment = new EquipmentPanel();
		
		otherPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		otherPlayerLabel.setText("<html>-<br>-<br>-<br>-<br>-<br>-<br>-<br>-<br></html>");

		this.add(boardLabel, BorderLayout.PAGE_START);
		this.add(backPlayerButton, BorderLayout.LINE_START);
		this.add(nextPlayerButton, BorderLayout.LINE_END);
		this.add(otherPlayerLabel, BorderLayout.CENTER);
		this.add(otherPlayerEquipment, BorderLayout.PAGE_END);
	}
	
	private void createPlayersInfo(ArrayList<String> otherPlayerInfo) {
		if(otherPlayerStatus.size() > 0)
			return;
		
		for(String s: otherPlayerInfo) {
			if(s.compareTo(Language.OTHERPLAYER) == 0)
				otherPlayerStatus.add("");
		}
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
		String info = "<html>";
		int player = 0;
		
		createPlayersInfo(otherPlayerInfo);
		
		//Starting from the second because the first is "OTHERPLAYER" and doesn't give us any information
		for(int i=1; i< otherPlayerInfo.size(); i=i+2 ) {
			
			if(otherPlayerInfo.get(i).compareTo(Language.OTHERPLAYER) == 0) {
				info += "</html>";
				
				otherPlayerStatus.set(player, info);
				
				++player;
				info = "<html>";
				
			} else {
				info += otherPlayerInfo.get(i) + ": <font color=\"red\">";
				info += otherPlayerInfo.get(i+1) + "</font><br>";
			}
			
		}

		info += "</html>";
		
		otherPlayerStatus.set(player, info);
		otherPlayerLabel.setText(otherPlayerStatus.get(lookingPlayer));
	}

}
