package application;

import javafx.stage.Stage;
import javafx.application.Application;
import java.util.ArrayList;

public class ApplicationFactory {

	private static User currentUser;
	
	public static enum ApplicationType
	{
		RIDE_LIST, ADD_RIDE_POST, ADD_RIDE_REQUEST, CREATE_ACCOUNT, LOG_IN
	}
	
	private static ArrayList<ApplicationWithType> applications = new ArrayList<ApplicationWithType>();
	
	public static Application getApplication(ApplicationType typeDesired) {
		for(ApplicationWithType applicationWithType : applications) {
			if(applicationWithType.getType() == typeDesired) {
				return applicationWithType.getApplication();
			}
		}
		
		Application newApplication;
		switch(typeDesired) {
			case RIDE_LIST:
				newApplication = new RideListApplication();

				break;
			case ADD_RIDE_POST:
				newApplication = new AddRidePostApplication();
				break;
			case ADD_RIDE_REQUEST:
				newApplication = new AddRideRequestApplication();
				break;
			case CREATE_ACCOUNT:
				newApplication = new CreateAccountApplication();
				break;
			case LOG_IN:
				newApplication = new LogInApplication();
				break;
			default: 
				return null;
		}
		ApplicationWithType newApplicationWithType = new ApplicationWithType(newApplication, typeDesired);
		applications.add(newApplicationWithType);
		return newApplication;
	}
	
	public static User setCurrentUser(String email)
	{
		ArrayList<User> users = DatabaseHandler.getUser(email);
		if(!users.isEmpty())
		{
			currentUser = users.get(0);
			System.out.println("Current user: "+email);
			return currentUser;
		}
		else
		{
			System.out.println("Not a current user: "+email);
			return new User("","");
		}
	}
	
	private static class ApplicationWithType{
		private Application application;
		private ApplicationType type;
		
		public ApplicationWithType(Application application, ApplicationType type) {
			this.application = application;
			this.type = type;
		}
		
		public Application getApplication() {
			return application;
		}
		
		public ApplicationType getType() {
			return type;
		}
	}
	
	public static User getCurrentUser()
	{
		return currentUser;
	}
}
