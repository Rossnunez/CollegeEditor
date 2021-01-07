package model;

import java.io.Serializable;

public class ClassroomBag implements Serializable{
	private Classroom[] classroomArray;
	private int nElems;

	public ClassroomBag(int maxSize) {
		classroomArray = new Classroom[maxSize];
		nElems = 0;
	}
	
	public void insert(Classroom classroom) {
		classroomArray[nElems++] = classroom;
	}
	
	
	
	public Classroom[] getClassroomArray() {
		return classroomArray;
	}

	

	public int getnElems() {
		return nElems;
	}

	public Classroom findById(String roomNumber) {
		for(int i = 0; i < nElems; i++) {
			if(classroomArray[i].getRoomNumber().contentEquals(roomNumber)) {
				return classroomArray[i];
			}
		}
		return null;
	}
	
	public Classroom removeById(String roomNumber) {
		int i;
		for(i = 0; i < nElems; i++) {
			if(classroomArray[i].getRoomNumber().contentEquals(roomNumber)) {
				break;
			}
		}
		
		if(i == nElems) {
			return null;
		} else {
			Classroom temp = classroomArray[i];
			for(int j = i; j < nElems - 1; j++) {
				classroomArray[j] = classroomArray[j + 1];
			}
			nElems--;
			return temp;
		}
	}
	
	public void display() {
		for(int i = 0; i < nElems; i++) {
			System.out.println(classroomArray[i]);
		}
		System.out.println();
	}
	
}
