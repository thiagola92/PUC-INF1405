package server.card;

import server.board.Board;
import server.player.Player;

/**
 * You came here from Board or Player.
 * <br>This interface was created so you could hold equipments/weapons/events cards in one ArrayList.
 * <br>All you have to do for a type of card be hold by deck/hand is to implement Card.
 * <br>You could even create a new type and put on the game (not that easy).
 * <p>Notice that when you doesn't know what is (equipment/weapon/event) you can only call this methods.
 * <p>Notice that useCard is the method default called when the card is used.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
public interface Card {
	
	public String getName();
	
	public String getDescription();
	
	public void useCard(Player player, Board board);

}
