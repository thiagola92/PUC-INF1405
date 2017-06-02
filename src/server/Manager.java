package server;

import java.util.ArrayList;

import server.board.Board;
import server.window.ServerFrame;

public class Manager implements Runnable {

	private Board board;
	private ServerFrame serverFrame;
	
	public Manager(Board board, ServerFrame serverFrame) {
		this.board = board;
		this.serverFrame = serverFrame;
	}

	@Override
	public void run() {
		
		ArrayList<String> boardInfo;
		ArrayList<String> playerInfo;
		
		serverFrame.getPlayersPanel().setPlayers(board.getPlayers().size());
		
		boolean firstPack = true;
		while(true) {
			boardInfo = board.getBoardInfo();
			serverFrame.getBoardPanel().updateBoardInfo(boardInfo);
			
			for(int i=0; i < board.getPlayers().size(); ++i) {
				playerInfo = board.getPlayers().get(i).getPlayerInfo();
				serverFrame.getPlayersPanel().updatePlayerInfo(playerInfo, i);
			}
			
			if(firstPack) {
				serverFrame.pack();
				firstPack = false;
			}
			
		}
		
	}
	
}
