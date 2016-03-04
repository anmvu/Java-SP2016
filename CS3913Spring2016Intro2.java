/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3913.spring2016.intro2;
import java.util.*;
import java.io.*;
/**
 *
 * @author dkatz
 */
public class CS3913Spring2016Intro2 {
    static ArrayList<Integer> theInts;
    /**
     * @param args the command line arguments
     */
    static String getFilename(){
        System.out.print("Please enter a filename: ");
        Scanner sin = new Scanner(System.in);
        String filename=sin.nextLine();
        while (!new File(filename).canRead()){
            System.out.println("Bad Filename");
            System.out.print("Please enter a filename: ");
            filename = sin.nextLine();
        }
        return filename;
    }
    static void fillTheInts()throws IOException{
        Scanner inFile = new Scanner(new File(getFilename()));
        while (inFile.hasNextInt()){
            int i=inFile.nextInt();
            theInts.add(i);
        }
            
    }
    static double getAverage(){
        double sum=0;
        for(int i:theInts)
            sum+=i;
        return sum/theInts.size();
    }
    static void printGreaterThan(double limit){
        for(int i:theInts)
            if (i>limit)
                System.out.println(i);
    }
    public static void main(String[] args) throws IOException{
        theInts=new ArrayList<Integer>();
        fillTheInts();
        double average = getAverage();
        printGreaterThan(average);
    }
    
}
