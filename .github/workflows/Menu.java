package user_login;

import javax.swing.JOptionPane;
import static user_login.Main_Page.loginUser;
import static user_login.Main_Page.registerUser;

public class Menu {
    /**
     * Displays the main menu and handles user input for login, registration, and exit actions.
     */
    public void sendMenu() {
        // Loop continues as long as checkValidInput() returns true//
        while (checkValidInput()) {
            // Define options for the dialog//
            String[] options = {"Login", "Register", "Exit"};
            // Show the option dialog and get the user's choice//
            int choice = JOptionPane.showOptionDialog(null, "What do you want to do?", "User Login", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0: // Login//
                    // Prompt the user for their username and password//
                    String logUsername = JOptionPane.showInputDialog("Enter your username:");
                    String logPassword = JOptionPane.showInputDialog("Enter your password:");
                    // Call the loginUser method with the provided credentials//
                    loginUser(logUsername, logPassword);
                    break;

                case 1: // Register//
                    // Prompt the user for their first name and last name//
                    String regFirstname = JOptionPane.showInputDialog("Enter your firstname:");
                    String regLastname = JOptionPane.showInputDialog("Enter your lastname:");
                    boolean validUsername = false;
                    boolean validPassword = false;

                    // Loop until a valid username is entered//
                    while (!validUsername) {
                        String regUsername = JOptionPane.showInputDialog("Enter your username:");
                        // Check if the username is valid//
                        if (checkUsername(regUsername)) {
                            validUsername = true;
                            JOptionPane.showMessageDialog(null, "Username successfully captured.");
                            // Loop until a valid password is entered//
                            while (!validPassword) {
                                String regPassword = JOptionPane.showInputDialog("Create a password:");
                                // Check if the password meets the complexity requirements//
                                if (checkPasswordComplexity(regPassword)) {
                                    JOptionPane.showMessageDialog(null, "Password successfully captured");
                                    // Register the user with the provided details//
                                    registerUser(regUsername, regFirstname, regLastname, regPassword);
                                    validPassword = true;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
                        }
                    }
                    break;

                case 2: // Exit//
                    // Inform the user that the program is exiting//
                    JOptionPane.showMessageDialog(null, "Exiting...");
                    // Set loginUser to false to break the loop//
                    loginUser = false;
                    break;

                default:
                    // Inform the user to enter a valid option if an invalid choice is made//
                    JOptionPane.showMessageDialog(null, "Please enter a valid option.");
                    break;
            }
        }
    }

    /**
     * Checks if the username is valid. A valid username must contain an underscore and be no more than 5 characters in length.
     * @param username The username to check
     * @return true if the username is valid, false otherwise
     */
    private static boolean checkUsername(String username) {
        return username != null && username.matches(".*[_].*") && username.length() <= 5;
    }

    /**
     * Checks if the password is complex enough. A valid password must contain at least 8 characters,
     * a capital letter, a number, and a special character.
     * @param password The password to check
     * @return true if the password is complex enough, false otherwise
     */
    private static boolean checkPasswordComplexity(String password) {
        return password != null && password.matches("^(?=.*[!@#$%^&*()])(?=.*[A-Z])(?=.*[0-9]).{8,}$") && password.length() >= 8;
    }

    /**
     * Checks if the input is valid, which is determined by the loginUser flag.
     * @return true if loginUser is true, false otherwise
     */
    private static boolean checkValidInput() {
        return loginUser;
    }
}