package db_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data_classes.Book;
import data_classes.BookStatus;
import data_classes.BookType;
import data_classes.HistoricalPeriod;

public class DatabaseManager {
	
	// fields
	private static List<HistoricalPeriod> historical_periods;
	private static List<Book> bookCollection;
	Connection connection;
	
	
	
	// constructor
	public DatabaseManager() {
		historical_periods = new ArrayList<>();
		bookCollection     = new ArrayList<>();
	}


	
	// getters
	public static List<HistoricalPeriod> getHistorical_periods() {
		return historical_periods;
	}

	public static List<Book> getBookCollection() {
		return bookCollection;
	}
	
	
	
	// method to create a database connection
	Connection connectDB() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library", "root", "Ronaldo");
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	// method to retrieve all data from database
	public void retrieveData() {
		try {
			connection         = connectDB();
			historical_periods = retrieveHistoricalPeriods(connection);
			bookCollection     = retrieveBooks(connection);
			connection.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// method to retrive histocial records from database
	private static List<HistoricalPeriod> retrieveHistoricalPeriods(Connection con) throws SQLException {
		
		List<HistoricalPeriod> historical_periods = new ArrayList<>();
		Statement stmt = con.createStatement();
		ResultSet rs   = stmt.executeQuery("SELECT * FROM historical_period");
		
		while(rs.next()) {
			HistoricalPeriod hp = new HistoricalPeriod();
			hp.setPeriodID(rs.getInt("period_id"));
			hp.setName(rs.getString("name"));
			hp.setStart_year(rs.getInt("start_year"));
			hp.setEnd_year(rs.getInt("end_year"));
			hp.setDescription(rs.getString("description"));
			historical_periods.add(hp);
		}
		rs.close();
		stmt.close();
		return historical_periods;
 	}
	
	
	
	// method to retrieve book data from database
	private static List<Book> retrieveBooks(Connection con) throws SQLException{
		
		List<Book> bookCollection = new ArrayList<>();
		Statement stmt            = con.createStatement();
		ResultSet rs              = stmt.executeQuery("SELECT * FROM Book");
		
		while(rs.next()) {
			Book book = new Book();
			book.setBookId(rs.getInt("book_id"));
			book.setTitle(rs.getString("title")); 
			book.setAuthor(rs.getString("author")); 
			String typeFromDatabase = rs.getString("book_type");
			BookType bookType = BookType.valueOf(typeFromDatabase);
			book.setBookType(bookType);
			book.setYearPublished(rs.getInt("publication_year"));
			book.setPeriodId(rs.getInt("period_id"));
			String statusFromDatabase = rs.getString("status");
			BookStatus status = BookStatus.valueOf(statusFromDatabase);
			book.setStatus(status);
			book.setRating(rs.getInt("rating"));
			book.setNotes(rs.getString("notes")); 
			bookCollection.add(book);
		}
		rs.close();
		stmt.close();
		return bookCollection;
	}

	
	// retrieve a Book
	
	// retrieve an historical_period 
	
	// create new Book
	
	// create new historical_record 
	
	// update Book details
	
	// update historical record details
	
	// delete Book
	
	// delete historical record
}