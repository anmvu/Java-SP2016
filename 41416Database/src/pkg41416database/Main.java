/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg41416database;

import java.io.*;
import java.sql.*;

/**
 *
 * @author webteam
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // 172.16.10.117
        //INSERT INTO TEST(MOTTO,NNUM) VALUES("SOME MOTTO","12345C")
        Connection conn = null; //connection to sql server
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();//create new instance to have jdbc to connect to mysql
            String url = "jdbc:mysql://172.16.10.117/cs391";
            String dbuser = "cs391";
            String dbpass = "abc123";
            conn = DriverManager.getConnection(url,dbuser,dbpass);
            //to advance the result set at least once, must get result set
            Statement s = conn.createStatement();
//            s.executeQuery("Select * from test;");
//            ResultSet rs = s.getResultSet();
//            while(rs.next()){
//                    int tid = rs.getInt("TID");
//                    String motto = rs.getString("Motto");
//                    System.out.println("tid: "+ tid + "\tMotto: "+ motto);
//            }
//            rs.close();
//            s.close();
//            s = conn.createStatement();
            s.execute("INSERT INTO TEST(Motto,NNum) VALUES('Pero like Why','N14433367');");
        }catch(Exception e){
            System.out.println("Connection error: "+e.toString());
        }finally{
            try{
                conn.close();
                System.out.println("Connection Closed!");
            }catch(Exception e){}
        }
    }
    
}
