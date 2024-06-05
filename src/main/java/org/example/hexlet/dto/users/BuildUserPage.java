package org.example.hexlet.dto.users;

import io.javalin.validation.ValidationError;
import org.example.hexlet.dto.BasePage;

import java.util.List;
import java.util.Map;

public class BuildUserPage extends BasePage {
    private String name;
    private String email;
    private Map<String, List<ValidationError<Object>>> errors;

    public BuildUserPage() {
    }

    public BuildUserPage(String name, String email, Map<String, List<ValidationError<Object>>> errors) {
        this.name = name;
        this.email = email;
        this.errors = errors;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, List<ValidationError<Object>>> getErrors() {
        return errors;
    }
}
