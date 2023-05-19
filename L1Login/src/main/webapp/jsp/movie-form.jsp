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
   <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/user/page">Profile</a>
   <a class="nav-link active" href="<%=request.getContextPath()%>/sign-out">Sign Out</a>

   <div class="container col-md-5">
      <div class="card">
         <div class="card-body">
            <c:if test="${movie != null}">
               <form action="update" method="post">
            </c:if>
            <c:if test="${movie == null}">
               <form action="add" method="post">
            </c:if>

            <caption>
               <h2>
                  <c:if test="${movie != null}">
                         Edit Favorite Movie
                      </c:if>
                  <c:if test="${movie == null}">
                         Add New Favorite Movie
                      </c:if>
               </h2>
            </caption>

            <c:if test="${movie != null}">
                    <input type="hidden" name="id" value="<c:out value='${movie.id}' />" />
                </c:if>
                <fieldset class="form-group">
                   <label>Title</label> <input type="text"
                      value="<c:out value='${movie.title}' />" class="form-control"
                      name="title" required="required">
                </fieldset>

                <fieldset class="form-group">
                   <label>Release Year</label> <input type="text"
                      value="<c:out value='${movie.release}' />" class="form-control"
                      name="release" required="required">
                </fieldset>
                <c:if test="${error != null}">
                    <font color="red"> <c:out value='${error}' /></font>
                </c:if>
                <div class="text-center">
                <button type="submit" class="btn btn-success">Save</button>
                <c:if test="${movie != null}">
                    <a href="<%=request.getContextPath()%>/movie/delete?id=${movie.id}" class="btn btn-danger" role="button">Delete</a>
                </c:if>
                </div>
            </form>
         </div>
      </div>
   </div>
</body>
</html>

