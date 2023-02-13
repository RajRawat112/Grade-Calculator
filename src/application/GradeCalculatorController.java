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

    @FXML
    void calculateGrade(ActionEvent event) {
    	double courseGrade = 0.0;
    	String projectGrade = projectGradeTextfield.getText();
    	courseGrade = Double.parseDouble(projectGrade) * 0.4;
    	System.out.println("Project grade " + projectGrade + " Course grade so far: " + courseGrade);
    	
    	double quizGrade = quizSlider.getValue();
    	courseGrade = courseGrade + (quizGrade * 3.0);
    	System.out.println("Quiz grade " + quizGrade + " Course grade so far: " + courseGrade);
    	
    	int codingChallengesPassed = codingcChoiceBox.getValue();
    	courseGrade = courseGrade + (codingChallengesPassed * 1.5);
    	System.out.println("Coding Challanges passed:  " + codingChallengesPassed + " Course grade so far: " + courseGrade);
    	
    	int optionalcodingChallengesPassed = optionalChoiceBox.getValue();
    	courseGrade = courseGrade + (optionalcodingChallengesPassed * 1.5);
    	System.out.println("Optional Coding Challanges passed:  " + optionalcodingChallengesPassed + " Course grade so far: " + courseGrade);

    	courseGradeLabel.setText(String.format("Your course grade is %.2f", courseGrade));
    	

    }

}
