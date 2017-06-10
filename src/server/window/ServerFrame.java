package server.window;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Window to visualize what is happen in the game.
 * <br>This window is composed from the following panels:
 * <li>BoardPanel</li>
 * <li>PlayersPanel</li>
 * <li>LogPanel</li>
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
@SuppressWarnings("serial")
public class ServerFrame extends JFrame {
	
	private JPanel panel;
	
	private BoardPanel boardPanel;
	private PlayersPanel playersPanel;
	private LogPanel logPanel;
	
	public ServerFrame() {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panel = new JPanel(new BorderLayout());
		
		boardPanel = new BoardPanel();
		playersPanel = new PlayersPanel();
		logPanel = new LogPanel();
		
		panel.add(boardPanel, BorderLayout.PAGE_START);
		panel.add(playersPanel, BorderLayout.CENTER);
		panel.add(logPanel, BorderLayout.PAGE_END);
		
		this.add(panel);
		this.pack();
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}
	
	public PlayersPanel getPlayersPanel() {
		return playersPanel;
	}

	public LogPanel getLogPanel() {
		return logPanel;
	}
	
}
