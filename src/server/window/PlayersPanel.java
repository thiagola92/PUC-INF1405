package server.window;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel that show the players informations.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
@SuppressWarnings("serial")
public class PlayersPanel extends JPanel {
	
	private JPanel playersPanel;
	private GridBagConstraints constraints;
	private ArrayList<JLabel> playersLabel;
	
	public PlayersPanel() {
		playersPanel = new JPanel(new GridBagLayout());
		constraints = new GridBagConstraints();
		playersLabel = new ArrayList<JLabel>();
		
		this.setLayout(new BorderLayout());
		this.add(playersPanel);
	}
	
	/**
	 * The playersPanel doesn't know how many players are and how many JLabel create.
	 * <br>Initialize this before start using.
	 * @param quantity
	 */
	public void setPlayers(int quantity) {
		
		constraints.weightx = 1;
		
		for(int i=0; i < quantity; ++i) {
			constraints.gridx = i;
			
			JLabel label = new JLabel();
			
			playersLabel.add(label);
			playersPanel.add(label, constraints);
		}
		
	}
	
	public void updatePlayerInfo(ArrayList<String> playerInfo, int index) {
		String label = "<html>";

		for(String info: playerInfo) 
			label += info + "<br/>";
		
		label += "</html>";

		playersLabel.get(index).setText(label);
	}

}
