package model;

import java.io.Serializable;

public class MiniStudentCourseBag implements Serializable{
	private MiniStudentInfo[] miniStudentInfo;
	private int nElems;
	private int maxSize;

	public MiniStudentCourseBag(int maxSize) {
		miniStudentInfo = new MiniStudentInfo[maxSize];
		nElems = 0;
		this.maxSize = maxSize;
	}

	public void insert(MiniStudentInfo miniStudentInfo) {
		this.miniStudentInfo[nElems++] = miniStudentInfo;
	}

	public MiniStudentInfo findById(String courseNumber) {
		for (int i = 0; i < nElems; i++) {
			if (miniStudentInfo[i].getCourseNumber().contentEquals(courseNumber)) {
				return miniStudentInfo[i];
			}
		}
		return null;
	}

	public MiniStudentInfo removeById(String courseNumber) {
		int i;
		for (i = 0; i < nElems; i++) {
			if (miniStudentInfo[i].getCourseNumber().contentEquals(courseNumber)) {
				break;
			}
		}

		if (i == nElems) {
			return null;
		} else {
			MiniStudentInfo temp = miniStudentInfo[i];
			for (int j = i; j < nElems - 1; j++) {
				miniStudentInfo[j] = miniStudentInfo[j + 1];
			}
			nElems--;
			return temp;
		}
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(miniStudentInfo[i]);
		}
		System.out.println();
	}

	public int getnElems() {
		return nElems;
	}
	
	public int getSize() {
		return maxSize;
	} 



	public MiniStudentInfo[] getMiniStudentInfo() {
		return miniStudentInfo;
	}

	public void setMiniStudentInfo(MiniStudentInfo[] miniStudentInfo) {
		this.miniStudentInfo = miniStudentInfo;
	}

	public void setnElems(int nElems) {
		this.nElems = nElems;
	}

	public MiniStudentInfo[] getCourses() {
		return miniStudentInfo;
	}



}
