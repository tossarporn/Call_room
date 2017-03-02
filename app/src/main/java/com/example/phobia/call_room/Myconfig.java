package com.example.phobia.call_room;

/**
 * Created by Phobia on 2/28/2017.
 */

public class Myconfig {
    private String service_register ="http://callroom.bsruteam.tk/service/service_register.php?";
    private String service_login = "http://callroom.bsruteam.tk/service/service_login.php?";
    private String service_showlistroom = "http://callroom.bsruteam.tk/service/sevice_showlistroom.php?";
    private String service_userjoinroom = "http://callroom.bsruteam.tk/service/sevice_user_joinroom.php?";
    private String service_show_add_user = "http://callroom.bsruteam.tk/service/service_show_add_user.php?";
    private String service_add_user = "http://callroom.bsruteam.tk/service/service_add_user.php?";
    private String service_create_room = "http://callroom.bsruteam.tk/service/service_create_room.php?";

    public String getService_create_room() {
        return service_create_room;
    }

    public String getService_add_user() {
        return service_add_user;
    }

    public String getService_show_add_user() {
        return service_show_add_user;
    }

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
