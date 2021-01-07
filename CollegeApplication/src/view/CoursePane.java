package view;

import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Course;
import model.Helper;
import model.MasterCourseBag;
import model.Name;
import model.Course;

public class CoursePane extends ButtonPane{
	private MasterCourseBag courseBag;
	private Course course;		
	
	private TextField courseNumberField;
	private TextField courseTitleField;
	private TextField courseDescriptionField;
	private TextField numberOfCreditsField;

	
	
	private VBox root;
	private HBox textFieldBox;
	private HBox buttonBox;
	private HBox titleBox;
		
	public CoursePane(MasterCourseBag courseBag) {
		this.courseBag = courseBag;
		buildTextFields();
		buildHBoxes();
		buildVBox();		
		handleEvent();
		
	}
	
	public void buildVBox() {
		root = new VBox(20);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(titleBox, textFieldBox, buttonBox, ButtonPane.getButtonBox());
	}
	
	public void handleEvent() {
		getInsertBtn().setOnAction(e ->{
			 String courseNumber = courseNumberField.getText();
			 String courseTitle = courseTitleField.getText();
			 String courseDescription = courseDescriptionField.getText();
			 
			 try {
			 double numberOfCredits = Double.parseDouble(numberOfCreditsField.getText());
			course = new Course(courseNumber, courseTitle, courseDescription, numberOfCredits);
			courseBag.insert(course);
			
			courseNumberField.clear();
			courseTitleField.clear();
			courseDescriptionField.clear();
			numberOfCreditsField.clear();
			 } catch(NumberFormatException e2) {
				 Alerts.getCreditsAlert();
			 }
		});
		
		getSearchBtn().setOnAction(e -> {
			String courseNumber = courseNumberField.getText();
			course = courseBag.findById(courseNumber);
			if(course != null) {
			courseTitleField.setText(course.getCourseTitle());
			courseDescriptionField.setText(course.getCourseDescription());
			String credits = "" + course.getNumberOfCredits();
			numberOfCreditsField.setText(credits);
			} else {
				Alerts.getCourseSearchAlert();
			}
		});
		
		getRemoveBtn().setOnAction(e -> {
			String courseNumber = courseNumberField.getText();
			courseBag.removeById(courseNumber);
			courseNumberField.clear();
			courseTitleField.clear();
			courseDescriptionField.clear();
			numberOfCreditsField.clear();
		}); 
		
		getUpdateBtn().setOnAction(e -> {
			String courseNumber = courseNumberField.getText();
			course = (Course) courseBag.findById(courseNumber);
			
			//String courseNumber = courseNumberField.getText();
			String courseTitle = courseTitleField.getText();
			double numberOfCredits = Double.parseDouble(numberOfCreditsField.getText());
			String courseDescription = courseDescriptionField.getText();

			course.setCourseTitle(courseTitle);
			course.setNumberOfCredits(numberOfCredits);
			course.setCourseDescription(courseDescription);
			courseNumberField.clear();
			courseTitleField.clear();
			courseDescriptionField.clear();
			numberOfCreditsField.clear();
		});
	}

	
	
	public void buildHBoxes() {
		Label myLabel = new Label("Edit Course Info.");

		titleBox = new HBox(50);
		textFieldBox = new HBox(10);
		buttonBox = new HBox(10);
		
		textFieldBox.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);
		titleBox.setAlignment(Pos.TOP_CENTER);
		
		titleBox.getChildren().add(myLabel);
		textFieldBox.getChildren().addAll(courseNumberField, courseTitleField, courseDescriptionField, numberOfCreditsField);

	}
	
	public void buildTextFields() {
		courseNumberField = new TextField();
		courseTitleField = new TextField();
		courseDescriptionField = new TextField();
		courseNumberField.setPromptText("COURSE NUMBER");
		courseTitleField.setPromptText("COURSE TITLE");
		courseDescriptionField.setPromptText("COURSE DESCRIPTION");
		numberOfCreditsField = new TextField();
		numberOfCreditsField.setPromptText("NUMBER OF CREDITS");
		
	}
	

	public Pane getPane() {
		return root;
	}
}
