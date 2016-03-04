/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buttoncounter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author webteam
 */
public class ButtonCounter {

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
    public static void main(String[] args){
            i = 0;
            upPresses = 0;
            downPresses = 0;
            jf = new JFrame("My counter");
            jf.setSize(600,600);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setResizable(false);
            jp = new JPanel();
            jf.add(jp);
            jp.setLayout(new BorderLayout());
            top = new JPanel();
            bottom = new JPanel();
            jp.add(top,BorderLayout.NORTH);
            jp.add(bottom,BorderLayout.SOUTH);
            bottom.setLayout(new GridLayout(4,3));
            for (int j=0; j < 4; j++)
                bottom.add(new JLabel());
            upButton = new JButton("UP");
            bottom.add(upButton);
            upButton.addActionListener(new ButtonPress());
            for (int j=0; j < 5; j++)
                bottom.add(new JLabel());
            downButton = new JButton("DOWN");
            bottom.add(downButton);
            downButton.addActionListener(new ButtonPress());
            iLabel = new JLabel("i="+i);
            upLabel = new JLabel("UP presses = "+upPresses);
            downLabel = new JLabel("DOWN presses = "+downPresses);
            totalLabel = new JLabel("TOTAL presses = "+(upPresses+downPresses));
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
            
            jf.setVisible(true);
    }
    static class ButtonPress implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent ae){
                if (ae.getSource() == upButton){
                    i++;
                    upPresses++;
                    iLabel.setText("i = "+ i);
                    upLabel.setText("UP presses = "+upPresses);
                    downLabel.setText("DOWN presses = "+ downPresses);
                    totalLabel.setText("TOTAL presses = "+(upPresses + downPresses));
                }
                else{
                    i--;
                    downPresses++;
                    iLabel.setText("i = "+ i);
                    upLabel.setText("UP presses = "+upPresses);
                    downLabel.setText("DOWN presses = "+ downPresses);
                    totalLabel.setText("TOTAL presses = "+(upPresses + downPresses));
                }
            }
    }
    
}
