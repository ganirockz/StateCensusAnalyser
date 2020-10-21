package com.capgemini;

public class IncorrectCSVFileException extends Exception {
	String Imessage;
	public IncorrectCSVFileException(String message) {
		super(message);
		Imessage = message;
	}
}
