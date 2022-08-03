package com.sg.classroster.dao;

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
		throw new UnsupportedOperationException("Not supported yet");
	}

	@Override
	public Student getStudent(String studentID) {
		throw new UnsupportedOperationException("Not supported yet");
	}

	@Override
	public Student removeStudent(String studentID) {
		throw new UnsupportedOperationException("Not supported yet");
	}

	
	

}
