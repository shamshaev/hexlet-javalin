package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.dto.MainPage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.hello.HelloPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.BaseRepository;
import org.example.hexlet.repository.UserRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HelloWorld {

    private static final List<Course> COURSES = List.of(
            new Course(1L, "php", "the easiest language to get in IT"),
            new Course(2L, "python", "the most popular language among rookies"),
            new Course(3L, "java", "this is my way to IT")
    );

    public static Javalin getApp() throws Exception {

        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(System.getenv(JDBC_DATABASE_URL));

        var dataSource = new HikariDataSource(hikariConfig);

        // Получаем путь до файла в src/main/resources
        var url = HelloWorld.class.getClassLoader().getResourceAsStream("schema.sql");
        var sql = new BufferedReader(new InputStreamReader(url))
                .lines().collect(Collectors.joining("\n"));

        // Получаем соединение, создаем стейтмент и выполняем запрос
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> {
            var visited = Boolean.valueOf(ctx.cookie("visited"));
            var currentUser = (String) ctx.sessionAttribute("currentUser");
            var page = new MainPage(visited, currentUser);
            ctx.render("index.jte", model("page", page));
            ctx.cookie("visited", String.valueOf(true));
        });

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

        app.get(NamedRoutes.usersPath(), UsersController::index);

        app.get(NamedRoutes.buildUserPath(), UsersController::build);

        app.post(NamedRoutes.usersPath(), UsersController::create);

        // Отображение формы логина
        app.get(NamedRoutes.sessionsPath(), SessionsController::build);
        // Процесс логина
        app.post(NamedRoutes.sessionsPath(), SessionsController::create);
        // Процесс выхода из аккаунта
        app.post(NamedRoutes.sessionsDeletePath(), SessionsController::destroy);

        return app;
    }

    public static void main(String[] args) throws Exception {

        Javalin app = getApp();
        app.start(7070);

        UserRepository.save(new User("john", "john@google.com", "jobs2349"));
        UserRepository.save(new User("justin", "justin@yahoo.com", "miracle65"));
        UserRepository.save(new User("natasha", "natasha@apple.com", "great12_4"));
    }
}
