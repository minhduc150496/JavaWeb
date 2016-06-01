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
        
        <% 
            String username = (String)session.getAttribute("USERNAME");
            if (username==null) username="";
        %>
        
        <form action="ProcessServlet" method="POST">
            Username <input type="text" name="txtUsername" value="<%= username %>" autocomplete="off"/><br/>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <input type="submit" value="Login" name="btAction"/>
            <input type="reset" value="Reset" />
        </form>

    </body>
</html>
