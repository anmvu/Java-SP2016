/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg22516notes;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author webteam
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    /*
    A class can only have 1 class
    A class can have abstract class
    no multiple inheritance
    can extend one class
    can implement as many classes as you like
    */
    
    static Boring b;
    
    public static void main(String[] args) {
        // interface and inheritance
        Car c = new ar();
        Refuelable r = c;
        Vehicle v = c;
        Car c2;
        c2 = (Car) v;
        v = new Vehicle();
        //vehicle has a lot of functions because of object - which can be done in any object that exists in java
        //only primitives don't have access to these functions    

        //THREADS
        //any interactive is a thread apparently
        //can't extend class and thread
        //can have interface runnable - to be constructred
        //thread constructor pass a runnable object
        //Thread.getCurrentThread- reference to current thread you're in
        //yield -- releases CPU - I don't want to run right now - run another
            //looks for other threads that wants to run - in this case, this thread will go right back on - might be no delay
        //sleep -- puts thread to sleep for some number of ms
            //not acceptable to use as a long time timer
            //timer isn't realtime based
            //throws thread interruption exception
        Counter count = new Counter();
        c.start();//start creates a new thread- calls run in a seperate thread
        b = new Boring();
        JFrame jf = new JFrame("The counter");
        jf.setSize(400,400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(b);
        jf.setVisible(true);

    }
    
}
//interface and inheritance
interface Refuelable{
    public void putGasInIt();
}

class Vehical{
    int numOfPassengers;
    Vehical(){this(1);}
    Vehical(int pass){numOfPassengers = pass;}
}
class Car extends Vehical implements Refuelable,Cloneable{
    //constructor
    Car(){
        super(5);//needs to be the first line
        //super constructor (super)
        //implement cloneable -> super.cloneable
    }

    @Override
    public void putGasInIt(){//has to be here cause Refuelable has it
        throw new UnsupportedOperationException();
    }
}
class ElectricCar extends Vehical{}
class MotorCycle extends Vehical implements Refuelable{
    @Override
    public void putGasInIt(){
        throw new UnsupportedOperationException();
    }
}
class Chainsaw implements Refuelable{
    @Override
    public void putGasInIt(){
        throw new UnsupportedOperationException();
    }
}

class GasStation{
    void addGasToIt(Refuelable thing){
        thing.putGasInIt();
    }
}

//THREADS
//create anoter code to run parallel to whatever is happening to the system
//completely seperate program but inside the original program
//every second, button would update color automatically
//pressing button disables automatic update (every odd number of times)

class MyTime extends Thread{
    //extends SystemTime

}

class Counter extends Thread{
    int i;
    boolean running;
    Counter(){running=true; i=0;}
    void toggleCounting(){
        if(running)running=false;
        else
            running = true;
    }
    public void run(){//only function that matters
        while(true){//we're not in main anymore
            if(running){
                i++;
                Main.b.counter.setText(""+i);
                Main.b.stopButton.setText("Stop");
            }
            else{
                Main.b.stopButton.setText("Go");
            }
            try{
                sleep(1000)
            } catch{}
        }
    }
}

//made through creating interactiv JPanel design view
public class Boring extends javax.swing.Jpanel{
    Counter c;
    public BOring(Counter newc){
        initComponents();
        c = newc;
    }
}

c.toggleCounting();

//end interactive JPanel