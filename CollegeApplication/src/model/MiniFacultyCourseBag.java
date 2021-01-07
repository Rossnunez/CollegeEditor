package model;

import java.io.Serializable;

public class MiniFacultyCourseBag implements Serializable{
	private MiniFacultyCourseInfo[] miniFacultyCourseInfo;
	private int nElems;
	private int maxSize;

	public MiniFacultyCourseBag(int maxSize) {
		miniFacultyCourseInfo = new MiniFacultyCourseInfo[maxSize];
		nElems = 0;
		this.maxSize = maxSize;
	}
	
	public int getSize() {
		return maxSize;
	}

	public MiniFacultyCourseInfo[] getMiniFacultyCourseInfo() {
		return miniFacultyCourseInfo;
	}

	public int getnElems() {
		return nElems;
	}

	public void insert(MiniFacultyCourseInfo miniFacultyCourseInfo) {
		this.miniFacultyCourseInfo[nElems++] = miniFacultyCourseInfo;
	}

	public MiniFacultyCourseInfo findById(String courseNumber) {
		for (int i = 0; i < nElems; i++) {
			if (miniFacultyCourseInfo[i].getCourseNumber().contentEquals(courseNumber)) {
				return miniFacultyCourseInfo[i];
			}
		}
		return null;
	}

	public MiniFacultyCourseInfo removeById(String courseNumber) {
		int i;
		for (i = 0; i < nElems; i++) {
			if (miniFacultyCourseInfo[i].getCourseNumber().contentEquals(courseNumber)) {
				break;
			}
		}

		if (i == nElems) {
			return null;
		} else {
			MiniFacultyCourseInfo temp = miniFacultyCourseInfo[i];
			for (int j = i; j < nElems - 1; j++) {
				miniFacultyCourseInfo[j] = miniFacultyCourseInfo[j + 1];
			}
			nElems--;
			return temp;
		}
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(miniFacultyCourseInfo[i]);
		}
		System.out.println();
	}
}
