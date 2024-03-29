package application;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class GradeCalculatorController {

    @FXML
    private ChoiceBox<Integer> codingcChoiceBox;
    
    @FXML
    private ChoiceBox<Integer> optionalChoiceBox;
    
    @FXML
    private Slider quizSlider;

    @FXML
    private TextField projectGradeTextfield;
    
    @FXML
    private Label courseGradeLabel;
    
    @FXML
    Label projectErrorLabel;

    /**
     * Check if the value provided is a valid project Grade. A project Grade must be numeric and a
     * percentage between (0 to 100). If Valid, the equivalent double will be returned, if not, this must
     * return Zero.
     * @param valueEntered the value entered as the project grade
     * @return the double value of valueEntered if it is numeric, a valid percentage grade not % symbol
     *  and 0 otherwise.
     */
    
    double getProjectGrade(String valueEntered) {
    	
    	// This for checking whether the user is inputing a numeric value or not.
    	
    	boolean validProjectGrade = true;  // Here validProjectGrade is a Flag Variable
    	int decimalflag = 0; // Here decimalflag is flag variable
    	for (char c : valueEntered.toCharArray()) {
    		if (!Character.isDigit(c) && c!= '.' && c!='-') {
    			validProjectGrade = false;
    			projectErrorLabel.setText("Don't include the character:  " + c + " Project grade should be percentage. ");
    		}
    		// Checks if Character 'c' is a digit.
    		if (c == '.') {
    			decimalflag += 1;
    		} 
    		// Turn validProjectGrade variable into false if there is more than 1 decimals.
    		if (decimalflag>1) {
    			validProjectGrade = false;
    			projectErrorLabel.setText("Don't include the character '.' more than once");
    		}
    	}
    	// Default project grade is 0. If valid number entered, convert user input to floating point number.
    	
    	double projectGrade = 0;
    	if (validProjectGrade) {
    		projectGrade = Double.parseDouble(valueEntered);
    	}
    	
    	// Check if project Grade is a valid percentage Grade. If not, reset to default grade of 0. 
    	if (projectGrade < 0 || projectGrade > 100) {
    		projectErrorLabel.setText("Project Grade should be between 0% and 100%. Invalid project grade:" + projectGrade);
    		projectGrade = 0;
    	}
    	
    	return projectGrade;
    }

    
    /**
     * Calculate all project Grade, Quiz Grade , Required/Optional Coding Challenge Grades into a Final
     * Single Grade.
     * @param event Takes input from the user in the GUI interface and then trigger the calculation.
     */
    @FXML
    void calculateGrade(ActionEvent event) {
    	projectErrorLabel.setText("");
    	double courseGrade = 0.0;
    	String projectValueEntered = projectGradeTextfield.getText();
    	
    	// Check if user entered a percentage grade. If not, display error message and don't include project grade in course grade.
    	
    	double projectGrade = getProjectGrade(projectValueEntered);
    	// Include Project Grade in Course Grade

    	courseGrade = courseGrade + projectGrade * 50/100;
    	
    	System.out.println("Project grade " + projectGrade + " Course grade so far: " + courseGrade);
    	
    	double quizGrade = quizSlider.getValue();
    	courseGrade = courseGrade + (quizGrade * 2.5);
    	System.out.println("Quiz grade " + quizGrade + " Course grade so far: " + courseGrade);
    	
    	int codingChallengesPassed = codingcChoiceBox.getValue();
    	courseGrade = courseGrade + (codingChallengesPassed * 1.25);
    	System.out.println("Coding Challanges passed:  " + codingChallengesPassed + " Course grade so far: " + courseGrade);
    	
    	int optionalcodingChallengesPassed = optionalChoiceBox.getValue();
    	courseGrade = courseGrade + (optionalcodingChallengesPassed * 1.25);
    	System.out.println("Optional Coding Challanges passed:  " + optionalcodingChallengesPassed + " Course grade so far: " + courseGrade);

    	courseGradeLabel.setText(String.format("Your course grade is %.2f", courseGrade));
    	

    }

}
