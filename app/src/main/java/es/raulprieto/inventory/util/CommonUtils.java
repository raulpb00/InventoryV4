package es.raulprieto.inventory.util;

import java.util.regex.Pattern;

/**
 * This classes cannot be inherit, so it is indicated that they are final
 */
public final class CommonUtils {

    /**
     * This method checks if the password meets a defined pattern:
     */
    public static boolean isPasswordValid(String password) {
        final String PASSWORD_REQUISITES = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,12}$"; // If no spaces either, add: (?=\\S+$)

        Pattern pattern = Pattern.compile(PASSWORD_REQUISITES); // Stored Process

        return pattern.matcher(password).matches();
    }
}
