package com.sg.classroster.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{

	final private Scanner console = new Scanner(System.in);

	//shortcut for writing to console
	@Override
	public void print(String msg) {
		System.out.println(msg);	
	}

	@Override
	public double readDouble(String msgPrompt) {
		while (true) {
			try {
				return Double.parseDouble(this.readString(msgPrompt));
			} catch (NumberFormatException e) {
				this.print("Input error. Please try again.");
			}
		}
	}

	@Override
	public double readDouble(String msgPrompt, double min, double max) {
		double result;
		do {
			result = readDouble(msgPrompt);
		} while (result < min || result > max);
		
		return result;
	}

	@Override
	public float readFloat(String msgPrompt) {
		while (true) {
			try {
				return Float.parseFloat(this.readString(msgPrompt));
			} catch (NumberFormatException e) {
				this.print("Input error. Please try again.");
			}
		}
	}

	@Override
	public float readFloat(String msgPrompt, float min, float max) {
		float result;
		do {
			result = readFloat(msgPrompt);
		} while (result < min || result > max);
		
		return result;
	}

	@Override
	public int readInt(String msgPrompt) {
		boolean invalidInput = true;
		int num = 0;
		while (invalidInput) {
			try {
				//print msg 
				String stringValue = this.readString(msgPrompt);
				//get input line and try to parse
				num = Integer.parseInt(stringValue);
				
				invalidInput = false;
			} catch (NumberFormatException e) {
				//if it explodes
				this.print("Input error. Please try again");
			}
		}
		return num;
	}

	@Override
	public int readInt(String msgPrompt, int min, int max) {
		int result;
		do {
			result = readInt(msgPrompt);
		} while (result < min || result > max);
		
		return result;
	}

	@Override
	public long readLong(String msgPrompt) {
		while (true) {
			try {
				//return is in the try and the while will break once it executes
				return Long.parseLong(this.readString(msgPrompt));
			} catch (NumberFormatException e) {
				this.print("Input error. Please try again.");
			}
		}
	}

	@Override
	public long readLong(String msgPrompt, long min, long max) {
		long result;
		do {
			result = readLong(msgPrompt);
		} while (result < min || result > max);
		
		return result;
	}

	@Override
	public String readString(String msgPrompt) {
		System.out.println(msgPrompt);
		return console.nextLine();
	}

	@Override
	public void close() {
		this.console.close();
	}
	
	
}
