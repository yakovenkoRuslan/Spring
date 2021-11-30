<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoginPage</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <style>
        .cc {
            background: red;
            color: black;
            padding: 10px;
            border-radius: 5px;
            text-align: center;
            align-content: center;
        }
        </style>
</head>
<body>

<div class="container">
    <div class="row justify-content-center align-items-center" style="height:100vh">
        <div class="col-4">
            <div class="card">
                <div class="card-body">
                    <form method="POST" action="${pageContext.request.contextPath}/login" name = 'f'>
                        <fieldset>
                            <c:if test="${param.error}">
                                <div class="cc">Wrong login or password</div>
                            </c:if>
                            <H1 style="text-align: center">Login</H1>
                            <div class="form-group">
                                <input type="text" class="form-control" required placeholder="login" name="username">
                            </div>
                            <div class="form-group">
                                <input type="password" class="form-control" name="password" required placeholder="password">
                            </div>
                            <div style="text-align: center">
                                <button style="text-align: center" type="submit" class="btn btn-primary">Log in</button>
                            </div>
                            <br>
                            <p style="text-align: center"><a href="${pageContext.request.contextPath}/registration">Create account</a></p>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
