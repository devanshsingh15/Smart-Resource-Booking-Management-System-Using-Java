package com.abes.rms.dto;

import java.util.Objects;

public class Room {
	private int count = 0;
	private String id;
	private String type;
	private double costPerHour;
	private boolean isAvailable;
	
	public Room(String id, String type, double costPerHour) {
		this.id = id;
		this.type = type;
		this.costPerHour = costPerHour;
		this.isAvailable = true;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getCostPerHour() {
		return costPerHour;
	}

	public void setCostPerHour(double costPerHour) {
		this.costPerHour = costPerHour;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "RoomID: " + id + ", Room Type: " + type + ", Cost/hour: " + costPerHour;
	}

	@Override
	public int hashCode() {
		return Objects.hash(costPerHour, count, id, type);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Room room = (Room) obj;
	    return Objects.equals(getId(), room.getId()); // compare by ID only
	}
	
}