package com.abes.rms;

import java.util.*; 
import com.abes.rms.ui.*;

public class App 
{
    @SuppressWarnings("resource")
	public static void main( String[] args ) {
    	printMenu();
    	
		UserUi user = new UserUi();
		ManagerUi manager = new ManagerUi();
		AdminUi admin = new AdminUi();
		
		Scanner sc = new Scanner(System.in);
		
        boolean flag = true;
        while(flag) {
        	System.out.println("-----------------------------------------------------------------------------------");
            System.out.print("Enter your choice: ");
            
            String choice = sc.nextLine();
            
            if(choice.equalsIgnoreCase("customer")) {
            	user.entry();
            }
            else if(choice.equalsIgnoreCase("manager")) {
            	manager.entry();
            }
            else if(choice.equalsIgnoreCase("admin")) {
            	admin.entry();
            }
            else if(choice.equalsIgnoreCase("menu")) {
            	printMenu();
            }
            else if(choice.equalsIgnoreCase("quit")) {
            	System.out.println("-----------------------------------------------------------------------------------");
        		System.out.println("Thank you for using application");
        		System.out.println("-----------------------------------------------------------------------------------");
        		System.exit(0);
            }
            else {
            	System.out.println("-----------------------------------------------------------------------------------");
        		System.out.println("Wrong choice");
            }
        }
    }
    
    private static void printMenu() {
    	System.out.println("-----------------------------------------------------------------------------------");
    	System.out.println("|                              You want to Continue as:                           |");
		System.out.println("|  customer-> Customer      |  manager-> Manager      | admin-> Admin             |");
		System.out.println("|  menu-> Show Menu         |  quit-> Quit            |                           |");
		System.out.println("-----------------------------------------------------------------------------------");
    }
}
