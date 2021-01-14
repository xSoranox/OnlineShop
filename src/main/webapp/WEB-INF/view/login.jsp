<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
</head>
<body>
    <div style="text-align: center">
        <h1>Login as admin</h1>
        <form align="center" action="/onlineshop/login" method="post">
            <label for="username">Username:</label>
            <input name="username" size="30" />
            <br><br>
            <label for="password">Password:</label>
            <input type="password" name="password" size="30" />
            <br>${message}
            <br><br>  
            <button type="submit">Login</button>  &nbsp;  &nbsp;
            <button type="button"><a href="<c:url value="/products" />"> Home </a></button>
        </form>
    </div>
</body>
</html>