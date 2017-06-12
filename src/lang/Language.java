package lang;

/**
 * Every text that can be translated or shouldn't be translated should be here.
 * <br>The idea is to put all this text together for easy management.
 * @author Thiago Lages de Alencar
 * @version		%I%, %G%
 */
public class Language {
	////////////////////////////////////////////////////////////////////CLIENT OR SERVER
	//ServerOrClient
	public static String player = "Player";
	public static String server = "Server";
	
	////////////////////////////////////////////////////////////////////CLIENT
	//Asking nickname
	public static String submit_your_nickname = "Submit your nickname";
	
	//Use card
	public static String use_card = "Use card";
	
	//Cards image
	public static String TEMPLATE = "TEMPLATE";
	public static String TRANSPARENT = "TRANSPARENT";
	
	////////////////////////////////////////////////////////////////////SERVER
	//Board
	public static String game_ended = "Game ended";
	public static String number_of_players = "Number of players";
	public static String cards_on_deck = "Cards on deck";
	public static String cards_on_discard = "Cards on discard";
	public static String attacks_this_turn = "Attacks this turn";
	public static String turn_from_player = "Turn from player";

	//Player
	public static String player_name = "Player name";
	public static String resets = "Resets";
	public static String health = "Health";
	public static String team = "Team";
	public static String state = "State";
	public static String damage = "Damage";
	public static String attacks = "Attacks";
	public static String distance = "Distance";
	public static String range = "Range";
	public static String number_of_cards_holding = "Number of cards holding";
	public static String cards = "Cards";
	public static String equipment = "Equipment";
	
	//Hand size
	public static String chose_one_card_to_discard = "Chose one card to discard";
	
	//Attacking
	public static String chose_one_player_to_attack = "Chose one player to attack";
	
	//Blocking
	public static String chose_a_block_card = "Chose a block card (or don't)";
	
	////////////////////////////////////////////////////////////////////FORBIDDEN WORDS
	// From client and server
	public static final String SEPARATOR = "|";
	
	// From server to client
	public static final String ASKTEXT = "ASKTEXT";
	public static final String OPTIONS = "OPTIONS";
	public static final String UPDATE = "UPDATE";
	public static final String BOARD = "BOARD";
	public static final String OTHERPLAYER = "OTHERPLAYER";
	
	// From the client to server
	public static final String NEXTTURN = "NEXTTURN";
	public static final String USECARD = "USECARD";
}
