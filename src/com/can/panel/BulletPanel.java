package com.can.panel;

import java.awt.Graphics;

public class BulletPanel {
	public int x;
	public int y;
	public int p;// 方向
	public int type;

	public BulletPanel() {
	}

	public BulletPanel(int x, int y, int p,int type) {
		setxyp(x, y, p,type);

	}

	void paint(Graphics g) {
		if (type == FinalData.MYBULLET) {
			if (p == FinalData.UP)
				g.drawImage(Img.BULLETUP, x, y, null);
			if (p == FinalData.DOWN)
				g.drawImage(Img.BULLETDOWN, x, y, null);
			if (p == FinalData.LEFT)
				g.drawImage(Img.BULLETLEFT, x, y, null);
			if (p == FinalData.RIGHT)
				g.drawImage(Img.BULLETRIGHT, x, y, null);
		}
		if(type==FinalData.EMEMYBULLET){
			g.drawImage(Img.EMEMYBULLET, x, y,null);
		}
	}

	public void setxyp(int x, int y, int p,int type) {
		this.x = x;
		this.y = y;
		this.p = p;
		this.type=type;
	}

	void change() {
		if (p == FinalData.UP) { // 向上y--
			y -= 1;
		}
		if (p == FinalData.DOWN) { // 向下y++
			y += 1;
		}
		if (p == FinalData.LEFT) { // 向左y--
			x -= 1;
		}
		if (p == FinalData.RIGHT) { // 向右y--
			x += 1;
		}
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
		}
	}
}
