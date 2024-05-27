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
        app.get("/users", ctx -> ctx.result("GET /users\n"));
        app.post("/users", ctx -> ctx.result("POST /users\n"));
        app.get("/hello", ctx -> {
            var name = ctx.queryParam("name") != null ? ctx.queryParam("name") : "World";
            ctx.result("Hello, " + name + "!");
        });
        app.start(7070); // Стартуем веб-сервер
    }
}
