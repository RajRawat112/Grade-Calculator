package basicjava;

import java.util.Arrays;

public class CCArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void replace(char[] chars, char c, char d) {
		int len = chars.length;
		for(int i = 0; i<len ; i++) {
			char U = Character.toLowerCase(c); // Convert Character C to lower case.
			char L = Character.toUpperCase(c); // Convert Character C to upper Case.
			if(chars[i] == c || chars[i] == U || chars[i] == L) {
				chars[i] = d;
			}
		}
		
	}

	public static void sortAlphabetic(String[] strs) {
		Arrays.sort(strs, String.CASE_INSENSITIVE_ORDER); /* The CASE_INSENSITIVE_ORDER sort the Array of Strings
		without differentiating between Upper and Lower Case*/
		
		
		
	}

}
