/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3913.spring2016.classes;
import java.io.*;
import java.util.*;
import javax.swing.JFrame;
/**
 *
 * @author dkatz
 */
public class CS3913Spring2016Classes {

    /**
     * @param args the command line arguments
     */
    public static void exceptionDemo() throws BadDMRatio{
        try{
            double milk;
            int donuts;
            System.out.print("How much milk would you like? ");
            Scanner sin = new Scanner(System.in);
            milk = sin.nextDouble();
            if (milk<=0)
                throw new NoMilk();
            System.out.print("How many donuts would you like? ");
            donuts = sin.nextInt();
            if (donuts<=0)
                throw new NoDonuts();
            System.out.println("Your Donut to milk ratio is: "+(donuts/milk));
            if (donuts/milk <2)
                throw new BadDMRatio(milk,donuts);
            
        }
        catch(NoMilk m){
            System.out.println("What's your beef with milk?");
        }
        catch(NoDonuts d){
            System.out.println("Seriously?  Who doesn't like donuts????");
        }
        finally{
            System.out.println("FINALLY!!!!!");
        }
        
    }
    public static void main(String[] args) {
        
        
        try{
            exceptionDemo();
        }
        catch(BadDMRatio b){
            System.out.println("Your ratio isn't good");
        }
        System.exit(0);//DEBUG
        A a = new A();
        
        A a2 = new A(50);
        A a3 = a2;
        System.out.println("Values before the change:");
        System.out.println("A2: "+a2.getValues());
        System.out.println("A3: "+a3.getValues());
        a2.x=20;
        System.out.println("Values after the change:");
        System.out.println("A2: "+a2.getValues());
        System.out.println("A3: "+a3.getValues());
        
        a.display();
        a2.display();
        System.out.println("Y's value: "+A.y);
        System.out.println(A.sum(5,10,15,20));
    }
    
    
}

class NoMilk extends Throwable{}
class NoDonuts extends Throwable{}
class BadDMRatio extends Throwable{
    double milk;
    int donuts;
    BadDMRatio(double newmilk, int newdonuts){
        milk = newmilk;
        donuts=newdonuts;
    }
}

class A{
    int x;
    static double y;
    {
        System.out.println("Hello from the instance initializer!");
    }
    void func(int... params){
        System.out.println("Params array has: "+params.length+" members.");
        for (int i=0; i<params.length; i++)
            System.out.println(params[i]);
        //or...
        for (int p:params)
            System.out.println(p);
    }
    public static double sum(double... vars){
        double s=0;
        for (double v:vars)
            s+=v;
        return s;
    }
    void func2 (String s, int... vars){
        
    }
    A(){
        this(5); 
        y=100;
    }
    A(int newX){
        x=newX;
        y=100;
        System.out.println("Hello from the constructor!");
    }
    void display(){
        System.out.print("x="+x);
        System.out.print("y="+y);
    }
    String getValues(){
        return "x="+x+";y="+y;
    }
}
