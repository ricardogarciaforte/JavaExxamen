<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Home page</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
</head>
<body>
<fieldset>
    <legend>Home Page</legend>
    <nav>
        <ul>
            <c:if test="${not empty logout}" >
                <p><font color="red">${logout}</font></p>
            </c:if>

            <c:if test="${empty currentUser}" >
                <li><a href="<c:url value="/auth/form"/> ">Log in</a></li>
            </c:if>

            <security:authorize access="hasAnyAuthority('ADMIN', 'USER')">
                <li>
                    <form action="<c:url value="/logout"/>" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit">Log out</button>
                    </form>
                </li>
                <li><a href="<c:url value="/user/${currentUser.id}"/> ">View myself</a></li>
            </security:authorize>

            <security:authorize access="hasAuthority('ADMIN')">
                <li><a href="<c:url value="/user/add"/> ">Add a new user</a></li>
                <li><a href="<c:url value="/user/list"/> ">View all users</a></li>
            </security:authorize>
        </ul>
    </nav>
</fieldset>
</body>
</html>
