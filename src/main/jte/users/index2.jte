@import org.example.hexlet.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
        <p>${"User ID: " + page.getId() + ", post ID: " + page.getPostid()}</p>
    `
)
