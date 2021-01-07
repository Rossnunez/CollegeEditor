package model;

import java.io.Serializable;

public class MiniFacultyCourseInfo implements Serializable{
	private String courseNumber;
	private double numberOfCredits;
	private CourseStatus_2 courseStatus;
	
	
	public MiniFacultyCourseInfo(String courseNumber, double numberOfCredits, CourseStatus_2 courseStatus) {
		super();
		this.courseNumber = courseNumber;
		this.numberOfCredits = numberOfCredits;
		this.courseStatus = courseStatus;
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


	public void setNumberOfCredits(double numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}


	public CourseStatus_2 getCourseStatus() {
		return courseStatus;
	}


	public void setCourseStatus(CourseStatus_2 courseStatus) {
		this.courseStatus = courseStatus;
	}


	@Override
	public String toString() {
		return "MiniFacultyCourseInfo [courseNumber=" + courseNumber 
				+ ", numberOfCredits=" + numberOfCredits + ", courseStatus=" + courseStatus + "]";
	}
	
	
	
}
