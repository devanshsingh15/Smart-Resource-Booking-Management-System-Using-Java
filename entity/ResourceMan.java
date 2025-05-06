package project.SRBMS.entity;

import java.util.Scanner;

import project.SRBMS.Driver.DriverClass;
import project.SRBMS.service.*;

public class ResourceMan extends User{
	
	public ResourceMan(String id, String pass, String name) {
		super(id, pass, name);
	}
	
	public ResourceMan(String id, String pass, String name, String email) {
		super(id, pass, name, email);
	}

	@Override
	public void showMenu() {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("**************************WELCOME RESOURCE MANAGER************************");
		System.out.println("|  addroom-> Add a Room             |  editroom-> Edit a Room            |");
		System.out.println("|  deleteroom-> Delete a Room       |  showrooms-> Show All Rooms        |");
		System.out.println("|  showbooked-> Show Booked Room(s) |  showavail-> Show Available Rooms  |");
		System.out.println("|  signout-> Sign Out               |  help-> Show Features List         |");
		System.out.println("**************************************************************************");
		
		Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
    		System.out.println("--------------------------------------------------------------------------");
            System.out.print("Enter your choice: ");
            
            try {
            	String choice = sc.nextLine();
            	if(choice.equals("addroom")) {
            		System.out.println("--------------------------------------------------------------------------");
            		System.out.println("Enter room id: ");
            		String id = sc.nextLine();
            		System.out.println("Enter room name: ");
            		String name = sc.nextLine();
            		System.out.println("Enter room type: ");
            		String type = sc.nextLine();
            		System.out.println("Enter room cost/hour: ");
            		double cost = sc.nextDouble();
            		sc.nextLine();
            		Room room = new Room(id, name, type, cost);
            		ResourceService.addRoom(room);
            	}
            	else if(choice.equals("editroom")) {
            		System.out.println("--------------------------------------------------------------------------");
            		System.out.println("Enter room id you want to edit: ");
            		String id2 = sc.nextLine();
            		System.out.println("What do you want to edit(1:cost): ");
            		int ch3 = sc.nextInt();
            		sc.nextLine();
            		if(ch3 == 1) {
            			System.out.println("Enter new cost/hour: ");
                		double cost2 = sc.nextDouble();
                		sc.nextLine();
                		ResourceService.editRoom(id2, cost2);
            		}
            		else System.out.println("Wrong choice.");
            	}
            	else if(choice.equals("deleteroom")) {
            		System.out.println("--------------------------------------------------------------------------");
            		System.out.println("Enter room id you want to delete: ");
            		String id3 = sc.nextLine();
            		ResourceService.deleteRoom(id3);
            	}
            	else if(choice.equals("showrooms")) {
            		ResourceService.showAllRooms();
            	}
            	else if(choice.equals("showbooked")) {
            		ResourceService.showBookedRooms();
            	}
            	else if(choice.equals("showavail")) {
                	UserService.showAvailRooms();
            	}
            	else if(choice.equals("signout")) {
            		String[] temp3 = {""};
                	DriverClass.main(temp3);
            	}
            	else if(choice.equalsIgnoreCase("help")) {
            		System.out.println("--------------------------------------------------------------------------");
            		System.out.println("**************************WELCOME RESOURCE MANAGER************************");
            		System.out.println("|  addroom-> Add a Room             |  editroom-> Edit a Room            |");
            		System.out.println("|  deleteroom-> Delete a Room       |  showrooms-> Show All Rooms        |");
            		System.out.println("|  showbooked-> Show Booked Room(s) |  showavail-> Show Available Rooms  |");
            		System.out.println("|  signout-> Sign Out               |  help-> Show Features List         |");
            		System.out.println("**************************************************************************");
            	}
            	else {
            		System.out.println("--------------------------------------------------------------------------");
            		System.out.println("Wrong choice");
            	}
            	
                System.out.print("Do you want to continue(y/n)? ");
                String choice2 = sc.nextLine();
                if(choice2.equalsIgnoreCase("n")) flag = false;
            } catch(Exception e) {
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("Invalid input. Please enter correct data type.");
				sc.nextLine(); 
            }
        }
		sc.close();
	}

}
