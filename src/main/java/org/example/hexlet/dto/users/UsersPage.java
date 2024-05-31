package org.example.hexlet.dto.users;

import org.example.hexlet.model.User;

import java.util.List;

public class UsersPage {
    private List<User> users;

    public UsersPage(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
