package io_classes;

import data_classes.Book;
import data_classes.BookStatus;
import data_classes.BookType;
import data_classes.HistoricalPeriod;

public class Librarian {
	
	private Keyboard kb;

	
	public Librarian() {
		kb = new Keyboard();
	}
	
	
	// method to take in new book data from user
	public Book getNewBookDataFromUser() {
		Book book = new Book();
		book.setTitle(kb.readString("enter title: ", "invalid title, please try again"));
		book.setAuthor(kb.readString("enter author: ", "Invalid author, please try again"));
		book.setBookType(BookType.valueOf(kb.readString("enter book type: ", "Invalid book type, please try again")));
		book.setYearPublished(kb.readInteger("enter year published: ", "Invalid year, please try again"));
		book.setPeriodId(kb.readInteger("enter period id: ", "invalid period id, please try again")); // give a branched option to view list of periods?
		book.setStatus(BookStatus.valueOf(kb.readString("enter book status: ", "Invalid book status, please try again")));
		book.setRating(kb.readInteger("enter rating: ", "Invalid rating, please try again"));
		book.setNotes(kb.readString("enter note: ", "Invalid note, please try again"));
		return book;
	}
	
	
	public HistoricalPeriod getNewHistoricalPeriodDataFromUser() {
		HistoricalPeriod newHistoricalPeriod = new HistoricalPeriod();
		newHistoricalPeriod.setName(kb.readString("enter name: ", "invalid name, please try again"));
		newHistoricalPeriod.setStart_year(kb.readInteger("enter start year: ", "invalid start year, please try again"));
		newHistoricalPeriod.setEnd_year(kb.readInteger("enter end year: ", "invalid end year, please try again"));
		newHistoricalPeriod.setDescription(kb.readString("enter description: ", "invalid description, please try again"));
		return newHistoricalPeriod;
	}
	
}
