package com.tkachuk.websocketproject.model;

import java.util.HashSet;
import java.util.Set;

public class UserStorage {

    private static UserStorage instance;
    private static Set<String> users;

    private UserStorage() {
        users = new HashSet<>();
    }

    public static synchronized UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }

    public Set<String> getUsers() {
        return users;
    }

    public static void setUser(String userName) throws Exception {
        users.add(userName);
    }
}
