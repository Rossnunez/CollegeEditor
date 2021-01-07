package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Helper implements Serializable{

	
	//the import of student, faculty, classroom, and textbooks//
	public static void fillFacultyBag(PersonBag theBag, String file) {
		File coursefile = new File(file);
		Scanner scanner = null;
		try {
			scanner = new Scanner(coursefile, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String[] tokens = null;
		String[] tokens2 = null;
		MiniFacultyCourseBag miniFCB = null;
		Name name = null;
		double salary = 0;
		while(scanner.hasNextLine()) {

			String temp = scanner.nextLine();
			if(temp.contentEquals("Faculty Info")) {
				String str = scanner.nextLine();
				tokens = str.split(",");
				name = new Name(tokens[0], tokens[1]);
				salary = Double.parseDouble(tokens[3]);
			}
			temp = scanner.nextLine();
			if(temp.contentEquals("MiniFacultyCourseInfo")) {
				String str2 = scanner.nextLine();
				if(str2.contentEquals("null")) {
					miniFCB = new MiniFacultyCourseBag(30);
					Faculty faculty = new Faculty(name, Helper.findTitle(tokens[2]), salary, tokens[4],miniFCB);
					theBag.insertFaculty(faculty);
				} else {
					tokens2 = str2.split(",");
					//max = tokens2.length / 3;
					miniFCB = new MiniFacultyCourseBag(30);
					for(int i = 0; i < tokens2.length; i+=3) {
						String courseNumber = tokens2[i];
						double numberOfCredits = Double.parseDouble(tokens2[i + 1]);
						String courseStatus = tokens2[i + 2];
						
						MiniFacultyCourseInfo info = new MiniFacultyCourseInfo(courseNumber, numberOfCredits,
								 Helper.findCourseStatus_2(courseStatus));
						miniFCB.insert(info);
						
					}
					Faculty faculty = new Faculty(name, Helper.findTitle(tokens[2]), salary, tokens[4],miniFCB);
					theBag.insertFaculty(faculty);
				}
			}
		}
		scanner.close();
	}
	
	
	public static void fillStudentBag(PersonBag theBag, MasterCourseBag masterCourseBag, String file) throws FileNotFoundException {
		File coursefile = new File(file);
		Scanner scanner = new Scanner(coursefile, "UTF-8");
		//Scanner scanner = new Scanner(coursefile);
		String[] tokens = null;
		String[] tokens2 = null;
		MiniStudentCourseBag miniSCB = null;
		Name name = null;
		while(scanner.hasNextLine()) {

			String temp = scanner.nextLine();
			if(temp.contentEquals("Student Info")) {
				String str = scanner.nextLine();
				tokens = str.split(",");
				name = new Name(tokens[0], tokens[1]);
			}
			temp = scanner.nextLine();
			if(temp.contentEquals("MiniStudentCourseInfo")) {
				String str2 = scanner.nextLine();
				if(str2.contentEquals("null")) {
					Student student = new Student(name, Helper.findMajor(tokens[2]), tokens[3], masterCourseBag);
					theBag.insertStudent(student);
				} else {
					tokens2 = str2.split(",");
					miniSCB = new MiniStudentCourseBag(30);
					for(int i = 0; i < tokens2.length; i+=4) {
						String courseNumber = tokens2[i];
						double numberOfCredits = Double.parseDouble(tokens2[i + 1]);
						String grade = tokens2[i + 2];
						String courseStatus = tokens2[i + 3];
						MiniStudentInfo info = new MiniStudentInfo(courseNumber, numberOfCredits,
								findLetterGrade(grade), Helper.findCourseStatus(courseStatus));
						miniSCB.insert(info);
					}
					Student student = new Student(name, Helper.findMajor(tokens[2]), tokens[3], miniSCB);
					theBag.insertStudent(student);
				}
			}
		}
		scanner.close();
	}
	
	public static void fillClassroomBag(ClassroomBag theBag, String file) throws FileNotFoundException {
		File coursefile = new File(file);
		Scanner scanner = new Scanner(coursefile, "UTF-8");
		
		while(scanner.hasNextLine()) {
			String roomNumber = scanner.nextLine();
			String buildingName = scanner.nextLine();
			String space = scanner.nextLine();
			
			Classroom classroom = new Classroom(roomNumber, Helper.findBuildingName(buildingName));
			theBag.insert(classroom);
		}
		scanner.close();
	}
	
	public static void fillCourseBag(MasterCourseBag theBag, String file) throws FileNotFoundException {
		
		File coursefile = new File(file);
		Scanner scanner = new Scanner(coursefile, "UTF-8");
		
		while(scanner.hasNextLine()) {
			String courseNumber = scanner.nextLine();
			String title = scanner.nextLine();
			String description = scanner.nextLine();
			String credit = scanner.nextLine();
			System.out.println(credit);
			double credits = 0;
			try {
			 credits = Double.parseDouble(credit);
			}catch(NoSuchElementException e) {
				System.out.println(courseNumber);
			}
			String space = scanner.nextLine();
			
			Course course = new Course(courseNumber, title, description, credits);
			theBag.insert(course);
		}
		scanner.close();
	}

	public static void fillTextbookBag(TextbookBag theBag, String file) throws FileNotFoundException {
		File textbookFile = new File(file);
		Scanner scanner = new Scanner(textbookFile, "UTF-8");
		while(scanner.hasNextLine()) {
			String title = scanner.nextLine();
			String isbn = scanner.nextLine();
			String priceS = scanner.nextLine();
			double price = Double.parseDouble(priceS);
			String str = scanner.nextLine();
			String[] tokens = str.split(",");
			Name[] authors = new Name[tokens.length / 2];
			int count = 0;
			for(int i = 0; i < tokens.length-1;i+=2) {
				Name name = new Name(tokens[i], tokens[i+1]);
				authors[count++] = name;
				
			}
			count = 0;
			String space = scanner.nextLine();
			Textbook textbook = new Textbook(title, isbn, authors, price);
			theBag.insert(textbook);
		}
		scanner.close(); 
	
	}
	
	//Enum Type Converters//
	public static Major findMajor(String major) {
		if(major.contentEquals("ENG")) {
			return Major.ENG;
		}else if(major.contentEquals("CSE")) {
			return Major.CSE;
		} else if(major.contentEquals("MAT")) {
			return Major.MAT;
		}
		else {
			return null;
		}
	}
	
	public static Title findTitle(String title) {
		if(title.contentEquals("PROF")) {
			return Title.PROF;
		} else if (title.contains("ASSISTANT_PROF")){
			return Title.ASSISTANT_PROF;
		} else if (title.contains("INSTRUCTOR")) {
			return Title.INSTRUCTOR;
		} else if (title.contains("ASSOCIATE_PROF")) {
			return Title.ASSOCIATE_PROF;
		}
		 else {
			 return null;
		}
	}
	
	public static BuildingName findBuildingName(String name) {
		if(name.contentEquals("Islip")) {
			return BuildingName.Islip;
		} if(name.contentEquals("Riverhead")) {
			return BuildingName.Riverhead;
		} if(name.contentEquals("NFL")) {
			return BuildingName.NFL;
		} if(name.contentEquals("Smithtown")) {
			return BuildingName.Smithtown;
		}
		else {
			return null;
		}
	}
	
	public static LetterGrade findLetterGrade(String grade) {
		if(grade.contentEquals("A")) {
			return LetterGrade.A;
		} else if (grade.contentEquals("B+")) {
			return LetterGrade.B_PLUS;
		} else if (grade.contentEquals("B")) {
			return LetterGrade.B;
		} else if (grade.contentEquals("C+")) {
			return LetterGrade.C_PLUS;
		} else if (grade.contentEquals("C")) {
			return LetterGrade.C;
		} else if (grade.contentEquals("D+")) {
			return LetterGrade.D_PLUS;
		} else if (grade.contentEquals("D")) {
			return LetterGrade.D;
		} else if (grade.contentEquals("F")) {
			return LetterGrade.F;
		} else if (grade.contentEquals("NO GRADE")) {
			return LetterGrade.NO_GRADE;
		}
		else {
			return null;
		}
	}
	
	public static CourseStatus findCourseStatus(String courseStatus) {
		if(courseStatus.contentEquals("TO TAKE")) {
			return CourseStatus.TO_TAKE;
		} else if (courseStatus.contentEquals("TAKING")) {
			return CourseStatus.TAKING;
		} else if(courseStatus.contentEquals("TAKEN")){
			return CourseStatus.TAKEN;
		}else {
			return null;
		}
	}
	
	public static CourseStatus_2 findCourseStatus_2(String courseStatus) {
		if(courseStatus.contentEquals("TAUGHT")) {
			return CourseStatus_2.TAUGHT;
		} else if (courseStatus.contentEquals("TEACHING")) {
			return CourseStatus_2.TEACHING;
		} else {
			return null;
		}
	}

}


