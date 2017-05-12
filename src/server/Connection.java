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

public class Connection {
	
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
	
	public void sendMessage(String msg) {
		saida.println(msg);
	}
	
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

}
