//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hotel.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class ValidationUtil {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public ValidationUtil() {
    }

    public static boolean isValidEmail(String var0) {
        return var0 != null && EMAIL_PATTERN.matcher(var0.trim()).matches();
    }

    public static boolean isNonEmpty(String var0) {
        return var0 != null && !var0.trim().isEmpty();
    }

    public static boolean isPositiveDouble(String var0) {
        try {
            return Double.parseDouble(var0) >= (double)0.0F;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    public static boolean isInteger(String var0) {
        try {
            Integer.parseInt(var0);
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    public static boolean isValidDate(String var0) {
        try {
            LocalDate.parse(var0);
            return true;
        } catch (DateTimeParseException var2) {
            return false;
        }
    }

    public static boolean isCheckOutAfterCheckIn(String var0, String var1) {
        try {
            return LocalDate.parse(var1).isAfter(LocalDate.parse(var0));
        } catch (DateTimeParseException var3) {
            return false;
        }
    }
}
