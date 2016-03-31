/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs391spring2015threads2;

import static cs391spring2015threads2.Buffer.MAX_SIZE;

/**
 *
 * @author dkatz
 */
public class CS391Spring2015Threads2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Buffer b = new Buffer();
        for (int i=0; i<26; i++){
            new Producer((char)('A'+i),b).start();
            new Consumer(b).start();
        }*/
        Object o=new Object();
        for (int i=0; i<5; i++)
            new PrintToScreen(i).start();
        //synchronized(o){
            
        //}
    }
    
}

class PrintToScreen extends Thread{
    int id;
    PrintToScreen(int i){id = i;}
    
    public void run(){
        for (int i=0; i<1000; i++)
            printLine(id);
    }
    public static synchronized void printLine(int id){
        for (int i=0;i<id; i++)
            System.out.print("\t");
        System.out.println(id);
    }
    
}

class Buffer{
    char[] data;
    int count;
    public static final int MAX_SIZE=10;
    Buffer(){
        data=new char[MAX_SIZE];
        count=0;
    }
    int getSize(){return count;}
    synchronized void addToBuffer(char c){
        Thread.currentThread().yield();
        if (count>=MAX_SIZE)
            return;
        data[count]=c;
        count++;
    }
    
    synchronized char getFromBuffer(){
        Thread.currentThread().yield();
        if (count<=0)
            return '!';
        char retval;
        count--;
        retval=data[count];
        return retval;
 
    }
}

class Producer extends Thread{
    char id;
    Buffer buf;
    Producer(char newid,Buffer newbuf){
        id=newid;
        buf = newbuf;
    }
    public void run(){
        try{
            while(true){
                while(buf.getSize()>=Buffer.MAX_SIZE || buf.getSize()<0)
                    try{sleep(200);}catch(Exception e){}
                System.out.println("Adding "+id+" to the buffer size is: "+buf.getSize());
                buf.addToBuffer(id);
                yield();
            }
        }
        catch(Exception e){
            System.out.println("Exception caught in thread "+id);
            System.out.println("Exception was: "+e.toString());
            System.exit(1);
        }
    }
}

class Consumer extends Thread{
    Buffer buf;
    Consumer(Buffer newbuf){
        buf = newbuf;
    }
    public void run(){
        try{
            while(true){
                while(buf.getSize()<=0 || buf.getSize()>MAX_SIZE)
                    try{sleep(200);}catch(Exception e){}
                System.out.println("Got: "+buf.getFromBuffer()+" from the buffer, size is "+buf.getSize());
                yield();
                
            }
        }
        catch(Exception e){
            System.out.println("Exception caught in consumer thread ");
            System.out.println("Exception was: "+e.toString());
            System.exit(1);
        }
    }
}