package server.window;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ServerFrame extends JFrame {
	
	private BoardPanel boardPanel;
	private PlayersPanel playersPanel;
	private LogPanel logPanel;
	
	public ServerFrame() {
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		boardPanel = new BoardPanel();
		playersPanel = new PlayersPanel();
		logPanel = new LogPanel();
		
		this.add(boardPanel, BorderLayout.PAGE_START);
		this.add(playersPanel, BorderLayout.CENTER);
		this.add(logPanel, BorderLayout.PAGE_END);
		
		this.pack();
	}

	public void setPlayers(int size) {
		playersPanel.setPlayers(size);
	}
	
	public void updateBoardInfo(ArrayList<String> boardInfo) {
		boardPanel.updateBoardInfo(boardInfo);
	}

	public void updatePlayersInfo(ArrayList<String> playerInfo, int i) {
		playersPanel.updatePlayersInfo(playerInfo, i);
	}

	public void updateLogInfo(ArrayList<String> logInfo) {
		logPanel.updateLogInfo(logInfo);
	}
	
}
