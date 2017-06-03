package server.window;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Panel that show the players actions.
 * @author Thiago
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

}
