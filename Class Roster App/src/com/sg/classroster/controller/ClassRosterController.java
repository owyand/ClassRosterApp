package com.sg.classroster.controller;

import java.util.List;

import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;
import com.sg.classroster.service.ClassRosterDataValidationException;
import com.sg.classroster.service.ClassRosterDuplicateIDException;
import com.sg.classroster.service.ClassRosterServiceLayer;
import com.sg.classroster.ui.ClassRosterView;

public class ClassRosterController {

		private ClassRosterView view;
		private ClassRosterServiceLayer service;
		
		//constructor for dependency injection
		//view and service are getting passed into the controller
		//app tells controller which implementations to use
		public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view) {
			this.service = service;
			this.view = view;
		}
		
		public void run() {
			boolean keepGoing = true;
			int menuSelection = 0;
			
			try {
				while(keepGoing) {
					
					menuSelection = getMenuSelection();
					
					switch (menuSelection) {
					case 1: 
						listStudents();
						break;
					case 2:
						createStudent();
						break;
					case 3:
						viewStudent();
						break;
					case 4:
						removeStudent();
						break;
					case 5:
						keepGoing = false;
						break;
					default:
						unknownCommand();
					}
				}
				exitMessage();
			} catch (ClassRosterPersistenceException e) {
				view.displayErrorMessage(e.getMessage());
			}
		}
		
		private int getMenuSelection() {
			return view.printMenuAndGetSelection();
		}
		
		private void createStudent() throws ClassRosterPersistenceException {
			view.displayCreateStudentBanner();
			boolean hasErrors = false;
			
			//only loops if an error is thrown
			do { 
				Student currentStudent = view.getNewStudentInfo();
				try {
					service.createStudent(currentStudent);
					view.displayCreateSuccessBanner();
					hasErrors = false;
				} catch (ClassRosterDuplicateIDException | ClassRosterDataValidationException e) {
					hasErrors = true;
					view.displayErrorMessage(e.getMessage());
				}
			} while (hasErrors);
		}
		
		private void listStudents () throws ClassRosterPersistenceException {
			view.displayDisplayAllBanner();
			List<Student> studentList = service.getAllStudents();
			view.displayDisplayStudentList(studentList);
		}
		
		private void viewStudent() throws ClassRosterPersistenceException {
			view.displayDisplayStudentBanner();
			String studentID = view.getStudentIdChoice();
			Student student = service.getStudent(studentID);
			view.displayStudent(student);
		}
		
		private void removeStudent() throws ClassRosterPersistenceException {
			view.displayRemoveStudentBanner();
			String studentID = view.getStudentIdChoice();
			Student removedStudent = service.removeStudent(studentID);
			view.displayRemoveResult(removedStudent);
		}
		
		private void unknownCommand() {
			view.displayUnknownCommandBanner();
		}
		private void exitMessage() {
			view.displayExitBanner();
		}
}

