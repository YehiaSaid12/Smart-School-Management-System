package netbeans_school;
// class made by yehia, changes not listed
public class UserAccount {
    private final String username;
    private String password;
    private final Person owner;
    private boolean active = true;

    public UserAccount(String username, String password, Person owner) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password is required.");
        }
        if (owner == null) {
            throw new IllegalArgumentException("Account owner is required.");
        }

        this.username = username.trim();
        this.password = password;
        this.owner = owner;
    }

    public String getUsername() {
        return username;
    }

    public Person getOwner() {
        return owner;
    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public boolean checkPassword(String input) {
        return password.equals(input);
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (!checkPassword(oldPassword)) {
            throw new IllegalArgumentException("Old password is incorrect.");
        }
        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("New password cannot be blank.");
        }
        password = newPassword;
    }
}
