<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Log in</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
</head>
<body>
<fieldset>
    <legend>Log in</legend>
    <nav role="navigation">
        <ul>
            <li><a href="<c:url value="/home"/> ">Home</a></li>
        </ul>
    </nav>

    <p><cite>You can use: root@email.com / root</cite></p>

    <c:if test="${not empty error}">
        <p><font color="red">${error}</font></p>
    </c:if>

    <form name="f" action="<c:url value="/auth/login"/>" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <table>
          <tr>
            <td>User:</td>
            <td>
                <input type='email' name='j_username' required autofocus>
            </td>
          </tr>
          <tr>
            <td>Password:</td>
            <td>
                <input type='password' name='j_password' required>
            </td>
          </tr>
          <tr>
            <td colspan='2'>
                <input name="submit" type="submit">
            </td>
          </tr>
        </table>
    </form>
</fieldset>
</body>
</html>