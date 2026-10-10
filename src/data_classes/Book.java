package data_classes;

public class Book {

	// class fields
	int bookId;
	String name;
	String author;
	BookType bookType;
	Integer yearPublished;
	int periodId;
	BookStatus status;
	Integer rating;
	String notes;
	
	
	
	// constructor
	public Book(int bookId, String name, String author, BookType type, Integer yearPublished, int periodId, BookStatus status, Integer rating, String notes) {
		this.bookId        = bookId;
		this.name          = name;
		this.author        = author;
		this.bookType      = type;
		this.yearPublished = yearPublished;
		this.periodId      = periodId;
		this.status        = status;
		this.notes         = notes;
	}
	
	
	public Book() {
		super();
	}



	// getters
	public int getBookId() {
		return bookId;
	}



	public String getName() {
		return name;
	}



	public String getAuthor() {
		return author;
	}



	public BookType getBookType() {
		return bookType;
	}



	public Integer getYearPublished() {
		return yearPublished;
	}



	public int getPeriodId() {
		return periodId;
	}



	public BookStatus getStatus() {
		return status;
	}



	public Integer getRating() {
		return rating;
	}



	public String getNotes() {
		return notes;
	}



	// setters
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}


	public void setYearPublished(Integer yearPublished) {
		this.yearPublished = yearPublished;
	}


	public void setStatus(BookStatus status) {
		this.status = status;
	}


	public void setRating(Integer rating) {
		this.rating = rating;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}



	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", name=" + name + ", author=" + author + ", bookType=" + bookType
				+ ", yearPublished=" + yearPublished + ", periodId=" + periodId + ", status=" + status + ", rating="
				+ rating + ", notes=" + notes + "]";
	}
	
	
}
