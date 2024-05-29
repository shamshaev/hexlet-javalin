@import org.example.hexlet.dto.users.IdPage
@param IdPage page

@template.layout.page(
content = @`
    <h1>${page.getId()}</h1>
`
)
