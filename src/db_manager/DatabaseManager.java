package db_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	
	
	// Singleton instance variable
	private static DatabaseManager instance;
	
	

	// constructor
	private DatabaseManager() {}
	
	
	
	//Singleton method to ensure only one instance of DatabaseManager is created.
	public static DatabaseManager getInstance() {
		if(instance == null) {
			instance = new DatabaseManager();
		}
		return instance;
	}
	
	
	private static void validateID(int id) {
		if(id < 1) {
			throw new IllegalArgumentException("Invalid ID: " + id);
		}
	}
	
	
	
	private static void validateBook(Book book) {
		
	}
	
	
	private static void validateHistoricalPeriod(HistoricalPeriod period) {
		
	}
	
	
	// method to create a database connection
	private Connection connectDB() throws SQLException {
		
		String url      = "jdbc:mysql://localhost:3306/db_library";
		String username = "root";
		String password = System.getenv("DB_PASSWORD");
	
		return DriverManager.getConnection(url, username, password);
	}
	
	
	
	// method to retrive histocial records from database
	public List<HistoricalPeriod> retrieveHistoricalPeriods() throws SQLException {
		
		List<HistoricalPeriod> historical_periods = new ArrayList<>();
		
		String sql = "SELECT * FROM historical_period";
		
		try(Connection con = connectDB();
			Statement stmt = con.createStatement();
		    ResultSet rs   = stmt.executeQuery(sql)){
		
			while(rs.next()) {
				
				HistoricalPeriod hp = new HistoricalPeriod();
				
				hp.setPeriodID(rs.getInt("period_id"));
				hp.setName(rs.getString("name"));
				hp.setStart_year(rs.getInt("start_year"));
				hp.setEnd_year(rs.getInt("end_year"));
				hp.setDescription(rs.getString("description"));
				
				historical_periods.add(hp);
			}
	    }
		return historical_periods;
 	}
	
	
	
	// method to retrieve book data from database
	public List<Book> retrieveBooks() throws SQLException {
		
		List<Book> books = new ArrayList<>();
		
		String sql = "SELECT * FROM Book";
		
		try (Connection con  = connectDB();
			 Statement stmt  = con.createStatement();
		     ResultSet rs    = stmt.executeQuery(sql)){
		
			while(rs.next()) {
				
				Book book = new Book();
				
				book.setBookId(rs.getInt("book_id"));
				book.setTitle(rs.getString("title")); 
				book.setAuthor(rs.getString("author")); 
				BookType bookType = BookType.valueOf(rs.getString("book_type"));
				book.setBookType(bookType);
				book.setYearPublished(rs.getInt("publication_year"));
				book.setPeriodId(rs.getInt("period_id"));
				BookStatus status = BookStatus.valueOf(rs.getString("status"));
				book.setStatus(status);
				book.setRating(rs.getInt("rating"));
				book.setNotes(rs.getString("notes")); 
				
				books.add(book);
			}
		}
		return books;
	}

	
	
	// method to retrieve book object from datbase 
	public Book retrieveBook(int id) throws SQLException {

		validateID(id);
		String sql = "SELECT * FROM BOOK WHERE book_id = ?";
		
		try (Connection connection = connectDB();
			 PreparedStatement ps  = connection.prepareStatement(sql)){
			
			ps.setInt(1, id);
			
			try(ResultSet rs = ps.executeQuery()){
				
				if(rs.next()) {
	
					Book retrievedBook = new Book();
					
					retrievedBook.setBookId(id);
					retrievedBook.setTitle(rs.getString("title")); 
					retrievedBook.setAuthor(rs.getString("author")); 
					String typeFromDatabase = rs.getString("book_type");
					BookType bookType = BookType.valueOf(typeFromDatabase);
					retrievedBook.setBookType(bookType);
					retrievedBook.setYearPublished(rs.getInt("publication_year"));
					retrievedBook.setPeriodId(rs.getInt("period_id"));
					String statusFromDatabase = rs.getString("status");
					BookStatus status = BookStatus.valueOf(statusFromDatabase);
					retrievedBook.setStatus(status);
					retrievedBook.setRating(rs.getInt("rating"));
					retrievedBook.setNotes(rs.getString("notes")); 
					
					return retrievedBook;
				}
			}
		}
		return null;
	}
	
	
	
	// method to retrieve historicalPeriod object from datbase
	public HistoricalPeriod retrieveHistoricalPeriod(int id)  throws SQLException {
		
		validateID(id);
		String sql = "SELECT * FROM historical_period WHERE period_id = ?";
		
		try (Connection connection = connectDB();
			 PreparedStatement ps  = connection.prepareStatement(sql)){
			
			ps.setInt(1, id);
			
			try(ResultSet rs = ps.executeQuery()){
				
				if(rs.next()) {
					
					HistoricalPeriod retrievedHistoricalPeriod = new HistoricalPeriod();
					
					retrievedHistoricalPeriod.setPeriodID(id);
					retrievedHistoricalPeriod.setName(rs.getString("name"));
					retrievedHistoricalPeriod.setStart_year(rs.getInt("start_year"));
					retrievedHistoricalPeriod.setEnd_year(rs.getInt("end_year"));
					retrievedHistoricalPeriod.setDescription(rs.getString("description"));
					
					return retrievedHistoricalPeriod;
				}
			}
		}
		return null;
	}
	
	
	
	// this method calls librarian to grab new book details from user
	public void createNewBook(Book newBook) throws SQLException {
		
		String sql = "INSERT INTO book "
				   + "(title, author, book_type, publication_year, "
				   + "period_id, status, rating, notes) "
				   + "VALUES(?,?,?,?,?,?,?,?)";
		
		try (Connection connection = connectDB();
			 PreparedStatement ps  = connection.prepareStatement(sql)) {
			
			ps.setString(1, newBook.getTitle()); // need to capitalize somehow
			ps.setString(2, newBook.getAuthor());
			ps.setString(3, newBook.getBookType().name().toUpperCase());
			ps.setInt(4, newBook.getYearPublished());
			ps.setInt(5, newBook.getPeriodId());
			ps.setString(6, newBook.getStatus().name().toUpperCase());
			ps.setInt(7, newBook.getRating());
			ps.setString(8, newBook.getNotes());
			
			ps.executeUpdate();
			
			System.out.println("Book: " 
			                  + newBook.getTitle()
			                  + " added successfully..");
	   }
	}
	
	
	
	// this method calls librarian to grab new HistoricalPeriod details from user
	public void createNewHistoricalPeriod(HistoricalPeriod newPeriod) throws SQLException {
		
		String sql = "INSERT INTO historical_period "
				   + "(name, start_year, end_year, description) "
				   + "VALUES(?, ?, ?, ?)";
		
		try (Connection connection = connectDB();
			 PreparedStatement ps  = connection.prepareStatement(sql)) {
				
			ps.setString(1, newPeriod.getName());
			ps.setInt(2, newPeriod.getStart_year());
			ps.setInt(3, newPeriod.getEnd_year());
			ps.setString(4, newPeriod.getDescription());
			
			ps.executeUpdate();
			
			System.out.println("Historical Period: "
					          + "" + newPeriod.getName() 
					          + " added successfully..");
		}
	}
	
	
	
	// method to update historical_period in database
	public void updateBook(int bookId, Book updatedBook) throws SQLException {
		
		validateID(bookId);
		String sql = "UPDATE book "
				   + "SET title = ?, author = ?, book_type = ?, publication_year = ?, period_id = ?, status = ?, rating = ?, notes = ? "
				   + "WHERE book_id = ?";
		
		try (Connection connection = connectDB();
			 PreparedStatement ps  = connection.prepareStatement(sql)){
			
			ps.setString(1, updatedBook.getTitle());
			ps.setString(2, updatedBook.getAuthor());
			ps.setString(3, updatedBook.getBookType().name().toUpperCase());
			ps.setInt(4, updatedBook.getYearPublished());
			ps.setInt(5, updatedBook.getPeriodId());
			ps.setString(6, updatedBook.getStatus().name().toUpperCase());
			ps.setInt(7, updatedBook.getRating());
			ps.setString(8, updatedBook.getNotes());
			ps.setInt(9, bookId);
			
			int rowsAffected = ps.executeUpdate();
			
			// this logic needs improving
	        if (rowsAffected == 1) {
	            System.out.println(
	                "Book ID: " + bookId + " updated successfully.");
	        } else {
	            System.out.println(
	                "No book found with ID: " + bookId);
	        }
		}
	}
	
	
	
	// method to update historical_period in database
	public void updateHistoricalPeriod(int periodId, HistoricalPeriod updatedPeriod) throws SQLException {
		
		validateID(periodId);
		String sql = "UPDATE historical_period "
				   + "SET name = ?, start_year = ?, end_year = ?, description = ? "
				   + "WHERE period_id = ?";
		
		try (Connection connection = connectDB();
			 PreparedStatement ps  = connection.prepareStatement(sql)){
			
			ps.setString(1, updatedPeriod.getName());
			ps.setInt(2, updatedPeriod.getStart_year());
			ps.setInt(3, updatedPeriod.getEnd_year());
			ps.setString(4, updatedPeriod.getDescription());
			ps.setInt(5, periodId);
			
			int rowsAffected = ps.executeUpdate();

	        if (rowsAffected == 1) {
	            System.out.println(
	                "Period ID: " + periodId + " updated successfully.");
	        } else {
	            System.out.println(
	                "No period found with ID: " + periodId);
	        }
		}	
	}
	
	
	
	// method to delete book from database
	public void deleteBook(int bookId) throws SQLException {
		
		validateID(bookId);
		String sql = "DELETE FROM book WHERE book_id = ?";
		
		try(Connection connection = connectDB();
				PreparedStatement ps = connection.prepareStatement(sql)){
			
			ps.setInt(1, bookId);
			
			int rowsAffected = ps.executeUpdate();
			
	        if (rowsAffected == 1) {
	            System.out.println(
	                "Book ID: " + bookId + " deleted successfully.");
	        } else {
	            System.out.println(
	                "No Book found with ID: " + bookId);
	        }
		}
	}
	
	
	
	// method to delete historical_period from database
	public void deleteHistoricalPeriod(int periodId) throws SQLException {
		
		validateID(periodId);
		String sql = "DELETE FROM historical_period WHERE period_id = ?";
		
		try(Connection connection = connectDB();
			PreparedStatement ps  = connection.prepareStatement(sql)){
			
			ps.setInt(1, periodId);
			
			int rowsAffected = ps.executeUpdate();
			
	        if (rowsAffected == 1) {
	            System.out.println(
	                "Period ID: " + periodId + " deleted successfully.");
	        } else {
	            System.out.println(
	                "No Period found with ID: " + periodId);
	        }
		}
	}
	
	//TODO
	// create environment varibale DBPassword
	
	// class validation methods
	
	// testing
}