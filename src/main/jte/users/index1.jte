@import org.example.hexlet.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
    <div style="text-align: center; height: 600px; overflow: auto;">
        <form action="/users/build" method="get">
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
