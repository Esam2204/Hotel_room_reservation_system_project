package hotel.util;

import java.util.Scanner;

public class InputUtil {

    public static String promptNonEmpty(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (ValidationUtil.isNonEmpty(input)) {
                return input.trim();
            }

            System.out.println("Input cannot be empty.");
        }
    }

    public static String promptEmail(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (ValidationUtil.isValidEmail(input)) {
                return input.trim();
            }

            System.out.println("Invalid email format. Example: user@example.com");
        }
    }

    public static int promptInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (ValidationUtil.isInteger(input)) {
                return Integer.parseInt(input);
            }

            System.out.println("Please enter a valid integer.");
        }
    }

    public static double promptDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (ValidationUtil.isPositiveDouble(input)) {
                return Double.parseDouble(input);
            }

            System.out.println("Please enter a valid non-negative number.");
        }
    }

    public static String promptDate(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (ValidationUtil.isValidDate(input)) {
                return input.trim();
            }

            System.out.println("Invalid date format. Use YYYY-MM-DD.");
        }
    }

    public static boolean promptBoolean(Scanner scanner, String message) {
        while (true) {
            System.out.print(message + " (yes/no): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("yes") || input.equals("y")) {
                return true;
            }

            if (input.equals("no") || input.equals("n")) {
                return false;
            }

            System.out.println("Please type yes or no.");
        }
    }
}