import java.util.ArrayList;

public class Movie {
    private String title;
    private ArrayList<MovieScreening> screenings;

    public Movie(String title) {
        this.title = title;
    }

    public void addScreening(MovieScreening screening) {
        this.screenings.add(screening);
    }

    public String toString() {
        return this.title.toString();
    }


}
