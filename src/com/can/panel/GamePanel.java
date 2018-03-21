package com.can.panel;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	BrikPanel brikpanel;
	BulletPanel bulletpanel;
	TankPanel tankpanel;
	BoomPanel boompanel;
	Control c;
	EmemyTank ememytank;
	GameJudge gamejudge;
	int step=0;//保存关卡数
	public GameOverPanel g1;
	int tankcount = 0;

	public GamePanel() {
		this.setLayout(null);
		ememytank = new EmemyTank(this);
		tankpanel = new TankPanel(this, false);// 我方的坦克
		tankpanel.setxyp(250, 250, FinalData.UP);
		bulletpanel = new BulletPanel(500, 500, FinalData.DOWN,
				FinalData.MYBULLET);// 初始化我方子弹
		brikpanel = new BrikPanel(this);
		boompanel = new BoomPanel(-700, -700, 60, 60);
		Thread t = new Thread(this);
		t.start();
		gamejudge = new GameJudge(this);
		this.setFocusable(true);
		c = new Control(tankpanel, bulletpanel);
		this.addKeyListener(c);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(Img.BACKGROUND, 0, 0, null);
		if (!gamejudge.gameisover) {
			brikpanel.paint(g);
			bulletpanel.paint(g);
			tankpanel.paint(g);
			if (boompanel.x > -500) {
				boompanel.paint(g);
			}

			for (int i = 0; i < ememytank.ememybullet.size(); i++) {
				ememytank.ememybullet.get(i).paint(g);
			}
			for (int i = 0; i < FinalData.TANKNUM; i++) {
				if (ememytank.ememytankpanel.get(i).iamalive) { // 只有当敌方坦克还活着的时候才进行重绘
					ememytank.ememytankpanel.get(i).paint(g);
				}
				if (!ememytank.ememytankpanel.get(i).iamalive
						&& tankcount++ <= FinalData.TANKNUM) {// 坦克会重新出现和坦克数量一样多的次数
					ememytank.ememytankpanel.get(i).iamalive = true;
					Random rand = new Random();
					int position = rand.nextInt(4);
					if (position == 0)//随机出现在四个角落
						ememytank.ememytankpanel.get(i).setxyp(0, 450,
								FinalData.DOWN);
					if (position == 1)
						ememytank.ememytankpanel.get(i).setxyp(0, 0,
								FinalData.DOWN);
					if (position == 2)
						ememytank.ememytankpanel.get(i).setxyp(450, 0,
								FinalData.DOWN);
					if (position == 3)
						ememytank.ememytankpanel.get(i).setxyp(450, 450,
								FinalData.DOWN);
				}
			}
		}
		if (gamejudge.gameisover) {
			if (g1 == null) {
				PlayMusic.playmusic(new File("sound/fail.wav"), 1);
				g1 = new GameOverPanel(this);
				this.add(g1.restart);
				this.add(g1.exit);
			}
			g1.paint(g);
		}
	
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(30);
				gamejudge.judge(); // 游戏结束，子弹打到敌方坦克等关于游戏胜负的判断
			} catch (InterruptedException e) {
			}
			if(gamejudge.allTankDeath()){
				step++;
				brikpanel.map=brikpanel.updataMap().maplist.get(step);
				tankcount=0;
				tankpanel.setxyp(250, 250,FinalData.DOWN);
				if(FinalData.FIRETIME>=6){
				    FinalData.FIRETIME-=5;
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
			}
			repaint();
		}
	}

}
