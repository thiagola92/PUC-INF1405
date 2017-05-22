package server;

import java.util.ArrayList;
import java.util.Collections;

import server.card.AllCards;
import server.card.Card;
import server.player.Color;
import server.player.ConnectionToClient;
import server.player.Player;

/**
 * Here is where the game start.
 * <br>Board will communicate with:
 * <li>Players</li>
 * <li>Deck</li>
 * <li>Discard</li>
 * <br>And this 3 shouldn't communicate between them without Board knowing.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
public class Board {
	
	private int turnFromPlayer = 0;			// Number going from 0 to numberOfPlayers - 1
	private int numberOfPlayers = 0;
	private int attacksThisTurn = 0;
	
	private ArrayList<Card> deck = new ArrayList<Card>();
	private ArrayList<Card> discard = new ArrayList<Card>();
	
	private ArrayList<Player> player = new ArrayList<Player>();
	
	/**
	 * Receive all clients made during ConnectionReceiver.
	 * <br>This also give you the number of players because you know the size.
	 * <br>
	 * <br>It will prepare the game, so this include:
	 * <li>Decide the order of players
	 * <li>Add all cards to the deck
	 * <li>Shuffle them
	 * <li>Give everyone cards
	 * <li>Decide everyone teams
	 * @param clients		ArrayList of clients
	 */
	public Board(ArrayList<ConnectionToClient> clients) {
		numberOfPlayers = clients.size();
		
		decideShiftOrder(clients);
		new AllCards(deck);
		Collections.shuffle(deck);
		
		// Distributing cards
		int cardsStart = 4;
		for(int i=0; i < player.size(); ++i) {
			player.get(i).receiveCards(pickFromDeck(cardsStart));
			if((i+1)%2 == 1)
				++cardsStart;
		}
		
		/* 
		 * Deciding how many of each teams have.
		 * After this shuffling so can distribute the teams randomly.
		 * 
		 * Notice that starts from 1, not 0.
		 * The reason is, the players are already shuffle and the first to play HAVE TO BE yellow.
		 * So there is no reason to shuffle with others and distributing.
		 */
		ArrayList<Color> teams = new ArrayList<Color>();
		for(int i = 1; i < player.size(); ++i) {
			if(i == 1)
				teams.add(Color.BLUE);
			if(i == 2)
				teams.add(Color.BLUE);

			if(i > 2 && i%3 == 0)
				teams.add(Color.YELLOW);
			if(i > 2 && i%3 == 1)
				teams.add(Color.RED);
			else if(i > 2 && i%3 == 2)
				teams.add(Color.BLUE);
		}
		
		Collections.shuffle(teams);
		player.get(0).setTeam(Color.YELLOW);
		for(int i=1; i < player.size(); ++i)
			player.get(i).setTeam(teams.remove(0));
		
	}
	
	public int getAttacksThisTurn() {
		return attacksThisTurn;
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
	 * Pick randomly each client and fixing with your 'Player'.
	 * @param clients	ArrayList of clients/players
	 */
	public void decideShiftOrder(ArrayList<ConnectionToClient> clients) {
		Collections.shuffle(clients);
		ConnectionToClient connection;
		Player new_player;
		
		for(int i=0; i < clients.size(); ++i) {
			connection = clients.get(i);
			new_player = new Player(this, connection);
			player.add(new_player);
		}
		
		System.out.println(">>Players shifted");
	}
	
	/**
	 * Shift the turn to the next player.
	 * <br>This includes reseting the counter of attacks.
	 */
	public void nextTurn() {
		
		if(turnFromPlayer == numberOfPlayers - 1)
			turnFromPlayer = 0;
		else
			turnFromPlayer += 1;

		resetAttacksThisTurn();
		
		System.out.format(">>Turn from player %d\n", turnFromPlayer);
	}
	
	public void resetAttacksThisTurn() {
		attacksThisTurn = 0;
	}
	
	public void increaseAttacksThisTurn() {
		attacksThisTurn += 1;
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
			shuffleDiscardOnDeck();
		} else {
			for(int i=0; i < quantity; ++i) {
				card[i] = deck.remove(deck.size() - 1);
				System.out.format(">>Card %s picked from the deck\n", card[i].getName());
			}
		}

		System.out.format(">>%d cards were picked from deck\n", quantity);
		
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
			System.out.format(">>Card %s picked from the discard\n", card[i].getName());
		}
		
		System.out.format(">>%d cards were picked from discard\n", quantity);
		
		return card;
	}
	
	/**
	 * Put every card from discard on deck and shuffle.
	 * This will happen every time that the deck ends.
	 */
	public void shuffleDiscardOnDeck() {
		deck.addAll(discard);
		Collections.shuffle(deck);
		System.out.println(">>Shuffling the discard on deck");
	}
	
	/**
	 * Discard one Card to the discard pile.
	 * <br>This method exist because players shouldn't be able to access the discard or deck as they like.
	 * @param card		Card that will be discarded
	 */
	public void discardCard(Card card) {
		discard.add(card);
		
		System.out.format(">>Card %s discarded\n", card.getName());
	}
}
