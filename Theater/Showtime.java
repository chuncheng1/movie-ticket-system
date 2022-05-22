/*
 * The Showtime class, representing a schedule spot
 * This class consolidates the movie and the time it is being shown.
 */
public class Showtime {
	private Movie movie;
	private Time runtime;
	
	public Showtime(Movie movie, Time runtime) {
		if (movie == null) {
            throw new IllegalArgumentException("Movie in showtime cannot be null");
        }        
        if (runtime == null) {
            throw new IllegalArgumentException("Runtime in showtime cannot be null");
        }
		this.movie = movie;
		this.runtime = runtime;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public Time getTime() {
		return runtime;
	}
	
	// prints the showtime
	public void printShowtime() {
		System.out.println(movie.getName());
		runtime.printTime();
	}
	
}