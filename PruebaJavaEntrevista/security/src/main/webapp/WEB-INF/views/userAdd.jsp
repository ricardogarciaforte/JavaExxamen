<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Add a new user</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
</head>
<body>
<fieldset>
    <legend>Create a new user</legend>
    <nav role="navigation">
        <ul>
            <li><a href="<c:url value="/home"/> ">Home</a></li>
        </ul>
    </nav>

    <c:url var="save" value="/user/save"/>
    <form:form modelAttribute="user" action="${save}" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <table>
            <tr>
                <td>Full Name:</td>
                <td><form:input path="fullName" type="name" required="true" autofocus="true"/></td>
            </tr>
            <tr>
                <td>Email Address:</td>
                <td><form:input path="email" type="email" required="true" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:input path="password" type="password" required="true"/></td>
            </tr>
            <tr>
                <td>Repeat:</td>
                <td><form:input path="passwordRepeated" type="password" required="true"/></td>
            </tr>
            <tr>
                <td>Perfil</td>
                <td>
                    <form:select path="perfil" required="true">
                        <form:option value="ADMIN" label="ADMIN" />
                        <form:option value="USER" label="USER" />
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><button type="submit">Save</button></td>
            </tr>
        </table>
    </form:form>

    <c:if test="${not empty error}">
        <p><font color="red">${error}</font>	</p>
    </c:if>
</fieldset>
</body>
</html>