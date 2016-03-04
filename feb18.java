/*
February 18
Windows!
*/

/*

Anonymous class- class with no name

class A{
	class b{
	
	}
}

class A{
	static class b{
	
	}
}

class A{
	void func 0{
		classB{}
	}
}



*/

import javax.swing.*;
import  java.awt.event.*;

public class feb18{

	static JFrame jf;
	static JPanel jp;
	static JButton firstButton;
	static JButton secondButton;
	static JPanel middle;
	public static void main(String[] args){
		// SomeClass sc = new SomeClass(); //create
		// System.out.println(sc.SomeFunc()); //call func

		// System.out. println(new SomeClass.SomeFunc()); //create and call

		// System.out.println(new SomeClass(){//not creating SomeClass. creating new datatype. derives from another class - no name
		// 	String SomeFunc(){
		// 		returns "Some other func's string!!!";
		// 	}
		// }.SomeFunc());

		jf = new JFrame("Some title");
		jf.setSize(400,400);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizeable(false);
		//components added to another component will always occupy the entire space
		firstButton = new JButton('Make Blue');
		firstButton.setBackground(Color.BLUE);
		firstButton.addActionListener(new Buttonpress());
		secondButton = new JButton('Make Pink');
		secondButton.setBackground(Color.pink);
		secondButton.addActionListener(new ActionListener(){//derived anon class
			public void actionPerformed(ActionListener ae){//derived function
				middle.setBackground(Color.PINK);//never to be used again
			}
		});
		//OR
		secondButton.addActionListener(new Buttonpress());


		//jpanels default is flow
		//grid layout - columns, rows, h gap and v gap
		//border layout - 5 sections - looks like a frame
			//must define which panel you want to place it in (NSEW, CENTER)
		jp = new Jpanel();
		middle = new JPanel();
		middle.setBackground(Color.yellow);
		jp.setLayout(new BorderLayout());
		jf.add(jp);
		// jp.add(firstButton);
		jp.add(firstButton,BorderLayout.NORTH);
		jp.add(secondButton,BorderLayout.SOUTH);
		jp.add(middle,BorderLayout.CENTER);
		/*
			________________________
			|\|______NORTH_______|/|
			|W|                  |E| 
			|E|      CENTER      |A|
			|S|                  |S|
			|T|__________________|T|
			|/|______SOUTH_______|\|

		*/
		jf.setVisible(true);
	}


	class Buttonpress implements ActionListener{
		// public void actionPerformed(ActionEvent ae){
		// 	middle.setBackground(Color.BLUE);
		// }
		//OR

		public void actionPerformed(ActionEvent ae){
			if (ae.getSource() == firstButton){
				middle.setBackground(Color.BLUE);
			}
			else
				middle.setBackground(Color.PINK);
		}
	}
}

class SomeClass{
	String SomeFunc(){
		return "someclass string";
	}
}