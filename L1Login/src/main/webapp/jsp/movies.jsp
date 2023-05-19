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
   <a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/user/page">Home</a>
   <a class="nav-link active"  style = "color: red;" href="<%=request.getContextPath()%>/sign-out">Sign Out</a>
   <div class="container col-md-5">
        <div class="card">
           <div class="card-body">
                <caption>
                    <h2>
                         Information about your favorite movies
                    </h2>
                </caption>

                <c:if test="${movies != null}">
                    <table class="table">
                        <thead>
                          <tr>
                            <th scope="col">Information</th>
                            <th scope="col"></th>
                          </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="movie" items="${movies}">
                                <tr>
                                    <td>
                                        <p>ID: ${movie.id}</p>
                                        <p>Title: ${movie.title}</p>
                                        <p>Release Year: ${movie.releaseYear}</p>
                                    </td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/movie/delete?id=${movie.id}" class="btn btn-danger" role="button">Delete</a>
                                    </td>

                                </tr>
                            </c:forEach>
                        </tbody>
                      </table>
                </c:if>
            </div>
        </div>
   </div>
</body>
</html>

