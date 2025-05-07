package project.SRBMS.entity;

import java.util.ArrayList;

import java.util.Scanner;

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
	public void showMenu() {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("*******************************WELCOME USER*******************************");
		System.out.println("|  showrooms-> Show Available Rooms  |  book-> Book a Room               |");
		System.out.println("|  cancel-> Cancel a Room            |  showbooked-> Show Booked Room(s) |");
		System.out.println("|  addtocart-> Add to Cart           |  showcart-> Show Cart             |");
		System.out.println("|  edit-> Edit account details       |  showdetails-> Show User Details  |");
		System.out.println("|  signout-> Sign Out                |  help-> Show Features List        |");
		System.out.println("**************************************************************************");
		
		Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
    		System.out.println("--------------------------------------------------------------------------");
            System.out.print("Enter your choice: ");
            
            try {
            	String choice = sc.nextLine();
            	if(choice.equalsIgnoreCase("showrooms")) {
            		UserService.showAvailRooms();
            	}
            	else if(choice.equalsIgnoreCase("book")) {
            		System.out.println("--------------------------------------------------------------------------");
            		System.out.print("Enter room ID: ");
                	String id = sc.nextLine();
                	System.out.print("Enter duration in hours: ");
                	double time = sc.nextDouble();
                	sc.nextLine();
                	Room temp = UserService.getRoom(id);
                	if(temp != null) {
                		UserService.bookRoom(id, this, time);
                	}
                	else {
                		System.out.println("No such room available!!!");
                	}        
            	}
            	else if(choice.equalsIgnoreCase("cancel")) {
            		System.out.println("--------------------------------------------------------------------------");
            		System.out.print("Enter room ID: ");
                    String id2 = sc.nextLine();
                    Room temp2 = UserService.getRoom(id2);
                	UserService.cancelRoom(id2, this, temp2);
            	}
            	else if(choice.equalsIgnoreCase("showbooked")) {
            		UserService.showBookedRooms(this);
            	}
            	else if(choice.equalsIgnoreCase("addtocart")) {
            		System.out.println("--------------------------------------------------------------------------");
            		System.out.print("Enter room ID: ");
                    String id3 = sc.nextLine();
                    System.out.print("Enter duration in hours: ");
                	double time2 = sc.nextDouble();
                	sc.nextLine();
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
            		System.out.println("--------------------------------------------------------------------------");
            		System.out.println("*******************************WELCOME USER*******************************");
            		System.out.println("|  showrooms-> Show Available Rooms  |  book-> Book a Room               |");
            		System.out.println("|  cancel-> Cancel a Room            |  showbooked-> Show Booked Room(s) |");
            		System.out.println("|  addtocart-> Add to Cart           |  showcart-> Show Cart             |");
            		System.out.println("|  signout-> Sign Out                |  help-> Show Features List        |");
            		System.out.println("**************************************************************************");
            	}
            	else {
            		System.out.println("--------------------------------------------------------------------------");
            		System.out.println("Wrong choice");
            	}
            		
        		System.out.println("--------------------------------------------------------------------------");
                System.out.print("Do you want to continue(y/n)? ");
                String choice2 = sc.nextLine();
                if(choice2.equals("n")) flag = false; 
            } catch(Exception e) {
				System.out.println("--------------------------------------------------------------------------");
				System.out.println("Invalid input. Please enter correct data type.");
				sc.nextLine();
            }
        }
	}
	
}
