package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {
	
	public static void getImportAlert() {
	Alert importAlert = new Alert(AlertType.INFORMATION);
	importAlert.setTitle("Information Alert");
	importAlert.setHeaderText("The Bag has been filled");
	importAlert.setContentText("INFORMATION ALERT");
	importAlert.showAndWait();
	}
	
	public static void getExportAlert() {
	Alert exportAlert = new Alert(AlertType.INFORMATION);
	exportAlert.setTitle("Information Alert");
	exportAlert.setHeaderText("The Bag has been exported");
	exportAlert.setContentText("INFORMATION ALERT");
	exportAlert.showAndWait();
	}
	
	public static void getBackupAlert() {
	Alert backupAlert = new Alert(AlertType.INFORMATION);
	backupAlert.setTitle("Information Alert");
	backupAlert.setHeaderText("The college has been saved.");
	backupAlert.setContentText("INFORMATION ALERT");
	backupAlert.showAndWait();
	}
	
	public static void getRestoreAlert() {
	Alert restoreAlert = new Alert(AlertType.INFORMATION);
	restoreAlert.setTitle("Information Alert");
	restoreAlert.setHeaderText("The college has been restored.");
	restoreAlert.setContentText("INFORMATION ALERT");
	restoreAlert.showAndWait();
	}
	
	public static void getStudentCBAlert() {
		Alert studentCBAlert = new Alert(AlertType.INFORMATION);
		studentCBAlert.setTitle("Information Alert");
		studentCBAlert.setHeaderText("Make sure you select a grade before inserting a course in the TAKEN section.");
		studentCBAlert.setContentText("To remove, select the course then press REMOVE");
		studentCBAlert.showAndWait();
	}
	
	public static void getPhonenumberAlert() {
		Alert phonenumberAlert = new Alert(AlertType.ERROR);
		phonenumberAlert.setTitle("Warning Alert");
		phonenumberAlert.setHeaderText("PhoneNumber has to be 10 digits long");
		phonenumberAlert.setContentText("No characters allowed");
		phonenumberAlert.showAndWait();
	}
	
	public static void getstudentCBAlert2() {
		Alert studentCBAlert2 = new Alert(AlertType.ERROR);
		studentCBAlert2.setTitle("Warning Alert");
		studentCBAlert2.setHeaderText("Course not Found for Major");
		studentCBAlert2.setContentText("Make sure courses are created or imported first");
		studentCBAlert2.showAndWait();
	}
	
	public static void getStudentImportAlert() {
	Alert studentImportAlert = new Alert(AlertType.ERROR);
	studentImportAlert.setTitle("Warning Alert");
	studentImportAlert.setHeaderText("Courses must be imported before Students");
	studentImportAlert.setContentText("WARNING ALERT");
	studentImportAlert.showAndWait();
	}
	
	public static void getSearchAlert() {
		Alert searchAlert = new Alert(AlertType.ERROR);
		searchAlert.setTitle("Warning Alert");
		searchAlert.setHeaderText("ID not found");
		searchAlert.setContentText("You must enter the correct ID");
		searchAlert.showAndWait();
	}
	
	public static void getTextbookSearchAlert() {
		Alert textbookSearchAlert = new Alert(AlertType.ERROR);
		textbookSearchAlert.setTitle("Warning Alert");
		textbookSearchAlert.setHeaderText("ISBN not found");
		textbookSearchAlert.setContentText("WARNING ALERT");
		textbookSearchAlert.showAndWait();
	}
	
	public static void getCourseSearchAlert() {
		Alert courseSearchAlert = new Alert(AlertType.ERROR);
		courseSearchAlert.setTitle("Warning Alert");
		courseSearchAlert.setHeaderText("Course Number not found");
		courseSearchAlert.setContentText("WARNING ALERT");
		courseSearchAlert.showAndWait();
	}
	
	public static void getClassroomSearchAlert() {
		Alert classroomSearchAlert = new Alert(AlertType.ERROR);
		classroomSearchAlert.setTitle("Warning Alert");
		classroomSearchAlert.setHeaderText("Classroom not found");
		classroomSearchAlert.setContentText("WARNING ALERT");
		classroomSearchAlert.showAndWait();
	}
	
	public static void getMajorAlert() {
		Alert majorAlert = new Alert(AlertType.ERROR);
		majorAlert.setTitle("Warning Alert");
		majorAlert.setHeaderText("Major not found");
		majorAlert.setContentText("WARNING ALERT");
		majorAlert.showAndWait();
	}
	
	public static void getSalaryAlert() {
		Alert majorAlert = new Alert(AlertType.ERROR);
		majorAlert.setTitle("Warning Alert");
		majorAlert.setHeaderText("Enter digits for salary");
		majorAlert.setContentText("WARNING ALERT");
		majorAlert.showAndWait();
	}
	
	public static void getTitleAlert() {
		Alert majorAlert = new Alert(AlertType.ERROR);
		majorAlert.setTitle("Warning Alert");
		majorAlert.setHeaderText("Title not found");
		majorAlert.setContentText("WARNING ALERT");
		majorAlert.showAndWait();
	}
	
	public static void getBuildingNameAlert() {
		Alert buildingNameAlert = new Alert(AlertType.ERROR);
		buildingNameAlert.setTitle("Warning Alert");
		buildingNameAlert.setHeaderText("Building Name not found");
		buildingNameAlert.setContentText("WARNING ALERT");
		buildingNameAlert.showAndWait();
	}
	
	public static void getPriceAlert() {
		Alert buildingNameAlert = new Alert(AlertType.ERROR);
		buildingNameAlert.setTitle("Warning Alert");
		buildingNameAlert.setHeaderText("Enter digits for price");
		buildingNameAlert.setContentText("WARNING ALERT");
		buildingNameAlert.showAndWait();
	}

	public static void getCreditsAlert() {
		Alert creditsAlert = new Alert(AlertType.ERROR);
		creditsAlert.setTitle("Warning Alert");
		creditsAlert.setHeaderText("Enter digits for credits");
		creditsAlert.setContentText("WARNING ALERT");
		creditsAlert.showAndWait();
		
	}
	
}
