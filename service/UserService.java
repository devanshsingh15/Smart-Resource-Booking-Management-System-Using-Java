package project.SRBMS.service;

import java.util.*;
import project.SRBMS.entity.*;
import project.SRBMS.repository.*;

@SuppressWarnings("resource")
public class UserService {
	
	public static void signUp() throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------------------------------------------------------------------");
		Thread.sleep(90);
		
		String name;
		while(true) {
			System.out.print("Enter name of user: ");
			name = sc.nextLine();
    		Thread.sleep(90);
			if(!name.matches("^[A-Za-z][A-Za-z\\s'-]{1,49}$")) {
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
		        System.out.println("Invalid name format, try again.");
        		Thread.sleep(90);
		        System.out.println("Name should have atleast length of 2. No special character and digits are allowed.");
        		Thread.sleep(90);
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
			} else break;
		}
		
		String email;
		while(true) {
			System.out.print("Enter valid email of user(optional): ");
			email = sc.nextLine();
    		Thread.sleep(90);
			if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
		        System.out.println("Invalid email format, try again");
        		Thread.sleep(90);
		        System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
		    } else break;
		}
		
		System.out.print("Enter username/ID: ");
		String id = sc.nextLine();
		Thread.sleep(90);
		
		while(true) {
			System.out.print("Set password(must include lowercase, uppercase, and digit): ");
			String pass1 = sc.nextLine();
    		Thread.sleep(90);
			if(!pass1.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
				System.out.println("Not a valid password, Try again");
        		Thread.sleep(90);
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
			    continue;
			}
			System.out.print("Confirm password: ");
			String pass2 = sc.nextLine();
    		Thread.sleep(90);
			
			if(pass1.equals(pass2)) {
				UserService.addUser(id, pass1, name, email);
				break;
			} else {
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
				System.out.println("password did not match");
        		Thread.sleep(90);
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
			}
		}
		
		
	}

	public static void signIn() throws InterruptedException {
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("-----------------------------------------------------------------------------------");
    		Thread.sleep(90);
	    	System.out.print("Enter your username/ID: ");
			String userID = sc.next();
    		Thread.sleep(90);
			System.out.print("Enter your password: ");
			String pass = sc.next();
    		Thread.sleep(90);
			if(UserService.verifyUser(userID, pass)) {
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
				System.out.println("User Signed In Successfully");
        		Thread.sleep(90);
				RegularUser user = UserService.getUser(userID, pass);
				user.showMenu();
				break;
			} else {
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
				System.out.println("Invalid credentials/User not registered. Try again.");
        		Thread.sleep(90);
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
			}
		}
	}
	
	public static void addUser(String id, String pass, String name, String email) throws InterruptedException {
		for (RegularUser u : Database.users) {
			if (u != null && u.id.equals(id)) {
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
				System.out.println("User with this ID already exists!");
        		Thread.sleep(90);
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
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
		System.out.println("-----------------------------------------------------------------------------------");
		Thread.sleep(90);
		System.out.println("User Added successfully");
		Thread.sleep(90);
		System.out.println("-----------------------------------------------------------------------------------");
		Thread.sleep(90);
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
	
	public static RegularUser getUserbyEmail(String userID, String email) {
		for(RegularUser user : Database.users) {
			if(user.id.equals(userID) && user.email.equals(email)) return user;
		}
		return null;
	}
	
	public static void showAvailRooms() throws InterruptedException {
		boolean present = false;
		for(Room room : Database.rooms.keySet()) {
			if(Database.rooms.get(room)) {
				System.out.println(room);
				present = true;
			}
		}
		if(!present) {
			System.out.println("-----------------------------------------------------------------------------------");
    		Thread.sleep(90);
			System.out.println("No rooms are available!!!");
    		Thread.sleep(90);
		}
	}

	public static void bookRoom(String id, RegularUser user, double time) throws InterruptedException {
		for(Room room : Database.rooms.keySet()) {
			if(room.id.equals(id) && !Database.rooms.get(room)) {
				System.out.println("room not available");
        		Thread.sleep(90);
				return;  
			}
			if(room.id.equals(id)) {
				Database.rooms.put(room, false);
				double cost = time * room.costPerHour;
				Booking booking = new Booking(user, room, time, cost);
				Database.bookRecord.add(booking);
				user.rooms.add(room);
				room.count++;
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
				System.out.println("Room Booked");
        		Thread.sleep(90);
			}
		}
	}

	public static void cancelRoom(String id, RegularUser user, Room r) throws InterruptedException {
		boolean present = false;
		for(Room rm : user.rooms) {
			if(rm.id.equalsIgnoreCase(id)) {
				Database.rooms.put(rm, true);
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
				System.out.println("Room Cancelled");
        		Thread.sleep(90);
				present = true;
			}
		}
		if(!present) {
			System.out.println("-----------------------------------------------------------------------------------");
    		Thread.sleep(90);
			System.out.println("No room booked with given id!!!");
    		Thread.sleep(90);
		}
		if(present) user.rooms.remove(r);
		
//		for(Room room : Database.rooms.keySet()) {
//			if(room.id.equals(id)) {
//				if(!Database.rooms.get(room)) {
//					Database.rooms.put(room, true);
//					System.out.println("--------------------------------------------------------------------------");
//					System.out.println("Room Cancelled");
//				}
//				
//				return;
//			} 
//		}
		
//		Iterator<Booking> iter = Database.bookRecord.iterator();
//		while (iter.hasNext()) {
//		    Booking booking = iter.next();
//		    if (booking.room.id.equals(id)) {
//		        iter.remove();
//		        break;
//		    }
//		}
	}

	public static void showBookedRooms(RegularUser user) throws InterruptedException {
		if (user.rooms.isEmpty()) {
			System.out.println("-----------------------------------------------------------------------------------");
    		Thread.sleep(90);
	        System.out.println("No rooms booked yet.");
    		Thread.sleep(90);
	        return;
	    }
		for(Room room : user.rooms) {
			System.out.println(room);
		}
	}

	public static void addToCart(RegularUser user,String id3, double time2) throws InterruptedException {
		Room room = ResourceService.getRoom(id3);
		if(room != null) {
			Booking booking = new Booking(user, room, time2, time2*room.costPerHour);
			ArrayList<Booking> temp = Database.cart.get(user);
			if (temp == null) {
		        temp = new ArrayList<>();
		    }
			if(Database.rooms.get(room)) {
				temp.add(booking);
				Database.cart.put(user, temp);
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
				System.out.println("added to cart");
        		Thread.sleep(90);
			}
			else {
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
				System.out.println("Room not available");
        		Thread.sleep(90);
			}
		} else {
			System.out.println("-----------------------------------------------------------------------------------");
    		Thread.sleep(90);
			System.out.println("No such room present");
    		Thread.sleep(90);
		}
	}

	public static void showCart(RegularUser user) throws InterruptedException {
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
		System.out.println("-----------------------------------------------------------------------------------");
		Thread.sleep(90);
		System.out.println("Total cart cost: " + cartCost);
		Thread.sleep(90);
	}

	public static void edit(RegularUser user) throws InterruptedException {
		System.out.println("-----------------------------------------------------------------------------------");
		Thread.sleep(90);
		System.out.print("What you want to edit(name/email/userID/pass)? ");
		Thread.sleep(90);
		Scanner sc = new Scanner(System.in);
		String ch = sc.nextLine();
		
		if(ch.equalsIgnoreCase("name")) {
			System.out.println("-----------------------------------------------------------------------------------");
    		Thread.sleep(90);
			System.out.print("Enter new name of user: ");
			String name = sc.nextLine();
    		Thread.sleep(90);
			user.name = name;
			System.out.println("-----------------------------------------------------------------------------------");
    		Thread.sleep(90);
			System.out.println("Name updated");
    		Thread.sleep(90);
			return;
		}
		
		else if(ch.equalsIgnoreCase("email")) {
			System.out.println("-----------------------------------------------------------------------------------");
    		Thread.sleep(90);
			System.out.print("Enter valid email of user: ");
			String email = sc.nextLine();
    		Thread.sleep(90);
			if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
		        System.out.println("Invalid email format.");
        		Thread.sleep(90);
		        System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
		        return;
		    } else {
		    	user.email = email;
		    	System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
				System.out.println("email updated");
        		Thread.sleep(90);
				return;
		    }
		}
		
		else if(ch.equalsIgnoreCase("userid")) {
			System.out.println("-----------------------------------------------------------------------------------");
    		Thread.sleep(90);
			System.out.print("Enter new username/ID: ");
			String id = sc.nextLine();
    		Thread.sleep(90);
			user.id = id;
			System.out.println("-----------------------------------------------------------------------------------");
    		Thread.sleep(90);
			System.out.println("User ID updated");
    		Thread.sleep(90);
			return;
		}
		
		else if(ch.equalsIgnoreCase("pass")) {
			System.out.println("-----------------------------------------------------------------------------------");
    		Thread.sleep(90);
			System.out.print("Enter Old Passwrod: ");
			String oldPass = sc.nextLine();
    		Thread.sleep(90);
			if(oldPass.equals(user.pass)) {
				System.out.print("Set new password(must include lowercase, uppercase, and digit): ");
				String pass1 = sc.nextLine();
        		Thread.sleep(90);
				if(!pass1.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
					System.out.println("-----------------------------------------------------------------------------------");
            		Thread.sleep(90);
					System.out.println("Not a valid password(must include lowercase, uppercase, and digit).");
            		Thread.sleep(90);
					System.out.println("-----------------------------------------------------------------------------------");
            		Thread.sleep(90);
				    return;
				}
				System.out.print("Confirm new password: ");
				String pass2 = sc.nextLine();
        		Thread.sleep(90);
				if(pass1.equals(pass2)) {
					user.pass = pass1;
				} else {
					System.out.println("-----------------------------------------------------------------------------------");
            		Thread.sleep(90);
					System.out.println("password did not match");
            		Thread.sleep(90);
					System.out.println("-----------------------------------------------------------------------------------");
            		Thread.sleep(90);
				}
			} else {
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
				System.out.println("Wrong Password");
        		Thread.sleep(90);
				System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
			}
		}
		
		else {
			System.out.println("-----------------------------------------------------------------------------------");
    		Thread.sleep(90);
    		System.out.println("Wrong choice");
    		Thread.sleep(90);
		}
	}

	public static void show(RegularUser user) throws InterruptedException {
		System.out.println("-----------------------------------------------------------------------------------");
		Thread.sleep(90);
		System.out.println(user);
	}
}

