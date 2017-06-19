package client.window;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import client.Translator;
import client.window.cards.EquipmentPanel;
import client.window.cards.HandPanel;
import client.window.cards.UseCardListener;
import lang.Language;

/**
 * When you divide the ClientFrame in 3 parts, this is the bottom part where all the information about the player is AND where the player can interact with the server.
 * <br>The information about the player is also divided in 3 parts.
 * <li>General informations</li>
 * <li>Cards on hand</li>
 * <li>Cards equipped</li>
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
	
	private JPanel statusPanel;
	private JLabel statusLabel;
	private JButton nextTurn;
	
	private JPanel handAndEquipPanel;
	private HandPanel handPanel;
	private EquipmentPanel equipmentsPanel;
	
	private JButton useCard;

	public PlayerPanel(Translator translator) {
		this.setLayout(new BorderLayout());
		
		this.statusPanel = new JPanel(new BorderLayout());
		
		this.statusLabel = new JLabel("");
		this.statusLabel.setHorizontalTextPosition(JLabel.LEFT);
		
		this.nextTurn = new JButton("Next turn");
		this.nextTurn.addMouseListener(new NextTurnListener(translator));
		
		this.handAndEquipPanel = new JPanel(new BorderLayout());
		this.handPanel = new HandPanel(translator);
		this.equipmentsPanel = new EquipmentPanel();
		
		this.useCard = new JButton(Language.use_card);
		this.useCard.addMouseListener(new UseCardListener(translator));

		handAndEquipPanel.add(new JScrollPane(equipmentsPanel), BorderLayout.PAGE_START);
		handAndEquipPanel.add(new JScrollPane(handPanel), BorderLayout.PAGE_END);
		
		this.statusPanel.add(statusLabel, BorderLayout.LINE_START);
		this.statusPanel.add(nextTurn, BorderLayout.LINE_END);
		
		this.add(new JScrollPane(statusPanel), BorderLayout.PAGE_START);
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
	 * <br>Notice that the hand and equipment cards doesn't have the text "Cards" and "Equipments" before the name of the cards anymore.
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
		handPanel.updateCardsPanel(hand);
		equipmentsPanel.updateCardsPanel(equip);
	}
	
}
