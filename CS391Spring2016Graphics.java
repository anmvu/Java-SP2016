/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs391.spring2016.graphics;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;

/**
 *
 * @author dkatz
 */
public class CS391Spring2016Graphics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame jf = new JFrame("Silly Graphics Demo");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(400,400);
        GraphicsPanel gp= new GraphicsPanel();
        new UpdatePanel(gp).start();
        jf.add(gp);
        jf.setVisible(true);
        /*for(int i=0;i<360; i+=90){
            System.out.println("i="+i+"\tsin="+Math.sin(i)+"\tcos="+Math.cos(i));
        }*/
        
        
    }
    
}

class UpdatePanel extends Thread{
    GraphicsPanel jp;
    UpdatePanel(GraphicsPanel newjp){jp=newjp;}
    public void run(){
        try{
            while(true){
                jp.moveAngle();
                sleep(1000);
            }
        }
        catch(Exception e){}
    }
}
class SquarePoints{
    int[] xs;
    int[] ys;
    SquarePoints(){
        xs = new int[4];
        ys = new int[4];
    }
}
class Point{
    int x;
    int y;
}
class GraphicsPanel extends JPanel{
    int i;
    SquarePoints[] spa;
    int angle;
    ArrayList<Point> presses;
    GraphicsPanel(){
        i=0;
        presses= new ArrayList();
        angle=0;
        int dist = 100;
        spa = new SquarePoints[9];
        int j=0;
        for (int i=0; i<90; i+=10){
            SquarePoints sp= new SquarePoints();
            sp.xs[0] = (int)(dist*Math.cos(Math.toRadians(i)));
            sp.ys[0]= (int) (dist*Math.sin(Math.toRadians(i)));
            
            sp.xs[1]=(int) (dist*Math.cos(Math.toRadians(i-90)));
            sp.ys[1]=(int) (dist*Math.sin(Math.toRadians(i-90)));
            
            sp.xs[2] = (int)(dist*Math.cos(Math.toRadians(-180+i)));
            sp.ys[2]= (int) (dist*Math.sin(Math.toRadians(-180+i)));
            
            sp.xs[3]=(int) (dist*Math.cos(Math.toRadians(i+90)));
            sp.ys[3]=(int) (dist*Math.sin(Math.toRadians(i+90)));
            spa[j++] = sp;
        }
        addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent me) {
                Point p = new Point();
                p.x = me.getX();
                p.y=me.getY();
                presses.add(p);
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        
        });
    }
    void moveAngle(){
        if (angle==8)
            angle=0;
        else
            angle++;
        repaint();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        double height = getSize().getHeight();
        double width = getSize().getWidth();
        int middleWidth=(int)width/2;
        int middleHeight=(int)height/2;
        //g.drawString(""+angle, middleWidth, middleHeight);
        Calendar c = Calendar.getInstance();
        String time =""+c.get(Calendar.HOUR_OF_DAY);
        time+=":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
        g.drawString(time, middleWidth-20, middleHeight-5);
        //int boxSize=(int)width/2;
        g.setColor(Color.BLUE);
        //g.drawRect(middleWidth-(boxSize/2), middleHeight-(boxSize/2), boxSize, boxSize);
        //g.fillOval((int)width-100, (int)height-100, 100, 100);
        SquarePoints sp2 = new SquarePoints();
        for (int i=0; i<4; i++){
            sp2.xs[i] = spa[angle].xs[i]+middleWidth;
            sp2.ys[i] = spa[angle].ys[i]+middleHeight;
        }
        g.drawPolygon(sp2.xs, sp2.ys, 4);
        i++;
        g.setColor(Color.RED);
        for (Point p :presses){
            g.fillOval(p.x-5, p.y-5, 10, 10);
        }
    }    
}
