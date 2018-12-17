package application;

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
		if(DatabaseHandler.filterEmails(email) && !DatabaseHandler.checkEmail(email))
		{
			DatabaseHandler.addAccount(this,password);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * main method
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
		DatabaseHandler.initialize();
		User user = new User("first.last@coloradocollege.edu","Student");
		User badUser = new User("first.last@randomemail.com","Bad Guy");
		System.out.println("user verified: "+DatabaseHandler.filterEmails(user.getEmail()));
		System.out.println("badUser verified: "+DatabaseHandler.filterEmails(badUser.getEmail()));
		
		if(user.addToDatabase("password"))
			System.out.println("User exists in database");
		else
			System.out.println("User does not exist in database");
		if(badUser.addToDatabase("password"))
			System.out.println("Bad User exists in database");
		else
			System.out.println("Bad User does not exist in database");
	}
}
