package client.window.playerpanel;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import client.Translator;

/**
 * Extends everything from CardsPanel, so draw the cards on the hand.
 * <br>Plus also let you click them so you can select the card you want to use.
 * <br>The card selected is saved on the class Translator so can be send to the server in case the player click 'use card'.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
@SuppressWarnings("serial")
public class HandPanel extends CardsPanel {
	
	private Translator translator;
	
	private JLabel cardSelected;
	
	public HandPanel(Translator translator) {
		super();
		
		this.translator = translator;
		
		this.cardSelected = null;
		
		listenToClick();
	}
	
	/**
	 * Configure every JLabel to listen to mouse actions on them.
	 * <br>The mouse listener receive this class so can call method from this.
	 * <br>
	 * <br>It's private method because just need to be called once.
	 */
	private void listenToClick() {
		ArrayList<JLabel> cardsImages = super.getCardsImages();
		
		synchronized(cardsImages) {
			for(JLabel image: cardsImages) {
				image.addMouseListener(new SelectCardListener(this));
			}
		}
	}
	
	/**
	 * Clear the selection
	 * <br>Pick the previously selected and set icon to null so seems like is not selected.
	 * <br>This function is called every time you select one card and when the cards update.
	 */
	public void clearSelection() {
		if(cardSelected != null)
			cardSelected.setBorder(null);
		
		translator.cardSelected("");
	}
	
	public void selectCard(JLabel selectedImage) {
		ArrayList<JLabel> cardsImages = super.getCardsImages();
		
		clearSelection();
		
		cardSelected = selectedImage;
		cardSelected.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		synchronized(cardsImages) {
			for(int i=0; i < cardsImages.size(); ++i) {
				
				// If this is the label that you clicked AND if this label is not an invisible label
				if(cardsImages.get(i) == cardSelected && i < super.getCardsName().size()) {
					translator.cardSelected(super.getCardsName().get(i));
					
					break;
				}
				
			}
		}
	}
	
	public void updateCardsPanel(ArrayList<String> cards) {
		clearSelection();
		
		super.updateCardsPanel(cards);
	}
}
