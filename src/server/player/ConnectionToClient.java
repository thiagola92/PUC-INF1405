/**
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */

package server.player;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConnectionToClient extends Thread {
	
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
	 * Pass a String and will write to the client.
	 * @param message		String to be write
	 */
	public void sendMessage(String message) {
		saida.println(message);
	}
	
	/**
	 * Receive a string from client and split it, so you can now the arguments.
	 * <br>
	 * The message arguments are divide by comma, for example:
	 * <br>
	 * USE,CARD
	 * <br>
	 * This way i know that the first argument means you are trying to use a card and the second the card you want to use.
	 * <br>
	 * Take care because you can end in a loop when waiting for message. Normally you would need to throw a exception to get out.
	 * @return		One array of arguments for the class Player interpret.
	 */
	public String[] receiveMessage() {
		String[] arguments = null;
		String message = null;
		
		try {
			
			message = entrada.nextLine();
			arguments = message.split("[,]");
			
		} catch(NoSuchElementException e) {
			System.out.println("NoSuchElementException - if no line was found.");
		} catch(IllegalStateException e) {
			System.out.println("IllegalStateException - if this scanner is closed.");
		}

		return arguments;
	}
	
	/**
	 * The action that the connection need to be doing all the time, right now the only thing that i can think is waiting for client message.
	 */
	public void run() {
	}

}
