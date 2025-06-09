package com.abes.rms.exception;

public class InvalidDurationException extends Exception{
	public InvalidDurationException() {
		super("Please enter a valid Duration!!.");
	}
}
