package com.sg.classroster.controller;

import java.util.List;

import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoFileImpl;
import com.sg.classroster.dto.Student;
import com.sg.classroster.ui.ClassRosterView;
import com.sg.classroster.ui.UserIO;
import com.sg.classroster.ui.UserIOConsoleImpl;

public class ClassRosterController {

		private ClassRosterView view = new ClassRosterView();
		private ClassRosterDao dao = new ClassRosterDaoFileImpl();
		
		public void run() {
			boolean keepGoing = true;
			int menuSelection = 0;
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
		}
		
		private int getMenuSelection() {
			return view.printMenuAndGetSelection();
		}
		
		private void createStudent() {
			view.displayCreateStudentBanner();
			Student newStudent = view.getNewStudentInfo();
			dao.addStudent(newStudent.getStudentID(), newStudent);
			view.displayCreateSuccesssBanner(); 
		}
		
		private void listStudents() {
			view.displayDisplayAllBanner();
			List<Student> studentList = dao.getAllStudents();
			view.displayDisplayStudentList(studentList);
		}
		
		private void viewStudent() {
			view.displayDisplayStudentBanner();
			String studentID = view.getStudentIdChoice();
			Student student = dao.getStudent(studentID);
			view.displayStudent(student);
		}
		
		private void removeStudent() {
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

