package server.player;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import lang.Language;

/**
 * This class take care of sending and receiving messages from the client.
 * <br>If you already saw ConnectionToServer you may be asking yourself why this class doesn't extend from Thread.
 * <br>This class doesn't need to run a thread to listen to client/player because you just care about what the player says when he HAVE to say something.
 * <p>For example,
 * <br>If is this player turn you need to know what card he want to use.
 * <br>If is not, then you shouldn't care.
 * <br>If he is being attack you need to know if he wants to block.
 * <br>If he is not being attack, you shouldn't care.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
public class ConnectionClient {
	
	private Socket client;
	private Scanner entrada;
	private PrintStream saida;
	
	private static ArrayList<String> historic;
	
	public ConnectionClient(Socket socket) {
		this.client = socket;
		
        try {
        	
        	this.entrada = new Scanner(client.getInputStream());
        	this.saida = new PrintStream(client.getOutputStream());
	        
		} catch(IOException e) {
			System.out.println("IOException - if an I/O error occurs when creating the input stream, the socket is closed, the socket is not connected, or the socket input has been shutdown using shutdownInput().");
			System.out.println("IOException - if an I/O error occurs when creating the output stream or if the socket is not connected.");
		}
        
        ConnectionClient.historic = new ArrayList<String>();
	}
	
	public ArrayList<String> getHistoric() {
		return historic;
	}
	
	public void sendMessage(String message) {
		saida.println(message);
	}
	
	public String[] receiveMessage() {
		String[] arguments = null;
		String message = null;
		
		try {
			
			message = entrada.nextLine();
			historic.add(message);
			
			arguments = message.split("[" + Language.SEPARATOR + "]");
			
		} catch(NoSuchElementException e) {
			System.out.println("NoSuchElementException - if no line was found.");
		} catch(IllegalStateException e) {
			System.out.println("IllegalStateException - if this scanner is closed.");
		}

		return arguments;
	}

}
