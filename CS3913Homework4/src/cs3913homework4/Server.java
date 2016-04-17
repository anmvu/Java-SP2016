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
 *
 * @author An Vu
 */
public class Server {
    
    public static ArrayList<Socket> users = new ArrayList<Socket>();
    public static void main(String[] args) {
        try{
           ServerSocket ss = new ServerSocket(5190);
            while(true){
               Socket sock = ss.accept();
               users.add(sock);
               new ProcessClient(sock).start();
            }
        }catch(IOException e){}
    }
}

class ProcessClient extends Thread{
    Socket sock;
    String username;
    ProcessClient(Socket newSock){sock = newSock;}
    @Override
    public void run(){
        try{
            Scanner sin =  new Scanner(sock.getInputStream());
            PrintStream sout = new PrintStream(sock.getOutputStream());
            username = sin.nextLine();
            while(sock.isConnected()){
                String line = "";
                try {
                    line = sin.nextLine();
                }
                catch (NoSuchElementException e) { 
                    sock.close();
                }
                if (!"".equals(line)) {
                    for (Socket user : Server.users) {
                        PrintStream cout = new PrintStream(user.getOutputStream());
                        cout.printf("%s: %s\r\n", username, line);
                    }
                }
                
            }
            Server.users.remove(sock);
        }catch(IOException e){}
    }
}