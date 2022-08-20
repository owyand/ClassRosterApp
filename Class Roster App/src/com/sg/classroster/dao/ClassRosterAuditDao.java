package com.sg.classroster.dao;

public interface ClassRosterAuditDao {

	//this is it 
	public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;
}
