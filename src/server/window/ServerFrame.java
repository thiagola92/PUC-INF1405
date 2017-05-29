package server.window;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import server.Board;

@SuppressWarnings("serial")
public class ServerFrame extends JFrame {
	
	private Board board;

	private JButton history = new JButton("History");
	private JPanel panel;
	
	public ServerFrame(Board board) {
		this.board = board;
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		history.addActionListener((ActionEvent e) -> {
			board.history.read();
		});
		
		panel = new JPanel();
		panel.add(history);
		
		this.add(panel);
		this.pack();
	}
}
