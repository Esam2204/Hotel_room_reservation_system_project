package hotel;

import hotel.model.*;
import hotel.service.*;
import hotel.util.InputUtil;
import hotel.util.ValidationUtil;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final GuestService guestService = new GuestService();
    private static final RoomService roomService = new RoomService();
    private static final ReservationService reservationService = new ReservationService(roomService);

    public static void main(String[] args) {
        showPolymorphismDemo();
        runApplication();
    }

    private static void showPolymorphismDemo() {
        System.out.println("=====================================");
        System.out.println("Hotel Room Reservation System");
        System.out.println("=====================================");

        Person[] people = {
                new Guest(0, "Demo Guest", "guest@example.com", "+123456789"),
                new Admin(0, "Demo Admin", "admin@example.com", "Front Office")
        };

        System.out.println("Polymorphism demo:");

        for (Person person : people) {
            System.out.println(person.getName() + " -> " + person.getRoleDescription());
        }

        System.out.println();
    }

    private static void runApplication() {
        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = InputUtil.promptInt(scanner, "Choose an option: ");

            try {
                switch (choice) {
                    case 0 -> {
                        running = false;
                        System.out.println("Goodbye!");
                    }
                    case 1 -> guestMenu();
                    case 2 -> roomMenu();
                    case 3 -> reservationMenu();
                    case 4 -> exportMenu();
                    case 5 -> importMenu();
                    default -> System.out.println("Invalid menu option.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Validation error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Manage Guests");
        System.out.println("2. Manage Rooms");
        System.out.println("3. Manage Reservations");
        System.out.println("4. Export Data");
        System.out.println("5. Import Data");
        System.out.println("0. Exit");
    }

    private static void guestMenu() {
        System.out.println("\n----- GUEST MENU -----");
        System.out.println("1. Add guest");
        System.out.println("2. View all guests");
        System.out.println("3. Update guest");
        System.out.println("4. Delete guest");
        System.out.println("0. Back");

        int choice = InputUtil.promptInt(scanner, "Choose an option: ");

        switch (choice) {
            case 1 -> addGuest();
            case 2 -> viewGuests();
            case 3 -> updateGuest();
            case 4 -> deleteGuest();
        }
    }

    private static void roomMenu() {
        System.out.println("\n----- ROOM MENU -----");
        System.out.println("1. Add room");
        System.out.println("2. View all rooms");
        System.out.println("3. Update room");
        System.out.println("4. Delete room");
        System.out.println("0. Back");

        int choice = InputUtil.promptInt(scanner, "Choose an option: ");

        switch (choice) {
            case 1 -> addRoom();
            case 2 -> viewRooms();
            case 3 -> updateRoom();
            case 4 -> deleteRoom();
        }
    }

    private static void reservationMenu() {
        System.out.println("\n----- RESERVATION MENU -----");
        System.out.println("1. Add reservation");
        System.out.println("2. View all reservations");
        System.out.println("3. Update reservation");
        System.out.println("4. Delete reservation");
        System.out.println("0. Back");

        int choice = InputUtil.promptInt(scanner, "Choose an option: ");

        switch (choice) {
            case 1 -> addReservation();
            case 2 -> viewReservations();
            case 3 -> updateReservation();
            case 4 -> deleteReservation();
        }
    }

    // ================= GUEST =================

    private static void addGuest() {
        String name = InputUtil.promptNonEmpty(scanner, "Enter guest name: ");
        String email = InputUtil.promptEmail(scanner, "Enter guest email: ");
        String phone = InputUtil.promptNonEmpty(scanner, "Enter guest phone: ");

        Guest guest = new Guest(guestService.generateNextId(), name, email, phone);
        guestService.addGuest(guest);

        System.out.println("Guest added successfully.");
    }

    private static void viewGuests() {
        List<Guest> guests = guestService.getAllGuests();

        if (guests.isEmpty()) {
            System.out.println("No guests found.");
        } else {
            for (Guest guest : guests) {
                System.out.println(guest);
            }
        }
    }

    private static void updateGuest() {
        int id = InputUtil.promptInt(scanner, "Enter guest ID: ");

        if (guestService.findById(id) == null) {
            System.out.println("Guest not found.");
            return;
        }

        String name = InputUtil.promptNonEmpty(scanner, "Enter new name: ");
        String email = InputUtil.promptEmail(scanner, "Enter new email: ");
        String phone = InputUtil.promptNonEmpty(scanner, "Enter new phone: ");

        guestService.updateGuest(id, name, email, phone);
        System.out.println("Guest updated successfully.");
    }

    private static void deleteGuest() {
        int id = InputUtil.promptInt(scanner, "Enter guest ID: ");

        if (guestService.deleteGuest(id)) {
            System.out.println("Guest deleted successfully.");
        } else {
            System.out.println("Guest not found.");
        }
    }

    // ================= ROOM =================

    private static void addRoom() {
        String roomNumber = InputUtil.promptNonEmpty(scanner, "Enter room number: ");
        String type = InputUtil.promptNonEmpty(scanner, "Enter room type: ");
        double price = InputUtil.promptDouble(scanner, "Enter price: ");
        boolean available = InputUtil.promptBoolean(scanner, "Is available?");

        Room room = new Room(roomService.generateNextId(), roomNumber, type, price, available);
        roomService.addRoom(room);

        System.out.println("Room added successfully.");
    }

    private static void viewRooms() {
        List<Room> rooms = roomService.getAllRooms();

        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            for (Room room : rooms) {
                System.out.println(room);
            }
        }
    }

    private static void updateRoom() {
        int id = InputUtil.promptInt(scanner, "Enter room ID: ");

        if (roomService.findById(id) == null) {
            System.out.println("Room not found.");
            return;
        }

        String roomNumber = InputUtil.promptNonEmpty(scanner, "Enter new room number: ");
        String type = InputUtil.promptNonEmpty(scanner, "Enter new type: ");
        double price = InputUtil.promptDouble(scanner, "Enter new price: ");
        boolean available = InputUtil.promptBoolean(scanner, "Is available?");

        roomService.updateRoom(id, roomNumber, type, price, available);
        System.out.println("Room updated successfully.");
    }

    private static void deleteRoom() {
        int id = InputUtil.promptInt(scanner, "Enter room ID: ");

        if (roomService.deleteRoom(id)) {
            System.out.println("Room deleted successfully.");
        } else {
            System.out.println("Room not found.");
        }
    }

    // ================= RESERVATION =================

    private static void addReservation() {
        if (guestService.getAllGuests().isEmpty() || roomService.getAllRooms().isEmpty()) {
            System.out.println("You need guests and rooms first.");
            return;
        }

        viewGuests();
        int guestId = InputUtil.promptInt(scanner, "Enter guest ID: ");

        if (guestService.findById(guestId) == null) {
            System.out.println("Guest not found.");
            return;
        }

        viewRooms();
        int roomId = InputUtil.promptInt(scanner, "Enter room ID: ");
        Room room = roomService.findById(roomId);

        if (room == null || !room.isAvailable()) {
            System.out.println("Room not available.");
            return;
        }

        String checkIn = InputUtil.promptDate(scanner, "Enter check-in: ");
        String checkOut = InputUtil.promptDate(scanner, "Enter check-out: ");

        if (!ValidationUtil.isCheckOutAfterCheckIn(checkIn, checkOut)) {
            System.out.println("Invalid dates.");
            return;
        }

        String status = InputUtil.promptNonEmpty(scanner, "Enter status: ");

        Reservation reservation = new Reservation(
                reservationService.generateNextId(),
                guestId,
                roomId,
                checkIn,
                checkOut,
                status
        );

        reservationService.addReservation(reservation);
        System.out.println("Reservation added successfully.");
    }

    private static void viewReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    private static void updateReservation() {
        int id = InputUtil.promptInt(scanner, "Enter reservation ID: ");

        if (reservationService.findById(id) == null) {
            System.out.println("Reservation not found.");
            return;
        }

        viewGuests();
        int guestId = InputUtil.promptInt(scanner, "Enter new guest ID: ");

        viewRooms();
        int roomId = InputUtil.promptInt(scanner, "Enter new room ID: ");

        String checkIn = InputUtil.promptDate(scanner, "Enter new check-in: ");
        String checkOut = InputUtil.promptDate(scanner, "Enter new check-out: ");

        if (!ValidationUtil.isCheckOutAfterCheckIn(checkIn, checkOut)) {
            System.out.println("Invalid dates.");
            return;
        }

        String status = InputUtil.promptNonEmpty(scanner, "Enter new status: ");

        reservationService.updateReservation(id, guestId, roomId, checkIn, checkOut, status);
        System.out.println("Reservation updated successfully.");
    }

    private static void deleteReservation() {
        int id = InputUtil.promptInt(scanner, "Enter reservation ID: ");

        if (reservationService.deleteReservation(id)) {
            System.out.println("Deleted successfully.");
        } else {
            System.out.println("Not found.");
        }
    }

    // ================= EXPORT / IMPORT =================

    private static void exportMenu() {
        int choice = InputUtil.promptInt(scanner, "1.Guests 2.Rooms 3.Reservations: ");

        switch (choice) {
            case 1 -> guestService.exportToCsv("exports/guests.csv");
            case 2 -> roomService.exportToCsv("exports/rooms.csv");
            case 3 -> reservationService.exportToCsv("exports/reservations.csv");
        }
    }

    private static void importMenu() {
        int choice = InputUtil.promptInt(scanner, "1.Guests 2.Rooms 3.Reservations: ");

        switch (choice) {
            case 1 -> guestService.importFromCsv("exports/guests.csv");
            case 2 -> roomService.importFromCsv("exports/rooms.csv");
            case 3 -> reservationService.importFromCsv("exports/reservations.csv");
        }
    }
}