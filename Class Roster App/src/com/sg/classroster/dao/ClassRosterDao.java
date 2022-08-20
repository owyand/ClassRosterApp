package com.sg.classroster.dao;

import java.util.List;

import com.sg.classroster.dto.Student;

public interface ClassRosterDao {
	/**
	 * Adds the given Student to the roster and associates it with the given student
	 * id. If theres already a student associated with the given student id it will
	 * return that student object, otherwise it will return null
	 * 
	 * @param studentID - id with which the student is to be associated
	 * @param student   - student to be added to the roster
	 * @return the Student object previouslyvassociated with the student id if it
	 *         exists, null otherwise
	 */
	Student addStudent(String studentID, Student student) throws ClassRosterPersistenceException;

	/**
	 * Returns a List of all students in the roster
	 * 
	 * @return List containing all Students in the roster
	 */
	List<Student> getAllStudents() throws ClassRosterPersistenceException;

	/**
	 * Returns the Student object associated with the given student id. returns null
	 * if it doesnt exist
	 * 
	 * @param StudentID - id number of the student to retrieve
	 * @return the Student object associated with the Student id, null if it doesnt
	 *         exist
	 */
	Student getStudent(String studentID) throws ClassRosterPersistenceException;

	/**
	 * removes the Student object associated with the given student id from the
	 * roster. returns the student object that is being removed or null if there is
	 * no student associated with that id
	 * 
	 * @param studentID - id associated with the Student to remove
	 * @return the Student object that was removed from the roster or null if it
	 *         doesnt exist
	 */
	Student removeStudent(String studentID) throws ClassRosterPersistenceException;
}
