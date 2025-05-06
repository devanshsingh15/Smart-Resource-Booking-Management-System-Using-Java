package project.SRBMS.Driver;

import java.util.*;

import project.SRBMS.entity.Admin;
import project.SRBMS.entity.ResourceMan;
import project.SRBMS.service.*;

public class DriverClass {

	public static void main(String[] args) {
		System.out.println("-------------------------------------------------------------------");
		System.out.println("*****************************WELCOME TO SRBMS**********************");
		System.out.println("|  signup-> User Sign Up              |  signin-> User Sign In    |");
		System.out.println("|  rmlogin-> Resource Manager Login   |  admin-> Admin Login      |");
		System.out.println("|  help-> Show List                   |  quit-> Quit              |");
		System.out.println("*******************************************************************");
		
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
    		System.out.println("--------------------------------------------------------");
            System.out.print("Enter your choice: ");
            
            String choice = sc.nextLine();
            
        	if(choice.equalsIgnoreCase("signup")) {   //user sign up	
        		UserService.signUp();
        		String[] temp = {""};
            	DriverClass.main(temp);
        	}
        	else if(choice.equalsIgnoreCase("signin")) {
        		UserService.signIn();
        	}
        	else if(choice.equalsIgnoreCase("rmlogin")) {
        		managerFun();
        	}
        	else if(choice.equalsIgnoreCase("admin")) {
        		adminFun();
        	}
        	else if(choice.equalsIgnoreCase("help")) {
        		System.out.println("-------------------------------------------------------------------");
        		System.out.println("*****************************WELCOME TO SRBMS**********************");
        		System.out.println("|  signup-> User Sign Up              |  signin-> User Sign In    |");
        		System.out.println("|  rmlogin-> Resource Manager Login   |  admin-> Admin Login      |");
        		System.out.println("|  help-> Show List                   |  quit-> Quit              |");
        		System.out.println("*******************************************************************");
        	}
        	else if(choice.equalsIgnoreCase("quit")) {
        		System.out.println("--------------------------------------------------------");
        		System.out.println("Thank you for using application");
        		System.out.println("--------------------------------------------------------");
        		System.exit(0);
        	}
        	else {
        		System.out.println("--------------------------------------------------------");
        		System.out.println("Wrong choice");
        	}
        	
    		System.out.println("--------------------------------------------------------");
            System.out.print("Do you want to continue(y/n)? ");
            String choice2 = sc.next();
            sc.nextLine();
            if(choice2.equalsIgnoreCase("n")) flag = false;
        }  
        sc.close();
	}  
	 
	public static void managerFun() {
    	ResourceMan obj = new ResourceMan(null, null, null);
    	obj.showMenu();
    }
	 
	public static void adminFun() {
    	Admin admin = new Admin(null, null, null);
    	admin.showMenu();
    }    

}
