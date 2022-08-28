package com.sg.classroster.dto;

import java.util.Objects;

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
	
	/*
	 * Testing methods to compare whole Objects
	 */

	@Override
	public int hashCode() {
		return Objects.hash(cohort, firstName, lastName, studentID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(cohort, other.cohort) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(studentID, other.studentID);
	}

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", studentID=" + studentID + ", cohort="
				+ cohort + "]";
	}




}
