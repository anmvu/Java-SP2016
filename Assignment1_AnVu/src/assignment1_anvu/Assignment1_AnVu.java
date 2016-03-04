/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1_anvu;
import java.util.*;
import java.io.*;
       
/**
 * Assignment 1
 * @author An Vu
 * Make weird Caesar cipher
 * ask for encrypt/decrypt
 * ask for the key - convert to all lowercase or uppercase
 * ask for input and output filenames
 * print to output file the encoded or decoded file
 */
public class Assignment1_AnVu {
    /**
     * @param args the command line arguments
     */
    
    //A = 65
    //Z = 90
    
    static File getFilename(){
        System.out.print("Enter your input flename: ");
        Scanner sin = new Scanner(System.in);
        String filename = sin.nextLine();
        while(!new File(filename).canRead()){
            System.out.println("Bad Filename");
            System.out.println("Please enter your input filename: ");
            filename = sin.nextLine();
        }
        File myFile = new File(filename);
        return myFile;
    }
    
    static String getOutputFile(){
        System.out.print("Enter your output flename: ");
        Scanner sin = new Scanner(System.in);
        String filename = sin.nextLine();
        while(!filename.contains(".txt")){
            System.out.println("Bad Filename (does not contain .txt)");
            System.out.println("Please enter your output filename: ");
            filename = sin.nextLine();
        }
        return filename;
    }
    
    static int getJob(){
        System.out.println("What would you like to do?");
        System.out.println("1 - Encrypt a file");
        System.out.println("2 - Decrypt a file");
        System.out.println("3 - Exit");
        System.out.print("Your choice: ");
        Scanner sin = new Scanner(System.in);
        int job = sin.nextInt();
        while(job < 1 || job > 3){
            System.out.println("That ain't a choice!");
            System.out.print("Your choice (1, 2, 3): ");
            job = sin.nextInt();
        }
        return job;
    }
    
    static String getKey(){
        System.out.print("Enter your key: ");
        Scanner sin = new Scanner(System.in);
        String key=sin.nextLine();
        return key;
    }
    
    //based off of the difference between string lengths, the key length would be extended
    static String extendKey(int extra, String key){
        String extended = key;
        while (extra > 0){
            if (extra >= key.length()){
                extended += key;
                extra -= key.length();
            }
            else{
                extended += key.substring(0,extra);
                extra -= extra;
            }
        }
        return extended;
    }
    
    /*
    static int countChars(String line){
        int charCount = 0;
        for (int i = 0, n = line.length(); i < n; i++){
            char c = line.charAt(i);
            if (Character.isLetter(c))
                charCount++;
        }
        return charCount;
    }*/
    
  
    static String matchCase(String original, String output){
        StringBuilder send_out = new StringBuilder();
        System.out.println(original.length());
        System.out.println(output.length());
        for (int i = 0, n = original.length(); i < n;i++){
            char orig = original.charAt(i);
            char comp = output.charAt(i);
            char change;
            if (Character.isLetter(comp)){
                if(Character.isLowerCase(orig)){
                    change = Character.toLowerCase(comp);
                }
                else{change = comp;}
                send_out.append(change);
            }
            else{send_out.append (comp);}
        }
        return send_out.toString();
    }
    
    static void writeToFile(String output, String output_file)throws IOException{
        PrintStream outFile = new PrintStream(output_file);
        outFile.println(output);
    }
    
    static char changeChar(char original,char key,boolean encrypt){
        int changed_int;
        char changed;
        System.out.println(((char)original )+": " +( (char)key));
        System.out.println(((int)original )+": " +( (int)key));
        if (encrypt == true){
            changed_int = original + key;
        }
        else{
            changed_int = original - key;
        }
        System.out.println(changed_int);
        while(changed_int > 90 || changed_int < 65){
            if (changed_int >=156){
                changed_int -= 91;
            }
            else if(changed_int < 156  && changed_int > 90){
                 changed_int -= 65;
            }
            else if(changed_int <65 && changed_int > 0){
                changed_int += 65;
            }
            else{
                changed_int += 91;
            }
        }
        changed = (char)changed_int;
        System.out.println(changed_int +": "+changed);
        return changed;
    }
    
    static void encrypt(File input, String output, String key)throws IOException{
        Scanner inFile = new Scanner (input);
        while(inFile.hasNextLine()){
            String line = inFile.nextLine();
            String upper = line.toUpperCase();
            String upper_key = key.toUpperCase();
            //String output_line;
                
            if (key.length() < upper.length()){
                int charCount = upper.length() - key.length();
                upper_key = extendKey(charCount,upper_key);
            }
            String output_line = "";
            for (int i = 0, n = line.length(); i < n;i++){
                char c = upper.charAt(i);
                char out_char;
                if (Character.isLetter(c)){
                    out_char = changeChar(c, upper_key.charAt(i), true);
                }
                else{out_char = upper.charAt(i);}
                output_line += out_char;
                
            }
            String final_out = matchCase(line,output_line);
            writeToFile(final_out,output);
        }
    }
    
    static void decrypt(File input, String output, String key)throws IOException{
        Scanner inFile = new Scanner (input);
        while(inFile.hasNextLine()){
            String line = inFile.nextLine();
            String upper = line.toUpperCase();
            String upper_key = key.toUpperCase();
            //String output_line;
                
            if (key.length() < upper.length()){
                int charCount = upper.length() - key.length();
                upper_key = extendKey(charCount,upper_key);
            }
            String output_line = "";
            for (int i = 0, n = line.length(); i < n;i++){
                char c = upper.charAt(i);
                char out_char;
                if (Character.isLetter(c)){
                    out_char = changeChar(c, upper_key.charAt(i), false);
                }
                else{out_char = upper.charAt(i);}
                output_line += out_char;
                
            }
            String final_out = matchCase(line,output_line);
            writeToFile(final_out,output);
        }
    }
    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        int job = getJob();
        while (job < 3){
            String key = getKey();
            File input = getFilename();
            String output = getOutputFile();
            if (job == 1){
                encrypt(input,output,key);
            }
            else{
                decrypt(input,output,key);
            }
            job = getJob();
        }  
            System.exit(0);
    }
    
}
