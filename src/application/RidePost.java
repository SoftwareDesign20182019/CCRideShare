package application;
import java.util.ArrayList;

public class RidePost {

	private String date;
	private String time;
	private String toLocation;
	private String fromLocation;
	private int numSpots;
	private String price;
	private String comments;
	
	// private User driver;
	// private ArrayList<User> riders;
	
	public RidePost(String date, String time, String toLocation, String fromLocation, int numSpots, String price, String comments) {
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public void addToDatabase() {
		DatabaseHandler.addRidePost(this);
	}
	
	public static void main(String[] args) {
		
		DatabaseHandler.initialize();
		RidePost post = new RidePost("1/1/19", "11:00PM", "Dover", "Colorado College", 4, "$20", "no comments");
		RidePost post2 = new RidePost("1/1/20", "11:00PM", "Denver", "Colorado College", 4, "$20", "");
		post.addToDatabase();
		post2.addToDatabase();
		ArrayList<RidePost> ridePosts = DatabaseHandler.getRidePosts();
	}
}
