package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GradeCalculatorController {
	Stage applicationStage;
	double averageQuizGrade = 0.0;
	double averageOptionalQuizGrade = 0.0;

    @FXML
    private ChoiceBox<Integer> codingcChoiceBox;
    
    @FXML
    private ChoiceBox<Integer> optionalChoiceBox;
    
    @FXML
    private ChoiceBox<Integer> quizzesChoiceBox;
    
    @FXML
    private ChoiceBox<Integer> optinalQuizzesChoiceBox;

    @FXML
    private TextField projectGradeTextfield;
    
    @FXML
    private TextField avgRequiredQuizGrade;
    
    @FXML
    private TextField avgOptionalQuizGrade;
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
     *  
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
    
    void calculateQuizGrade(Scene MainScene, ArrayList<TextField> quizGradeTextfields) {
    	averageQuizGrade = 0.0;
    	for (TextField textfield : quizGradeTextfields) {
        	averageQuizGrade += Double.parseDouble(textfield.getText());
        	

    	}
    	averageQuizGrade = averageQuizGrade / quizGradeTextfields.size();
    	applicationStage.setScene(MainScene);
    	avgRequiredQuizGrade.setText(String.format(" ",averageQuizGrade));
    }

    @FXML
    void getQuizGrades(ActionEvent event) {
    	Scene mainScene = applicationStage.getScene();
    	int numberOfQuizzes = quizzesChoiceBox.getValue();
    	int rowCounter = 0;
    	VBox allRows = new VBox();
    	ArrayList<TextField> quizTextFields = new ArrayList<TextField>();
    	while (rowCounter < numberOfQuizzes) {
    		rowCounter++;
        	
    		HBox quizRow = new HBox();
        	Label quizLabel = new Label("Quiz" + rowCounter + "grade");
        	TextField quizGradeTextfield = new TextField();
        	quizTextFields.add(quizGradeTextfield);

        	quizRow.getChildren().addAll(quizLabel,quizGradeTextfield);
        	allRows.getChildren().add(quizRow);
    	}
    	
    	Button doneButton = new Button("Done");
    	doneButton.setOnAction(doneEvent -> calculateQuizGrade(mainScene,quizTextFields));
    	allRows.getChildren().add(doneButton);
    	Scene quizScene = new Scene(allRows);
    	applicationStage.setScene(quizScene);
    	applicationStage.setTitle("Required Quizzes");
    }
    
    
    
 
    void calculateOptinalQuizGrade(Scene MainScene, ArrayList<TextField> quizGradeTextfields1) {
    	averageOptionalQuizGrade = 0.0;
    	for (TextField textfield : quizGradeTextfields1) {
    		averageOptionalQuizGrade += Double.parseDouble(textfield.getText());

    		
    	}
    	averageOptionalQuizGrade = averageOptionalQuizGrade / quizGradeTextfields1.size();
    	applicationStage.setScene(MainScene);
    }  
    
    
    
    
    

    @FXML
    void getOptionalQuizGrades(ActionEvent event) {
    	Scene mainScene = applicationStage.getScene();
    	int numberOfQuizzes1 = optinalQuizzesChoiceBox.getValue();
    	int rowCounter1 = 0;
    	VBox allRows1 = new VBox();
    	ArrayList<TextField> quizTextFields1 = new ArrayList<TextField>();
    	while (rowCounter1 < numberOfQuizzes1) {
    		rowCounter1++;
        	
    		HBox quizRow = new HBox();
        	Label quizLabel1 = new Label("Optinal Quiz" + rowCounter1 + "grade");
        	TextField quizGradeTextfield1 = new TextField();
        	quizTextFields1.add(quizGradeTextfield1);

        	quizRow.getChildren().addAll(quizLabel1,quizGradeTextfield1);
        	allRows1.getChildren().add(quizRow);
    	}
    	
    	Button doneButton = new Button("Done");
    	doneButton.setOnAction(doneEvent -> calculateOptinalQuizGrade(mainScene,quizTextFields1));
    	allRows1.getChildren().add(doneButton);
    	Scene quizScene = new Scene(allRows1);
    	applicationStage.setScene(quizScene);
    	applicationStage.setTitle("Optional Required Quizzes");
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
    	
    	double quizGrade = averageQuizGrade;
    	courseGrade = courseGrade + (quizGrade * 1.25);
    	System.out.println("Quiz grade " + quizGrade + " Course grade so far: " + courseGrade);
    	
    	double optionalQuizGrade = averageOptionalQuizGrade;
    	courseGrade = courseGrade + (optionalQuizGrade * 1.25);
    	System.out.println("Optinal Quiz grade " + optionalQuizGrade + " Course grade so far: " + courseGrade);
    	
    	int codingChallengesPassed = codingcChoiceBox.getValue();
    	courseGrade = courseGrade + (codingChallengesPassed * 1.25);
    	System.out.println("Coding Challanges passed:  " + codingChallengesPassed + " Course grade so far: " + courseGrade);
    	
    	int optionalcodingChallengesPassed = optionalChoiceBox.getValue();
    	courseGrade = courseGrade + (optionalcodingChallengesPassed * 1.25);
    	System.out.println("Optional Coding Challanges passed:  " + optionalcodingChallengesPassed + " Course grade so far: " + courseGrade);

    	courseGradeLabel.setText(String.format("Your course grade is %.2f", courseGrade));
    	

    }

}
