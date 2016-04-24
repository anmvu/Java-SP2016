/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clock;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author An
 */
public class Clock {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame jf = new JFrame("Clock");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400,400);
        GraphicsPanel c = new GraphicsPanel();
        new UpdatePanel(c).start();
        jf.add(c);
        jf.setVisible(true);
        
    }    
}

class UpdatePanel extends Thread{
    JPanel jp;
    UpdatePanel(JPanel newjp){jp = newjp;}
    public void run(){
        try{
            while(true){
                jp.repaint();
                sleep(100000);
            }
        }catch(Exception e){}
    }
}

class Hand{
    int x;
    int y;
    int angle;
    int time;
}

class GraphicsPanel extends JPanel{
    int i;
    int hours;
    int minutes;
    int seconds;
    Hand hour;
    Hand minute;
    Hand second;
    GraphicsPanel(){
        i = 0;
        hours = 0;
        minutes = 0;
        seconds = 0;
        int dist = 100;
        for(seconds = 90; seconds < 450; seconds += 60){
            
            if(seconds == 90){
                seconds = 0;
                minutes++;
                if(minutes == 60){
                    minutes = 0;
                    hours++;
                    if(hours == 12){
                        hours = 0;
                    }
                }
            }
            minute = new Hand(); 
            minute.x = (int)(dist*Math.cos(Math.toRadians(minutes*6)));
            minute.y = (int)(dist*Math.sin(Math.toRadians(minutes*6)));
            hour = new Hand();
            hour.x = (int)(dist*Math.cos(Math.toRadians(hours*30)));
            hour.y = (int)(dist*Math.cos(Math.toRadians(hours*30)));
            second = new Hand();
            second.x = (int)(dist*Math.cos(Math.toRadians(seconds)));
            second.y = (int)(dist*Math.sin(Math.toRadians(seconds)));
        }
    }
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        double height = getSize().getHeight();
        double width = getSize().getWidth();
        int middleWidth = (int)width/2;
        int middleHeight = (int)height/2;
        int boxSize = 200;
        g.drawRect(Hour.)
    }
}