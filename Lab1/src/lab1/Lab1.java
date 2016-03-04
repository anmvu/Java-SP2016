/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;
import java.util.*;
import java.io.*;
/**
 *
 * @author An
 */
public class Lab1 {
    //ask for encrypt/decrypt
    //ask for the key - convert to all lowercase or uppercase
    //ask for input and output filenames
    //print to output file the encoded or decoded file
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
    
    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        String function;
        Scanner sin = new Scanner(System.in);
        System.out.println("Would you like to encrypt/decrypt? (e or d)");
        function = sin.nextLine();
        System.out.println("Please enter input filname");
        Scanner input = new Scanner(new File(getFilename()));
    }
    
}
