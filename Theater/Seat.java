/*
 * The Seat class, representing the seat the customer will book in a theater
 * Stores the seat's number and what theater it is located in.
 * Also stores a list of reservations for the seat, as the same seat can be reserved at different times.
 */
public class Seat {
	private int seatID;
	private int theaterID;
	private Time[] reservation;
	private int reservationNum;
	private static int MAX_SLOTS = 21;
	
	public Seat(int seatID, int theaterID) {
		if (seatID < 0) {
            throw new IllegalArgumentException("Seat ID cannot be negative");
        }
		if (theaterID < 0) {
            throw new IllegalArgumentException("Theater ID cannot be negative");
        }
		this.seatID = seatID;
		this.theaterID = theaterID;
		this.reservation = new Time[MAX_SLOTS];
		this.reservationNum = 0;
	}
	
	public int getID() {
		return seatID;
	}
	
	public int getTheater() {
		return theaterID;
	}
	
	public Time[] getReservation() {
		return reservation;
	}
	
	//add a reservation time interval, return true if successfully added, false if array is full or the time slot is already booked
	public boolean addReservation(Time t) {
		if (t == null) {
			System.out.println("Time input is null.");
            return false;
        }
		if (reservationNum == reservation.length) {
			System.out.println("All time slots booked.");
			return false;
		}
		
		for (int i = 0; i < reservation.length; i++) {
			if (reservation[i] != null && reservation[i].equals(t)) {
				System.out.println("Time slot already booked.");
				return false;
			}
		}
		reservation[reservationNum] = t;
		reservationNum++;
		return true;
	}
	
	//delete a reservation time interval, return true if successfully deleted, false if time not found
	public boolean deleteReservation(Time t) {
		if (t == null) {
			System.out.println("Time input is null.");
            return false;
        }
		for(int i = 0; i < reservation.length; i++) {
			if (reservation[i] != null && reservation[i].equals(t)) {
				//shift up the remaining reservations
				int j = i;
				while (j < reservation.length-1) {
					reservation[j] = reservation[j+1];
					j++;
				}
				reservation[j] = null;
				reservationNum--;
				return true;
			}
		}
		System.out.println("Time not found.");
		return false;
	}
	
	//searches for whether or not time t is reserved, returns true if time t is booked, false otherwise
	public boolean hasReservation(Time t) {
		if (t == null) {
			System.out.println("Time input is null.");
            return false;
        }
		for(int i = 0; i < reservation.length; i++) {
			if (reservation[i] != null && reservation[i].equals(t)) {
				return true;
			}
		}
		return false;
	}
}