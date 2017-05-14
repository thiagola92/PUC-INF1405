/**
 * @author		Thiago Lages de Alencar
 * @version		%I%, %G%
 */

package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class ConnectionReceiver {
	
	private ArrayList<ConnectionToClient> clients = new ArrayList<ConnectionToClient>();
	
	/**
	 * @param port			Port that will be receving players.
	 * @param howManyWait	How many players it will wait until start the game.
	 */
	public ConnectionReceiver(int port, int howManyWait) {
		
		try {
			ServerSocket serversocket = new ServerSocket(port);

			for(; howManyWait > 0; --howManyWait) {
				System.out.format(">>Waiting %s players\n", howManyWait);
				
				ConnectionToClient connection = new ConnectionToClient(serversocket.accept());
				clients.add(connection);
			}
			
			System.out.println(">>Connected to everyone, creating game");
			serversocket.close();
			
		} catch(SocketTimeoutException e) {
			System.out.println("SocketTimeoutException - if a timeout was previously set with setSoTimeout and the timeout has been reached.");
		} catch(IOException e) {
			System.out.println("IOException - if an I/O error occurs when opening the socket.");
			System.out.println("IOException - if an I/O error occurs when waiting for a connection.");
		} catch(SecurityException e) {
			System.out.println("SecurityException - if a security manager exists and its checkListen method doesn't allow the operation.");
		} catch(IllegalArgumentException e) {
			System.out.println("IllegalArgumentException - if the port parameter is outside the specified range of valid port values, which is between 0 and 65535, inclusive.");
			System.out.println("IllegalBlockingModeException - if this socket has an associated channel, the channel is in non-blocking mode, and there is no connection ready to be accepted");
		}
		
		Board game = new Board(clients);
		game.startGame();
	}
}
