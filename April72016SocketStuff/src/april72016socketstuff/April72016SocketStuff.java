/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package april72016socketstuff;
import java.io.*;
import java.util.*;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author webteam
 */

public class April72016SocketStuff {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try{
                ServerSocket ss = new ServerSocket(1999);
                //to check go to terminal and type telnet ip address port so 172.16.13.11 1999
                //not use for communication
                //if we accept - waits for connection to be established(client) and when connected, the socket is passed as a return
                //do not need to connect socket but can get input stream and output stream from it
                while(true){
                        Socket sock = ss.accept();//now connected to a client will not return from call until connected to client from the other side of the connection
                        
                }
        }catch(IOException e){
                System.out.println("Caught IO Excetion");
        }
    }
    
}

class ProcessClient extends Thread{
    Socket sock;
    ProcessClient(Socket newSock){sock = newSock;}
    public void run(){
        try{
            Scanner sin =  new Scanner(sock.getInputStream());
            PrintStream sout = new PrintStream(sock.getOutputStream());
            while(sock.isConnected()){//
                //as long as connected - taking data and sending it back
                sout.print("Welcome to the Echo Server!\r\n");
                String line = sin.nextLine();
                sout.print(line+"\r\n");
                System.out.println(sock.getInetAddress().getHostAddress()+": "+ line);
                if(line.equalsIgnoreCase("EXIT")){
                        sock.close();
                }
            }
        }catch(IOException e){}
    }
}