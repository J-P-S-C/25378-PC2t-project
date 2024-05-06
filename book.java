import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Book implements Comparable<Book>{
	protected String title;
	private String author;
	private int year;
	protected StateOfBook bookstate;
	public enum StateOfBook {VYPUJCENO,VRACENO};
	
	
	public Book(String title, String author, int year) {
		this.title = title;
		this.author = author;
		this.year = year;
		bookstate = StateOfBook.VRACENO;//neutral state
	}
	
	public String GetTitle() {
		return title;
	}
	
	public void SetTitle(String title) {
		this.title = title;
	}
	
	public String GetAuthor() {
		return author;
	}
	
	public void SetAuthor(String author) {
		this.author = author;
	}

	public int GetYear() {
		return year;
	}
	
	public void SetYear(int year) 
	{
		this.year = year;
	}
	
	public void SetStateOfBook(StateOfBook state) {
		bookstate = state;
	}
	
	public StateOfBook GetStateOfBook() {
		return bookstate;
	}
	
	public int compareTo(Book o) {
		return this.title.compareTo(o.title);
	}

	public String GetGenre() {
		return null;
	}
 }
