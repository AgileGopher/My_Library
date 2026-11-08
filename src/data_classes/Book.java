package data_classes;

import java.time.Year;

public class Book {

	// class fields
	int bookId;
	String title;
	String author;
	BookType bookType;
	Integer yearPublished;
	int periodId;
	BookStatus status;
	Integer rating;
	String notes;
	
	
	
	// symbolic constants
	
	
	
	// constructors
	public Book() {
		super();
	}
	
	public Book(int bookId, String title, String author, BookType type, Integer yearPublished, int periodId, BookStatus status, Integer rating, String notes) {
		this.bookId        = bookId;
		this.title         = title;
		this.author        = author;
		this.bookType      = type;
		this.yearPublished = yearPublished;
		this.periodId      = periodId;
		this.status        = status;
		this.rating        = rating;
		this.notes         = notes;
	}

	
	
	// validation methods
	private static void validateId(int id) {
		if(id < 1) {
			throw new IllegalArgumentException("Book Error, invalid book id: " + id);
		}
	}
	
	private static void validateTitle(String title) {
		if(title.isBlank() || title.length() > 100) {
			throw new IllegalArgumentException("Book Error, invalid title: " + title);
		}
	}
	
	private static void validateAuthor(String author) {
		if(author.isBlank() || author.length() > 50) {
			throw new IllegalArgumentException("Book Error, invalid title: " + author);
		}
	}
	
	private static void validateYear(int year){
		if(year > Year.now().getValue()) {
			throw new IllegalArgumentException("Book Error, invalid year: " + year);
		}
	}
	
	private static void validateRating(int rating){
		if(rating < 1 || rating > 10) {
			throw new IllegalArgumentException("Book Error, invalid rating: " + rating);
		}
	}
	
	private static void validateNotes(String notes) {
		if(notes.length() > 500) {
			throw new IllegalArgumentException("Book Error, invalid notes: " + notes);
		}
	}


	// getters
	public int getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
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
	
	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}

	public void setTitle(String title) {
		this.title = title;
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



	// toString
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + 
			   ", name=" + title + 
			   ", author=" + author + 
			   ", bookType=" + bookType + 
			   ", yearPublished=" + yearPublished + 
			   ", periodId=" + periodId + 
			   ", status=" + status + 
			   ", rating=" + rating + 
			   ", notes=" + notes + 
			   "]";
	}
	
	
}
