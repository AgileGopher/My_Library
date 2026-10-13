package driver;

import java.sql.SQLException;

import db_manager.DatabaseManager;

public class Driver {

	private static DatabaseManager dbManager;
	
	
	public Driver() {
	}
	
	
	public static void main(String[] args) {
		dbManager = new DatabaseManager();
		dbManager.retrieveData();
		System.out.println("operation successful..");
		dbManager.createNewBook();
	}
}
