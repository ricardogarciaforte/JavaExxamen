<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Access Denied</title>
</head>
<body>
	<h1>Warning! Access is denied...</h1>
	 
	<p>Sorry, but you don't have access in this area</p>
	
	<p>
		Go back to home page <a href="<c:url value="/home"/>">&larr;</a>
	</p>
</body>
</html>
