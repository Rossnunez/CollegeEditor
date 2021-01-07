package model;

import java.io.Serializable;

public class College implements Serializable{
	private PersonBag studentBag;
	private PersonBag facultyBag;
	private ClassroomBag classroomBag;
	private TextbookBag textbookBag;
	private MasterCourseBag masterCourseBag;
	private Person[] studentArray;
	private Person[] facultyArray;

	public College(PersonBag studentBag, PersonBag facultyBag, ClassroomBag classroomBag, TextbookBag textbookBag,
			MasterCourseBag masterCourseBag) {
		super();
		this.studentBag = studentBag;
		studentArray = studentBag.getStudentArray();
		this.facultyBag = facultyBag;
		facultyArray = facultyBag.getFacultyArray();
		this.classroomBag = classroomBag;
		this.textbookBag = textbookBag;
		this.masterCourseBag = masterCourseBag;
	}

	public Person[] getFacultyArray() {
		return facultyArray;
	}
	
	public Person[] getStudentArray() {
		return studentArray;
	}
	
	public PersonBag getStudentBag() {
		return studentBag;
	}
	
	public PersonBag getFacultyBag() {
		return facultyBag;
	}
	
	public TextbookBag getTextbookBag() {
		return textbookBag;
	}
	
	public ClassroomBag getClassroomBag() {
		return classroomBag;
	}
	
	public MasterCourseBag getMasterCourseBag() {
		return masterCourseBag;
	}

	public void setClassroomBag(ClassroomBag classroomBag) {
		this.classroomBag = classroomBag;
	}

	public void setTextbookBag(TextbookBag textbookBag) {
		this.textbookBag = textbookBag;
	}

	public void setMasterCourseBag(MasterCourseBag masterCourseBag) {
		this.masterCourseBag = masterCourseBag;
	}
	

	

	@Override
	public String toString() {
		return "College [studentBag=" + studentBag + ", facultyBag=" + facultyBag + ", classroomBag=" + classroomBag
				+ ", textbookBag=" + textbookBag + ", masterCourseBag=" + masterCourseBag + "]";
	}


	public void setFacultyBag(PersonBag facultyBag) {
		this.facultyBag = facultyBag;
	}

}
