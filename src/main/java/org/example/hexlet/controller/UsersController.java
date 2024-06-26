package org.example.hexlet.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.validation.ValidationException;
import org.example.hexlet.NamedRoutes;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.sql.SQLException;

public class UsersController {
    public static void index(Context ctx) throws SQLException {
        var users = UserRepository.getEntities();
        var page = new UsersPage(users);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("users/index.jte", model("page", page));
    }

//    public static void show(Context ctx) {
//        var id = ctx.pathParamAsClass("id", Long.class).get();
//        var user = UserRepository.find(id)
//                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
//        var page = new UserPage(user);
//        ctx.render("users/show.jte", model("page", page));
//    }

    public static void build(Context ctx) {
        var page = new BuildUserPage();
        ctx.render("users/build.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        var email = ctx.formParam("email");
        var name = ctx.formParam("name");

        try {
            var passwordConfirmation = ctx.formParam("passwordConfirmation");

            name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() > 1, "Слишком короткое имя")
                    .check(value -> !value.equals("l"), "Эта буква в имени не допускается")
                    .get();

            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                    .get();
            var user = new User(name, email, password);
            UserRepository.save(user);
            ctx.sessionAttribute("flash", "Добавление пользователя успешно!");
            ctx.redirect(NamedRoutes.usersPath());
        } catch (ValidationException e) {
            var page = new BuildUserPage(name, email, e.getErrors());
            ctx.sessionAttribute("flash", "Ошибка добавления пользователя!");
            page.setFlash(ctx.consumeSessionAttribute("flash"));
            ctx.render("users/build.jte", model("page", page));
        }
    }

//    public static void edit(Context ctx) {
//        var id = ctx.pathParamAsClass("id", Long.class).get();
//        var user = UserRepository.find(id)
//                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
//        var page = new UserPage(user);
//        ctx.render("users/edit.jte", model("page", page));
//    }
//
//
//    public static void update(Context ctx) {
//        var id = ctx.pathParamAsClass("id", Long.class).get();
//
//        var name = ctx.formParam("name");
//        var email = ctx.formParam("email");
//        var password = ctx.formParam("password");
//
//        var user = UserRepository.find(id)
//                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
//        user.setName(name);
//        user.setEmail(email);
//        user.setPassword(password);
//        UserRepository.save(user);
//        ctx.redirect(NamedRoutes.usersPath());
//    }
//
//    public static void destroy(Context ctx) {
//        var id = ctx.pathParamAsClass("id", Long.class).get();
//        UserRepository.delete(id);
//        ctx.redirect(NamedRoutes.usersPath());
//    }
}
