import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Addi Djikic
 * 
 *         This threading class runs the threads needed for the server while it
 *         listens for the client inputs and passes them to the server.
 * 
 */
public class Threading extends Thread {

	Socket socket;
	String inputLine = "";
	Expression exp = new Expression(null);
	String expResult = "Null";
	//Check if user input only contains the valid characters
	String theRegex = "[0-9\\*\\-\\/\\+\\(\\)\\s]+";

	// Thread constructor for passing socket
	Threading(Socket socket) {
		this.socket = socket;
	}

	/**
	 * Check if the client input only contains valid characters
	 * 
	 * @param clientExp
	 * @return
	 */
	boolean regexChecker(String clientExp) {
		Pattern checkRegex = Pattern.compile(this.theRegex);
		Matcher regexMatcher = checkRegex.matcher(clientExp);
		return regexMatcher.matches();
	}

	public void run() {

		try {
			// -------------------------------------- Check server connection
			System.out.println("\nPort access accepted, running server...");
			System.out.println("Connection from : "
					+ socket.getInetAddress().getHostAddress() + ':'
					+ socket.getPort());
			System.out.println("(Terminate server with <Ctrl+C>)");
			System.out.println("\n\tWaiting for client...\n");
			// --------------------------------------

			/**
			 * Used to read the clients input and write the output back to
			 * client
			 */
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			// Read back the output from client to server PrintWriter
			this.inputLine = in.readLine();

			// Stop server if client input is 'exit'
			if (this.inputLine.equals("exit")) {
				System.out.println("Server is now disconnected..");
				System.exit(0);
			}

			// -------------------------------------- Handle clients input
			
			// Confirm server receive of expression
			out.println("Expression: < " + inputLine + " > received at server");

			boolean regexConfirm = regexChecker(inputLine); // Check if input is
															// valid characters
			if (regexConfirm == true) {

				long startTime = System.currentTimeMillis();
				expResult = exp.getExpReusult(inputLine);
				long stopTime = System.currentTimeMillis();
				long totalTime = stopTime - startTime;

				out.println("Calculated Expression: " + expResult);
				out.println("Expression Cost: < " + totalTime
						+ "ms > to calculate");
			} else {
				out.println("Sorry, you have entered an invalid expression");
			}
			socket.close();
			// --------------------------------------

		} catch (IOException e) {
			System.out.println("I/O exception in thread");
		}

	}

}
