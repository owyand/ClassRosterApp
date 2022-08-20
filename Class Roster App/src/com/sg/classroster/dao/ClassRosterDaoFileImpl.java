package com.sg.classroster.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sg.classroster.dto.Student;

public class ClassRosterDaoFileImpl implements ClassRosterDao {

	final Map<String, Student> students = new HashMap<>();
	public static final String ROSTER_FILE = "roster.txt";
	public static final String DELIMITER = "::";

	@Override
	public Student addStudent(String studentID, Student student) throws ClassRosterPersistenceException {
		loadRoster();
		Student newStudent = students.put(studentID, student);
		writeRoster();
		return newStudent;
	}

	@Override
	public List<Student> getAllStudents() throws ClassRosterPersistenceException {
		// returns an ArrayList when List return type is declared
		// perfectly fine because of inheritance
		loadRoster();
		return new ArrayList<Student>(students.values());
	}

	@Override
	public Student getStudent(String studentID) throws ClassRosterPersistenceException {
		loadRoster();
		return students.get(studentID);
	}

	@Override
	public Student removeStudent(String studentID) throws ClassRosterPersistenceException {
		loadRoster();
		Student student = students.remove(studentID);
		writeRoster();
		return student;
	}

	/**
	 * add a method to unmarshal a line of text into a Student object
	 */

	private Student readStudentFromFile(String studentAsText) {
		/**
		 * Line format: elements[0]::elements[1]::elements[2]::elements[3]
		 * studentID::firstName::lastName::cohort
		 */
		String[] elements = studentAsText.split(DELIMITER);

		Student student = new Student(elements[0]);
		student.setFirstName(elements[1]);
		student.setLastName(elements[2]);
		student.setCohort(elements[3]);

		return student;
	}

	private void loadRoster() throws ClassRosterPersistenceException {
		// create an initial Scanner
		// set to null so it can check if it is open to be closed
		Scanner fromRoster = null;

		try {
			fromRoster = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));

			String currentLine;
			Student currentStudent;

			while (fromRoster.hasNextLine()) {
				currentLine = fromRoster.nextLine();
				currentStudent = readStudentFromFile(currentLine);

				students.put(currentStudent.getStudentID(), currentStudent);
			}
		} catch (FileNotFoundException e) {
			throw new ClassRosterPersistenceException("-_- could not load roster memory into memory.", e);
		} finally {
			if (fromRoster != null) {
				fromRoster.close();
			}
		}
	}

	private String marshalStudent(Student aStudent) {
		String studentAsText = aStudent.getStudentID() + DELIMITER + aStudent.getFirstName() + DELIMITER
				+ aStudent.getLastName() + DELIMITER + aStudent.getCohort();
		return studentAsText;
	}

	public void writeRoster() throws ClassRosterPersistenceException {
		PrintWriter out = null;

		try {
			out = new PrintWriter(new FileWriter(ROSTER_FILE));
			String studentAsText;
			List<Student> studentList = new ArrayList<>(students.values());
			for (Student currentStudent : studentList) {
				studentAsText = marshalStudent(currentStudent);
				out.println(studentAsText);
				// forces PrintWriter to write line for line exactly
				out.flush();
			}
		} catch (IOException e) {
			throw new ClassRosterPersistenceException("Could not save student data.", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
