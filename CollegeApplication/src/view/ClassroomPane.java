package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Classroom;
import model.ClassroomBag;
import model.Course;
import model.Classroom;
import model.Helper;
import model.MasterCourseBag;
import model.Name;
import model.ClassroomBag;

public class ClassroomPane extends ButtonPane{
	private ClassroomBag classroomBag;
	private Classroom classroom;	

	
	private TextField roomNumberField;
	private String buildingNameField;
	private TextField buildingNameReturnField;
	
	private VBox root;
	private HBox textFieldBox;
	private HBox roomNumberBox;
	private ObservableList<String> list;
	private ComboBox<String> box;
		
	public ClassroomPane(ClassroomBag classroomBag) {
		this.classroomBag = classroomBag;
		buildTextFields();
		buildHBoxes();
		buildList();
		buildVBox();
		handleEvent();
		
	}
	
	public void buildVBox() {
		root = new VBox(20);
		root.setAlignment(Pos.CENTER);
		box.setPromptText("BUILDING NAME");
		root.getChildren().addAll(roomNumberBox, textFieldBox,box,ButtonPane.getButtonBox());
		
	}
	
	public void handleEvent() {
		
		box.setOnAction(e ->{
			buildingNameField = (String) box.getValue();
		});
		
		getInsertBtn().setOnAction(e ->{
			 String roomNumber = roomNumberField.getText();
			 try {
			 classroom = new Classroom(roomNumber, Helper.findBuildingName(buildingNameField));
			 classroomBag.insert(classroom);
			 roomNumberField.clear();
			 } catch (NullPointerException e1) {
				 Alerts.getBuildingNameAlert();
			 }
		});
		
		getSearchBtn().setOnAction(e -> {
			String roomNumber = roomNumberField.getText();
			classroom = (Classroom) classroomBag.findById(roomNumber);
			if(classroom != null) {
			buildingNameReturnField.setText(classroom.getRoomBuilding().toString());
			} else {
				Alerts.getClassroomSearchAlert();
			}

		});
		
		getRemoveBtn().setOnAction(e -> {
			String roomNumber = roomNumberField.getText();
			classroomBag.removeById(roomNumber);
			roomNumberField.clear();
			buildingNameReturnField.clear();

		}); 
		
		getUpdateBtn().setOnAction(e -> {
			String roomNumber = roomNumberField.getText();
			classroom = (Classroom) classroomBag.findById(roomNumber);
			
	
			classroom.setRoomBuilding(Helper.findBuildingName(buildingNameField));

			roomNumberField.clear();
			buildingNameReturnField.clear();
		});
	}

	
	
	public void buildHBoxes() {
		Label myLabel = new Label("Edit Classroom Info.");

		roomNumberBox = new HBox(50);
		textFieldBox = new HBox(10);
		

		textFieldBox.setAlignment(Pos.CENTER);
		roomNumberBox.setAlignment(Pos.TOP_CENTER);
		
		roomNumberBox.getChildren().add(myLabel);
		textFieldBox.getChildren().addAll(roomNumberField,buildingNameReturnField);
	}
	
	public void buildTextFields() {
		roomNumberField = new TextField();
		buildingNameReturnField = new TextField();
		
		

		roomNumberField.setPromptText("ROOM NUMBER");
		buildingNameReturnField.setPromptText("BUILDING NAME RESULT");
		buildingNameReturnField.setDisable(true);
	}
	
	public void buildList() {
		 list = FXCollections.observableArrayList("Riverhead", "Islip", "Smithtown", "NFL");
		 box = new ComboBox();
		 box.setItems(list);
				 
	}
	
	public Pane getPane() {
		return root;
	}
}
