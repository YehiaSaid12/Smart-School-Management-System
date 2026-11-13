package netbeans_school;

import java.util.ArrayList;
// class made by yehia, changes not listed
public class AuthenticationService {
    private final ArrayList<UserAccount> accounts = new ArrayList<UserAccount>();
    private UserAccount currentAccount;

    public void addAccount(UserAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Account is required.");
        }
        if (findAccount(account.getUsername()) != null) {
            throw new IllegalArgumentException("Duplicate username.");
        }
        accounts.add(account);
    }

    public UserAccount findAccount(String username) {
        if (username == null) {
            return null;
        }

        for (UserAccount account : accounts) {
            if (account.getUsername().equalsIgnoreCase(username)) {
                return account;
            }
        }

        return null;
    }

    public boolean login(String username, String password) {
        UserAccount account = findAccount(username);

        if (account == null
                || !account.isActive()
                || !account.checkPassword(password)) {
            return false;
        }

        currentAccount = account;
        return true;
    }

    public void logout() {
        currentAccount = null;
    }

    public boolean isLoggedIn() {
        return currentAccount != null;
    }

    public UserAccount getCurrentAccount() {
        return currentAccount;
    }

    public Person getCurrentUser() {
        if (currentAccount == null) {
            return null;
        }
        return currentAccount.getOwner();
    }

    public boolean isAdmin() {
        return hasRole("ADMIN");
    }

    public boolean isTeacher() {
        return hasRole("TEACHER");
    }

    public boolean isStudent() {
        return hasRole("STUDENT");
    }

    private boolean hasRole(String role) {
        Person user = getCurrentUser();
        return user != null && user.getRole().equals(role);
    }
}
