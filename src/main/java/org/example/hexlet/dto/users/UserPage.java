package org.example.hexlet.dto.users;

public class UserPage {
    private Integer id;
    private Integer postid;

    public UserPage(Integer id, Integer postid) {
        this.id = id;
        this.postid = postid;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPostid() {
        return postid;
    }
}
