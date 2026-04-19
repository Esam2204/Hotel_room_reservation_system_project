package hotel.util;

import java.util.Scanner;

public class InputUtil {
    public InputUtil() {
    }

    public static String promptNonEmpty(Scanner var0, String var1) {
        while(true) {
            System.out.print(var1);
            String var2 = var0.nextLine();
            if (ValidationUtil.isNonEmpty(var2)) {
                return var2.trim();
            }

            System.out.println("Input cannot be empty.");
        }
    }

    public static String promptEmail(Scanner var0, String var1) {
        while(true) {
            System.out.print(var1);
            String var2 = var0.nextLine();
            if (ValidationUtil.isValidEmail(var2)) {
                return var2.trim();
            }

            System.out.println("Invalid email format. Example: user@example.com");
        }
    }

    public static int promptInt(Scanner var0, String var1) {
        while(true) {
            System.out.print(var1);
            String var2 = var0.nextLine();
            if (ValidationUtil.isInteger(var2)) {
                return Integer.parseInt(var2);
            }

            System.out.println("Please enter a valid integer.");
        }
    }

    public static double promptDouble(Scanner var0, String var1) {
        while(true) {
            System.out.print(var1);
            String var2 = var0.nextLine();
            if (ValidationUtil.isPositiveDouble(var2)) {
                return Double.parseDouble(var2);
            }

            System.out.println("Please enter a valid non-negative number.");
        }
    }

    public static String promptDate(Scanner var0, String var1) {
        while(true) {
            System.out.print(var1);
            String var2 = var0.nextLine();
            if (ValidationUtil.isValidDate(var2)) {
                return var2.trim();
            }

            System.out.println("Invalid date format. Use YYYY-MM-DD.");
        }
    }

    public static boolean promptBoolean(Scanner var0, String var1) {
        while(true) {
            System.out.print(var1 + " (yes/no): ");
            String var2 = var0.nextLine().trim().toLowerCase();
            if (!var2.equals("yes") && !var2.equals("y")) {
                if (!var2.equals("no") && !var2.equals("n")) {
                    System.out.println("Please type yes or no.");
                    continue;
                }

                return false;
            }

            return true;
        }
    }
}
