package com.example.guvi.service;

import com.example.guvi.dao.UserDAO;
import com.example.guvi.model.User;
import java.sql.SQLException;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) throws SQLException {
        return userDAO.registerUser(user);
    }

    public User loginUser(String username, String password) throws SQLException {
        return userDAO.loginUser(username, password);
    }
}
