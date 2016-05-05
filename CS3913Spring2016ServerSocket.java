/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3913.spring2016.serversocket;

import java.io.*;
import java.util.*;
import java.net.*;
/**
 *
 * @author dkatz
 */
public class CS3913Spring2016ServerSocket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(1999);
            while(true){
                Socket sock = ss.accept();
                new ProcessClient(sock).start();
            }
        }
        catch(IOException e){
            System.out.println("Caught IOException!");
        }
    }
    
}
class ProcessClient extends Thread{
    Socket sock;
    ProcessClient(Socket newSock){sock=newSock;}
    public void run(){
        try{
            System.out.println(sock.getInetAddress().getHostAddress()+" connected");
            Scanner sin = new Scanner(sock.getInputStream());
            PrintStream sout = new PrintStream(sock.getOutputStream());
            sout.print("Welcome to the Echo Server!\r\n");
            while (!sock.isClosed()){
                String line = sin.nextLine();
                sout.print(line+"\r\n");
                System.out.println(sock.getInetAddress().getHostAddress()+": "+line);
                if (line.equalsIgnoreCase("EXIT"))
                    sock.close();
            }
        }
        catch(IOException e){}
        System.out.println(sock.getInetAddress().getHostAddress()+" disconnected");
        
    }
}
