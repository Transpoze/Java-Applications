package typicalValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Addi Djikic CELTE13, DD1332, KTH 2016
 * 
 *         Hemuppgift 1, Övn 5. Hittar det tal i en Array som förekommer flest
 *         antal gånger och skriver ut det, om två tal förekommer lika många
 *         gånger så prioriterar det den minsta av de.
 * 
 */
public class FindTypicalValue {

	private ArrayList<Integer> integerList = new ArrayList<Integer>();
	private ArrayList<Integer> valueList = new ArrayList<Integer>();
	private Map<Integer, Integer> countMap = new HashMap<>();

	/**
	 * Takes the given Array and finds the most common value in the Array
	 * 
	 * @param listOfValues
	 */
	public void findTypicalValue(ArrayList<Integer> listOfValues) {

		// Put all values from the array once in an unsorted new HashSet map
		Set<Integer> integerMap = new HashSet<Integer>(listOfValues);
		// System.out.println(integerMap);

		// Loops and sets the frequency of a number and its value in a new Hash
		// Map
		for (Integer hashKeys : integerMap) {
			int nmbrOfOccurences = Collections
					.frequency(listOfValues, hashKeys);

			countMap.put(nmbrOfOccurences, hashKeys);
			valueList.add(nmbrOfOccurences);

			/*
			 * Use this to see how much every element occurs
			 * 
			 * System.out.println("The number: " + hashKeys + " occured " +
			 * nmbrOfOccurences + " time(s)");
			 */
		}

		// Prioritizes the smallest number when numbers appear an equal amount
		// of times
		if (Collections
				.frequency(valueList, Collections.max(countMap.keySet())) > 1) {
			System.out
					.println("Several numbers occured equal amount of times,");

			System.out.println("The most common and smal number occured is: "
					+ (valueList.get(Collections.max(countMap.keySet())))
					+ " and it occured " + Collections.max(countMap.keySet())
					+ " time(s)");
		}

		else {
			System.out.println("The most common number occured is: "
					+ countMap.get(Collections.max(countMap.keySet()))
					+ " and it occured " + Collections.max(countMap.keySet())
					+ " time(s)");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FindTypicalValue ftv = new FindTypicalValue();
		ftv.integerList.addAll(Arrays.asList(45, 3, 2, 2, 2, -4, -2, 4, 6, 23,
				-8, -78, 34, 9, 9, 9, 9, 9, 5, 5, 4, 4, 2, -67, 5, 5, 5, 4, -44,
				-8, 25, 78));
		ftv.findTypicalValue(ftv.integerList);
	}
}