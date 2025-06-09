package com.abes.rms.ui;

import java.util.*; 

import com.abes.rms.App;
import com.abes.rms.service.*;
import com.abes.rms.util.*;
import com.abes.rms.dto.*;

public class UserUi {
	UserService userService = new UserService();
	RoomService roomService = new RoomService();
	
	Scanner sc = new Scanner(System.in);
	
	public void entry() {
		
		mainMenu();
		
        boolean flag = true;
        while(flag) {
        	System.out.println("-----------------------------------------------------------------------------------");
            System.out.print("Enter your choice: ");
            
            String choice = sc.nextLine();
            
            if(choice.equalsIgnoreCase("signup")) {   //user sign up	
            	
        		String name;
        		while(true) {
        			System.out.print("Enter name of user: ");
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
        			System.out.print("Enter valid email of user: ");
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
        				boolean isAdd = userService.signUp(id, pass1, name, email);
        				if(!isAdd) {
        					System.out.println("User with this id already present");
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
        			if(userService.verifyUser(userID, pass)) {
        				System.out.println("-----------------------------------------------------------------------------------");
        				System.out.println("User Signed In Successfully");
        				RegularUser user = userService.getUser(userID, pass);
        				showMenu(user);
        				break;
        			} else {
        				System.out.println("-----------------------------------------------------------------------------------");
        				System.out.println("Invalid credentials/User not registered. Try again.");
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
        	
        }
		
		
	}

	private void showMenu(RegularUser user) {
		printMenu();
		
        boolean flag = true;
        while(flag) {
        	System.out.println("-----------------------------------------------------------------------------------");
            System.out.print("Enter your choice: ");
            
            try {
            	String choice = sc.nextLine();
            	if(choice.equalsIgnoreCase("showrooms")) {
            		ArrayList<Room> rooms = roomService.showAvailRooms();
            	  
            		if(rooms.isEmpty()) {
            			System.out.println("No room present.");
            		} else {
            			for(Room room : rooms) {
            				System.out.println(room);
            			}
            		}
            	}
            	else if(choice.equalsIgnoreCase("book")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		System.out.print("Enter room ID: ");
                	String id = sc.nextLine();
                	
                	double time;
                	while(true) {
                		System.out.print("Enter duration in hours: ");
                    	time = sc.nextDouble();
                    	sc.nextLine();
                    	
                    	if(time <= 0.0) {
                    		System.out.println("-----------------------------------------------------------------------------------");
                    		System.out.println("Invalid time duration. Try again!!!");
                    	} else break;
                	}
                	Room temp = roomService.getRoom(id);
                	if(temp != null) {
                		boolean present = userService.bookRoom(id, user, time);
                		if(present) System.out.println("Room Booked.");
                		else System.out.println("Room not available");
                	}
                	else {
                		System.out.println("No such room available!!!");
                	}        
            	}
            	
            	else if(choice.equalsIgnoreCase("cancel")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		System.out.print("Enter room ID: ");
                    String id2 = sc.nextLine();
                    
                    Room temp2 = roomService.getRoom(id2);
                	boolean cancel = userService.cancelRoom(id2, user, temp2);
                	if(cancel) System.out.println("Room canceled");
                	else System.out.println("No room booked with given id!!!");
            	}
            	
            	else if(choice.equalsIgnoreCase("showbooked")) {
            		ArrayList<Room> booked = userService.showBookedRooms(user);
            		if(!booked.isEmpty()) {
            			for(Room room : booked) {
            				System.out.println(room);
            			}
            		}
            		else {
            			System.out.println("No booked rooms yet.");
            		}
            	}
            	
            	else if(choice.equalsIgnoreCase("addtocart")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		System.out.print("Enter room ID: ");
                    String id3 = sc.nextLine();
                    
                    double time2;
                	while(true) {
                		System.out.print("Enter duration in hours: ");
                    	time2 = sc.nextDouble();
                    	sc.nextLine();
                    	
                    	if(time2 <= 0.0) {
                    		System.out.println("-----------------------------------------------------------------------------------");
                    		System.out.println("Invalid time duration. Try again!!!");
                    	} else break;
                	}
                	try {
                		boolean add = userService.addToCart(user, id3, time2);
                		if(add) {
                			System.out.println("Added to cart");
                		} else {
                			System.out.println("Room not available.");
                		}
                		
                	} catch (Exception e) {
                		System.out.println(e.getMessage());
                	}	
            	}
            	
            	else if(choice.equalsIgnoreCase("showcart")) {
            		userService.showCart(user);
            		System.out.print("Do you want to make payment(y/n)? ");
            		String isPay = sc.nextLine();
            		if(isPay.equalsIgnoreCase("y")) {
            			boolean makePayment = userService.makePayment(user);
            			if(makePayment) {
            				System.out.println(".");
            				Thread.sleep(90);
            				System.out.println("..");
            				Thread.sleep(90);
            				System.out.println("....");
            				Thread.sleep(90);
            				System.out.println("All rooms in the Cart are booked.");
            			}
            			else {
            				System.out.println("Room not available.");
            			}
            		}
            		else if(isPay.equalsIgnoreCase("n")) {
            			showMenu(user);
            		} else {
            			System.out.println("Invalid Choice.");
            			showMenu(user);
            		}
            	}
            	else if(choice.equalsIgnoreCase("removecart")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		System.out.print("Enter room ID: ");
                    String id3 = sc.nextLine();
                    
                	try {
                		boolean remove = userService.removeCart(user, id3);
                		if(remove) {
                			System.out.println("Removed cart");
                		} else {
                			System.out.println("Not in cart.");
                		}
                		
                	} catch (Exception e) {
                		System.out.println(e.getMessage());
                	}
            	}
            	else if(choice.equalsIgnoreCase("edit")) {
            		
            		System.out.println("-----------------------------------------------------------------------------------");
            		System.out.print("What you want to edit(name/email/userID/pass)? ");
            		String ch = sc.nextLine();
            		
            		if(ch.equalsIgnoreCase("name")) {
            			System.out.println("-----------------------------------------------------------------------------------");
            			System.out.print("Enter new name of user: ");
            			String name = sc.nextLine();
            			user.setName(name);
            			System.out.println("-----------------------------------------------------------------------------------");
            			System.out.println("Name updated");
            		}
            		
            		else if(ch.equalsIgnoreCase("email")) {
            			System.out.println("-----------------------------------------------------------------------------------");
            			System.out.print("Enter valid email of user: ");
            			String email = sc.nextLine();
            			if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            				System.out.println("-----------------------------------------------------------------------------------");
            		        System.out.println("Invalid email format.");
            		        System.out.println("-----------------------------------------------------------------------------------");
            		    } else {
            		    	user.setEmail(email); 
            		    	System.out.println("-----------------------------------------------------------------------------------");
            				System.out.println("email updated");
            		    }
            		}
            		
            		else if(ch.equalsIgnoreCase("userid")) {
            			System.out.println("-----------------------------------------------------------------------------------");
            			System.out.print("Enter new username/ID: ");
            			String id = sc.nextLine();
            			user.setId(id);
            			System.out.println("-----------------------------------------------------------------------------------");
            			System.out.println("User ID updated");
            			return;
            		}
            		
            		else if(ch.equalsIgnoreCase("pass")) {
            			System.out.println("-----------------------------------------------------------------------------------");
            			System.out.print("Enter Old Passwrod: ");
            			String oldPass = sc.nextLine();
            			if(oldPass.equals(user.getPass())) {
            				System.out.print("Set new password(must include lowercase, uppercase, and digit): ");
            				String pass1 = sc.nextLine();
            				if(!pass1.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            					System.out.println("-----------------------------------------------------------------------------------");
            					System.out.println("Not a valid password(must include lowercase, uppercase, and digit).");
            					System.out.println("-----------------------------------------------------------------------------------");
            				}
            				System.out.print("Confirm new password: ");
            				String pass2 = sc.nextLine();
            				if(pass1.equals(pass2)) {
            					user.setPass(pass1);
            				} else {
            					System.out.println("-----------------------------------------------------------------------------------");
            					System.out.println("password did not match");
            					System.out.println("-----------------------------------------------------------------------------------");
            				}
            			} else {
            				System.out.println("-----------------------------------------------------------------------------------");
            				System.out.println("Wrong Password");
            				System.out.println("-----------------------------------------------------------------------------------");
            			}
            		}
            		else {
            			System.out.println("-----------------------------------------------------------------------------------");
                		System.out.println("Wrong choice");
            		}
            	}
            	else if(choice.equalsIgnoreCase("sort")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		System.out.print("Sort Rooms based on(1-> Price/hour, 2-> Room id): ");
            		int ch = sc.nextInt();
            		sc.nextLine();
            		switch (ch) {
					case 1:
						ArrayList<Room> sorted = new ArrayList<Room>(roomService.showAvailRooms());
						Collections.sort(sorted, new SortRoomByCost());
						for(Room room : sorted) {
							System.out.println(room);
						}
						break;

					case 2:
						sorted = new ArrayList<Room>(roomService.showAvailRooms());
						Collections.sort(sorted, new SortRoomById());
						for(Room room : sorted) {
							System.out.println(room);
						}
						break;
						
					default:
						System.out.println("Wrong Choice.");
					}
            	}
            	else if(choice.equalsIgnoreCase("filter")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		System.out.print("Filter Rooms(1-> AC, 2-> Non-AC): ");
            		int ch = sc.nextInt();
            		sc.nextLine();
            		switch (ch) {
					case 1:
						roomService.showAvailRooms().stream().filter((room) -> "ac".equalsIgnoreCase(room.getType())).forEach(System.out::println);
						break;
						
					case 2:
						roomService.showAvailRooms().stream().filter((room) -> "non-ac".equalsIgnoreCase(room.getType())).forEach(System.out::println);
						break;
						
					default:
						
            		}
            	}
            	else if(choice.equalsIgnoreCase("showdetails")) {
            		System.out.println(user);
            	}
            	else if(choice.equalsIgnoreCase("signout")) {
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
            	System.out.println(e.getMessage());
            	System.out.println("-----------------------------------------------------------------------------------");
				System.out.println("Invalid input. Please enter correct type.");
            }
        }
	}
	
	private static void mainMenu() {
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("***********************************WELCOME TO RMS********************************");
		System.out.println("|  signup-> User Sign Up                 |  signin-> User Sign In                 |");
		System.out.println("|  menu-> Show menu                      |  home-> Back to Home                   |");
		System.out.println("***********************************************************************************");
	}
	
	private static void printMenu() {
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("*********************************WELCOME USER**************************************");
		System.out.println("|  showrooms-> Show Available Rooms    |  book-> Book a Room                      |");
		System.out.println("|  cancel-> Cancel a Room              |  showbooked-> Show Booked Room(s)        |");
		System.out.println("|  addtocart-> Add to Cart             |  showcart-> Show Cart                    |");
		System.out.println("|  removecart-> Remove from Cart       |  showdetails-> Show User Details         |");
		System.out.println("|  edit-> Edit account details         |  sort-> Sort Rooms                       |");
		System.out.println("|  filter-> Filter rooms               |  signout-> Sign Out                      |");
		System.out.println("|                                      |  menu-> Show Menu                        |");
		System.out.println("***********************************************************************************");
	}
}

