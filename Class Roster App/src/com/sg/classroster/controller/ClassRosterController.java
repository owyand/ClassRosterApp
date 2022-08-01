package com.sg.classroster.controller;

import com.sg.classroster.ui.UserIO;
import com.sg.classroster.ui.UserIOConsoleImpl;

public class ClassRosterController {

		private UserIO io = new UserIOConsoleImpl();
		
		public void run() {
			boolean keepGoing = true;
			int menuSelection = 0;
			while(keepGoing) {
				io.print("Main Menu");
				io.print("1. List Students");
				io.print("2. Create New Student");
				io.print("3. View a Student");
				io.print("4. Remove a Student");
				io.print("5. Exit");
				
				menuSelection = io.readInt("Please select from the above choices", 1, 5);
				
				switch (menuSelection) {
				case 1: 
					io.print("LIST STUDENTS");
					break;
				case 2:
					io.print("CREATE STUDENT");
					break;
				case 3:
					io.print("VIEW STUDENT");
					break;
				case 4:
					io.print("REMOVE STUDENT");
					break;
				case 5:
					keepGoing = false;
					break;
				default:
					io.print("UNKNOWN COMMAND");
				}
			}
			io.print("GOOD BYE");
			io.close();
		}
}
