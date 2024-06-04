@import org.example.hexlet.NamedRoutes

@template.layout.page(
content = @`
    <form action="${NamedRoutes.sessionsPath()}" method="post">
        <input type="text" placeholder="Nickname" required name="nickname" />
        <input type="password" placeholder="Password" required name="password" />
        <input type="submit" value="Submit"/>
    </form>
`
)

