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
	public void showMenu() {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("******************************WELCOME ADMIN**********************************");
		System.out.println("|  showusers-> Show All Users        |  viewbookings-> View All Bookings    |");
		System.out.println("|  showrooms-> Show All Rooms        |  reports-> Generate Reports          |");
		System.out.println("|  signout-> Sign Out                |  help-> Show Features                |");
		System.out.println("*****************************************************************************");
		
        boolean flag = true;
        while(flag) {
    		System.out.println("-----------------------------------------------------------------------------");
            System.out.print("Enter your choice: ");
			Scanner sc = new Scanner(System.in);
            
            String choice = sc.nextLine();
            if(choice.equalsIgnoreCase("showusers")) {
        		Database.showUsers();
        	}
        	else if(choice.equalsIgnoreCase("viewbookings")) {
        		BookingService.showAllBookings();
        	}
        	else if(choice.equalsIgnoreCase("showrooms")) {
        		Database.showAllRooms();
        	}
        	else if(choice.equalsIgnoreCase("reports")) {
        		ReportService.maxBookedRoom();
        		System.out.println("Total bookings: " + ReportService.totalBooking());
        		System.out.println("Total Revenue: " + ReportService.revenue());
        	}
        	else if(choice.equalsIgnoreCase("signout")) {
        		String[] temp3 = {""};
            	DriverClass.main(temp3);
        	}
        	else if(choice.equalsIgnoreCase("help")) {
        		System.out.println("-----------------------------------------------------------------------------");
        		System.out.println("******************************WELCOME ADMIN**********************************");
        		System.out.println("|  showusers-> Show All Users        |  viewbookings-> View All Bookings    |");
        		System.out.println("|  showrooms-> Show All Rooms        |  reports-> Generate Reports          |");
        		System.out.println("|  signout-> Sign Out                |  help-> Show Features List           |");
        		System.out.println("*****************************************************************************");
        	}
        	else {
        		System.out.println("-----------------------------------------------------------------------------");
        		System.out.println("Wrong choice");
        	}
    		System.out.println("-----------------------------------------------------------------------------");
            System.out.print("Do you want to continue(y/n)? ");
            String choice2 = sc.next();
            if(choice2.equals("n")) flag = false;
        }
	}
}
