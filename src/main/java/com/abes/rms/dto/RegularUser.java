package com.abes.rms.dto;

import java.util.ArrayList;

@SuppressWarnings("resource")
public class RegularUser extends User{
	
	public ArrayList<Room> rooms = new ArrayList<Room>();
	
	public RegularUser(String id, String pass, String name, String email) {
		super(id, pass, name, email);
	}

	@Override
	public String toString() {
		return "Id=" + getId() + ", Name=" + getName()
				+ ", Email=" + getEmail();
	}

	
	
	
	
}
