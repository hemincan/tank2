package com.can.panel;


import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GameJudge {
	GamePanel gp;
	BulletPanel bulletpanel;
	boolean gameisover;

	public GameJudge(GamePanel gp) {
		gameisover = false;
		this.gp = gp;
		bulletpanel = gp.bulletpanel;
	}

	void judge() {
		int x, y;
		for (int i = 0; i < gp.ememytank.ememytankpanel.size(); i++) {// 我方子弹与敌方坦克的碰撞
			x = gp.ememytank.ememytankpanel.get(i).x;
			y = gp.ememytank.ememytankpanel.get(i).y;
			if ((gp.bulletpanel.x + 5 > x && gp.bulletpanel.x + 5 < (x + 50)) && (gp.bulletpanel.y + 10) > y
					&& (gp.bulletpanel.y + 10) < (y + 50) && gp.ememytank.ememytankpanel.get(i).iamalive) {
				// 我方子弹进入了敌方坦克,并且坦克还活着
				gp.ememytank.ememytankpanel.get(i).iamalive = false;
				gp.bulletpanel.setxyp(550, 550, FinalData.DOWN, FinalData.MYBULLET);// 我方的子弹因为坐标已经超出屏幕，会自动停止线程序
				// 以下显示爆炸效果和播放声音
				gp.boompanel.setxywh(x + 15, y + 15, 10, 10);// 设置爆炸效果的位置
				Thread boomthread = new Thread(gp.boompanel);
				boomthread.start();
				PlayMusic.playmusic(new File("sound/boom.wav"), 1);
			}
		}
		for (int i = 0; i < gp.ememytank.ememybullet.size(); i++) {// 我方子弹与敌方子弹的碰撞
			x = gp.ememytank.ememybullet.get(i).x;
			y = gp.ememytank.ememybullet.get(i).y;
			// 假如两个子弹碰在了一直
			if ((gp.bulletpanel.x + 15 > x && gp.bulletpanel.x + 15 < x + 20)
					&& (gp.bulletpanel.y + 15 > y && gp.bulletpanel.y + 15 < y + 20)) {
				// 把敌方的子弹位置设在屏幕外面，有其他的线会让它消失，
				gp.ememytank.ememybullet.get(i).setxyp(550, 550, FinalData.DOWN, FinalData.EMEMYBULLET);
				gp.bulletpanel.setxyp(550, 550, FinalData.DOWN, FinalData.MYBULLET);
			}
			// 假如敌方的子弹打到了我方的坦克
			int x2 = gp.tankpanel.x;
			int y2 = gp.tankpanel.y;
			if ((x + 5 > x2 && x + 5 < x2 + 50) && (y + 5 > y2 && y + 5 < y2 + 50)) {
				gameisover = true;
				// System.out.println("game is over!");
			}
		}
		// 我方坦克与敌方坦克的碰撞
		for (int i = 0; i < gp.ememytank.ememytankpanel.size(); i++) {
			if (gp.ememytank.ememytankpanel.get(i).iamalive) {// 如果坦克还活着才有价值去判断
				x = gp.ememytank.ememytankpanel.get(i).x;
				y = gp.ememytank.ememytankpanel.get(i).y;
				int x1, y1, x2, y2, x3, y3;// 对应了坦克的四个角的坐标
				x1 = x + 50;
				y1 = y;
				y2 = y + 50;
				x2 = x;
				x3 = x + 50;
				y3 = y + 50;
				if (x > gp.tankpanel.x && x < gp.tankpanel.x + 50 && y > gp.tankpanel.y && y < gp.tankpanel.y + 50) {
					gameisover = true;
				}
				if (x1 > gp.tankpanel.x && x1 < gp.tankpanel.x + 50 && y1 > gp.tankpanel.y
						&& y1 < gp.tankpanel.y + 50) {
					gameisover = true;
				}
				if (x2 > gp.tankpanel.x && x2 < gp.tankpanel.x + 50 && y2 > gp.tankpanel.y
						&& y2 < gp.tankpanel.y + 50) {
					gameisover = true;
				}
				if (x3 > gp.tankpanel.x && x3 < gp.tankpanel.x + 50 && y3 > gp.tankpanel.y
						&& y3 < gp.tankpanel.y + 50) {
					gameisover = true;
				}
			}
		}
	}

	public boolean allTankDeath() {
		int alivetank=0;//保存还活着的坦克
		for (int i = 0; i < FinalData.TANKNUM; i++) {
			if (gp.ememytank.ememytankpanel.get(i).iamalive) {
				alivetank++;
			}
		}
		if(alivetank==0){
			for (int i = 0; i < FinalData.TANKNUM; i++) {//坦克都死了，进入第二关，把它们复活，并随机在四个角落出现
				gp.ememytank.ememytankpanel.get(i).iamalive = true;
				Random rand = new Random();
				int position = rand.nextInt(4);
				if (position == 0)//随机出现在四个角落
					gp.ememytank.ememytankpanel.get(i).setxyp(0, 450,
							FinalData.DOWN);
				if (position == 1)
					gp.ememytank.ememytankpanel.get(i).setxyp(0, 0,
							FinalData.DOWN);
				if (position == 2)
					gp.ememytank.ememytankpanel.get(i).setxyp(450, 0,
							FinalData.DOWN);
				if (position == 3)
					gp.ememytank.ememytankpanel.get(i).setxyp(450, 450,
							FinalData.DOWN);
			}
			return true;
		}
		return false;    

	}

}
