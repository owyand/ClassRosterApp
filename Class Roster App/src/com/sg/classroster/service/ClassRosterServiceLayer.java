package com.sg.classroster.service;

import java.util.List;

import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;

public interface ClassRosterServiceLayer {

	void createStudent(Student student)
			throws ClassRosterDuplicateIDException, 
				ClassRosterDataValidationException, 
				ClassRosterPersistenceException;

	List<Student> getAllStudents() 
			throws ClassRosterPersistenceException;

	Student getStudent(String studentID) 
			throws ClassRosterPersistenceException;

	Student removeStudent(String studentID) 
			throws ClassRosterPersistenceException;

}
