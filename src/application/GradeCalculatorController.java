package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GradeCalculatorController {
	

	Stage applicationStage;

    @FXML
    private ChoiceBox<Integer> quizzesCompletedChoiceBox;
    
    @FXML
    private ChoiceBox<Integer> optionalquizzesCompletedChoiceBox;

    @FXML
    private ChoiceBox<Integer> optionalCodingChallengeChoiceBox;

    @FXML
    private ChoiceBox<Integer> requiredCodingChallengeChoiceBox;

    @FXML
    private TextField projectGradeTextfield;
    
    @FXML
    private Label courseGradeLabel;
    
    @FXML
    private Label projectGradeErrorLabel;

    @FXML
    private Label reqQuizGradeAverage;
    
    @FXML
    private Label optQuizGradeAverage;
    
    @FXML
    private Button ReqcalculateGradeButton;
    
    @FXML
    private Button OptcalculateGradeButton;
    
    
    
   
    double averageQuizGrade = 0.0;
    Label quizErrorLabel = new Label();
    /**
     * This function will calculate average grade for required quiz.
     * @param mainScene: This will return the user to the main scene after entering the individual Required Quiz Grades.
     * @param quizGradeTextfields: Here the user enter the Grades for Calculation.
     */
    void calculateAverageQuizGrade(Scene mainScene, ArrayList<TextField> quizGradeTextfields) {
    	
    	quizErrorLabel.setText("");
    	
    	double weightPerQuiz = .1/15.0;
    	
    	averageQuizGrade = 0.0;
    	boolean validquizGrade = false;
    	    	   	
    	for (TextField quizGradeTextfield : quizGradeTextfields) {
    		Grade quizGrade = new Grade(0,10,weightPerQuiz);
    		String errorMessage=quizGrade.setValue(quizGradeTextfield.getText());
    		if (!errorMessage.equals("")) {
    			validquizGrade=true;
    			quizErrorLabel.setText(errorMessage);
    		}	
    		averageQuizGrade+= quizGrade.getWeightedPercentageGrade();
    		reqQuizGradeAverage.setText(String.format("Quiz Average: %.2f", averageQuizGrade));
    	}
    	if(!validquizGrade) {
    		applicationStage.setScene(mainScene);
    	}
    }
   
    /**
     * this is the function which provide user to enter the Scene for required Quiz.
     * @param enterQuizGradeEvent: the function is based on the button that says "Enter Quiz Grade" and the linked event opens the scene that has the text fields 
     *and a the amount of quiz text fields are based on the amount selected in the choice box.
     */
    @FXML
    void getQuizGrades(ActionEvent enterQuizGradeEvent) {
    	Scene mainScene = applicationStage.getScene();
    	applicationStage.setTitle("Required Quiz Calculator");
    	
    	int numberofQuizzes = quizzesCompletedChoiceBox.getValue();
    	int row_counter = 0;
    	VBox quizGradeContainer = new VBox();
    	quizGradeContainer.getChildren().add(quizErrorLabel);
    	ArrayList<TextField> quizGradeTextfields = new ArrayList<TextField>();
    	
    	while (row_counter < numberofQuizzes) {
    	
	    	HBox rowContainer = new HBox();
	    	Label quizGradeLabel = new Label("Quiz Grade");
	    	TextField quizGradeTextfield = new TextField("0.0");
	    	Label quizGraderangeLabel = new Label("Enter Grade from 0-10");
	    	quizGradeTextfields.add(quizGradeTextfield);
	    	
	    	
	    	rowContainer.getChildren().addAll(quizGradeLabel, quizGradeTextfield, quizGraderangeLabel);
	    	row_counter++;
	    	
	    	quizGradeContainer.getChildren().add(rowContainer);
    	}

    	Button doneButton = new Button("Done");
    	doneButton.setOnAction(doneEven -> calculateAverageQuizGrade(mainScene, quizGradeTextfields));
    	quizGradeContainer.getChildren().add(doneButton);
    	
    	Scene quizGradesScene = new Scene(quizGradeContainer);
		applicationStage.setScene(quizGradesScene);
    }
    
    
    
    double averageOptionalQuizGrade = 0.0;
    /**
     * This function will calculate average grade for Optional quiz.
     * @param mainScene: This will return the user to the main scene after entering the individual Optional Quiz Grades.
     * @param optionalquizGradeTextfields: Here the user enter the OPtional Quiz Grades for Calculation.
     */
    void calculateAverageOptionalQuizGrade(Scene mainScene,ArrayList<TextField> optionalquizGradeTextfields) {
    	//applicationStage.setScene(mainScene);
    	quizErrorLabel.setText("");
    	double weightPerQuiz = .1/5.0;
    	averageOptionalQuizGrade = 0.0;
    	boolean validQuizGrade=false;
    	
	    
    	int size=optionalquizGradeTextfields.size();
	    
	    
	    
    	for (TextField optionalquizGradeTextfield : optionalquizGradeTextfields) {
    		
    		Grade optionalQuizGrade = new Grade(0,10,weightPerQuiz);
    		String errorMessage=optionalQuizGrade.setValue(optionalquizGradeTextfield.getText());
    		if(!errorMessage.equals("")) {
    			validQuizGrade=true;
    			quizErrorLabel.setText(errorMessage);
    		}
    		
	    	
	    	if(size<=5) {
	    		averageOptionalQuizGrade=averageOptionalQuizGrade+optionalQuizGrade.getWeightedPercentageGrade();
	    		optQuizGradeAverage.setText(String.format("Quiz Average: %.2f", averageOptionalQuizGrade));
	    	}
	    	
	    	if(size>5) {
	    		double sum = gradeQuizSum(optionalquizGradeTextfields);
	    		averageOptionalQuizGrade=sum; 
	    	}
    	
    	}
    	if(!validQuizGrade) {
    		applicationStage.setScene(mainScene);
    	}
    }
    /**
     * this function creates a double array used to remove the lowest grade when there is more than 5 optional quiz grades.
     * @param optionalquizGradeTextfields: Using the ArrayList of the optional grade text fields to generate a double array but parsing the array list and getting the values  
     * @return array returned is then used in the gradeQuizSum() to remove then add up the remaining quiz grades that are then used for calculating the average quiz grade and weighted grade
     */
    Double[] createGradesArray(ArrayList<TextField> optionalquizGradeTextfields) {		
 		int i=0;	
 		Double[] temp = new Double[optionalquizGradeTextfields.size()];
     	if (optionalquizGradeTextfields.size()>5) {   	
 	    	for(i=0;i<optionalquizGradeTextfields.size();i++) {
 	    		temp[i] = Double.parseDouble(optionalquizGradeTextfields.get(i).getText());
 	    	}
     	}
     	return temp;
 	}
    /**
     * this function will drop the 2 lowest grade when the user had attempted more than 5 optional quiz then returns that value to calculateAverageOptionalQuizGrade() 
     * @param optionalquizGradeTextfields used as a parameter in the createGradesArray to create the array used for the calculation
     * @return returns the sum of all the remaining quiz grades added together and divided by 5.
     */
	private double gradeQuizSum(ArrayList<TextField> optionalquizGradeTextfields) {
		
		double sum =0.0;    	
		int i=0;
		int size = 0;
    	Double[] gradesArray = createGradesArray(optionalquizGradeTextfields);   	    	
	 	
	    		
    	Arrays.sort(gradesArray, Collections.reverseOrder());
    	System.out.println("the length is: "+gradesArray.length);
    	if(gradesArray.length==6) {
    		size=gradesArray.length-1;
    	}
    	if(gradesArray.length==7) {
    		size=(gradesArray.length)-2;
    	}
    	System.out.println("the size is: "+size);
    	for (i=0;i<size;i++) {
    		sum = sum + gradesArray[i]; 
		
			}
    	optQuizGradeAverage.setText(String.format("Quiz Average: %.2f", sum/5.0));
    	sum = (sum/5.0);
    	System.out.println("the sum is:"+sum);
		return sum;
	}

	/**
	 * this is the function is where the user enters the scene to enter the optional quiz grades.
	 * @param optionalQuizGradeEvent: the function is based on the button that says "Enter Quiz Grade" and the linked event opens the scene that has the text fields 
     *and a the amount of quiz text fields are based on the amount selected in the choice box.
	 */
	@FXML
    void getoptionalQuizGrades(ActionEvent optionalQuizGradeEvent) {
    	Scene mainScene = applicationStage.getScene();
    	applicationStage.setTitle("Optional Quiz Calculator");
    	
    	int numberofoptionalQuizzes = optionalquizzesCompletedChoiceBox.getValue();
    	int rows_Created = 0;
    	VBox optionalquizGradeContainer = new VBox();
    	optionalquizGradeContainer.getChildren().add(quizErrorLabel);
    	ArrayList<TextField> optionalquizGradeTextfields = new ArrayList<TextField>();
    	
    	while(rows_Created<numberofoptionalQuizzes) {
	    	HBox rowContainer = new HBox();
	    	Label optionalquizGradeLabel = new Label("Optional Quiz Grade");
	    	TextField optionalquizGradeTextfield = new TextField("0.0");
	    	Label quizGraderangeLabel = new Label("Enter Grade from 0-10");
	    	optionalquizGradeTextfields.add(optionalquizGradeTextfield);
	    	
	    	rowContainer.getChildren().addAll(optionalquizGradeLabel, optionalquizGradeTextfield,quizGraderangeLabel );
	    	rows_Created++;
	    	
	    	optionalquizGradeContainer.getChildren().add(rowContainer);
    	}
    	
    	Button doneButton = new Button("Done");
    	doneButton.setOnAction(doneEvent -> calculateAverageOptionalQuizGrade(mainScene, optionalquizGradeTextfields));
    	optionalquizGradeContainer.getChildren().addAll(doneButton);
    	
    	Scene quizgradeScene = new Scene(optionalquizGradeContainer);
    	applicationStage.setScene(quizgradeScene);
    	
    }
    
    /**
     * this main function will calls all the other functions and calculates the weighted grades of the components 
     * @param event: the event that is called when the user hits the calculate grade button that shows what the properly weighted grade is for the class based on the grades of the different components.
     */
    @FXML
    void calculateGrade(ActionEvent event) {
    	//Clears the error messages
    	projectGradeErrorLabel.setText("");
  	
    	
    	Grade projectGrade = new Grade(0.0,100,.5);
    	projectGradeErrorLabel.setText(projectGrade.setValue(projectGradeTextfield.getText()));
   	
    	Grade requiredQuizGrade = new Grade(averageQuizGrade,10,.1875);
    	
    	Grade optionalQuizGrade = new Grade(averageOptionalQuizGrade,10,.0625);
    	
    	Grade requiredCCGrade = new Grade(requiredCodingChallengeChoiceBox.getValue(),15,.1875);
    	
    	Grade optionalCCGrade = new Grade(optionalCodingChallengeChoiceBox.getValue(),5,.0625);
   	
    	double courseGrade= projectGrade.getWeightedPercentageGrade()+
    			requiredQuizGrade.getWeightedPercentageGrade()+
    			optionalQuizGrade.getWeightedPercentageGrade()+
    			requiredCCGrade.getWeightedPercentageGrade()+
    			optionalCCGrade.getWeightedPercentageGrade();

    	courseGradeLabel.setText(String.format("Your course grade is: %.2f", courseGrade));
    }

}
