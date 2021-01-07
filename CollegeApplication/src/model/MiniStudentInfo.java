package model;

import java.io.Serializable;

public class MiniStudentInfo implements Serializable{
	private String courseNumber;
	private double numberOfCredits;
	private LetterGrade letterGrade;
	public CourseStatus courseStatus;

	 public MiniStudentInfo(String courseNumber, double numberOfCredits, LetterGrade letterGrade,
			CourseStatus courseStatus) {
		super();
		this.courseNumber = courseNumber;
		this.numberOfCredits = numberOfCredits;
		this.letterGrade = letterGrade;
		this.courseStatus = courseStatus;
	} 
	
	public MiniStudentInfo(String courseNumber, double numberOfCredits) {
		super();
		this.courseNumber = courseNumber;
		this.numberOfCredits = numberOfCredits;
		this.letterGrade = LetterGrade.NO_GRADE;
		this.courseStatus = CourseStatus.TO_TAKE;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public double getNumberOfCredits() {
		return numberOfCredits;
	}

	public double convert() {
		double point = 0;
		switch (letterGrade) {
		case A:
			point = 4.0;
			break;
		case B_PLUS:
			point = 3.5;
			break;
		case B:
			point = 3.0;
			break;
		case C_PLUS:
			point = 2.5;
			break;
		case C:
			point = 2.0;
			break;
		case D_PLUS:
			point = 1.5;
			break;
		case D:
			point = 1.0;
			break;
		case F:
			point = 0.0;
			break;
		default:
			point = 0;
		}
		return point;
	}

	public void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	public LetterGrade getLetterGrade() {
		return letterGrade;
	}

	public void setLetterGrade(LetterGrade letterGrade) {
		this.letterGrade = letterGrade;
	}

	public CourseStatus getCourseStatus() {
		return courseStatus;
	}

	public void setCourseStatus(CourseStatus courseStatus) {
		this.courseStatus = courseStatus;
	}

	@Override
	public String toString() {
		return "MiniStudentInfo [courseNumber=" + courseNumber + ", numberOfCredits=" + numberOfCredits
				+ ", letterGrade=" + letterGrade + ", courseStatus=" + courseStatus + "]";
	}

}
