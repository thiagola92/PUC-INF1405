package server.player;

import server.card.Card;

/**
 * If you are here you came from Player.
 * <br>This is a log. Basically "WHO used WHAT in WHOM".
 * <br>The class player have a static attribute
 * <br><code>ArrayList<Action> history</code>
 * <br>So you can register every action of the players.
 * @author Thiago Lages de Alencar
 *
 */
public class Action {
	
	private Player player = null;
	private Card card = null;
	private Player target = null;

	/**
	 * Add a new action to the history.
	 * @param player		The player responsible for the action.
	 * @param card			The card that this player used, if no card were used put null.
	 * @param target		The target of the card, if no target were selected put null.
	 */
	public Action(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Card getCard() {
		return card;
	}

	public Player getTarget() {
		return target;
	}
	
	public void setCard(Card card) {
		this.card = card;
	}
	
	public void setTarget(Player target) {
		this.target = target;
	}
	
}
