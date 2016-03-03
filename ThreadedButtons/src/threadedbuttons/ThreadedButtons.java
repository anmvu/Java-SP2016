/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadedbuttons;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 *
 * An Vu
 * Modify HW2 so that the buttons now change automatically every 1 second. 
 * if a button is pressed, it hold its color until pressed again.  
 * When a button is on hold, the other buttons should continue to change at their regular interval.
 */
public class ThreadedButtons {

    /**
     * @param args the command line arguments
     */
    static int num_button = 12;
    static JFrame jf;
    static JPanel jp;
    static ArrayList<JButton> buttons;
    static ArrayList<JButton> pressed;
    static Random rand;
    
    public static void createButtons(){
        rand = new Random();
        for(int i = 0; i < num_button; i++){
            JButton butt = new JButton();
            butt.addActionListener(new ColorChange());
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            Color random = new Color(r,g,b);
            butt.setBackground(random);
            buttons.add(butt);
        }
    }
    
    public static void createPanel(){
        jp.setLayout(new GridLayout(3,4));
        for(int i = 0; i < buttons.size(); i++){
            jp.add(buttons.get(i));
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        //mac stuff
        /*try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch(Exception e){
            e.printStackTrace();
        }*/
        buttons = new ArrayList<JButton>();
        pressed = new ArrayList<JButton>();
        jf = new JFrame("Change Colors all the times");
        jf.setSize(600,600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jp = new JPanel();
        createButtons();
        createPanel();
        jf.add(jp);
        ColorChange test = new ColorChange();
        test.start();
        jf.setVisible(true);
    }
    
    static class ColorChange extends Thread implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton bx = (JButton)e.getSource();
            if(buttons.remove(bx)){
                pressed.add(bx);
            }
            else if (pressed.remove(bx)){
                buttons.add(bx);
            }
        }
        
        public void run(){
            while(true){
                for(int i = 0; i < buttons.size(); i++){
                    int r = rand.nextInt(256);
                    int g = rand.nextInt(256);
                    int b = rand.nextInt(256);
                    Color random = new Color(r,g,b);
                    buttons.get(i).setBackground(random);
                }  
            }
        }
    }
}