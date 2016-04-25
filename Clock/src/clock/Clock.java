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
    JPanel jp;
    UpdatePanel(JPanel newjp){jp = newjp;}
    @Override
    public void run(){
        try{
            while(true){
                jp.repaint();
                //System.out.println("change");
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
    int i;
    int hours;
    int minutes;
    int seconds;
    double length;
    static double width;
    static double height;
    Hand hour;
    Hand minute;
    Hand second;
    GraphicsPanel(double newWidth, double newHeight){
        i = 0;
        hours = 0;
        minutes = 0;
        seconds = 0;
        width = newWidth;
        height = newHeight;
        int length;
        if (width < height) length = (int)width;
        else length = (int)height;
        minute = new Hand(); 
        minute.x = (int)(length*0.4*Math.sin(Math.toRadians(minutes)));
        minute.y = (int)(length*0.4*Math.cos(Math.toRadians(minutes)));
        hour = new Hand();
        hour.x = (int)(length*0.6*Math.sin(Math.toRadians(hours)));
        hour.y = (int)(length*0.6*Math.cos(Math.toRadians(hours)));
        second = new Hand();
        second.x = (int)(length*0.8*Math.sin(Math.toRadians(seconds)));
        second.y = (int)(length*0.8*Math.cos(Math.toRadians(seconds)));
        System.out.println("Seconds: " + seconds + " coords: " + second.x + ','+ second.y);
        System.out.println("Minutes: " + minutes + " coords: " + minute.x + ',' + minute.y);
        System.out.println("Hours: " + hours + " coords: " + hour.x + ',' + hour.y);  
        System.out.println("Width" + width);
        System.out.println("Height" + height);
    }
    
    void moveAngle(){
        if(hours == 360) hours = 0;
        else hours += 6;
        if(minutes == 360) minutes = 0;
        else minutes += 30;
        if(seconds == 360) seconds = 0;
        else seconds += 6;
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int boxSize;
        double width;
        double height;
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
        hour2.x = hour.x + middleWidth;
        hour2.y = hour.y + middleHeight;
        minute2.x = minute.x + middleWidth;
        minute2.y = minute.y + middleHeight;
        second2.x = second.x + middleWidth;
        second2.y = second.y + middleHeight;
        g.setColor(Color.black);
        g.drawLine(hour2.x,hour2.y,middleWidth, middleHeight);
        g.setColor(Color.red);
        g.drawLine(minute2.x,minute2.y,middleWidth, middleHeight);
        g.setColor(Color.blue);
        g.drawLine (second2.x,second2.y,middleWidth, middleHeight);
        g.drawString("Here", middleWidth, middleHeight);
   
    }
}