package basicjava;

import java.util.ArrayList;

public class RecursionExercises {

	public int countDigits(int digit) {
		if ( digit < 0 ) {        // Check if digit is negative return - times digit.
			digit = -digit;
		}
		if ( digit < 10) {        // Checks if digit is less than 10(excluding 10) return 1 because all number between 0 to 9 are single decimal number.
			return 1;
		}
		else {
			return 1 + countDigits(digit/10);
		}
		
	}

	public int addDigits(int num) {
		if ( num < 0 || num == 0) {          // Check if num is less than equal to 0. Return 0
			return 0;
			
		}
		if ( num < 10) {          // If num is less than 10 return num.
			return num;
		}
		else {
			return num % 10 + addDigits( num / 10 );
		}
	}

	public static int sum(int sum_From, int sum_To) {
		if (sum_From < 0 || sum_To < 0 || sum_From > sum_To) {
			return 0;
		}
		else if ( sum_From == sum_To) {
			return sum_From;
		}
		else {
			return sum_From + sum(sum_From + 1,sum_To);
		}
	}

	public int sumEvenNumbers(int sum_Even_Num) {
		if (sum_Even_Num < 0) {
			return 0;
		}
		else if ( sum_Even_Num % 2 == 0 ) {
			return sum_Even_Num + sumEvenNumbers(sum_Even_Num - 2);
		}
		else {
			return sumEvenNumbers(sum_Even_Num-1);
		}
	}

	public int countVowels(String Str) {
		if (Str == null || Str == "") {
			return 0;
		}
		char uppr_Char = Character.toUpperCase(Str.charAt(0));
		int Cntr = countVowels(Str.substring(1));
		if (uppr_Char == 'A' || uppr_Char == 'E' || uppr_Char == 'I' || uppr_Char == 'O' || uppr_Char == 'U') {
			Cntr = Cntr + 1;
		}
		return Cntr;
	}

	public static String removeVowels(String Str) {
		if (Str == "") {
			return "";
		}if (Str == null) {
			return null;
		}
		char first_Char = Str.charAt(0);// It will store the first character of String Str to char first_Char
		String rest_Str = removeVowels(Str.substring(1));
		if (first_Char == 'A' || first_Char == 'a' || first_Char == 'e' || first_Char == 'E' || first_Char == 'i' ||
				first_Char == 'I' || first_Char == 'o' || first_Char == 'O' || first_Char == 'u' || first_Char == 'U'	) {
			return rest_Str;
		}
		return first_Char + rest_Str;
	}
	

	public static int sumOfMultiple(ArrayList<Integer> list_Num) {
		 if (list_Num == null || list_Num.size() == 0 ) {      // Check if ArrayList is null or has length 0 will return a 0.
			 return 0;
		}
		 else {
			 int num_Removed = list_Num.remove(0); // num_Removed store the int that has been removed from the arrayList.
			 if (num_Removed % 5 == 0) {
				 return num_Removed + sumOfMultiple(list_Num);
			}else {
				return sumOfMultiple(list_Num);
			}
		 }
	}

}
