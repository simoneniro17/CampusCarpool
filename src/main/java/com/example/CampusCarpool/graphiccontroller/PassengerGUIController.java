package com.example.CampusCarpool.graphiccontroller;

import com.example.CampusCarpool.engineering.ShowExceptionSupport;

import java.io.IOException;

public class PassengerGUIController {
    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    public void toProfile() throws IOException {
        ShowExceptionSupport.showException("Not implemented yet!");
    }
}
