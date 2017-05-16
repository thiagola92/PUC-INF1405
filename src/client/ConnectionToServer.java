package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class take care of sending and receiving messages from the server.
 * <br>Extends from Thread so can always be waiting from the server update.
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */
public class ConnectionToServer extends Thread {
	
	private Socket server;
	private Scanner entrada;
	private PrintStream saida;
	
	/**
	 * Create the socket that will communicate with the server.
	 * @param port		Port to connect
	 * @param ip		IP to connect
	 */
	public ConnectionToServer(int port, String ip) {
		
		try {
			
			server = new Socket(ip, port);
			System.out.println(">>Connected to the server");

			entrada = new Scanner(server.getInputStream());
	        saida = new PrintStream(server.getOutputStream());
			
		} catch(UnknownHostException e) {
			System.out.println("UnknownHostException - if the IP address of the host could not be determined.");
		} catch(IOException e) {
			System.out.println("IOException - if an I/O error occurs when creating the socket.");
			System.out.println("IOException - if an I/O error occurs when creating the input stream, the socket is closed, the socket is not connected, or the socket input has been shutdown using shutdownInput().");
			System.out.println("IOException - if an I/O error occurs when creating the output stream or if the socket is not connected.");
		} catch(SecurityException E) {
			System.out.println("SecurityException - if a security manager exists and its checkConnect method doesn't allow the operation.");
		} catch(IllegalArgumentException e) {
			System.out.println("IllegalArgumentException - if the port parameter is outside the specified range of valid port values, which is between 0 and 65535, inclusive.");
		}
		
	}

	/**
	 * Pass a String and will write to the server.
	 * @param message		String to be write to server
	 */
	public void sendMessage(String message) {
		saida.println(message);
	}

	/**
	 * Receive a string from server.
	 * <br>Take care because you can end in a loop when waiting for message. Normally you would need to throw a exception to get out.
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
	 * The action that the connection need to be doing all the time, right now the only thing that i can think is waiting for server message.
	 */
	public void run() {
		// Temporary
		while(true) {
			String message = receiveMessage();
			System.out.println(message);
		}
	}

}
