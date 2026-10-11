package driver;

import db_manager.DatabaseManager;

public class Driver {

	private static DatabaseManager dbManager;
	
	
	public Driver() {
		dbManager = new DatabaseManager();
	}
	
	
	public static void main(String[] args) {
		Driver driver = new Driver();
		
		dbManager.retrieveData();
		System.out.println("yo");
	}
}
