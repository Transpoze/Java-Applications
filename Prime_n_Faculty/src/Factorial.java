import java.util.Scanner;

/**
 * 2016-01-21
 * Hemmuppgift 1: Skriv ett program som utför beräkning av fakultet för heltal
 * DD1332 vt 2016
 * 
 * @author Addi_Djikic
 * 
 */

public class Factorial {

	public static void main(String[] args) {

		long factorNbr = 0;
		
		/*
		Scanner scanFactorialNbr = new Scanner(System.in);
		System.out.print("Enter a number you wish to perform factorial on: ");
		factorNbr = scanFactorialNbr.nextLong();
		*/
		

		doFactorial(factorNbr);

	}

	public static void doFactorial(long factorNbr) {

		long i;
		long factorial = factorNbr;

		for (i = 1; i < factorNbr; i++) {

			factorial *= i;
			
			if(i == factorNbr-1){
				System.out.println(factorial);
			}
				
		}

	}

}
