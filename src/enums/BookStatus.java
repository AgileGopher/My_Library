package enums;

public enum BookStatus {

	PLANNED ("PLANNED"),
	READING ("READING"),
	FINISHED ("FINISHED");
	
	
	private String statusType;
	
	
	
	BookStatus(String status){
		this.statusType = status;
	}
	
	
	
	public String getStatus() {
		return statusType;
	}
	
	
	
	public static BookStatus fromString(String inputStatus) {
		for(BookStatus status : BookStatus.values()) {
			if(status.statusType.equalsIgnoreCase(inputStatus)) {
				return status;
			}
		}
		return BookStatus.PLANNED;
	}
	
}
