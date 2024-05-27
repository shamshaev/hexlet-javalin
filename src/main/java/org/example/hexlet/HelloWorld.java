package org.example.hexlet;

import io.javalin.Javalin;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        // Описываем, что загрузится по адресу /
        app.get("/", ctx -> ctx.result("This is Javalin app in action"));
        app.get("/users", ctx -> ctx.result("GET /users"));
        app.post("/users", ctx -> ctx.result("POST /users"));
        app.get("/users/{id}/posts/{postId}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            var postId = ctx.pathParamAsClass("postId", Integer.class).get();
            ctx.result("User ID: " + id + ", post ID: " + postId);
        });
        app.get("/hello", ctx -> {
            var name = ctx.queryParam("name") != null ? ctx.queryParam("name") : "World";
            ctx.result("Hello, " + name + "!");
        });
        app.start(7070); // Стартуем веб-сервер
    }
}
