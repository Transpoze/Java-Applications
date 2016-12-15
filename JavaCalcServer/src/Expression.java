import java.util.*;
import java.io.*;

/**
 * 
 * @author (Co.Author) Addi Djikic
 * 
 *         This class skeleton is taken from the course DD1332 from KTH and
 *         includes the grammar for the calculation made by the java server.
 * 
 */
public class Expression {

	Scanner scanner;
	private StringBuilder soFar = new StringBuilder();

	// ----------------------------------------------------------
	/**
	 * This main below is not needed, moved to the client class
	 */
	/*
	 * public static void main(String[] argv) { Expression p = new
	 * Expression(new Scanner("5 * 3 + 2")); if (argv.length > 0) { String s =
	 * padWithSpace(argv[0]); p = new Expression(new Scanner(s)); } double
	 * result = p.readExpr(); System.out.println("Parsed line: " + p.soFar);
	 * System.out.println(result); }
	 */
	// ----------------------------------------------------------

	/**
	 * Get the parsed calculated result from this func. to send back to client
	 * 
	 * @param clientExp
	 * @return
	 */
	// ----------------------------------------------------------
	String getExpReusult(String clientExp) {
		try {
			Expression p = new Expression(new Scanner(clientExp));
			if (!clientExp.equals(null)) {
				String s = padWithSpace(clientExp);
				p = new Expression(new Scanner(s));
			}
			double result = p.readExpr();
			System.out.println("Parsed expression: " + p.soFar);
			return String.valueOf(result);
		} catch (Exception ex) {
			System.out.println("Invalid input, could not parse correctly");
		}
		return "Valid characters, but your input was incorrect";
	}

	// ----------------------------------------------------------

	/**
	 * @param s
	 *            Expression String where operators will be space separated
	 *            1+2*3*(1-2) -> 1 + 2 * 3 * ( 1 - 2 )
	 */
	static String padWithSpace(String s) {
		s = s.replaceAll("\\+", " + ");
		s = s.replaceAll("-", " - ");
		s = s.replaceAll("\\*", " * ");
		s = s.replaceAll("/", " / ");
		s = s.replaceAll("\\)", " ) ");
		s = s.replaceAll("\\(", " ( ");
		return s;
	}

	// ------------------------------------------------------
	// Constructor
	Expression(Scanner s) {
		scanner = s;
	}

	// ------------------------------------------------------
	// I/O methods - note: must have space between tokens, 3*2 will not be
	// parsed
	private double popValue() {
		String next = scanner.next();
		soFar.append(" " + next);
		return Double.parseDouble(next);
	}

	private double popNegativeValue() {
		scanner.next("-");
		String next = scanner.next();
		soFar.append(" -" + next);
		return -1 * Double.parseDouble(next);
	}

	private String popToken() {
		String next = scanner.next();
		soFar.append(" " + next);
		return next;
	}

	private boolean peek(char s) {
		if (scanner.hasNext()) {
			if (scanner.hasNext("\\" + s)) // if s = '*' then must prefix "\\*"
				return true;
		}
		return false;
	}

	// ///////////////////////////////////////////////////////////////////////////
	// Recursive descent
	//
	// Trace outputs with:
	// System.out.println("in method X:Parsed so far: " + soFar);

	double readExpr() {
		// expr ::= term | term + expr | term - expr

		double x = readTerm(); // expr ::= term ...

		if (peek('+')) { // expr ::= term + expr
			popToken();
			x += readExpr();
		}
		// expr ::= term - expr

		// ***
		else if (peek('-')) {
			while (peek('-')) {
				popToken();
				x -= readTerm();
			}
			if (peek('+')) {
				popToken();
				x += readExpr();
			}
		}
		// ***

		return x;
	}

	protected double readTerm() {
		// term ::= factor | factor * term | factor / term

		double x = readFactor(); // term ::= factor

		if (peek('*')) { // term ::= factor * term
			popToken();
			return x * readTerm();
		}
		// term ::= factor / term

		// ***
		if (peek('/')) {
			popToken();
			return x / readTerm();
		}
		// ***

		return x;
	}

	protected double readFactor() {
		// factor ::= ( expr ) | value

		if (peek('(')) {
			popToken(); // '('
			double x = readExpr();
			// ***
			if (peek(')')) {
				popToken(); // ')' // Check if ')' at end
			}
			// ***
			return x;
		} else {
			return readVal();
		}
	}

	protected double readVal() {
		return popValue();
	}
}
