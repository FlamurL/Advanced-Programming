import java.io.*;
import java.util.*;

class Movie {
    private String title;
    private String genre;
    private int year;
    private double avgRating;

    public Movie(String title, String genre, int year, double avgRating) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.avgRating = avgRating;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public double getAvgRating() {
        return avgRating;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %.2f", title, genre, year, avgRating);
    }
}

class MovieTheater {
    private ArrayList<Movie> movies;

    public MovieTheater() {
        this.movies = new ArrayList<>();
    }

    public void readMovies(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String title = br.readLine();
            String genre = br.readLine();
            int year = Integer.parseInt(br.readLine());
            String[] ratingsStr = br.readLine().split("\\s+");

            double sum = 0;
            for (String rating : ratingsStr) {
                sum += Integer.parseInt(rating);
            }
            double avgRating = sum / ratingsStr.length;

            movies.add(new Movie(title, genre, year, avgRating));
        }

        br.close();
    }

    public void printByGenreAndTitle() {
        movies.sort(Comparator.comparing(Movie::getGenre)
                .thenComparing(Movie::getTitle));

        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public void printByYearAndTitle() {
        movies.sort(Comparator.comparing(Movie::getYear)
                .thenComparing(Movie::getTitle));

        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public void printByRatingAndTitle() {
        movies.sort(Comparator.comparing(Movie::getAvgRating)
                .reversed()
                .thenComparing(Movie::getTitle));

        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}

public class MovieTheaterTester {
    public static void main(String[] args) {
        MovieTheater mt = new MovieTheater();
        try {
            mt.readMovies(System.in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("SORTING BY RATING");
        mt.printByRatingAndTitle();
        System.out.println("\nSORTING BY GENRE");
        mt.printByGenreAndTitle();
        System.out.println("\nSORTING BY YEAR");
        mt.printByYearAndTitle();
    }
}