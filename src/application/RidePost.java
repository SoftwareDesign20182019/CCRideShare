package application;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Class for ride posts - a post listed by drivers, for people finding a ride
 * @author kbhat
 *
 */
public class RidePost {

	private String date;
	private String time;
	private String toLocation;
	private String fromLocation;
	private int numSpots;
	private int price;
	private String comments;
	
	// private User driver;
	// private ArrayList<User> riders;
	
	/**
	 * Constructor for RidePost
	 * @param date - date of departure
	 * @param time - time of departure
	 * @param toLocation - destination location
	 * @param fromLocation - starting location
	 * @param numSpots - number of available seats in car
	 * @param price - cost to ride
	 * @param comments - any additional comments about trunk space for luggage, car coziness, etc.
	 */
	public RidePost(String date, String time, String toLocation, String fromLocation, int numSpots, int price, String comments) {
		this.date = date; 
		this.time = time;
		this.toLocation = toLocation;
		this.fromLocation = fromLocation;
		this.numSpots = numSpots;
		this.price = price;
		this.comments = comments;
		// databaseStatement = DatabaseHandler.getStatement();
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

	public int getNumSpots() {
		return numSpots;
	}

	public void setNumSpots(int numSpots) {
		this.numSpots = numSpots;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * adds a RiderPost to the database
	 * @return number of rows added
	 */
	public int addToDatabase() {
		DatabaseHandler dbHandler = DatabaseHandler.getInstance();
		int rowsAdded = dbHandler.addRidePost(this);
		return rowsAdded;
	}
}
