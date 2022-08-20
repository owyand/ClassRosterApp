package com.sg.classroster.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ClassRosterAuditDaoFileImpl implements ClassRosterAuditDao {
	
	public static final String AUDIT_FILE = "audit.txt";

	@Override
	public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
		PrintWriter out;
		
		try {
			//append mode has the boolean on the end - when true will append to existing file
			out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
		} catch (IOException e) {
			throw new ClassRosterPersistenceException("Could not persist audit information", e);
		}
		
		LocalDateTime timestamp = LocalDateTime.now();
		//to file
		out.println(timestamp.toString() + " : " + entry);
		
		//not specified in code along
		out.close();
	}
}
