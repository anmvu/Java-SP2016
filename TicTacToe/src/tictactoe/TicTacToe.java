/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 *
 * @author webteam
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    static int turn;
    static JFrame jf;
    static JPanel jp;
    static JButton topLeft;
    static JButton topMid;
    static JButton topRight;
    static JButton midLeft;
    static JButton midMid;
    static JButton midRight;
    static JButton botLeft;
    static JButton botMid;
    static JButton botRight;
    static ArrayList<Integer> p1;
    static ArrayList<Integer> p2;
    static ArrayList <int []> winning;
    
    public static void main(String[] args) {
        // TODO code application logic here
        turn = 0;
        jf = new JFrame("Tic Tac Toe");
        jf.setSize(600,600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jp = new JPanel();
        jf.add(jp);
        jp.setLayout(new GridLayout(3,3));
        topLeft = new JButton();
        topMid = new JButton();
        topRight = new JButton();
        midLeft = new JButton();
        midMid = new JButton();
        midRight = new JButton();
        botLeft = new JButton();
        botMid = new JButton();
        botRight = new JButton();
        
        jp.add(topLeft);
        jp.add(topMid);
        jp.add(topRight);
        jp.add(midLeft);
        jp.add(midMid);
        jp.add(midRight);
        jp.add(botLeft);
        jp.add(botMid);
        jp.add(botRight);
        
        topLeft.setName("0");
        topMid.setName("1");
        topRight.setName("2");
        midLeft.setName("3");
        midMid.setName("4");
        midRight.setName("5");
        botLeft.setName("6");
        botMid.setName("7");
        botRight.setName("8");
        
        topLeft.addActionListener(new ButtonPress());
        topMid.addActionListener(new ButtonPress());
        topRight.addActionListener(new ButtonPress());
        midLeft.addActionListener(new ButtonPress());
        midMid.addActionListener(new ButtonPress());
        midRight.addActionListener(new ButtonPress());
        botLeft.addActionListener(new ButtonPress());
        botMid.addActionListener(new ButtonPress());
        botRight.addActionListener(new ButtonPress());
        
        jf.setVisible(true);
    }
    
    static class ButtonPress implements ActionListener{
        public ArrayList<Integer> check_win(int box, int player){
            ArrayList<Integer> win = new ArrayList<Integer>();
            
            
            return win;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae){
            JButton bx = (JButton)ae.getSource();
            int player;
            if (turn%2 == 0){
               bx.setText("X");
               p1.add(Integer.parseInt(bx.getName()));
               player = 1;
            }
            else{
                bx.setText("O");
                p2.add(Integer.parseInt(bx.getName()));
                player = 2;
            }
            bx.setEnabled(false);
            turn++;
            if (turn >= 4){
                ArrayList<Integer> win = check_win(Integer.parseInt(bx.getName()),player);
                if (!win.isEmpty()){
                    bx.setBackground(Color.GREEN);
                    
                }
            }
            else if(turn == 8){
                System.out.println("GAME OVER");
            }
        }
    }
        
}
