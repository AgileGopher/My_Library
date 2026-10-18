package driver;

import java.sql.SQLException;

import db_manager.DatabaseManager;

public class Driver {

	private static DatabaseManager dbManager;
	
	
	public Driver() {
	}
	
	
	public static void main(String[] args) {
		
		dbManager = new DatabaseManager();
		
		try {
			dbManager.retrieveData();
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
