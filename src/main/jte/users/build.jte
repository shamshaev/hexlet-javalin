@template.layout.page(
content = @`
    <div style="text-align: center; height: 600px; overflow: auto;">
        <form action="/users" method="post">
            <div>
                <label>
                    Имя
                    <input type="text" name="name" />
                </label>
            </div>
            <div>
                <label>
                    Email
                    <input type="email" required name="email" />
                </label>
            </div>
            <div>
                <label>
                    Пароль
                    <input type="password" required name="password" />
                </label>
            </div>
            <input type="submit" value="Зарегистрировать" />
        </form>

    </div>
`
)
