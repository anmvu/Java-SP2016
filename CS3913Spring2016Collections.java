/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3913spring2016collections;
import java.util.*;
/**
 *
 * @author dkatz
 */
public class CS3913Spring2016Collections {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random r = new Random();
        ArrayList al = new ArrayList();
        al.add(r.nextInt(1000));
        al.add(r.nextInt(1000));
        al.add(r.nextInt(1000));
        al.add(r.nextInt(1000));
        al.add(r.nextInt(1000));
        
        for (int i=0; i<al.size(); i++){
            Integer a = (Integer)al.get(i);
            //System.out.println(a);
        }
            
        //ArrayList<Integer> al2 = new ArrayList();
        //for (int i=0; i<5; i++)
        //    al2.add(r.nextInt(1000));
       
        //for(Integer i: al2)
        //    System.out.println(i);
        TreeSet<Integer> ts = new TreeSet();
        for (int i=0; i<10; i++)
            ts.add(r.nextInt(1000));
        
        for (Integer i: ts)
            System.out.println(i);
        
        TreeMap<String, Integer> tm= new TreeMap();
        tm.put("Daniel", null);
        
    }
    
}
