public class Roman extends Book {
    private String author;
    private int year;
    private GenreOfBook genre;

    public enum GenreOfBook { ROMANCE, HORROR, HISTORY, FANTASY, ACTION }

    public Roman(String title, String author, int year, GenreOfBook genre, StateOfBook state) {
        super(title, author, year); 
        this.genre = genre;
        setStateOfBook(state); 
    }

    @Override
    public String GetGenre() {
        return genre.toString(); 
    }

    @Override
    public int compareTo(Book o) {
        return this.GetTitle().compareTo(o.GetTitle());
    }
    
    public void setStateOfBook(Book.StateOfBook state) {
        this.bookstate = state;
    }
}
