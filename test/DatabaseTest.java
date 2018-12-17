import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.ArrayList;
import org.junit.Test;
import application.DatabaseHandler;
import application.RidePost;
import java.sql.*;

/**
 * Unit tests for the DatabaseHandler class
 * @author kbhat
 *
 */
public class DatabaseTest {

	/**
	 * Check to see that addToDatabase adds a single post to the database successfully
	 */
	@Test
	public void databaseEntryTest() 
	{
		DatabaseHandler.initialize();
		RidePost ridePost = new RidePost("1/1/1", "11:11PM", "Danver", "Colorado College", 4, 20, "no comments");
		int rowsAdded = ridePost.addToDatabase();
		
		assertEquals(rowsAdded,1);
	}
	
	/**
	 * Check to see that the number of rows in the RidePosts table is the same as the number of items 
	 * returned in the getRidePosts() method
	 */
	@Test
	public void getAllRidePostsTest()
	{
		DatabaseHandler.initialize();
		int numRows = DatabaseHandler.getTotalRows();
		ArrayList<RidePost> riderList = DatabaseHandler.getRidePosts();
		
		assertEquals(riderList.size(),numRows);
	}
}
