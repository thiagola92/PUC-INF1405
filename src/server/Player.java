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
	
	//delete?
	public void useCard(String name) {
		Card card;
		
		for(int i=0; i < hand.size(); ++i) {
			if(hand.get(i).getName() == name) {
				card = hand.remove(i);
				card.useCard();
			}
		}
	}

	
}
