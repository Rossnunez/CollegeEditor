package model;

import java.io.Serializable;

public class Faculty extends Person implements Serializable{
	private Name name;
	private Title title;
	private double salary;
	private String officePhone;
	private MiniFacultyCourseBag miniFacultyCourseBag;

	public Faculty(Name name, Title title, double salary, String officePhone, MiniFacultyCourseBag miniFacultyCourseBag) {
		super(name);
		this.name = name;
		this.title = title;
		this.salary = salary;
		this.officePhone = officePhone;
		this.miniFacultyCourseBag = miniFacultyCourseBag;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public MiniFacultyCourseBag getMiniFacultyCourseBag() {
		return miniFacultyCourseBag;
	}
	
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Faculty [name=" + name + ", title=" + title + ", salary=" + salary + ", officePhone=" + officePhone
				+ ", miniFacultyCourseBag=" + miniFacultyCourseBag + "]";
	}

	

}
