package org.example.hexlet.dto.courses;
import org.example.hexlet.model.Course;

public class CoursePage {
    private Course course;

    public CoursePage(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }
}
