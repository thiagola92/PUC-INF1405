package server;

import java.util.ArrayList;

import server.board.Board;
import server.player.Action;
import server.player.ConnectionToClient;
import server.window.ServerFrame;

/**
 * This class was created so visual things didn't interact with the game.
 * <br>If you want to edit windows/frames/panels go the <b>serverFrame</b>.
 * <br>If you want to edit the game go to <b>board</b>.
 * @author Thiago
 * @version		%I%, %G%
 */
public class Manager implements Runnable {

	private Board board;
	private ServerFrame serverFrame;
	
	/**
	 * Create the board and window.
	 * @param clients	To create the board you need clients, connections.
	 */
	public Manager(ArrayList<ConnectionToClient> clients) {
		this.board = new Board(clients);
		this.serverFrame = new ServerFrame();
		
		new Thread(board).start();
	}

	/**
	 * It will get information about player and board and insert to the right panel.
	 * <br>This will be repeat until closed.
	 */
	@Override
	public void run() {
		
		ArrayList<String> boardInfo;
		ArrayList<String> playerInfo;
		ArrayList<Action> logInfo;
		
		serverFrame.getPlayersPanel().setPlayers(board.getPlayers().size());
		
		boolean firstPack = true;
		while(true) {
			boardInfo = board.getBoardInfo();
			serverFrame.getBoardPanel().updateBoardInfo(boardInfo);
			
			for(int i=0; i < board.getPlayers().size(); ++i) {
				playerInfo = board.getPlayers().get(i).getPlayerInfo();
				serverFrame.getPlayersPanel().updatePlayerInfo(playerInfo, i);
			}
			
			logInfo = board.getPlayers().get(0).getLogInfo();
			serverFrame.getLogPanel().updateLogInfo(logInfo);
			
			if(firstPack) {
				serverFrame.pack();
				firstPack = false;
			}
			
		}
		
	}
	
}
