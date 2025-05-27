package atm;

import java.util.HashMap;

public class UserStorage {
    private static final HashMap<String, User> userMap = new HashMap<>();

    public static void addUser(User user) {
        userMap.put(user.getUsername(), user);
    }

    public static User getUser(String username) {
        return userMap.get(username);
    }
}
