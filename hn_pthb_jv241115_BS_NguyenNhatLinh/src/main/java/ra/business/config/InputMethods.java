package ra.business.config;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * <p><b>Description Details</b></p>
 * <ul>
 * <li><b>getString()</b> - Returns a string value from the user.</li>
 * <li><b>getChar()</b> - Returns a character value from the user.</li>
 * <li><b>getBoolean()</b> - Returns a boolean value from the user.</li>
 * <li><b>getByte()</b> - Returns a byte value from the user.</li>
 * <li><b>getShort()</b> - Returns a short value from the user.</li>
 * <li><b>getInteger()</b> - Returns an integer value from the user.</li>
 * <li><b>getLong()</b> - Returns a long value from the user.</li>
 * <li><b>getFloat()</b> - Returns a float value from the user.</li>
 * <li><b>getDouble()</b> - Returns a double value from the user.</li>
 * </ul>
 */
public final class InputMethods {

    private static final String ERROR_ALERT = "===>> Input error, please try again.";
    private static final String EMPTY_ALERT = "===>> Input cannot be empty, please try again.";

    /*============================== Input Methods Start ==============================*/

    /**
     * Returns a string value from the user.
     */
    public static String getString() {
        while (true) {
            String result = getInput();
            if (result.isEmpty()) {
                System.err.println(EMPTY_ALERT);
                continue;
            }
            return result;
        }
    }

    /**
     * Returns a character value from the user.
     */
    public static char getChar() {
        return getString().charAt(0);
    }

    /**
     * Returns a boolean value from the user.
     */
    public static boolean getBoolean() {
        String result = getString();
        return Boolean.parseBoolean(result);
    }

    /**
     * Returns a byte value from the user.
     */
    public static byte getByte() {
        while (true) {
            try {
                return Byte.parseByte(getString());
            } catch (NumberFormatException e) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * Returns a BigDecimal value from the user.
     */
    public static BigDecimal getBigDecimal() {
        while (true) {
            try {
                return new BigDecimal(getInput());
            } catch (NumberFormatException e) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * Returns a short value from the user.
     */
    public static short getShort() {
        while (true) {
            try {
                return Short.parseShort(getString());
            } catch (NumberFormatException e) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * Returns an integer value from the user.
     */
    public static int getInteger() {
        while (true) {
            try {
                return Integer.parseInt(getString());
            } catch (NumberFormatException e) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * Returns a long value from the user.
     */
    public static long getLong() {
        while (true) {
            try {
                return Long.parseLong(getString());
            } catch (NumberFormatException e) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * Returns a float value from the user.
     */
    public static float getFloat() {
        while (true) {
            try {
                return Float.parseFloat(getString());
            } catch (NumberFormatException e) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * Returns a double value from the user.
     */
    public static double getDouble() {
        while (true) {
            try {
                return Double.parseDouble(getString());
            } catch (NumberFormatException e) {
                System.err.println(ERROR_ALERT);
            }
        }
    }

    /**
     * Returns a date value from the user in "yyyy-MM-dd" format.
     */
    public static Date getDate() {
        while (true) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                String input = getInput();
                return sdf.parse(input);
            } catch (ParseException e) {
                System.err.println("Invalid date format, please use 'yyyy-MM-dd'.");
            }
        }
    }

    /*============================== Input Methods End ==============================*/

    /**
     * Returns any string value from the user.
     */
    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prompts the user to press any key to continue.
     */
    public static void pressAnyKey() {
        System.out.println("Press any key to continue...");
        getInput();
    }
}

