/**
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */

package server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConnectionToClient extends Thread {
	
	private Player player;
	
	private Socket client;
	private Scanner entrada;
	private PrintStream saida;
	
	public ConnectionToClient(Socket socket) {
		client = socket;
		
        try {
        	
			entrada = new Scanner(client.getInputStream());
	        saida = new PrintStream(client.getOutputStream());
	        
		} catch(IOException e) {
			System.out.println("IOException - if an I/O error occurs when creating the input stream, the socket is closed, the socket is not connected, or the socket input has been shutdown using shutdownInput().");
			System.out.println("IOException - if an I/O error occurs when creating the output stream or if the socket is not connected.");
		}
	}

	/**
	 * Hold on one variable the player that this connection represents.
	 * @param player	Player that this connection represents
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * Pass a String and will write to the client.
	 * @param message		String to be writed
	 */
	public void sendMessage(String message) {
		saida.println(message);
	}
	
	/**
	 * Receive a string from client.
	 * <br>
	 * Take care because you can end in a loop when waiting for message. Normally you would need to throw a exception to get out.
	 * @return
	 */
	public String receiveMessage() {
		String message = null;
		
		try {
			
			message = entrada.nextLine();
			
		} catch(NoSuchElementException e) {
			System.out.println("NoSuchElementException - if no line was found.");
		} catch(IllegalStateException e) {
			System.out.println("IllegalStateException - if this scanner is closed.");
		}
		
		return message;
	}
	
	/**
	 * Right now i don't know how much use this method have.
	 * <br>
	 * Receive a message and translate to actions in the game.
	 * <br>
	 * The message arguments are divide by comma, for example:
	 * <br>
	 * USE,CARD
	 * <br>
	 * This way i know that the first argument means you are trying to use a card and the second the card you want to use.
	 * @param message		Message to be translate to action.
	 */
	public void translate(String message) {
		String[] arguments;
		arguments = message.split("[,]");
		
		for(int i=0; i < arguments.length; ++i) {
			System.out.format(">>Argument[%d]: %s\n", i ,arguments[i]);
		}
		
		if(arguments[0] == "USECARD") {
			sendMessage(player.useCard(arguments[1]) + "");
		}
		
	}
	
	/**
	 * The action that the connection need to be doing all the time, right now the only thing that i can think is waiting for client message.
	 */
	public void run() {
		//Temporary
		while(true) {
			String message = receiveMessage();
			System.out.format(">>Message received: %s\n", message);
			translate(message);
		}
	}

}
