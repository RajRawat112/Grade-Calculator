package application;


public class Grade {
	// attribute/data
	double value;
	double weight;
	int maxValue = 100;
	
	
	Grade(double gradeValue , int maxGradeValue, double weightTowardsCourseGrade){
		value = gradeValue;
		maxValue = maxGradeValue;
		weight = weightTowardsCourseGrade;
	}
	
	/**
	 * 
	 * @return " The Function will return the percentage value of each component like Project Grade, Quiz,
	 *and Coding Challenge"
	 */
	double getWeightedPercentageValue() {
		return value * 100 * weight / maxValue; 
	}
	
	//actions
	String setValue(String valueAsString) {
		String errorMessage = ""; // Start with assuming that there is no error.
    	// This for checking whether the user is inputing a numeric value or not.
    	
    	boolean validGrade = true;  // Here validProjectGrade is a Flag Variable
    	int decimalflag = 0; // Here decimalflag is flag variable
    	for (char c : valueAsString.toCharArray()) {
    		if (!Character.isDigit(c) && c!= '.' && c!='-') {
    			validGrade = false;
    			errorMessage = "Don't include the character:  " + c + " Grade should be percentage. ";
    		}
    		// Checks if Character 'c' is a digit.
    		if (c == '.') {
    			decimalflag += 1;
    		} 
    		// Turn validProjectGrade variable into false if there is more than 1 decimals.
    		if (decimalflag>1) {
    			validGrade = false;
    			errorMessage = "Don't include the character '.' more than once";
    		}
    	}
    	// Default project grade is 0. If valid number entered, convert user input to floating point number.
    	
  
    	if (validGrade) {
    		value = Double.parseDouble(valueAsString);
    	}
    	
    	// Check if project Grade is a valid percentage Grade. If not, reset to default grade of 0. 
    	if (value < 0 || value > maxValue) {
    	errorMessage = String.format("Grade should be between 0% and 100%. Invalid grade: "+ value);
    	value = 0;
    	}
    	
    	return errorMessage;

	}

}
