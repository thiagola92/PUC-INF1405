package server.window;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import server.card.Card;
import server.player.Action;
import server.player.Player;

/**
 * Panel that show the players actions.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
@SuppressWarnings("serial")
public class LogPanel extends JPanel {
	
	private JTextArea logArea = new JTextArea(20, 20);
	private JScrollPane logScroll = new JScrollPane(logArea);
	
	public LogPanel() {
		logArea.setEditable(false);
		
		this.add(logScroll);
	}

	public void updateLogInfo(ArrayList<Action> logInfo) {
		String event = "";
		
		for(Action action: logInfo) {
			synchronized(logInfo) {
				Player player = action.getPlayer();
				Card card = action.getCard();
				Player target = action.getTarget();
				
				if(player != null && card != null && target != null) {
					event += player.getName() + " used " + card.getName() + " on " + target.getName();
				} else if(player != null && card != null && target == null) {
					event += player.getName() + " used " + card.getName();
				} else if(player != null && card == null && target == null) {
					event += player.getName() + " passed the turn";
				}
				
				event += "\n";
			}
		}
		
		logArea.setText(event);
	}
}
