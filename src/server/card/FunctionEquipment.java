package server.card;

import server.board.Board;
import server.player.Player;

/**
 * .
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
public interface FunctionEquipment {
	public void useCard(Equipment card, Player player, Board board);
}
