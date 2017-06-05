package other;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import client.Translator;
import server.ConnectionReceiver;

/**
 * Create a window that give you two options run as server or run as player.
 * <br>If you want to run as player, it will try to make a connection to the server using ConnectionToServer.
 * <br>Else if you want to run as server, it will create ConnectionReceiver.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
@SuppressWarnings("serial")
public class ServerOrClientFrame extends JFrame {
	
	private JButton client = new JButton("Player");
	private JButton server = new JButton("Server");
	private JPanel panel = new JPanel();		// Default is FlowLayout

	public ServerOrClientFrame() {
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		client.addActionListener((ActionEvent e) -> {
			new Translator(4, "127.0.0.1");
			this.setVisible(false);
		});
		
		server.addActionListener((ActionEvent e) -> {
			new ConnectionReceiver(4, 2);
			this.setVisible(false);
		});
		
		panel.add(client);
		panel.add(server);
		
		this.add(panel);
		this.pack();	// this function organize the size from objects, let it be the last function called to be precise.
	}

}
