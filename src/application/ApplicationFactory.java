package application;

import javafx.stage.Stage;
import javafx.application.Application;
import java.util.ArrayList;

public class ApplicationFactory {

	public static enum ApplicationType{
		RIDE_LIST, ADD_RIDE_POST
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
			default: 
				return null;
		}
		ApplicationWithType newApplicationWithType = new ApplicationWithType(newApplication, typeDesired);
		applications.add(newApplicationWithType);
		return newApplication;
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
	
}
