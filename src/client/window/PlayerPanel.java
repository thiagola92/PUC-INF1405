package client.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lang.Language;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
	
	private JLabel statusLabel = new JLabel("test");
	
	private JPanel handAndEquipPanel = new JPanel(new BorderLayout());
	private CardsPanel handPanel = new CardsPanel();
	private CardsPanel equipmentsPanel = new CardsPanel();
	
	private JButton useCard = new JButton(Language.use_card);

	public PlayerPanel() {
		
		this.setLayout(new BorderLayout());
		
		//temporary
		equipmentsPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		handPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));

		handAndEquipPanel.add(equipmentsPanel, BorderLayout.PAGE_START);
		handAndEquipPanel.add(handPanel, BorderLayout.PAGE_END);
		
		ArrayList<String> cards = new ArrayList<String>();
		cards.add("Weapon2");
		cards.add("Weapon3");
		equipmentsPanel.updateCardsPanel(cards);
		cards.add("Block");
		cards.add("Buy card");
		handPanel.updateCardsPanel(cards);
		
		this.add(statusLabel, BorderLayout.PAGE_START);
		this.add(handAndEquipPanel, BorderLayout.CENTER);
		this.add(useCard, BorderLayout.PAGE_END);
	}
	
	/**
	 * Update the status from the player.
	 * <br>We will receive something like "Player name|name|Resets|3|Health|4|Team|YELLOW....|Cards|Equip3|Cards|Block|Cards|Weapon3....|Equipment|Equip3|Equipment|Equip1...."
	 * <br>If you remember in <code>Translator</code> we divided in 3 parts, here we will again separate in 3 parts.
	 * <li>General informations about the player, everything before the first "Cards"</li>
	 * <li>Cards from the player, everything after the first "Cards"</li>
	 * <li>Equipments from the player, everything after the first "Equipment"</li>
	 * @param playerStatus
	 */
	public void updateStatus(ArrayList<String> playerStatus) {
		String status = "";
		ArrayList<String> equip = new ArrayList<String>();
		ArrayList<String> hand = new ArrayList<String>();
		
		for(int i=0; i < playerStatus.size(); i=i+2) {

			if(playerStatus.get(i).compareTo(Language.cards) == 0) {
				hand.add(playerStatus.get(i+1));
				
			} else if(playerStatus.get(i).compareTo(Language.equipment) == 0) {
				equip.add(playerStatus.get(i+1));
				
			} else {
				status += playerStatus.get(i) + ": ";
				status += playerStatus.get(i+1) + Language.SEPARATOR;
			}
		}
		
		statusLabel.setText(status);
	}
}
