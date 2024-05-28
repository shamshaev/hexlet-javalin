package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursePage;

import java.util.List;

public class HelloWorld {

    private static final List<Course> COURSES = List.of(
            new Course(1L, "php", "the easiest language to get in IT"),
            new Course(2L, "python", "the most popular language among rookies"),
            new Course(3L, "java", "this is my way to IT")
    );

    public static void main(String[] args) {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> ctx.result("This is Javalin app in action"));
        app.get("/users", ctx -> ctx.result("GET /users"));
        app.post("/users", ctx -> ctx.result("POST /users"));

        app.get("/hello", ctx -> {
            var name = ctx.queryParam("name") != null ? ctx.queryParam("name") : "World";
            ctx.result("Hello, " + name + "!");
        });

        app.get("/users/{id}/posts/{postId}", ctx -> {
            var id = ctx.pathParamAsClass("id", Integer.class).get();
            var postId = ctx.pathParamAsClass("postId", Integer.class).get();
            ctx.result("User ID: " + id + ", post ID: " + postId);
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var course = COURSES.stream()
                    .filter(c -> c.getId().equals(id))
                    .findAny()
                    .orElseThrow(() -> new NotFoundResponse("Course not found"));
            var page = new CoursePage(course);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.start(7070);
    }
}
