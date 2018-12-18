package application;

import javafx.application.Application;
import java.util.ArrayList;
/**
 * Factory class for Application objects, implementing the factory design pattern
 * @author emerenstein
 */
public class ApplicationFactory {

	private static User currentUser;
	
	public static enum ApplicationType
	{
		RIDE_LIST, ADD_RIDE_POST, ADD_RIDE_REQUEST, CREATE_ACCOUNT, LOG_IN
	}
	
	private static ArrayList<ApplicationWithType> applications = new ArrayList<ApplicationWithType>();
	
	/**
	 * creates the desired type of application and returns it (just like a good factory does)
	 * @param typeDesired - the type of Application needed
	 * @return
	 */
	public static GeneralApplication getApplication(ApplicationType typeDesired) {
		for(ApplicationWithType applicationWithType : applications) {
			if(applicationWithType.getType() == typeDesired) {
				return applicationWithType.getApplication();
			}
		}
		
		GeneralApplication newApplication;
		switch(typeDesired) {
			case RIDE_LIST:
				newApplication = new GeneralApplication();
				newApplication.setGUIPath("/ui/RideListGUI.fxml");
				break;
			case ADD_RIDE_POST:
				newApplication = new GeneralApplication();
				newApplication.setGUIPath("/ui/AddRidePostGUI.fxml");
				break;
			case ADD_RIDE_REQUEST:
				newApplication = new GeneralApplication();
				newApplication.setGUIPath("/ui/AddRideRequestGUI.fxml");
				break;
			case CREATE_ACCOUNT:
				newApplication = new GeneralApplication();
				newApplication.setGUIPath("/ui/CreateAccountGUI.fxml");
				break;
			case LOG_IN:
				newApplication = new GeneralApplication();
				newApplication.setGUIPath("/ui/LogInGUI.fxml");
				break;
			default: 
				return null;
		}
		ApplicationWithType newApplicationWithType = new ApplicationWithType(newApplication, typeDesired);
		applications.add(newApplicationWithType);
		return newApplication;
	}
	/**
	 * returns the object corresponding to an email address
	 * @param email of user
	 * @return the user object that has the email 
	 */
	public static User setCurrentUser(String email)
	{
		DatabaseHandler dbHandler = DatabaseHandler.getInstance();
		ArrayList<User> users = dbHandler.getUser(email);
		if(!users.isEmpty())
		{
			currentUser = users.get(0);
			return currentUser;
		}
		else
		{
			System.out.println("Not a current user: "+email);
			return new User("","");
		}
	}
	
	private static class ApplicationWithType{
		private GeneralApplication application;
		private ApplicationType type;
		
		public ApplicationWithType(GeneralApplication application, ApplicationType type) {
			this.application = application;
			this.type = type;
		}
		
		public GeneralApplication getApplication() {
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
