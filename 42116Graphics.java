import java.awt.*;
import javax.swing.*;

public class 42116GraphicsUsedToBeApplets{
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
	UpdatePanel(Jpanel newjp){jp = newjp;}
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
	int angle
	GraphicsPanel() {
		i=0;
		angle = 0;
		int dist = 100;
		int j = 0;
		for(int i = 90; i > 0; i -=10){
			SquarePoints sp = new SquarePoints();
			sp.xs[0] = (int) (dist*Math.cos(Math.toRadians(i)));
			sp.ys[0] = (int) (dist*Math.sin(Math.toRadians(i)));
			sp.xs[1] = (int) (dist*Math.cos(Math.toRadians(i-90)));
			sp.ys[1] = (int) (dist*Math.sin(Math.toRadians(i-90)));
			sp.xs[2] = (int) (dist*Math.cos(Math.toRadians(-180+i)));
			sp.ys[2] = (int) (dist*Math.sin(Math.toRadians(-180+i)));
			sp.xs[3] = (int) (dist*Math.cos(Math.toRadians(90+i)));
			sp.ys[3] = (int) (dist*Math.sin(Math.toRadians(90+i)));
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
		for(int i = 0; i < 4; i++){
			sp2.xs[i] = spa[angle].xs[i]+middleWidth;
			sp2.ys[i] = spa[angle].ys[i].middleHeight;
		}
		g.drawPolygon(sp.xs,sp.ys,4);
		i++;
	}
}