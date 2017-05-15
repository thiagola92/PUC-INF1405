/**
 * Each state after the first will limit your actions.
 * <br>WAITING_TURN, doesn't let you do nothing to the game.
 * <br>ATTACKING, you can't do nothing until the player that is being attack decide if it will block or not.
 * <br>BLOCKING, you can say the card that you will use to block or that you will not block.
 * <br>DEAD, if you are dead you may be untargable to others players (for example, players can't kill the dead player).
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */

package server.player;

public enum State {
	
	PLAYING,
	WAITING_TURN,
	ATTACKING,
	BLOCKING,
	DEAD;

}
