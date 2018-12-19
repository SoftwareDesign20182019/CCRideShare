package application;

import java.util.ArrayList;
/**
 * 
 * @author kbhat
 * constructs the user class with email and name as attributes and allows passing of user contact
 *
 */

public class User 
{
	private String email;
	private String fullName;
	private DatabaseHandler databaseHandler;
	
	public User(String email, String fullName)
	{
		this.email = email;
		this.fullName = fullName;
		this.databaseHandler = DatabaseHandler.getInstance();
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	public boolean addToDatabase(String password) {
		if(databaseHandler.filterEmails(email) && !databaseHandler.checkEmail(email))
		{
			databaseHandler.addAccount(this,password);
			return true;
		}
		else
		{
			return false;
		}
	}
}
