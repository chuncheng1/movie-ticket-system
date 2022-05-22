/*
 * The Ticket class, representing the ticket the customer purchases
 * When the customer purchases a ticket, it is automatically assigned an ID.
 * The ticket stores information about the showtime, as well as the seat reserved.
 */
public class Ticket {
	
	private int ticketID;
	private Showtime showtime;
	private Seat seat;
	private int cost; // in dollars
	private static int DEFAULT_TICKET_COST = 10;
	
	public Ticket(int ticketID, Showtime showtime, Seat seat) {
		if (ticketID < 0) {
            throw new IllegalArgumentException("Ticket ID cannot be negative");
        }
		if (showtime == null) {
            throw new IllegalArgumentException("Showtime in ticket cannot be null");
        }        
        if (seat == null) {
            throw new IllegalArgumentException("Seat in ticket cannot be null");
        }
		this.ticketID = ticketID;
		this.showtime = showtime;
		this.seat = seat;
		this.cost = DEFAULT_TICKET_COST;
	}
	
	public Showtime getShowtime() {
		return showtime;
	}
	
	public Seat getSeat() {
		return seat;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void updateID(int id) {
		ticketID = id;
	}
	
	// prints the ticket information
	public void printTicket() {
		System.out.println("Ticket " + ticketID);
		this.showtime.printShowtime();
		System.out.println("Theater " + this.seat.getTheater());
		System.out.println("Seat " + this.seat.getID());
		System.out.println("Cost: $" + this.cost);
	}

}