package hotel.service;

import hotel.model.UserAccount;
import hotel.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class AuthService {

    private static final String USERS_FILE = "data/users.txt";

    public AuthService() {
        initializeDefaultUsers();
    }

    private void initializeDefaultUsers() {
        List<String> lines = FileUtil.readLines(USERS_FILE);

        if (lines.isEmpty()) {
            List<String> defaultUsers = new ArrayList<>();
            defaultUsers.add("admin,admin123,ADMIN");
            defaultUsers.add("user,user123,USER");
            FileUtil.writeLines(USERS_FILE, defaultUsers);
        }
    }

    public UserAccount login(String username, String password) {
        List<String> lines = FileUtil.readLines(USERS_FILE);

        for (String line : lines) {
            String[] parts = line.split(",");

            if (parts.length < 3) {
                continue;
            }

            String fileUsername = parts[0].trim();
            String filePassword = parts[1].trim();
            String fileRole = parts[2].trim();

            if (fileUsername.equals(username) && filePassword.equals(password)) {
                return new UserAccount(fileUsername, filePassword, fileRole);
            }
        }

        return null;
    }
}