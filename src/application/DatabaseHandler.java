package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * class for DatabaseHandler - responsible for creating database and tables (if they don't already exist) and for adding rides to the database
 * @author kbhat and arehorst
 *
 */
public class DatabaseHandler {
	
	public static final String PORT_NUMBER = "3306"; // Most people seem to use this port
//	 public static final String PORT_NUMBER = "8889"; // Ely uses this port
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[a-z0-9](\\.?[a-z0-9]){5,}@coloradocollege\\.edu$", Pattern.CASE_INSENSITIVE);
	
	
	private static Statement databaseStatement; // Does this need to be closed ever?
	
	/**
	 * Creates the local database, the RidePost table, and RideRequestPost table if they don't already exist
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
		
			String createRideTable = "create table if not exists RidePosts ( " + 
					 "id int not null auto_increment, "
					 + "date varchar(25), "
					 + "time varchar(10), "
					 + "toLocation varchar(50), "
					 + "fromLocation varchar(50), "
					 + "numSpots int, "
					 + "price varchar(50), "
					 + "comments varchar(500), "
					 + "primary key (id));";
			
			String createRequestTable = "create table if not exists RideRequestPosts ( " + 
					 "id int not null auto_increment, "
					 + "date varchar(25), "
					 + "time varchar(10), "
					 + "toLocation varchar(50), "
					 + "fromLocation varchar(50), "
					 + "comments varchar(500), "
					 + "primary key (id));";
			
			String createLocationTable = "create table if not exists Locations ( "
					 + "id int not null auto_increment, "
					 + "location varchar(50), "
					 + "primary key (id));";
			
			String createAccountTable = "create table if not exists Accounts ( "
					+ "id not null_auto_increment, "
					+ "name varchar(15), "
					+ "email varchar(25), "
					+ "password varchar(25));";
			
			databaseStatement.execute(createRideTable);
			databaseStatement.execute(createRequestTable);
			databaseStatement.execute(createLocationTable);
			databaseStatement.execute(createAccountTable);
			
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
	 * adds a specified RideRequestPost to the RideRequestPosts table
	 * @param riderequestPost - the RideRequestPost that is to be added to the table
	 * @return number of posts added
	 */
	public static int addRideRequestPost(RideRequestPost riderequestPost) {
		String sqlInsert = String.format("INSERT INTO RideRequestPosts values (null, '%s', '%s', '%s', '%s', '%s')", 
				 riderequestPost.getDate(), riderequestPost.getTime(), riderequestPost.getToLocation(), riderequestPost.getFromLocation(), riderequestPost.getComments());

		try{
			int rowsAdded = databaseStatement.executeUpdate(sqlInsert);
			return rowsAdded;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * adds a specified location string to the Locations table
	 * @param location the location string to be added to the table
	 * @return the number of location strings added
	 */
	public static int addLocation(String location) {
		String sqlInsert = String.format("INSERT INTO Locations values (null, '%s')", location);
		
		try{
			int locationsAdded = databaseStatement.executeUpdate(sqlInsert);
			return locationsAdded;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	/**
	 * adds a user account to the Accounts table
	 * @param account the account of type user to be added to the table
	 * @return the number of accounts added
	 */
	public static int addAccount(User account) {
		String sqlInsert = String.format("INSERT INTO Accounts values (null, '%s', '%s', '%s')", account.getFullName(), account.getEmail());
		try{
			int accountsAdded = databaseStatement.executeUpdate(sqlInsert);
			return accountsAdded;
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
	 * Retrieves a list of all RideRequestPost rows in the RideRequestPosts table
	 * @return ArrayList of RideRequestPost
	 */
	public static ArrayList<RideRequestPost> getRideRequestPosts() {
		String sqlSelect = "select * from RideRequestPosts";
		try{
			ResultSet rset = databaseStatement.executeQuery(sqlSelect);
			ArrayList<RideRequestPost> riderequestPosts = new ArrayList<RideRequestPost>();
			while(rset.next()) {
				String date = rset.getString("date");
				String time = rset.getString("time");
				String toLocation = rset.getString("toLocation");
				String fromLocation = rset.getString("fromLocation");
				String comments = rset.getString("comments");
				RideRequestPost currRideRequestPost = new RideRequestPost(date, time, toLocation, fromLocation, comments);
				
				riderequestPosts.add(currRideRequestPost);
			}
			
			rset.close();
			return riderequestPosts;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.exit(-1);
			return null;
		}
	}
	
	/**
	 * Retrieves a list of all location strings in the Locations table
	 * @return ArrayList of String (the list of locations)
	 */
	public static ArrayList<String> getLocations() {
		String sqlSelect = "select location from Locations";
		try{
			ResultSet rset = databaseStatement.executeQuery(sqlSelect);
			ArrayList<String> locations = new ArrayList<String>();
			while(rset.next()) {
				String location = rset.getString("location");
				locations.add(location);
			}
			
			rset.close();
			Collections.sort(locations); // So they list comes in alphabetical order when it's displayed
			return locations;
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.exit(-1);
			return null;
		}
	}
	
	public static boolean filterEmails(String email)
	{
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
	}
	
	/**
	 * retrieves the number of rows in the RidePosts table
	 * @return number of rows in table
	 */
	//For JUnit testing purposes
//	public static int getTotalRows()
//	{
//		try
//		{	
//			int count = 0;
//			ResultSet rset = databaseStatement.executeQuery("SELECT COUNT(*) FROM RidePosts");
//			while (rset.next())
//			{
//				count = rset.getInt(1);
//			}
//			return count;
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//			return 0;
//		}
//	}
}
