package com.sg.classroster.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sg.classroster.dto.Student;

public class ClassRosterDaoFileImpl implements ClassRosterDao {
	
	final Map<String, Student> students = new HashMap<>();

	@Override
	public Student addStudent(String studentID, Student student) {
		return students.put(studentID, student);
	}

	@Override
	public List<Student> getAllStudents() {
		// returns an ArrayList when List return type is declared
		//perfectly fine because of inheritance
		return new ArrayList<Student>(students.values());
	}

	@Override
	public Student getStudent(String studentID) {
		return students.get(studentID);
	}

	@Override
	public Student removeStudent(String studentID) {
		Student student = students.remove(studentID);
		return student;
	}

	
	

}
