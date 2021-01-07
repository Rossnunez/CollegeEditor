package view;

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
import model.Faculty;
import model.Helper;
import model.MasterCourseBag;
import model.MiniFacultyCourseBag;
import model.Name;
import model.PersonBag;
import model.Student;

public class FacultyPane extends ButtonPane{
	private PersonBag personBag;
	private Faculty faculty;
	private MasterCourseBag masterCourseBag;
	private String id;	

	
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField salaryField;
	private TextField phonenumberField;
	private TextField idField;
	private String titleField;
	private TextField titleReturnField;
	
	private BorderPane root2;
	private Button editCourseBag;
	private VBox root;
	private HBox textFieldBox;
	private HBox titleBox;
	private ObservableList<String> list;
	private ComboBox<String> box;
	private HBox buttonBox;
		
	public FacultyPane(PersonBag personBag, MasterCourseBag masterCourseBag, BorderPane root2) {
		this.root2 = root2;
		this.masterCourseBag = masterCourseBag;
		this.personBag = personBag;
		buildTextFields();
		buildEditButton();
		buildHBoxes();
		buildList();
		buildVBox();
		handleEvent();
		
	}
	
	public void buildVBox() {
		root = new VBox(20);
		root.setAlignment(Pos.CENTER);
		//buttonBox
		box.setPromptText("TITLE");
		root.getChildren().addAll(titleBox, textFieldBox,box,ButtonPane.getButtonBox(), buttonBox);
		//root.getChildren().add(box);
	}
	
	public void handleEvent() {
		editCourseBag.setOnAction(e ->{
			id = idField.getText();
			faculty = (Faculty) personBag.findByIdFaculty(id);
			if(faculty != null) {
			FacultyCourseBagPane facultyCourseBagPane = new FacultyCourseBagPane(faculty, masterCourseBag);
	
			root2.setCenter(facultyCourseBagPane.getPane());
			root2.setMargin(facultyCourseBagPane.getPane(), new Insets(20,20,30,20));
			} else {
				Alerts.getSearchAlert();
			}
		});
		
		box.setOnAction(e ->{
			titleField = (String) box.getValue();
		});
		
		getInsertBtn().setOnAction(e ->{
			 String firstName = firstNameField.getText();
			 String lastName = lastNameField.getText();
			 double salary = 0;
			 String phonenumber = phonenumberField.getText();
			 if(phonenumber.length() == 10) {
				 try {
					 salary = Double.parseDouble(salaryField.getText());
					 Integer.parseInt(phonenumber);
					 MiniFacultyCourseBag filler = new MiniFacultyCourseBag(30);
				 
					 Name name = new Name(firstName, lastName);
					 faculty = new Faculty(name, Helper.findTitle(titleField), salary, phonenumber, filler);
					 personBag.insertFaculty(faculty);
					 firstNameField.clear();
					 lastNameField.clear();
					 salaryField.clear();
					 phonenumberField.clear();
				
				 } catch (NumberFormatException e1) {
					 Alerts.getSalaryAlert();
					 Alerts.getPhonenumberAlert();
				 } catch (NullPointerException e3) {
					 Alerts.getTitleAlert();
				 }
			 } else {
				 Alerts.getPhonenumberAlert();
			 }
			 
			
		});
		
		getSearchBtn().setOnAction(e -> {
			id = idField.getText();
			faculty = (Faculty) personBag.findByIdFaculty(id);
			if(faculty != null) {
			firstNameField.setText(((Faculty) faculty).getName().getFirstName());
			lastNameField.setText(faculty.getName().getLastName());
			titleReturnField.setText(faculty.getTitle().toString());
			
			String salary = "" + faculty.getSalary();
			salaryField.setText(salary);
			phonenumberField.setText(faculty.getOfficePhone());
			} else {
				Alerts.getSearchAlert();
			}
		});
		
		getRemoveBtn().setOnAction(e -> {
			id = idField.getText();
			personBag.removeByIdFaculty(id);
			firstNameField.clear();
			lastNameField.clear();
			idField.clear();
			salaryField.clear();
			titleReturnField.clear();
			phonenumberField.clear();
		}); 
		
		getUpdateBtn().setOnAction(e -> {
			id = idField.getText();
			faculty = (Faculty) personBag.findByIdFaculty(id);
			
			String firstName = firstNameField.getText();
			String lastName = lastNameField.getText();
			double salary = Double.parseDouble(salaryField.getText());
			String phonenumber = phonenumberField.getText();
			
			Name name = new Name(firstName, lastName);
			faculty.setName(name);
			faculty.setSalary(salary);
			faculty.setTitle(Helper.findTitle(titleField));
			faculty.setOfficePhone(phonenumber);
			firstNameField.clear();
			lastNameField.clear();
			idField.clear();
			titleReturnField.clear();
			salaryField.clear();
			phonenumberField.clear();
		});
	}

	
	
	public void buildHBoxes() {
		Label myLabel = new Label("Edit Faculty Info.");

		titleBox = new HBox(50);
		textFieldBox = new HBox(10);
		buttonBox = new HBox(10);

		textFieldBox.setAlignment(Pos.CENTER);
		titleBox.setAlignment(Pos.TOP_CENTER);
		buttonBox.setAlignment(Pos.CENTER);
		
		titleBox.getChildren().add(myLabel);
		textFieldBox.getChildren().addAll(firstNameField, lastNameField, idField, salaryField, phonenumberField, titleReturnField);
		buttonBox.getChildren().addAll(editCourseBag);
	}
	
	public void buildTextFields() {
		firstNameField = new TextField();
		lastNameField = new TextField();
		idField = new TextField();
		salaryField = new TextField();
		phonenumberField = new TextField();
		titleReturnField = new TextField();
		
		
		phonenumberField.setPromptText("PHONENUMBER");
		salaryField.setPromptText("SALARY");
		firstNameField.setPromptText("FIRST NAME");
		lastNameField.setPromptText("LAST NAME");
		idField.setPromptText("ID");
		titleReturnField.setPromptText("TITLE RESULT");
		titleReturnField.setDisable(true);
	}
	
	public void buildList() {
		 list = FXCollections.observableArrayList("PROF", "ASSOCIATE_PROF", "ASSISTANT_PROF", "INSTRUCTOR");
		 box = new ComboBox();
		 box.setItems(list);
				 
	}
	
	public void buildEditButton() {
		editCourseBag = new Button("EDIT COURSE INFO");
	}
	
	public Pane getPane() {
		return root;
	}
}
