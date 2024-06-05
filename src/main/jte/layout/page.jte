@import org.example.hexlet.NamedRoutes
@import gg.jte.Content
@import org.example.hexlet.dto.BasePage
@param Content content
@param BasePage page = null 

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Training</title>
</head>
<body>
    <h1 style="text-align: center; font-size: 24px; font-weight: bold;">
        <span style="padding: 20px;"><a href="/">Main page</a></span>
        <span style="padding: 20px;"><a href="${NamedRoutes.usersPath()}">Users</a></span>
        <span style="padding: 20px;"><a href="${NamedRoutes.coursesPath()}">Courses</a></span>
    </h1>
    @if(page != null && page.getFlash() != null)
        <p style="text-align: center; font-size: 20px;">${page.getFlash()}</p>
    @endif
    <div style="text-align: center; margin-top: 300px;">${content}</div>
    <h3 style="text-align: center; position: absolute; bottom: 0; width: 100%;">
        <a href="${NamedRoutes.gitHubPath()}">My GitHub profile</a>
    </h3>
</body>
</html>
