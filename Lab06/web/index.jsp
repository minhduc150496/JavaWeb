<%-- 
    Document   : index
    Created on : May 18, 2016, 4:59:19 PM
    Author     : Chuot Bach
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
            
        <jsp:useBean id="loginAtt" class="sample.beans.LoginBean" scope="session"/>  
        <form action="processLogin.jsp" method="POST">
            Username <input type="text" name="username" value="${loginAtt.username}" autocomplete="off"/><br/>
            Password <input type="password" name="password" value="" /><br/>
            <input type="submit" value="Login" name="btAction"/>
            <input type="reset" value="Reset" />
        </form>

    </body>
</html>
