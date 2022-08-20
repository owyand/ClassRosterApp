package com.sg.classroster.ui;

import java.util.List;

import com.sg.classroster.dto.Student;

public class ClassRosterView {

	private UserIO io;
	
	public ClassRosterView(UserIO io) {
		this.io = io;
	}

	public int printMenuAndGetSelection() {
		io.print("Main Menu");
		io.print("1. List Students");
		io.print("2. Create New Student");
		io.print("3. View a Student");
		io.print("4. Remove a Student");
		io.print("5. Exit");

		// this calls a method from UserIOConsoleImpl
		return io.readInt("Please select from the above choices", 1, 5);
	}

	// gets info from user to create and store a new student object
	public Student getNewStudentInfo() {
		Student currentStudent = new Student(io.readString("Please enter Student ID"));

		currentStudent.setFirstName(io.readString("Please enter First Name"));
		currentStudent.setLastName(io.readString("Please enter Last Name"));
		currentStudent.setCohort(io.readString("Please enter Cohort"));

		return currentStudent;
	}

	// banner
	public void displayCreateStudentBanner() {
		io.print("=== Create Student ===");
	}

	public void displayCreateSuccessBanner() {
		io.readString("Student successfully create. Please hit continue");
	}

	public void displayDisplayStudentList(List<Student> studentList) {
		for (Student currentStudent : studentList) {
			String studentInfo = String.format("#%s : %s %s %s", 
					currentStudent.getStudentID(),
					currentStudent.getFirstName(), 
					currentStudent.getLastName(), 
					currentStudent.getCohort());
			io.print(studentInfo);
		}
	}
	public void displayDisplayAllBanner() {
		io.print("=== Display All Students ===");
	}
	
	public void displayDisplayStudentBanner() {
		io.print("=== Display Student ===");
	}
	public String getStudentIdChoice() {
		return io.readString("Please enter the Student ID");
	}
	public void displayStudent(Student student) {
		if (student != null) {
			io.print(student.getStudentID());
			io.print(student.getFirstName() + " " + student.getLastName());
			io.print(student.getCohort());
			io.print("");
		} else {
			io.print("No such student");
		}
		io.readString("Please hit enter to continue");
	}
	
	public void displayRemoveStudentBanner() {
		io.print("=== Remove Student ===");
	}
	public void displayRemoveResult(Student studentRecord) {
		if (studentRecord != null) {
			io.print("Student successfully removed");
		} else {
			io.print("No such student.");
		}
		
		io.readString("Please hit enter to continue.");
	}
	
	public void displayExitBanner() {
		io.print("program exited");
	}
	public void displayUnknownCommandBanner() {
		io.print("Unknown Command");
	}
	
	public void displayErrorMessage(String errormsg) {
		io.print("=== ERROR ===");
		io.print(errormsg);
	}
	
}
