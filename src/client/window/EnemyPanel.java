package client.window;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.window.cards.CardsPanel;
import client.window.listeners.BackListener;
import client.window.listeners.NextListener;
import lang.Language;

/**
 * SOON
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
@SuppressWarnings("serial")
public class EnemyPanel extends JPanel {
	
	private JButton nextButton;
	private JButton backButton;

	private JLabel enemyLabel;
	private ArrayList<String> enemyStatus;
	private ArrayList<Object> enemyEquips;
	
	private CardsPanel enemyEquipmentPanel;
	
	private int lookingPlayer = 0;
	
	public EnemyPanel() {
		this.setLayout(new BorderLayout());

		this.nextButton = new JButton("Next");
		this.backButton = new JButton("Back");
		this.enemyLabel = new JLabel("");
		this.enemyStatus = new ArrayList<String>();
		this.enemyEquips = new ArrayList<Object>();
		this.enemyEquipmentPanel = new CardsPanel();
		
		this.nextButton.addMouseListener(new NextListener(this));
		this.backButton.addMouseListener(new BackListener(this));
		
		enemyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		enemyLabel.setText("<html>-<br>-<br>-<br>-<br>-<br>-<br>-<br>-<br></html>");

		this.add(backButton, BorderLayout.LINE_START);
		this.add(nextButton, BorderLayout.LINE_END);
		this.add(enemyLabel, BorderLayout.CENTER);
		this.add(enemyEquipmentPanel, BorderLayout.PAGE_END);
	}
	
	private void createPlayersInfo(ArrayList<String> otherPlayerInfo) {
		if(enemyStatus.size() > 0)
			return;
		
		for(String s: otherPlayerInfo) {
			if(s.compareTo(Language.OTHERPLAYER) == 0) {
				enemyStatus.add("");
				enemyEquips.add(new ArrayList<String>());
			}
		}
	}
	
	public void nextPlayer() {
		if(lookingPlayer+1 < enemyStatus.size()) {
			++lookingPlayer;
			
			enemyLabel.setText(enemyStatus.get(lookingPlayer));
			enemyEquipmentPanel.updateCardsPanel((ArrayList<String>)enemyEquips.get(lookingPlayer));
		}
	}
	
	public void backPlayer() {
		if(lookingPlayer-1 > -1) {
			--lookingPlayer;
			
			enemyLabel.setText(enemyStatus.get(lookingPlayer));
			enemyEquipmentPanel.updateCardsPanel((ArrayList<String>)enemyEquips.get(lookingPlayer));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void updateEnemy(ArrayList<String> enemyInfo) {
		ArrayList<String> equip = new ArrayList<String>();
		String info = "<html>";
		int player = 0;
		
		createPlayersInfo(enemyInfo);
		
		//Starting from the second because the first is "OTHERPLAYER" and doesn't give us any information
		for(int i=1; i< enemyInfo.size(); i=i+2 ) {
			
			if(enemyInfo.get(i).compareTo(Language.OTHERPLAYER) == 0) {
				info += "</html>";
				
				enemyStatus.set(player, info);
				enemyEquips.set(player, equip);
				
				++player;
				
				info = "<html>";
				equip = new ArrayList<String>();
				
				//How this position didn't have any information to get from i+1, we will take one walk back because the next position does have some information.
				--i;
				
			} else if(enemyInfo.get(i).compareTo(Language.equipment) == 0) {
				equip.add(enemyInfo.get(i+1));
				
			} else {
				info += enemyInfo.get(i) + ": <font color=\"red\">";
				info += enemyInfo.get(i+1) + "</font><br>";
			}
			
		}

		info += "</html>";
		
		enemyStatus.set(player, info);
		enemyEquips.set(player, equip);
		
		enemyLabel.setText(enemyStatus.get(lookingPlayer));
		enemyEquipmentPanel.updateCardsPanel((ArrayList<String>)enemyEquips.get(lookingPlayer));
	}

}
