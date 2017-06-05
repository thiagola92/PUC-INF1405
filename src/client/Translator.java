package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Translator implements Runnable{

	private ConnectionToServer connection;
	
	public Translator(int port, String ip) {
		
		try {
			
			connection = new ConnectionToServer(new Socket(ip, port));
			new Thread(connection).start();
			
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
		
		while(true) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			String message = scan.nextLine();
			connection.sendMessage(message);
		}
		
	}
}

/*

*/