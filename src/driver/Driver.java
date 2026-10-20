package driver;

import java.sql.SQLException;

import data_classes.Book;
import db_manager.DatabaseManager;
import io_classes.Librarian;

public class Driver {

	private static DatabaseManager dbManager;
	private static Librarian librarian;
	
	
	public Driver() {
	}
	
	
	public static void main(String[] args) {
		
		dbManager = new DatabaseManager();
		librarian = new Librarian();
		
		try {
			//Book book = librarian.getNewBookDataFromUser();
			//dbManager.createNewBook(book);
			dbManager.deleteBook(40);
		} 
		catch (SQLException e) {
			// TODO correct error handlinh message?
			e.printStackTrace();
		}
		System.out.println("operation successful..");
		// dbManager.createNewHistoricalPeriod();
		// dbManager.updateBook(39);
	}
}
