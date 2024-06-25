package user_login;

import javax.swing.JOptionPane;
import static user_login.Main_Page.userDatabase;

public class Register {
    /**
     * Registers a new user with the provided username, firstName, lastName, and password.
     *
     * @param username  The username chosen by the user
     * @param firstname The user's first name
     * @param lastname  The user's last name
     * @param password  The password chosen by the user
     */
    public void registerUser(String username, String firstname, String lastname, String password) {
        // Check if the input is valid (non-empty)
        if (isValidInput(username, password)) {
            // Create a new UserData object with the provided details
            UserData uData = new UserData(firstname, lastname, password);
            // Add the user to the userDatabase with the username as the key
            userDatabase.put(username, uData);
            // Display a success message with the user's first and last name
            JOptionPane.showMessageDialog(null, "Registration successful for: " + uData.getFirstName() + " " + uData.getLastName());
        }
    }

    /**
     * Validates the input to ensure that neither the username nor the password is empty.
     *
     * @param name     The username to check
     * @param password The password to check
     * @return true if neither the username nor the password is empty, false otherwise
     */
    private static boolean isValidInput(String name, String password) {
        return !name.isEmpty() && !password.isEmpty();
    }
}