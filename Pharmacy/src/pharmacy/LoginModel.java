/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Zahin Ahmed
 */
public class LoginModel {
    Connection connection;
    
    public LoginModel(){
        try {
            this.connection=SQLiteConnect.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (this.connection==null){
            System.exit(1);
        }
    }
    
    public boolean isDatabaseConnected(){
        return this.connection!=null;
    }
    
    public boolean isLogin(String user, String pass, String opt) throws Exception{
        PreparedStatement pr =null;
        ResultSet rs= null;
        String sql = "SELECT * FROM Login where username= ? and password = ? and division = ?";
        
        try {
            pr=this.connection.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, pass);
            pr.setString(3, opt);
            
            rs= pr.executeQuery();
            
            boolean bool1;
            if(rs.next()){
                return true;
            }
            return false;
    }
        catch (SQLException ex){
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            pr.close();
            rs.close();
        }
        
}
}


