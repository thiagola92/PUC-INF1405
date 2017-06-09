package other;

import java.awt.event.ActionEvent;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import client.Translator;
import lang.Language;
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
	
	private JButton client = new JButton(Language.player);
	private JButton server = new JButton(Language.server);
	private JPanel panel = new JPanel();		// Default is FlowLayout

	public ServerOrClientFrame() {
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		client.addActionListener((ActionEvent e) -> {
			Translator t = new Translator(4, "127.0.0.1");
			
			//temporary
			(new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true) {
						Scanner scan = new Scanner(System.in);
						String message = scan.nextLine();
						t.answer(message);
					}
				}
			})).start();
			
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
