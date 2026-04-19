//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hotel;

import hotel.model.Admin;
import hotel.model.Guest;
import hotel.model.Person;
import hotel.model.Reservation;
import hotel.model.Room;
import hotel.service.GuestService;
import hotel.service.ReservationService;
import hotel.service.RoomService;
import hotel.util.InputUtil;
import hotel.util.ValidationUtil;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner;
    private static final GuestService guestService;
    private static final RoomService roomService;
    private static final ReservationService reservationService;

    public Main() {
    }

    public static void main(String[] var0) {
        showPolymorphismDemo();
        runApplication();
    }

    private static void showPolymorphismDemo() {
        System.out.println("=====================================");
        System.out.println("Hotel Room Reservation System");
        System.out.println("=====================================");
        Person[] var0 = new Person[]{new Guest(0, "Demo Guest", "guest@example.com", "+123456789"), new Admin(0, "Demo Admin", "admin@example.com", "Front Office")};
        System.out.println("Polymorphism demo:");

        for(Person var4 : var0) {
            PrintStream var10000 = System.out;
            String var10001 = var4.getName();
            var10000.println(var10001 + " -> " + var4.getRoleDescription());
        }

        System.out.println();
    }

    private static void runApplication() {
        boolean var0 = true;

        while(var0) {
            printMainMenu();
            int var1 = InputUtil.promptInt(scanner, "Choose an option: ");

            try {
                switch (var1) {
                    case 0:
                        var0 = false;
                        System.out.println("Goodbye!");
                        break;
                    case 1:
                        guestMenu();
                        break;
                    case 2:
                        roomMenu();
                        break;
                    case 3:
                        reservationMenu();
                        break;
                    case 4:
                        exportMenu();
                        break;
                    case 5:
                        importMenu();
                        break;
                    default:
                        System.out.println("Invalid menu option.");
                }
            } catch (IllegalArgumentException var3) {
                System.out.println("Validation error: " + var3.getMessage());
            } catch (Exception var4) {
                System.out.println("Unexpected error: " + var4.getMessage());
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
        int var0 = InputUtil.promptInt(scanner, "Choose an option: ");
        switch (var0) {
            case 0 -> { }
            case 1 -> addGuest();
            case 2 -> viewGuests();
            case 3 -> updateGuest();
            case 4 -> deleteGuest();
            default -> System.out.println("Invalid menu option.");
        }

    }

    private static void roomMenu() {
        System.out.println("\n----- ROOM MENU -----");
        System.out.println("1. Add room");
        System.out.println("2. View all rooms");
        System.out.println("3. Update room");
        System.out.println("4. Delete room");
        System.out.println("0. Back");
        int var0 = InputUtil.promptInt(scanner, "Choose an option: ");
        switch (var0) {
            case 0 -> { }
            case 1 -> addRoom();
            case 2 -> viewRooms();
            case 3 -> updateRoom();
            case 4 -> deleteRoom();
            default -> System.out.println("Invalid menu option.");
        }

    }

    private static void reservationMenu() {
        System.out.println("\n----- RESERVATION MENU -----");
        System.out.println("1. Add reservation");
        System.out.println("2. View all reservations");
        System.out.println("3. Update reservation");
        System.out.println("4. Delete reservation");
        System.out.println("0. Back");
        int var0 = InputUtil.promptInt(scanner, "Choose an option: ");
        switch (var0) {
            case 0 -> { }
            case 1 -> addReservation();
            case 2 -> viewReservations();
            case 3 -> updateReservation();
            case 4 -> deleteReservation();
            default -> System.out.println("Invalid menu option.");
        }

    }

    private static void addGuest() {
        String var0 = InputUtil.promptNonEmpty(scanner, "Enter guest name: ");
        String var1 = InputUtil.promptEmail(scanner, "Enter guest email: ");
        String var2 = InputUtil.promptNonEmpty(scanner, "Enter guest phone: ");
        Guest var3 = new Guest(guestService.generateNextId(), var0, var1, var2);
        guestService.addGuest(var3);
        System.out.println("Guest added successfully.");
    }

    private static void viewGuests() {
        List<Guest> var0 = guestService.getAllGuests();
        if (var0.isEmpty()) {
            System.out.println("No guests found.");
        } else {
            for(Guest var2 : var0) {
                System.out.println(var2);
            }

        }
    }

    private static void updateGuest() {
        int var0 = InputUtil.promptInt(scanner, "Enter guest ID to update: ");
        if (guestService.findById(var0) == null) {
            System.out.println("Guest not found.");
        } else {
            String var1 = InputUtil.promptNonEmpty(scanner, "Enter new name: ");

            while(true) {
                String var2 = InputUtil.promptNonEmpty(scanner, "Enter new email: ");
                if (ValidationUtil.isValidEmail(var2)) {
                    String var3 = InputUtil.promptNonEmpty(scanner, "Enter new phone: ");
                    guestService.updateGuest(var0, var1, var2, var3);
                    System.out.println("Guest updated successfully.");
                    return;
                }

                System.out.println("Invalid email format.");
            }
        }
    }

    private static void deleteGuest() {
        int var0 = InputUtil.promptInt(scanner, "Enter guest ID to delete: ");
        if (guestService.deleteGuest(var0)) {
            System.out.println("Guest deleted successfully.");
        } else {
            System.out.println("Guest not found.");
        }

    }

    private static void addRoom() {
        String var0 = InputUtil.promptNonEmpty(scanner, "Enter room number: ");
        String var1 = InputUtil.promptNonEmpty(scanner, "Enter room type: ");
        double var2 = InputUtil.promptDouble(scanner, "Enter room price per night: ");
        boolean var4 = InputUtil.promptBoolean(scanner, "Is the room available?");
        Room var5 = new Room(roomService.generateNextId(), var0, var1, var2, var4);
        roomService.addRoom(var5);
        System.out.println("Room added successfully.");
    }

    private static void viewRooms() {
        List<Room> var0 = roomService.getAllRooms();
        if (var0.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            for(Room var2 : var0) {
                System.out.println(var2);
            }

        }
    }

    private static void updateRoom() {
        int var0 = InputUtil.promptInt(scanner, "Enter room ID to update: ");
        if (roomService.findById(var0) == null) {
            System.out.println("Room not found.");
        } else {
            String var1 = InputUtil.promptNonEmpty(scanner, "Enter new room number: ");
            String var2 = InputUtil.promptNonEmpty(scanner, "Enter new room type: ");
            double var3 = InputUtil.promptDouble(scanner, "Enter new room price: ");
            boolean var5 = InputUtil.promptBoolean(scanner, "Is the room available?");
            roomService.updateRoom(var0, var1, var2, var3, var5);
            System.out.println("Room updated successfully.");
        }
    }

    private static void deleteRoom() {
        int var0 = InputUtil.promptInt(scanner, "Enter room ID to delete: ");
        if (roomService.deleteRoom(var0)) {
            System.out.println("Room deleted successfully.");
        } else {
            System.out.println("Room not found.");
        }

    }

    private static void addReservation() {
        if (!guestService.getAllGuests().isEmpty() && !roomService.getAllRooms().isEmpty()) {
            viewGuests();
            int var0 = InputUtil.promptInt(scanner, "Enter guest ID: ");
            if (guestService.findById(var0) == null) {
                System.out.println("Guest not found.");
            } else {
                viewRooms();
                int var1 = InputUtil.promptInt(scanner, "Enter room ID: ");
                Room var2 = roomService.findById(var1);
                if (var2 == null) {
                    System.out.println("Room not found.");
                } else if (!var2.isAvailable()) {
                    System.out.println("Selected room is not available.");
                } else {
                    String var3 = InputUtil.promptDate(scanner, "Enter check-in date (YYYY-MM-DD): ");
                    String var4 = InputUtil.promptDate(scanner, "Enter check-out date (YYYY-MM-DD): ");
                    if (!ValidationUtil.isCheckOutAfterCheckIn(var3, var4)) {
                        System.out.println("Check-out date must be after check-in date.");
                    } else {
                        String var5 = InputUtil.promptNonEmpty(scanner, "Enter reservation status: ");
                        Reservation var6 = new Reservation(reservationService.generateNextId(), var0, var1, var3, var4, var5);
                        reservationService.addReservation(var6);
                        System.out.println("Reservation added successfully.");
                    }
                }
            }
        } else {
            System.out.println("You need at least one guest and one room before creating a reservation.");
        }
    }

    private static void viewReservations() {
        List<Reservation> var0 = reservationService.getAllReservations();
        if (var0.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for(Reservation var2 : var0) {
                System.out.println(var2);
            }

        }
    }

    private static void updateReservation() {
        int var0 = InputUtil.promptInt(scanner, "Enter reservation ID to update: ");
        if (reservationService.findById(var0) == null) {
            System.out.println("Reservation not found.");
        } else {
            viewGuests();
            int var1 = InputUtil.promptInt(scanner, "Enter new guest ID: ");
            if (guestService.findById(var1) == null) {
                System.out.println("Guest not found.");
            } else {
                viewRooms();
                int var2 = InputUtil.promptInt(scanner, "Enter new room ID: ");
                if (roomService.findById(var2) == null) {
                    System.out.println("Room not found.");
                } else {
                    String var3 = InputUtil.promptDate(scanner, "Enter new check-in date (YYYY-MM-DD): ");
                    String var4 = InputUtil.promptDate(scanner, "Enter new check-out date (YYYY-MM-DD): ");
                    if (!ValidationUtil.isCheckOutAfterCheckIn(var3, var4)) {
                        System.out.println("Check-out date must be after check-in date.");
                    } else {
                        String var5 = InputUtil.promptNonEmpty(scanner, "Enter new reservation status: ");
                        reservationService.updateReservation(var0, var1, var2, var3, var4, var5);
                        System.out.println("Reservation updated successfully.");
                    }
                }
            }
        }
    }

    private static void deleteReservation() {
        int var0 = InputUtil.promptInt(scanner, "Enter reservation ID to delete: ");
        if (reservationService.deleteReservation(var0)) {
            System.out.println("Reservation deleted successfully.");
        } else {
            System.out.println("Reservation not found.");
        }

    }

    private static void exportMenu() {
        System.out.println("\n----- EXPORT MENU -----");
        System.out.println("1. Export Guests to CSV");
        System.out.println("2. Export Rooms to CSV");
        System.out.println("3. Export Reservations to CSV");
        System.out.println("0. Back");

        int choice = InputUtil.promptInt(scanner, "Choose an option: ");

        switch (choice) {
            case 0:
                break;
            case 1:
                guestService.exportToCsv("exports/guests.csv");
                System.out.println("Guests exported successfully.");
                break;
            case 2:
                roomService.exportToCsv("exports/rooms.csv");
                System.out.println("Rooms exported successfully.");
                break;
            case 3:
                reservationService.exportToCsv("exports/reservations.csv");
                System.out.println("Reservations exported successfully.");
                break;
            default:
                System.out.println("Invalid menu option.");
        }
    }

    private static void importMenu() {
        System.out.println("\n----- IMPORT MENU -----");
        System.out.println("1. Import Guests from CSV");
        System.out.println("2. Import Rooms from CSV");
        System.out.println("3. Import Reservations from CSV");
        System.out.println("0. Back");

        int choice = InputUtil.promptInt(scanner, "Choose an option: ");

        switch (choice) {
            case 0:
                break;
            case 1:
                guestService.importFromCsv("exports/guests.csv");
                break;
            case 2:
                roomService.importFromCsv("exports/rooms.csv");
                break;
            case 3:
                reservationService.importFromCsv("exports/reservations.csv");
                break;
            default:
                System.out.println("Invalid menu option.");
        }
    }

    static {
        scanner = new Scanner(System.in);
        guestService = new GuestService();
        roomService = new RoomService();
        reservationService = new ReservationService(roomService);
    }
}
