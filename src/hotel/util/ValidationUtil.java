package hotel.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class ValidationUtil {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public static boolean isNonEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isPositiveDouble(String value) {
        try {
            return Double.parseDouble(value) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isCheckOutAfterCheckIn(String checkInDate, String checkOutDate) {
        try {
            return LocalDate.parse(checkOutDate).isAfter(LocalDate.parse(checkInDate));
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}