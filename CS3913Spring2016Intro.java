/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3913.spring2016.intro;
import java.io.File;
import java.util.*;
import java.io.*;
/**
 *
 * @author dkatz
 */
public class CS3913Spring2016Intro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        System.out.println("Hello world!");
        int i;
        double d;
        float f;
        boolean b;
        char c;
        i=5;
        d = 100d; //d=100. or d=1e3
        i= (int) d;
        
        if (i==0)
            System.out.println("Hello world 2!");
        System.out.println("sqrt(25)="+Math.sqrt(25));
        String s = "Daniel Katz";
        s=new String("John Doe");
        Scanner sin=new Scanner(System.in);
        String s2=null;
        System.out.println(s2);
        s = "First";
        s2="Second";
        swap(s,s2);
        System.out.println("S="+s);
        System.out.println("S2="+s2);
        System.out.println("**********************");
        /*
        System.out.println("What is your name? ");
        String name;
        name = sin.nextLine();
        System.out.println("What is your favorite color? ");
        String favoriteColor = sin.nextLine();
        System.out.println("What is the average airspeed of a swallow? ");
        double speed;
        speed= sin.nextDouble();
        if (speed!=25)
            System.out.println("I'm sorry, you're wrong.");
        else
            System.out.println("Correct!");
        */
        int [] arr = new int[100];
        for (int j=0; j<50; j++)
            arr[j]=j;
        System.out.println("There are "+arr.length+ " items in the array");
        System.out.println("The string S has "+s.length()+" characters.");
        
        ArrayList<Integer> al=new ArrayList<Integer>();
        for (int j=0; j<50; j++)
            al.add(j);
        
        System.out.println("The ArrayList contains: "+al.size()+" elements");
        //for (int j=0; j<al.size(); j++)
        //    System.out.println(al.get(j));
        for (int element:al)
            System.out.println(element);
        
        
        PrintStream outFile = new PrintStream("outfile.txt");
        for (int j=0; j<100; j++)
            outFile.println(j);
        
        
        String filename;
        System.out.print("What filename? ");
        filename=sin.nextLine();
        File myFile = new File(filename);
        while (!myFile.canRead()){
            System.out.println("Bad filename");
            System.out.println("What filename? ");
            filename=sin.nextLine();
            myFile = new File(filename);
        }
        
        Scanner inFile = new Scanner(myFile);
        int temp;
        while (inFile.hasNextInt()){
            temp = inFile.nextInt();
            System.out.println("Got: "+temp+ " from the file");
        }
        
        
    }
    public static void swap(String s1, String s2){
        String temp = s1;
        s1=s2;
        s2=temp;
    }
    
}
