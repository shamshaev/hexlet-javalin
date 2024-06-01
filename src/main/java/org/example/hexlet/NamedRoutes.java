package org.example.hexlet;

public class NamedRoutes {
    public static String helloPath() {
        return "/hello";
    }

    public static String usersPostsPath(String id, String postId) {
        return "/users/" + id + "/posts/" + postId;
    }
    public static String usersPath() {
        return "/users";
    }

    public static String buildUserPath() {
        return "/users/build";
    }

    public static String coursesPath() {
        return "/courses";
    }

    // Это нужно, чтобы не преобразовывать типы снаружи
    public static String coursePath(Long id) {
        return coursePath(String.valueOf(id));
    }

    public static String coursePath(String id) {
        return "/courses/" + id;
    }

    public static String gitHubPath() {
        return "https://github.com/shamshaev";
    }
}
