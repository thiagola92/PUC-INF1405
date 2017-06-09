package client.window;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import client.Translator;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
	
	private Translator translator;
	
	private JPanel panel = new JPanel(new BorderLayout());
	
	private CardsPanel hand = new CardsPanel();
	private CardsPanel equipments = new CardsPanel();

	public PlayerPanel() {

		panel.add(equipments, BorderLayout.PAGE_START);
		panel.add(hand, BorderLayout.PAGE_END);
		
		ArrayList<String> cards = new ArrayList<String>();
		cards.add("Weapon2");
		cards.add("Weapon3");
		cards.add("Block");
		hand.updateCardsPanel(cards);
		
		this.add(panel);
	}
	
	public void setTranslator(Translator translator) {
		this.translator = translator;
	}
}
