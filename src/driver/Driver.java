package driver;

import java.sql.SQLException;
import java.util.Map;

import data_classes.Book;
import data_classes.HistoricalPeriod;
import db_manager.DatabaseManager;
import enums.BookStatus;
import enums.BookType;
import io_classes.Keyboard;
import io_classes.Librarian;


public class Driver {

	// fields
	private static DatabaseManager dbManager;
	private static Keyboard kb;
	private static Librarian librarian;
	
	
	
	// constructor
	public Driver() {
		dbManager = DatabaseManager.getInstance();
		kb        = new Keyboard();
		librarian = new Librarian();
	}
	
	
	
	// method to launch main menu
	public void runProgram() throws SQLException {
		
		int choice;
		boolean proceed = true;
		
		String promptMsg = "Make a selection:\n";
		String errorMsg  = "Invalid entry, enter and integer value in the range (1 - 4)\n";
		
		while(proceed) {
			
			System.out.println("------- History Library Management System -------\n"
					+ "\n-------- Home Menu ---------\n"
					+ "1. CRUD Book Menu\n"
					+ "2. CRUD historical Period Menu\n"
					+ "3. Display Library\n"
					+ "4. Quit\n");
			
			choice = kb.readInteger(promptMsg, errorMsg, 1, 4);
			
			switch(choice) {
			
				case 1:
					runBookMenu();
					break;
					
				case 2:
					runHistoricalPeriodMenu();
					break;
					
				case 3:
					Map<Book, HistoricalPeriod> library = dbManager.retrieveData();
					dbManager.displayLibrary(library);
					break;
					
				case 4:
					proceed = false;
					System.out.println("Bye for now!");
					break;
					
				default:
					System.out.println("Invalid entry!");
			}
		}
	}
	
	
	
	// method to launch book menu
	void runBookMenu() throws SQLException {
		
		int choice;
		boolean proceed = true;
		
		
		String promptMsg = "Make a selection:\n";
		String errorMsg  = "Invalid entry, enter and integer value in the range (1 - 6)\n";
		
		while(proceed) {
			
			System.out.println("--------Driver CRUD Menu--------\n"
					+ "1. Create Book\n"
					+ "2. Read Book Information\n"
					+ "3. Update Book Information\n"
					+ "4. Delete Book\n"
					+ "5. Back");
			
			choice = kb.readInteger(promptMsg, errorMsg, 1, 5);
			
			switch(choice) {
				
				case 1:
					Book newBook = librarian.getNewBookDataFromUser();
					dbManager.createNewBook(newBook);
					break;
					
				case 2:
					int inputId;
					String idPromptMsg = "enter book id: \n";
					String idErrorMsg  = "error, invalid id..\n";
					
					inputId = kb.readInteger(idPromptMsg, idErrorMsg);
					System.out.println(dbManager.retrieveBook(inputId));
					break;
					
				case 3:
					int bookId;
					String author;
					String title;
					String type;
					Integer yearPublished;
					int periodId;
					String status;
					Integer rating;
					String notes;
					
					bookId = kb.readInteger("enter bookId: \n", "invalid id, try again.. \n");
					
					for(Book book : dbManager.retrieveBooks()) {
						if(book.getBookId() == bookId) {
							author = kb.readString("update author: ", "invalid author..");
							book.setAuthor(author);
							title = kb.readString("update title: ", "invalid title..");
							book.setTitle(title);
							type = kb.readString("update type: ", "invalid type..");
							book.setBookType(BookType.valueOf(type.trim().toUpperCase()));
							yearPublished = kb.readInteger("update year published: ", "invalid year..");
							book.setYearPublished(yearPublished);
							periodId = kb.readInteger("update period id: ", "invlalid period id..");
							book.setPeriodId(periodId);
							status = kb.readString("update status", "invalid status..");
							book.setStatus(BookStatus.valueOf(status.trim().toUpperCase()));
							rating = kb.readInteger("update rating", "invalid rating");
							book.setRating(rating);
							notes = kb.readString("update notes", "invalid notes..");
							book.setNotes(notes);
							
							dbManager.updateBook(bookId, book);
							System.out.println("book updated: " + bookId);
						}
					}
					break;
					
				case 4:
					
					
			}
		}
	}
	
	
	
	// method to launch historical Period menu
	void runHistoricalPeriodMenu() throws SQLException {
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		Driver d = new Driver();
		
		try {
			d.runProgram();
		}
		catch(IllegalArgumentException | SQLException e) {
			System.out.println(e);	
		}
	}
}
