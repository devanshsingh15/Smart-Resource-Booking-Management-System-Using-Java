package project.SRBMS.Driver;

import java.util.*;

import project.SRBMS.entity.Admin;
import project.SRBMS.entity.ResourceMan;
import project.SRBMS.service.*;

@SuppressWarnings("resource")
public class DriverClass {
	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("-----------------------------------------------------------------------------------");
		Thread.sleep(90);
		System.out.println("************************************WELCOME TO SRBMS*******************************");
		Thread.sleep(90);
		System.out.println("|  signup-> User Sign Up                    |  signin-> User Sign In              |");
		Thread.sleep(90);
		System.out.println("|  rmlogin-> Resource Manager Login         |  admin-> Admin Login                |");
		Thread.sleep(90);
		System.out.println("|  help-> Show List                         |  quit-> Quit                        |");
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
        		System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
        		System.out.println("***********************************WELCOME TO SRBMS********************************");
        		Thread.sleep(90);
        		System.out.println("|  signup-> User Sign Up                 |  signin-> User Sign In                 |");
        		Thread.sleep(90);
        		System.out.println("|  rmlogin-> Resource Manager Login      |  admin-> Admin Login                   |");
        		Thread.sleep(90);
        		System.out.println("|  help-> Show List                      |  quit-> Quit                           |");
        		Thread.sleep(90);
        		System.out.println("***********************************************************************************");
        		Thread.sleep(90);
        	}
        	else if(choice.equalsIgnoreCase("quit")) {
        		System.out.println("-----------------------------------------------------------------------------------");
        		Thread.sleep(90);
        		System.out.println("Thank you for using application");
        		Thread.sleep(90);
        		System.out.println("-----------------------------------------------------------------------------------");
        		System.exit(0);
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
            sc.nextLine();
            if(choice2.equalsIgnoreCase("n")) flag = false;
        }  
	}  
	 
	public static void managerFun() throws InterruptedException {
    	ResourceMan obj = new ResourceMan(null, null, null);
    	obj.showMenu();
    }
	 
	public static void adminFun() throws InterruptedException {
    	Admin admin = new Admin(null, null, null);
    	admin.showMenu();
    }    

}
