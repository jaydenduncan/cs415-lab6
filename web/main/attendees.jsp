<%-- 
    Document   : attendees
    Created on : Oct 20, 2022, 12:49:46 PM
    Author     : jayde
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attendee Page</title>
        <script src="jquery-3.6.1.min.js"></script>
        <script src="registration.js" type="text/JavaScript"></script>
    </head>
    <body>
        
        <form name="editattendeeform" id="editattendeeform">
            <fieldset>
                <legend>Edit User Profile</legend>
                
                <p>
                    <label for="attendeeid">Your ID Number:</label>
                    <input name="attendeeid" id="attendeeid" type="number">
                </p>
                
                <p>
                    <label for="newfirstname">New First Name:</label>
                    <input name="newfirstname" id="newfirstname" type="text">
                </p>
                
                <p>
                    <label for="newlastname">New Last Name:</label>
                    <input name="newlastname" id="newlastname" type="text">
                </p>
                
                <p>
                    <label for="newdisplayname">New Display Name:</label>
                    <input name="newdisplayname" id="newdisplayname" type="text">
                </p>
                
                <p>
                    <input type="button" value="Edit Attendee"  onclick="Lab6.onclick4()">
                </p>
                
            </fieldset> 
        </form>
        
        <p>Back to home page: <a href="index.jsp">Home Page</a></p>
        
        <script>
            $("#newfirstname").change(function(){
                var firstname = $("#newfirstname").val().trim();
                var lastname = $("#newlastname").val().trim();
                $("#newdisplayname").val(firstname + " " + lastname);
            });

            $("#newlastname").change(function(){
                var firstname = $("#newfirstname").val().trim();
                var lastname = $("#newlastname").val().trim();
                $("#newdisplayname").val(firstname + " " + lastname);
            });
        </script>
        
    </body>
</html>
