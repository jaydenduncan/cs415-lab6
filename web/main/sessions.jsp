<%-- 
    Document   : sessions
    Created on : Oct 20, 2022, 12:49:21 PM
    Author     : jayde
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sessions Page</title>
        <script src="jquery-3.6.1.min.js"></script>
        <script src="registration.js" type="text/JavaScript"></script>
    </head>
    <body>
        
        <form id="editregistrationform" name="editregistrationform">
            <fieldset>
                <legend>Edit a Registration</legend>
                
                <p>
                    <label for="putattendeeid">Your ID Number:</label>
                    <input name="putattendeeid" id="putattendeeid" type="number">
                </p>
                
                <p>
                    <label for="putsessionid">Current Session Number:</label>
                    <input name="putsessionid" id="putsessionid" type="number">
                </p>
                
                <p>
                    <label for="putnewsessionid">New Session Number:</label>
                    <input name="putnewsessionid" id="putnewsessionid" type="number">
                </p>
                
                <p>
                    <input type="button" value="Edit Registration" onclick="Lab6.onclick2();">
                </p>
                
            </fieldset> 
        </form>
        
        <br>
        
        <form id="deleteregistrationform" name="deleteregistrationform">
            <fieldset>
                <legend>Delete a Registration</legend>
                
                <p>
                    <label for="deleteattendeeid">Your ID Number:</label>
                    <input name="deleteattendeeid" id="deleteattendeeid" type="number">
                </p>
                
                <p>
                    <label for="deletesessionid">Session Number:</label>
                    <input name="deletesessionid" id="deletesessionid" type="number">
                </p>
                
                <p>
                    <input type="button" value="Delete Registration" onclick="Lab6.onclick3();">
                </p>
            </fieldset>
        </form>
        
        <p>Back to home page: <a href="index.jsp">Home Page</a></p>
        
    </body>
</html>
