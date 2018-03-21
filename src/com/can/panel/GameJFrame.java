package com.can.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFrame;

public class GameJFrame extends JFrame {
	  static GamePanel g;
      public GameJFrame() {
		this.setSize(515,538);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newgame();
		this.setVisible(true);
		//PlayMusic.playmusic(new File("sound/backgroudmusic.wav"), 10);
	}
    public void newgame(){
    	g=new GamePanel();
		this.getContentPane().add(g);
    }
	
}
