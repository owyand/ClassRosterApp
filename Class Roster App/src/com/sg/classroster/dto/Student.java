package com.sg.classroster.dto;

public class Student {
	
	private String firstName;
	private String lastName;
	//only determined by the constructor no setter method because it is final
	final String studentID;
	//programming language + cohort month/year
	private String cohort;
	
	//constructor with student ID
	public Student(String studentID) {
		this.studentID = studentID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getStudentID() {
		return studentID;
	}
	
	public String getCohort() {
		return cohort;
	}
	public void setCohort(String cohort) {
		this.cohort = cohort;
	}
}
