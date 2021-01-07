package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;
import model.MasterCourseBag;
import view.Alerts;

public class MajorCourseFillerHelper implements Serializable{
	
	public static MiniStudentCourseBag fillWithMajorCourses(Major major, MasterCourseBag masterCourseBag) throws FileNotFoundException {
		MiniStudentCourseBag bag = new MiniStudentCourseBag(30);
		
		File file = new File("MajorCourses/" + major.toString() + ".txt");
		Scanner scan = new Scanner(file);
		
		while(scan.hasNextLine()) {
			String coursenum = scan.nextLine();
			Course course = masterCourseBag.findById(coursenum);
			if(course != null) {
			bag.insert(new MiniStudentInfo(coursenum, course.getNumberOfCredits()));
			} else {
				Alerts.getstudentCBAlert2();
			}
		}
		scan.close();
		return bag;
	}
}
