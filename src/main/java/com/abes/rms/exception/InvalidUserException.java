package com.abes.rms.exception;

public class InvalidUserException extends Exception{
	public InvalidUserException() {
		super("Not a Valid User.");
	}
}
