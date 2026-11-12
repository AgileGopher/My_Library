package driver;

import java.sql.SQLException;

import db_manager.DatabaseManager;
import io_classes.Keyboard;


public class Driver {

	// fields
	private static DatabaseManager dbManager;
	private static Keyboard kb;
	
	
	
	// constructor
	public Driver() {
		dbManager = DatabaseManager.getInstance();
		kb        = new Keyboard();
	}
	
	
	
	// method to launch main menu
	public void runProgram() throws SQLException {
		
		int choice;
		boolean proceed = true;
		
		String promptMsg = "Make a selection:\n";
		String errorMsg = "Invalid entry, enter and integer value in the range ()\n";
		
		while(proceed) {
			
			System.out.println("------- History Library Management System -------\n"
					+ "\n-------- Home Menu ---------\n"
					+ "1. CRUD Book Menu\n"
					+ "2. CRUD historical Period Menu\n"
					+ "3. Display full Library\n"
					+ "4. Quit\n");
		}
	}
	
	
	
	// method to launch book menu
	void runBookMenu() throws SQLException {
		
	}
	
	
	
	// method to launch historical Period menu
	void runHistoricalPeriodMenu() throws SQLException {
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
	}
}
