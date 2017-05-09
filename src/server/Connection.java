package server;

import java.net.Socket;

public class Connection {
	
	private Socket client;
	
	public Connection(Socket socket) {
		client = socket;
	}

}
