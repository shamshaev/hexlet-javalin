@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.MainPage
@param MainPage page

@template.layout.page(
    content = @`
        <div style="text-align: right; margin-right: 100px;">
            @if(page.getCurrentUser() == null)
                <span><a href="${NamedRoutes.sessionsPath()}">log in</a></span>
            @else
                <form action="${NamedRoutes.sessionsDeletePath()}" method="post">
                    <span>${page.getCurrentUser()}</span>
                    <input type="submit" value="Log out" />
                </form>
            @endif
        </div>
        <h1>${"This is Javalin app in action"}</h1>
        @if(!page.isVisited())
            Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки
        @endif
    `
)
