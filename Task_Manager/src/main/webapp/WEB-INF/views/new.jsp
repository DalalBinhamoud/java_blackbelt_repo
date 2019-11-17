<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>new Task</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<h1>Create a new task</h1>

<form:form action="/tasks/create" method="post" modelAttribute="task">

<input type="hidden" name="creator" value="<c:out value="${user.name}"/>"/>
  <p>
        <form:label path="title">Task:</form:label>
        <form:errors path="title"/>
        <form:input path="title" required="required"/>
    </p>
    
    
    <p>
        Assignee:

  <select name="user">
  

       <c:forEach items="${users}" var="user">

 <c:if test = "${user.tasks.size()< 3}">
      <option  value="${user.id}" >  
            <c:out value="${user.name}"/>
        </option>
      </c:if>
      
       
        </c:forEach>
            </select>    
    </p>
    
    
       <p>
        Priority:

  <select name="priority">
  
 <option  value="a" > High </option>
  <option  value="b" > Medium </option>
   <option  value="c" > Low </option>

            </select>    
    </p>
    
    <input type="submit" value="Create"/>
</form:form>   
</body>
</html>