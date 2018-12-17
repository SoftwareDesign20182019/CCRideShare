package application;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	public static final String CC_DOMAIN = "coloradocollege.edu";
	
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
					 + "date DATE, "
					 + "time varchar(10), "
					 + "toLocation varchar(50), "
					 + "fromLocation varchar(50), "
					 + "numSpots int, "
					 + "price varchar(50), "
					 + "comments varchar(500), "
					 + "primary key (id));";
			
			String createRequestTable = "create table if not exists RideRequestPosts ( " + 
					 "id int not null auto_increment, "
					 + "date DATE, "
					 + "time varchar(10), "
					 + "toLocation varchar(50), "
					 + "fromLocation varchar(50), "
					 + "primary key (id));";
			
			String createLocationTable = "create table if not exists Locations ( "
					 + "id int not null auto_increment, "
					 + "location varchar(50), "
					 + "primary key (id));";
			
			String createAccountTable = "create table if not exists Accounts ( "
					+ "id int not null auto_increment, "
					+ "name varchar(100), "
					+ "email varchar(100), "
					+ "password varchar(100), "
					+ "primary key (id));";
			
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
		String date = ridePost.getDate();
		String sqlInsert = String.format("INSERT INTO RidePosts values (null, %s, '%s', '%s', '%s', %d, '%s', '%s')", 
				"STR_TO_DATE('"+date+"', '%Y-%m-%d')", ridePost.getTime(), ridePost.getToLocation(), ridePost.getFromLocation(), ridePost.getNumSpots(), ridePost.getPrice(), ridePost.getComments());
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
		String date = riderequestPost.getDate();
		String sqlInsert = String.format("INSERT INTO RideRequestPosts values (null, %s, '%s', '%s', '%s')", 
				 "STR_TO_DATE('"+date+"','%Y-%m-%d')", riderequestPost.getTime(), riderequestPost.getToLocation(), riderequestPost.getFromLocation());

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
	public static int addAccount(User account,String password) {
		String sqlInsert = String.format("INSERT INTO Accounts values (null, '%s', '%s', '%s')", account.getFullName(), account.getEmail(), password);
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
				int price = rset.getInt("price");
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
				RideRequestPost currRideRequestPost = new RideRequestPost(date, time, toLocation, fromLocation);
				
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
	
	public static ArrayList<User> getUser(String email) {
		String sqlSelect = "SELECT * FROM Accounts WHERE email = '"+email+"';";
		ArrayList<User> users = new ArrayList<User>();

		try
		{
			ResultSet rset = databaseStatement.executeQuery(sqlSelect);
			while(rset.next()) 
			{
				String name = rset.getString("name");
				String userEmail = rset.getString("email");
				User currUser = new User(userEmail,name);
				
				users.add(currUser);
			}
			return users;
		}
		catch(SQLException ex) 
		{
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
		return email.contains(CC_DOMAIN);
	}
	
	public static boolean checkEmail(String email)
	{
		String sqlQuery = "SELECT email FROM Accounts WHERE email = '"+email+"'";
		try
		{
			ResultSet rset = databaseStatement.executeQuery(sqlQuery);
			if(rset.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(SQLException ex) 
		{
			ex.printStackTrace();
			System.exit(-1);
			return false;
		}
	}
	
	public static boolean isRightPassword(String email, String password)
	{
		String sqlQuery = "SELECT email FROM Accounts WHERE email = '"+email+"' AND password = '"+password+"';";
		try
		{
			ResultSet rset = databaseStatement.executeQuery(sqlQuery);
			if(rset.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<RidePost> RidesofDate()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		Date desiredDate = Date.valueOf(localDate);
		String nowDate = formatter.format(localDate);
		
		String sqlQuery = "SELECT * FROM RidePosts WHERE date = STR_TO_DATE('"+nowDate+"','%Y-%m-%d');";
		try
		{
			ResultSet rset = databaseStatement.executeQuery(sqlQuery);
			ArrayList<RidePost> SearchedPostsByDate = new ArrayList<RidePost>();
			
			if(rset.next())
			{
				String StringDate = rset.getString("date");
				String time = rset.getString("time");
				String toLocation = rset.getString("toLocation");
				String fromLocation = rset.getString("fromLocation");
				int numSpots = rset.getInt("numSpots");
				int price = rset.getInt("price");
				String comments = rset.getString("comments");
				RidePost RidePosts = new RidePost(StringDate, time, toLocation, fromLocation, numSpots, price, comments);	
			
				SearchedPostsByDate.add(RidePosts);	
			}
			rset.close();
			
			return SearchedPostsByDate;
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
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
