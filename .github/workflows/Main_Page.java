package user_login;

import java.util.HashMap;
import java.util.Map;

public class Main_Page {

    // A static map to store user data with the username as the key
    static Map<String, UserData> userDatabase = new HashMap<>();
    // A static boolean flag to control the menu loop
    static boolean loginUser = true;

    /**
     * The main method serves as the entry point for the application.
     *
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Create an instance of the Menu class
        Menu menu = new Menu();
        // Call the sendMenu method to display the menu and handle user input
        menu.sendMenu();
    }

    /**
     * Calls the loginUser method in the Login class to attempt user login.
     *
     * @param name     The username entered by the user
     * @param password The password entered by the user
     */
    public static void loginUser(String name, String password) {
        // Create an instance of the Login class
        Login log = new Login();
        // Call the loginUser method with the provided credentials
        log.loginUser(name, password);
    }

    /**
     * Calls the registerUser method in the Register class to register a new user.
     *
     * @param username  The username chosen by the user
     * @param firstname The user's first name
     * @param lastname  The user's last name
     * @param password  The password chosen by the user
     */
    public static void registerUser(String username, String firstname, String lastname, String password) {
        // Create an instance of the Register class
        Register reg = new Register();
        // Call the registerUser method with the provided details
        reg.registerUser(username, firstname, lastname, password);
    }
}