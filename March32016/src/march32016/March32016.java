/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package march32016;
import java.util.*;
import java.io.*;
/**
 *
 * @author An
 */
public class March32016 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for (int i = 0; i < 5; i++) new Printer(i).start();
        
    }
    
    class Supplier extends Thread{
	Buffer theBuff;
	Supplier(Buffer newBuff){theBuff = newBuff;}

	public void run(){
		while(true){
			
		}
	}
}

class Buffer{
	final int MAX = 100;
	int count;
	Buffer() {count = 0;}

	synchronized void addToBuffer(){//goes to sleep with the lock still enabled -- DEADLOCK
            //can't hold lock and sleep
            //if have a lock, do NOT SLEEP -- will NEVER unlock
            
		if (count >= MAX){
//			try{
//				Thread.currentThread().sleep(100);
//			} catch(InterruptedException ex){}
                    return;
		}
                Thread.currentThread().yield();
		int newCount = count+1;
		count = newCount;
                if (count < 0 || count > MAX){
//			try{
                            System.out.println("takeFromBuffer found buffer Count = " + count);
                            //Thread.currentThread().sleep(10000000);
//                        }catch(InterruptedException ex){}
		}
	}

	synchronized void  takeFromBuffer(){
		if (count <= 0 ){
//			try{
//				Thread.currentThread().sleep(100);
//			} catch(InterruptedException ex){}
                    return;
		}
		int newCount = count-1;
		count = newCount;
		if (count < 0 || count > MAX){
//			try{
                            System.out.println("takeFromBuffer found buffer Count = " + count);
//                            Thread.currentThread().sleep(10000000);
//                        }catch(InterruptedException ex){}
		}
	}
}


class Printer extends Thread{
	int i;
	// Object sem;
	// Printer(int newi, Object newSem)(i = newi; sem = newSem;)
	//.. I guess we don't use the built in semphores

	public void run(){
		for(int j = 0; j <300; j++) printLine(i);
	}

	//if static - then synchronized needs to happen on class object not on the instance
	public synchronized void printLine(int tabs){
		// try{
		// 	sem.wait();
		// }catch(InterruptedException ex){}

		for(int i = 0; i < tabs; i++){
			System.out.print("\t");
		}
		// System.out.println(tabs);
		// sem.notify();
	}

	//every object in Java has a built-in semaphore
}
}


/*
MIDTERM
CH 1 - 14

data types, loops, branches, arrays
oop and exceptions
windowing, extending classes
interfaces and nested classes
threads

*/