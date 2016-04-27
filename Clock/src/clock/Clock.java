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
        jf.setResizable(false);
        double width = jf.getSize().getWidth();
        double height = jf.getSize().getHeight();
        GraphicsPanel c = new GraphicsPanel(width,height);
        new UpdatePanel(c).start();
        jf.add(c);
        jf.setVisible(true);
        
    }    
}

class UpdatePanel extends Thread{
    GraphicsPanel jp;
    UpdatePanel(GraphicsPanel newjp){jp = newjp;}
    @Override
    public void run(){
        try{
            while(true){
                jp.moveAngle();
                sleep(100);
            }
        }catch(Exception e){}
    }
}

class Hand{
    int x;
    int y;
}

class GraphicsPanel extends JPanel{
    int hour;
    int minute;
    int second;
    double length;
    static double width;
    static double height;
    Hand[] hours;
    Hand[] minutes;
    Hand[] seconds;
    GraphicsPanel(double newWidth, double newHeight){
        hour = 0;
        minute = 0;
        second = 0;
        width = newWidth;
        height = newHeight;
        hours = new Hand[24];
        minutes = new Hand[60];
        seconds = new Hand[600];
        int j = 0;
        int k = 0;
        int l = 0;
        int length = (int)height;
        if (width < height) length = (int)width;
        for(double i = 360; i > 0; i -= 0.6){
            Hand sec = new Hand();
            sec.x = (int)(length*0.45*Math.sin(Math.toRadians(i+180)));
            sec.y = (int)(length*0.45*Math.cos(Math.toRadians(i+180)));
            seconds[j++] = sec;
        }
        for(int i = 360; i > 0; i -= 6){
            Hand min = new Hand(); 
            min.x = (int)(length*0.4*Math.sin(Math.toRadians(i+180)));
            min.y = (int)(length*0.4*Math.cos(Math.toRadians(i+180)));
            minutes[k++] = min;
        }
        for (int i = 360; i > 0; i -= 15){
            Hand h = new Hand();
            h.x = (int)(length*0.3*Math.sin(Math.toRadians(i+180)));
            h.y = (int)(length*0.3*Math.cos(Math.toRadians(i+180)));
            hours[l++] = h;
        }
        
    }
    
    void moveAngle(){
        if(second == 599){
            second = 0;
            minute++;
            if((minute+1) % 30 == 0){
                hour++;
                if(minute == 59) minute = 0;
            }
            if(hour == 23) hour = 0;
        }
        else second++;
        
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int boxSize;
        width = getSize().getWidth();
        height = getSize().getHeight();
        if (width < height) boxSize = (int)width;
        else boxSize = (int)height;
        int middleWidth = (int)width/2;
        int middleHeight = (int)height/2;
        
        g.setColor(Color.white);
        g.fillOval(middleWidth-boxSize/2,middleHeight-boxSize/2, boxSize, boxSize);
       
        
        Hand hour2 = new Hand();
        Hand minute2 = new Hand();
        Hand second2 = new Hand();
        hour2.x = hours[hour].x + middleWidth;
        hour2.y = hours[hour].y + middleHeight;
        minute2.x = minutes[minute].x + middleWidth;
        minute2.y = minutes[minute].y + middleHeight;
        second2.x = seconds[second].x + middleWidth;
        second2.y = seconds[second].y + middleHeight;
        g.setColor(Color.green);
        g.drawLine(hour2.x,hour2.y,middleWidth, middleHeight);
        g.setColor(Color.red);
        g.drawLine(minute2.x,minute2.y,middleWidth, middleHeight);
        g.setColor(Color.blue);
        g.drawLine (second2.x,second2.y,middleWidth, middleHeight);
        
        g.setColor(Color.black);
        int h = 0;
        for(int i = 0; i < 360; i += 30){
            String time = Integer.toString(h);
            if(h == 0) time = "12";
            h++;
            int x =(int) (boxSize*0.4*Math.cos(Math.toRadians(i-90)) + middleWidth-4);
            int y = (int)(boxSize*0.4*Math.sin(Math.toRadians(i-90)) + middleHeight+5);
            g.drawString(time,x ,y );
        }
   
    }
}