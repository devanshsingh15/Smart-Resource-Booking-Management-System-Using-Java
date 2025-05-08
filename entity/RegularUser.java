package project.SRBMS.entity;

import java.util.ArrayList;

import java.util.Scanner;

import project.SRBMS.service.ResourceService;
import project.SRBMS.service.UserService;
import project.SRBMS.Driver.*;

@SuppressWarnings("resource")
public class RegularUser extends User{

	public ArrayList<Room> rooms = new ArrayList<Room>();
	
	public RegularUser(String id, String pass, String name) {
		super(id, pass, name);
	}
	
	public RegularUser(String id, String pass, String name, String email) {
		super(id, pass, name, email);
	}

	@Override
	public void showMenu() throws InterruptedException {
		System.out.println("-----------------------------------------------------------------------------------");
		Thread.sleep(90);
		System.out.println("*********************************WELCOME USER**************************************");
		Thread.sleep(90);
		System.out.println("|  showrooms-> Show Available Rooms    |  book-> Book a Room                      |");
		Thread.sleep(90);
		System.out.println("|  cancel-> Cancel a Room              |  showbooked-> Show Booked Room(s)        |");
		Thread.sleep(90);
		System.out.println("|  addtocart-> Add to Cart             |  showcart-> Show Cart                    |");
		Thread.sleep(90);
		System.out.println("|  edit-> Edit account details         |  showdetails-> Show User Details         |");
		Thread.sleep(90);
		System.out.println("|  signout-> Sign Out                  |  help-> Show Features List               |");
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
            	if(choice.equalsIgnoreCase("showrooms")) {
            		UserService.showAvailRooms();
            	}
            	else if(choice.equalsIgnoreCase("book")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		Thread.sleep(90);
            		System.out.print("Enter room ID: ");
                	String id = sc.nextLine();
                	Thread.sleep(90);
                	
                	double time;
                	while(true) {
                		System.out.print("Enter duration in hours: ");
                    	time = sc.nextDouble();
                    	sc.nextLine();
                    	Thread.sleep(90);
                    	
                    	if(time <= 0.0) {
                    		System.out.println("-----------------------------------------------------------------------------------");
                    		Thread.sleep(90);
                    		System.out.println("Invalid time duration. Try again!!!");
                    		Thread.sleep(90);
                    	} else break;
                	}
                	Room temp = ResourceService.getRoom(id);
                	if(temp != null) {
                		UserService.bookRoom(id, this, time);
                	}
                	else {
                		System.out.println("No such room available!!!");
                		Thread.sleep(90);
                	}        
            	}
            	else if(choice.equalsIgnoreCase("cancel")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		Thread.sleep(90);
            		System.out.print("Enter room ID: ");
                    String id2 = sc.nextLine();
                    Thread.sleep(90);
                    
                    Room temp2 = ResourceService.getRoom(id2);
                	UserService.cancelRoom(id2, this, temp2);
            	}
            	else if(choice.equalsIgnoreCase("showbooked")) {
            		UserService.showBookedRooms(this);
            	}
            	else if(choice.equalsIgnoreCase("addtocart")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		Thread.sleep(90);
            		System.out.print("Enter room ID: ");
                    String id3 = sc.nextLine();
                    Thread.sleep(90);
                    
                    double time2;
                	while(true) {
                		System.out.print("Enter duration in hours: ");
                    	time2 = sc.nextDouble();
                    	sc.nextLine();
                    	Thread.sleep(90);
                    	
                    	if(time2 <= 0.0) {
                    		System.out.println("-----------------------------------------------------------------------------------");
                    		Thread.sleep(90);
                    		System.out.println("Invalid time duration. Try again!!!");
                    		Thread.sleep(90);
                    	} else break;
                	}
                	UserService.addToCart(this, id3, time2);
            	}
            	else if(choice.equalsIgnoreCase("showcart")) {
            		UserService.showCart(this);
            	}
            	else if(choice.equalsIgnoreCase("edit")) {
            		UserService.edit(this);
            	}
            	else if(choice.equalsIgnoreCase("showdetails")) {
            		UserService.show(this);
            	}
            	else if(choice.equalsIgnoreCase("signout")) {
            		String[] temp3 = {""};
                	DriverClass.main(temp3);
            	}
            	else if(choice.equalsIgnoreCase("help")) {
            		System.out.println("-----------------------------------------------------------------------------------");
            		Thread.sleep(90);
            		System.out.println("*********************************WELCOME USER**************************************");
            		Thread.sleep(90);
            		System.out.println("|  showrooms-> Show Available Rooms    |  book-> Book a Room                      |");
            		Thread.sleep(90);
            		System.out.println("|  cancel-> Cancel a Room              |  showbooked-> Show Booked Room(s)        |");
            		Thread.sleep(90);
            		System.out.println("|  addtocart-> Add to Cart             |  showcart-> Show Cart                    |");
            		Thread.sleep(90);
            		System.out.println("|  edit-> Edit account details         |  showdetails-> Show User Details         |");
            		Thread.sleep(90);
            		System.out.println("|  signout-> Sign Out                  |  help-> Show Features List               |");
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
                if(choice2.equals("n")) flag = false; 
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
