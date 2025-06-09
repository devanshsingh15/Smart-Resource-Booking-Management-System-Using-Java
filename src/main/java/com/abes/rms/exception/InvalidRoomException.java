package com.abes.rms.exception;

public class InvalidRoomException extends Exception{
	public InvalidRoomException() {
		super("Not a Valid Room.");
	}
}
