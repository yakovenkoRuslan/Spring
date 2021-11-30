<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customTags" prefix="ct" %>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h1 style="text-align: right">Hi, ${login}!</h1>
<h2 style="text-align: right"><a
        href="${pageContext.request.contextPath}/logout">logout</a></h2>
<h2><a href="${pageContext.request.contextPath}/home/add">Add user</a></h2>
<table class="table table-dark">
    <tr>
        <th scope="col">Login</th>
        <th scope="col">Email</th>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
        <th scope="col">Birthday</th>
        <th scope="col">Role</th>
        <th scope="col">Actions</th>
    </tr>
    <ct:tableTag users="${listOfUsers}">
        <tr>
            <td>${login}</td>
            <td>${firstName}</td>
            <td>${lastName}</td>
            <td>${age}</td>
            <td>${role}</td>
            <td><a href="${pageContext.request.contextPath}/home/edit/${id}"
                   style="align-content: center; text-align: center">Edit</a>
            </td>
            <td>
                <a onclick="return confirm('Sure, you want to delete this user?')"
                   href="${pageContext.request.contextPath}/home/delete/${id}">Delete</a>
            </td>
        </tr>
    </ct:tableTag>
</table>
</body>
</html>
