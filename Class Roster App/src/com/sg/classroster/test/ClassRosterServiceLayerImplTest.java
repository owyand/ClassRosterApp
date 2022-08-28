package com.sg.classroster.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterDao;
import com.sg.classroster.dto.Student;
import com.sg.classroster.service.ClassRosterDuplicateIDException;
import com.sg.classroster.service.ClassRosterServiceLayer;
import com.sg.classroster.service.ClassRosterServiceLayerImpl;
import com.sg.classroster.service.ClassRosterDataValidationException;
import com.sg.classroster.dao.ClassRosterPersistenceException;

class ClassRosterServiceLayerImplTest {
	
	private ClassRosterServiceLayer service;
	
	public ClassRosterServiceLayerImplTest() {
		ClassRosterDao dao = new ClassRosterDaoStubImpl();
		ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
		
		service = new ClassRosterServiceLayerImpl(dao, auditDao);
	}
	
	@Test
	public void testCreateValidStudent() {
		//arrange
		Student student = new Student("0002");
		student.setFirstName("Charles");
		student.setLastName("Babbage");
		student.setCohort(".NET-May-1845");
		
		//act
		try {
			service.createStudent(student);
		} catch (ClassRosterDuplicateIDException | ClassRosterDataValidationException | ClassRosterPersistenceException e) {
			//assert
			fail("Student was valid no exception shouldve been thrown");
		}
	}
	
	@Test
	public void testCreateStudentDuplicateID() {
		//arrange
		Student student = new Student("0001");
		student.setFirstName("Charles");
		student.setLastName("Babbage");
		student.setCohort(".NET-May-1845");
		
		//act
		try {
			service.createStudent(student);
			fail("Expected DupeID was not thrown");
		} catch (ClassRosterDataValidationException | ClassRosterPersistenceException e) {
			//assert
			fail("incorrect exception was thrown");
		} catch (ClassRosterDuplicateIDException e) {
			return;
		}
	}
	
	@Test
	public void testCreateStudentInvalidData() throws Exception {
		//arrange
		Student student = new Student("0002");
		student.setFirstName("");
		student.setLastName("Babbage");
		student.setCohort(".NET-May-1845");
		
		//act
		try {
			service.createStudent(student);
			fail("Expected ValidationException was not thrown");
		}  catch (ClassRosterDuplicateIDException | ClassRosterPersistenceException e) {
			//assert
			fail("incorrect exception was thrown");
		} catch (ClassRosterDataValidationException e) {
			return;
		}
	}
	
	@Test
	public void testGetAllStudents() throws Exception{
		//arrange
		Student testClone = new Student("0001");
		testClone.setFirstName("Ada");
		testClone.setLastName("Lovelace");
		testClone.setCohort("Java-May-1845");
		
		//act and assert
		assertEquals(1, service.getAllStudents().size(), "should only have one student");
		assertTrue(service.getAllStudents().contains(testClone), "The one student should be Ada");
	}
	
	@Test
	public void testGetStudent() throws Exception {
		//arrange
		Student testClone = new Student("0001");
		testClone.setFirstName("Ada");
		testClone.setLastName("Lovelace");
		testClone.setCohort("Java-May-1845");
		
		//act and assert
		Student shouldBeAda = service.getStudent("0001");
		assertNotNull(shouldBeAda, "gettint 0001 should not be null");
		assertEquals(testClone, shouldBeAda, "student stored under 0001 should be Ada");
		
		Student shouldBeNull = service.getStudent("0042");
		assertNull(shouldBeNull, "getting 0042 should be null");
		
	}
	
	@Test
	public void testRemoveStudent() throws Exception {
		//arrange
		Student testClone = new Student("0001");
		testClone.setFirstName("Ada");
		testClone.setLastName("Lovelace");
		testClone.setCohort("Java-May-1845");
		
		//act and assert
		Student shouldBeAda = service.removeStudent("0001");
		assertNotNull(shouldBeAda, "removing 0001 should not be null");
		assertEquals(testClone, shouldBeAda, "student removed from 0001 should be ada");
		
		Student shouldBeNull = service.removeStudent("0042");
		assertNull(shouldBeNull, "removing 0042 should be null");
	}
	


}
