package server.window;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import server.Board;

@SuppressWarnings("serial")
public class ServerFrame extends JFrame {
	
	private JTextArea log = new JTextArea(20, 20);
	private JScrollPane scroll = new JScrollPane(log);

	private JButton updateHistory = new JButton("Update");
	private JPanel panel = new JPanel();
	
	public ServerFrame(Board board) {
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		updateHistory.addActionListener((ActionEvent e) -> {
			log.setText(board.history.read());
		});
		
		panel.add(scroll);
		panel.add(updateHistory);
		
		this.add(panel);
		this.pack();
	}
}
