public class Movie {
	private String name;
	private String rating;
	private String genre;
	private int length; // in minutes
	private float boxOffice;
	private int ticketSales;
	private static int DEFAULT_TICKET_COST = 10;
	
	public Movie(String name, String rating, String genre, int length) {
		if (name == null) {
            throw new IllegalArgumentException("Movie name cannot be null");
        } 
		if (length < 0) {
            throw new IllegalArgumentException("Movie length cannot be negative");
        }
		this.name = name;
		this.rating = rating;
		this.genre = genre;
		this.length = length;
		this.boxOffice = 0;
		this.ticketSales = 0;
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getRating() {
		return rating;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public int getLength() {
		return length;
	}

	public float getBoxOffice() {
		return boxOffice;
	}
	
	public void addProfit(float amount) {
		boxOffice += amount;
		ticketSales = (int)boxOffice/DEFAULT_TICKET_COST;
	}

	public int getTicketSales() {
		return ticketSales;
	}
	
	public void printMovie() {
		System.out.println(name);
		System.out.println("Rating: " + rating);
		System.out.println("Genre: " + genre);
		System.out.println("Runtime: " + length + " min");
	}

}