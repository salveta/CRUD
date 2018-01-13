package com.salvador.perez.crud.commons;

import com.salvador.perez.crud.model.User;

/**
 * Created by Salva on 13/01/2018.
 */

public class Event {

    public static final int UPDATE_USER_LIST = 1;
    public static final int REMOVE_USER_LIST = 2;

    public int type;
    public int id;

    public User user;

    public Event(int id, int type) {
        this.id = id;
        this.type = type;
    }

    public Event(User user, int type) {
        this.type = type;
        this.user = user;
    }

    public int getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

}