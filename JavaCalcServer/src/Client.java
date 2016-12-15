import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author Addi Djikic
 * 
 *         This is the client that will send the mathematical expressions to the
 *         java server to calculate them.
 * 
 */
public class Client {

	SetPortNumber spn = new SetPortNumber();
	Socket socket;
	String acceptedResultFromServer;
	String userInput = "";

	Client() throws IOException {

		try {

			while (!userInput.equals("exit")) {
				// Bind server and client with same IP and port
				socket = new Socket(spn.getIP(), spn.getPort());

				// Use to pass arguments to server
				PrintWriter out = new PrintWriter(socket.getOutputStream(),
						true);

				// ------------- Read user input expression
				System.out.print("\nSend expression to server (or 'exit'): ");
				BufferedReader stdIn = new BufferedReader(
						new InputStreamReader(System.in));
				userInput = stdIn.readLine();
				// -------------

				// Send users input to server
				out.println(userInput);

				// Get the servers output result
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				// ------------ Server received expression confirmation
				String confirmReceived = in.readLine();
				if (confirmReceived != null) {
					System.out.println(confirmReceived);
				} else {
					System.out.print("");
				}
				// ------------

				// ------------ Display calculated expression from server
				String calculatedExp = in.readLine();
				if (calculatedExp != null) {
					System.out.println("\n" + calculatedExp);
				} else {
					System.out.print("");
				}
				// ------------

				// ------------ Time duration cost of calculation
				String totalTime = in.readLine();
				if (totalTime != null) {
					System.out.println(totalTime);
				} else {
					System.out.print("");
				}
				// ------------

				in.close();
				out.close();
				socket.close();
			}
		} catch (UnknownHostException e) {
			System.out.println("Unknown host, could not bind socket");
		} catch (IOException e) {
			System.out.println("I/O exception, could not bind socket");
		}

	}

	public static void main(String[] args) throws IOException {

		new Client();

	}
}
