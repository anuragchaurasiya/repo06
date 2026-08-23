package project_work;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// ======================================================
// MOVIE CLASS
// ======================================================

class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;

    Movie(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void display() {
        System.out.println(id + ". " + name);
    }
}

// ======================================================
// CUSTOMER CLASS
// ======================================================

class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String mobile;
    private String email;

    Customer(String name, String mobile, String email) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public void displayCustomer() {

        System.out.println("\n========== CUSTOMER DETAILS ==========");
        System.out.println("Name   : " + name);
        System.out.println("Mobile : " + mobile);
        System.out.println("Email  : " + email);
        System.out.println("======================================");
    }
}

// ======================================================
// SEAT CLASS
// ======================================================

class Seat implements Serializable {

    private static final long serialVersionUID = 1L;

    private String seatNumber;
    private String seatType;
    private int price;
    private boolean booked;

    Seat(String seatNumber, String seatType, int price) {

        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.price = price;
        this.booked = false;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public int getPrice() {
        return price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void book() {
        booked = true;
    }

    public void cancel() {
        booked = false;
    }
}

// ======================================================
// BOOKING CLASS
// ======================================================

class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bookingId;
    private Customer customer;
    private Movie movie;
    private String showTime;
    private int screenNumber;

    private ArrayList<Seat> seats;

    private String paymentMethod;
    private boolean cancelled;

    Booking(
            String bookingId,
            Customer customer,
            Movie movie,
            String showTime,
            int screenNumber) {

        this.bookingId = bookingId;
        this.customer = customer;
        this.movie = movie;
        this.showTime = showTime;
        this.screenNumber = screenNumber;

        seats = new ArrayList<>();

        cancelled = false;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getShowTime() {
        return showTime;
    }

    public int getScreenNumber() {
        return screenNumber;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void addSeat(Seat seat) {

        seats.add(seat);

        seat.book();
    }

    public void cancelBooking() {

        for (Seat seat : seats) {

            seat.cancel();
        }

        cancelled = true;
    }

    public int getTotalAmount() {

        int total = 0;

        for (Seat seat : seats) {

            total += seat.getPrice();
        }

        return total;
    }

    public double getDiscount() {

        int total = getTotalAmount();

        if (total >= 1000) {

            return total * 0.10;
        }

        return 0;
    }

    public double getGST() {

        double amountAfterDiscount =
                getTotalAmount() - getDiscount();

        return amountAfterDiscount * 0.18;
    }

    public double getFinalAmount() {

        double amountAfterDiscount =
                getTotalAmount() - getDiscount();

        return amountAfterDiscount + getGST();
    }

    public void setPaymentMethod(String paymentMethod) {

        this.paymentMethod = paymentMethod;
    }

    public void showBookedSeats() {

        if (seats.size() == 0) {

            System.out.println("No seats booked.");

            return;
        }

        System.out.println("\nBooked Seats:");

        for (Seat seat : seats) {

            System.out.print(
                    seat.getSeatNumber()
                    + "("
                    + seat.getSeatType()
                    + ") "
            );
        }

        System.out.println();
    }

    public void generateReceipt() {

        System.out.println("\n");
        System.out.println("================================================");
        System.out.println("              MOVIE TICKET RECEIPT");
        System.out.println("================================================");

        System.out.println(
                "Booking ID    : " + bookingId
        );

        System.out.println(
                "Customer Name : " + customer.getName()
        );

        System.out.println(
                "Mobile Number : " + customer.getMobile()
        );

        System.out.println(
                "Email         : " + customer.getEmail()
        );

        System.out.println(
                "Movie         : " + movie.getName()
        );

        System.out.println(
                "Show Time     : " + showTime
        );

        System.out.println(
                "Screen        : Screen " + screenNumber
        );

        System.out.println("-----------------------------------------------");

        System.out.println("Seats:");

        for (Seat seat : seats) {

            System.out.println(
                    seat.getSeatNumber()
                    + " - "
                    + seat.getSeatType()
                    + " - ₹"
                    + seat.getPrice()
            );
        }

        System.out.println("-----------------------------------------------");

        System.out.println(
                "Ticket Amount : ₹" + getTotalAmount()
        );

        System.out.println(
                "Discount      : ₹"
                + String.format("%.2f", getDiscount())
        );

        System.out.println(
                "GST (18%)     : ₹"
                + String.format("%.2f", getGST())
        );

        System.out.println(
                "Final Amount  : ₹"
                + String.format("%.2f", getFinalAmount())
        );

        System.out.println(
                "Payment       : " + paymentMethod
        );

        System.out.println(
                "Status        : "
                + (cancelled ? "CANCELLED" : "CONFIRMED")
        );

        System.out.println("================================================");
        System.out.println("             THANK YOU! ENJOY MOVIE");
        System.out.println("================================================");
    }
}

// ======================================================
// SHOW CLASS
// ======================================================

class Show implements Serializable {

    private static final long serialVersionUID = 1L;

    private int showId;
    private String time;
    private int screenNumber;

    Show(int showId, String time, int screenNumber) {

        this.showId = showId;
        this.time = time;
        this.screenNumber = screenNumber;
    }

    public int getShowId() {
        return showId;
    }

    public String getTime() {
        return time;
    }

    public int getScreenNumber() {
        return screenNumber;
    }

    public void display() {

        System.out.println(
                showId
                + ". "
                + time
                + " - Screen "
                + screenNumber
        );
    }
}

// ======================================================
// MAIN CLASS
// ======================================================

public class Movie_Theater_SheetBooking {

    static Scanner sc = new Scanner(System.in);

    // ==================================================
    // MOVIES
    // ==================================================

    static ArrayList<Movie> movies =
            new ArrayList<>();

    // ==================================================
    // SHOWS
    // ==================================================

    static ArrayList<Show> shows =
            new ArrayList<>();

    // ==================================================
    // BOOKINGS
    // ==================================================

    static ArrayList<Booking> bookings =
            new ArrayList<>();

    // ==================================================
    // SEATS
    // 3 Screens × 25 Seats
    // ==================================================

    static Seat[][][] seats =
            new Seat[3][5][5];

    // ==================================================
    // CUSTOMER
    // ==================================================

    static Customer customer;

    // ==================================================
    // SELECTED MOVIE
    // ==================================================

    static Movie selectedMovie;

    // ==================================================
    // SELECTED SHOW
    // ==================================================

    static Show selectedShow;

    // ==================================================
    // FILE
    // ==================================================

    static final String FILE_NAME =
            "movie_booking.dat";

    // ==================================================
    // ADMIN LOGIN
    // ==================================================

    static final String ADMIN_USERNAME =
            "admin";

    static final String ADMIN_PASSWORD =
            "admin123";

    // ==================================================
    // MAIN
    // ==================================================

    public static void main(String[] args) {

        loadData();

        initializeMovies();

        initializeShows();

        createSeats();

        System.out.println(
                "\n=========================================="
        );

        System.out.println(
                "       MOVIE THEATER BOOKING SYSTEM"
        );

        System.out.println(
                "=========================================="
        );

        customerRegistration();

        mainMenu();
    }

    // ==================================================
    // DEFAULT MOVIES
    // ==================================================

    static void initializeMovies() {

        if (movies.size() == 0) {

            movies.add(
                    new Movie(1, "Avengers")
            );

            movies.add(
                    new Movie(2, "Pushpa 2")
            );

            movies.add(
                    new Movie(3, "Dangal")
            );
        }
    }

    // ==================================================
    // DEFAULT SHOWS
    // ==================================================

    static void initializeShows() {

        if (shows.size() == 0) {

            shows.add(
                    new Show(1, "10:00 AM", 1)
            );

            shows.add(
                    new Show(2, "2:00 PM", 2)
            );

            shows.add(
                    new Show(3, "6:00 PM", 3)
            );

            shows.add(
                    new Show(4, "9:00 PM", 1)
            );
        }
    }

    // ==================================================
    // CUSTOMER REGISTRATION
    // ==================================================

    static void customerRegistration() {

        System.out.println(
                "\n========== CUSTOMER REGISTRATION =========="
        );

        System.out.print(
                "Enter Customer Name: "
        );

        String name = sc.nextLine();

        System.out.print(
                "Enter Mobile Number: "
        );

        String mobile = sc.nextLine();

        System.out.print(
                "Enter Email: "
        );

        String email = sc.nextLine();

        customer =
                new Customer(
                        name,
                        mobile,
                        email
                );

        System.out.println(
                "\nRegistration Successful!"
        );
    }

    // ==================================================
    // MAIN MENU
    // ==================================================

    static void mainMenu() {

        int choice;

        do {

            System.out.println(
                    "\n======================================"
            );

            System.out.println(
                    "               MAIN MENU"
            );

            System.out.println(
                    "======================================"
            );

            System.out.println(
                    "1. Show Movies"
            );

            System.out.println(
                    "2. Select Movie"
            );

            System.out.println(
                    "3. Show Timings"
            );

            System.out.println(
                    "4. Select Show"
            );

            System.out.println(
                    "5. Show Seat Layout"
            );

            System.out.println(
                    "6. Show Available Seats"
            );

            System.out.println(
                    "7. Book Seats"
            );

            System.out.println(
                    "8. Show My Bookings"
            );

            System.out.println(
                    "9. Search Booking"
            );

            System.out.println(
                    "10. Cancel Booking"
            );

            System.out.println(
                    "11. Generate Receipt"
            );

            System.out.println(
                    "12. Customer Details"
            );

            System.out.println(
                    "13. Admin Login"
            );

            System.out.println(
                    "14. Save Data"
            );

            System.out.println(
                    "15. Exit"
            );

            System.out.print(
                    "Enter Choice: "
            );

            choice = readInt();

            switch (choice) {

                case 1:

                    showMovies();

                    break;

                case 2:

                    selectMovie();

                    break;

                case 3:

                    showTimings();

                    break;

                case 4:

                    selectShow();

                    break;

                case 5:

                    showSeatLayout();

                    break;

                case 6:

                    showAvailableSeats();

                    break;

                case 7:

                    bookSeats();

                    break;

                case 8:

                    showMyBookings();

                    break;

                case 9:

                    searchBooking();

                    break;

                case 10:

                    cancelBooking();

                    break;

                case 11:

                    generateReceipt();

                    break;

                case 12:

                    customer.displayCustomer();

                    break;

                case 13:

                    adminLogin();

                    break;

                case 14:

                    saveData();

                    break;

                case 15:

                    saveData();

                    System.out.println(
                            "\nThank you for visiting!"
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid Choice!"
                    );
            }

        } while (choice != 15);
    }

    // ==================================================
    // SHOW MOVIES
    // ==================================================

    static void showMovies() {

        System.out.println(
                "\n========== MOVIES =========="
        );

        for (Movie movie : movies) {

            movie.display();
        }
    }

    // ==================================================
    // SELECT MOVIE
    // ==================================================

    static void selectMovie() {

        showMovies();

        System.out.print(
                "Enter Movie ID: "
        );

        int id = readInt();

        Movie movie = findMovie(id);

        if (movie == null) {

            System.out.println(
                    "Movie not found!"
            );

            return;
        }

        selectedMovie = movie;

        System.out.println(
                "Movie Selected: "
                + movie.getName()
        );
    }

    // ==================================================
    // FIND MOVIE
    // ==================================================

    static Movie findMovie(int id) {

        for (Movie movie : movies) {

            if (movie.getId() == id) {

                return movie;
            }
        }

        return null;
    }

    // ==================================================
    // SEARCH MOVIE
    // ==================================================

    static void searchMovie() {

        sc.nextLine();

        System.out.print(
                "Enter movie name to search: "
        );

        String name =
                sc.nextLine();

        boolean found = false;

        for (Movie movie : movies) {

            if (movie.getName()
                    .toLowerCase()
                    .contains(name.toLowerCase())) {

                movie.display();

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "Movie not found!"
            );
        }
    }

    // ==================================================
    // SHOW TIMINGS
    // ==================================================

    static void showTimings() {

        System.out.println(
                "\n========== SHOW TIMINGS =========="
        );

        for (Show show : shows) {

            show.display();
        }
    }

    // ==================================================
    // SELECT SHOW
    // ==================================================

    static void selectShow() {

        showTimings();

        System.out.print(
                "Enter Show ID: "
        );

        int id = readInt();

        for (Show show : shows) {

            if (show.getShowId() == id) {

                selectedShow = show;

                System.out.println(
                        "\nShow Selected: "
                        + show.getTime()
                );

                System.out.println(
                        "Screen: "
                        + show.getScreenNumber()
                );

                return;
            }
        }

        System.out.println(
                "Invalid Show!"
        );
    }

    // ==================================================
    // CREATE SEATS
    // ==================================================

    static void createSeats() {

        for (int screen = 0;
             screen < 3;
             screen++) {

            for (int i = 0;
                 i < 5;
                 i++) {

                for (int j = 0;
                     j < 5;
                     j++) {

                    String seatNumber =
                            (char) ('A' + i)
                            + ""
                            + (j + 1);

                    String seatType;

                    int price;

                    if (i < 2) {

                        seatType = "Silver";

                        price = 150;

                    } else if (i < 4) {

                        seatType = "Gold";

                        price = 200;

                    } else {

                        seatType = "Premium";

                        price = 300;
                    }

                    seats[screen][i][j] =
                            new Seat(
                                    seatNumber,
                                    seatType,
                                    price
                            );
                }
            }
        }
    }

    // ==================================================
    // SHOW SEAT LAYOUT
    // ==================================================

    static void showSeatLayout() {

        if (selectedShow == null) {

            System.out.println(
                    "Please select a show first!"
            );

            return;
        }

        int screen =
                selectedShow.getScreenNumber() - 1;

        System.out.println(
                "\n========== SCREEN "
                + selectedShow.getScreenNumber()
                + " =========="
        );

        System.out.println(
                "              SCREEN"
        );

        System.out.println(
                "------------------------------------"
        );

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {

                Seat seat =
                        seats[screen][i][j];

                String status;

                if (seat.isBooked()) {

                    status = "X";

                } else {

                    status = "O";
                }

                System.out.print(
                        seat.getSeatNumber()
                        + "="
                        + status
                        + " "
                );
            }

            System.out.println();
        }

        System.out.println(
                "\nO = Available"
        );

        System.out.println(
                "X = Booked"
        );

        System.out.println(
                "Silver = ₹150"
        );

        System.out.println(
                "Gold = ₹200"
        );

        System.out.println(
                "Premium = ₹300"
        );
    }

    // ==================================================
    // SHOW AVAILABLE SEATS
    // ==================================================

    static void showAvailableSeats() {

        if (selectedShow == null) {

            System.out.println(
                    "Please select a show first!"
            );

            return;
        }

        int screen =
                selectedShow.getScreenNumber() - 1;

        System.out.println(
                "\n======= AVAILABLE SEATS ======="
        );

        boolean found = false;

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {

                Seat seat =
                        seats[screen][i][j];

                if (!seat.isBooked()) {

                    System.out.print(
                            seat.getSeatNumber()
                            + "("
                            + seat.getSeatType()
                            + ") "
                    );

                    found = true;
                }
            }
        }

        if (!found) {

            System.out.println(
                    "No seats available!"
            );
        }

        System.out.println();
    }

    // ==================================================
    // FIND SEAT
    // ==================================================

    static Seat findSeat(
            String seatNumber,
            int screen) {

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {

                Seat seat =
                        seats[screen][i][j];

                if (seat.getSeatNumber()
                        .equalsIgnoreCase(
                                seatNumber)) {

                    return seat;
                }
            }
        }

        return null;
    }

    // ==================================================
    // BOOK SEATS
    // ==================================================

    static void bookSeats() {

        if (selectedMovie == null) {

            System.out.println(
                    "Please select movie first!"
            );

            return;
        }

        if (selectedShow == null) {

            System.out.println(
                    "Please select show first!"
            );

            return;
        }

        int screen =
                selectedShow.getScreenNumber() - 1;

        System.out.print(
                "\nHow many seats do you want to book? "
        );

        int count = readInt();

        if (count <= 0 || count > 25) {

            System.out.println(
                    "Invalid number of seats!"
            );

            return;
        }

        String bookingId =
                "BK"
                + (1000
                + (int) (Math.random() * 9000));

        Booking booking =
                new Booking(
                        bookingId,
                        customer,
                        selectedMovie,
                        selectedShow.getTime(),
                        selectedShow.getScreenNumber()
                );

        for (int i = 1; i <= count; i++) {

            while (true) {

                System.out.print(
                        "Enter Seat "
                        + i
                        + " (A1-E5): "
                );

                String seatNumber =
                        sc.next().toUpperCase();

                Seat seat =
                        findSeat(
                                seatNumber,
                                screen
                        );

                if (seat == null) {

                    System.out.println(
                            "Invalid Seat!"
                    );

                    continue;
                }

                if (seat.isBooked()) {

                    System.out.println(
                            "Seat already booked!"
                    );

                    continue;
                }

                booking.addSeat(seat);

                System.out.println(
                        "Seat "
                        + seatNumber
                        + " booked successfully!"
                );

                break;
            }
        }

        // Payment
        System.out.println(
                "\n========== PAYMENT =========="
        );

        System.out.println(
                "1. Cash"
        );

        System.out.println(
                "2. UPI"
        );

        System.out.println(
                "3. Card"
        );

        System.out.print(
                "Select Payment Method: "
        );

        int payment =
                readInt();

        String paymentMethod;

        if (payment == 1) {

            paymentMethod = "Cash";

        } else if (payment == 2) {

            paymentMethod = "UPI";

        } else if (payment == 3) {

            paymentMethod = "Card";

        } else {

            paymentMethod = "Cash";

            System.out.println(
                    "Invalid payment. Cash selected."
            );
        }

        booking.setPaymentMethod(
                paymentMethod
        );

        bookings.add(booking);

        saveData();

        System.out.println(
                "\n================================"
        );

        System.out.println(
                "       BOOKING SUCCESSFUL"
        );

        System.out.println(
                "================================"
        );

        System.out.println(
                "Booking ID : "
                + booking.getBookingId()
        );

        System.out.println(
                "Amount     : ₹"
                + String.format(
                        "%.2f",
                        booking.getFinalAmount()
                )
        );
    }

    // ==================================================
    // SHOW MY BOOKINGS
    // ==================================================

    static void showMyBookings() {

        System.out.println(
                "\n========== BOOKING HISTORY =========="
        );

        boolean found = false;

        for (Booking booking : bookings) {

            if (booking.getCustomer()
                    .getMobile()
                    .equals(customer.getMobile())) {

                System.out.println(
                        "\nBooking ID : "
                        + booking.getBookingId()
                );

                System.out.println(
                        "Movie      : "
                        + booking.getMovie().getName()
                );

                System.out.println(
                        "Show       : "
                        + booking.getShowTime()
                );

                System.out.println(
                        "Screen     : "
                        + booking.getScreenNumber()
                );

                System.out.println(
                        "Seats      : "
                        + booking.getSeats().size()
                );

                System.out.println(
                        "Amount     : ₹"
                        + String.format(
                                "%.2f",
                                booking.getFinalAmount()
                        )
                );

                System.out.println(
                        "Status     : "
                        + (booking.isCancelled()
                        ? "CANCELLED"
                        : "CONFIRMED")
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No booking history found."
            );
        }
    }

    // ==================================================
    // SEARCH BOOKING
    // ==================================================

    static void searchBooking() {

        sc.nextLine();

        System.out.println(
                "\n========== SEARCH BOOKING =========="
        );

        System.out.print(
                "Enter Booking ID or Mobile Number: "
        );

        String search =
                sc.nextLine();

        boolean found = false;

        for (Booking booking : bookings) {

            if (booking.getBookingId()
                    .equalsIgnoreCase(search)
                    ||
                    booking.getCustomer()
                            .getMobile()
                            .equals(search)) {

                booking.generateReceipt();

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "Booking not found!"
            );
        }
    }

    // ==================================================
    // CANCEL BOOKING
    // ==================================================

    static void cancelBooking() {

        sc.nextLine();

        System.out.print(
                "\nEnter Booking ID: "
        );

        String id =
                sc.nextLine();

        for (Booking booking : bookings) {

            if (booking.getBookingId()
                    .equalsIgnoreCase(id)) {

                if (booking.isCancelled()) {

                    System.out.println(
                            "Booking already cancelled!"
                    );

                    return;
                }

                booking.cancelBooking();

                saveData();

                System.out.println(
                        "\nBooking "
                        + id
                        + " cancelled successfully!"
                );

                return;
            }
        }

        System.out.println(
                "Booking ID not found!"
        );
    }

    // ==================================================
    // GENERATE RECEIPT
    // ==================================================

    static void generateReceipt() {

        sc.nextLine();

        System.out.print(
                "\nEnter Booking ID: "
        );

        String id =
                sc.nextLine();

        for (Booking booking : bookings) {

            if (booking.getBookingId()
                    .equalsIgnoreCase(id)) {

                booking.generateReceipt();

                return;
            }
        }

        System.out.println(
                "Booking not found!"
        );
    }

    // ==================================================
    // ADMIN LOGIN
    // ==================================================

    static void adminLogin() {

        sc.nextLine();

        System.out.println(
                "\n========== ADMIN LOGIN =========="
        );

        System.out.print(
                "Username: "
        );

        String username =
                sc.nextLine();

        System.out.print(
                "Password: "
        );

        String password =
                sc.nextLine();

        if (username.equals(ADMIN_USERNAME)
                &&
                password.equals(ADMIN_PASSWORD)) {

            System.out.println(
                    "\nAdmin Login Successful!"
            );

            adminMenu();

        } else {

            System.out.println(
                    "Invalid Username or Password!"
            );
        }
    }

    // ==================================================
    // ADMIN MENU
    // ==================================================

    static void adminMenu() {

        int choice;

        do {

            System.out.println(
                    "\n=================================="
            );

            System.out.println(
                    "             ADMIN MENU"
            );

            System.out.println(
                    "=================================="
            );

            System.out.println(
                    "1. Show Movies"
            );

            System.out.println(
                    "2. Add Movie"
            );

            System.out.println(
                    "3. Delete Movie"
            );

            System.out.println(
                    "4. Search Movie"
            );

            System.out.println(
                    "5. Show Timings"
            );

            System.out.println(
                    "6. Add Show"
            );

            System.out.println(
                    "7. Delete Show"
            );

            System.out.println(
                    "8. Admin Reports"
            );

            System.out.println(
                    "9. Save Data"
            );

            System.out.println(
                    "10. Logout"
            );

            System.out.print(
                    "Enter Choice: "
            );

            choice = readInt();

            switch (choice) {

                case 1:

                    showMovies();

                    break;

                case 2:

                    addMovie();

                    break;

                case 3:

                    deleteMovie();

                    break;

                case 4:

                    searchMovie();

                    break;

                case 5:

                    showTimings();

                    break;

                case 6:

                    addShow();

                    break;

                case 7:

                    deleteShow();

                    break;

                case 8:

                    adminReports();

                    break;

                case 9:

                    saveData();

                    break;

                case 10:

                    System.out.println(
                            "Admin Logout Successful!"
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid Choice!"
                    );
            }

        } while (choice != 10);
    }

    // ==================================================
    // ADD MOVIE
    // ==================================================

    static void addMovie() {

        sc.nextLine();

        System.out.print(
                "Enter Movie Name: "
        );

        String name =
                sc.nextLine();

        int id = 1;

        for (Movie movie : movies) {

            if (movie.getId() >= id) {

                id = movie.getId() + 1;
            }
        }

        movies.add(
                new Movie(id, name)
        );

        saveData();

        System.out.println(
                "Movie Added Successfully!"
        );
    }

    // ==================================================
    // DELETE MOVIE
    // ==================================================

    static void deleteMovie() {

        showMovies();

        System.out.print(
                "Enter Movie ID to Delete: "
        );

        int id =
                readInt();

        Movie movie =
                findMovie(id);

        if (movie == null) {

            System.out.println(
                    "Movie not found!"
            );

            return;
        }

        movies.remove(movie);

        saveData();

        System.out.println(
                "Movie Deleted Successfully!"
        );
    }

    // ==================================================
    // ADD SHOW
    // ==================================================

    static void addShow() {

        sc.nextLine();

        System.out.print(
                "Enter Show Time: "
        );

        String time =
                sc.nextLine();

        System.out.print(
                "Enter Screen Number (1-3): "
        );

        int screen =
                readInt();

        if (screen < 1 || screen > 3) {

            System.out.println(
                    "Invalid Screen!"
            );

            return;
        }

        int id = 1;

        for (Show show : shows) {

            if (show.getShowId() >= id) {

                id = show.getShowId() + 1;
            }
        }

        shows.add(
                new Show(
                        id,
                        time,
                        screen
                )
        );

        saveData();

        System.out.println(
                "Show Added Successfully!"
        );
    }

    // ==================================================
    // DELETE SHOW
    // ==================================================

    static void deleteShow() {

        showTimings();

        System.out.print(
                "Enter Show ID to Delete: "
        );

        int id =
                readInt();

        for (Show show : shows) {

            if (show.getShowId() == id) {

                shows.remove(show);

                saveData();

                System.out.println(
                        "Show Deleted Successfully!"
                );

                return;
            }
        }

        System.out.println(
                "Show not found!"
        );
    }

    // ==================================================
    // ADMIN REPORTS
    // ==================================================

    static void adminReports() {

        int totalBookings = 0;

        int confirmedBookings = 0;

        int cancelledBookings = 0;

        double revenue = 0;

        for (Booking booking : bookings) {

            totalBookings++;

            if (booking.isCancelled()) {

                cancelledBookings++;

            } else {

                confirmedBookings++;

                revenue +=
                        booking.getFinalAmount();
            }
        }

        int totalSeats = 3 * 25;

        int bookedSeats = 0;

        for (Booking booking : bookings) {

            if (!booking.isCancelled()) {

                bookedSeats +=
                        booking.getSeats().size();
            }
        }

        int availableSeats =
                totalSeats - bookedSeats;

        System.out.println(
                "\n===================================="
        );

        System.out.println(
                "          ADMIN REPORT"
        );

        System.out.println(
                "===================================="
        );

        System.out.println(
                "Total Movies       : "
                + movies.size()
        );

        System.out.println(
                "Total Shows        : "
                + shows.size()
        );

        System.out.println(
                "Total Bookings     : "
                + totalBookings
        );

        System.out.println(
                "Confirmed Bookings : "
                + confirmedBookings
        );

        System.out.println(
                "Cancelled Bookings : "
                + cancelledBookings
        );

        System.out.println(
                "Booked Seats       : "
                + bookedSeats
        );

        System.out.println(
                "Available Seats    : "
                + availableSeats
        );

        System.out.println(
                "Total Revenue      : ₹"
                + String.format(
                        "%.2f",
                        revenue
                )
        );

        System.out.println(
                "===================================="
        );
    }

    // ==================================================
    // SAVE DATA
    // ==================================================

    static void saveData() {

        try {

            FileOutputStream fos =
                    new FileOutputStream(
                            FILE_NAME
                    );

            ObjectOutputStream oos =
                    new ObjectOutputStream(fos);

            oos.writeObject(movies);

            oos.writeObject(shows);

            oos.writeObject(bookings);

            oos.close();

            fos.close();

            System.out.println(
                    "Data saved successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "Error while saving data: "
                    + e.getMessage()
            );
        }
    }

    // ==================================================
    // LOAD DATA
    // ==================================================

    @SuppressWarnings("unchecked")
    static void loadData() {

        File file =
                new File(FILE_NAME);

        if (!file.exists()) {

            return;
        }

        try {

            FileInputStream fis =
                    new FileInputStream(
                            FILE_NAME
                    );

            ObjectInputStream ois =
                    new ObjectInputStream(fis);

            movies =
                    (ArrayList<Movie>)
                            ois.readObject();

            shows =
                    (ArrayList<Show>)
                            ois.readObject();

            bookings =
                    (ArrayList<Booking>)
                            ois.readObject();

            ois.close();

            fis.close();

            System.out.println(
                    "Previous data loaded successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "Unable to load previous data."
            );
        }
    }

    // ==================================================
    // SAFE INTEGER INPUT
    // ==================================================

    static int readInt() {

        while (!sc.hasNextInt()) {

            System.out.println(
                    "Please enter a valid number!"
            );

            sc.next();
        }

        return sc.nextInt();
    }
}