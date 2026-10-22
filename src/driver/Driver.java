package driver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_classes.Book;
import db_manager.DatabaseManager;


public class Driver {

	private static DatabaseManager dbManager;
	
	
	public Driver() {
		
	}
	
	
	public static void main(String[] args) {
		
		dbManager = DatabaseManager.getInstance();

		try {
			//Book book = librarian.getNewBookDataFromUser();
			//dbManager.createNewBook(book);
			//dbManager.deleteBook(40);
			List<Book> library = new ArrayList<>();
			library = dbManager.retrieveBooks();
			
			for(Book b : library) {
				System.out.println(b.getTitle() + " \n");
			}
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
