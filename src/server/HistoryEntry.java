package server;

import java.util.ArrayList;

import server.card.Card;
import server.player.Player;

/**
 * <br>This class register every action of the game:
 * <li>When someone pass the turn.</li>
 * <li>When someone use a card.</li>
 * <li>When someone use a card on somebody.</li>
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
public class HistoryEntry {
	
	private ArrayList<Action> history = new ArrayList<Action>();
	
	/**
	 * Add a new action to the history.
	 * @param player		The player responsible for the action.
	 * @param card			The card that this player used, if no card were used put null.
	 * @param target		The target of the card, if no target were selected put null.
	 */
	public void append(Player player, Card card, Player target) {
		Action action = new Action(player, card, target);
		
		history.add(action);
	}
	
	public String read() {
		String unification = "";
		
		for(Action action: history)
			unification += action.read() + "\n";
		
		return unification;
	}
}
