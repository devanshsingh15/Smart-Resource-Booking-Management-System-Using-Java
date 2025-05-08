package project.SRBMS.entity;

import java.util.Scanner;

import project.SRBMS.Driver.DriverClass;
import project.SRBMS.service.*;

@SuppressWarnings("resource")
public class ResourceMan extends User{
	
	public ResourceMan(String id, String pass, String name) {
		super(id, pass, name);
	}
	
	public ResourceMan(String id, String pass, String name, String email) {
		super(id, pass, name, email);
	}

	@Override
	public void showMenu() throws InterruptedException {
		System.out.println("-----------------------------------------------------------------------------------");
		Thread.sleep(90);
		System.out.println("******************************WELCOME RESOURCE MANAGER*****************************");
		Thread.sleep(90);
		System.out.println("|  addroom-> Add a Room                 |  editroom-> Edit a Room                 |");
		Thread.sleep(90);
		System.out.println("|  deleteroom-> Delete a Room           |  showrooms-> Show All Rooms             |");
		Thread.sleep(90);
		System.out.println("|  showbooked-> Show Booked Room(s)     |  showavail-> Show Available Rooms       |");
		Thread.sleep(90);
		System.out.println("|  signout-> Sign Out                   |  help-> Show Features List              |");
		Thread.sleep(90);
		System.out.println("***********************************************************************************");
		Thread.sleep(90);
		
		Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
        	System.out.println("-----------------------------------------------------------------------------------");
        	Thread.sleep(90);
            System.out.print("Enter your choice: ");
            Thread.sleep(90);
            
            try {
            	String choice = sc.nextLine();
            	if(choice.equals("addroom")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		Thread.sleep(90);
            		System.out.print("Enter room id: ");
            		String id = sc.nextLine();
            		Thread.sleep(90);
            		System.out.print("Enter room name: ");
            		String name = sc.nextLine();
            		Thread.sleep(90);
            		System.out.print("Enter room type: ");
            		String type = sc.nextLine();
            		Thread.sleep(90);
            		
            		double cost;
                	while(true) {                		
                		System.out.print("Enter room cost/hour: ");
                		cost = sc.nextDouble();
                		sc.nextLine();          
                		Thread.sleep(90);
                		
                    	if(cost < 0.0) {
                    		System.out.println("-----------------------------------------------------------------------------------");
                    		Thread.sleep(90);
                    		System.out.println("Invalid cost. Try again!!!");
                    		Thread.sleep(90);
                    	} else break;
                	}            		
            		Room room = new Room(id, name, type, cost);
            		ResourceService.addRoom(room);
            	}
            	else if(choice.equals("editroom")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		Thread.sleep(90);
            		System.out.print("Enter room id you want to edit: ");
            		String id2 = sc.nextLine();
            		Thread.sleep(90);
            		System.out.print("What do you want to edit(1:cost): ");
            		int ch3 = sc.nextInt();
            		sc.nextLine();
            		Thread.sleep(90);
            		if(ch3 == 1) {
            			System.out.println("-----------------------------------------------------------------------------------");
            			Thread.sleep(90);
                		double cost2;
                    	while(true) {                		
                    		System.out.print("Enter new room cost/hour: ");
                    		cost2 = sc.nextDouble();
                    		sc.nextLine();      
                    		Thread.sleep(90);
                        	if(cost2 < 0.0) {
                        		System.out.println("-----------------------------------------------------------------------------------");
                        		Thread.sleep(90);
                        		System.out.println("Invalid cost. Try again!!!");
                        		Thread.sleep(90);
                        	} else break;
                    	}     
                		ResourceService.editRoom(id2, cost2);
            		}
            		else {
            			System.out.println("-----------------------------------------------------------------------------------");
            			Thread.sleep(90);
            			System.out.println("Wrong choice.");
            			Thread.sleep(90);
            		}
            	}
            	else if(choice.equals("deleteroom")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		Thread.sleep(90);
            		System.out.print("Enter room id you want to delete: ");
            		String id3 = sc.nextLine();
            		Thread.sleep(90);
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
            		System.out.println("-----------------------------------------------------------------------------------");
            		Thread.sleep(90);
            		System.out.println("******************************WELCOME RESOURCE MANAGER*****************************");
            		Thread.sleep(90);
            		System.out.println("|  addroom-> Add a Room                 |  editroom-> Edit a Room                 |");
            		Thread.sleep(90);
            		System.out.println("|  deleteroom-> Delete a Room           |  showrooms-> Show All Rooms             |");
            		Thread.sleep(90);
            		System.out.println("|  showbooked-> Show Booked Room(s)     |  showavail-> Show Available Rooms       |");
            		Thread.sleep(90);
            		System.out.println("|  signout-> Sign Out                   |  help-> Show Features List              |");
            		Thread.sleep(90);
            		System.out.println("***********************************************************************************");
            		Thread.sleep(90);
            	}
            	else {
            		System.out.println("-----------------------------------------------------------------------------------");
            		Thread.sleep(90);
            		System.out.println("Wrong choice");
            		Thread.sleep(90);
            	}
            	
            	System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
                System.out.print("Do you want to continue(y/n)? ");
                String choice2 = sc.nextLine();
        		Thread.sleep(90);
                if(choice2.equalsIgnoreCase("n")) flag = false;
            } catch(Exception e) {
            	System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
				System.out.println("Invalid input. Please enter correct data type.");
				sc.nextLine(); 
        		Thread.sleep(90);
            }
        }
	}

}
