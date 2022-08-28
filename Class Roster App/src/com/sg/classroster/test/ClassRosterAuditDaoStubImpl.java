package com.sg.classroster.test;

import com.sg.classroster.dao.ClassRosterAuditDao;
import com.sg.classroster.dao.ClassRosterPersistenceException;

public class ClassRosterAuditDaoStubImpl implements ClassRosterAuditDao {

	@Override
	public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
		// does nothing - is a stub
	}
	

}
