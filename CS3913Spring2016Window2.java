/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs3913.spring2016.window2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author dkatz
 */
public class CS3913Spring2016Window2 {

    /**
     * @param args the command line arguments
     */
    static int i;
    static int upPresses;
    static int downPresses;
    static JFrame jf;
    static JPanel jp;
    static JPanel top;
    static JPanel bottom;
    static JButton upButton;
    static JButton downButton;
    static JLabel iLabel;
    static JLabel upLabel;
    static JLabel downLabel;
    static JLabel totalLabel;
    public static void main(String[] args) {
        i=0;
        upPresses=0;
        downPresses=0;
        jf = new JFrame("My counter");
        jf.setSize(600,600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jp = new JPanel();
        jf.add(jp);
        jp.setLayout(new BorderLayout());
        top=new JPanel();
        bottom = new JPanel();
        jp.add(top,BorderLayout.NORTH);
        jp.add(bottom,BorderLayout.SOUTH);
        bottom.setLayout(new GridLayout(4,3));
        for(int j=0; j<4; j++)
            bottom.add(new JLabel());//fill blank space
        upButton =new JButton("UP");
        bottom.add(upButton);
        for(int j=0; j<5; j++)
            bottom.add(new JLabel());//fill blank space
        downButton =new JButton("DOWN");
        bottom.add(downButton);
        
        iLabel =new JLabel("i="+i);
        upLabel =new JLabel("UP presses="+upPresses);
        downLabel =new JLabel("DOWN presses="+downPresses);
        totalLabel =new JLabel("Total Presses="+ (upPresses+downPresses));
        top.setLayout(new GridLayout(4,3));
        top.add(new JLabel());
        top.add(iLabel);
        top.add(new JLabel());
        top.add(new JLabel());
        top.add(upLabel);
        top.add(new JLabel());
        top.add(new JLabel());
        top.add(downLabel);
        top.add(new JLabel());
        top.add(new JLabel());
        top.add(totalLabel);
        upButton.addActionListener(new ButtonPress());
        downButton.addActionListener(new ButtonPress());
        jf.setVisible(true);
        
    }
    
    static class ButtonPress implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            if (ae.getSource()==upButton){
                i++;
                upPresses++;
                iLabel.setText("i="+i);
                upLabel.setText("UP presses="+upPresses);
                downLabel.setText("DOWN presses="+downPresses);       
                totalLabel.setText("Total Presses="+ (upPresses+downPresses));
            }
            else{
                i--;
                downPresses++;
                iLabel.setText("i="+i);
                upLabel.setText("UP presses="+upPresses);
                downLabel.setText("DOWN presses="+downPresses);       
                totalLabel.setText("Total Presses="+ (upPresses+downPresses));
            }
                
        }
    }
}
