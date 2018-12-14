package application;

import java.sql.*;
import java.util.ArrayList;

/**
 * class for DatabaseHandler - responsible for creating database and tables (if they don't already exist) and for adding rides to the database
 * @author kbhat
 *
 */
public class DatabaseHandler {
	
	public static final String PORT_NUMBER = "3306";
	
	private static Statement databaseStatement; // Does this need to be closed ever?
	
	/**
	 * Creates the local database and the RidePost table, if they don't already exist
	 */
	public static void initialize() {
		try(
				Connection initialConn = DriverManager.getConnection("jdbc:mysql://localhost:" + PORT_NUMBER + "/", 
						"root", "root");
				Statement initialStmt = initialConn.createStatement();
				){
			
			String createDatabaseString = "create database if not exists CCRideShare";
			initialStmt.execute(createDatabaseString);
		
			Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:" + PORT_NUMBER + "/CCRideShare?user=root&password=root");
			databaseStatement = conn.createStatement();	
		
			String createTable = "create table if not exists RidePosts ( " + 
					 "id int not null auto_increment, "
					 + "date varchar(25), "
					 + "time varchar(10), "
					 + "toLocation varchar(50), "
					 + "fromLocation varchar(50), "
					 + "numSpots int, "
					 + "price varchar(50), "
					 + "comments varchar(500), "
					 + "primary key (id));";
			
			databaseStatement.execute(createTable);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/*public static Statement getStatement() {	
		try {
				// Set up new Connection and Statement, now that the database is created
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:" + PORT_NUMBER + "/CCRideShare?user=root&password=root");
				Statement stmt = conn.createStatement();
			return stmt;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
		
	}*/
	
	/**
	 * adds a specified RidePost to the RidePosts table
	 * @param ridePost - the RidePost that is to be added to the table
	 * @return number of posts added
	 */
	public static int addRidePost(RidePost ridePost) {
		String sqlInsert = String.format("INSERT INTO RidePosts values (null, '%s', '%s', '%s', '%s', %d, '%s', '%s')", 
				 ridePost.getDate(), ridePost.getTime(), ridePost.getToLocation(), ridePost.getFromLocation(), ridePost.getNumSpots(), ridePost.getPrice(), ridePost.getComments());

		try{
			int rowsAdded = databaseStatement.executeUpdate(sqlInsert);
			return rowsAdded;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Retrieves a list of all RidePost rows in the RidePosts table
	 * @return ArrayList of RidePost
	 */
	public static ArrayList<RidePost> getRidePosts() {
		String sqlSelect = "select * from RidePosts";
		try{
			ResultSet rset = databaseStatement.executeQuery(sqlSelect);
			ArrayList<RidePost> ridePosts = new ArrayList<RidePost>();
			while(rset.next()) {
				String date = rset.getString("date");
				String time = rset.getString("time");
				String toLocation = rset.getString("toLocation");
				String fromLocation = rset.getString("fromLocation");
				int numSpots = rset.getInt("numSpots");
				String price = rset.getString("price");
				String comments = rset.getString("comments");
				RidePost currRidePost = new RidePost(date, time, toLocation, fromLocation, numSpots, price, comments);
				
				ridePosts.add(currRidePost);
			}
			
			rset.close();
			return ridePosts;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.exit(-1);
			return null;
		}
	}
	
	/**
	 * retrieves the number of rows in the RidePosts table
	 * @return number of rows in table
	 */
	public static int getTotalRows()
	{
		try
		{	
			int count = 0;
			ResultSet rset = databaseStatement.executeQuery("SELECT COUNT(*) FROM RidePosts");
			while (rset.next())
			{
				count = rset.getInt(1);
			}
			return count;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
}
