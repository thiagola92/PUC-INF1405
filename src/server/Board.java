/**
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */

package server;

import java.util.ArrayList;
import java.util.Random;

public class Board {
	
	private int turnFromPlayer = 0;			// Number going from 1 to numberOfPlayers
	private int numberOfPlayers = 0;
	private int attacksThisTurn = 0;
	
	private ArrayList<Card> deck = new ArrayList<Card>();
	private ArrayList<Card> discard = new ArrayList<Card>();
	
	private ArrayList<Player> player = new ArrayList<Player>();
	
	/**
	 * @param numberOfPlayers	How many players are playing the game,
	 * 							this will be constant during the game
	 */
	public Board(ArrayList<Connection> clients) {
		numberOfPlayers = clients.size();
		
		decideShiftOrder(clients);
	}
	
	public void nextTurn() {
		
		if(turnFromPlayer == numberOfPlayers)
			turnFromPlayer = 1;
		else
			turnFromPlayer += 1;

		System.out.println(">>Turn from player " + turnFromPlayer);
		
		resetAttacksThisTurn();
	}
	
	public void resetAttacksThisTurn() {
		attacksThisTurn = 0;
	}
	
	public void increaseAttacksThisTurn() {
		attacksThisTurn += 1;
	}
	
	/**
	 * Pick randomly each client and fixing with you 'Player'.
	 * @param clients	ArrayList with all clients that the game will talk
	 */
	public void decideShiftOrder(ArrayList<Connection> clients) {
		Random r = new Random();
		Connection connection;
		Player new_player;

		for(int playersPicked = 0; numberOfPlayers - playersPicked > 1; ++playersPicked) {
			connection = clients.remove(r.nextInt(numberOfPlayers - playersPicked));
			new_player = new Player(this, connection);
			player.add(new_player);
		}
		
		// Picking the last player
		connection = clients.remove(0);
		new_player = new Player(this, connection);
		player.add(new_player);
		
		System.out.println(">>Players shifted");
	}
	
	/**
	 * Pick cards from the top of deck.
	 * <br>
	 * If there is not enough cards, pick the discard and shuffle and now this is the new deck.
	 * <br>
	 * Just to be clear, the deck is an ArrayList and let's say that you have this Array as your deck:
	 * <br>
	 * <br>
	 * [Thiago][Leo][Matheus][Senpai][GR]
	 * <br>
	 * <br>
	 * And you are going to pick 1 card from the deck, this card will be [Gr].
	 * <br>
	 * The first card that you add to the deck is the last to be removed.
	 * <br>
	 * Try to think like a discard pile, the first card played, will be the last to be seeing.
	 * @param quantity	How many cards the player will pick from the deck
	 * @return			Array of Card
	 */
	public Card[] pickFromDeck(int quantity) {
		Card card[] = new Card[quantity];
		
		if(deck.size() < quantity) {
			//suffle discard
		} else {
			for(int i=0; i < quantity; ++i) {
				card[i] = deck.remove(deck.size() - 1);
			}
		}

		System.out.println(quantity + " were picked from deck");
		
		return card;
	}

	/**
	 * BETA
	 * Pick cards from the top of discard.
	 * <br>
	 * If there is not enough cards, pick from the top of deck.
	 * <br>
	 * Just to be clear, the discard is an ArrayList and let's say that you have this Array as your discard:
	 * <br>
	 * <br>
	 * [Thiago][Leo][Matheus][Senpai][GR]
	 * <br>
	 * <br>
	 * And you are going to pick 1 card from the discard, this card will be [Gr].
	 * <br>
	 * The first card that you add to the discard is the last to be removed.
	 * <br>
	 * Try to think like a discard pile, the first card played, will be the last to be seeing.
	 * @param quantity	How many cards the player will pick from discard
	 * @return			Array of card
	 */
	public Card[] pickFromDiscard(int quantity) {
		Card card[] = new Card[quantity];

		for(int i=0; i < quantity; ++i) {
			card[i] = (discard.size() > 0) ? discard.remove(discard.size() - 1) : pickFromDeck(1)[0];
		}
		
		System.out.println(quantity + " were picked from discard");
		
		return card;
	}
	
	/**
	 * @param card		Card that will be discarded
	 */
	public void discardCard(Card card) {
		discard.add(card);
	}
}
