import java.util.Scanner;

/**
 * Hemmuppgift 2: kriv ett program som avgör om ett tal är ett primtal (delbart endast med ett och sig själv)
 * DD1332 vt 2016
 * 
 * @author Addi_Djikic
 * 
 */


public class FindPrime {

	public static void main(String[] args) {

		int checkPrime;
		Scanner scan_prime = new Scanner(System.in);
		System.out.print("Enter a integer to se if it´s a prime: ");
		checkPrime = scan_prime.nextInt();
		FindPrime fp = new FindPrime();
		fp.isItPrime(checkPrime);
		

	}

	private boolean isItPrime(int checkPrime) {
		
		//We do brute force iteration to check prime...
		int i;
		// We dont devide the number by itself or one
		for (i = 2; i < checkPrime; i++) {

			if (checkPrime % i == 0) {
				// If there is no rest after modulus, the number is divisible
				System.out.println("\nSorry, not a prime number");
				return false;
			}
		}
		System.out.println("\nLooks like you found a prime buddy!");
		return true;
	}

}
