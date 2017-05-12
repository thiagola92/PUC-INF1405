package client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Connection {
	
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
