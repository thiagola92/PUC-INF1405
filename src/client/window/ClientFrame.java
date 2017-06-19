package client.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.Translator;
import lang.Language;

/**
 * The window that the player will see during the game.
 * <br>This window is separate in 3 parts:
 * <li>boardPanel: Where the information about the board is displayed.</li>
 * <li>otherPlayerPanel: Where the information about one of the others player is displayed.</li>
 * <li>playerPanel: Where the information about the player is displayed and where the player interact with the game.</li>
 * <br>
 * <br>Not only this but this window also send questions to the player, like:
 * <li>Ask for an answer (string).</li>
 * <li>Ask to chose one option.</li>
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
@SuppressWarnings("serial")
public class ClientFrame extends JFrame {
	
	private JPanel panel;

	private JLabel boardLabel;
	private OtherPlayerPanel otherPlayerPanel;
	private PlayerPanel playerPanel;

	public ClientFrame(Translator translator) {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.panel = new JPanel(new BorderLayout());
		
		this.boardLabel = new JLabel("test");
		this.otherPlayerPanel = new OtherPlayerPanel();
		this.playerPanel = new PlayerPanel(translator);
		
		//temporary
		boardLabel.setBorder(BorderFactory.createLineBorder(Color.red));
		otherPlayerPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		playerPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		
		panel.add(boardLabel, BorderLayout.PAGE_START);
		panel.add(otherPlayerPanel, BorderLayout.CENTER);
		panel.add(playerPanel, BorderLayout.PAGE_END);
		
		this.add(panel);
		
		this.pack();
	}
	
	public OtherPlayerPanel getOtherPlayerPanel() {
		return otherPlayerPanel;
	}
	
	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}
	
	/**
	 * Bring a pop-up asking the player to insert some text(string).
	 * <br>The pop-up will include a description so the player know what he have to answer.
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
	
	/**
	 * Update the board information.
	 * <br>It will receive an array like "BOARD|Game ended|false|Number of players|2|...."
	 * <br>And get the information that the player needs to know.
	 * @param boardInfo		The array with information about the board
	 */
	public void updateBoard(ArrayList<String> boardInfo) {
		String board = "";

		//Starting from the second because the first is "BOARD" and doesn't give us any information
		for(int i = 1; i < boardInfo.size(); i=i+2) {
			board += boardInfo.get(i) + ": ";
			board += boardInfo.get(i+1) + Language.SEPARATOR;
		}
		
		boardLabel.setText(board);
	}
	
}
