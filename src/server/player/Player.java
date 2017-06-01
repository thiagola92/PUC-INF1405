package server.player;

import java.util.ArrayList;

import server.Board;
import server.card.Card;
import server.card.Equipment;
import server.card.Weapon;

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
	
	private String name = "NOME";
	private int resets = 4;
	private int health = 5;
	private Color team;
	private State state = State.WAITING_TURN;
	
	private ConnectionToClient connection;
	
	private ArrayList<Card> hand = new ArrayList<Card>();
	private ArrayList<Equipment> equipments = new ArrayList<Equipment>();
	
	/**
	 * Create a class Player.
	 * @param board			Class Board that will run the game.
	 * @param connection	Class ConnectionToClient that will let send/receive message to/from the player.
	 */
	public Player(Board board, ConnectionToClient connection) {
		this.board = board;
		this.connection = connection;

		connection.sendMessage(">>Choose your nickname:");
		name = connection.receiveMessage()[0];
	}
	
	public String getName() {
		return name;
	}

	public int getResets() {
		return resets;
	}


	public int getHealth() {
		return health;
	}

	public Color getTeam() {
		return team;
	}

	public State getState() {
		return state;
	}
	
	public int getDamage() {
		int damage = 0;
		
		for(Equipment equip: equipments)
			damage += equip.getDamage();
		
		return damage;
	}
	
	public int getAttacks() {
		int attacks = 0;
		
		for(Equipment equip: equipments)
			attacks += equip.getAttacks();
		
		return attacks;
	}

	public int getDistance() {
		int distance = 0;
		
		for(Equipment equip: equipments)
			distance += equip.getDistance();
		
		return distance;
	}
	
	public int getRange() {
		int range = 0;

		for(Equipment equip: equipments)
			range += equip.getRange();
		
		return range;
	}
	
	public String getPlayerInfo() {
		return null;
	}
	
	/**
	 * Sets the player health to this amount(if is less than 0, it will set to 0).
	 * <br>If his health is 0:
	 * <li>State goes to DEAD</li>
	 * <li>Lose one honor</li>
	 * <p>Observation:
	 * <br>Some people may think that this class is to subtract or increment health, but is not. You will set the health to the amount you want.
	 * <br>If you want to increment you will have to do:
	 * <p><code>setHealth(getHealth() + 1)</code>
	 * @param health	The player health quantity
	 */
	public void setHealth(int health) {
		int pre_health = this.health;
		this.health = health;
		
		if(this.health <= 0) {
			this.health = 0;
			this.state = State.DEAD;

			System.out.format(">>Player %s health: %d -> %d\n", this.getName(), pre_health, this.getHealth());
			
			setResets(getResets() - 1);
		} else
			System.out.format(">>Player %s health: %d -> %d\n", this.getName(), pre_health, this.getHealth());
	}
	
	/**
	 * Sets the player resets to this amount(if is less than 0, it will set to 0).
	 * <br>If his resets is 0:
	 * <li>It will call endGame() and end the game.</li>
	 * <p>Observation:
	 * <br>Some people may think that this class is to subtract or increment resets, but is not. You will set the resets to the amount you want.
	 * <br>If you want to increment you will have to do:
	 * <p><code>setResets(getResets() + 1)</code>
	 * @param resets	The player resets quantity
	 */
	public void setResets(int resets) {
		int pre_resets = this.resets;
		this.resets = resets;
		
		if(this.resets <= 0) {
			this.resets = 0;

			System.out.format(">>Player %s resets: %d -> %d\n", this.getName(), pre_resets, this.getResets());
			
			board.endGame();
		} else
			System.out.format(">>Player %s resets: %d -> %d\n", this.getName(), pre_resets, this.getResets());
	}

	public void setTeam(Color team) {
		this.team = team;
		System.out.format(">>Player %s team is %s\n", name, this.team);
	}
	
	public void setState(State state) {
		this.state = state;
		System.out.format(">>Player %s state changed to %s\n", name, this.state);
	}

	/**
	 * Add ONE cards to equipments.
	 * <br>This cards must be an equipment.
	 * <br>Cards don't have access to the equipments, they will only be able to change using methods.
	 * @param card		Card to be equipped
	 */
	public void equipCard(Equipment card) {
		equipments.add(card);
		System.out.format(">>Player %s equiped card %s\n", name, card.getName());
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
	 * <br>This happens because you are giving the exactly card.
	 * @param card		Card to discard
	 */
	public void discardCard(Card card) {
		
		for(int i=0; i < hand.size(); ++i) {
			if(hand.get(i) == card) {
				card = hand.remove(i);
				break;
			}
		}
		
		for(int i=0; i < equipments.size(); ++i) {
			if(equipments.get(i) == card) {
				card = equipments.remove(i);
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
	 * @param cardName		Name of the card to search.
	 */
	public void useCard(String cardName) {
		Card card;

		System.out.format(">>Player %s tried to use card %s\n", this.getName(), cardName);
		
		for(int i=0; i < hand.size(); ++i) {
			if(hand.get(i).getName().compareTo(cardName) == 0) {
				card = hand.get(i);
				card.useCard(this, board);
				break;
			}
		}
	}
	
	/**
	 * The player attacks another using one Weapon.
	 * <li>Check if the player can still attack.</li>
	 * <li>Search all players alive (is not State.DEAD).</li>
	 * <li>Get all players in range.</li>
	 * <li>Ask the client the target.</li>
	 * <li>Call blockPlayer() to know if the target is going to block.</li>
	 * <li>If he die after the attack (doesn't matter if blocked or not), you gain you reset.</li>
	 * @param weapon	Weapon used in the attack.
	 */
	public void attackPlayer(Weapon weapon) {
		
		if(board.getAttacksThisTurn() > getAttacks()) {
			System.out.format(">>You can not attack more than %s time(s) this turn\n", board.getAttacksThisTurn());
			return;
		}
		
		ArrayList<Player> playersThatCanBeAttacked = board.getPlayersWithState(State.WAITING_TURN);
		String message = "OPTIONS";
		
		for(Player player: playersThatCanBeAttacked) {
			if(board.distanceFromPlayer1ToPlayer2(this, player) <= weapon.getRange() + this.getRange())
				message += ("," + player.getName());
		}
		
		System.out.format(">>Options that this player can attack \n%s\n", message);
		
		connection.sendMessage(message);
		String answer = connection.receiveMessage()[0];
		System.out.format(">>Chosen target: %s\n", answer);
		
		for(Player player: playersThatCanBeAttacked) {
			if(player.getName().compareTo(answer) == 0) {
				
				System.out.format(">>Target %s attacked\n", player.getName());
				
				board.history.append(this, weapon, player);
				board.setAttacksThisTurn(board.getAttacksThisTurn() + 1);
				player.blockPlayer(this, weapon);
				discardCard(weapon);
				
				if(player.state == State.DEAD) {
					System.out.format(">>Player %s gain one reset\n", this.getName());
					setResets(getResets() + 1);
				}
				
				return;
			}
		}
		
		System.out.println(">>Target not found.");
	}
	
	/**
	 * Check if the player will block the other player attack.
	 * <li>Get all cards that can block</li>
	 * <li>Ask player what he wants to do</li>
	 * <li>Check if his answer is one card that can block</li>
	 * @param player
	 * @param weapon
	 */
	public void blockPlayer(Player player, Weapon weapon) {
		ArrayList<Card> cardsThatCanBlock = new ArrayList<Card>();
		String message = "OPTIONS,RECEIVE";
		
		for(Card card: hand) {
			if(card.getName().compareTo("BLOCK") == 0) {
				message += ("," + card.getName());
				cardsThatCanBlock.add(card);
			}
		}
		
		System.out.format(">>Options that the other player can do \n%s\n", message);
		
		connection.sendMessage(message);
		String answer = connection.receiveMessage()[0];
		System.out.format(">>Target chose: %s\n", answer);
		
		for(Card card: cardsThatCanBlock) {
			if(card.getName().compareTo(answer) == 0) {
				System.out.format(">>Player %s blocked with %s\n", this.getName(), card.getName());
				
				board.history.append(this, card, null);
				discardCard(card);
				
				return;
			}
		}

		System.out.format(">>Player %s didn't block\n", this.getName());
		setHealth(getHealth() - weapon.getDamage() - player.getDamage());
	}
	
	/**
	 * Get the player message and translate to one action/command.
	 * <br>Right now the options are:
	 * <li>NEXTTURN</li>
	 * <li>USECARD</li>
	 */
	public void command() {
		String[] arguments = connection.receiveMessage();
		
		if(arguments == null) {
			System.out.format(">>#ERROR - null arguments\n");
			return;
		}
		
		for(int i=0; i < arguments.length; ++i) {
			System.out.format(">>Argument[%d]: %s\n", i ,arguments[i]);
		}
		
		if(arguments[0].compareTo("NEXTTURN") == 0) {
			board.history.append(this, null, null);
			board.nextTurn();
		} else if(arguments[0].compareTo("USECARD") == 0 && arguments.length == 2) {
			this.useCard(arguments[1]);
		}
	}
	
}
