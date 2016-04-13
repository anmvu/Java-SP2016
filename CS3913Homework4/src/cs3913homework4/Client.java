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
import cs3913homework4.Server;
/**
 *
 * @author Annie Vuvu
 */
public class Client {

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
            ServerSocket ss = Server.ss;
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


