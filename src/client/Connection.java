package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
	
	private Socket server;
	
	public Connection(int port, String ip) {
		
		try {
			
			server = new Socket(ip, port);
			System.out.println(">>Connected to the server");
			
		} catch(UnknownHostException e) {
			System.out.println("UnknownHostException - if the IP address of the host could not be determined.");
		} catch(IOException e) {
			System.out.println("IOException - if an I/O error occurs when creating the socket.");
		} catch(SecurityException E) {
			System.out.println("SecurityException - if a security manager exists and its checkConnect method doesn't allow the operation.");
		} catch(IllegalArgumentException e) {
			System.out.println("IllegalArgumentException - if the port parameter is outside the specified range of valid port values, which is between 0 and 65535, inclusive.");
		}
		
	}

}
