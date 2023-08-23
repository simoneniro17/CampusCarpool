package com.example.CampusCarpool.dao;

import com.example.CampusCarpool.exception.UserNotFoundException;
import com.example.CampusCarpool.model.UserProfile;

public interface LoginDAO {
    UserProfile checkUser(String username, String password) throws UserNotFoundException;
}
