package client.window;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.Translator;

/**
 * The window that the player will see during the game.
 * <br>This window is separate in 2 parts:
 * <li>otherPlayerPanel: Where the information about one of the others player is displayed <b>AND</b> the information about the board.</li>
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

	private BoardPanel boardPanel;
	private EnemyPanel enemyPanel;
	private PlayerPanel playerPanel;

	public ClientFrame(Translator translator) {
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		
		this.boardPanel = new BoardPanel();
		this.enemyPanel = new EnemyPanel();
		this.playerPanel = new PlayerPanel(translator);
		
		this.add(boardPanel, BorderLayout.PAGE_START);
		this.add(enemyPanel, BorderLayout.CENTER);
		this.add(playerPanel, BorderLayout.PAGE_END);
		
		this.pack();
	}
	
	public BoardPanel getBoardPanel() {
		return boardPanel;
	}
	
	public EnemyPanel getOtherPlayerPanel() {
		return enemyPanel;
	}
	
	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}
	
	public void notification(String[] arguments) {
		JOptionPane.showMessageDialog(this, arguments[1]);
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

	public void talking(boolean b) {
		playerPanel.talking(b);
	}
	
}
