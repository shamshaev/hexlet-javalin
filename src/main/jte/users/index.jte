@import org.example.hexlet.dto.users.UsersPage
@import org.example.hexlet.NamedRoutes
@param UsersPage page

@template.layout.page(
    page = page,
    content = @`
    <div style="text-align: center; height: 600px; overflow: auto;">
        <form action="${NamedRoutes.buildUserPath()}" method="get">
            <input type="submit" value="Add user" />
        </form>
        @for(var user : page.getUsers())
            <div>
                <p style="font-size: 20;">
                    ${user.getId()}   ${user.getName()}   ${user.getEmail()}
                </p>
            </div>
        @endfor
    </div>
    `
)
