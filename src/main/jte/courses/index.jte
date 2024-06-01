@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

@template.layout.page(
content = @`
    <div style="height: 600px; overflow: auto;">
        <form action="${NamedRoutes.coursesPath()}" method="get">
            <input type="search" name="term" value="${page.getTerm()}" />
            <input type="submit" value="Search" />
        </form>
        @for(var course : page.getCourses())
            <div style="font-size: 20; padding-top: 20px;">
                <span style="font-weight: bold">${course.getName()}</span>
                <span style="font-weight: normal">${course.getDescription()}</span>
            </div>
        @endfor
    </div>
`
)
