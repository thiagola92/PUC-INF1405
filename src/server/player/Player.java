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
 * <br>Every player can communicate with Board and using it can communicate with others player.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
public class Player {
	
	private Board board;
	
	private String name;
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

	public void setTeam(Color team) {
		this.team = team;
		System.out.format(">>Player %s team is %s\n", name, team);
	}

	/**
	 * Add ONE cards to equipments.
	 * <br>This cards must be an equipment.
	 * <br>Cards don't have access to the equipments, they will only be able to change using methods.
	 * @param card		Card to be equipped
	 */
	public void equipCard(Equipment card) {
		equipment.add(card);
		System.out.format(">>Player %s received card %s\n", name, card.getName());
	}
	
	/**
	 * Add X cards to your hand.
	 * <br>Cards don't have access to the hand, they will only be able to change using methods.
	 * @param card		Cards that is going to your hand
	 */
	public void receiveCards(Card[] card) {
		for(int i=0; i < card.length; ++i) {
			hand.add(card[i]);
			System.out.format(">>Player %s received card %s\n", name, card[i].getName());
		}
	}
	
	public void receiveCards(Card card) {
		Card[] x = new Card[1];
		x[0] = card;
		receiveCards(x);
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
		
		System.out.format(">>Player %s discarded %s\n", name, card.getName());
		board.discardCard(card);
	}
	
	/**
	 * The player use one card, search his hand for the card, if exist then use.
	 * <br>Notice that the card will not be removed from the hand, this will be the cards job's.
	 * <br>Why? Some cards can go to the discard and others can be moved to the equipment, so i can't know for sure where it will go, just the card knows.
	 * @param name		Name of the card to search.
	 */
	public void useCard(String name) {
		Card card;

		System.out.format(">>Player %s tried to use card %s\n", this.name, name);
		
		for(int i=0; i < hand.size(); ++i) {
			if(hand.get(i).getName() == name) {
				card = hand.get(i);
				card.useCard(this, board);
				break;
			}
		}
	}
	
	public void attackPlayer() {
	}
	
	public void command() {
		String[] arguments = connection.receiveMessage();
		
		if(arguments == null) {
			System.out.format(">>#ERROR - null arguments\n");
			return;
		}
		
		for(int i=0; i < arguments.length; ++i) {
			System.out.format(">>Argument[%d]: %s\n", i ,arguments[i]);
		}
		
		if(arguments[0].compareTo("NEXTTURN") == 0)
			board.nextTurn();
		else if(arguments[0].compareTo("USECARD") == 0 && arguments.length == 2) {
			this.useCard(arguments[1]);
		}
	}
	
}
