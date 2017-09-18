package sorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 
 * @author Addi Djikic CELTE13, DD1332, KTH 2016
 * 
 *         Hemuppgift 2, Övn 6. Sorterar en lista med negtiva och positiva tal i
 *         stigande ordning utan att ta hänsyn till deras storlek
 * 
 */
public class SortSmallToBig {

	public ArrayList<Integer> integerList = new ArrayList<Integer>();

	/**
	 * Sorts all the integers from negative to positive in the right order and
	 * prints it out
	 * 
	 * @param listToSort
	 */
	public void sortList(ArrayList<Integer> listToSort) {

		System.out.println("Original list: " + integerList);


		// Loops until all elements are swaped in the right order, is will swap
		// the amount of times as the list is long
		for (int j = 0; j <= integerList.size() - 1; ++j) {
			for (int i = 1; i < integerList.size(); ++i) {
				if (((integerList.get(i - 1) <= 0 && integerList.get(i) <= 0) || (integerList
						.get(i - 1).equals(integerList.get(i)) || (integerList
						.get(i - 1) >= 0 && integerList.get(i) >= 0)))) {
					continue;
				} else if ((integerList.get(i - 1) >= 0 && integerList.get(i) <= 0)) {
					Collections.swap(integerList, i, i - 1);
				}
			}
		}
		System.out.println("Sorted list: " + integerList);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SortSmallToBig stb = new SortSmallToBig();
		stb.integerList.addAll(Arrays.asList(45, 3, 54, 4, -4, -2, 4, 6, 23,
				-8, -78, 34, 9, 4, 7, 4, -67, -44, -8, 2));
		stb.sortList(stb.integerList);
	}
}
