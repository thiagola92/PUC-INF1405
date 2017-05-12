package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Connection extends Thread {
	
	private Socket server;
	private Scanner entrada;
	private PrintStream saida;
	
	public Connection(int port, String ip) {
		
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
		
		// Temporary
		while(true) {
			Scanner scan = new Scanner(System.in);
			String msg = scan.nextLine();
			sendMessage(msg);
		}
	}

	/**
	 * Pass a String and will write to the server.
	 * @param msg		String to be writed
	 */
	public void sendMessage(String msg) {
		saida.println(msg);
	}

	/**
	 * Receive a string from server.
	 * <br>
	 * Take care because you can end in a loop when waiting for message. Normally you would need to throw a exception to get out.
	 * @return
	 */
	public String receiveMessage() {
		String msg = null;
		
		try {
			
			msg = entrada.nextLine();
			
		} catch(NoSuchElementException e) {
			System.out.println("NoSuchElementException - if no line was found.");
		} catch(IllegalStateException e) {
			System.out.println("IllegalStateException - if this scanner is closed.");
		}
		
		return msg;
	}

	/**
	 * The action that the connection need to be doing all the time, right now the only thing that i can think is waiting for server message.
	 */
	public void run() {
		while(true) {
			String msg = receiveMessage();
			System.out.println(msg);
		}
	}

}
