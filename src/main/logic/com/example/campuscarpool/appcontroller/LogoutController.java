package com.example.campuscarpool.appcontroller;

import com.example.campuscarpool.engineering.Session;

public class LogoutController {

    public void logout() {
        Session.closeSession();
    }
}
