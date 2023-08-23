package com.example.CampusCarpool.appcontroller;

import com.example.CampusCarpool.engineering.Session;

public class LogoutController {

    public void logout() {
        Session.closeSession();
    }
}
