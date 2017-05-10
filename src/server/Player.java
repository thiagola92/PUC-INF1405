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
				card.useCard(this);
				break;
			}
		}
	}

	
}
