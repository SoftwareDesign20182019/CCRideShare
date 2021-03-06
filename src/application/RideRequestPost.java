package application;
import java.util.ArrayList;


/**
* Class for ride request posts - a post listed by riders for drivers looking for riders
* @author arehorst
*
*/
public class RideRequestPost {

	private String date;
	private String time;
	private String toLocation;
	private String fromLocation;
	private String email;

	public RideRequestPost(String date, String time, String toLocation, String fromLocation, String email) {
		this.date = date; 
		this.time = time;
		this.toLocation = toLocation;
		this.fromLocation = fromLocation;
		this.email = email;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * adds a RiderPost to the database
	 * @return number of rows added
	 */
	public int addToDatabase() {
		DatabaseHandler dbHandler = DatabaseHandler.getInstance();
		int rowsAdded = dbHandler.addRideRequestPost(this);
		return rowsAdded;
	}
}
