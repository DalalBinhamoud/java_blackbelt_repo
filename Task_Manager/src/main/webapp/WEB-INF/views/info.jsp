<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Task Info</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<h1>Task: <c:out value="${task.title}"/></h1>

<p>Creator: <c:out value="${task.creator}"/> </p>
<p>Assignee: <c:out value="${task.user.getName()}"/> </p>

    <c:if test = "${task.priority == 'a'}">
     <p>Priority: High</p>
      </c:if>
      
      <c:if test = "${task.priority == 'b'}">
     <p>Priority: Medium</p>
      </c:if>
      <c:if test = "${task.priority == 'c'}">
     <p>Priority: Low</p>
      </c:if>


<%--   <c:if test="if!_user_has_not_logged_in"> --%>

  <c:if test = "${user.name == task.creator}">
       <a href="/tasks/${task.id}/edit">Edit</a>
       <a href="/tasks/${task.id}/deleteTask">Delete</a>
       
  </c:if>



<c:if test = "${user.name == task.user.getName()}">
<a href="/tasks/${task.id}/deleteTask">Completed</a>
 </c:if>


</body>
</html>