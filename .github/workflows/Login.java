package user_login;

import javax.swing.JOptionPane;
import static user_login.Main_Page.userDatabase;

public class Login {
    /**
     * Attempts to log in a user with the provided username and password.
     *
     * @param name     The username entered by the user
     * @param password The password entered by the user
     */
    public void loginUser(String name, String password) {
        // Check if the input is valid (non-empty)//
        if (isValidInput(name, password)) {
            // Check if the username exists in the database and the password matches//
            if (userDatabase.containsKey(name) && userDatabase.get(name).getPassword().equals(password)) {
                // Retrieve the user data//
                UserData uData = userDatabase.get(name);
                // Display a welcome message with the user's first and last name//
                JOptionPane.showMessageDialog(null, "Welcome " + uData.getFirstName() + " " + uData.getLastName() + " it is great to see you again.");
                // Call the tasks method to perform further actions//
                tasks();
            } else {
                // Display an error message if the username or password is incorrect//
                JOptionPane.showMessageDialog(null, "Username or password incorrect, please try again.");
            }
        }
    }

    /**
     * Calls the Task_Manager to perform further tasks after successful login.
     */
    public static void tasks() {
        Task_Manager taskManager = new Task_Manager();
        taskManager.tasks();
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
