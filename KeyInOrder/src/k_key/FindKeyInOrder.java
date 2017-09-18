package k_key;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author Addi Djikic. Uses the quicksort algorithm to find the K:th value
 *         inside a integer array list. I have not taken care if the user chooses
 *         a value outside the list or an invalid input. 
 */

public class FindKeyInOrder {

	private int findThisValInOrder = 0;
	private ArrayList<Integer> intList = new ArrayList<Integer>();

	/**
	 * Finds the K:th value in order from the list given i Main
	 * 
	 * @param intList
	 * @return
	 */
	private int findKVal(ArrayList<Integer> integerList, int findThisKVal) {

		findThisValInOrder = findThisKVal;

		int counter = 0;
		for (int i = 0; i < integerList.size(); i++) {
			int tempCount = 0;
			for (int j = 0; j < integerList.size(); j++) {
				if (integerList.get(i) <= integerList.get(j)
						&& tempCount < integerList.size() - findThisValInOrder
								+ 1) {
					tempCount++;
					if (tempCount == integerList.size() - findThisValInOrder
							+ 1
							&& integerList.get(i) > counter) {
						counter = integerList.get(i);
						j = integerList.size();
					}
				}
			}
		}
		int kVal = counter;

		return kVal;
	}

	/**
	 * Sets the list with values and sends it to method 'FindKeyInOrder'
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		FindKeyInOrder fk = new FindKeyInOrder();
		fk.intList.addAll(Arrays.asList(66, 34, 13, 43, 6, 5, 8, 9, 89, 2, 3,
				4, 76));
		System.out.println("The list: " + fk.intList);
		Scanner sc = new Scanner(System.in);
		System.out
				.print("Write the K:th value in order you want to access from the list above: ");
		String userInput = sc.nextLine();
		int findThisKthVal = Integer.parseInt(userInput);
		System.out.println("\nThe K:th value in the list is: "
				+ fk.findKVal(fk.intList, findThisKthVal));
	}

}
