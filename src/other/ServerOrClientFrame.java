/**
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 * @since		1.8
 */

package other;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ServerOrClientFrame extends JFrame {
	
	private JButton client = new JButton("Player");
	private JButton server = new JButton("Server");
	private JPanel panel = new JPanel();		// Default is FlowLayout

	public ServerOrClientFrame() {
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		client.addActionListener(new clientActionListener());
		server.addActionListener(new serverActionListener());
		
		panel.add(client);
		panel.add(server);
		
		this.add(panel);
		
		this.pack();	// this function organize the size from objects, let it be the last function called to be precise.
	}

	private class clientActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

		}
		
	}

	private class serverActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

		}
		
	}
}
