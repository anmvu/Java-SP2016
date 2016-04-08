/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3913homework4;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.net.*;

/**
 *Homework 4
 * Instant Messaging
 * PART 1:
server component - runs in the background - no graphical interface - listens for connections - has protocol specifications
need to get name of user and lines that come in from a connection and rebroadcasted to all the other connections in the server

Client:
	2 screens
	- text area
	- text input at the bottom - push send = sent to server
	when starts, asks person for their name
	when connected, that screen goes away, connection establishes to server and chat shows
	upon connection, greet, accept and anything after that gets rebroadcasted
 * 
 * 
 * @author An
 */
public class CS3913Homework4 {

    /**
     * @param args the command line arguments
     */
    static JFrame jf;
    static JPanel jp;
    static JButton submit;
    static JTextField chat;
    static JScrollPane history;
    public static void main(String[] args) {
        Log l = new Log();
        try{
            jf = new JFrame("Let's Chat!");
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setSize(800,600);
            jp = new JPanel();
            submit = new JButton("Submit");
            ServerSocket ss = new ServerSocket(5190);
            while(true){
               Socket sock = ss.accept();
               Scanner sin = new Scanner(sock.getInputStream());
               PrintStream sout= new PrintStream(sock.getOutputStream());
               String line = sin.nextLine();
               while(l.getList('u').contains(line)){
                   line = sin.nextLine();
               }
               String handle = line;
               String ip =sock.getInetAddress().getHostAddress();
               new ProcessClient(sock,handle,ip).start();
            }
       }catch(IOException e){}
    }
    
}

class Log{
    ArrayList<String> u;
    ArrayList<String> h;
    ArrayList<String> chatHistory;
    Log(){
        u = new ArrayList();
        h = new ArrayList();
        chatHistory = new ArrayList();
    }
    public synchronized boolean addToList(String element, char list){
        for(String s : u)
            if(s.equals(element)) return true;
        for(String s : h)
            if(s.equals(element)) return true;   
        switch (list) {
            case 'u':
                u.add(element);
                break;
            case 'c':
                chatHistory.add(element);
                break;
            default:
                h.add(element);
                break;
        }
        return false;
    }

    public synchronized ArrayList<String> getList(char list){
        switch (list) {
            case 'u':
                return (ArrayList<String>)u.clone();
            case 'c':
                return (ArrayList<String>)chatHistory.clone();
            default:
                return (ArrayList<String>)h.clone();
        }
    }
}

class ProcessClient extends Thread{
    Socket sock;
    String handle;
    String ip;
    ProcessClient(Socket newSock,String newH, String newip){sock = newSock; handle=newH; ip = newip;}
    @Override
    public void run(){
        try{
            Scanner sin =  new Scanner(sock.getInputStream());
            PrintStream sout = new PrintStream(sock.getOutputStream());
            while(sock.isConnected()){
                String line = sin.nextLine();
                sout.print(line+"\r\n");
                System.out.println(sock.getInetAddress().getHostAddress()+": "+ line);
                
            }
            sock.close();
        }catch(IOException e){}
    }
}