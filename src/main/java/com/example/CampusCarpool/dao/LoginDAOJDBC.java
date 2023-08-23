package com.example.CampusCarpool.dao;

import com.example.CampusCarpool.connection.ConnectionDB;
import com.example.CampusCarpool.dao.queries.RetrieveQueries;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.exception.NotFoundException;
import com.example.CampusCarpool.exception.UserNotFoundException;
import com.example.CampusCarpool.model.UserProfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOJDBC implements LoginDAO{
    @Override
    public UserProfile checkUser(String username, String password) throws UserNotFoundException {
        Connection connection;
        int role;
        UserProfile userProfile = null;

        try {

            connection = ConnectionDB.getConnection();
            ResultSet resultSet = RetrieveQueries.checkUser(connection, username, password);

            if (!resultSet.isBeforeFirst()){
                throw new UserNotFoundException();
            }

            resultSet.first();

            String nameRole = resultSet.getString(1);
            switch (nameRole){
                case "Driver"-> role = 1;
                case "Passenger" -> role = 2;
                default -> throw new NotFoundException("No role found");
            }

            resultSet.close();
            userProfile = new UserProfile(role, username);

        } catch (NotFoundException | SQLException e){
            Printer.printError(e.getMessage());
        }

        return userProfile;
    }
}
