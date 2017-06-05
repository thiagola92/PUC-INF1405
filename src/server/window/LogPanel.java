package server.window;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import server.player.Action;

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
			synchronized(action) {
				event += action.getPlayer().getName();
	
				if(action.getCard() != null) {
					event += " used " + action.getCard().getName();
					
					if(action.getTarget() != null)
						event += " in " + action.getTarget().getName();
					
				} else {
					event += "has passed the turn";
				}
				
				event += "\n";
			}
		}
		
		logArea.setText(event);
	}
}
