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
		try {
			// System.out.println(dbManager.retrieveBook(46));
			System.out.println(dbManager.retrieveHistoricalPeriod(4));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
