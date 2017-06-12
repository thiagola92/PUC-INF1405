package client.window.playerpanel;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lang.Language;

/**
 * One panel responsible for drawing one array of cards on the screen.
 * <br>This class doesn't 'draw' the images, this class uses JLabels to draw passing the image as icons.
 * <br>Not sure if it is the best way to draw the array of cards.
 * <p>
 * This class doesn't distinguish equipments from cards on hand, that's why the EquipmentPanel and HandPanel extends from it.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
@SuppressWarnings("serial")
public class CardsPanel extends JPanel {
	
	private String location;
	private String format;
	
	private ArrayList<String> cardsName;
	private ArrayList<JLabel> cardsImages;

	public CardsPanel() {
		this.location = "./src/cards/";
		this.format = ".png";

		this.cardsImages = new ArrayList<JLabel>();
		
		//	System.out.println(System.getProperty("user.dir"));			Get the directory of the application 
		
		// Never know how many cards the player will end in hand.
		for(int i=0; i < 256; ++i) {
			JLabel label = new JLabel("", null, JLabel.CENTER);
			label.setVisible(false);
			
			cardsImages.add(label);
			
			this.add(label);
		}
		
		// The start size of window is decided by the number of cards that will load when the game starts.
		for(int i=0; i < 7; ++i) {
			ImageIcon image = new ImageIcon(location + Language.TEMPLATE + format);
			cardsImages.get(i).setVisible(true);
			cardsImages.get(i).setIcon(image);
		}
		
	}
	
	public ArrayList<String> getCardsName() {
		return cardsName;
	}
	
	public ArrayList<JLabel> getCardsImages() {
		return cardsImages;
	}
	
	/**
	 * Receive an Array with name of cards and draw their image in order from left to right.
	 * <br>When i say draw, i say pass the image as an icon to the JLabel.
	 * <br>
	 * <br>It will pass through every JLabel putting the image as:
	 * <li>If it still less than cards size then you have a card for this JLabel.</li>
	 * <li>Else null because you don't have a card for this label.</li>
	 * <br>
	 * <br>The last if is just so the window doesn't resize the handpanel or equipmentpanel when you doesn't have cards.
	 * @param cards			Name of cards that should be draw
	 */
	public void updateCardsPanel(ArrayList<String> cards) {
		this.cardsName = cards;
		
		ImageIcon image = null;
		
		for(int i = 0; i < cardsImages.size(); ++i) {
			
			if(i < cards.size()) {
				image = new ImageIcon(location + cards.get(i) + format);
				cardsImages.get(i).setVisible(true);
				cardsImages.get(i).setIcon(image);
			} else {
				cardsImages.get(i).setVisible(false);
				cardsImages.get(i).setIcon(null);
			}
			
		}
		
		if(cards.size() < 1) {
			image = new ImageIcon(location + Language.TRANSPARENT + format);
			cardsImages.get(0).setVisible(true);
			cardsImages.get(0).setIcon(image);
		}
		
	}
}
