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
	private static Librarian librarian;
	Connection connection;

	
	
	// constructor
	public DatabaseManager() {
		historical_periods = new ArrayList<>();
		bookCollection     = new ArrayList<>();
		librarian          = new Librarian();
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

	
	public Book retrieveBook(int id) throws SQLException {
		
		Book retrievedBook   = new Book();
		PreparedStatement ps = null;
		ResultSet rs         = null;
		
		try {
			connection = connectDB();
			ps = connection.prepareStatement("SELECT * FROM BOOK WHERE book_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
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
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return retrievedBook;
	}
	
	
	
	public HistoricalPeriod retrieveHistoricalPeriod(int id)  throws SQLException {
		
		HistoricalPeriod retrievedHistoricalPeriod = new HistoricalPeriod();
		PreparedStatement ps = null;
		ResultSet rs         = null;
		
		try {
			connection = connectDB();
			ps = connection.prepareStatement("SELECT * FROM historical_period WHERE period_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				retrievedHistoricalPeriod.setPeriodID(id);
				retrievedHistoricalPeriod.setName(rs.getString("name"));
				retrievedHistoricalPeriod.setStart_year(rs.getInt("start_year"));
				retrievedHistoricalPeriod.setEnd_year(rs.getInt("end_year"));
				retrievedHistoricalPeriod.setDescription(rs.getString("description"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return retrievedHistoricalPeriod;
	}
	
	
	
	public void createNewBook() {
		Book newBook = librarian.getNewBookDataFromUser();
		try {
			connection = connectDB();
			PreparedStatement ps = connection.prepareStatement("INSERT INTO book (title, author, book_type, publication_year, period_id, status, rating, notes) VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1, newBook.getTitle()); // need to capitalize somehow
			ps.setString(2, newBook.getAuthor());
			ps.setString(3, String.valueOf(newBook.getBookType()).toUpperCase());
			ps.setInt(4, newBook.getYearPublished());
			ps.setInt(5, newBook.getPeriodId());
			ps.setString(6, String.valueOf(newBook.getStatus()).toUpperCase());
			ps.setInt(7, newBook.getRating());
			ps.setString(8, newBook.getNotes());
			ps.execute();
			ps.close();
			connection.close();
			System.out.println("Book: " + newBook.getTitle() + " added successfully..");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void createNewHistoricalPeriod() {
		
	}
	// retrieve a Book == DONE
	
	// retrieve an historical_period == DONE
	
	// create new Book == DONE
	
	// create new historical_record == 
	
	// update Book details ==
	
	// update historical record details ==
	
	// delete Book ==
	
	// delete historical record ==
}