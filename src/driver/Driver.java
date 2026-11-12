package driver;

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
	
	
	public static void main(String[] args) {
		
	}
}
