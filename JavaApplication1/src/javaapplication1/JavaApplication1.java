/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.*;
import java.io.*;
/**
 *
 * @author An
 */
public class JavaApplication1 {
    static ArrayList<Integer> theInts;
    /**
     * @param args the command line arguments
     */
    
    static String getFilename(){
        System.out.print("Please enter a filename: ");
        Scanner sin = new Scanner(System.in);
        String filename = sin.nextLine();
        while(!new File(filename).canRead()){
            System.out.println("Bad Filename");
            System.out.println("Please enter a filename: ");
            filename = sin.nextLine();
        }
        return filename;
    }
    
    static void fillTheInts() throws IOException{
        Scanner inFile = new Scanner(new File(getFilename()));
        while(inFile.hasNextInt())
            theInts.add(inFile.nextInt());
    }
    
    static double getAverage(){
        double sum = 0;
        for(int i:theInts)
            sum +=i;
        return sum/theInts.size();
    }
    
    static void printGreaterThan(double limit){
        for(int i:theInts)
            if(i > limit)
                System.out.println(i);
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        theInts = new ArrayList<Integer>();
        fillTheInts();
        double average = getAverage();
        printGreaterThan(average);
    }
    
}
