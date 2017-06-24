package server.window;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Panel that show the players actions.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
@SuppressWarnings("serial")
public class LogPanel extends JPanel {
	
	private JTextArea logArea;
	private JScrollPane logScroll;
	
	public LogPanel() {
		this.logArea = new JTextArea(20, 20);
		this.logScroll = new JScrollPane(logArea);
		
		logArea.setEditable(false);
		
		this.add(logScroll);
	}

	public void updateLogInfo(ArrayList<String> logInfo) {
		String event = "";

		event = String.join("\n", logInfo);
		
		logArea.setText(event);
	}
}
