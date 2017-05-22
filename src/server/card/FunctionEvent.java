package server.card;

import server.Board;
import server.player.Player;

/**
 * .
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
public interface FunctionEvent {
	public void useCard(Event card, Player player, Board board);
}
