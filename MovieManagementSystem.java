public class MovieManagementSystem {
    public static void main(String[] args) {
        MovieManagementSystem mm = new MovieManagementSystem();
        mm.addMovieAtBeginning("Inception", "Christopher Nolan", 2010, 8.8);
        mm.addMovieAtEnd("Avengers: Endgame", "Anthony Russo, Joe Russo", 2019, 8.4);
        mm.addMovieAtEnd("Interstellar", "Christopher Nolan", 2014, 8.6);

        mm.displayMoviesForward();

        mm.displayMoviesReverse();

        mm.searchMovieByDirector("Christopher Nolan");

        mm.removeMovie("Avengers: Endgame");

        System.out.println("After updating rating:");
        mm.updateMovieRating("Interstellar", 9.0);
        mm.displayMoviesForward();
    }
    class Movie {
        String movieTitle;
        String director;
        int yearOfRelease;
        double rating;
        Movie next, prev;

        public Movie(String movieTitle,String director, int yearOfRelease, double rating){
            this.movieTitle = movieTitle;
            this.director = director;
            this.yearOfRelease = yearOfRelease;
            this.rating = rating;
            this.next = null;
            this.prev = null;
        }
    }

    private Movie head, tail;

    public MovieManagementSystem() {
        this.head = this.tail = null;
    }

    public void addMovieAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if(head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    public void addMovieAtEnd(String title, String director, int year, double rating){
        Movie newMovie = new Movie(title, director, year, rating);
        if(head == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    public void removeMovie(String title) {
        Movie temp = head;
        while (temp != null && !temp.movieTitle.equals(title)) {
            temp = temp.next;
        }
        if (temp == null) return;

        if (temp == head) {
            head = temp.next;
            if (head != null) head.prev = null;
        } else if (temp == tail) {
            tail = temp.prev;
            if (tail != null) tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }
    public void searchMovieByDirector(String director) {
        Movie temp = head;
        while (temp != null) {
            if (temp.director.equals(director)) {
                System.out.println(temp.movieTitle + " (" + temp.yearOfRelease + ") - " + temp.rating);
            }
            temp = temp.next;
        }
    }

    public void searchMovieByRating(double rating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.rating == rating) {
                System.out.println(temp.movieTitle + " directed by " + temp.director);
            }
            temp = temp.next;
        }
    }

    public void displayMoviesForward() {
        Movie temp = head;
        while (temp != null) {
            System.out.println("Title: " + temp.movieTitle + ", Director: " + temp.director + ", Year: " + temp.yearOfRelease + ", Rating: " + temp.rating);
            temp = temp.next;
        }
        System.out.println();
    }

    public void displayMoviesReverse() {
        Movie temp = tail;
        while (temp != null) {
            System.out.println("Title: " + temp.movieTitle + ", Director: " + temp.director + ", Year: " + temp.yearOfRelease + ", Rating: " + temp.rating);
            temp = temp.prev;
        }
        System.out.println();
    }

    public void updateMovieRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.movieTitle.equals(title)) {
                temp.rating = newRating;
                return;
            }
            temp = temp.next;
        }
    }
}
//output
// Title: Inception, Director: Christopher Nolan, Year: 2010, Rating: 8.8
//Title: Avengers: Endgame, Director: Anthony Russo, Joe Russo, Year: 2019, Rating: 8.4
//Title: Interstellar, Director: Christopher Nolan, Year: 2014, Rating: 8.6
//
//Title: Interstellar, Director: Christopher Nolan, Year: 2014, Rating: 8.6
//Title: Avengers: Endgame, Director: Anthony Russo, Joe Russo, Year: 2019, Rating: 8.4
//Title: Inception, Director: Christopher Nolan, Year: 2010, Rating: 8.8
//
//Inception (2010) - 8.8
//Interstellar (2014) - 8.6
//After updating rating:
//Title: Inception, Director: Christopher Nolan, Year: 2010, Rating: 8.8
//Title: Interstellar, Director: Christopher Nolan, Year: 2014, Rating: 9.0