package application;

import java.util.ArrayList;

public class User 
{
	private String email;
	private String fullName;
	
	public User(String email, String fullName)
	{
		this.email = email;
		this.fullName = fullName;
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
		DatabaseHandler dbHandler = DatabaseHandler.getInstance();
		if(dbHandler.filterEmails(email) && !dbHandler.checkEmail(email))
		{
			dbHandler.addAccount(this,password);
			return true;
		}
		else
		{
			return false;
		}
	}
}
