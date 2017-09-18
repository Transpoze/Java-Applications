package changeToSEK;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Restövning 1 för missad första övning ('Uppgift 45'). UPPGIFT: Skriv ett
 * program som läser en fil och ersätter alla priser på formen ”xx kr” eller
 * ”xx.xx kr” med motsvarande moderna form ”SEK xx” respektive ”SEK xx.xx”
 * 
 * @author Addi Djikic CELTE13, DD1332, KTH 2016-02-19
 * 
 */
public class KrToSEKConverter {

	// Uses regex to find all matches on the form xx kr and xx.xx kr
	private String theRegex = "\\s[0-9]{2}(\\.[0-9]{2})?\\skr";
	private String allText;
	private String newFile;
	private String writeToThisFile = "/Users/Addi/Documents/workspace/newFileWithSEK.txt";
	private File file = new File(
			"/Users/Addi/Documents/workspace/findTheKr.txt");
	private ArrayList<String> allMatches = new ArrayList<String>();

	/**
	 * Reads the file that contains the old form with 'kr' and stores it in a
	 * String Code skeleton taken from couse DD1332 KTH
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public String readCurrencyFile() throws FileNotFoundException {

		Scanner scan_file = new Scanner(file, "UTF-8");
		scan_file.useLocale(Locale.ENGLISH);
		while (scan_file.hasNextLine()) {
			allText = allText + " " + scan_file.nextLine();
		}
		scan_file.close();
		// System.out.println(allText);
		return allText;
	}

	/**
	 * Replaces all xx kr or xx.xx kr to SEK xx (xx.xx) in the file and writes
	 * the new file with 'SEK' to another new file
	 */
	public void regexReplacer() {
		Pattern replace = Pattern.compile(theRegex);
		Matcher regexMatcher = replace.matcher(allText);

		while (regexMatcher.find()) {
			allMatches.add(regexMatcher.group());
		}

		for (int i = 0; i < allMatches.size(); i++) {
			newFile = regexMatcher.replaceAll(" SEK" + allMatches.get(i))
					.replace("kr", "");
		}
		// System.out.println(newFile.replace("null", ""));

		try {

			FileWriter fw = new FileWriter(writeToThisFile, true);
			fw.write(newFile.replace("null", ""));
			fw.close();
		} catch (IOException e) {
			System.out.println("Could not read to new file");
		}

	}

	/**
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		KrToSEKConverter krC = new KrToSEKConverter();
		krC.readCurrencyFile();
		krC.regexReplacer();

	}

}
