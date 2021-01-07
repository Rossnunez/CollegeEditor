package model;

import java.io.Serializable;
import java.util.Arrays;

public class Textbook implements Serializable{
	private String bookTitle;
	private String isbn;
	private Name[] author;
	private double price;
 
	public Textbook(String bookTitle, String isbn, Name[] author, double price) {
		super();
		this.bookTitle = bookTitle;
		this.isbn = isbn;
		this.author = author;
		this.price = price;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Name[] getAuthor() {
		return author;
	}

	public void setAuthor(Name[] author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Textbook [bookTitle=" + bookTitle + ", isbn=" + isbn + ", author=" + Arrays.toString(author) + ", price=" + price + "]";
	}

}
