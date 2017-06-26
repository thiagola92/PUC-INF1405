package server.card.interfaces;

import server.board.Board;
import server.card.Equipment;
import server.player.Player;

/**
 * .
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
public interface FunctionEquipment {
	public void useCard(Equipment card, Player player, Board board);
}
