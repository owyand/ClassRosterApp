package com.sg.classroster.controller;

import java.util.List;

import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoException;
import com.sg.classroster.dto.Student;
import com.sg.classroster.ui.ClassRosterView;

public class ClassRosterController {

		private ClassRosterView view;
		private ClassRosterDao dao;
		
		//constructor for dependency injection
		//view and dao are getting passed into the controller
		//app tells controller which implementations to use
		public ClassRosterController(ClassRosterDao dao, ClassRosterView view) {
			this.dao = dao;
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
			} catch (ClassRosterDaoException e) {
				view.displayErrorMessage(e.getMessage());
			}
		}
		
		private int getMenuSelection() {
			return view.printMenuAndGetSelection();
		}
		
		private void createStudent() throws ClassRosterDaoException {
			view.displayCreateStudentBanner();
			Student newStudent = view.getNewStudentInfo();
			dao.addStudent(newStudent.getStudentID(), newStudent);
			view.displayCreateSuccesssBanner(); 
		}
		
		private void listStudents () throws ClassRosterDaoException {
			view.displayDisplayAllBanner();
			List<Student> studentList = dao.getAllStudents();
			view.displayDisplayStudentList(studentList);
		}
		
		private void viewStudent() throws ClassRosterDaoException {
			view.displayDisplayStudentBanner();
			String studentID = view.getStudentIdChoice();
			Student student = dao.getStudent(studentID);
			view.displayStudent(student);
		}
		
		private void removeStudent() throws ClassRosterDaoException {
			view.displayRemoveStudentBanner();
			String studentID = view.getStudentIdChoice();
			Student removedStudent = dao.removeStudent(studentID);
			view.displayRemoveResult(removedStudent);
		}
		
		private void unknownCommand() {
			view.displayUnknownCommandBanner();
		}
		private void exitMessage() {
			view.displayExitBanner();
		}
}

