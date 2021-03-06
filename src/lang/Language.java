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
	//Board information
	public static String game_ended = "Game ended";
	public static String number_of_players = "Number of players";
	public static String cards_on_deck = "Cards on deck";
	public static String cards_on_discard = "Cards on discard";
	public static String attacks_this_turn = "Attacks this turn";
	public static String turn_from_player = "Turn from";

	//Player information
	public static String player_name = "Player name";
	public static String resets = "Resets";
	public static String health = "Health";
	public static String team = "Team";
	public static String state = "State";
	public static String damage = "Damage";
	public static String attacks = "Attacks extra";
	public static String distance = "Distance";
	public static String range = "Range";
	public static String number_of_cards_holding = "Number of cards holding";
	public static String cards = "Cards";
	public static String equipment = "Equipment";

	// Notifications
	public static String cant_attack_anymore = "Can't attack anymore";
	public static String enemy_attacking = "This player is attacking you: ";
	public static String enemy_damage = "Damage you will receive: ";
	public static String chose_one_player_to_attack = "Chose one player to attack";
	public static String chose_a_block_card = "Chose a card to block (or don't)";
	public static String chose_one_card_to_discard = "Chose one card to discard";
	public static String points_team_yellow = "Team YELLOW points: ";
	public static String points_team_blue = "Team BLUE points: ";
	public static String points_team_red = "Team RED points: ";
	
	////////////////////////////////////////////////////////////////////FORBIDDEN WORDS
	// From client and server
	public static final String SEPARATOR = "|";
	
	// From server to client
	public static final String TALK = "TALK";
	public static final String DONTTALK = "DONTTALK";
	public static final String NOTIFICATION = "NOTIFICATION";
	public static final String ASKTEXT = "ASKTEXT";
	public static final String OPTIONS = "OPTIONS";
	public static final String UPDATE = "UPDATE";
	public static final String BOARD = "BOARD";
	public static final String OTHERPLAYER = "OTHERPLAYER";
	
	// From the client to server
	public static final String NEXTTURN = "NEXTTURN";
	public static final String USECARD = "USECARD";
}
