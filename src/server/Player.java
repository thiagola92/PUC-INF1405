/**
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */

package server;

import java.util.ArrayList;

public class Player {
	
	private Board board;
	
	private String name = "NULL";
	private int resets = 4;
	private int lifes = 5;
	private Color team;
	private State state = State.WAITING_TURN;
	
	private Connection connection;
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<Card> equipment = new ArrayList<Card>();
	
	public Player(Board board, Connection connection) {
		this.board = board;
		this.connection = connection;
	}
	
	/**
	 * Pick cards from deck and put in the player hand.
	 * @param quantity	How many cards pick.
	 */
	public void pickFromDeck(int quantity) {
		Card[] cards = board.pickFromDeck(quantity);
		
		for(; quantity >= 0; --quantity) {
			System.out.println("Card " + cards[quantity-1] + " picked from the deck");
			hand.add(cards[quantity-1]);
		}
	}
	
	/**
	 * The player use one card, search his hand for the card, if exist then use.
	 * <br>
	 * Notice that the card will not be removed from the hand, this will be the cards job's.
	 * <br>
	 * Why? Some cards can go to the discard and others can be moved to the equipment, so i can't know for sure where it will go, just the card knows.
	 * @param name		Name of the card to search.
	 */
	public void useCard(String name) {
		Card card;
		
		for(int i=0; i < hand.size(); ++i) {
			if(hand.get(i).getName() == name) {
				card = hand.get(i);
				System.out.println("Player " + this.name + " tried to use card " + card.getName());
				card.useCard(this);
				break;
			}
		}
	}

	/**
	 * Add one card to your hand.
	 * @param card		Card that is going to your hand
	 */
	public void receiveCard(Card card) {
		hand.add(card);
	}
	
	/**
	 * Search on hand and equipments the card that will be discard.
	 * <br>
	 * Good side is that work as an unequipCard one time that you have know exactly the card to be removed.
	 * @param card		The location of the card
	 */
	public void discardCard(Card card) {
		
		for(int i=0; i < hand.size(); ++i) {
			if(hand.get(i) == card) {
				card = hand.remove(i);
				break;
			}
		}
		
		for(int i=0; i < equipment.size(); ++i) {
			if(equipment.get(i) == card) {
				card = equipment.remove(i);
				break;
			}
		}
		
		board.discardCard(card);
	}
	
	/**
	 * Add one card to equipments.
	 * <br>
	 * This card must be an equipment.
	 * @param card		Card to be equipped
	 */
	public void equipCard(Equipment card) {
		equipment.add(card);
	}
	
	public void attackPlayer() {
		
	}
	
}
