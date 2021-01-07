package view;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Course;
import model.CourseStatus;
import model.CourseStatus_2;
import model.Faculty;
import model.Helper;
import model.LetterGrade;
import model.MasterCourseBag;
import model.MiniFacultyCourseInfo;
import model.Student;

public class FacultyCourseBagPane {
	private Faculty faculty;	
	private MasterCourseBag masterCourseBag;
	
	
	private TextField courseNumberField;
	private GridPane gridPane;
	
	
	private Button insertBtn;
	private Button removeBtn;
	private ObservableList<String> coursesTaughtList;
	private ObservableList<String> coursesTeachingList;
	
	private ListView<String> coursesTaughtListView;
	private ListView<String> coursesTeachingListView;

	private VBox root;
	private HBox textFieldBox;
	private HBox buttonBox;
	private HBox titleBox;
		
	public FacultyCourseBagPane(Faculty faculty, MasterCourseBag masterCourseBag) {
		this.faculty = faculty;
		this.masterCourseBag = masterCourseBag;
		buildTextFields();
		buildButtons();
		buildHBoxes();
		getStuff();
		buildVBox();
		handleEvent();
		
	}
	

	public void getStuff() {
		gridPane = new GridPane();
		gridPane.setGridLinesVisible(false);
		gridPane.setPadding(new Insets(5));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		
		ColumnConstraints column1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		ColumnConstraints column2 = new ColumnConstraints(50);
		ColumnConstraints column3 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		
		
		column1.setHgrow(Priority.ALWAYS);
		column3.setHgrow(Priority.ALWAYS);
		
		gridPane.getColumnConstraints().addAll(column1, column2, column3);
		
		Label coursesToTakeLbl = new Label("Courses Taught");
		gridPane.setHalignment(coursesToTakeLbl, HPos.CENTER);
		gridPane.add(coursesToTakeLbl, 0, 0);
		
		Label coursesTakingLbl = new Label("Courses Teaching");
		gridPane.setHalignment(coursesTakingLbl, HPos.CENTER);
		gridPane.add(coursesTakingLbl, 2, 0);
		
		
		this.faculty = faculty;
		int max = faculty.getMiniFacultyCourseBag().getnElems();
		String[] courses = new String[max];
		MiniFacultyCourseInfo[] facultyInfo = faculty.getMiniFacultyCourseBag().getMiniFacultyCourseInfo();
		for(int i = 0; i < max; i++){
			courses[i] = facultyInfo[i].getCourseNumber();
		}

		coursesTaughtList = FXCollections.observableArrayList(Arrays.asList(courses));
		coursesTaughtListView = new ListView<>(coursesTaughtList);
		gridPane.add(coursesTaughtListView, 0, 1);
		
		coursesTeachingList = FXCollections.observableArrayList(Arrays.asList());
		coursesTeachingListView = new ListView<>(coursesTeachingList);
		gridPane.add(coursesTeachingListView, 2, 1);
		
		
		Button sendRightBtn = new Button("->");
		Button sendLeftBtn = new Button("<-");
		
		
		sendRightBtn.setOnAction(e ->{
			String potiental = coursesTaughtListView.getSelectionModel().getSelectedItem();
			if(potiental != null) {
				coursesTaughtListView.getSelectionModel().clearSelection();
				coursesTaughtList.remove(potiental);
				coursesTeachingList.add(potiental);
				faculty.getMiniFacultyCourseBag().findById(potiental).setCourseStatus(CourseStatus_2.TAUGHT);
			}
			
		});
		
		sendLeftBtn.setOnAction(e ->{
			String potiental = coursesTeachingListView.getSelectionModel().getSelectedItem();
			
			if(potiental != null) {
				coursesTeachingListView.getSelectionModel().clearSelection();
				coursesTaughtList.add(potiental);
				coursesTeachingList.remove(potiental);
				faculty.getMiniFacultyCourseBag().findById(potiental).setCourseStatus(CourseStatus_2.TEACHING);
			}
			
		});
		VBox vBox = new VBox(5);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(sendRightBtn, sendLeftBtn);
		gridPane.add(vBox, 1, 1);
	}
	
	public void buildVBox() {
		root = new VBox(20);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(gridPane, textFieldBox, buttonBox, titleBox);
	}
	
	public void handleEvent() {
		
		insertBtn.setOnAction(e ->{
			String courseNumber = courseNumberField.getText();
			Course course = masterCourseBag.findById(courseNumber); 
			if(course != null) {
			MiniFacultyCourseInfo miniStudentInfo = new MiniFacultyCourseInfo(course.getCourseNumber(), course.getNumberOfCredits(), CourseStatus_2.TAUGHT);
			if(faculty.getMiniFacultyCourseBag().getnElems() < faculty.getMiniFacultyCourseBag().getSize()) {
			faculty.getMiniFacultyCourseBag().insert(miniStudentInfo);
			}
			courseNumberField.clear();
			coursesTaughtList.add(courseNumber);
			} else {
				Alerts.getCourseSearchAlert();
			}
			
		});
		
		
		removeBtn.setOnAction(e -> {
			
			String potiental = coursesTaughtListView.getSelectionModel().getSelectedItem();
			String potiental2 = coursesTeachingListView.getSelectionModel().getSelectedItem();
			
			if(potiental != null) {
				faculty.getMiniFacultyCourseBag().removeById(potiental);
				coursesTaughtList.remove(potiental);
			} else if(potiental2 != null) {
				faculty.getMiniFacultyCourseBag().removeById(potiental2);
				coursesTeachingList.remove(potiental2);
			}
			
			/*
			String courseNumber = courseNumberField.getText();
			faculty.getMiniFacultyCourseBag().removeById(courseNumber);
			courseNumberField.clear();
			coursesTaughtList.remove(courseNumber); */
		}); 
		
	}

	
	
	public void buildHBoxes() {
		Label myLabel = new Label("Edit Faculty Course Info.");

		titleBox = new HBox(50);
		textFieldBox = new HBox(10);
		buttonBox = new HBox(10);
		
		textFieldBox.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);
		titleBox.setAlignment(Pos.TOP_CENTER);
		
		titleBox.getChildren().add(myLabel);
		textFieldBox.getChildren().addAll(courseNumberField);
		buttonBox.getChildren().addAll(insertBtn, removeBtn);
		
	}
	
	public void buildTextFields() {
		courseNumberField = new TextField();
		courseNumberField.setPromptText("COURSE NUMBER");
	}
	
	
	public void buildButtons() {
		insertBtn = new Button("INSERT");
		removeBtn = new Button("REMOVE");
	}
	
	public Pane getPane() {
		return root;
	}

}
