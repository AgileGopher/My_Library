package data_classes;

public class HistoricalPeriod {

	//class fields
	private int periodID;
	private String name;
	private int start_year;
	private int end_year;
	private String description;
	
	
	
	// symbolic constants
	
	
	
	// constructors
	public HistoricalPeriod() {
		super();
	}
	
	public HistoricalPeriod(int id, String name, int startYear, int endYear, String description) {
		validateId(id);
		this.periodID = id;
		validateName(name);
		this.name = name;
		validateStartYear(startYear);
		this.start_year = startYear;
		validateEndyear(endYear);
		this.end_year = endYear;
		validateDescription(description);
		this.description = description;
	}


	
	// validation methods
	private static void validateId(int id) {
		
	}
	
	private static void validateName(String name) {
		
	}
	
	private static void validateStartYear(int startYear) {
		
	}
	
	private static void validateEndyear(int endYear) {
		
	}
	
	private static void validateDescription(String description) {
		
	}
	
	
	
	// setters
	public void setPeriodID(int periodID) {
		validateId(periodID);
		this.periodID = periodID;
	}


	public void setName(String name) {
		validateName(name);
		this.name = name;
	}


	public void setStart_year(int start_year) {
		validateStartYear(start_year);
		this.start_year = start_year;
	}


	public void setEnd_year(int end_year) {
		validateEndyear(end_year);
		this.end_year = end_year;
	}


	public void setDescription(String description) {
		validateDescription(description);
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
		return "HistoricalPeriod [periodID=" + periodID + 
				", name=" + name + 
				", start_year=" + start_year + 
				", end_year=" + end_year + 
				", description=" + description + 
				"]";
	}
	
	
}
