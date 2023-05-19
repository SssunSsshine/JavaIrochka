<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<title>User Registration App</title>
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
</head>
<body>
    <div class="container col-md-2" style = "position: relative; height: 739px;">
        <div style = "height: 35%;position: absolute;top: 0;bottom: 0;margin: auto 0;width: 250px;">
            <form action="sign-in" method="post">
                <h2>Login</h2>
                <fieldset class="form-group">
                    <input type="email"
                            class="form-control"
                        name="login" placeholder="email" required="required">
                </fieldset>

                <fieldset class="form-group">
                    <input type="password"
                            class="form-control"
                        name="password" placeholder="password" required="required">
                </fieldset>
                <c:if test="${error != null}">
                    <font color="red"> <c:out value='${error}' /></font>
                </c:if>
                <div class="text-center">
                    <button type="submit" class="btn btn-success">Sign in</button>
                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success" role="button">Sign up</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>