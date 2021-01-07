package model;

import java.io.Serializable;

public class Course implements Serializable{
	private String courseNumber;
	private String courseTitle;
	private String CourseDescription;
	private double numberOfCredits;

	public Course(String courseNumber, String courseTitle, String courseDescription, double numberOfCredits) {
		super();
		this.courseNumber = courseNumber;
		this.courseTitle = courseTitle;
		CourseDescription = courseDescription;
		this.numberOfCredits = numberOfCredits;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseDescription() {
		return CourseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		CourseDescription = courseDescription;
	}

	public double getNumberOfCredits() {
		return numberOfCredits;
	}

	public void setNumberOfCredits(double numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}

	@Override
	public String toString() {
		return "Course [courseNumber=" + courseNumber + ", courseTitle=" + courseTitle + ", CourseDescription="
				+ CourseDescription + ", numberOfCredits=" + numberOfCredits + "]";
	}

}
