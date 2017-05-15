/**
 * The Board have direct or indirect information about everything in the game.
 * <br>Hold the deck and discard, controlling what you can do with it.
 * <br>Hold the players, but what can do with it is limited.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */

package server;

import java.util.ArrayList;
import java.util.Random;

import server.card.AllCards;
import server.card.Card;
import server.player.ConnectionToClient;
import server.player.Player;

public class Board {
	
	private int turnFromPlayer = 0;			// Number going from 0 to numberOfPlayers - 1
	private int numberOfPlayers = 0;
	private int attacksThisTurn = 0;
	
	private ArrayList<Card> deck = new ArrayList<Card>();
	private ArrayList<Card> discard = new ArrayList<Card>();
	
	private ArrayList<Player> player = new ArrayList<Player>();
	
	/**
	 * @param numberOfPlayers	How many players are playing the game,
	 * 							this will be constant during the game
	 */
	public Board(ArrayList<ConnectionToClient> clients) {
		numberOfPlayers = clients.size();
		
		decideShiftOrder(clients);
		new AllCards(deck);
	}
	
	/**
	 * Start the game.
	 * <br>In others words, stay on the loop until the game end.
	 */
	public void startGame() {
		while(true) {
			System.out.format(">>Waiting command from player %d\n", turnFromPlayer);
			player.get(turnFromPlayer).command();
		}
	}
	
	/**
	 * Shift the turn to the next player.
	 * <br>This includes reset the counter of attacks.
	 */
	public void nextTurn() {
		
		if(turnFromPlayer == numberOfPlayers - 1)
			turnFromPlayer = 0;
		else
			turnFromPlayer += 1;

		System.out.format(">>Turn from player %d\n", turnFromPlayer);
		
		resetAttacksThisTurn();
	}
	
	public void resetAttacksThisTurn() {
		attacksThisTurn = 0;
	}
	
	public void increaseAttacksThisTurn() {
		attacksThisTurn += 1;
	}
	
	/**
	 * Pick randomly each client and fixing with your 'Player'.
	 * @param clients	ArrayList of ConnectionToClient
	 */
	public void decideShiftOrder(ArrayList<ConnectionToClient> clients) {
		Random r = new Random();
		ConnectionToClient connection;
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
	 * <br>If there is not enough cards, pick the discard and shuffle and now this is the new deck.
	 * <br>Just to be clear, the deck is an ArrayList and let's say that you have this Array as your deck:
	 * <p>[Thiago][Leo][Matheus][Senpai][GR]
	 * <p>And you are going to pick 1 card from the deck, this card will be [Gr].
	 * <br>The first card that you add to the deck is the last to be removed.
	 * <br>Try to think like a discard pile, the first card played, will be the last to be seeing.
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

		System.out.format("%d were picked from deck", quantity);
		
		return card;
	}

	/**
	 * BETA
	 * Pick cards from the top of discard.
	 * <br>If there is not enough cards, pick from the top of deck.
	 * <br>Just to be clear, the discard is an ArrayList and let's say that you have this Array as your discard:
	 * <p>[Thiago][Leo][Matheus][Senpai][GR]
	 * <p>And you are going to pick 1 card from the discard, this card will be [Gr].
	 * <br>The first card that you add to the discard is the last to be removed.
	 * <br>Try to think like a discard pile, the first card played, will be the last to be seeing.
	 * @param quantity	How many cards the player will pick from discard
	 * @return			Array of Card
	 */
	public Card[] pickFromDiscard(int quantity) {
		Card card[] = new Card[quantity];

		for(int i=0; i < quantity; ++i) {
			card[i] = (discard.size() > 0) ? discard.remove(discard.size() - 1) : pickFromDeck(1)[0];
		}
		
		System.out.format(" were picked from discard", quantity);
		
		return card;
	}
	
	/**
	 * Discard one Card to the discard pile.
	 * <br>This method exist because players shouldn't be able to access the discard or deck as they like.
	 * @param card		Card that will be discarded
	 */
	public void discardCard(Card card) {
		discard.add(card);
	}
}
