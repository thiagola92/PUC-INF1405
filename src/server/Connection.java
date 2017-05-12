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

public class Connection extends Thread {
	
	private Socket client;
	private Scanner entrada;
	private PrintStream saida;
	
	public Connection(Socket socket) {
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
	 * @param msg		String to be writed
	 */
	public void sendMessage(String msg) {
		saida.println(msg);
	}
	
	/**
	 * Receive a string from client.
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
	 * The action that the connection need to be doing all the time, right now the only thing that i can think is waiting for client message.
	 */
	public void run() {
		while(true) {
			String msg = receiveMessage();
			System.out.println(msg);
		}
	}

}
