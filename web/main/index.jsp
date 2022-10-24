<%-- 
    Document   : index
    Created on : Oct 23, 2022, 10:27:00 PM
    Author     : jayde
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Lab #6</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <p>For more information about this API, see the instructions on Canvas.</p>
        
        <p>Click this link to visit registrations page: <a href="/Lab6/main/registrations.jsp">Registrations</a></p>
        <p>Click this link to visit sessions page: <a href="/Lab6/main/sessions.jsp">Sessions</a></p>
        <%
            if ( request.isUserInRole("administrator") ) {
        %>
        <p>Click this link to visit attendees page: <a href="/Lab6/main/attendees.jsp">Attendees</a></p>
        <%
            }
        %>
        
        <p>
            <input type="button" value="Logout" onclick="window.open('<%= request.getContextPath() %>/main/logout.jsp', '_self', false);" />
        </p>
    </body>
</html>
