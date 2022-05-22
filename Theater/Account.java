/*
 * The Account class, which represents the customer and employee
 * Accounts are used to access the theater's features, such as ordering a ticket.
 * To register an account, the user needs a password. They are automatically assigned an ID after the account is created.
 * The Account class also keeps tracks of the account's ticket orders.
 * Employees have special accounts which allow them to access movie sales records.
*/
public class Account {
	private int accountID;
	private String password;
	private Ticket[] ticketOrders;
	private int orderNum;
	private boolean isEmployee; // determines whether the account is an admin account or not
	private static int MAX_ORDERS = 3; // max number of tickets a customer can order
	
	public Account(int id, String email, boolean isEmployee) {
		if (id < 0) {
            throw new IllegalArgumentException("Account ID cannot be negative");
        }
		this.accountID = id;
		this.password = email;
		this.ticketOrders = new Ticket[MAX_ORDERS];
		this.orderNum = 0;
		this.isEmployee = isEmployee;
		
	}
	
	public int getID() {
		return accountID;
	}
	
	public boolean verifyPassword(String enteredP) {
		if(password.equals(enteredP)){
			return true;
		} else {
			return false;
		}
	}
	
	public int getOrderNum() {
		return orderNum;
	}
	
	public boolean getIsEmployee() {
		return isEmployee;
	}
	
	public boolean updatePassword(String newPassword) {
		password = newPassword;
		return true;
	}
	
	/*
	 * Ordering a ticket
	 * When a valid input is given, the system checks if the account does not already have a max number of ticket orders.
	 * Returns false if the purchase failed, such as invalid input, max number of tickets bought,
	 * or the seat chosen has already been reserved (this is checked in seat.addReservation()).
	 * Returns true if the purchase was successful.
	 */
	public boolean orderTicket(Showtime showtime, Seat seat) {
		if (showtime == null) {
			System.out.println("Showtime input is null.");
            return false;
        }
		if (seat == null) {
			System.out.println("Seat input is null.");
            return false;
        }
		if (orderNum >= MAX_ORDERS) {
			System.out.println("You cannot buy any more ticket");
			return false;
		}
		Time time = showtime.getTime();
		if (seat.addReservation(time)) {
			Ticket newTicket = new Ticket(orderNum, showtime, seat);
			ticketOrders[orderNum] = newTicket;
			orderNum++;
			System.out.println("Purchase succcessful");
			return true;
		}
		System.out.println("Purchase failed");
		return false;
	}
	
	/*
	 * Cancel an order
	 * User enters the ID of the ticket they wish to cancel.
	 * Returns false if the ticket cancellation failed.
	 * Returns true if ticket cancellation was a success.
	 */
	public boolean cancelTicket(int ticketID) {
		if (ticketID < 0 || ticketID >= ticketOrders.length) {
			System.out.println("TicketID is invalid.");
            return false;
        }
		Ticket ticket = ticketOrders[ticketID];
		if (ticket == null) {
			System.out.println("TicketID is invalid.");
            return false;
		}
		Showtime showtime = ticket.getShowtime();
		Seat seat = ticket.getSeat();
		Time time = showtime.getTime();
		if (seat.deleteReservation(time)) {
			for(int i = 0; i < ticketOrders.length; i++) {
				if (i == ticketID) {
					//shift up the remaining tickets
					Movie movie = showtime.getMovie();
					movie.addProfit(-10);
					int j = i;
					while (j < ticketOrders.length-1) {
						ticketOrders[j] = ticketOrders[j+1];
						if (ticketOrders[j] != null) {
							ticketOrders[j].updateID(j);
						}
						j++;
					}
					ticketOrders[j] = null;
					orderNum--;
					System.out.println("The ticket is successfully canceled");
					return true;
				}
			}
		}
		System.out.println("Ticket ID is invalid");
		return false;
	}
	
	/*
	 * Prints information of all ticket orders on the account
	 */
	public void printTickets() {
		System.out.println("Account " + accountID + " Ticket Orders");
		for (int i = 0; i < ticketOrders.length; i++) {
			if (ticketOrders[i] != null) {
				ticketOrders[i].printTicket();
				System.out.println();
			}
		}
	}
}