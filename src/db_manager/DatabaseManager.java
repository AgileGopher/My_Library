package db_manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data_classes.Book;
import data_classes.BookStatus;
import data_classes.BookType;
import data_classes.HistoricalPeriod;
import io_classes.Keyboard;

public class DatabaseManager {
	
	// fields
	private static List<HistoricalPeriod> historical_periods;
	private static List<Book> Library;
	private Keyboard kb;
	Connection connection;
	
	
	
	// constructor
	public DatabaseManager() {
		historical_periods = new ArrayList<>();
		Library            = new ArrayList<>();
		kb = new Keyboard();
	}


	
	// getters
	public static List<HistoricalPeriod> getHistorical_periods() {
		return historical_periods;
	}


	public static List<Book> getLibrary() {
		return Library;
	}
	
	
	// method to retrive histocial records from database
	private static List<HistoricalPeriod> retrieveHistoricalPeriods(Connection con) throws SQLException {
		
		List<HistoricalPeriod> historical_periods = new ArrayList<>();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM historical_period");
		
		while(rs.next()) {
			
			HistoricalPeriod hp = new HistoricalPeriod();
			hp.setPeriodID(rs.getInt("period_id"));
			hp.setName("name");
			hp.setStart_year(rs.getInt("start_year"));
			hp.setEnd_year(rs.getInt("end_year"));
			hp.setDescription("description");
			
			historical_periods.add(hp);
		}
		rs.close();
		stmt.close();
		
		return historical_periods;
 	}
	
	
	// method to retrieve book data from database
	private static List<Book> retrieveBooks(Connection con) throws SQLException{
		
		List<Book> bookCollection = new ArrayList<>();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Book");
		
		while(rs.next()) {
			
			Book book = new Book();
			book.setBookId(rs.getInt("book_id"));
			book.setTitle("title");
			book.setAuthor("author");
			String typeFromDatabase = rs.getString("book_type");
			BookType bookType = BookType.valueOf(typeFromDatabase);
			book.setBookType(bookType);
			book.setYearPublished(rs.getInt("publication_year"));
			book.setPeriodId(rs.getInt("period_id"));
			String statusFromDatabase = rs.getString("status");
			BookStatus status = BookStatus.valueOf(statusFromDatabase);
			book.setStatus(status);
			book.setRating(rs.getInt("rating"));
			book.setNotes("notes");
			
			bookCollection.add(book);
		}
		rs.close();
		stmt.close();
		
		return bookCollection;
	}

}