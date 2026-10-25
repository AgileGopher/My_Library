import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import data_classes.Book;
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
}
