package model;

import java.io.FileNotFoundException;
import java.io.Serializable;

public class Student extends Person implements Serializable{
	private Name name;
	private Major major;
	private double gpa;
	private String phoneNumber;
	private MiniStudentCourseBag miniStudentCourseBag;
	
	
	public Student(Name name, Major major, String phoneNumber, MiniStudentCourseBag miniStudentCourseBag) throws FileNotFoundException {
		super(name);
		this.name = name;
		this.major = major;
		this.phoneNumber = phoneNumber;
		this.miniStudentCourseBag = miniStudentCourseBag;
	}
	
	public Student(Name name, Major major, String phoneNumber, MasterCourseBag masterCourseBag) throws FileNotFoundException {
		super(name);
		this.name = name;
		this.major = major;
		this.phoneNumber = phoneNumber;
		miniStudentCourseBag = MajorCourseFillerHelper.fillWithMajorCourses(major, masterCourseBag);
		gpa = 0;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public double getGpa() {
		gpa = gpaConverter(miniStudentCourseBag);
		return gpa;
	}

	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public static double gpaConverter(MiniStudentCourseBag miniStudentCourseBag) {
		MiniStudentInfo[] info = miniStudentCourseBag.getCourses();
		double gradePoints = 0;
		double totalCredits = 0;
		int nElems = miniStudentCourseBag.getnElems();

		for (int i = 0; i < nElems; i++) {
			totalCredits += info[i].getNumberOfCredits();
			gradePoints += info[i].getNumberOfCredits() * info[i].convert();
		}

		return gradePoints / totalCredits;
	}

	public MiniStudentCourseBag getMiniStudentCourseBag() {
		return miniStudentCourseBag;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", major=" + major + ", gpa=" + gpa + ", miniStudentCourseBag="
				+ miniStudentCourseBag + "]";
	}
}
