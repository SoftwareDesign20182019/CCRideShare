import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {
	
	public static final String PORT_NUMBER = "3306";
	
	private static Statement databaseStatement; // Does this need to be closed ever?
	
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
	
	public static void addRidePost(RidePost ridePost) {
		String sqlInsert = String.format("INSERT INTO RidePosts values (null, '%s', '%s', '%s', '%s', %d, '%s', '%s')", 
				 ridePost.getDate(), ridePost.getTime(), ridePost.getToLocation(), ridePost.getFromLocation(), ridePost.getNumSpots(), ridePost.getPrice(), ridePost.getComments());

		try{
			databaseStatement.executeUpdate(sqlInsert);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
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
}
