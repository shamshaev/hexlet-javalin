package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.hello.HelloPage;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld {

    private static final List<Course> COURSES = List.of(
            new Course(1L, "php", "the easiest language to get in IT"),
            new Course(2L, "python", "the most popular language among rookies"),
            new Course(3L, "java", "this is my way to IT")
    );

    public static void main(String[] args) {

        UserRepository.save(new User("john", "john@google.com", "jobs2349"));
        UserRepository.save(new User("justin", "justin@yahoo.com", "miracle65"));
        UserRepository.save(new User("natasha", "natasha@apple.com", "great12_4"));

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        app.get(NamedRoutes.helloPath(), ctx -> {
            var name = ctx.queryParam("name") != null ? ctx.queryParam("name") : "World";
            var page = new HelloPage(name);
            ctx.render("hello/index.jte", model("page", page));
        });

        app.get(NamedRoutes.usersPostsPath("{id}", "{postId}"), ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            var postId = ctx.pathParamAsClass("postId", Integer.class).get();
            var page = new UserPage(id, postId);
            ctx.render("users/index2.jte", model("page", page));
        });

        app.get(NamedRoutes.coursesPath(), ctx -> {
            var term = ctx.queryParam("term");
            var courses = new ArrayList<>(COURSES);

            if (term != null) {
                courses.removeIf(course -> !course.getDescription().contains(term));
            }

            var page = new CoursesPage(courses, term);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get(NamedRoutes.coursePath("{id}"), ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var course = COURSES.stream()
                    .filter(c -> c.getId().equals(id))
                    .findAny()
                    .orElseThrow(() -> new NotFoundResponse("Course not found"));
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.get(NamedRoutes.usersPath(), ctx -> {
            var users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index1.jte", model("page", page));
        });

        app.get(NamedRoutes.buildUserPath(), ctx -> {
            var page = new BuildUserPage();
            ctx.render("users/build.jte", model("page", page));
        });

        app.post(NamedRoutes.usersPath(), ctx -> {

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
                ctx.redirect(NamedRoutes.usersPath());
            } catch (ValidationException e) {
                var page = new BuildUserPage(name, email, e.getErrors());
                System.out.println(e.getErrors());
                ctx.render("users/build.jte", model("page", page));
            }
        });
        
        app.start(7070);
    }
}
