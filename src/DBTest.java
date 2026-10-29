import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import data_classes.Book;
import data_classes.BookStatus;
import data_classes.BookType;
import data_classes.HistoricalPeriod;
import db_manager.DatabaseManager;

class DBTest {

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	// retrieval tests
	@Test
	void retrieveBooks_returnslist() throws Exception {
		
		DatabaseManager dbManager = DatabaseManager.getInstance();
		
		List<Book> books = dbManager.retrieveBooks();
		
		assertNotNull(books);
	}
	
	
	
	@Test
	void retrievePeriods_returnslist() throws Exception {
		
		DatabaseManager dbManager = DatabaseManager.getInstance();
		
		List<HistoricalPeriod> periods = dbManager.retrieveHistoricalPeriods();
		
		assertNotNull(periods);
	}
	
	
	
	@Test
	void retrieveBook_withExcistingId() throws Exception {
		
		DatabaseManager dbManager = DatabaseManager.getInstance();
		int id = 1;
		
		Book book = dbManager.retrieveBook(id);
		
		assertNotNull(book);
		assertEquals("Gilgamesh the King", book.getTitle());
		assertEquals(id, book.getBookId());
	}
	
	
	
	@Test
	void retrieveBook_withNonexistentId() throws Exception {
		
		DatabaseManager dbManager = DatabaseManager.getInstance();
		int nonexistentId = 999;
		
		Book book = dbManager.retrieveBook(nonexistentId);
		
		assertNull(book);
	}

	
	
	@Test
	void retrievePeriod_withExcistingId() throws Exception {
		
		DatabaseManager dbManager = DatabaseManager.getInstance();
		int periodId = 1;
		
		HistoricalPeriod period = dbManager.retrieveHistoricalPeriod(periodId);
		
		assertNotNull(period);
		assertEquals("Early Civilizations & Mesopotamia", period.getName());
		assertEquals(periodId, period.getPeriodID());
	}
	
	
	
	@Test
	void retrievePeriod_withNonexistentId() throws Exception {
		
		DatabaseManager dbManager = DatabaseManager.getInstance();
		int nonexistentId = 999;
		
		Book book = dbManager.retrieveBook(nonexistentId);
		
		assertNull(book);
	}
	
	
	
	
	@Test
	void createNewBook_savesBookSuccessfully() throws SQLException {
	
	    DatabaseManager db = DatabaseManager.getInstance();
	
	    // Get a valid historical period for the foreign key.
	    List<HistoricalPeriod> periods = db.retrieveHistoricalPeriods();
	
	    assertFalse(periods.isEmpty(), "The test database must contain a historical period.");
	
	    int periodId = periods.get(0).getPeriodID();
	
	    // Create a unique title so this test can identify its own record.
	    String testTitle = "JUnit Test " + UUID.randomUUID();
	
	    Book newBook = new Book();
	    newBook.setTitle(testTitle);
	    newBook.setAuthor("Test Author");
	    newBook.setBookType(BookType.values()[0]);
	    newBook.setYearPublished(2000);
	    newBook.setPeriodId(periodId);
	    newBook.setStatus(BookStatus.values()[0]);
	    newBook.setRating(8);
	    newBook.setNotes("Created by a JUnit test.");
	
	    try {
	        // Act: save the book to the database.
	        db.createNewBook(newBook);
	
	        // Retrieve the saved record using its unique title.
	        Book savedBook = db.retrieveBooks()
	                .stream()
	                .filter(book -> testTitle.equals(book.getTitle()))
	                .findFirst()
	                .orElse(null);
	
	        // Assert: verify that the record was saved correctly.
	        assertNotNull(savedBook, "The book should have been saved.");
	
	        assertEquals(testTitle, savedBook.getTitle());
	        assertEquals("Test Author", savedBook.getAuthor());
	        assertEquals(newBook.getBookType(), savedBook.getBookType());
	        assertEquals(2000, savedBook.getYearPublished());
	        assertEquals(periodId, savedBook.getPeriodId());
	        assertEquals(newBook.getStatus(), savedBook.getStatus());
	        assertEquals(8, savedBook.getRating());
	        assertEquals("Created by a JUnit test.", savedBook.getNotes());
	
	    } finally {
	        // Clean up the test record, even if an assertion fails.
	        Book savedBook = db.retrieveBooks()
	                .stream()
	                .filter(book -> testTitle.equals(book.getTitle()))
	                .findFirst()
	                .orElse(null);
	
	        if (savedBook != null) {
	            db.deleteBook(savedBook.getBookId());
	        }
	    }
	}
	

}
