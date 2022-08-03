package com.sg.classroster.ui;

import com.sg.classroster.dto.Student;

public class ClassRosterView {
	
	private UserIO io = new UserIOConsoleImpl();
	
	public int printMenuAndGetSelection() {
		io.print("Main Menu");
		io.print("1. List Students");
		io.print("2. Create New Student");
		io.print("3. View a Student");
		io.print("4. Remove a Student");
		io.print("5. Exit");
		
		//this calls a method from UserIOConsoleImpl
		return io.readInt("Please select from the above choices", 1, 5);
	}
	
	//gets info from user to create and store a new student object
	public Student getNewStudentInfo() {
		Student currentStudent = new Student(io.readString("Please enter Student ID"));
		
		currentStudent.setFirstName(io.readString("Please enter First Name"));
		currentStudent.setLastName(io.readString("Please enter Last Name"));
		currentStudent.setCohort(io.readString("Please enter Cohort"));
		
		return currentStudent;
	}
	
	//banner
	public void displayCreateStudentBanner() {
		io.print("=== Create Student ===");
	}
	
	public void displayCreateSuccesssBanner() {
		io.readString("Student successfully create. Please hit continue");
	}
}
