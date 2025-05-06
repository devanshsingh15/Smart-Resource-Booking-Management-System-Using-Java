package project.SRBMS.service;

import java.util.*;
import project.SRBMS.entity.*;
import project.SRBMS.repository.*;

@SuppressWarnings("resource")
public class UserService {
	
	public static void signUp() {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------------------");
		System.out.print("Enter name of user: ");
		String name = sc.nextLine();
		System.out.print("Enter valid email of user: ");
		String email = sc.nextLine();
		if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			System.out.println("--------------------------------------------------------");
	        System.out.println("Invalid email format.");
			System.out.println("--------------------------------------------------------");
	        return;
	    }
		System.out.print("Enter username/ID: ");
		String id = sc.nextLine();
		System.out.print("Set password(must include lowercase, uppercase, and digit): ");
		String pass1 = sc.nextLine();
		if(!pass1.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
			System.out.println("--------------------------------------------------------");
			System.out.println("Not a valid password(must include lowercase, uppercase, and digit).");
			System.out.println("--------------------------------------------------------");
		    return;
		}
		System.out.print("Confirm password: ");
		String pass2 = sc.next();
		
		if(pass1.equals(pass2)) {
			UserService.addUser(id, pass1, name, email);
		} else {
			System.out.println("--------------------------------------------------------");
			System.out.println("password did not match");
			System.out.println("--------------------------------------------------------");

		}
	}

	public static void signIn() {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------------------");
    	System.out.print("Enter your username/ID: ");
		String userID = sc.next();
		System.out.print("Enter your password: ");
		String pass = sc.next();
		if(UserService.verifyUser(userID, pass)) {
			System.out.println("--------------------------------------------------------");
			System.out.println("User Signed In Successfully");
			RegularUser user = UserService.getUser(userID, pass);
			user.showMenu();
		} else {
    		System.out.println("--------------------------------------------------------");
			System.out.println("Invalid credentials/User not registered");
    		System.out.println("--------------------------------------------------------");
		}
	}
	
	public static void addUser(String id, String pass, String name, String email) {
		for (RegularUser u : Database.users) {
			if (u != null && u.id.equals(id)) {
				System.out.println("-------------------------------------------------------");
				System.out.println("User with this ID already exists!");
				System.out.println("--------------------------------------------------------");
				return;
			}
		}
		RegularUser user;
		if(email.equals("")) {
			user = new RegularUser(id, pass, name);
		} else {
			user = new RegularUser(id, pass, name, email);
		}
		
		Database.users.add(user);
		System.out.println("--------------------------------------------------------");
		System.out.println("User Added successfully");
		System.out.println("--------------------------------------------------------");
		
	}

	public static boolean verifyUser(String userID, String pass) {
		for(RegularUser user : Database.users) {
			if(user.id.equalsIgnoreCase(userID) && user.pass.equalsIgnoreCase(pass)) return true;
		}
		return false;
	}

	public static RegularUser getUser(String userID, String pass) {
		for(RegularUser user : Database.users) {
			if(user.id.equals(userID) && user.pass.equals(pass)) return user;
		}
		return null;
	}
	
	public static void showAvailRooms() {
		for(Room room : Database.rooms.keySet()) {
			if(Database.rooms.get(room)) {
				System.out.println(room);
			}
		}
	}

	public static void bookRoom(String id, RegularUser user, double time) {
		for(Room room : Database.rooms.keySet()) {
			if(room.id.equals(id) && !Database.rooms.get(room)) {
				System.out.println("room not available");
				return;
			}
			if(room.id.equals(id)) {
				Database.rooms.put(room, false);
				double cost = time * room.costPerHour;
				Booking booking = new Booking(user, room, time, cost);
				Database.bookRecord.add(booking);
				user.rooms.add(room);
				room.count++;
        		System.out.println("--------------------------------------------------------------------------");
				System.out.println("Room Booked");
			}
		}
	}
	
	public static Room getRoom(String id) {
		for(Room room : Database.rooms.keySet()) {
			if(room.id.equals(id)) {
				return room;
			}
		}
		return null;
	}

	public static void cancelRoom(String id) {
		
		for(Room room : Database.rooms.keySet()) {
			if(room.id.equals(id)) {
				if(!Database.rooms.get(room)) {
					Database.rooms.put(room, true);
					System.out.println("--------------------------------------------------------------------------");
					System.out.println("Room Cancelled");
				}
				else {
					System.out.println("--------------------------------------------------------------------------");
					System.out.println("Room not booked");
				}
				return;
			} 
		}
		
//		Iterator<Booking> iter = Database.bookRecord.iterator();
//		while (iter.hasNext()) {
//		    Booking booking = iter.next();
//		    if (booking.room.id.equals(id)) {
//		        iter.remove();
//		        break;
//		    }
//		}
	}

	public static void showBookedRooms(RegularUser user) {
		for(Room room : user.rooms) {
			System.out.println(room);
		}
	}

	public static void addToCart(RegularUser user,String id3, double time2) {
		Room room = getRoom(id3);
		if(room != null) {
			Booking booking = new Booking(user, room, time2, time2*room.costPerHour);
			ArrayList<Booking> temp = Database.cart.get(user);
			if (temp == null) {
		        temp = new ArrayList<>();
		    }
			if(Database.rooms.get(room)) {
				temp.add(booking);
				Database.cart.put(user, temp);
				System.out.println("added to cart");
			}
			else {
				System.out.println("Room not available");
			}
		} else {
			System.out.println("No such room present");
		}
		
	}

	public static void showCart(RegularUser user) {
		
		double cartCost = 0;		
		for(RegularUser user1 : Database.cart.keySet()) {
			if(user1.id.equals(user.id)) {
				
				ArrayList<Booking> temp = Database.cart.get(user1);
				for(Booking booking : temp) {
					System.out.println("RoomID: " + booking.room.id + "Total Cost: " + booking.totalCost);
					cartCost += booking.totalCost;
				}
				
			}
		}
		
		System.out.println("Total cart cost: " + cartCost);
	}
	
	
	
}
