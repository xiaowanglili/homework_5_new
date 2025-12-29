package com.example.homework_5_new.service;

import com.example.homework_5_new.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    private final Map<String, User> userMap = new ConcurrentHashMap<>();

    public boolean register(User user) {
        if (userMap.containsKey(user.getUsername())) return false;
        userMap.put(user.getUsername(), user);
        return true;
    }

    public User login(String username, String password) {
        User user = userMap.get(username);
        return (user != null && user.getPassword().equals(password)) ? user : null;
    }
}