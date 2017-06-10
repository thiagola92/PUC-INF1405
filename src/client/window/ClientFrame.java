package client.window;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import client.Translator;

@SuppressWarnings("serial")
public class ClientFrame extends JFrame {
	
	private JPanel panel;

	private BoardPanel boardPanel;
	private OtherPlayerPanel otherPlayerPanel;
	private PlayerPanel playerPanel;

	public ClientFrame(Translator translator) {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.panel = new JPanel(new BorderLayout());
		
		this.boardPanel = new BoardPanel();
		this.otherPlayerPanel = new OtherPlayerPanel();
		this.playerPanel = new PlayerPanel(translator);
		
		//temporary
		boardPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		otherPlayerPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		playerPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		
		panel.add(new JScrollPane(boardPanel), BorderLayout.PAGE_START);
		panel.add(new JScrollPane(otherPlayerPanel), BorderLayout.CENTER);
		panel.add(new JScrollPane(playerPanel), BorderLayout.PAGE_END);
		
		this.add(panel);
		
		this.pack();
	}
	
	public BoardPanel getBoardPanel() {
		return boardPanel;
	}
	
	public OtherPlayerPanel getOtherPlayerPanel() {
		return otherPlayerPanel;
	}
	
	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}
	
	/**
	 * Bring a pop-up asking the player to insert some text.
	 * @param arguments			The text that will appear with the pop-up (normally a description of what the player have to do)
	 * @return					The answer of the player
	 */
	public String askText(String[] arguments) {
		String answer = (String) JOptionPane.showInputDialog(this, arguments[1], "", JOptionPane.PLAIN_MESSAGE, null, null, null);
		System.out.println(answer);
		
		return answer;
	}
	
	/**
	 * Bring a pop-up asking the player to chose one the options.
	 * <br>If the <code>arguments</code> size is more than 2, than everything after the position 1 is one option.
	 * <br>If the <code>arguments</code> size is 2, than you have 0 options and the window will pop-up with the option "" so the player can still bluff.
	 * <br>If the <code>arguments</code> size is less than 2, something is wrong and return null.
	 * @param arguments			Every option that the player can select
	 * @return					The option selected
	 */
	public String options(String[] arguments) {
		String[] options = null;
		
		if(arguments.length < 2) {
			return null;
			
		} else if(arguments.length == 2) {
			options = new String[1];
			options[0] = "";
			
		} else {
			options = new String[arguments.length - 2];
			for(int i=2; i < arguments.length; ++i)
				options[i-2] = arguments[i];
			
		}
		
		String answer = (String) JOptionPane.showInputDialog(this, arguments[1], "", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		System.out.println(answer);
		
		return answer;
	}
	
}
