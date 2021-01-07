package model;

import java.io.Serializable;

public class MasterCourseBag implements Serializable{
	private Course[] courseArray;
	private int nElems;

	public MasterCourseBag(int maxSize) {
		courseArray = new Course[maxSize];
		nElems = 0;
	}

	public void insert(Course course) {
		courseArray[nElems++] = course;
	}

	public Course findById(String courseNumber) {
		for (int i = 0; i < nElems; i++) {
			if (courseArray[i].getCourseNumber().contentEquals(courseNumber)) {
				return courseArray[i];
			}
		}
		return null;
	}

	public Course removeById(String courseNumber) {
		int i;
		for (i = 0; i < nElems; i++) {
			if (courseArray[i].getCourseNumber().contentEquals(courseNumber)) {
				break;
			}
		}

		if (i == nElems) {
			return null;
		} else {
			Course temp = courseArray[i];
			for (int j = i; j < nElems - 1; j++) {
				courseArray[j] = courseArray[j + 1];
			}
			nElems--;
			return temp;
		}
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(courseArray[i]);
		}
	}
	
	public String getCourseNumber(int i) {
		return courseArray[i].getCourseNumber();
	} 
	
	public double getNumberOfCredits(int i) {
		return courseArray[i].getNumberOfCredits();
	}

	public Course[] getCourseArray() {
		return courseArray;
	}

	public void setCourseArray(Course[] courseArray) {
		this.courseArray = courseArray;
	}

	public int getnElems() {
		return nElems;
	}

	public void setnElems(int nElems) {
		this.nElems = nElems;
	}
	
	
	
}
