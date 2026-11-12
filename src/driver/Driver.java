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
			
			choice = kb.readInteger(promptMsg, errorMsg, 1, 4);
			
			switch(choice) {
			
				case 1:
					runBookMenu();
					break;
					
				case 2:
					runHistoricalPeriodMenu();
					break;
					
				case 3:
					System.out.println("Working on it..");
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
