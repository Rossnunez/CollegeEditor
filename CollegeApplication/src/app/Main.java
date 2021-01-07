package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Classroom;
import model.ClassroomBag;
import model.College;
import model.Course;
import model.Faculty;
import model.Helper;
import model.MasterCourseBag;
import model.MiniFacultyCourseInfo;
import model.MiniStudentCourseBag;
import model.MiniStudentInfo;
import model.Name;
import model.Person;
import model.PersonBag;
import model.Student;
import model.Textbook;
import model.TextbookBag;
import view.Alerts;
import view.ClassroomPane;
import view.CoursePane;
import view.FacultyPane;
import view.StudentPane;
import view.TextbookPane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Main extends Application {
	
	private College college;
	private MasterCourseBag masterCourseBag;
	private TextbookBag textbookBag;
	private PersonBag studentBag;
	private PersonBag facultyBag;
	private ClassroomBag classroomBag;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		studentBag = new PersonBag(1000, 1);
		facultyBag = new PersonBag(1000);
		classroomBag = new ClassroomBag(1000);
		masterCourseBag = new MasterCourseBag(5500);
		textbookBag = new TextbookBag(39000);
		////////////////////////////////////////////
		primaryStage.setTitle("College Editor");
		BorderPane root = new BorderPane();
		
		MenuBar menuBar = new MenuBar();
		Menu fileMenu =  new Menu("File");
		Menu editMenu = new Menu("Edit");
		
		//menu items for File
		MenuItem exitItem = new MenuItem("Exit");
		Menu importMenu = new Menu("Import");
		Menu exportMenu = new Menu("Export");
		MenuItem backupItem = new MenuItem("Backup");
		MenuItem restoreItem = new MenuItem("Restore");
		
		//menu items for Export of File
		MenuItem studentExportItem = new MenuItem("Student");
		MenuItem facultyExportItem = new MenuItem("Faculty");
		MenuItem textbookExportItem = new MenuItem("Textbook");
		MenuItem courseExportItem = new MenuItem("Course");
		MenuItem classroomExportItem = new MenuItem("Classroom");
		exportMenu.getItems().addAll(studentExportItem, 
				facultyExportItem, 
				textbookExportItem, 
				courseExportItem, 
				classroomExportItem);
		
		//menu items for Import of File
		MenuItem studentImportItem = new MenuItem("Student");
		MenuItem facultyImportItem = new MenuItem("Faculty");
		MenuItem textbookImportItem = new MenuItem("Textbook");
		MenuItem courseImportItem = new MenuItem("Course");
		MenuItem classroomImportItem = new MenuItem("Classroom");
		importMenu.getItems().addAll(studentImportItem, 
				facultyImportItem, 
				textbookImportItem, 
				courseImportItem, 
				classroomImportItem);
		
		//Compiling all the File menu items//
		fileMenu.getItems().addAll(importMenu, 
				exportMenu, 
				new SeparatorMenuItem(),
				backupItem,
				restoreItem,
				new SeparatorMenuItem(),
				exitItem);
		
		//menu items for Edit//
		MenuItem studentItem = new MenuItem("Student");
		MenuItem facultyItem = new MenuItem("Faculty");
		MenuItem textbookItem = new MenuItem("Textbook");
		MenuItem courseItem = new MenuItem("Course");
		MenuItem classroomItem = new MenuItem("Classroom");
		
		//Compiling all the Edit menu items//
		editMenu.getItems().addAll(studentItem,
				facultyItem,
				textbookItem,
				courseItem,
				classroomItem);
		
		
		//menu item actions for Import of File// 
		studentImportItem.setOnAction(
			new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                	final FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showOpenDialog(primaryStage);
                    if (file != null) {
                        String studentFile = "" + file;
                        if(masterCourseBag.getnElems() != 0) {
                        	try {
                        		Helper.fillStudentBag(studentBag, masterCourseBag, studentFile);
                        	} catch (FileNotFoundException e1) {
                        		e1.printStackTrace();
                        	}
                        	Alerts.getImportAlert();
                        } else {
                        	Alerts.getStudentImportAlert(); 
                        }
                    }
        
                }
		}); 
		
		facultyImportItem.setOnAction(
			new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                	final FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showOpenDialog(primaryStage);
                    if (file != null) {
                    	String facultyFile = "" + file;
                    	Helper.fillFacultyBag(facultyBag, facultyFile);
                    	Alerts.getImportAlert();
                    } 
                    
                }
		});
		
		textbookImportItem.setOnAction(
			new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                	final FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showOpenDialog(primaryStage);
                    
                    if (file != null) {
                    	String textbookFile = "" + file;
                        try {
            				Helper.fillTextbookBag(textbookBag, textbookFile);
            			} catch (FileNotFoundException e1) {
            				e1.printStackTrace();
            			}
                        Alerts.getImportAlert();
                    }
                 
                }
		});
		
		courseImportItem.setOnAction(
			 new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                	final FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showOpenDialog(primaryStage);
                    
                    if (file != null) {
                    	String courseFile = "" + file;
                    	try {
            				Helper.fillCourseBag(masterCourseBag, courseFile);
            			} catch (FileNotFoundException e1) {
            				e1.printStackTrace();
            			}
                    	Alerts.getImportAlert();
                    }
                } 
		});
		
		classroomImportItem.setOnAction(
			new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                	final FileChooser fileChooser = new FileChooser();
                    File file = fileChooser.showOpenDialog(primaryStage);
                    
                    if (file != null) {
                    	String classroomFile = "" + file;
                    	try {
            				Helper.fillClassroomBag(classroomBag, classroomFile);
            			} catch (FileNotFoundException e1) {
            				e1.printStackTrace();
            			}
                    	Alerts.getImportAlert();
                    }
                }
		});
		
		
		//menu Items for Export of File//
		studentExportItem.setOnAction(e ->{
			PrintWriter pw = null;
			try {
				pw = new PrintWriter("Rawdata/ExportTextFile/Student.txt");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			Person[] persons = studentBag.getStudentArray();
			for(int i = 0; i < studentBag.getnElemS(); i++) {
				
			pw.println("Student Info");	
			String s = ((Student) persons[i]).getName().getFirstName();
			s += "," + ((Student) persons[i]).getName().getLastName();
			s += "," + ((Student) persons[i]).getMajor();
			s += "," + ((Student) persons[i]).getPhoneNumber();
			pw.println(s);
			pw.println("MiniStudentCourseInfo");
			int max = ((Student)persons[i]).getMiniStudentCourseBag().getnElems();
			if(max == 0) {
				pw.println("null");
			} else {
				s = "";
				MiniStudentInfo[] studentInfo = ((Student)persons[i]).getMiniStudentCourseBag().getCourses();
				for(int j = 0; j < max; j++) {
						s += studentInfo[j].getCourseNumber();
						s += "," + studentInfo[j].getNumberOfCredits();
						s += "," + studentInfo[j].getLetterGrade();
						s += "," + studentInfo[j].getCourseStatus();
						if(j + 1 != max) {
							s += ",";
						}
					}
				pw.println(s);
				}
			}
			pw.close();
			Alerts.getExportAlert();
		});
		
		facultyExportItem.setOnAction(e ->{
			PrintWriter pw = null;
			try {
				pw = new PrintWriter("Rawdata/ExportTextFile/Faculty.txt");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			Person[] persons = facultyBag.getFacultyArray();
			for(int i = 0; i < facultyBag.getnElemF(); i++) {
				
			pw.println("Faculty Info");	
			String s = ((Faculty) persons[i]).getName().getFirstName();
			s += "," + ((Faculty) persons[i]).getName().getLastName();
			s += "," + ((Faculty) persons[i]).getTitle();
			s += "," + ((Faculty) persons[i]).getSalary();
			s += "," + ((Faculty) persons[i]).getOfficePhone();
			pw.println(s);
			pw.println("MiniFacultyCourseInfo");
			int max = ((Faculty)persons[i]).getMiniFacultyCourseBag().getnElems();
			if(max == 0) {
				pw.println("null");
			} else {
				s = "";
				MiniFacultyCourseInfo[] facultyInfo = ((Faculty)persons[i]).getMiniFacultyCourseBag().getMiniFacultyCourseInfo();
				for(int j = 0; j < max; j++) {
						s += facultyInfo[j].getCourseNumber();
						s += "," + facultyInfo[j].getNumberOfCredits();
						s += "," + facultyInfo[j].getCourseStatus();
						if(j + 1 != max ) {
							s += ",";
						}
					}
					pw.println(s);
				}	
			}
			pw.close();
			Alerts.getExportAlert();
		});
		
		textbookExportItem.setOnAction(e ->{
			PrintWriter pw = null;
			try {
				pw = new PrintWriter("Rawdata/ExportTextFile/Textbooks.txt");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			Textbook[] textbook = textbookBag.getTextbook();
			for(int i = 0; i < textbookBag.getnElems(); i++) {
			pw.println(textbook[i].getBookTitle());
			pw.println(textbook[i].getIsbn());
			pw.println(textbook[i].getPrice());
			Name[] name = textbook[i].getAuthor();
			String s = "";
			for(int j = 0; j < textbook[i].getAuthor().length; j++) {
				try {	
				s += name[j].getFirstName() + ",";
				s += name[j].getLastName();
				if(j != (textbook[i].getAuthor().length - 1)) {
					s += ",";
				}
				} catch (NullPointerException e1) {
					break;
				}
				
			}
			pw.println(s);
			pw.println(" ");
			
			}
			pw.close();
			Alerts.getExportAlert();
			
		});
		
		courseExportItem.setOnAction(e ->{
			PrintWriter pw = null;
			try {
				pw = new PrintWriter("Rawdata/ExportTextFile/Courses.txt");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			Course[] course = masterCourseBag.getCourseArray();
			for(int i = 0; i < masterCourseBag.getnElems(); i++) {
				
			pw.println(course[i].getCourseNumber());
			pw.println(course[i].getCourseTitle());
			pw.println(course[i].getCourseDescription());
			pw.println(course[i].getNumberOfCredits());
			pw.println(" ");
			}
			pw.close();
			Alerts.getExportAlert();
		});
		
		classroomExportItem.setOnAction(e ->{
			PrintWriter pw = null;
			try {
				pw = new PrintWriter("Rawdata/ExportTextFile/Classrooms.txt");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			Classroom[] classroom = classroomBag.getClassroomArray();
			for(int i = 0; i < classroomBag.getnElems(); i++) {
				
			pw.println(classroom[i].getRoomNumber());
			pw.println(classroom[i].getRoomBuilding());
			pw.println(" ");
			}
			pw.close();
			Alerts.getExportAlert();
		});
		
		
		//Menu Items for File//
		backupItem.setOnAction(e ->{
	
			college = new College(studentBag, facultyBag,
					classroomBag, textbookBag, masterCourseBag);
			try {
			FileOutputStream fos = new FileOutputStream("RawData/Backups/College.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(college);
			oos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
			Alerts.getBackupAlert();
		});
		
		restoreItem.setOnAction(e ->{
			FileInputStream fis;
			try {
				fis = new FileInputStream("RawData/Backups/College.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				college = (College) ois.readObject();
				studentBag = college.getStudentBag();
				studentBag.setStudentArray(college.getStudentArray());
				facultyBag = college.getFacultyBag();
				facultyBag.setFacultyArray(college.getFacultyArray());
				textbookBag = college.getTextbookBag();
				classroomBag = college.getClassroomBag();
				masterCourseBag = college.getMasterCourseBag();
				ois.close();
				fis.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			Alerts.getRestoreAlert();

		});
		
		exitItem.setOnAction(e -> {
			System.exit(0);
		});
		
		//menu items for Edit//
		studentItem.setOnAction(e ->{
			StudentPane studentPane = new StudentPane(studentBag, masterCourseBag,root);

			root.setCenter(studentPane.getPane());
			root.setMargin(studentPane.getPane(), new Insets(20,20,30,20));
			
		});
		
		facultyItem.setOnAction(e ->{
			FacultyPane facultyPane = new FacultyPane(facultyBag, masterCourseBag, root);

			root.setCenter(facultyPane.getPane());
			root.setMargin(facultyPane.getPane(), new Insets(20,20,30,20));
		});
		
		courseItem.setOnAction(e ->{
			CoursePane coursePane = new CoursePane(masterCourseBag);
			
			root.setCenter(coursePane.getPane());
			root.setMargin(coursePane.getPane(), new Insets(20,20,30,20));
		});
		
		classroomItem.setOnAction(e ->{
			ClassroomPane classroomPane = new ClassroomPane(classroomBag);
			
			root.setCenter(classroomPane.getPane());
			root.setMargin(classroomPane.getPane(), new Insets(20,20,30,20));
		});
		
		textbookItem.setOnAction(e ->{
			TextbookPane textbookPane = new TextbookPane(textbookBag);
			
			root.setCenter(textbookPane.getPane());
			root.setMargin(textbookPane.getPane(), new Insets(20,20,30,20));
		});
		
		//Creating an image and title
	    Image image = new Image("/app/sharks.png");  
	    ImageView imageView = new ImageView(image); 
	    imageView.setX(50); 
	    imageView.setY(25); 
	    imageView.setFitHeight(400); 
	    imageView.setFitWidth(500); 
	    imageView.setPreserveRatio(true);   
	      
	      
	    Label label = new Label("College Editor", imageView);
	    label.setFont(new Font("Impact", 32));
	    label.setTextFill(Color.web("#3300FF"));
	    label.setAlignment(Pos.CENTER);
	    label.setContentDisplay(ContentDisplay.BOTTOM);
		
		menuBar.getMenus().addAll(fileMenu, editMenu);
		root.setTop(menuBar);
		root.setCenter(label);
 
		
		Scene scene = new Scene(root, 800, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
	
}