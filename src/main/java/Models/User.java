package Models;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    int id;
    String firstName;
    String lastName;
    String username;
    String password;
    String role;

    public User(int id, String firstName, String lastName, String username, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
                + ", password=" + password + ", role=" + role + "]";
    }

    public static String hashPassword(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12) + "salt");
        return hashed;
    }

    public static boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }

}
