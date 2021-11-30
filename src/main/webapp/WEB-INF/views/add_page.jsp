<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add page</title>
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
<h1><b>Add user</b></h1><br><br>
<div class="form-group">
    <form method="post" action="${pageContext.request.contextPath}/home/add">
        <table class="table table-dark">
            <tr>
                <td>
                    <label>Login: </label>
                </td>
                <td>
                    <input type="text" required placeholder="login" name="login"><br><br>
                    <c:if test="${errors.get(0) != null}" >
                        <div class="cc" >
                                ${errors.get(0)}
                        </div>
                    </c:if>
                </td></tr>

            <tr>
                <td>
                    <label>Password: </label>
                </td>
                <td>
                    <input type="password" required placeholder="password"
                           name="password"><br>
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
                    <label>Email: </label></td>
                <td>
                    <input type="email" required placeholder="email" name="email"><br>
                    <c:if test="${errors.get(3) != null}" >
                        <div class="cc" >
                                ${errors.get(3)}
                        </div>
                    </c:if><br>
                </td>
            </tr>
            <tr>
                <td>
                    <label>First name: </label></td>
                <td>
                    <input type="name" required placeholder="First Name" name="firstName"><br>
                    <c:if test="${errors.get(4) != null}" >
                        <div class="cc" >
                                ${errors.get(4)}
                        </div>
                    </c:if><br>
                </td>
            </tr>

            <tr>
                <td>
                    <label>Last name: </label>
                </td>
                <td>
                    <input type="name" required placeholder="Last Name" name="lastName"><br>
                    <c:if test="${errors.get(5) != null}" >
                        <div class="cc" >
                                ${errors.get(5)}
                        </div>
                    </c:if><br>
                </td></tr>

            <tr>
                <td>
                    <label>Date: </label></td>
                <td>
                    <input type="date" required placeholder="date of birtday" name="date"><br>
                    <c:if test="${errors.get(6) != null}" >
                        <div class="cc" >
                                ${errors.get(6)}
                        </div>
                    </c:if><br>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Role :</label>
                </td>
                <td>
                    <p><select size="2" required multiple name="role">
                        <option disabled>Select a role</option>
                        <option value="user">user</option>
                        <option selected value="admin">admin</option>
                    </select></p>


                    <input class="button" type="submit" value="add user">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
