public class Ucebnice extends Book {
    private String author;
    private int year;
    private String recommendedGrade;

    public Ucebnice(String title, String author, int year, String recommendedGrade, StateOfBook state) {
        super(title, author, year); 
        this.recommendedGrade = recommendedGrade;
        }

    public String getRecommendedGrade() {
        return recommendedGrade;
    }

    @Override
    public int compareTo(Book o) {
        return this.GetTitle().compareTo(o.GetTitle());
    }
}
