package project.SRBMS.entity;

import java.util.Scanner;

import project.SRBMS.Driver.DriverClass;
import project.SRBMS.repository.Database;
import project.SRBMS.service.*;

@SuppressWarnings("resource")
public class Admin extends User{

	public Admin(String id, String pass, String name) {
		super(id, pass, name);
	}
	
	public Admin(String id, String pass, String name, String email) {
		super(id, pass, name, email);
	}

	@Override
	public void showMenu() throws InterruptedException {
		System.out.println("-----------------------------------------------------------------------------------");
		Thread.sleep(90);
		System.out.println("*********************************WELCOME ADMIN*************************************");
		Thread.sleep(90);
		System.out.println("|  showusers-> Show All Users          |  viewbookings-> View All Bookings        |");
		Thread.sleep(90);
		System.out.println("|  showrooms-> Show All Rooms          |  reports-> Generate Reports              |");
		Thread.sleep(90);
		System.out.println("|  signout-> Sign Out                  |  deleteuser-> Deleter User               |");
		Thread.sleep(90);
		System.out.println("|  help-> Show Features                |                                          |");
		Thread.sleep(90);
		System.out.println("***********************************************************************************");
		Thread.sleep(90);
		
        boolean flag = true;
        while(flag) {
        	System.out.println("-----------------------------------------------------------------------------------");
        	Thread.sleep(90);
            System.out.print("Enter your choice: ");
			Scanner sc = new Scanner(System.in);
			Thread.sleep(90);
			
            String choice = sc.nextLine();
            if(choice.equalsIgnoreCase("showusers")) {
            	System.out.println("-----------------------------------------------------------------------------------");
            	Thread.sleep(90);
        		Database.showUsers();
        	}
        	else if(choice.equalsIgnoreCase("viewbookings")) {
        		BookingService.showAllBookings();
        	}
        	else if(choice.equalsIgnoreCase("showrooms")) {
        		Database.showAllRooms();
        	}
        	else if(choice.equalsIgnoreCase("reports")) {
        		System.out.println("-----------------------------------------------------------------------------------");
            	Thread.sleep(90);
        		ReportService.maxBookedRoom();
        		System.out.println("-----------------------------------------------------------------------------------");
            	Thread.sleep(90);
        		System.out.println("Total bookings: " + ReportService.totalBooking());
        		System.out.println("-----------------------------------------------------------------------------------");
            	Thread.sleep(90);
        		System.out.println("Total Revenue: " + ReportService.revenue());
        		System.out.println("-----------------------------------------------------------------------------------");
            	Thread.sleep(90);
        	}
        	else if(choice.equalsIgnoreCase("signout")) {
        		String[] temp3 = {""};
            	DriverClass.main(temp3);
        	}
        	else if(choice.equalsIgnoreCase("deleteuser")) {
        		System.out.println("-----------------------------------------------------------------------------------");
            	Thread.sleep(90);
        		System.out.print("Enter User ID of User: ");
        		String id = sc.nextLine();
        		System.out.println("-----------------------------------------------------------------------------------");
            	Thread.sleep(90);
        		System.out.print("Enter email of User: ");
        		String email = sc.nextLine();
        		System.out.println("-----------------------------------------------------------------------------------");
            	Thread.sleep(90);
            	
        		boolean isBook = false;
        		RegularUser user = UserService.getUserbyEmail(id, email);
        		if(user != null) {
        			for(Booking b : Database.bookRecord) {
            			if(b.user.id.equals(user.id)) {
            				isBook = true;
            				break;
            			}
            		}
            		if(!isBook) {
            			Database.users.remove(user);
                		System.out.println("-----------------------------------------------------------------------------------");
                    	Thread.sleep(90);
                		System.out.println("User Removed!!!");
                		Thread.sleep(90);
            		} else {
                		System.out.println("-----------------------------------------------------------------------------------");
                		Thread.sleep(90);
            			System.out.println("User have bookings!!!");
            			Thread.sleep(90);
            		}
        		} else {
            		System.out.println("-----------------------------------------------------------------------------------");
            		Thread.sleep(90);
        			System.out.println("User not present!!!");
        			Thread.sleep(90);
        		}
        	}
        	else if(choice.equalsIgnoreCase("help")) {
        		System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
        		System.out.println("*********************************WELCOME ADMIN*************************************");
        		Thread.sleep(90);
        		System.out.println("|  showusers-> Show All Users          |  viewbookings-> View All Bookings        |");
        		Thread.sleep(90);
        		System.out.println("|  showrooms-> Show All Rooms          |  reports-> Generate Reports              |");
        		Thread.sleep(90);
        		System.out.println("|  signout-> Sign Out                  |  deleteuser-> Deleter User               |");
        		Thread.sleep(90);
        		System.out.println("|  help-> Show Features                |                                          |");
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
            Thread.sleep(90);
            String choice2 = sc.next();
            if(choice2.equals("n")) flag = false;
        }
	}
}
