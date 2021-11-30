<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit user</title>
    <script src="static/js/main.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
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
<h1><b>Edit user</b></h1><br><br>

<div class="form-group">
    <form id="data" method="post" action="${pageContext.request.contextPath}/home/edit/${id}">
        <table class="table table-dark">
            <tr><td>
                <label>Login: </label>
            </td>
                <td>
                    <input type="text" placeholder="${login}" name="login" disabled><br><br>
                </td>
            </tr>

            <tr><td>
                <lable>Password</lable>
            </td>
                <td>
                    <input type="password" name="password"><br>
                    <c:if test="${errors.get(1) != null}" >
                        <div class="cc" >
                                ${errors.get(1)}
                        </div>
                    </c:if><br>
                </td></tr>

            <tr>
                <td>
                    <lable>Confirm password</lable>
                </td>
                <td>
                    <input type="password" name="confirmPassword"><br>
                    <c:if test="${errors.get(2) != null}" >
                        <div class="cc" >
                                ${errors.get(2)}
                        </div>
                    </c:if><br>
                </td>
            </tr>

            <tr>
                <td>
                    <lable>Email:</lable>
                </td><td>
                <input type="email" name="email" ><br>
                <c:if test="${errors.get(3) != null}" >
                    <div class="cc" >
                            ${errors.get(3)}
                    </div>
                </c:if><br>
            </td>
            </tr>

            <tr>
                <td>
                    <lable>First name:</lable>
                </td>
                <td>
                    <input type="name" name="firstName"><br>
                    <c:if test="${errors.get(4) != null}" >
                        <div class="cc" >
                            i${errors.get(4)}
                        </div>
                    </c:if><br>
                </td>
            </tr>
            <tr>
                <td>
                    <lable>Last Name:</lable>
                </td>
                <td>
                    <input type="name" name="lastName"><br>
                    <c:if test="${errors.get(5) != null}" >
                        <div class="cc" >
                                ${errors.get(5)}
                        </div>
                    </c:if><br>
                </td>
            </tr>

            <tr>
                <td>
                    <lable>Date:</lable>
                </td>
                <td>
                    <input type="date" name="date"><br>
                    <c:if test="${errors.get(6) != null}" >
                        <div class="cc" >
                                ${errors.get(6)}
                        </div>
                    </c:if><br>
                </td>
            </tr>

            <tr>
                <td>
                    <lable>Role:</lable>
                </td>
                <td>
                    <p><select size="2" name="role" form="data">
                        <option disabled>Select a role</option>
                        <option value="user">user</option>
                        <option selected value="admin">admin</option>
                    </select></p>
                    <input class="button" type="submit" value="Edit user">
                </td></tr>
        </table>

    </form>
</div>
</body>
</html>
