/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3913.spring2016.io;
import java.util.*;
import java.io.*;
import java.net.*;


/**
 *
 * @author dkatz
 */
public class CS3913Spring2016IO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Socket s = new Socket("www.google.com",80);
            Scanner sin = new Scanner(s.getInputStream());
            PrintStream ps = new PrintStream(s.getOutputStream());
            ps.print("GET / HTTP/1.0\r\n\r\n");
            while (sin.hasNext())
                System.out.println(sin.nextLine());
        }
        catch(IOException e){
            System.out.println("Connection to google failed. :(");
        }
        
    }
    
}
