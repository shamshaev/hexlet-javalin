package org.example.hexlet.dto;

public class MainPage {
    private Boolean visited;
    private String currentUser;

    public MainPage(Boolean visited, String currentUser) {
        this.visited = visited;
        this.currentUser = currentUser;
    }

    public Boolean getVisited() {
        return visited;
    }

    public Boolean isVisited() {
        return visited;
    }

    public String getCurrentUser() {
        return currentUser;
    }
}
