/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.lab6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author jayde
 */
public class AttendeeDAO {
    
    private static final String QUERY_FIND_BY_ID = "SELECT firstname, lastname, displayname "
            + "FROM attendee WHERE id = ?";
    private final String QUERY_CREATE = "INSERT INTO attendee " 
            + "(firstname, lastname, displayname) VALUES(?, ?, ?)";
    private final String QUERY_UPDATE = "UPDATE attendee SET "
            + "firstname=?, lastname=?, displayname=? WHERE id=?";
    private final String QUERY_ATTENDEE_CODE = "SELECT CONCAT(\"R\", LPAD(id, 6, 0)) AS num FROM attendee " +
            "WHERE id=?";
    
    private final DAOFactory daoFactory;
    
    AttendeeDAO(DAOFactory dao){
        this.daoFactory = dao;
    }
    
    public String find(int attendeeid){
        
        JSONObject json = new JSONObject();
        json.put("success", false);
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        
        try{
            ps = conn.prepareStatement(QUERY_FIND_BY_ID);
            ps.setInt(1, attendeeid);
            
            boolean hasResults = ps.execute();
            
            if(hasResults){
                rs = ps.getResultSet();
                
                while(rs.next()){
                    
                    json.put("firstname", rs.getString("firstname"));
                    json.put("lastname", rs.getString("lastname"));
                    json.put("displayname", rs.getString("displayname"));
                    json.put("success", true);
                    
                }
            }
            
            ps2 = conn.prepareStatement(QUERY_ATTENDEE_CODE);
            ps2.setInt(1, attendeeid);
            
            boolean hasresults2 = ps2.execute();
            
            if(hasresults2){
                rs = ps2.getResultSet();
                if(rs.next()){
                    json.put("registrationcode", rs.getString("num"));
                }
            }
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
            if (ps2 != null) {
                try {
                    ps2.close();
                    ps2 = null;
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
        
        return JSONValue.toJSONString(json);
    }
    
    public String create(Attendee a){
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        JSONObject json = new JSONObject();
        json.put("success", false);
        
        int result = 0;
        
        try{
            
            ps = conn.prepareStatement(QUERY_CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getFirstname());
            ps.setString(2, a.getLastName());
            ps.setString(3, a.getDisplayName());
            
            int updateCount = ps.executeUpdate();
            
            if(updateCount > 0){
                json.put("success", true);
                
                rs = ps.getGeneratedKeys();
                
                if(rs.next()){
                    result = rs.getInt(1);
                    json.put("id", result);
                }
                
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {

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
        
        return JSONValue.toJSONString(json);
        
    }
    
    public String update (int id, String newfirstname, String newlastname, String newdisplayname){
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        JSONObject json = new JSONObject();
        
        json.put("success", false);
        
        boolean result = false;
        
        try{
            
            ps = conn.prepareStatement(QUERY_UPDATE);
            ps.setString(1, newfirstname);
            ps.setString(2, newlastname);
            ps.setString(3, newdisplayname);
            ps.setInt(4, id);
            
            int updateCount = ps.executeUpdate();
            
            if(updateCount > 0){
                result = true;
            }
            
            json.put("success", result);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {

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
        
        return JSONValue.toJSONString(json);
        
    }
    
}
