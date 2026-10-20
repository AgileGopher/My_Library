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
import io_classes.Librarian;

public class DatabaseManager {
	
	// fields
	private static List<HistoricalPeriod> historical_periods;
	private static List<Book> bookCollection;
	private final Librarian librarian;

	
	
	// constructor
	public DatabaseManager() {
		historical_periods = new ArrayList<>();
		bookCollection     = new ArrayList<>();
		librarian          = new Librarian();
	}


	
	// getters
	public List<HistoricalPeriod> getHistorical_periods() {
		return historical_periods;
	}

	public List<Book> getBookCollection() {
		return bookCollection;
	}
	
	
	
	// method to create a database connection
	private Connection connectDB() throws SQLException {
		
		String url      = "jdbc:mysql://localhost:3306/db_library";
		String username = "root";
		String password = "Ronaldo";
	
		return DriverManager.getConnection(url, username, password);
	}
	
	
	
	// method to connect to database, retrieve all data, and populate bookCollection and historical_periods lists
	public void retrieveData() throws SQLException {
		
		try (Connection con = connectDB()){
			
			List<HistoricalPeriod> periods = retrieveHistoricalPeriods(con);
			List<Book> books               = retrieveBooks(con);
			
			historical_periods = periods;
			bookCollection     = books;
		}
	}
	
	
	
	// method to retrive histocial records from database
	private static List<HistoricalPeriod> retrieveHistoricalPeriods(Connection con) throws SQLException {
		
		List<HistoricalPeriod> historical_periods = new ArrayList<>();
		
		String sql = "SELECT * FROM historical_period";
		
		try(Statement stmt = con.createStatement();
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
	private static List<Book> retrieveBooks(Connection con) throws SQLException {
		
		List<Book> books = new ArrayList<>();
		
		String sql = "SELECT * FROM Book";
		
		try (Statement stmt = con.createStatement();
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

		String sql = "SELECT * FROM BOOK WHERE book_id = ?";
		
		try (Connection connection = connectDB();
			PreparedStatement ps = connection.prepareStatement(sql)){
			
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
		
		String sql = "SELECT * FROM historical_period WHERE period_id = ?";
		
		try (Connection connection = connectDB();
			 PreparedStatement ps = connection.prepareStatement(sql)){
			
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
	public void createNewBook() throws SQLException {
		
		Book newBook = librarian.getNewBookDataFromUser();
		
		String sql = "INSERT INTO book "
				   + "(title, author, book_type, publication_year, "
				   + "period_id, status, rating, notes) "
				   + "VALUES(?,?,?,?,?,?,?,?)";
		
		try (Connection connection = connectDB();
			 PreparedStatement ps  = connection.prepareStatement(sql)) {
			
			ps.setString(1, newBook.getTitle()); // need to capitalize somehow
			ps.setString(2, newBook.getAuthor());
			ps.setString(3, newBook.getBookType().name());
			ps.setInt(4, newBook.getYearPublished());
			ps.setInt(5, newBook.getPeriodId());
			ps.setString(6, newBook.getStatus().name());
			ps.setInt(7, newBook.getRating());
			ps.setString(8, newBook.getNotes());
			
			ps.executeUpdate();
			
			System.out.println("Book: " 
			                  + newBook.getTitle()
			                  + " added successfully..");
	   }
	}
	
	
	
	// this method calls librarian to grab new HistoricalPeriod details from user
	public void createNewHistoricalPeriod() throws SQLException {
		
		HistoricalPeriod newPeriod = librarian.getNewHistoricalPeriodDataFromUser();
		
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
	
	
	
	public void updateBook(int bookId) throws SQLException {
		
		//TODO need to alter this method to take in updatedBook obejct with id instead of calling librarian to do it inside method
		Book updatedBook = librarian.getNewBookDataFromUser();
		
		String sql = "UPDATE book "
				   + "SET title = ?, author = ?, book_type = ?, publication_year = ?, period_id = ?, status = ?, rating = ?, notes = ? "
				   + "WHERE book_id = ?";
		
		try (Connection connection = connectDB();
			PreparedStatement ps = connection.prepareStatement(sql)){
			
			ps.setString(1, updatedBook.getTitle());
			ps.setString(2, updatedBook.getAuthor());
			ps.setString(3, String.valueOf(updatedBook.getBookType()).toUpperCase());
			ps.setInt(4, updatedBook.getYearPublished());
			ps.setInt(5, updatedBook.getPeriodId());
			ps.setString(6, String.valueOf(updatedBook.getStatus()).toUpperCase());
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
	
	
	public void updateHistoricalPeriod(int periodId) throws SQLException {
		
		HistoricalPeriod updatedPeriod = librarian.getNewHistoricalPeriodDataFromUser();
		
		String sql = "UPDATE historical_period "
				   + "SET name = ?, start_year = ?, end_year = ?, description = ? "
				   + "WHERE period_id = ?";
		
		try (Connection connection = connectDB();
			 PreparedStatement ps = connection.prepareStatement(sql)){
			
			ps.setString(1, updatedPeriod.getName());
			ps.setInt(2, updatedPeriod.getStart_year());
			ps.setInt(3, updatedPeriod.getEnd_year());
			ps.setString(4, updatedPeriod.getDescription());
			ps.setInt(5, periodId);
			
			int rowsAffected = ps.executeUpdate();

	        if (rowsAffected == 1) {
	            System.out.println(
	                "Book ID: " + periodId + " updated successfully.");
	        } else {
	            System.out.println(
	                "No book found with ID: " + periodId);
	        }
		}	
	}
	
	
	public void deleteBook(int bookId) {
		
	}
	
	
	public void deleteHistoricalPeriod(int periodId) {
		
	}
	
	//TODO
	// create new Book 					== DONE
	
	// create new historical_record 	== DONE
	
	// retrieve a Book				    == DONE
	
	// retrieve an historical_period	== DONE
	
	// update Book details				== DONE
	
	// update historical record details == DONE
	
	// delete Book                      == 
	
	// delete historical record         ==
}