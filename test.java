import java.util.ArrayList; 	import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

public class test {
	public class DatabaseManager {
	    private static final String URL = "jdbc:mysql://localhost:3306/library";
	    private static final String USERNAME = "username";
	    private static final String PASSWORD = "password";

	    public static List<Book> loadFromDatabase() {
	        List<Book> books = new ArrayList<>();

	        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
	            String selectBooksSQL = "SELECT * FROM books";

	            try (PreparedStatement stmt = conn.prepareStatement(selectBooksSQL)) {
	                ResultSet rs = stmt.executeQuery();
	                while (rs.next()) {
	                    String title = rs.getString("title");
	                    String author = rs.getString("author");
	                    int year = rs.getInt("year");
	                    String state = rs.getString("state");
	                    String genre = rs.getString("genre");
	                    String grade = rs.getString("grade");

	                    if (!genre.isEmpty()) {
	                        Roman.GenreOfBook genreEnum = Roman.GenreOfBook.valueOf(genre);
	                        books.add(new Roman(title, author, year, genreEnum, Book.StateOfBook.valueOf(state)));
	                    } else if (!grade.isEmpty()) {
	                        books.add(new Ucebnice(title, author, year, grade, Book.StateOfBook.valueOf(state)));
	                    } else {
	                        books.add(new Book(title, author, year));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return books;
	    }
	}
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		int volba;
		boolean beh = true;
		
		List<Book> listsbooks = new ArrayList<>();

		listsbooks.add(new Roman("Fairy relm", "Rosy Dila", 2002, Roman.GenreOfBook.FANTASY, Book.StateOfBook.VRACENO));
	    listsbooks.add(new Roman("Ready steady", "Rosy Dila", 2004, Roman.GenreOfBook.ACTION, Book.StateOfBook.VRACENO));
	    listsbooks.add(new Roman("Count your steps", "Vanesa Liliam", 1993, Roman.GenreOfBook.HORROR, Book.StateOfBook.VYPUJCENO));
	    listsbooks.add(new Roman("Egypt history", "David Somes", 2000, Roman.GenreOfBook.HISTORY, Book.StateOfBook.VRACENO));
	    listsbooks.add(new Roman("1+1 gets 3", "Susan Milers", 1995, Roman.GenreOfBook.ROMANCE, Book.StateOfBook.VYPUJCENO));
	    listsbooks.add(new Roman("Wings of fate", "Susan Milers", 2002, Roman.GenreOfBook.FANTASY, Book.StateOfBook.VRACENO));
	    listsbooks.add(new Roman("War never changes", "Troy Garner", 2002, Roman.GenreOfBook.HISTORY, Book.StateOfBook.VRACENO));
	    listsbooks.add(new Ucebnice("Textbook for basic math", "Troy Garner", 1980, "3rd grade", Book.StateOfBook.VYPUJCENO));
	    listsbooks.add(new Ucebnice("English for begginers", "Percy Sisal", 1975, "1st grade", Book.StateOfBook.VRACENO));
	    listsbooks.add(new Ucebnice("Higher algebra", "Troy Garner", 1981, "7th grade", Book.StateOfBook.VYPUJCENO));
	    listsbooks.add(new Ucebnice("Geography map", "Lucy Viliam", 2002, "6th grade", Book.StateOfBook.VRACENO));

		while(beh) {
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 ... Pridani nove knihy");
			System.out.println("2 ... Zmenit jmeno autor, rok vydani ci stav knihy.");
			System.out.println("3 ... Vymazit knihu dle nazvu");
			System.out.println("4... Zmenit stav knihy");
			System.out.println("5... Seznam knih.");
			System.out.println("6 ... Vyhledani informace o knize dle nazvu");
			System.out.println("7... Dle jmena autora ukazat seznam jejich del.");
			System.out.println("8... Vypsani vsech romanu dle zadaneho zanru.");
			System.out.println("9... Vypsani vypujcenych knih.");
			System.out.println("10... Ulozeni jedne knihy do souboru");
			System.out.println("11.. Precteni knihy ze souboru.");
			System.out.println("12... ukoncit program");
		
			//System.out.println("Number of books in the list: " + listsbooks.size());

		volba = sc.nextInt();
		sc.nextLine();
		
		switch (volba) {
			case 1:
				System.out.println("Pridani knihy, dle zadanych parametru:");
	            String title;
	            do {
	                System.out.println("Zadej nazev knihy:");
	                title = sc.nextLine().trim();
	                if (title.isEmpty()) {
	                    System.out.println("Nazev knihy nesmi byt prazdny.");
	                    System.out.println("");
	                }
	            } while (title.isEmpty());

	            String author;
	            do {
	            	System.out.println("Zadej jmeno autora:");
	            	author = sc.nextLine().trim();
	            	if (author.isEmpty()) {
	            		System.out.println("Jmeno autora nesmi byt prazdne.");
	                	System.out.println("");
	            	}
	            } while (author.isEmpty());	
	
				System.out.println("Zadej rok vydani:");
				int year = 0;
				try {
				    year = sc.nextInt();
				    sc.nextLine();
				} catch (InputMismatchException e) {
				    System.out.println("Neplatny vstup pro rok vydani. Zadejte prosim cele cislo.");
				    sc.nextLine(); 
				    continue;
				}
				
				int ChoiceOfBook;
				do {
				    System.out.println("Vyberte typ knihy, jsou dvě možosti: 1 = Roman, 2 = Ucebnice");
				    ChoiceOfBook = sc.nextInt();
				    
				    if (ChoiceOfBook != 1 && ChoiceOfBook != 2) {
				        System.out.println("Neplatna volba. Zadejte prosim 1 pro Roman nebo 2 pro Ucebnice.");
				        System.out.println("");
				    }
				} while (ChoiceOfBook != 1 && ChoiceOfBook != 2);
			
				
				if (ChoiceOfBook == 1) {
					System.out.println("Vyber žánr knihy:");
					System.out.println("1 - Romance, 2 - Horror, 3 - Historie, 4 - Fantazie, 5 - akcni");
					int genreIndex = sc.nextInt();
                    Roman.GenreOfBook genre = Roman.GenreOfBook.values()[genreIndex - 1]; // Adjust index

                    Roman kniha = new Roman(title, author, year, genre, Book.StateOfBook.VRACENO);
                    listsbooks.add(kniha);

				}
				else if (ChoiceOfBook == 2) {
					System.out.println("Zadejte pro jaky rocnik je ucebnice:");
                    String recommendedGrade = sc.next();
                    
                    Ucebnice textbook = new Ucebnice(title, author, year, recommendedGrade, Book.StateOfBook.VRACENO);
                    listsbooks.add(textbook);
                    
				}
				else {
					System.out.println("Jine moznosti nejsou, zadej 1 nebo 2");
				}
				
				break;
			case 2:
			    System.out.println("Zadejte nazev knihy, u ktere chcete zmenit atribut:");
			    String changeTitle = sc.nextLine().trim();

			    boolean bookFound = false;
			    for (Book book : listsbooks) {
			        if (book.GetTitle().equalsIgnoreCase(changeTitle)) { 
			            bookFound = true;

			            System.out.println("Vyberte, ktery atribut chcete zmenit:");
			            System.out.println("1 - Jmeno autora");
			            System.out.println("2 - Rok vydani");
			            System.out.println("3 - Stav knihy");

			            int attributeChoice = sc.nextInt();
			            sc.nextLine(); 

			            switch (attributeChoice) {
			                case 1:
			                    System.out.println("Zadejte nove jmeno autora:");
			                    String newAuthor = sc.nextLine().trim();
			                    book.SetAuthor(newAuthor);
			                    System.out.println("Jmeno autora bylo uspesne zmeneno.");
			                    break;
			                case 2:
			                    System.out.println("Zadejte novy rok vydani:");
			                    int newYear = 0;
			                    try {
			                        newYear = sc.nextInt();
			                        sc.nextLine(); 
			                        book.SetYear(newYear);
			                        System.out.println("Rok vydani byl uspesne zmenen.");
			                    } catch (InputMismatchException e) {
			                        System.out.println("Neplatny vstup pro rok vydani. Zadejte cele cislo.");
			                        sc.nextLine(); 
			                    }
			                    break;
			                case 3:
			                    System.out.println("Zadejte novy stav knihy (1 - VYPUJCENO, 2 - VRACENO):");
			                    int newStateIndex = sc.nextInt();
			                    sc.nextLine(); 
			                    if (newStateIndex == 1) {
			                        book.SetStateOfBook(Book.StateOfBook.VYPUJCENO);
			                        System.out.println("Stav knihy byl zmenen na VYPUJCENO.");
			                    } else if (newStateIndex == 2) {
			                        book.SetStateOfBook(Book.StateOfBook.VRACENO);
			                        System.out.println("Stav knihy byl zmenen na VRACENO.");
			                    } else {
			                        System.out.println("Neplatna volba. Stav knihy nebyl zmenen.");
			                    }
			                    break;
			                default:
			                    System.out.println("Neplatna volba. Zadny atribut nebyl zmenen.");
			            }
			            break; 
			        }
			    }
			    if (!bookFound) {
			        System.out.println("Kniha s nazvem " + changeTitle + " nebyla nalezena.");
			    }
			    System.out.println("Stisknete Enter pro pokracovani...");
			    sc.nextLine();
			    break;
			case 3:
			    System.out.println("Zadejte nazev knihy, kterou chcete smazat:");
			    String deleteTitle = sc.nextLine().trim();

			    boolean found = false;
			    for (Iterator<Book> iterator = listsbooks.iterator(); iterator.hasNext();) {
			        Book book = iterator.next();
			        if (book.GetTitle().equalsIgnoreCase(deleteTitle)) {
			            iterator.remove(); 
			            System.out.println("Kniha " + deleteTitle + " byla uspesne smazana.");
			            found = true;
			            break; 
			        }
			    }
			    if (!found) {
			        System.out.println("Kniha s nazvem " + deleteTitle + " nebyla nalezena.");
			    }
			    System.out.println("Stisknete Enter pro pokracovani...");
			    sc.nextLine();
			    break;
			case 4:
			    System.out.println("Zadejte nazev knihy, u ktere chcete zmenit stav:");
			    String Titlechange = sc.nextLine().trim();
			    boolean bookHadBeenFound = false;
			    for (Book book : listsbooks) {
			        if (book.GetTitle().equalsIgnoreCase(Titlechange)) {
			            bookHadBeenFound = true;
			            System.out.println("Zadejte novy stav knihy (1 - VYPUJCENO, 2 - VRACENO):");
			            int newStateIndex = sc.nextInt();
			            sc.nextLine(); 
			            if (newStateIndex == 1) {
			                book.SetStateOfBook(Book.StateOfBook.VYPUJCENO);
			                System.out.println("Stav knihy byl zmenen na VYPUJCENO.");
			            } else if (newStateIndex == 2) {
			                book.SetStateOfBook(Book.StateOfBook.VRACENO);
			                System.out.println("Stav knihy byl zmenen na VRACENO.");
			            } else {
			                System.out.println("Neplatna volba. Stav knihy nebyl zmenen.");
			            }
			            break; 
			        }
			    }
			    if (!bookHadBeenFound) {
			        System.out.println("Kniha s nazvem " + Titlechange + " nebyla nalezena.");
			    }
			    System.out.println("Stisknete Enter pro pokracovani...");
			    sc.nextLine();
			    break;
			case 5:
			    System.out.println("Seznam vsech knizek:");

			    if (listsbooks.isEmpty()) {
			        System.out.println("V systemu nejsou zadne knihy.");
			    } else {
			    	Collections.sort(listsbooks);

			        for (Book book : listsbooks) {
			            System.out.println("");
			            System.out.println("Nazev knihy: " + book.GetTitle());
			            System.out.println("Autor knihy: " + book.GetAuthor());
			            System.out.println("Rok vydani knihy: " + book.GetYear());
			            if (book instanceof Roman) {
			                Roman romanBook = (Roman) book;
			                System.out.println("Zanr: " + romanBook.GetGenre());
			            } else if (book instanceof Ucebnice) {
			                Ucebnice textbook = (Ucebnice) book;
			                System.out.println("Rocnik pro ktere je kniha doporucena: " + textbook.getRecommendedGrade());
			            }
			            System.out.println("Stav knihy: " + book.GetStateOfBook());
			            System.out.println("");
			        }
			    }
			    System.out.println("Stisknete Enter pro pokracovani...");
			    sc.nextLine();
			    break;
			case 6:
			    System.out.println("Zadejte nazev knihy, kterou chcete vyhledat:");
			    String searchTitle = sc.nextLine().trim();
			
			    boolean Foundbook = false;
			    
			    for (Book book : listsbooks) {
			        if (book.GetTitle().equalsIgnoreCase(searchTitle)) {
			            Foundbook = true;
			            System.out.println("Nalezena kniha:");
			            System.out.println("Nazev knihy: " + book.GetTitle());
			            System.out.println("Autor knihy: " + book.GetAuthor());
			            System.out.println("Rok vydani knihy: " + book.GetYear());
			            if (book instanceof Roman) {
			                Roman romanBook = (Roman) book;
			                System.out.println("Zanr: " + romanBook.GetGenre());
			            } else if (book instanceof Ucebnice) {
			                Ucebnice textbook = (Ucebnice) book;
			                System.out.println("Rocnik pro ktere je kniha doporucena: " + textbook.getRecommendedGrade());
			            }
			            System.out.println("Stav knihy: " + book.GetStateOfBook());
			            System.out.println("");
			            break; 
			        }
			    }
			    
			    if (!Foundbook) {
			        System.out.println("Kniha s nazvem " + searchTitle + " nebyla nalezena.");
			    }
			    
			    System.out.println("Stisknete Enter pro pokracovani...");
			    sc.nextLine();
			    break;
			case 7:
			    // Prompt the user to enter the name of the author
			    System.out.println("Zadejte jmeno autora:");
			    String authorName = sc.nextLine().trim();

			    // Create a list to store books by the specified author
			    List<Book> authorBooks = new ArrayList<>();

			    // Iterate through the list of books to find books by the specified author
			    for (Book book : listsbooks) {
			        if (book.GetAuthor().equalsIgnoreCase(authorName)) { // Use getAuthor() method
			            authorBooks.add(book);
			        }
			    }

			    // Check if any books by the author were found
			    if (authorBooks.isEmpty()) {
			        System.out.println("Autor " + authorName + " napsal žádné knihy.");
			    } else {
			        // Sort the list of books by year of publication (oldest to newest)
			        Collections.sort(authorBooks, Comparator.comparingInt(Book::GetYear));

			        // Display the sorted list of books
			        System.out.println("Knihy od tohoto autora " + authorName + " od nejstaršího po nejnovější:");
			        for (Book book : authorBooks) {
			            System.out.println("Nazev knihy: "  + book.GetTitle() +
			                    ", Rok vydani: " + book.GetYear() +
			                    ", Stav knihy: " + book.GetStateOfBook() +
			            		", Typ knihy: " + (book instanceof Roman ? "Roman" : "Ucebnice"));
			            
			            if (book instanceof Roman) {
			                Roman romanBook = (Roman) book;
			                System.out.println(", Žánr knihy: " + romanBook.GetGenre());
			            } else if (book instanceof Ucebnice) {
			                Ucebnice textbook = (Ucebnice) book;
			                System.out.println(", Pro ktery rocnik je ucebnice: " + textbook.getRecommendedGrade());
			            }
			        }
			    }

			    System.out.println("Stisknete Enter pro pokracovani...");
			    sc.nextLine();
			    break;
			case 8:
			    System.out.println("Zadejte zanr knihy, pro kterou chcete zobrazit seznam:");
			    System.out.println("1 - Romance, 2 - Horror, 3 - Historie, 4 - Fantazie, 5 - Akcni");
			    int genreChoice = sc.nextInt();
			    sc.nextLine(); // Consume newline
			    
			    System.out.println("Seznam knih daneho zanru:");
			    boolean booksFound = false;
			    for (Book book : listsbooks) {
			        if (book instanceof Roman) { // Check if the book is of type Roman
			            Roman romanBook = (Roman) book; // Cast the book to Roman type
			            if (romanBook.GetGenre() != null && romanBook.GetGenre().equals(Roman.GenreOfBook.values()[genreChoice - 1].toString())) {
			                // Check if the genre matches the user's choice
			                System.out.println("Nazev knihy: " + book.GetTitle());
			                System.out.println("Autor knihy: " + book.GetAuthor());
			                System.out.println("Rok vydani knihy: " + book.GetYear());
			                System.out.println("Zanr knihy: " + romanBook.GetGenre());
			                System.out.println("Stav knihy: " + book.GetStateOfBook());
			                System.out.println(); // Add a blank line for readability
			                booksFound = true;
			            }
			        }
			    }
			    if (!booksFound) {
			        System.out.println("Nebyly nalezeny zadne knihy daneho zanru.");
			    }
			    System.out.println("Stisknete Enter pro pokracovani...");
			    sc.nextLine();
			    break;
			case 9:
				List<Roman> borrowedRomanBooks = new ArrayList<>();
				List<Ucebnice> borrowedUcebniceBooks = new ArrayList<>();

				for (Book book : listsbooks) {
				    if (book.GetStateOfBook() == Book.StateOfBook.VYPUJCENO) {
				       if (book instanceof Roman) {
				           borrowedRomanBooks.add((Roman) book);
				        } else if (book instanceof Ucebnice) {
				            borrowedUcebniceBooks.add((Ucebnice) book);
				        }
				    }
				}

				System.out.println("Borrowed Roman Books:");
				for (Roman romanBook : borrowedRomanBooks) {
				    System.out.println("Title: " + romanBook.GetTitle());
				}
				System.out.println();

				System.out.println("Borrowed Ucebnice Books:");
				for (Ucebnice ucebniceBook : borrowedUcebniceBooks) {
				    System.out.println("Title: " + ucebniceBook.GetTitle());
				}

				System.out.println();
				System.out.println("Stisknete Enter pro pokracovani...");
				sc.nextLine();
				break;
			case 10:
				System.out.println("Zadejte nazev knihy, kterou chcete ulozit:");
				String saveTitle = sc.nextLine().trim();

				// Search for the book with the specified title
				boolean FoundThisBook = false;
				for (Book book : listsbooks) {
				    if (book.GetTitle().equalsIgnoreCase(saveTitle)) { // Use getTitle() method
				        FoundThisBook = true;

				        // Create a FileWriter and PrintWriter to write to a file
				        try (FileWriter fileWriter = new FileWriter("C:/Users/Scardy3Cat/Documents/FileWithSavedBooks.txt");
				             PrintWriter writer = new PrintWriter(fileWriter)) {

				            // Write the book information to the file
				            writer.println("Title: " + book.GetTitle());
				            writer.println("Author: " + book.GetAuthor());
				            writer.println("Year: " + book.GetYear());
				            writer.println("State: " + book.GetStateOfBook());
				            // Add other attributes as needed

				            System.out.println("Kniha byla uspesne ulozena do souboru 'FileWithSavedBooks.txt'.");
				        } catch (IOException e) {
				            System.out.println("Chyba pri zapisu do souboru.");
				        }

				        break; // Exit the loop after saving the book
				    }
				}

				if (!FoundThisBook) {
				    System.out.println("Kniha s nazvem " + saveTitle + " nebyla nalezena.");
				}
				
				System.out.println("Stisknete Enter pro pokracovani...");
				sc.nextLine();
				break;
			case 11:
				 try (FileReader fileReader = new FileReader("C:/Users/Scardy3Cat/Documents/FileWithSavedBooks.txt");
			             BufferedReader reader = new BufferedReader(fileReader)) {
			            String line;
			            System.out.println("Contents of the 'FileWithSavedBooks.txt':");
			            while ((line = reader.readLine()) != null) {
			                System.out.println(line); // Display each line of the file
			            }
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
				System.out.println("Stisknete Enter pro pokracovani...");
				sc.nextLine();
				break;
			case 12:
				System.out.println("Ukonceni programu.");
				class DatabaseManager {
				    private static final String URL = "jdbc:mysql://localhost:3306/library";
				    private static final String USERNAME = "username";
				    private static final String PASSWORD = "password";

				    public static void saveToDatabase(List<Book> books) {
				        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
				            // Create tables if they don't exist
				            createTables(conn);

				            // Insert book information into the database
				            for (Book book : books) {
				                insertBook(conn, book);
				            }
				        } catch (SQLException e) {
				            e.printStackTrace();
				        }
				    }

				    private static void createTables(Connection conn) throws SQLException {
				        String createBooksTableSQL = "CREATE TABLE IF NOT EXISTS books ("
				                + "id INT AUTO_INCREMENT PRIMARY KEY,"
				                + "title VARCHAR(255),"
				                + "author VARCHAR(255),"
				                + "year INT,"
				                + "state VARCHAR(255),"
				                + "genre VARCHAR(255),"
				                + "grade VARCHAR(255))";

				        try (PreparedStatement stmt = conn.prepareStatement(createBooksTableSQL)) {
				            stmt.execute();
				        }
				    }

				    private static void insertBook(Connection conn, Book book) throws SQLException {
				        String insertBookSQL = "INSERT INTO books (title, author, year, state, genre, grade) VALUES (?, ?, ?, ?, ?, ?)";

				        try (PreparedStatement stmt = conn.prepareStatement(insertBookSQL)) {
				            stmt.setString(1, book.GetTitle());
				            stmt.setString(2, book.GetAuthor());
				            stmt.setInt(3, book.GetYear());
				            stmt.setString(4, book.GetStateOfBook().toString());
				            stmt.setString(5, book instanceof Roman ? ((Roman) book).GetGenre().toString() : "");
				            stmt.setString(6, book instanceof Ucebnice ? ((Ucebnice) book).getRecommendedGrade() : "");
				            stmt.executeUpdate();
				        }
				    }
				}
				beh = false;
				break;
			}
		}
	}
}

