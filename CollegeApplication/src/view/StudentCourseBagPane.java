package view;

import java.io.FileNotFoundException;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import model.Helper;
import model.LetterGrade;
import model.MasterCourseBag;
import model.MiniStudentCourseBag;
import model.MiniStudentInfo;
import model.Name;
import model.PersonBag;
import model.Student;

public class StudentCourseBagPane extends ButtonPane {
	
	private Student student;	
	private MasterCourseBag masterCourseBag;
	private LetterGrade letterGrade;
	
	private TextField courseNumberField;
	private GridPane gridPane;
	
	private ObservableList<String> list;
	private ComboBox<String> box;
	
	private Button insertBtn;
	private Button removeBtn;
	private ObservableList<String> coursesToTakeList;
	private ObservableList<String> coursesTakingList;
	private ObservableList<String> coursesTakenList;
	
	private ListView<String> coursesTakingListView;
	ListView<String> coursesToTakeListView;
	ListView<String> coursesTakenListView;

	private VBox root;
	private HBox textFieldBox;
	private HBox buttonBox;
	private HBox titleBox;
		
	public StudentCourseBagPane(Student student, MasterCourseBag masterCourseBag) {
		this.student = student;
		this.masterCourseBag = masterCourseBag;
		buildTextFields();
		buildButtons();
		buildHBoxes();
		getStuff();
		buildList();
		buildVBox();
		handleEvent();
		Alerts.getStudentCBAlert();
		
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
		ColumnConstraints column4 = new ColumnConstraints(50);
		ColumnConstraints column5 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		
		column1.setHgrow(Priority.ALWAYS);
		column3.setHgrow(Priority.ALWAYS);
		column5.setHgrow(Priority.ALWAYS);
		
		gridPane.getColumnConstraints().addAll(column1, column2, column3, column4, column5);
		
		Label coursesToTakeLbl = new Label("Courses to Take");
		gridPane.setHalignment(coursesToTakeLbl, HPos.CENTER);
		gridPane.add(coursesToTakeLbl, 0, 0);
		
		Label coursesTakingLbl = new Label("Courses Taking");
		gridPane.setHalignment(coursesTakingLbl, HPos.CENTER);
		gridPane.add(coursesTakingLbl, 2, 0);
		
		Label coursesTookLbl = new Label("Courses Taken");
		gridPane.setHalignment(coursesTookLbl, HPos.CENTER);
		gridPane.add(coursesTookLbl, 4, 0);
		this.student = student;
		int x = student.getMiniStudentCourseBag().getnElems();
		String[] courses = new String[x];
		MiniStudentInfo[] z = student.getMiniStudentCourseBag().getMiniStudentInfo();
		for(int i = 0; i < x; i++){
			courses[i] = z[i].getCourseNumber();
		}
		
		coursesToTakeList = FXCollections.observableArrayList(Arrays.asList(courses));
		coursesToTakeListView = new ListView<>(coursesToTakeList);
		gridPane.add(coursesToTakeListView, 0, 1);
		
		coursesTakingList = FXCollections.observableArrayList(Arrays.asList());
		coursesTakingListView = new ListView<>(coursesTakingList);
		gridPane.add(coursesTakingListView, 2, 1);
		
		coursesTakenList = FXCollections.observableArrayList(Arrays.asList());
		coursesTakenListView = new ListView<>(coursesTakenList);
		gridPane.add(coursesTakenListView, 4, 1);
		
		Button sendRightBtn = new Button("->");
		Button sendLeftBtn = new Button("<-");
		
		Button sendR = new Button("->");
		Button sendL = new Button("<-");
		
		sendR.setOnAction(e ->{
			String potiental = coursesTakingListView.getSelectionModel().getSelectedItem();
			if(potiental != null) {
				coursesTakingListView.getSelectionModel().clearSelection();
				coursesTakingList.remove(potiental);
				coursesTakenList.add(potiental);
				student.getMiniStudentCourseBag().findById(potiental).setLetterGrade(letterGrade);
				student.getMiniStudentCourseBag().findById(potiental).setCourseStatus(CourseStatus.TAKEN);
				
			}
		});
		
		sendL.setOnAction(e ->{
			String potiental = coursesTakenListView.getSelectionModel().getSelectedItem();
			if(potiental != null) {
				coursesTakenListView.getSelectionModel().clearSelection();
				coursesTakenList.remove(potiental);
				coursesTakingList.add(potiental);
				student.getMiniStudentCourseBag().findById(potiental).setLetterGrade(LetterGrade.NO_GRADE);
				student.getMiniStudentCourseBag().findById(potiental).setCourseStatus(CourseStatus.TAKING);
			}
		});
		
		sendRightBtn.setOnAction(e ->{
			String potiental = coursesToTakeListView.getSelectionModel().getSelectedItem();
			if(potiental != null) {
				coursesToTakeListView.getSelectionModel().clearSelection();
				coursesToTakeList.remove(potiental);
				coursesTakingList.add(potiental);
				student.getMiniStudentCourseBag().findById(potiental).setCourseStatus(CourseStatus.TAKING);
			}
			
		});
		
		sendLeftBtn.setOnAction(e ->{
			String potiental = coursesTakingListView.getSelectionModel().getSelectedItem();
			
			if(potiental != null) {
				coursesTakingListView.getSelectionModel().clearSelection();
				coursesToTakeList.add(potiental);
				coursesTakingList.remove(potiental);
				student.getMiniStudentCourseBag().findById(potiental).setCourseStatus(CourseStatus.TO_TAKE);
			}
			
		});
		VBox vBox2 = new VBox(5);
		VBox vBox = new VBox(5);
		vBox.setAlignment(Pos.CENTER);
		vBox2.setAlignment(Pos.CENTER_RIGHT);
		vBox.getChildren().addAll(sendRightBtn, sendLeftBtn);
		vBox2.getChildren().addAll(sendR, sendL);
		gridPane.add(vBox, 1, 1);
		gridPane.add(vBox2, 3, 1);
	}
	
	public void buildVBox() {
		root = new VBox(20);
		root.setAlignment(Pos.CENTER);
		box.setPromptText("GRADE");
		root.getChildren().addAll(gridPane,box, textFieldBox, buttonBox, titleBox);
	}
	
	public void handleEvent() {
		box.setOnAction(e ->{
			String grade = (String) box.getValue();
			letterGrade = Helper.findLetterGrade(grade);
		});
		
		insertBtn.setOnAction(e ->{
			String courseNumber = courseNumberField.getText();
			Course course = masterCourseBag.findById(courseNumber); 
			if(course != null) {
			MiniStudentInfo miniStudentInfo = new MiniStudentInfo(course.getCourseNumber(), course.getNumberOfCredits());
			if(student.getMiniStudentCourseBag().getnElems() < student.getMiniStudentCourseBag().getSize()) {
			student.getMiniStudentCourseBag().insert(miniStudentInfo);
			}
			courseNumberField.clear();
			coursesToTakeList.add(courseNumber);
			} else {
				Alerts.getCourseSearchAlert();
			}
			
		});
		
		
		removeBtn.setOnAction(e -> {
			
			String potiental = coursesTakingListView.getSelectionModel().getSelectedItem();
			String potiental2 = coursesToTakeListView.getSelectionModel().getSelectedItem();
			String potiental3 = coursesTakenListView.getSelectionModel().getSelectedItem();
			
			if(potiental != null) {
				student.getMiniStudentCourseBag().removeById(potiental);
				coursesTakingList.remove(potiental);
			} else if(potiental2 != null) {
				student.getMiniStudentCourseBag().removeById(potiental2);
				coursesToTakeList.remove(potiental2);
			} else if(potiental3 != null){
				student.getMiniStudentCourseBag().removeById(potiental3);
				coursesTakenList.remove(potiental3);
			}
			
		}); 
		
	}

	
	
	public void buildHBoxes() {
		Label myLabel = new Label("Edit Student Course Info.");

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
	
	public void buildList() {
		 list = FXCollections.observableArrayList("A", "B+", "B", "C+", "C", "D+", "D", "F", "NO GRADE");
		 box = new ComboBox();
		 box.setItems(list);
				 
	}
	
	public void buildButtons() {
		insertBtn = new Button("INSERT");
		removeBtn = new Button("REMOVE");
	}
	
	public Pane getPane() {
		return root;
	}
}
