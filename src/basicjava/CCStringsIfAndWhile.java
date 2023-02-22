package basicjava;

public class CCStringsIfAndWhile {

	public static boolean isDigit(char c) {
		if (c == '1' || c == '2' || c == '3' || c == '4' || c== '5' || c == '6' || c == '7' || c== '8' || c == '9' || c == '0' ) {
			return true;
		}
		return false;
	
	}

	public static int count(String string1, String string2) {
		int flag_count = 0;
		string1=string1.toLowerCase();
		string2=string2.toLowerCase();
			for(int a=0;a<string1.length();a++) {
				for(int b=0;b<string2.length();b++){
					if (string1.charAt(a) == string2.charAt(b)) {
						flag_count = flag_count + 1;
					}
					
				}
				
			}
			
		return flag_count;
	}

	public static int smallestDigit(int i) {
		int smallest_no = 9;
		int num = 0;
		if (i<0) {
			i = i*-1;
		}
		if (i == 0) {
			smallest_no = 0;
		}
		else {
			while (i != 0) {
				num = i%10;
				i = i/10;
				if (num<smallest_no) {
					smallest_no = num;
				}
			}
			
		}
		return smallest_no;
	}
	
}
			
		


