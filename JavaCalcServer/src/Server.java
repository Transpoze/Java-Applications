import java.io.IOException;
import java.net.ServerSocket;

/**
 * 
 * @author Addi Djikic
 * 
 *         This class initializes the java server and starts the thread to loop
 *         listener for client.
 * 
 */
public class Server {

	private SetPortNumber setPort = new SetPortNumber();
	private boolean runs = true;
	ServerSocket serverSocket = new ServerSocket();

	Server() throws IOException {

		try {
			// Set server socket with same port as the client
			serverSocket = new java.net.ServerSocket(setPort.getPort());
			System.out.println("-------------------------------");
			System.out.println("\nServer is up and running...");
			System.out.println("-------------------------------");

		} catch (IOException e) {
			System.out.println("Could not access port: " + setPort.getPort());
			System.exit(0);
		}

		// Start the thread for the server until terminated by the client
		while (this.runs) {
			new Threading(serverSocket.accept()).start();
		}
	}

	public static void main(String[] args) throws IOException {
		new Server();
	}

}
