@import org.example.hexlet.NamedRoutes
@import org.example.hexlet.dto.users.BuildUserPage
@param BuildUserPage page

@template.layout.page(
page = page,
content = @`
    <div style="text-align: center; height: 600px; overflow: auto;">

        @if(page.getErrors() != null)
            <ul style="color: red;">
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        @endif

        <form action="${NamedRoutes.usersPath()}" method="post">
            <div style="margin-top: 30px;">
                <label>
                    Имя
                    <input type="text" name="name" value="${page.getName()}" />
                </label>
            </div>
            <div>
                <label>
                    Email
                    <input type="email" required name="email" value="${page.getEmail()}" />
                </label>
            </div>
            <div>
                <label>
                    Пароль
                    <input type="password" required name="password" />
                </label>
            </div>
            <div>
                <label>
                    Подтверждение пароля
                    <input type="password" required name="passwordConfirmation" />
                </label>
            </div>
            <input type="submit" value="Зарегистрировать" />
        </form>

    </div>
`
)
