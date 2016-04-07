/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package march32016internetstuff;
import java.util.*;
import java.net.*;
import java.io.*;
/**
 *
 * @author An
 */
//Sockets
public class March32016InternetStuff {
    static int number;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /* NOTES
        try{
            Socket s = new Socket("www.google.com",80);
            Scanner sin = new Scanner(s.getInputStream());
            PrintStream ps = new PrintStream(s.getOutputStream());
            //upon connection, client will send request
            //GET HAS TO BE CAPITALSSS
            ps.printf("GET / HTTP/1.0\r\n\r\n");
            //tell server who it's from, to, the name
            //i expect server will respond appropriately
            while(sin.hasNext())
                System.out.println(sin.nextLine());
        }catch(IOException e){
            System.out.println("Connection to google failed :(");
        }*/
        /*
            I.P 172.16.11.145
            lab port 1234
            upon connection, given random number printed after "200 n\r\n"
            add 124-14+7*622-100 to that number
            send back that number seperated by a space
            give N number (without the N) answer \r\n
            so "14433367 # \r\n"
        */
        try{
            Socket s = new Socket("172.16.11.145",1234);
            Scanner sin = new Scanner(s.getInputStream());
            PrintStream ps = new PrintStream(s.getOutputStream());
            //upon connection, client will send request
            //GET HAS TO BE CAPITALSSS
            //ps.printf("GET / HTTP/1.0\r\n\r\n");
            //tell server who it's from, to, the name
            //i expect server will respond appropriately
            //System.out.print(sin.nextLine());
//            while(sin.hasNextInt()){
                number = sin.nextInt();
                number = sin.nextInt();
                //System.out.println(number);
//            }
            System.out.println(number);
            int response = number+127-14+7*622-100;
            System.out.println(response);
            ps.print(response+" 14433367 \r\n");
//            while(sin.hasNextLine())
           System.out.println(sin.nextLine());
            
   
        }catch(IOException e){
            System.out.println("Connection to google failed :(");
        }
        
    }
    
}
