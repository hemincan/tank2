package com.can.panel;

import java.util.ArrayList;
import java.util.Random;

public class EmemyTank {
	GamePanel gp;
	static ArrayList<TankPanel> ememytankpanel;
	static ArrayList<BulletPanel> ememybullet;

	public EmemyTank() {
	}

	static int firetime = 0;

	public EmemyTank(GamePanel gp) {
		ememytankpanel = new ArrayList<TankPanel>();
		ememybullet = new ArrayList<BulletPanel>();
		this.gp = gp;
		addememytank();
		for (int i = 0; i < FinalData.TANKNUM; i++) {
			Thread move1 = new Thread(new EmemyMove(ememytankpanel.get(i)));
			move1.start();
			// Thread move2 = new Thread(new EmemyMove(ememytankpanel.get(1)));
			// move2.start();
			// Thread move3 = new Thread(new EmemyMove(ememytankpanel.get(2)));
			// move3.start();
		}
	}

	void addememytank() {
		for (int i = 0; i < FinalData.TANKNUM; i++) {
			TankPanel ememy = new TankPanel(gp, true);// 敌方的坦克为真
			ememy.setxyp(i * 50, i * 50, FinalData.DOWN);
			Random rand = new Random();
			int position = rand.nextInt(4);
			if (position == 0)//随机出现在四个角落
				ememy.setxyp(0, 450,
						FinalData.DOWN);
			if (position == 1)
				ememy.setxyp(0, 0,
						FinalData.DOWN);
			if (position == 2)
				ememy.setxyp(450, 0,
						FinalData.DOWN);
			if (position == 3)
				ememy.setxyp(450, 450,
						FinalData.DOWN);
			
			ememytankpanel.add(ememy);

		}
	}

	static void newbullet(TankPanel ememytank, int p) {//新建立一个敌方的子弹实例
		BulletPanel bullet = new BulletPanel(ememytank.x + 15, ememytank.y + 15, p, FinalData.EMEMYBULLET);
		BulletMoveThread bmt = new BulletMoveThread(bullet, ememytank);
		ememybullet.add(bullet);
	}

	static void ememytankmove(TankPanel ememytank, int p) {
		int x[] = new int[FinalData.TANKNUM];
		int y[] = new int[FinalData.TANKNUM];
		for (int i = 0; i < x.length; i++) {
			x[i] = ememytankpanel.get(i).x;
			y[i] = ememytankpanel.get(i).y;
		}
		int x1, y1;
		if (p == FinalData.DOWN) {
			x1 = ememytank.x + 25;
			y1 = ememytank.y + 50;
			for (int i = 0; i < x.length; i++) {
				if ((x1 > x[i] && x1 < x[i] + 50) && (y1 + 7 > y[i] && y1 < y[i] + 50)) // 进入了别的坦克位置，向下，Y+
					return;
			}
			ememytank.setxyp(ememytank.x, ememytank.y + 7, FinalData.DOWN);
			firetime++;
			if (firetime > FinalData.FIRETIME) {
				newbullet(ememytank, p);
				firetime = 0;
			}
		}
		if (p == FinalData.RIGHT) {
			x1 = ememytank.x + 50;
			y1 = ememytank.y + 25;
			for (int i = 0; i < x.length; i++) {
				if ((x1 + 7 > x[i] && x1 < x[i] + 50) && (y1 > y[i] && y1 < y[i] + 50)) // 进入了别的坦克位置，向下，Y+
					return;
			}
			ememytank.setxyp(ememytank.x + 7, ememytank.y, FinalData.RIGHT);
			firetime++;
			if (firetime > FinalData.FIRETIME) {
				newbullet(ememytank, p);
				firetime = 0;
			}
		}
		if (p == FinalData.LEFT) {
			x1 = ememytank.x;
			y1 = ememytank.y + 25;
			for (int i = 0; i < x.length; i++) {
				if ((x1 - 7 > x[i] && x1 < x[i] + 50) && (y1 > y[i] && y1 < y[i] + 50)) // 进入了别的坦克位置，向下，Y+
					return;
			}
			ememytank.setxyp(ememytank.x - 7, ememytank.y, FinalData.LEFT);
			firetime++;
			if (firetime > FinalData.FIRETIME) {
				newbullet(ememytank, p);
				firetime = 0;
			}

		}
		if (p == FinalData.UP) {
			x1 = ememytank.x + 25;
			y1 = ememytank.y;
			for (int i = 0; i < x.length; i++) {
				if ((x1 > x[i] && x1 < x[i] + 50) && (y1 - 7 > y[i] && y1 < y[i] + 50)) // 进入了别的坦克位置，向下，Y+
					return;
			}
			ememytank.setxyp(ememytank.x, ememytank.y - 7, FinalData.UP);
			firetime++;
			if (firetime > FinalData.FIRETIME) {
				newbullet(ememytank, p);
				firetime = 0;
			}
		}
	}
}

class EmemyMove implements Runnable {

	TankPanel ememytank;
	Direction d = new Direction();

	public EmemyMove(TankPanel ememytank) {
		this.ememytank = ememytank;
		Thread updateP = new Thread(d);
		updateP.start();
	}

	@Override
	public void run() {

		while (true) {
			if (ememytank.iamalive) {   //如果坦克还活着才让它移动并且发送子弹
				EmemyTank.ememytankmove(ememytank, d.getP());
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}
		}

	}

}

class Direction implements Runnable {

	int p;

	public int getP() {
		return p;
	}

	@Override
	public void run() {
		while (true) {
			Random r = new Random();
			p = r.nextInt(4) + 1;
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
		}
	}
}
