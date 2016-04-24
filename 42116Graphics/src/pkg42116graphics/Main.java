/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42116graphics;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author webteam
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
		JFrame jf = new JFrame("Silly Graphics Demo");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(400,400);
		GraphicsPanel gp = new GraphicsPanel();
		new UpdatePanel(gp).start();
		jf.add(gp);
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
				jp.repaint();//use to force paint to clear the screen and redraw
				sleep(1000);
			}
		}catch(Exception e){}
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

class GraphicsPanel extends JPanel{
	int i;
	SquarePoints[] spa;
	int angle;
	GraphicsPanel() {
		i=0;
		angle = 0;
		int dist = 100;
		int j = 0;
		for(int k = 90; k > 0; k -=10){
			SquarePoints sp = new SquarePoints();
			sp.xs[0] = (int) (dist*Math.cos(k));
			sp.ys[0] = (int) (dist*Math.sin(k));
			sp.xs[1] = (int) (dist*Math.cos(k-90));
			sp.ys[1] = (int) (dist*Math.sin(k-90));
			sp.xs[2] = (int) (dist*Math.cos(-180+k));
			sp.ys[2] = (int) (dist*Math.sin(-180+k));
			sp.xs[3] = (int) (dist*Math.cos(90+k));
			sp.ys[3] = (int) (dist*Math.sin(90+k));
			spa[j++] = sp;
		}
	}

	void moveAngle(){
		if(angle == 8)angle = 0;
		else angle++;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		double height = getSize().getHeight();
		double width = getSize().getWidth();
		int middleWidth = (int)width/2;
		int middleHeight = (int)height/2;
		g.drawString(""+i, middleWidth,middleHeight);
		int boxSize = 200;
		//g.drawRect(middleWidth-boxSize/2,middleHeight-boxSize/2,boxSize,boxSize);
		//g.fillOval((int)width-100, (int)height - 100, 100, 100);
		SquarePoints sp2 = new SquarePoints();
		for(int k = 0; k < 4; k++){
			sp2.xs[k] = spa[angle].xs[k]+middleWidth;
			sp2.ys[k] = spa[angle].ys[k]+middleHeight;
		}
		g.drawPolygon(sp2.xs,sp2.ys,4);
		i++;
	}
}
