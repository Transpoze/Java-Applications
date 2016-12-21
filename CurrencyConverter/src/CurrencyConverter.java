

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Addi Djikic, CELTE13, DD1332, 2016
 * 
 *         Labb1: Indata till programmet, d.v.s. valuta och belopp skall anges
 *         på kommandoraden. Om endast en valuta anges, skall det givna beloppet
 *         konverteras till SEK. Om två valutor anges, skall beloppet
 *         konverteras från den första till den andra. Programmet ska meddela
 *         sig begripligt med användaren. Programmet ska inte innehålla varken
 *         koduppreprepning eller hårdkodning. Programmet ska vara uppdelat i
 *         kommenterade funktioner enligt förra kursens principer.
 *
 */

public class CurrencyConverter {

	private String theRegex = "[A-Z]{3}\\s\\d+(((\\.)|(\\,))\\d+)?(\\s[A-Z]{3})?";
	private HashMap<String, Double> currencyMap = new HashMap<String, Double>();
	public String outputMessage;

	/**
	 * Scans the text file and puts the currency as key and rate as value in the
	 * object's HashMap.
	 * 
	 * @throws FileNotFoundException.
	 */
	public void readCurrencies() throws FileNotFoundException {

		//String fileName = "/Users/Addi/Documents/workspace/valutor.txt";
		String fileName = "/Users/Addi/Documents/OldJava_workspace/CurrencyConverter/src/currencies.txt";
		File fileOfCurrencies = new File(fileName);
		// Reads numbers (in English format) from a UTF-8 encoded file. Skeleton
		// of code taken from:
		// https://www.kth.se/social/files/56a39c2ef27654165ebbdd4a/ReadCurrencies.java

		Scanner sc = new Scanner(fileOfCurrencies, "UTF-8");
		sc.useLocale(Locale.ENGLISH);
		while (sc.hasNextLine()) {
			String currency = sc.next();
			double rate = sc.nextDouble();
			sc.nextLine();

			this.currencyMap.put(currency, rate);
		}
		sc.close();
	}

	/**
	 * Method checks if user has entered a valid input or not.
	 * 
	 * @param userInput
	 *            user's input.
	 * @return true if the user's input is on the correct form, false if input
	 *         expression is not.
	 */

	public boolean regexChecker(String userInput) {

		Pattern checkRegex = Pattern.compile(theRegex);

		Matcher regexMatcher = checkRegex.matcher(userInput);

		return regexMatcher.matches();

	}

	/**
	 * If user input is correct, the output message is
	 * "amount currency1 = converted amount currency2".
	 * 
	 * @param args
	 *            an array of the users input.
	 * 
	 */

	public void currencyCalculator(String[] args) {

		String currency1 = args[0];
		double amount = Double.parseDouble(args[1].replaceAll(",", "."));
		String currency2 = null;

		if (args.length == 2) {
			double convertedAmount = amount * this.currencyMap.get(currency1);
			this.outputMessage = amount + " " + currency1 + " = " + String.format(Locale.US, "%.2f", convertedAmount)
					+ " SEK";
		}

		else {
			currency2 = args[2];
			double convertedAmount = this.currencyMap.get(currency1) / this.currencyMap.get(currency2) * amount;
			this.outputMessage = amount + " " + currency1 + " = " + String.format(Locale.US, "%.2f", convertedAmount)
					+ " " + currency2;
		}

	}

	/**
	 * This method contains the logic and checks the user's input and assigns a
	 * suitable string for the output message accordingly.
	 * 
	 * @param userInput:
	 *            the user's input string.
	 * @param arrInput:
	 *            an array of the input.
	 */

	public void currencyOutput(String userInput, String[] arrInput) {

		if (userInput.equals("Q")) {
			System.exit(0);
		}

		else if (regexChecker(userInput) == true) {
			if (currencyMap.containsKey(arrInput[0]) == true
					&& (arrInput.length == 2 || currencyMap.containsKey(arrInput[2]) == true)) {
				currencyCalculator(arrInput);

			}

			else if (currencyMap.containsKey(arrInput[0]) == false) {
				if (arrInput.length == 3 && currencyMap.containsKey(arrInput[2]) == false) {
					this.outputMessage = "'" + arrInput[0] + "'" + " nor " + "'" + arrInput[2] + "'"
							+ " are recognized currencies.";
				} else {
					this.outputMessage = "'" + arrInput[0] + "'" + " is not a recognized currency.";
				}

			} else if (arrInput.length == 3 && currencyMap.containsKey(arrInput[2]) == false) {
				this.outputMessage = "'" + arrInput[2] + "'" + " is not a recognized currency.";
			}
		} else {
			this.outputMessage = "Usage: java Converter currency amount [currency]";
		}
	}

	public static void main(String[] args) throws FileNotFoundException {

		CurrencyConverter CC = new CurrencyConverter();

		try {
			CC.readCurrencies();
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found!");
			System.exit(0);
		}

		@SuppressWarnings("resource")
		Scanner inputScanner = new Scanner(System.in);

		while (true) {

			System.out.print("Enter a currency, amount and the currency you want to convert to (enter q to quit): ");

			String userInput = inputScanner.nextLine().toUpperCase();

			String[] arrInput = userInput.split(" ");

			CC.currencyOutput(userInput, arrInput);

			System.out.println(CC.outputMessage);

		}
	}
}
