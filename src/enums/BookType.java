package enums;

public enum BookType {

	HISTORICAL_FICTION ("HISTORICAL_FICTION"),
	NARRATIVE_HISTORY ("NARRATIVE_HISTORY"),
	ACADEMIC_HISTORY ("ACADEMIC_HISTORY"),
	PRIMARY_SOURCE ("PRIMARY_SOURCE");
	
	
	private String bookType;
	
	
	BookType(String type){
		this.bookType = type;
	}
	
	
	public String getType() {
		return bookType;
	}
	
	
	public static BookType fromString(String inputType) {
		for(BookType type : BookType.values()) {
			if(type.bookType.equalsIgnoreCase(inputType)) {
				return type;
			}
		}
		return BookType.PRIMARY_SOURCE;
	}
	
	
}
