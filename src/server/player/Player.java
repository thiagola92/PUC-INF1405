package server.player;

import java.util.ArrayList;

import server.Board;
import server.card.Card;
import server.card.Equipment;

/**
 * You probably came here from Board, this class represent the Player.
 * <br>Player will communicate with:
 * <li>Board</li>
 * <li>Player hand</li>
 * <li>Player equipments</li>
 * <li>Player connection</li>
 * <br>Every player can communicate with board, but it can communicate with others player.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
public class Player {
	
	private Board board;
	
	private String name = "NULL";
	private int resets = 4;
	private int lifes = 5;
	private Color team;
	private State state = State.WAITING_TURN;
	
	private ConnectionToClient connection;
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<Equipment> equipment = new ArrayList<Equipment>();
	
	/**
	 * Create a class Player.
	 * @param board			Class Board that will run the game.
	 * @param connection	Class ConnectionToClient that will let send/receive message to/from the player.
	 */
	public Player(Board board, ConnectionToClient connection) {
		this.board = board;
		this.connection = connection;
	}
	
	public String getName() {
		return name;
	}

	public int getResets() {
		return resets;
	}


	public int getLifes() {
		return lifes;
	}

	public Color getTeam() {
		return team;
	}

	public State getState() {
		return state;
	}

	/**
	 * Pick cards from deck and put in the player hand.
	 * @param quantity	How many cards will pick.
	 */
	public void pickFromDeck(int quantity) {
		Card[] cards = board.pickFromDeck(quantity);
		
		if(cards == null) {
			System.out.println(">>Deck is empty");
			return;
		}
		
		for(; quantity >= 0; --quantity) {
			System.out.format(">>Card %s picked from the deck\n", cards[quantity-1].getName());
			hand.add(cards[quantity-1]);
		}
	}
	
	/**
	 * The player use one card, search his hand for the card, if exist then use.
	 * <br>Notice that the card will not be removed from the hand, this will be the cards job's.
	 * <br>Why? Some cards can go to the discard and others can be moved to the equipment, so i can't know for sure where it will go, just the card knows.
	 * @param name		Name of the card to search.
	 */
	public void useCard(String name) {
		Card card;
		
		for(int i=0; i < hand.size(); ++i) {
			if(hand.get(i).getName() == name) {
				card = hand.get(i);
				System.out.format(">>Player %s tried to use card %s\n", this.name, card.getName());
				card.useCard(this);
				break;
			}
		}
	}

	/**
	 * Add one card to your hand.
	 * <br>Cards don't have access to the hand, they will only be able to change using methods.
	 * @param card		Card that is going to your hand
	 */
	public void receiveCard(Card card) {
		hand.add(card);
	}
	
	/**
	 * Search on hand and equipments the card that will be discard.
	 * <br>Good side is that work as an unequipCard one time that you have know exactly the card to be removed.
	 * @param card		Card to discard
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
	 * <br>This card must be an equipment.
	 * <br>Cards don't have access to the equipments, they will only be able to change using methods.
	 * @param card		Card to be equipped
	 */
	public void equipCard(Equipment card) {
		equipment.add(card);
	}
	
	public void attackPlayer() {
		
	}
	
	public void command() {
		String[] arguments = connection.receiveMessage();
		for(int i=0; i < arguments.length; ++i) {
			System.out.format(">>Argument[%d]: %s\n", i ,arguments[i]);
		}
		
		System.out.println(arguments[0].compareTo("NEXTTURN"));
		if(arguments[0].compareTo("NEXTTURN") == 0)
			board.nextTurn();
		else if(arguments[0].compareTo("USECARD") == 0)
			this.useCard(arguments[1]);
	}
	
}
