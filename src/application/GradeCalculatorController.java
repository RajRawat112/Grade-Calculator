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
	double averageRequiredQuizGrade = 0.0;
	double averageOptionalQuizGrade = 0.0;

    @FXML
    private TextField projectGradeTextField;

    @FXML
    private ChoiceBox<Integer> optionalCodingChallengesChoiceBox;

    @FXML
    private ChoiceBox<Integer> reqeiredCodingChallengesChoiceBox;
    
    @FXML
    private Label courseGradeLabel;
    
    @FXML
    private Label projectGradeErrorLabel;
    
    @FXML
    private ChoiceBox<Integer> optQuizzesChoiceBox;

    @FXML
    private ChoiceBox<Integer> reqQuizzesChoiceBox;
    
    @FXML
    private Label reqAverageQuizGrade;
    
    @FXML 
    private Label optAverageQuizGrade;
    
    private Label quizGradeErrorLabel;
    
    private Label optQuizGradeErrorLabel;
    
    @FXML
    private Label requiredQuizErrorLabel;
    
    @FXML
    private Label optionalQuizErrorLabel;
    
    /**
     * Check if the value provided by the user is valid or not. If the value is numeric and a percentage
     * between (0 and 100). If Valid, a double of equivalent value is returned, if not, this method 
     * returns 0.
     * 
     * @param valueEntered - the value entered as the project grade
     * @return the double of valueEntered if it is numeric and valid percentage and 0 otherwise.
     */    
	double avgOptQuizGrade = 0.0;
    void calculateOptionalQuizGrade(Scene mainScene, ArrayList<TextField> quizGradeTextFields) {
    	optQuizGradeErrorLabel.setText("");
    	boolean noErrors1 = true;
    	double weightOfEachOptQuiz = 1.0/quizGradeTextFields.size();
    	for  (TextField textfield : quizGradeTextFields) {
    		Grade optQuizGrade = new Grade(0,100,weightOfEachOptQuiz); 
    		String errorMessage = optQuizGrade.setValue(textfield.getText());
    		if (!errorMessage.equals("")) {
    			noErrors1 = false;
    			optQuizGradeErrorLabel.setText(errorMessage);
    		}
    		avgOptQuizGrade += optQuizGrade.getWeightedPercentageValue();
     	   
    	}
    	if (noErrors1) {
    	        
    	    	applicationStage.setScene(mainScene);
    	    	optAverageQuizGrade.setText(""+avgOptQuizGrade+"/10");
    	}
    }
    
    
    
    @FXML
    void getOptionalQuizGrades(ActionEvent enterQuizGradesEvent) {
    	Scene mainScene= applicationStage.getScene();
    	applicationStage.setTitle("Optional Quizzes Calculator");
    	
    	
    	int numberOfQuizzes = optQuizzesChoiceBox.getValue();
    	int rowCounter = 0;
    	VBox allRows = new VBox();
    	ArrayList<TextField> quizTextFields = new ArrayList<TextField>();
    	while(rowCounter<numberOfQuizzes) {
    		rowCounter++;
    		HBox quizRow = new HBox();
    		
        	Label quizLabel = new Label("Quiz " + rowCounter + " grade");
        	TextField quizGradeTextField = new TextField();
        	quizTextFields.add(quizGradeTextField);

        	
        	quizRow.getChildren().addAll(quizLabel,quizGradeTextField);
        	
        	
        	allRows.getChildren().add(quizRow);
        	
        	optQuizGradeErrorLabel = new Label();
        	allRows.getChildren().add(optQuizGradeErrorLabel);
        	
        	
        	
    	}
    	Button doneButton = new Button("Done");
    	//This will transfer us to a new pop-up window name"Optional Quiz Grades"
    	doneButton.setOnAction(doneEvent -> calculateOptionalQuizGrade(mainScene,quizTextFields));
    	allRows.getChildren().add(doneButton);
    	
    	Scene quizScene = new Scene(allRows);
    	applicationStage.setScene(quizScene);
    }
    
	double avgReqQuizGrade = 0.0;
    void calculateRequiredQuizGrade(Scene mainScene, ArrayList<TextField> quizGradeTextFields) {
    	quizGradeErrorLabel.setText("");
    	boolean noErrors2 = true;
    	double weightofEachQuiz = 1.0/quizGradeTextFields.size(); 
    	for  (TextField textfield : quizGradeTextFields) {
    		Grade quizGrade = new Grade(0, 100, weightofEachQuiz);
    		String errorMessage = quizGrade.setValue(textfield.getText());
    		if (!errorMessage.equals("")){
    			noErrors2 = false;
    			quizGradeErrorLabel.setText(errorMessage);
    		}
    		avgReqQuizGrade += 	quizGrade.getWeightedPercentageValue();
     	   
    	}
    	
    	if (noErrors2) {
	        applicationStage.setScene(mainScene);
	    	reqAverageQuizGrade.setText(""+avgReqQuizGrade+"/10");
    		
    	}
    }
    
    
    @FXML
    void getRequiredQuizGrades(ActionEvent enterQuizGradesEvent) {
    	Scene mainScene= applicationStage.getScene();
    	applicationStage.setTitle("Required Quiz Calculator");
    	int numberOfQuizzes = reqQuizzesChoiceBox.getValue();
    	int rowCounter = 0;
    	VBox allRows = new VBox();
    	ArrayList<TextField> quizTextFields = new ArrayList<TextField>();
    	while(rowCounter<numberOfQuizzes) {
    		rowCounter++;
    		HBox quizRow = new HBox();
    		
        	Label quizLabel = new Label("Quiz " + rowCounter + " grade");
        	TextField quizGradeTextField = new TextField();
        	quizTextFields.add(quizGradeTextField);

        	
        	quizRow.getChildren().addAll(quizLabel,quizGradeTextField);
        	
        	
        	allRows.getChildren().add(quizRow);
        	quizGradeErrorLabel = new Label();
        	allRows.getChildren().add(quizGradeErrorLabel);
        	
        	
        	
    	}
    	Button doneButton = new Button("Done");
    	//This will transfer us to a new pop-up window name"Required Quiz Grades"
    	doneButton.setOnAction(doneEvent -> calculateRequiredQuizGrade(mainScene,quizTextFields));
    	allRows.getChildren().add(doneButton);
    	
    	Scene quizScene = new Scene(allRows);
    	applicationStage.setScene(quizScene);
    }
   
    /**
     * Compute the grades of all components in a course. The user can enter their grades for different components
     * via a pop-up window. The result of their overall course grade will also be displayed there. 
     * @param event - this method is called when the "Calculate Grade" button is clicked on the pop-up window. 
     */

    @FXML
    void calculateGrade(ActionEvent event) {
    	
    	projectGradeErrorLabel.setText("");
    	double courseGrade = 0.0;
    	String projectValueEntered = projectGradeTextField.getText();
    	Grade projectGrade = new Grade(0, 100, 0.5);
    	projectGradeErrorLabel.setText(projectGrade.setValue(projectValueEntered));

    	
    	Grade quizGrade = new Grade(avgReqQuizGrade, 10, 0.125);

    	

    	
    	Grade optQuizGrade = new Grade(avgOptQuizGrade, 10, 0.125);

    	
    	Grade codingChallengeGrade = new Grade(reqeiredCodingChallengesChoiceBox.getValue(), 15, 0.125);

    	
    	Grade optCodingChallengeGrade = new Grade(optionalCodingChallengesChoiceBox.getValue(), 5, 0.125);

    	    	
        //Check if user entered a percentage grade. If not, display error message
    	//and don't include project grade in course grade
    	
        
    	courseGrade =  projectGrade.getWeightedPercentageValue() + quizGrade.getWeightedPercentageValue() 
    	+ optQuizGrade.getWeightedPercentageValue() + codingChallengeGrade.getWeightedPercentageValue()
    	+ optCodingChallengeGrade.getWeightedPercentageValue();
    	    
    	/*System.out.println("Project Grade entered: "+ projectGrade +
    			". Course grade so far: " + courseGrade);
    	
    	reqAverageQuizGrade.setText(""+avgReqQuizGrade);
    	optAverageQuizGrade.setText(""+avgOptQuizGrade);
    	
    	courseGrade += avgReqQuizGrade * 1.875;
    	System.out.println("Average Required Quiz Grade:"+ avgReqQuizGrade +". Course Grade so far: " + courseGrade);
    	
    	courseGrade += avgOptQuizGrade * 0.625;
    	System.out.println("Average optional Quiz Grade:"+ avgOptQuizGrade +". Course Grade so far: " + courseGrade);
    	
    	double requiredCodingChallengesPassed= reqeiredCodingChallengesChoiceBox.getValue();
    	courseGrade += requiredCodingChallengesPassed * 1.25;
    	System.out.println("Required Coding Challenges Passed: "+ requiredCodingChallengesPassed +
    			". Course grade so far: " + courseGrade);
    	
    	double optionalCodingChallengesPassed= optionalCodingChallengesChoiceBox.getValue();
    	courseGrade += optionalCodingChallengesPassed * 1.25;
    	System.out.println("Optional Coding Challenges Passed: "+ optionalCodingChallengesPassed +
    			". Course grade so far: " + courseGrade);
    	
    	*/
    	//Grade Result Display to the User
    	courseGradeLabel.setText(String.format("Your overall course grade: %.2f", courseGrade));
    	
    	
    	
    }

}