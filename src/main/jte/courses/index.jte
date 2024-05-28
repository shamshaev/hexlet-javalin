@import org.example.hexlet.dto.courses.CoursePage
@param CoursePage page

<main>
    <h1>${page.getCourse().getName()}</h1>
    <p>${page.getCourse().getDescription()}</p>
</main>
