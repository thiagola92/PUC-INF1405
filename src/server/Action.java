package server;

import server.card.Card;
import server.player.Player;

public class Action {
	
	private Player player;
	private Card card;
	private Player target;
	
	public Action(Player player, Card card, Player target) {
		this.player = player;
		this.card = card;
		this.target = target;
	}
	
	public String read() {
		String event = "";
		
		event += player.getName();
		
		if(card != null) {
			event += " USED " + card.getName();
			if(target != null)
				event += " IN " + target.getName();
		} else {
			event += " PASS THE TURN";
		}
		
		return event;
	}
}
