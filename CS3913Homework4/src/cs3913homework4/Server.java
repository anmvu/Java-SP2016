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
import cs3913homework4.Client;
/**
 *
 * @author Annie Vuvu
 */
public class Server {
    
    public static ServerSocket ss;
    public static void main(String[] args) {
        try{
           ss = new ServerSocket(5190);
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