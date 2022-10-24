/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var Lab6 = (function(){
    
    var createSessionMenu = function(response){
        
        var sessions = response.sessions;
        
        var sessionMenu = document.getElementById("sessionmenu");
        
        var select = document.createElement("select");
        select.name = "sessionid";
        select.id = "sessionid";
        select.style.backgroundColor = "#f0eded";
        
        for(var i=0; i<sessions.length; i++){
            var option = document.createElement("option");
            var description = sessions[i].description;
            
            var id = sessions[i].id;

            var optionName = "(" + description + ")";

            if(id===1){
                option.selected = true;
            }
            option.value = id.toString();
            var optionText = document.createTextNode(optionName);
            option.appendChild(optionText);

            select.appendChild(option);
        }
        
        sessionMenu.appendChild(select);
        
    };
    
    var registerAttendee = function(attendeeid){
        
        $.ajax({
            url: "http://localhost:8180/Lab6/main/registrations",
            method: 'POST',
            data: {"sessionid": $("#sessionid").val(), "attendeeid": attendeeid},
            datatype: 'json',
            success: function(){
                console.log("Successfully registered attendee!");
            }
        });
        
    };
    
    var addAttendee = function(){
        
        $.ajax({
            url: "http://localhost:8180/Lab6/main/AttendeeServlet",
            method: 'POST',
            data: $("#attendeeform").serialize(),
            datatype: 'json',
            success: function(response){
                console.log("Successfully added attendee!");
                registerAttendee(response.id);
            }
        });
        
    };
    
    var editRegistration = function(){
        
        $.ajax({
            url: "http://localhost:8180/Lab6/main/registrations",
            type: 'PUT',
            data: $("#editregistrationform").serialize(),
            datatype: 'json',
            success: function(){
                console.log("Successfully edited registration!");
            }
        });
        
    };
    
    var deleteRegistration = function(){
        
        $.ajax({
            url: "http://localhost:8180/Lab6/main/registrations",
            type: 'DELETE',
            data: $("#deleteregistrationform").serialize(),
            datatype: 'json',
            success: function(){
                console.log("Successfully deleted registration!");
            }
        });
        
    };
    
    var editAttendee = function(){
        
        $.ajax({
            url: "http://localhost:8180/Lab6/main/AttendeeServlet",
            method: 'PUT',
            data: $("#editattendeeform").serialize(),
            datatype: 'json',
            success: function(){
                console.log("Successfully edited attendee!");
            }
        });
        
    };
    
    return {
        
        getSessionsList: function(){
            
            $.ajax({
                url: "http://localhost:8180/Lab6/main/TrainingSessionServlet",
                method: 'GET',
                datatype: 'json',
                success: function(response){
                    createSessionMenu(response);
                }
            });
            
        },
        
        onclick1: function(){
            
            addAttendee();
            
        },
        
        onclick2: function(){
            
            editRegistration();
            
        },
        
        onclick3: function(){
            
            deleteRegistration();
            
        },
        
        onclick4: function(){
            
            editAttendee();
            
        }
        
    };
    
})();