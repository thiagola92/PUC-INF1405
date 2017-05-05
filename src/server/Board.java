package server;

public class Board {
	
	private int turnFromPlayer;			// Number going from 1 to numberOfPlayers
	private int numberOfPlayers;
	private int attacksThisTurn;
	
	public void nextTurn() {
		
		if(turnFromPlayer == numberOfPlayers)
			turnFromPlayer = 1;
		else
			turnFromPlayer += 1;
		
		attacksThisTurn = 0;
	}
	
	public void resetAttacksThisTurn() {
		attacksThisTurn = 0;
	}
	
	public void increaseAttacksThisTurn() {
		attacksThisTurn += 1;
	}

}
