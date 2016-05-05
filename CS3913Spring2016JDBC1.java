/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3913.spring2016.jdbc1;
import java.io.*;
import java.sql.*;
/**
 *
 * @author dkatz
 */
public class CS3913Spring2016JDBC1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conn=null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://127.0.0.1/cs391";
            String dbuser = "cs391";
            String dbpass = "abc123";
            conn = DriverManager.getConnection(url,dbuser,dbpass);
            Statement s = conn.createStatement();
            s.executeQuery("select * from test;");
            ResultSet rs = s.getResultSet();
            while(rs.next()){
                int tid = rs.getInt("TID");
                String motto = rs.getString("Motto");
                System.out.println("tid: "+tid+"\tMotto:"+motto);
            }
            rs.close();
            s.close();
            s = conn.createStatement();
            s.execute("INSERT INTO TEST(Motto,NNum) VALUES('Hello','1111');");//throws an exception if it fails
            
        }
        catch(Exception e){
            System.out.println("Connection error: "+e.toString());
        }
        finally{
            try{
                conn.close();
                System.out.println("Connection Closed!");
            }
            catch(Exception e){}
        }
    }
    
}
