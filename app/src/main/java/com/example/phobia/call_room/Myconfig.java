package com.example.phobia.call_room;

/**
 * Created by Phobia on 2/28/2017.
 */

public class Myconfig {
    private String service_register ="http://callroom.bsruteam.tk/service/service_register.php?";
    private String service_login = "http://callroom.bsruteam.tk/service/service_login.php?";
    private String service_showlistroom = "http://callroom.bsruteam.tk/service/sevice_showlistroom.php?";
    private String service_userjoinroom = "http://callroom.bsruteam.tk/service/sevice_user_joinroom.php?";

    public String getService_showlistroom() {
        return service_showlistroom;
    }

    public String getService_register() {
        return service_register;
    }

    public String getService_login() {
        return service_login;
    }

    public String getService_userjoinroom() {
        return service_userjoinroom;
    }
}
