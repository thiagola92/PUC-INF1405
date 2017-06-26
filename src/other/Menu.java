package other;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import client.Translator;
import lang.Language;
import server.Manager;

@SuppressWarnings("serial")
public class Menu extends JFrame {
	
	private JButton client;
	private JButton server;	

	public Menu() {
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.client = new JButton(Language.player);
		this.server = new JButton(Language.server);
		
		client.addActionListener((ActionEvent e) -> {
			new Translator(4, "127.0.0.1");
			this.setVisible(false);
		});
		
		server.addActionListener((ActionEvent e) -> {
			new Manager(4, 2);
			this.setVisible(false);
		});
		
		this.add(client);
		this.add(server);
		this.pack();
	}

}
