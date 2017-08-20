package com.lin.fiveChess.learning;

/**
 * Created by lin on 17-8-20.
 */
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel{

    private static final long serialVersionUID = 1L;
    public GamePanel(){

    }

    public void display(){
        System.out.println("GmaePanel - display");

        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        System.out.println("GamePanel - ------------paintComponent");

        g.setColor(new Color(0xcfcfcf));
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("GamePanel - ------------paint");
        super.paint(g);
    }

    @Override
    public void update(Graphics g) {
        System.out.println("GamePanel - ------------update");
        super.update(g);
    }

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setSize(300,300);
        GamePanel gp = new GamePanel();
        frame.add(gp);
        frame.setVisible(true);
        gp.display();
    }
}
