/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colorchanger;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * An Vu
 * Assignment 2
 * All the buttons start at the same color
 * when one button is pressed, all the other buttons change colors
 */
public class ColorChanger {

    /**
     * @param args the command line arguments
     */
    static int num_button = 12;
    static JFrame jf;
    static JPanel jp;
    static JButton[] buttons;
    static Random rand;
    
    public static void createButtons(){
        buttons = new JButton[num_button];
        rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        Color random = new Color(r,g,b);
        for(int i = 0; i < num_button; i++){
            JButton butt = new JButton();
            butt.addActionListener(new ButtonPress());
            butt.setBackground(random);
            butt.setOpaque(true);
            buttons[i] = butt;
        }
    }
    
    public static void createPanel(){
        jp.setLayout(new GridLayout(3,4));
        for(int i = 0; i < num_button; i++){
            jp.add(buttons[i]);
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        jf = new JFrame("Change Other Colors");
        jf.setSize(600,600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jp = new JPanel();
        createButtons();
        createPanel();
        jf.add(jp);
        
        jf.setVisible(true);
    }
    
    static class ButtonPress implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton bx = (JButton)e.getSource();
            int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        Color random = new Color(r,g,b);
            for(int i = 0; i < num_button; i++){
                if (bx != buttons[i]){
                    buttons[i].setBackground(random);
                    buttons[i].setOpaque(true);
                }
            }
        }
        
    }
    
}
