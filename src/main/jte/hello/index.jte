@import org.example.hexlet.dto.hello.HelloPage
@param HelloPage page

@template.layout.page(
    content = @`
        <p style="font-size: 24px;">${"Hello, " + page.getName() + "!"}</p>
    `
)
