package data_classes;

public class HistoricalPeriod {

	//class fields
	private int periodID;
	private String name;
	private int start_year;
	private int end_year;
	private String description;
	
	
	
	// constructor
	public HistoricalPeriod(int id, String name, int startYear, int endYear, String description) {
		this.periodID    = id;
		this.name        = name;
		this.start_year  = startYear;
		this.end_year    = endYear;
		this.description = description;
	}


	
	// getters
	public int getPeriodID() {
		return periodID;
	}


	public String getName() {
		return name;
	}


	public int getStart_year() {
		return start_year;
	}


	public int getEnd_year() {
		return end_year;
	}


	public String getDescription() {
		return description;
	}


	
	// toString
	@Override
	public String toString() {
		return "HistoricalPeriod [periodID=" + periodID + ", name=" + name + ", start_year=" + start_year
				+ ", end_year=" + end_year + ", description=" + description + "]";
	}
	
	
}
