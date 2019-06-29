<%-- 
    Document   : welcome
    Created on : Jun 29, 2019, 11:17:45 AM
    Author     : s206e18
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <% 
            out.println(request.getAttribute("message"));
        %>
    </body>
</html>
