package project.SRBMS.entity;

public class Booking {
	private static int counter = 0;
	public String id;
	public RegularUser user;
	public Room room;
	public double timeSlot;
	public double totalCost;
	
	public Booking(RegularUser user, Room room, double timeSlot, double totalCost) {
		this.id = "" + ++counter;
		this.user = user;
		this.room = room;
		this.timeSlot = timeSlot;
		this.totalCost = totalCost;
	}
	
	public String toString() {
		return "id: " + id + ", userName: " + user.name + ", roomID: " + room.id + ", duration: " + timeSlot + ", Price/hour: " + room.costPerHour;
	}
}
