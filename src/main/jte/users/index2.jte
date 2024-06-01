@import org.example.hexlet.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
        <p style="font-size: 22px;">${"User ID: " + page.getId() + ", post ID: " + page.getPostid()}</p>
    `
)
