import java.util.Scanner;

public class MovieTicketSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Theater[] theaters = new Theater[6];
		theaters[0] = new Theater(1);
		theaters[1] = new Theater(2);
		theaters[2] = new Theater(3);
		theaters[3] = new Theater(4);
		theaters[4] = new Theater(5);
		theaters[5] = new Theater(6);

		Movie[] movies = new Movie[6];
	    movies[0] = new Movie("Encanto","PG", "Musical", 109);
        movies[1] = new Movie("House of Gucci", "R", "Drama",  157);
        movies[2] = new Movie("Resident Evil: Welcome to Raccoon City","R","Horror", 107);
        movies[3] = new Movie("Ghostbusters: Afterlife", "PG-13", "Sci-Fi", 124);
        movies[4] = new Movie("King Richard", "PG-13", "Drama",  144);
        movies[5] = new Movie("Eternals", "PG-13", "SuperHero",  157);

		for (int i = 0; i < 6; i++) {
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

		int flag = 0;
		int accountCount = 0;
		int MAX_ACCOUNT_NUM = 100;
		Customer[] accountList = new Customer[MAX_ACCOUNT_NUM];
		Customer loggedInAccount = null;

		while (true) {
			System.out.println("Welcome to the Movie Theater!");
			System.out.println();
			System.out.println("What would you like to do?");
			System.out.println("(1) Register account"); // need to find a way for system to detect if you are logged in or not
			System.out.println("(2) Login");
			System.out.println("(3) Logout");
			System.out.println("(4) Purchase Ticket");
			System.out.println("(5) Cancel Ticket Order");
			System.out.println("(0) Quit");
			
			try {
				Scanner input = new Scanner(System.in);
				int choice = input.nextInt();
				if (choice == 0) {
					System.out.println("Thank you for using the Movie Theater. Goodbye!");
					break;
				} else if (choice == 1) {
					if (flag == 1){
						System.out.println("User already logged in");
						continue;
					}
					else{
						System.out.println("Enter Email Address: ");
						Scanner temp = new Scanner(System.in);
						String emailaddress = temp.nextLine();
						int createID = accountCount;
						System.out.print("Your account ID is: ");
						System.out.println(createID);
						accountList[createID] = new Customer (createID,emailaddress);
						accountCount++;
						continue;
					}
				} else if (choice == 2){
					if (flag == 1){
						System.out.println("You are already logged in");
						continue;
					}
					else{
						System.out.println("Enter your account ID: ");
						Scanner temp1 = new Scanner(System.in);
						int customerID = temp1.nextInt();
						if (customerID < 0 || customerID >= MAX_ACCOUNT_NUM || accountList[customerID] == null) {
							System.out.println("Invalid account ID.");
							continue;
						}
						System.out.println("Enter Email Address: ");
						Scanner temp2 = new Scanner(System.in);
						String emailaddress = temp2.nextLine();
						String validemail = accountList[customerID].getEmail();
						if(validemail.equals(emailaddress)){
							System.out.println("Succesful Login");
							flag = 1;
							loggedInAccount = accountList[customerID];
							continue;
						}
						else{
							System.out.println("UserID or emailaddress is incorrect");
							continue;
						}

					}

				} else if (choice == 3){
					flag = 0;
					loggedInAccount = null;
					System.out.println("User is logged out");
					continue;
				} else if (choice == 4){
					System.out.println("Choose a Film");
					System.out.println();
					for (int i = 0; i < 6; i++) {
						System.out.print((i) + ") ");
						movies[i].printMovie();
					}
				
					Scanner temp3 = new Scanner(System.in);
					int moviechoice = temp3.nextInt();
					theaters[moviechoice].printSchedule();
					System.out.println("Choose a show time");
					Scanner temp4 = new Scanner(System.in);
					int showtime = temp4.nextInt();
					Showtime[] showtimes = theaters[moviechoice].getSchedule();
					Time time = showtimes[showtime].getTime();
					System.out.println("Choose a seat");
					theaters[moviechoice].printSeats(time);
					Scanner temp5 = new Scanner(System.in);
					int seat = temp5.nextInt();
					Seat orderedseat = theaters[moviechoice].getSeat(seat);
					if (loggedInAccount.orderTicket(showtimes[showtime], orderedseat)) {
						movies[moviechoice].addProfit(10);
					}
					continue;

				} else if (choice == 5) {
					loggedInAccount.printTickets();
					System.out.println("Type the Id of ticket to ticket to remove");
					Scanner deletechoice = new Scanner(System.in);
					choice = deletechoice.nextInt();
					loggedInAccount.cancelTicket(choice);
	
					continue;
				} else {
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