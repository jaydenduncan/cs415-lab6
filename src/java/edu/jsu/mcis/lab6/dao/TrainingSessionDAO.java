/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.lab6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author jayde
 */
public class TrainingSessionDAO {
    
    private static final String QUERY_FIND_BY_ID = "SELECT attendeeid FROM registration WHERE sessionid = ?";
    private static final String QUERY_LIST = "SELECT * FROM session";
    
    private final DAOFactory daoFactory;
    
    TrainingSessionDAO(DAOFactory dao){
        this.daoFactory = dao;
    }
    
    public String find(int sessionid){
        
        JSONObject container = new JSONObject();
        JSONArray records = new JSONArray();
        JSONParser parser = new JSONParser();

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        AttendeeDAO aDAO = daoFactory.getAttendeeDAO();
        
        try{
            ps = conn.prepareStatement(QUERY_FIND_BY_ID);
            ps.setInt(1, sessionid);
            
            boolean hasResults = ps.execute();
            
            if(hasResults){
                container.put("success", true);
                rs = ps.getResultSet();
                while(rs.next()){
                    // CODE GOES HERE
                    
                    int attendeeID = rs.getInt("attendeeid");
                    String attendeejsonString = aDAO.find(attendeeID);
                    JSONObject attendeeObject = (JSONObject)parser.parse(attendeejsonString);
                    records.add(attendeeObject);
                    
                }
            }
            container.put("attendees", records);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            
        }
        
        return JSONValue.toJSONString(container);
    }
    
    public String list(){ 
        JSONObject container = new JSONObject();
        JSONArray records = new JSONArray();

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conn.prepareStatement(QUERY_LIST);
            
            boolean hasResults = ps.execute();
            
            if(hasResults){
                container.put("success", true);
                rs = ps.getResultSet();
                
                while (rs.next()) {
                    JSONObject json = new JSONObject();
                    
                    json.put("id", rs.getInt("id"));
                    json.put("description", rs.getString("description"));
                    
                    records.add(json);
                                        
                }
            }
            
            container.put("sessions", records);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception e) { e.printStackTrace(); }
            }
            
        }
        
        return JSONValue.toJSONString(container);
    }
    
}
