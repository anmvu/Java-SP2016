/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3913.spring2016.threads;

/**
 *
 * @author dkatz
*/
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class CS3913Spring2016Threads {
    static int i;
    static Boring b;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Counter c = new Counter();
        b = new Boring(c);
        c.start();
        new MyTimer().start();
        JFrame jf = new JFrame("The counter");
        jf.setSize(400,400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(b);
        jf.setVisible(true);
    }
}
class MyTimer extends Thread{
    long start;
    MyTimer(){start = System.currentTimeMillis();}
    void reset(){start=System.currentTimeMillis();}
    public void run(){
        while(true){
            long now = System.currentTimeMillis();
            int thisi=(int)((now-start)/1000);
            CS3913Spring2016Threads.b.sysTime.setText(""+thisi);
            int diff = thisi-CS3913Spring2016Threads.i;
            CS3913Spring2016Threads.b.diff.setText(""+diff);
        }
    }
}

class Counter extends Thread{
    
    boolean running;
    Counter(){running=true; CS3913Spring2016Threads.i=0;}
    void toggleCounting(){
        if (running)
            running=false;
        else
            running=true;
    }
    public void run(){
        while (true){
            //System.out.println("Run status is: "+running);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {}
            if (running){
                CS3913Spring2016Threads.i++;
                CS3913Spring2016Threads.b.counter.setText(""+CS3913Spring2016Threads.i);
                CS3913Spring2016Threads.b.stopButton.setText("Stop");
            }
            else
                CS3913Spring2016Threads.b.stopButton.setText("Go");
            
        }
    }
}