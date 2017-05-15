/**
 * Interface for all cards in the game.
 * <br>Every card needs name, description and a function(Player player).
 * <br>Notice that cards receive the player so their action become limited and can't change the game.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */

package server.card;

import server.player.Player;

public interface Card {
	
	public String getName();
	
	public String getDescription();
	
	public void useCard(Player player);

}
