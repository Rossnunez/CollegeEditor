package view;


import java.awt.Font;
import java.io.FileNotFoundException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Helper;
import model.Major;
import model.MasterCourseBag;
import model.MiniStudentCourseBag;
import model.Name;
import model.PersonBag;
import model.Student;

public class StudentPane extends ButtonPane{
	
	private MasterCourseBag masterCourseBag;
	private PersonBag personBag;
	private Student student;	
	private String id;	
	//private String name;
	
	private TextField firstNameField;
	private TextField lastNameField;
	private String majorField;
	private TextField phoneNumberField;
	private TextField idField;
	private TextField gpaReturnField;
	private TextField majorReturnField;
	
	private Button editCourseBag;
	private ObservableList<String> list;
	private ComboBox<String> box;
	/* private Button insertBtn;
	private Button searchBtn;
	private Button removeBtn;
	private Button updateBtn; */
	
	private BorderPane root2;
	private VBox root;
	private HBox textFieldBox;
	private HBox buttonBox;
	private HBox titleBox;
		
	public StudentPane(PersonBag personBag, MasterCourseBag masterCourseBag, BorderPane root2) {
		this.personBag = personBag;
		this.masterCourseBag = masterCourseBag;
		this.root2 = root2;
		buildTextFields();
		//Pane buttonBox = new ButtonPane().getButtonBox();
		buildButtons();
		buildHBoxes();
		buildList();
		buildVBox();		
		handleEvent();
		
	}
	
	public void buildVBox() {
		root = new VBox(20);
		root.setAlignment(Pos.CENTER);
		box.setPromptText("MAJOR");
		root.getChildren().addAll(titleBox, textFieldBox, buttonBox, box, ButtonPane.getButtonBox());
	}
	
	public void handleEvent() {
		editCourseBag.setOnAction(e ->{
			id = idField.getText();
			student = (Student) personBag.findByIdStudent(id);
			if(student != null) {
			StudentCourseBagPane studentCourseBagPane = new StudentCourseBagPane(student, masterCourseBag);
	
			root2.setCenter(studentCourseBagPane.getPane());
			root2.setMargin(studentCourseBagPane.getPane(), new Insets(20,20,30,20));
			} else {
				Alerts.getSearchAlert();
			}
		});
		
		box.setOnAction(e ->{
			majorField = (String) box.getValue();
		});
		
		getInsertBtn().setOnAction(e ->{
			 String firstName = firstNameField.getText();
			 String lastName = lastNameField.getText();
			 String phoneNumber = phoneNumberField.getText();
			 if(phoneNumber.length() == 10) {
				 Name name = new Name(firstName, lastName);
				 
				 try {
					Integer.parseInt(phoneNumber);
					student = new Student(name, Helper.findMajor(majorField), phoneNumber, masterCourseBag);
					personBag.insertStudent(student);
					firstNameField.clear();
					lastNameField.clear();
					phoneNumberField.clear();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (NumberFormatException e2) {
					Alerts.getPhonenumberAlert();
				} catch (NullPointerException e3) {
					Alerts.getMajorAlert();
				}
			 } else {
				 Alerts.getPhonenumberAlert();
			 }
			
		});
		
		getSearchBtn().setOnAction(e -> {
			 id = idField.getText();
			 student = (Student) personBag.findByIdStudent(id);
			 if(student != null) {
			
			firstNameField.setText(((Student) student).getName().getFirstName());
			lastNameField.setText(student.getName().getLastName());
			phoneNumberField.setText(student.getPhoneNumber());
			String gpa = "" + student.getGpa();
			gpaReturnField.setText(gpa);
			majorReturnField.setText(student.getMajor().toString());
			 } else {
				 Alerts.getSearchAlert();
			 }
		});
		
		getRemoveBtn().setOnAction(e -> {
			id = idField.getText();
			personBag.removeByIdStudent(id);
			firstNameField.clear();
			lastNameField.clear();
			phoneNumberField.clear();
			idField.clear();
		}); 
		
		getUpdateBtn().setOnAction(e -> {
			id = idField.getText();
			student = (Student) personBag.findByIdStudent(id);
			
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			String phoneNumber = phoneNumberField.getText();
			
			Name name = new Name(firstName, lastName);
			student.setName(name);
			student.setMajor(Helper.findMajor(majorField));
			student.setPhoneNumber(phoneNumber);
			firstNameField.clear();
			lastNameField.clear();
			phoneNumberField.clear();
			idField.clear();
		});
	}

	
	
	public void buildHBoxes() {
		Label myLabel = new Label("Edit Student Info.");

		titleBox = new HBox(50);
		textFieldBox = new HBox(10);
		buttonBox = new HBox(10);
		
		textFieldBox.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);
		titleBox.setAlignment(Pos.TOP_CENTER);
		
		titleBox.getChildren().add(myLabel);
		textFieldBox.getChildren().addAll(firstNameField, lastNameField, phoneNumberField, idField, gpaReturnField, majorReturnField);
		buttonBox.getChildren().addAll(editCourseBag);
	}
	
	public void buildTextFields() {
		firstNameField = new TextField();
		lastNameField = new TextField();
		phoneNumberField = new TextField();
		idField = new TextField();
		gpaReturnField = new TextField();
		majorReturnField = new TextField();
		
		firstNameField.setPromptText("FIRST NAME");
		lastNameField.setPromptText("LAST NAME");
		phoneNumberField.setPromptText("PHONE NUMBER");
		idField.setPromptText("ID");
		gpaReturnField.setPromptText("GPA RESULT");
		gpaReturnField.setDisable(true);
		majorReturnField.setPromptText("MAJOR RESULT");
		majorReturnField.setDisable(true);
		//majorField = new TextField();
		//majorField.setPromptText("MAJOR");
		//idField.setDisable(true);
	}
	
	public void buildList() {
		 list = FXCollections.observableArrayList("CSE", "MAT", "ENG");
		 box = new ComboBox();
		 box.setItems(list);
				 
	}
	
	public void buildButtons() {
		editCourseBag = new Button("EDIT COURSE BAG");

	} 
	
	public Pane getPane() {
		return root;
	}
}
