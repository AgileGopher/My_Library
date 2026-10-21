package driver;

import java.sql.SQLException;

import data_classes.Book;
import db_manager.DatabaseManager;


public class Driver {

	private static DatabaseManager dbManager;
	
	
	public Driver() {
		dbManager = DatabaseManager.getInstance();
	}
	
	
	public static void main(String[] args) {
		

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
