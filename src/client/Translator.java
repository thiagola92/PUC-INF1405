package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import client.window.ClientFrame;

public class Translator implements Runnable{

	private ConnectionToServer connection;
	private ClientFrame clientFrame;
	
	public Translator(int port, String ip) {
		
		this.clientFrame = new ClientFrame();
		
		try {
			
			connection = new ConnectionToServer(new Socket(ip, port));
			
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
	
	public void answer(String message) {
		connection.sendMessage(message);
	}
	
	public void command() {
		String[] arguments = connection.receiveMessage();

		for(int i=0; i < arguments.length; ++i)
			System.out.format(">>Argument[%d]: %s\n", i ,arguments[i]);
		
		if(arguments[0].compareTo("ASKTEXT") == 0) {

			String answer = clientFrame.askText(arguments);
			connection.sendMessage(answer);
			
		} else if(arguments[0].compareTo("OPTIONS") == 0) {
			
			String answer = clientFrame.options(arguments);
			connection.sendMessage(answer);
			
		} else if(arguments[0].compareTo("UPDATE") == 0) {
			
		}
	}
}

/*

*/