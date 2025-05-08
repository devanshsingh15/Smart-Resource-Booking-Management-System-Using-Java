package project.SRBMS.entity;

abstract public class User {
	public String id;
	public String pass;
	public String name;
	public String email;
	
	User(String id, String pass, String name) {
		this.id = id;
		this.pass = pass;
		this.name = name;
	}
	
	User(String id, String pass, String name, String email) {
		this(id, pass, name);
		this.email = email;
	}
	
	public abstract void showMenu() throws InterruptedException;
	
	public String toString() {
		return "ID: " + id + ", name: " + name + ", email: " + email;
	}
	
}
