package edu.jsu.mcis.lab6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.json.simple.*;

public class RegistrationDAO {
    
    private final DAOFactory daoFactory;
    
    private final String QUERY_SELECT_BY_ID = "SELECT * FROM "
            + "((registration JOIN attendee ON registration.attendeeid = attendee.id) "
            + "JOIN `session` ON registration.sessionid = `session`.id) "
            + "WHERE `session`.id = ? AND attendee.id = ?";
    private final String QUERY_CREATE = "INSERT INTO registration " 
            + "(attendeeid, sessionid) VALUES(?, ?)";
    private final String QUERY_UPDATE = "UPDATE registration SET "
            + "sessionid=? WHERE attendeeid=? AND sessionid=?";
    private final String QUERY_DELETE_REG = "DELETE FROM registration WHERE attendeeid=? "
            + "AND sessionid=?";
    private final String QUERY_DELETE_ATTENDEE = "DELETE FROM attendee WHERE id=?";
    private final String QUERY_ATTENDEE_CODE = "SELECT CONCAT(\"R\", LPAD(attendeeid, 6, 0)) AS num FROM registration " +
            "WHERE attendeeid=? AND sessionid=?";
    
    
    RegistrationDAO(DAOFactory dao) {
        this.daoFactory = dao;
    }
    
    public String find(int sessionid, int attendeeid) {

        JSONObject json = new JSONObject();
        json.put("success", false);

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(QUERY_SELECT_BY_ID);
            ps.setInt(1, sessionid);
            ps.setInt(2, attendeeid);
            
            boolean hasresults = ps.execute();

            if (hasresults) {

                rs = ps.getResultSet();
                
                if (rs.next()) {
                    
                    json.put("success", hasresults);
                    
                    json.put("attendeeid", rs.getInt("attendeeid"));
                    json.put("sessionid", rs.getInt("sessionid"));
                    json.put("firstname", rs.getString("firstname"));
                    json.put("lastname", rs.getString("lastname"));
                    json.put("displayname", rs.getString("displayname"));
                    json.put("session", rs.getString("description"));
                                        
                }

            }
            
            ps2 = conn.prepareStatement(QUERY_ATTENDEE_CODE);
            ps2.setInt(1, attendeeid);
            ps2.setInt(2, sessionid);
            
            boolean hasresults2 = ps2.execute();
            
            if(hasresults2){
                rs = ps2.getResultSet();
                if(rs.next()){
                    json.put("registrationcode", rs.getString("num"));
                }
            }

        }
        catch (Exception e) {
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
    
    public String create(int sessionid, int attendeeid){
        
        JSONObject json = new JSONObject();
        json.put("success", false);
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            
            ps = conn.prepareStatement(QUERY_CREATE);
            ps.setInt(1, attendeeid);
            ps.setInt(2, sessionid);
            ps.execute();
            
            json.put("success", true);
            
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
    
    public String update(String sessionid, String attendeeid, String newsessionid){
        
        JSONObject json = new JSONObject();
        json.put("success", false);
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        System.err.println("Session ID: " + sessionid);
        System.err.println("Attendee ID: " + attendeeid);
        System.err.println("New Session ID: " + newsessionid);
        
        int int_sessionid = Integer.parseInt(sessionid);
        int int_attendeeid = Integer.parseInt(attendeeid);
        int int_newsessionid = Integer.parseInt(newsessionid);
        
        try{
            
            ps = conn.prepareStatement(QUERY_UPDATE);
            ps.setInt(1, int_sessionid);
            ps.setInt(2, int_attendeeid);
            ps.setInt(3, int_newsessionid);
            ps.execute();
            
            json.put("success", true);
            
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
    
    public String delete(int sessionid, int attendeeid){
        
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        
        JSONObject json = new JSONObject();
        json.put("success", false);
        
        boolean result = false;
        
        try{
            
            ps = conn.prepareStatement(QUERY_DELETE_REG);
            ps.setInt(1, attendeeid);
            ps.setInt(2, sessionid);
            
            int updateCount = ps.executeUpdate();
            
            if(updateCount > 0){
                result = true;
            }
            
            ps2 = conn.prepareStatement(QUERY_DELETE_ATTENDEE);
            ps2.setInt(1, attendeeid);
            ps2.execute();
            
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
