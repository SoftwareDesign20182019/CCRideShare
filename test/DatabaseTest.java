import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.ArrayList;
import org.junit.Test;
import application.DatabaseHandler;
import application.RidePost;
import java.sql.*;


public class DatabaseTest {

	@Test
	public void databaseEntryTest() 
	{
		DatabaseHandler handler = new DatabaseHandler();
		handler.initialize();
		RidePost ridePost = new RidePost("1/1/1", "11:11PM", "Danver", "Colorado College", 4, "$20", "no comments");
		int rowsAdded = ridePost.addToDatabase();
		
		assertEquals(rowsAdded,1);
	}
	
	@Test
	public void getAllRidersTest()
	{
		DatabaseHandler handler = new DatabaseHandler();
		handler.initialize();
		int numRows = handler.getTotalRows();
		ArrayList<RidePost> riderList = handler.getRidePosts();
		
		assertEquals(riderList.size(),numRows);
	}
}
