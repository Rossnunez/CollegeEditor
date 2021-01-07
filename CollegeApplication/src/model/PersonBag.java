package model;

import java.io.Serializable;

public class PersonBag implements Serializable{
	private static Person[] facultyArray;
	private static Person[] studentArray;
	private int nElemF;
	private int nElemS;

	public PersonBag(int maxSize) {
		facultyArray = new Person[maxSize];
		nElemF = 0;
	}
	
	public PersonBag(int maxSize, int temp) {
		studentArray = new Person[maxSize];
		nElemS = 0;
	}
	
	public void setStudentArray(Person[] person) {
		studentArray = person;
	}
	
	public void setFacultyArray(Person[] person) {
		facultyArray = person;
	}
	
	

	public Person[] getFacultyArray() {
		return facultyArray;
	}
	
	public Person[] getStudentArray() {
		return studentArray;
	}



	public int getnElemS() {
		return nElemS;
	}
	
	public int getnElemF() {
		return nElemF;
	}


	public void insertFaculty(Person person) {
		facultyArray[nElemF++] = person;
	}
	
	public void insertStudent(Person person) {
		studentArray[nElemS++] = person;
	}

	public Person findByIdFaculty(String id) {
		for (int i = 0; i < nElemF; i++) {
			if (facultyArray[i].getId().contentEquals(id)) {
				return facultyArray[i];
			}
		}
		return null;
	}
	
	public Person findByIdStudent(String id) {
		for (int i = 0; i < nElemS; i++) {
			if (studentArray[i].getId().contentEquals(id)) {
				return studentArray[i];
			}
		}
		return null;
	}

	public Person removeByIdFaculty(String id) {
		int i;
		for (i = 0; i < nElemF; i++) {
			if (facultyArray[i].getId().contentEquals(id)) {
				break;
			}
		}

		if (i == nElemF) {
			return null;
		} else {
			Person temp = facultyArray[i];
			for (int j = i; j < nElemF - 1; j++) {
				facultyArray[j] = facultyArray[j + 1];
			}
			nElemF--;
			return temp;
		}
	}
	
	public Person removeByIdStudent(String id) {
		int i;
		for (i = 0; i < nElemS; i++) {
			if (studentArray[i].getId().contentEquals(id)) {
				break;
			}
		}

		if (i == nElemS) {
			return null;
		} else {
			Person temp = studentArray[i];
			for (int j = i; j < nElemS - 1; j++) {
				studentArray[j] = studentArray[j + 1];
			}
			nElemS--;
			return temp;
		}
	}

	public void displayFaculty() {
		for (int i = 0; i < nElemF; i++) {
			System.out.println(facultyArray[i]);
		}
		System.out.println();
	}
	
	public void displayStudent() {
		for (int i = 0; i < nElemS; i++) {
			System.out.println(studentArray[i]);
		}
		System.out.println();
	}

}
