package client.window;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	private JButton nextButton;
	private JButton backButton;

	private JLabel otherPlayerLabel;
	private ArrayList<String> otherPlayerStatus;
	private ArrayList<Object> otherPlayerEquips;
	
	private EquipmentPanel otherPlayerEquipmentPanel;
	
	private int lookingPlayer = 0;
	
	public OtherPlayerPanel() {
		this.setLayout(new BorderLayout());

		this.boardLabel = new JLabel("---");
		this.nextButton = new JButton("Next");
		this.backButton = new JButton("Back");
		this.otherPlayerLabel = new JLabel("");
		this.otherPlayerStatus = new ArrayList<String>();
		this.otherPlayerEquips = new ArrayList<Object>();
		this.otherPlayerEquipmentPanel = new EquipmentPanel();
		
		this.nextButton.addMouseListener(new NextListener(this));
		this.backButton.addMouseListener(new BackListener(this));
		
		otherPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		otherPlayerLabel.setText("<html>-<br>-<br>-<br>-<br>-<br>-<br>-<br>-<br></html>");

		this.add(boardLabel, BorderLayout.PAGE_START);
		this.add(backButton, BorderLayout.LINE_START);
		this.add(nextButton, BorderLayout.LINE_END);
		this.add(otherPlayerLabel, BorderLayout.CENTER);
		this.add(otherPlayerEquipmentPanel, BorderLayout.PAGE_END);
	}
	
	private void createPlayersInfo(ArrayList<String> otherPlayerInfo) {
		if(otherPlayerStatus.size() > 0)
			return;
		
		for(String s: otherPlayerInfo) {
			if(s.compareTo(Language.OTHERPLAYER) == 0) {
				otherPlayerStatus.add("");
				otherPlayerEquips.add(new ArrayList<String>());
			}
		}
	}
	
	public void nextPlayer() {
		if(lookingPlayer+1 < otherPlayerStatus.size()) {
			++lookingPlayer;
			
			otherPlayerLabel.setText(otherPlayerStatus.get(lookingPlayer));
			otherPlayerEquipmentPanel.updateCardsPanel((ArrayList<String>)otherPlayerEquips.get(lookingPlayer));
		}
	}
	
	public void backPlayer() {
		if(lookingPlayer-1 > -1) {
			--lookingPlayer;
			
			otherPlayerLabel.setText(otherPlayerStatus.get(lookingPlayer));
			otherPlayerEquipmentPanel.updateCardsPanel((ArrayList<String>)otherPlayerEquips.get(lookingPlayer));
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
	
	@SuppressWarnings("unchecked")
	public void updateOtherPlayer(ArrayList<String> otherPlayerInfo) {
		ArrayList<String> equip = new ArrayList<String>();
		String info = "<html>";
		int player = 0;
		
		createPlayersInfo(otherPlayerInfo);
		
		//Starting from the second because the first is "OTHERPLAYER" and doesn't give us any information
		for(int i=1; i< otherPlayerInfo.size(); i=i+2 ) {
			
			if(otherPlayerInfo.get(i).compareTo(Language.OTHERPLAYER) == 0) {
				info += "</html>";
				
				otherPlayerStatus.set(player, info);
				otherPlayerEquips.set(player, equip);
				
				++player;
				
				info = "<html>";
				equip = new ArrayList<String>();
				
				//How this position didn't have any information to get from i+1, we will take one walk back because the next position does have some information.
				--i;
				
			} else if(otherPlayerInfo.get(i).compareTo(Language.equipment) == 0) {
				equip.add(otherPlayerInfo.get(i+1));
				
			} else {
				info += otherPlayerInfo.get(i) + ": <font color=\"red\">";
				info += otherPlayerInfo.get(i+1) + "</font><br>";
			}
			
		}

		info += "</html>";
		
		otherPlayerStatus.set(player, info);
		otherPlayerEquips.set(player, equip);
		
		otherPlayerLabel.setText(otherPlayerStatus.get(lookingPlayer));
		otherPlayerEquipmentPanel.updateCardsPanel((ArrayList<String>)otherPlayerEquips.get(lookingPlayer));
	}

}
