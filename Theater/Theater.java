/*
 * The Theater class, representing the different showrooms in which the customer can watch a movie
 * The theater stores a list of seats that are located in that theater, as well as the schedule of movies set to show in the theater.
 * There are numerous of these smaller theaters in the overall movie theater.
 */
public class Theater {
	private int theaterID;
	private int capacity;
	private Seat[][] seats;
	private Showtime[] movieSchedule;
	private int showtimeNum;
	private static int SEAT_ROWS = 5;
	private static int SEAT_COLS = 5;
	private static int MAX_SHOWTIME_COUNT = 21;
	
	public Theater(int theaterID) {
		if (theaterID < 0) {
            throw new IllegalArgumentException("Theater ID cannot be negative");
        }
		this.theaterID = theaterID;
		this.capacity = SEAT_ROWS * SEAT_COLS;
		this.seats = new Seat[SEAT_ROWS][SEAT_COLS];
		
		int seatNum = 0;
		for (int i = 0; i < SEAT_ROWS; i++) {
			for (int j = 0; j < SEAT_COLS; j++) {
				this.seats[i][j] = new Seat(seatNum, theaterID);
				seatNum++;
			}
		}
		
		this.movieSchedule = new Showtime[MAX_SHOWTIME_COUNT];
		this.showtimeNum = 0;
	}
	
	public int getID() {
		return theaterID;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	//returns a pointer to a specific seat in the theater
	public Seat getSeat(int seatID) {
		if (seatID < 0 || seatID >= capacity) {
            System.out.println("Seat ID input is invalid");
            return null;
        }
		return seats[seatID/SEAT_COLS][seatID%SEAT_COLS];
	}
	
	public Seat[][] getSeats() {
		return seats;
	}
	
	public Showtime[] getSchedule() {
		return movieSchedule;
	}
	
	public int getShowtimeNum() {
		return showtimeNum;
	}
	
	//adds showtime s, returns true if successfully added, false if movieschedule is full or if
	//the time slot is already occupied
	public boolean addShowtime(Showtime s) {
		if (s == null) {
			System.out.println("Showtime input is null.");
            return false;
        }
		if (showtimeNum == MAX_SHOWTIME_COUNT) {
			System.out.println("Max showtime reached.");
			return false;
		}
		
		Time st = s.getTime();
		for (int i = 0; i < MAX_SHOWTIME_COUNT; i++) {
			if (movieSchedule[i] != null) {
				Time currentTime = movieSchedule[i].getTime();
				if (currentTime.overlaps(st)) {
					System.out.println("Time conflict exists in showtime. Cannot add.");
					return false;
				}
				if (currentTime.equals(st)) {
					System.out.println("Time slot already has a show.");
					return false;
				}
			}
		}
		movieSchedule[showtimeNum] = s;
		showtimeNum++;
		return true;
	}
	
	//deletes showtime s, returns true if successfully deleted, false if the showtime is not in the schedule
	public boolean deleteShowtime(Showtime s) {
		if (s == null) {
			System.out.println("Showtime input is null.");
            return false;
        }
		Time st = s.getTime();
		for(int i = 0; i < MAX_SHOWTIME_COUNT; i++) {
			if (movieSchedule[i] != null) {
				Time currentTime = movieSchedule[i].getTime();
				if (currentTime.equals(st)) {
					int j = i;
					while (j < MAX_SHOWTIME_COUNT-1) {
						movieSchedule[j] = movieSchedule[j+1];
						j++;
					}
					movieSchedule[j] = null;
					showtimeNum--;
					return true;
				}
			}
		}
		System.out.println("Showtime not found.");
		return false;
	}
	
	//searches for whether or not the theater has a specific showtime
	public boolean hasShowtime(Showtime s) {
		if (s == null) {
			System.out.println("Showtime input is null.");
            return false;
        }
		Movie m = s.getMovie();
		String mname = m.getName();
		Time t = s.getTime();
		for(int i = 0; i < MAX_SHOWTIME_COUNT; i++) {
			if (movieSchedule[i] != null) {
				Movie currentm = movieSchedule[i].getMovie();
				String currentmname = currentm.getName();
				Time currentt = movieSchedule[i].getTime();
				if (currentmname.equals(mname) && currentt.equals(t)) {
					return true;
				}
			}
		}
		return false;
	}
	
	//printSeats - lists all the seats in the theater at time t, taken seats are shown as "X,"
	// available seats are shown as seatID
	public void printSeats(Time t) {
		if (t == null) {
			System.out.println("Time input is null.");
			return;
        }
		System.out.println("Theater " + theaterID + " Seat Status");
		for (int i = 0; i < SEAT_ROWS; i++) {
			for (int j = 0; j < SEAT_COLS; j++) {
				if (seats[i][j].hasReservation(t) == true) {
					System.out.print(" X");
				} else {
					int id = seats[i][j].getID();
					if (id < 10) {
						System.out.print(" ");
					}
					System.out.print(id);
				}
				System.out.print(" | ");
			}
			System.out.println();
		} 
	}
	
	//printSchedule - lists the movie schedule of the theater
	public void printSchedule() {
		System.out.println("Theater " + theaterID + " Movie Schedule");
		for (int i = 0; i < MAX_SHOWTIME_COUNT; i++) {
			if (movieSchedule[i] != null) {
				System.out.print(i + ") ");
				movieSchedule[i].printShowtime();
				System.out.println();
			}
		}
	}
}