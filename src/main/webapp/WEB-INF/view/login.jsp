<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
</head>
<body>
    <div style="text-align: center">
        <h1>Login as admin</h1>
        <form action="login" method="post">
            <label for="username">Username:</label>
            <input name="username" size="30" />
            <br><br>
            <label for="password">Password:</label>
            <input type="password" name="password" size="30" />
            <br><br>${message}
            <br><br>           
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>