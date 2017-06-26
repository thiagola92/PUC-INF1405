package server;

import java.util.ArrayList;

import server.board.Board;
import server.player.Player;
import server.window.ServerFrame;

public class Manager implements Runnable {

	private ConnectionReceiver connections;
	private Board board;
	private ServerFrame serverFrame;
	
	public Manager(int port, int howManyWait) {
		this.connections = new ConnectionReceiver(port, howManyWait);
		this.board = new Board(connections.getConnections());
		this.serverFrame = new ServerFrame();
		
		new Thread(board).start();
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		
		ArrayList<String> boardInfo;
		ArrayList<String> playerInfo;
		ArrayList<String> logInfo;
		
		serverFrame.setPlayers(board.getPlayers().size());
		
		boolean firstPack = true;
		while(true) {
			boardInfo = board.getBoardInfo();
			serverFrame.updateBoardInfo(boardInfo);
			
			for(int i=0; i < board.getPlayers().size(); ++i) {
				Player player = board.getPlayers().get(i);
				playerInfo = player.getPlayerInfo(player, false);
				serverFrame.updatePlayersInfo(playerInfo, i);
			}
			
			logInfo = board.getPlayers().get(0).getLogInfo();
			serverFrame.updateLogInfo(logInfo);
			
			if(firstPack) {
				serverFrame.pack();
				firstPack = false;
			}
			
		}
		
	}
	
}
