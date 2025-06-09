package com.abes.rms.ui;

import java.util.*;

import com.abes.rms.App;
import com.abes.rms.dto.*;
import com.abes.rms.service.*;

public class ManagerUi {

	ManagerService manService = new ManagerService();
	public void entry() {
		mainMenu();
		
		Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
        	System.out.println("-----------------------------------------------------------------------------------");
            System.out.print("Enter your choice: ");
            
            String choice = sc.nextLine();
            
            if(choice.equalsIgnoreCase("signup")) {   //user sign up	
        		
            	String name;
        		while(true) {
        			System.out.print("Enter name of Manager: ");
        			name = sc.nextLine();
        			if(!name.matches("^[A-Za-z][A-Za-z\\s'-]{1,49}$")) {
        				System.out.println("-----------------------------------------------------------------------------------");
        		        System.out.println("Invalid name format, try again.");
        		        System.out.println("Name should have atleast length of 2. No special character and digits are allowed.");
        				System.out.println("-----------------------------------------------------------------------------------");
        			} else break;
        		}
        		
        		String email;
        		while(true) {
        			System.out.print("Enter valid email of Manager: ");
        			email = sc.nextLine();
        			if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
        				System.out.println("-----------------------------------------------------------------------------------");
        		        System.out.println("Invalid email format, try again");
        		        System.out.println("-----------------------------------------------------------------------------------");
        		    } else break;
        		}
        		
        		System.out.print("Enter username/ID: ");
        		String id = sc.nextLine();
        		
        		while(true) {
        			System.out.print("Set password(must include lowercase, uppercase, and digit): ");
        			String pass1 = sc.nextLine();
        			if(!pass1.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
        				System.out.println("-----------------------------------------------------------------------------------");
        				System.out.println("Not a valid password, Try again");
        				System.out.println("-----------------------------------------------------------------------------------");
        			    continue;
        			}
        			System.out.print("Confirm password: ");
        			String pass2 = sc.nextLine();
        			
        			if(pass1.equals(pass2)) {
        				boolean isAdd = manService.signUp(id, pass1, name, email);
        				if(!isAdd) {
        					System.out.println("Manager with this id already present");
        				}
        				else break;
        			} else {
        				System.out.println("-----------------------------------------------------------------------------------");
        				System.out.println("password did not match");
        				System.out.println("-----------------------------------------------------------------------------------");
        			}
        		}
        		entry();
        		
        	}
        	else if(choice.equalsIgnoreCase("signin")) {
        		while(true) {
        			System.out.println("-----------------------------------------------------------------------------------");
        	    	System.out.print("Enter your username/ID: ");
        			String userID = sc.nextLine();
        			System.out.print("Enter your password: ");
        			String pass = sc.nextLine();
        			if(manService.verifyUser(userID, pass)) {
        				System.out.println("-----------------------------------------------------------------------------------");
        				System.out.println("Manager Signed In Successfully");
        				ResourceMan manager = manService.getUser(userID, pass);
        				showMenu(manager);
        				break;
        			} else {
        				System.out.println("-----------------------------------------------------------------------------------");
        				System.out.println("Invalid credentials/Manager not registered. Try again.");
        				System.out.println("-----------------------------------------------------------------------------------");
        			}
        		}
        	}
        	else if(choice.equalsIgnoreCase("menu")) {
        		mainMenu();
        	}
        	else if(choice.equalsIgnoreCase("home")) {
        		String[] args = null;
        		App.main(args);
        	}
        	else {
        		System.out.println("-----------------------------------------------------------------------------------");
        		System.out.println("Wrong choice");
        	}
        	mainMenu();
        }
		
		
	}
	
	private void showMenu(ResourceMan manager) {
		
		printMenu();
		
		Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
        	System.out.println("-----------------------------------------------------------------------------------");
            System.out.print("Enter your choice: ");
            
            try {
            	String choice = sc.nextLine();
            	if(choice.equals("addroom")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		System.out.print("Enter room id: ");
            		String id = sc.nextLine();
            		System.out.print("Enter room type: ");
            		String type = sc.nextLine();
            		
            		double cost;
                	while(true) {                		
                		System.out.print("Enter room cost/hour: ");
                		cost = sc.nextDouble();
                		sc.nextLine();
                		
                    	if(cost < 0.0) {
                    		System.out.println("-----------------------------------------------------------------------------------");
                    		System.out.println("Invalid cost. Try again!!!");
                    	} else break;
                	}            		
            		Room room = new Room(id, type, cost);
            		boolean add = manService.addRoom(room);
            		if(add) {
            			System.out.println("Room added.");
            		} else {
            			System.out.println("Room with this id already present.");
            		}
            	}
            	else if(choice.equals("editroom")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		System.out.print("Enter room id you want to edit: ");
            		String id2 = sc.nextLine();
            		System.out.print("What do you want to edit(1:type, 2:cost): ");
            		int ch3 = sc.nextInt();
            		sc.nextLine();
            		if(ch3 == 1) {
            			System.out.println("-----------------------------------------------------------------------------------");
                		String type;
                    	while(true) {                		
                    		System.out.print("Enter new room type(AC/Non-AC): ");
                    		type = sc.nextLine();  
                        	if(!type.equalsIgnoreCase("AC") && !type.equalsIgnoreCase("Non-AC")) {
                        		System.out.println("-----------------------------------------------------------------------------------");
                        		System.out.println("Invalid type. Try again!!!");
                        	} else break;
                    	}     
                		boolean edit = manService.editRoom(id2, type);
                		if(edit) {
                			System.out.println("Type Updated.");
                		} else {
                			System.out.println("Room not present.");
                		}
            		}
            		else if(ch3 == 2) {
            			System.out.println("-----------------------------------------------------------------------------------");
                		double cost2;
                    	while(true) {                		
                    		System.out.print("Enter new room cost/hour: ");
                    		cost2 = sc.nextDouble();
                    		sc.nextLine();  
                        	if(cost2 < 0.0) {
                        		System.out.println("-----------------------------------------------------------------------------------");
                        		System.out.println("Invalid cost. Try again!!!");
                        	} else break;
                    	}     
                		boolean edit = manService.editRoom(id2, cost2);
                		if(edit) {
                			System.out.println("Cost Updated.");
                		} else {
                			System.out.println("Room not present.");
                		}
            		}
            		else {
            			System.out.println("-----------------------------------------------------------------------------------");
            			System.out.println("Wrong choice.");
            		}
            	}
            	
            	else if(choice.equals("deleteroom")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		System.out.print("Enter room id you want to delete: ");
            		String id3 = sc.nextLine();
            		try {
            			boolean delete = manService.deleteRoom(id3);
                		if(delete) {
                			System.out.println("Room Deleted Successfully.");
                		} else {
                			System.out.println("Room have booking");
                		}
            		} catch(Exception e) {
            			System.out.println(e.getMessage());
            		}
            	}
            	
            	else if(choice.equals("showrooms")) {
            		ArrayList<Room> rooms = manService.showAllRooms();
            		if(rooms == null) {
            			System.out.println("No room present.");
            		} else {
            			for(Room room : rooms) {
            				System.out.println(room + "isAvailable: " + room.isAvailable());
            			}
            		}
            	}
            	else if(choice.equals("showbooked")) {
            		ArrayList<Room> rooms = manService.showBookedRooms();
            		if(rooms.isEmpty()) {
            			System.out.println("No room booked.");
            		} else {
            			for(Room room : rooms) {
            				System.out.println(room);
            			}
            		}
            	}
            	else if(choice.equals("showavail")) {
            		ArrayList<Room> rooms = new RoomService().showAvailRooms();
            		if(rooms == null) {
            			System.out.println("No room available.");
            		} else {
            			for(Room room : rooms) {
            				System.out.println(room);
            			}
            		}
            	}
            	else if(choice.equals("signout")) {
            		entry();
            	}
            	else if(choice.equalsIgnoreCase("menu")) {
            		printMenu();
            	}
            	else {
            		System.out.println("-----------------------------------------------------------------------------------");
            		System.out.println("Wrong choice");
            	}
            	
            } catch(Exception e) {
            	System.out.println("-----------------------------------------------------------------------------------");
				System.out.println("Invalid input. Please enter correct data type.");
            }
        }
	}
	
	private static void mainMenu() {
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("***********************************WELCOME TO RMS**********************************");
		System.out.println("|  signup-> Manager Sign Up              |  signin-> Manager Sign In              |");
		System.out.println("|  menu-> Show Menu                      |  home-> Back to Home                   |");
		System.out.println("***********************************************************************************");
	}
	
	private static void printMenu() {
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("******************************WELCOME RESOURCE MANAGER*****************************");
		System.out.println("|  addroom-> Add a Room                 |  editroom-> Edit a Room                 |");
		System.out.println("|  deleteroom-> Delete a Room           |  showrooms-> Show All Rooms             |");
		System.out.println("|  showbooked-> Show Booked Room(s)     |  showavail-> Show Available Rooms       |");
		System.out.println("|  signout-> Sign Out                   |  menu-> Show Menu                       |");
		System.out.println("***********************************************************************************");
	}
}


