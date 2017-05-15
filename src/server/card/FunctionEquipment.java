/**
 * Interface so you can create anonymous class that will represent the cards actions.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */

package server.card;

import server.player.Player;

public interface FunctionEquipment {
	public void useCard(Equipment card, Player player);
}
