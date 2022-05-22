/*
 * The main class, which represents the MovieTicketSystem that users will interact with
 * The program is run from here. 
 */
import java.util.Scanner;

public class MovieTicketSystem {

	public static void main(String[] args) {

		Theater[] theaters = new Theater[6]; // initialize theaters
		theaters[0] = new Theater(1);
		theaters[1] = new Theater(2);
		theaters[2] = new Theater(3);
		theaters[3] = new Theater(4);
		theaters[4] = new Theater(5);
		theaters[5] = new Theater(6);

		Movie[] movies = new Movie[6]; // initialize movies
	    movies[0] = new Movie("Encanto","PG", "Musical", 109);
        movies[1] = new Movie("House of Gucci", "R", "Drama",  157);
        movies[2] = new Movie("Resident Evil: Welcome to Raccoon City","R","Horror", 107);
        movies[3] = new Movie("Ghostbusters: Afterlife", "PG-13", "Sci-Fi", 124);
        movies[4] = new Movie("King Richard", "PG-13", "Drama",  144);
        movies[5] = new Movie("Eternals", "PG-13", "SuperHero",  157);

		for (int i = 0; i < 6; i++) { // initialize movie show times
        	for (int j=0; j < 7; j++) {
        		Time newTime = new Time(12, 1+j, 15, 0, movies[i].getLength());
                Showtime newShowtime = new Showtime(movies[i], newTime);
                theaters[i].addShowtime(newShowtime);
                newTime = new Time(12, 1+j, 18, 0, movies[i].getLength());
                newShowtime = new Showtime(movies[i], newTime);
                theaters[i].addShowtime(newShowtime);
                newTime = new Time(12, 1+j, 21, 0, movies[i].getLength());
                newShowtime = new Showtime(movies[i], newTime);
                theaters[i].addShowtime(newShowtime);
        	}
        }

		int MAX_ORDERS = 3;
		int THEATER_NUM = 6;
		int MOVIE_NUM = THEATER_NUM;
		int DEFAULT_TICKET_COST = 10;
		
		int accountCount = 0;
		int MAX_ACCOUNT_NUM = 100;
		Account[] accountList = new Account[MAX_ACCOUNT_NUM];
		Account loggedInAccount = null;
		
		accountList[0] = new Account (0, "admin", true); // Employee account
		accountCount++;

		while (true) { // this is the main menu the customer will be interacting from
			System.out.println();
			System.out.println("Welcome to the Movie Theater!");
			System.out.println();
			System.out.println("What would you like to do?");
			System.out.println("(1) Register account"); 
			System.out.println("(2) Login");
			System.out.println("(3) Logout");
			System.out.println("(4) Purchase Ticket");
			System.out.println("(5) Cancel Ticket Order");
			System.out.println("(6) Print Your Ticket Orders");
			System.out.println("(7) Change password");
			if (loggedInAccount != null && loggedInAccount.getIsEmployee()) {
				System.out.println("(8) Access Movie Sales (Employee Only)");
			}
			System.out.println("(0) Quit");
			
			//printing log in status
			if (loggedInAccount == null) {
				System.out.println("You are not logged in");
			} else {
				System.out.println("You are logged in");
				System.out.println("Account ID: " + loggedInAccount.getID());
			}
			
			try {
				Scanner input = new Scanner(System.in);
				int choice = input.nextInt();
				if (choice == 0) {
					//Quit
					System.out.println("Thank you for using the Movie Theater. Goodbye!");
					break;
				} else if (choice == 1) {
					//Register account
					if (loggedInAccount != null){
						System.out.println("User already logged in");
						continue;
					} else{
						if (accountCount >= MAX_ACCOUNT_NUM) {
							System.out.print("Sorry, the system cannot add any more account");
							continue;
						}
						
						//reading password
						System.out.print("Create a password: ");
						Scanner temp = new Scanner(System.in);
						String password = temp.nextLine();
						
						//printing the new account's ID
						int createID = accountCount;
						System.out.print("Your account ID is: ");
						System.out.println(createID);
						accountList[createID] = new Account (createID, password, false); // create a customer account
						accountCount++;
						continue;
					}
				} else if (choice == 2){
					//Login
					if (loggedInAccount != null){
						System.out.println("You are already logged in");
						continue;
					} else{
						//reading account ID
						System.out.print("Enter your account ID: ");
						Scanner temp1 = new Scanner(System.in);
						int customerID = temp1.nextInt();
						if (customerID < 0 || customerID >= MAX_ACCOUNT_NUM || accountList[customerID] == null) {
							System.out.println("Invalid account ID");
							continue;
						}
						
						//reading password
						System.out.print("Enter your password: ");
						Scanner temp2 = new Scanner(System.in);
						String password = temp2.nextLine();
						if(accountList[customerID].verifyPassword(password)){
							System.out.println("Succesful Login");
							loggedInAccount = accountList[customerID];
							continue;
						}
						else{
							System.out.println("UserID or password is incorrect");
							continue;
						}

					}

				} else if (choice == 3){
					//Logout
					if (loggedInAccount == null) {
						System.out.println("You are not logged in");
						continue;
					} else {
						loggedInAccount = null;
						System.out.println("You have successfully logged out");
						continue;
					}
				} else if (choice == 4){
					//Purchase ticket
					if (loggedInAccount == null) {
						System.out.println("You are not logged in. Please log in first.");
						continue;
					} else {
						if (loggedInAccount.getOrderNum() >= MAX_ORDERS) {
							System.out.println("You cannot order more than 3 tickets");
							continue;
						}
						
						//reading movie choice
						System.out.println();
						for (int i = 0; i < MOVIE_NUM; i++) {
							System.out.print(i + ") ");
							movies[i].printMovie();
							System.out.println();
						}
						System.out.print("Please choose a film from above (Enter numbers only): ");
						Scanner temp3 = new Scanner(System.in);
						int moviechoice = temp3.nextInt();
						if (moviechoice < 0 || moviechoice >= MOVIE_NUM) {
							System.out.println("You have entered an invalid input");
							continue;
						}
						
						//reading showtime choice
						System.out.println();
						theaters[moviechoice].printSchedule();
						System.out.print("Please choose a showtime from above (Enter numbers only): ");
						Scanner temp4 = new Scanner(System.in);
						int showtimechoice = temp4.nextInt();
						if (showtimechoice < 0 || showtimechoice >= theaters[moviechoice].getShowtimeNum()) {
							System.out.println("You have entered an invalid input");
							continue;
						}
						
						//reading seat choice
						Showtime[] showtimes = theaters[moviechoice].getSchedule();
						Time time = showtimes[showtimechoice].getTime();
						System.out.println();
						theaters[moviechoice].printSeats(time);
						System.out.println("Seats marked with X are not available");
						System.out.print("Please choose a seat from above (Enter numbers only): ");
						Scanner temp5 = new Scanner(System.in);
						int seatchoice = temp5.nextInt();
						if (seatchoice < 0 || seatchoice >= theaters[moviechoice].getCapacity()) {
							System.out.println("You have entered an invalid input");
							continue;
						}
						
						//ordering ticket
						Seat orderedseat = theaters[moviechoice].getSeat(seatchoice);
						if (loggedInAccount.orderTicket(showtimes[showtimechoice], orderedseat)) {
							movies[moviechoice].addProfit(DEFAULT_TICKET_COST);
						}
						continue;
					}
					
				} else if (choice == 5) {
					//Cancel ticket order
					if (loggedInAccount == null) {
						System.out.println("You are not logged in. Please log in first.");
						continue;
					} else {
						if (loggedInAccount.getOrderNum() == 0) {
							System.out.println("You don't have any ticket order right now");
							continue;
						}
						
						//reading ticket choice
						System.out.println();
						loggedInAccount.printTickets();
						System.out.print("Type the ID of ticket you want to cancel: ");
						Scanner deletechoice = new Scanner(System.in);
						choice = deletechoice.nextInt();
						if (choice < 0 || choice >= MAX_ORDERS) {
							System.out.println("You have entered an invalid input");
							continue;
						}
						loggedInAccount.cancelTicket(choice);
						continue;
					}
					
				} else if (choice == 6) {
					//Print your ticket orders
					if (loggedInAccount == null) {
						System.out.println("You are not logged in. Please log in first.");
						continue;
					} else {
						System.out.println();
						loggedInAccount.printTickets();
						continue;
					}
				} else if (choice == 7) {
					if (loggedInAccount == null) {
						System.out.println("You are not logged in. Please log in first.");
						continue;
					} else {
						System.out.print("Enter new password: ");
						Scanner temp7 = new Scanner(System.in);
						String newPassword = temp7.nextLine();
						loggedInAccount.updatePassword(newPassword);
						System.out.println("Password changed successfully.");
						continue;
					}
				} else if (choice == 8 && loggedInAccount != null && loggedInAccount.getIsEmployee()) {
					//Access movie sales
					System.out.println();
					for (int i = 0; i < MOVIE_NUM; i++) {
						System.out.print(i + ") ");
						movies[i].printMovie();
						System.out.println();
					}
					
					//reading movie choice
					System.out.print("Please choose a film from above (Enter numbers only): ");
					Scanner temp8 = new Scanner(System.in);
					int moviechoice = temp8.nextInt();
					if (moviechoice < 0 || moviechoice >= MOVIE_NUM) {
						System.out.println("You have entered an invalid input");
						continue;
					}
					
					//printing movie
					System.out.println();
					movies[moviechoice].printMovie();
					System.out.println("Total tickets sold: " + movies[moviechoice].getTicketSales());
					System.out.println("Total box office: " + movies[moviechoice].getBoxOffice());
					continue;
				}
				else {
					System.out.println("You have entered an invalid input");
					continue;
				}
			} catch (Exception e) {
				System.out.println("You have entered an invalid input");
				continue;
			}

		}

	}

}