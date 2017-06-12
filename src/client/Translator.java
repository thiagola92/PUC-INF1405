package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import client.window.ClientFrame;
import lang.Language;

/**
 * This class receive translate everything that the server send to the window/visual things.
 * <br>Also translate everything that the client do for the server.
 * <br>This class was created so visual things were not interacting with the server.
 * <p>This class will find the words in CAPS LOCK and translate what they mean.
 * <br>ASKTEXT
 * <br>OPTIONS
 * <br>UPDATE
 * <br>BOARD
 * <br>OTHERPLAYER
 * <br>Unfortunately it will not pass brute values to the other method, for example, others methods can still receive "|Damage|3|".
 * <br>Good side is that you will only remove this type of label when needed so you can still <code>println</code> the code to see what is receiving.
 * <p>
 * <b>IMPORTANT</b>: Most documentation i will say "text|text|text|text".
 * <br>This means one ArrayList where each position have one text.
 * <br>The server send you a message like "text|text|text|text" but is already translated to arguments so there is no necessity to remove the Language.<code>SEPARATOR</code>
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
public class Translator implements Runnable{

	private ConnectionToServer connection;
	private ClientFrame clientFrame;
	
	private String cardSelected;
	
	public Translator(int port, String ip) {
		
		this.clientFrame = new ClientFrame(this);
		
		try {
			
			this.connection = new ConnectionToServer(new Socket(ip, port));
			
		} catch(UnknownHostException e) {
			System.out.println("UnknownHostException - if the IP address of the host could not be determined.");
		} catch(IOException e) {
			System.out.println("IOException - if an I/O error occurs when creating the socket.");
		} catch(SecurityException e) {
			System.out.println("SecurityException - if a security manager exists and its checkConnect method doesn't allow the operation.");
		} catch(IllegalArgumentException e) {
			System.out.print("IllegalArgumentException - if the port parameter is outside the specified range of valid port values, which is between 0 and 65535, inclusive.");
		}
		
		new Thread(this).start();
	}

	@Override
	public void run() {
		
		while(true)
			this.command();
		
	}
	
	/**
	 * Wait a message from the server. Depending with what the message start it will do something.
	 * <br>Starting with ASKTEXT: Pop-up that will ask the user to insert some text
	 * <br>Starting with OPTIONS: Pop-up that will ask the user to chose one of the options
	 * <br>Starting with UPDATE: It will update all the information that the user see
	 * <p> It will send all the arguments received to the chose made.
	 */
	public void command() {
		String[] arguments = connection.receiveMessage();

		for(int i=0; i < arguments.length; ++i)
			System.out.format(">>Argument[%d]: %s\n", i ,arguments[i]);
		
		if(arguments[0].compareTo(Language.ASKTEXT) == 0) {

			String answer = clientFrame.askText(arguments);
			connection.sendMessage(answer);
			
		} else if(arguments[0].compareTo(Language.OPTIONS) == 0) {
			
			String answer = clientFrame.options(arguments);
			connection.sendMessage(answer);
			
		} else if(arguments[0].compareTo(Language.UPDATE) == 0) {
			
			update(arguments);
			
		}
	}

	/**
	 * Update the client window.
	 * <br>Here we will receive a string like "UPDATE|....|BOARD|....|OTHERPLAYER|.....|OTHERPLAYER|....|OTHERPLAYER|...."
	 * <br>And separate in 3 parts.
	 * <li>The information about the player, that is between UPDATE and BOARD</li>
	 * <li>The information about the board, that is between BOARD and OTHERPLAYER</li>
	 * <li>The information about the others player, that is everything after the first OTHERPLAYER</li>
	 * @param arguments			Every information about the game
	 */
	public void update(String[] arguments) {
		ArrayList<String> playerInfo = new ArrayList<String>();
		ArrayList<String> boardInfo = new ArrayList<String>();
		ArrayList<String> otherPlayerInfo = new ArrayList<String>();

		//Position in the array arguments, starting from the second because the first is "UPDATE"
		int pos = 1;
		
		for(; pos < arguments.length; ++pos) {
			if(arguments[pos].compareTo(Language.BOARD) == 0)
				break;
			
			playerInfo.add(arguments[pos]);
		}

		for(; pos < arguments.length; ++pos) {
			if(arguments[pos].compareTo(Language.OTHERPLAYER) == 0)
				break;
			
			boardInfo.add(arguments[pos]);
		}

		for(; pos < arguments.length; ++pos) {
			otherPlayerInfo.add(arguments[pos]);
		}
		
		clientFrame.getPlayerPanel().updateStatus(playerInfo);
	}
	
	/**
	 * When you select one card you need to save the name of the card clicked in someplace.
	 * <br>You save so when you click 'use card' you send the server a message telling that you want to use the card selected (card saved).
	 * @param name			Name of the card selected by the player
	 */
	public void cardSelected(String name) {
		this.cardSelected = name;
	}
	
	/**
	 * Tell server that you want to use the card selected.
	 * <br>So send a message like "USECARD|card name".
	 * <br>If you never selected a card, it will send "USECARD|".
	 * <br>But if already selected one card(sometime in the game) it will send the last selected.
	 */
	public void useCard() {
		connection.sendMessage(Language.USECARD + Language.SEPARATOR + cardSelected);
	}
	
	/**
	 * Send to the server the message to pass the turn.
	 * <br>So send the message "NEXTTURN".
	 * <br>This message doesn't have extra parameters and doesn't need separator.
	 */
	public void nextTurn() {
		connection.sendMessage(Language.NEXTTURN);
	}
	
	//DELETE AFTER THE GAME IS COMPLETED
	public void answer(String message) {
		connection.sendMessage(message);
	}
}

/*

*/