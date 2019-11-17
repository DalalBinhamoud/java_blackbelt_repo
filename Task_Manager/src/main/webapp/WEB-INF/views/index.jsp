 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="/css/style.css">
</head>
<body>

  <div class="w3-row-padding w3-grayscale">
    <div class="w3-col l3 m6 w3-margin-bottom">
        
    <h1>Register</h1>
         <p class="errorCss"><c:out value="${registration_error}" /></p>
    <p><form:errors path="user.*"/></p>
    
    <form:form method="POST" action="/registration" modelAttribute="user">
    
     <p>
            <form:label path="name">Name:</form:label>
            <form:input  path="name" required="required"/>
        </p>
        <p>
            <form:label path="email">Email:</form:label>
            <form:input type="email" path="email" required="required"/>
        </p>
        <p>
            <form:label path="password">Password:</form:label>
            <form:password path="password" required="required"/>
        </p>
        <p>
            <form:label path="passwordConfirmation">Password Conf:</form:label>
            <form:password path="passwordConfirmation" required="required"/>
        </p>
        <input type="submit" value="Register"/>
    </form:form>
      
    </div>
    <div class="w3-col l3 m6 w3-margin-bottom">
         <h1>Login</h1>
    <p><c:out value="${error}" /></p>
    <form method="post" action="/login">
        <p>
            <label for="email">Email</label>
            <input type="text" id="email" name="email" required="required"/>
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required="required"/>
        </p>
        <input type="submit" value="Login"/>
    </form> 
    </div>
  </div>

    
    
  
    
    
</body>
</html>