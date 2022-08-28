/**
 * 
 */
package com.sg.classroster.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dao.ClassRosterDaoFileImpl;
import com.sg.classroster.dto.Student;

/**
 * @author Oliver Wyand
 *
 */
class ClassRosterDaoFileImplTest {

	ClassRosterDao testDao;
	
	public ClassRosterDaoFileImplTest() {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		String testFile = "testeroster.txt";
		//create a new FileWriter for the test roster file
		new FileWriter(testFile);
		testDao = new ClassRosterDaoFileImpl(testFile);
	}
	
	@Test
	public void testAddGetStudent() throws Exception {
		//Create our method test inputs
		String studentID = "0001";
		Student student = new Student(studentID);
		student.setFirstName("Ada");
		student.setLastName("Lovelace");
		student.setCohort("Java-May-1845");
		
		//add student to the DAO
		testDao.addStudent(studentID, student);
		//get student from the DAO
		Student retrievedStudent = testDao.getStudent(studentID);
		
		//check that the data is equal
		assertEquals(student.getStudentID(), retrievedStudent.getStudentID(), "checking student ID");
		assertEquals(student.getFirstName(), retrievedStudent.getFirstName(), "checking student first name");
		assertEquals(student.getLastName(), retrievedStudent.getLastName(), "checking student last name");
		assertEquals(student.getCohort(), retrievedStudent.getCohort(), "checking student cohort");
	}
	
	@Test
	public void testAddGetAllStudents() throws Exception {
		//Create first student
		Student firstStudent = new Student("0001");
		firstStudent.setFirstName("Ada");
		firstStudent.setLastName("Lovelace");
		firstStudent.setCohort("Java-May-1845");
		
		//Create second student
		Student secondStudent = new Student("0002");
		secondStudent.setFirstName("Charles");
		secondStudent.setLastName("Babbage");
		secondStudent.setCohort(".NET-May-1845");
		
		//Add both to Dao
		testDao.addStudent(firstStudent.getStudentID(), firstStudent);
		testDao.addStudent(secondStudent.getStudentID(), secondStudent);
		
		//retrieve the list of all students within dao
		List<Student> allStudents = testDao.getAllStudents();
		
		//1st check general contents
		assertNotNull(allStudents, "The list of the Students must not be null");
		assertEquals(2, allStudents.size(), "The list of students should have two students");
		
		//2nd specifics
		assertTrue(testDao.getAllStudents().contains(firstStudent), "The list should contain \'Ada\'");
		assertTrue(testDao.getAllStudents().contains(secondStudent), "The list should contains \'Charles\'");
	}
	
	@Test
	public void testRemoveStudent() throws Exception {
		//Create 2 students
		Student firstStudent = new Student("0001");
		firstStudent.setFirstName("Ada");
		firstStudent.setLastName("Lovelace");
		firstStudent.setCohort("Java-May-1845");
		Student secondStudent = new Student("0002");
		secondStudent.setFirstName("Charles");
		secondStudent.setLastName("Babbage");
		secondStudent.setCohort(".NET-May-1845");
		
		//Add both to Dao
		testDao.addStudent(firstStudent.getStudentID(), firstStudent);
		testDao.addStudent(secondStudent.getStudentID(), secondStudent);
		
		//remove first student (Ada)
		Student removedStudent = testDao.removeStudent(firstStudent.getStudentID());
		
		//check that it was removed
		assertEquals(removedStudent, firstStudent, "The removed Student should be \'Ada\'");
		
		//get all the students
		List<Student> allStudents = testDao.getAllStudents();
		
		//check general contents
		assertNotNull(allStudents, "All students list should not be null");
		assertEquals(1, allStudents.size(), "All students should only have 1 student");
		
		//specifics
		assertFalse(allStudents.contains(firstStudent), "All Students should not include \'Ada\'");
		assertTrue(allStudents.contains(secondStudent), "All Students should include \'Charles\'");
		
		//remove the second student
		removedStudent = testDao.removeStudent(secondStudent.getStudentID());
		
		//check that it was removed
		assertEquals(removedStudent, secondStudent, "The removed student should be \'Charles\'");
		
		//retrieve all students again
		allStudents = testDao.getAllStudents();
		
		//check contents - should be empty
		assertTrue(allStudents.isEmpty(), "The retrieved list of students should be empty");
		
		//try to get both students by their old id - should return null
		Student retrievedStudent  = testDao.getStudent(firstStudent.getStudentID());
		assertNull(retrievedStudent, "retrieved student should be null");
		
		retrievedStudent = testDao.getStudent(secondStudent.getStudentID());
		assertNull(retrievedStudent, "retrieved student should be null");
	}

}
