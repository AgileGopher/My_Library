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
	private static final int MIN_ID_VALUE      = 1;
	private static final int MAX_TITLE_LENGTH  = 100;
	private static final int MAX_AUTHOR_LENGTH = 50;
	private static final int MIN_RATING_VALUE  = 1;
	private static final int MAX_RATING_VALUE  = 10;
	private static final int MAX_NOTES_LENGTH  = 500;
	
	
	
	// constructors
	public Book() {
		super();
	}
	
	public Book(int bookId, String title, String author, BookType type, Integer yearPublished, int periodId, BookStatus status, Integer rating, String notes) {
		validateId(bookId);
		this.bookId = bookId;
		validateTitle(title);
		this.title = title;
		validateAuthor(author);
		this.author = author;
		this.bookType = type;
		validateYear(yearPublished);
		this.yearPublished = yearPublished;
		validateId(periodId);
		this.periodId = periodId;
		this.status = status;
		validateRating(rating);
		this.rating = rating;
		validateNotes(notes);
		this.notes = notes;
	}

	
	
	// validation methods
	private static void validateId(int id) {
		if(id < MIN_ID_VALUE) {
			throw new IllegalArgumentException("Book Error, invalid book id: " + id);
		}
	}
	
	private static void validateTitle(String title) {
		if(title.isBlank() || title.length() > MAX_TITLE_LENGTH) {
			throw new IllegalArgumentException("Book Error, invalid title: " + title);
		}
	}
	
	private static void validateAuthor(String author) {
		if(author.isBlank() || author.length() > MAX_AUTHOR_LENGTH) {
			throw new IllegalArgumentException("Book Error, invalid title: " + author);
		}
	}
	
	private static void validateYear(int year){
		if(year > Year.now().getValue()) {
			throw new IllegalArgumentException("Book Error, invalid year: " + year);
		}
	}
	
	private static void validateRating(int rating){
		if(rating < MIN_RATING_VALUE || rating > MAX_RATING_VALUE) {
			throw new IllegalArgumentException("Book Error, invalid rating: " + rating);
		}
	}
	
	private static void validateNotes(String notes) {
		if(notes.length() > MAX_NOTES_LENGTH) {
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
		validateId(bookId);
		this.bookId = bookId;
	}
	
	public void setPeriodId(int periodId) {
		validateId(periodId);
		this.periodId = periodId;
	}

	public void setTitle(String title) {
		validateTitle(title);
		this.title = title;
	}

	public void setAuthor(String author) {
		validateAuthor(author);
		this.author = author;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	public void setYearPublished(Integer yearPublished) {
		validateYear(yearPublished);
		this.yearPublished = yearPublished;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}

	public void setRating(Integer rating) {
		validateRating(rating);
		this.rating = rating;
	}

	public void setNotes(String notes) {
		validateNotes(notes);
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
