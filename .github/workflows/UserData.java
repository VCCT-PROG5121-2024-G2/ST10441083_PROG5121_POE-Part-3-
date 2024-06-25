package user_login;

/**
 * The UserData class stores the details of a user including their firstname, lastname, and password.
 */
public class UserData {
    // Instance variables to store user's firstname, lastname, and password
    String firstname;
    String lastname;
    String password;

    /**
     * Constructor to initialize the UserData object with provided details.
     *
     * @param firstname The user's first name
     * @param lastname  The user's last name
     * @param password  The user's password
     */
    public UserData(String firstname, String lastname, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    /**
     * Getter method to retrieve the user's first name.
     *
     * @return The user's first name
     */
    public String getFirstName() {
        return firstname;
    }

    /**
     * Getter method to retrieve the user's last name.
     *
     * @return The user's last name
     */
    public String getLastName() {
        return lastname;
    }

    /**
     * Getter method to retrieve the user's password.
     *
     * @return The user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Overrides the default toString method to provide a string representation of the UserData object.
     *
     * @return A string representation of the UserData object
     */
    @Override
    public String toString() {
        return "Firstname: " + firstname + ", Lastname: " + lastname + ", Password: " + password;
    }
}