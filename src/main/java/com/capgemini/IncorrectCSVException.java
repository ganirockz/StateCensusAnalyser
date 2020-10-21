package com.capgemini;

public class IncorrectCSVException extends Exception {
	String Imessage;
	public IncorrectCSVException(String message) {
		super(message);
		Imessage = message;
	}
}
