package application;

import java.util.ArrayList;

public class User 
{
	private String email;
	private String password;
	private String fullName;
	
	public User(String email, String password, String fullName)
	{
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * adds a RiderPost to the database
	 * @return number of rows added
	 */
	/*public int addToDatabase() {
		int rowsAdded = DatabaseHandler.addAccount(this);
		return rowsAdded;
	}*/
	
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
		DatabaseHandler.initialize();
		User user = new User("first.last@coloradocollege.edu", "password","Student");
		User badUser = new User("first.last@randomemail.com", "password","Bad Guy");
		System.out.println("user verified: "+DatabaseHandler.filterEmails(user.getEmail()));
		System.out.println("badUser verified: "+DatabaseHandler.filterEmails(badUser.getEmail()));
		//user.addToDatabase();
		//badUser.addToDatabase();
	}
}