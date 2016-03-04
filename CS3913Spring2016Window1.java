/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3913.spring2016.window1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author dkatz
 */
public class CS3913Spring2016Window1 {

    /**
     * @param args the command line arguments
     */
    static JFrame jf;
    static JPanel jp;
    static JButton firstButton;
    static JButton secondButton;
    static JPanel middle;
    public static void main(String[] args) {
        jf = new JFrame("Some boring title");
        jf.setSize(400, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        
        firstButton = new JButton("Make it BLUE");
        firstButton.setBackground(Color.BLUE);
        firstButton.addActionListener(new ButtonPress());
        secondButton = new JButton("Make it PINK");
        secondButton.setBackground(Color.pink);
        secondButton.addActionListener(new ButtonPress());
        /*secondButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                middle.setBackground(Color.PINK);
            }
        });*/
        jp = new JPanel();
        middle = new JPanel();
        middle.setBackground(Color.yellow);
        jp.setLayout(new BorderLayout());
        jf.add(jp);
        jp.add(firstButton,BorderLayout.NORTH);
        jp.add(secondButton,BorderLayout.SOUTH);
        jp.add(middle,BorderLayout.CENTER);
        jf.setVisible(true);
        /*SomeClass sc = new SomeClass();
        System.out.println(sc.SomeFunc());
        
        System.out.println(new SomeClass().SomeFunc());
        
        System.out.println(new SomeClass(){
            String SomeFunc(){
                return "Some other Func's string!!!!";
            }
        }.SomeFunc());*/
    }
    


    static class ButtonPress implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            if (ae.getSource()==firstButton)
                middle.setBackground(Color.BLUE);
            else
                middle.setBackground(Color.PINK);
        }    
    }
}
class SomeClass{
    String SomeFunc(){
        return "SomeFunc's String!";
    }
}
