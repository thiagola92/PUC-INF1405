package server.card.interfaces;

import server.board.Board;
import server.card.Special;
import server.player.Player;

/**
 * .
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
public interface FunctionSpecial {
	public void useCard(Special card, Player player, Board board);
}
