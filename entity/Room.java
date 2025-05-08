package project.SRBMS.entity;

import java.util.Objects;

import project.SRBMS.repository.Database;

public class Room {
	public int count = 0;
	public String id;
	public String name;
	public String type;
	public double costPerHour;
	
	public Room(String id, String name, String type, double costPerHour) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.costPerHour = costPerHour;
	}
	
	@Override
	public String toString() {
		System.out.println("-----------------------------------------------------------------------------------");
		return "RoomID: " + id + ", Room Name: " + name + ", Type: " + type + ", Cost/hour: " + costPerHour + ", isAvailable: " + Database.rooms.get(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(costPerHour, id, name, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return Double.doubleToLongBits(costPerHour) == Double.doubleToLongBits(other.costPerHour)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(type, other.type);
	}
	
	
}
