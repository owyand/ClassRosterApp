package com.sg.classroster.service;

import java.util.List;

import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterPersistenceException;
import com.sg.classroster.dto.Student;

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {

	private final ClassRosterAuditDao auditDao;
	ClassRosterDao dao;

	public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao) {
		this.dao = dao;
		this.auditDao = auditDao;
	}

	@Override
	public void createStudent(Student student) throws ClassRosterDuplicateIDException,
			ClassRosterDataValidationException, ClassRosterPersistenceException {

		// check for existing student associated with the student ID
		if (dao.getStudent(student.getStudentID()) != null) {
			throw new ClassRosterDuplicateIDException(
					"ERROR: Could not create student. Student ID: " + student.getStudentID() + " already exists");
		}
		
		//call to private method to validate the names and cohort throws
		//ClassRosterDataValidationException 
		validateStudentData(student);
		
		//if no exception is thrown we persist to dao
		dao.addStudent(student.getStudentID(), student);
		
		//student was successfully created with no thrown errors
		auditDao.writeAuditEntry("Student " + student.getStudentID() + " CREATED.");

	}

	@Override
	public List<Student> getAllStudents() throws ClassRosterPersistenceException {
		//simple pass through no logic needed
		return dao.getAllStudents();
	}

	@Override
	public Student getStudent(String studentID) throws ClassRosterPersistenceException {
		//simple pass through no logic needed
		return dao.getStudent(studentID);
	}

	@Override
	public Student removeStudent(String studentID) throws ClassRosterPersistenceException {
		
		Student removedStudent = dao.removeStudent(studentID);
		//write to audit log
		auditDao.writeAuditEntry("Student " + studentID + " REMOVED.");
		return removedStudent;
	}

	private void validateStudentData(Student student) throws ClassRosterDataValidationException {

		if (student.getFirstName() == null || student.getFirstName().trim().length() == 0
				|| student.getLastName() == null || student.getLastName().trim().length() == 0
				|| student.getCohort() == null || student.getCohort().trim().length() == 0) {
			throw new ClassRosterDataValidationException(
					"ERROR: All fields [First Name, Last Name, Cohort] are required");

		}
	}

}
