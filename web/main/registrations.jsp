<%-- 
    Document   : registrations
    Created on : Oct 20, 2022, 12:03:21 AM
    Author     : jayde
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrations Page</title>
        <script src="jquery-3.6.1.min.js"></script>
        <script src="registration.js"></script>
    </head>
    <body>
        
        <form id="attendeeform" name="attendeeform">
            
            <fieldset>
                <legend>Attendee Registration Form</legend>
                
                <p>
                    <label for="firstname">First Name:</label>
                    <input name="firstname" id="firstname" type="text">
                </p>
                
                <p>
                    <label for="lastname">Last Name:</label>
                    <input name="lastname" id="lastname" type="text">
                </p>
                
                <p>
                    <label for="displayname">Display Name:</label>
                    <input name="displayname" id="displayname" type="text">
                </p>
                
                <p>Session: <span id="sessionmenu"></span></p>
                
                <p>
                    <input type="button" value="Submit" onclick="Lab6.onclick1();">
                </p>
                
            </fieldset>
            
        </form>
        
        <p>Back to home page: <a href="index.jsp">Home Page</a></p>
        
        <script type="text/JavaScript">
            $("#firstname").change(function(){
                var firstname = $("#firstname").val().trim();
                var lastname = $("#lastname").val().trim();
                $("#displayname").val(firstname + " " + lastname);
            });

            $("#lastname").change(function(){
                var firstname = $("#firstname").val().trim();
                var lastname = $("#lastname").val().trim();
                $("#displayname").val(firstname + " " + lastname);
            });
            
            Lab6.getSessionsList();
        </script>
        
    </body>
</html>
