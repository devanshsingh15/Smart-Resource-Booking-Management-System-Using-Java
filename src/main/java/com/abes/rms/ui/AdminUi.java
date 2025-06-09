package com.abes.rms.ui;

import java.util.*; 

import com.abes.rms.App;
import com.abes.rms.dto.*;
import com.abes.rms.service.*;
import com.abes.rms.util.*;

public class AdminUi {
	AdminService adminService = new AdminService();
	ReportService reportService = new ReportService();
	
	Scanner sc = new Scanner(System.in);
	public void entry() {
	
		while(true) {
			System.out.println("-----------------------------------------------------------------------------------");
	    	System.out.print("Enter your username/ID: ");
			String userID = sc.next();
			System.out.print("Enter your password: ");
			String pass = sc.next();
			if(adminService.verifyUser(userID, pass)) {
				System.out.println("-----------------------------------------------------------------------------------");
				System.out.println("Admin Signed In Successfully");
				Admin admin = adminService.getUser(userID, pass);
				showMenu(admin);
				break;
			} else {
				System.out.println("-----------------------------------------------------------------------------------");
				System.out.println("Invalid credentials/Admin not registered. Try again.");
				System.out.println("-----------------------------------------------------------------------------------");
			}
		}
	}
	
	private void showMenu(Admin admin) {
		printMenu();
        boolean flag = true;
        while(flag) {
        	System.out.println("-----------------------------------------------------------------------------------");
            System.out.print("Enter your choice: ");
			Scanner sc = new Scanner(System.in);
			
            String choice = sc.nextLine();
            if(choice.equalsIgnoreCase("showusers")) {
            	System.out.println("-----------------------------------------------------------------------------------");
            	ArrayList<RegularUser> users = adminService.showAllUsers();
            	if(users.isEmpty()) System.out.println("No Users registered yet.");
            	else {
            		for(RegularUser user : users) System.out.println(user);
            	}
        	}
        	else if(choice.equalsIgnoreCase("viewbookings")) {
        		ArrayList<Booking> bookings = adminService.showAllBookings();
        		if(bookings.isEmpty()) System.out.println("No Bookings done yet.");
            	else {
            		for(Booking booking : bookings) System.out.println(booking);
            	}
        	}
        	else if(choice.equalsIgnoreCase("showrooms")) {
        		ArrayList<Room> rooms = new ManagerService().showAllRooms();
        		for(Room room : rooms) System.out.println(room);
        	}
            
        	else if(choice.equalsIgnoreCase("reports")) {
        		System.out.println("-----------------------------------------------------------------------------------");
        		Room temp = reportService.maxBookedRoom();
        		if(temp != null) {
        			System.out.println("Maximum Booked Room: room " + temp.getId() + ", " + temp.getCount() + " times.");
        		} else {
        			System.out.println("no rooms booked yet");
        		}
        		System.out.println("-----------------------------------------------------------------------------------");
        		System.out.println("Total bookings: " + reportService.totalBooking());
        		System.out.println("-----------------------------------------------------------------------------------");
        		System.out.println("Total Revenue: " + reportService.revenue());
        		System.out.println("-----------------------------------------------------------------------------------");
        	}
        	else if(choice.equalsIgnoreCase("deleteuser")) {
        		System.out.println("-----------------------------------------------------------------------------------");
        		System.out.print("Enter User ID of User: ");
        		String id = sc.nextLine();
        		System.out.println("-----------------------------------------------------------------------------------");
        		System.out.print("Enter email of User: ");
        		String email = sc.nextLine();
        		System.out.println("-----------------------------------------------------------------------------------");
            	
        		boolean isBook = false;
        		RegularUser user = new UserService().getUserByEmail(id, email);
        		if(user != null) {
        			for(Booking b : new AdminService().showAllBookings()) {
            			if(b.getUser().getId().equals(user.getId())) {
            				isBook = true;
            				break;
            			}
            		}
            		if(!isBook) {
            			adminService.deleteUser(user);
                		System.out.println("-----------------------------------------------------------------------------------");
                		System.out.println("User Removed!!!");
            		} else {
                		System.out.println("-----------------------------------------------------------------------------------");
            			System.out.println("User have bookings!!!");
            		}
        		} else {
            		System.out.println("-----------------------------------------------------------------------------------");
        			System.out.println("User not present!!!");
        		}
        	}
        	else if(choice.equalsIgnoreCase("sort")) {
        		System.out.println("-----------------------------------------------------------------------------------");
        		System.out.print("Sort Bookings based on(1-> Price, 2-> Booking id, 3-> Duration): ");
        		int ch = sc.nextInt();
        		sc.nextLine();
        		switch (ch) {
				case 1:
					ArrayList<Booking> sorted = new ArrayList<Booking>(adminService.showAllBookings());
					if(sorted.isEmpty()) {
						System.out.println("No bookings done yet");
					} else {
						Collections.sort(sorted, new SortBookingByCost());
						for(Booking booking : sorted) {
							System.out.println(booking);
						}
					}
					
					break;

				case 2:
					sorted = new ArrayList<Booking>(adminService.showAllBookings());
					if(sorted.isEmpty()) {
						System.out.println("No bookings done yet");
					} else {
						Collections.sort(sorted, new SortBookingById());
						for(Booking booking : sorted) {
							System.out.println(booking);
						}
					}
				
					break;
					
				case 3:
					sorted = new ArrayList<Booking>(adminService.showAllBookings());
					if(sorted.isEmpty()) {
						System.out.println("No bookings done yet");
					} else {
						Collections.sort(sorted, new SortBookingByDuration());
						for(Booking booking : sorted) {
							System.out.println(booking);
						}
					}
					
					break;
					
				default:
					System.out.println("Wrong Choice.");
				}
        	}
        	else if(choice.equalsIgnoreCase("menu")) {
        		printMenu();
        	}
        	else if(choice.equalsIgnoreCase("signout")) {
        		String[] temp3 = {""};
            	App.main(temp3);
        	}
        	else {
        		System.out.println("-----------------------------------------------------------------------------------");
        		System.out.println("Wrong choice");
        	}
        }
	}
	
	private static void printMenu() {
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("*********************************WELCOME ADMIN*************************************");
		System.out.println("|  showusers-> Show All Users          |  viewbookings-> View All Bookings        |");
		System.out.println("|  showrooms-> Show All Rooms          |  reports-> Generate Reports              |");
		System.out.println("|  sort-> Sort Bookings                |  deleteuser-> Deleter User               |");
		System.out.println("|  menu-> Show Menu                    |  signout-> Sign Out                      |");
		System.out.println("***********************************************************************************");
	}
	

}
