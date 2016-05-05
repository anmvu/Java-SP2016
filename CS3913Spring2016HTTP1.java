/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3913.spring2016.http1;
import java.io.*;
import java.util.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author dkatz
 */
public class CS3913Spring2016HTTP1 {
    static String a = "Final on May 5th";
    static String b = "Final on May 12th";
            
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Log l = new Log();
        try{
            ServerSocket ss = new ServerSocket(8080);
            JFrame jf = new JFrame("Vote Monitor");
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setSize(730, 500);
            Monitor m = new Monitor();
            jf.add(m);
            jf.setResizable(false);
            jf.setVisible(true);
            new UpdateList(m,l).start();
            while(true){
                Socket sock = ss.accept();
                new ProcessConnection(sock,l).start();
            }
        }
        catch(IOException e){
            System.out.println("Unable to listen on socket!");
        }
    }
    
}

class UpdateList extends Thread{
    Monitor m;
    Log l;
    UpdateList(Monitor newm,Log newl){m = newm; l=newl;}
    public void run(){
        try{
            while(true){
                m.aList.setText("");
            
                String totalList="";
                int count=0;
                for(String item:l.getList('a')){
                    totalList+=item+"\n";
                    count++;
                }
                //System.out.println("Found "+count+" items on the a list");
                m.aList.setText(totalList);
                m.aTotal.setText(""+count);

                m.bList.setText("");
                totalList="";
                count=0;
                for(String item:l.getList('b')){
                    totalList+=item+"\n";
                    count++;
                }
                m.bList.setText(totalList);
                m.bTotal.setText(""+count);
                sleep(100);
            }
        }
        catch(Exception e){
            System.out.println("Monitor ending!");
        }
    }
}
class Log{
    ArrayList<String> a;
    ArrayList<String> b;
    Log(){
        a = new ArrayList();
        b = new ArrayList();
    }
    public synchronized boolean addToList(String element, char list){
        System.out.println("Adding "+element+ " to "+list);
        for (String s:a)
            if (s.equals(element))
                return false;
        for (String s:b)
            if (s.equals(element))
                return false;
        
        if (list=='a')
            a.add(element);
        else
            b.add(element);
        return true;
    }
    public synchronized ArrayList<String> getList(char list){
        if (list=='a')
            return (ArrayList<String>)a.clone();
        else
            return (ArrayList<String>)b.clone();
    }
}

class ProcessConnection extends Thread{
    Log l;
    Socket sock;
    ProcessConnection(Socket newSock, Log newl){
        sock = newSock;
        l=newl;
    }
    public void run(){
        try{
            String a = CS3913Spring2016HTTP1.a;
            String b = CS3913Spring2016HTTP1.b;
            
            Scanner sin = new Scanner(sock.getInputStream());
            PrintStream sout = new PrintStream(sock.getOutputStream());
            String line=sin.nextLine();
            String firstLine = line;
            while (!line.isEmpty()){
                line = sin.nextLine();
            }
            String[] parts = firstLine.split(" ");
            String file = parts[1];
            //System.out.println(sock.getInetAddress().getHostAddress()+" wants: "+file); //debug
            if (file.equals("/")){
                String output = "<HTML><BODY><Center><H2>Hi</H2>"
                        + "</center>Which would you choose?<br>"
                        + "<form action='/a' method='GET'><input type='Submit' value='"+a+"'></form>"
                        + "<form action='/b' method='GET'><input type='Submit' value='"+b+"'></form></html>";
                sout.print("HTTP/1.0 200 OK\r\n\r\n");
                sout.print(output+"\r\n");
            }
            else if (file.equals("/a?") || file.equals("/a")){
                String output;
                if (l.addToList(sock.getInetAddress().getHostAddress(), 'a'))
                    output = "<HTML><BODY>Thank you, your vote has been cast for "+a+"</p></body></html>";
                else
                    output = "<HTML><BODY>Your vote was previously counted!</p></body></html>";
                sout.print("HTTP/1.0 200 OK\r\n\r\n");
                sout.print(output+"\r\n");
            }
            else if (file.equals("/b?")|| file.equals("/b")){
                String output;
                if (l.addToList(sock.getInetAddress().getHostAddress(), 'b'))
                    output = "<HTML><BODY>Thank you, your vote has been cast for "+b+"</p></body></html>";
                else
                    output = "<HTML><BODY>Your vote was previously counted!</p></body></html>";
                sout.print("HTTP/1.0 200 OK\r\n\r\n");
                sout.print(output+"\r\n");
            }
            else{
                String output;
                output = "<HTML><BODY>Sorry, I didn't understand what you want</p></body></html>";
                sout.print("HTTP/1.0 200 OK\r\n\r\n");
                sout.print(output+"\r\n");
            }
            sock.close();
        }
        catch(IOException e){}
    }
}