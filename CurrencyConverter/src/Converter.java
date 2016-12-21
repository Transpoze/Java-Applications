import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Converter {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		DecimalFormat deciForm = new DecimalFormat("#.##");
		double choosenValue = Double.parseDouble(args[1]);

		
		File file = new File("/Users/Addi/Documents/workspace/valutor.txt");
		Scanner scan_file = new Scanner(file, "UTF-8");

		
		ArrayList<String> currencyArray = new ArrayList<String>();
		ArrayList<Double> valueArray = new ArrayList<Double>();

		scan_file.useLocale(Locale.ENGLISH);
		while (scan_file.hasNextLine()) {
			String currency = scan_file.next();
			String rate = scan_file.next();
			currencyArray.add(currency);
			double value = Double.parseDouble(rate);
			valueArray.add(value);
			scan_file.nextLine();
			// System.out.println(currency + " " + value);
			
		}
		scan_file.close();
		
		
		Map<String, Double> valutaMap = new HashMap<>();
		int arSize = currencyArray.size();
		for (int i = 0; i < arSize; i++) {
			valutaMap.put(currencyArray.get(i), valueArray.get(i));
		}

		System.out.println("");

		if (args.length == 2) {
			double convertedValue = choosenValue
					* (valutaMap.get(args[0]) / valutaMap.get("SEK"));
			System.out.println(choosenValue + " " + args[0] + " = "
					+ deciForm.format(convertedValue) + " " + "SEK");
		}

		else {
			double convertedValue = choosenValue
					* (valutaMap.get(args[0]) / valutaMap.get(args[2]));
			System.out.println(choosenValue + " " + args[0] + " = "
					+ deciForm.format(convertedValue) + " " + args[2]);
		}

	}

}
