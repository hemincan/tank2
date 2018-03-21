package com.can.panel;

import java.awt.Graphics;

public class TankPanel {
	public int x;
	public int y;
	public int p;// 方向
	GamePanel gp;
	boolean iamalive=true;
	boolean isememy;       //敌人为真，我方为假
	public TankPanel(GamePanel gp,boolean isememy) {
		this.gp = gp;
		this.isememy=isememy;
	}

	void paint(Graphics g) {
		if (x > FinalData.FRAMEWIDE - 50) // 是否碰到边界的判断
			x = FinalData.FRAMEWIDE - 50;
		if (y > FinalData.FRAMEHIGHT - 50)
			y = FinalData.FRAMEHIGHT - 50;
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		judge();
		if (p == FinalData.UP&&!isememy)
			g.drawImage(Img.TANKUP, x, y, null);
		if (p == FinalData.DOWN&&!isememy)
			g.drawImage(Img.TANKDOWN, x, y, null);
		if (p == FinalData.LEFT&&!isememy)
			g.drawImage(Img.TANKLEFT, x, y, null);
		if (p == FinalData.RIGHT&&!isememy)
			g.drawImage(Img.TANKRIGHT, x, y, null);
		
		//绘制我方的坦克
		if (p == FinalData.UP&&isememy)
			g.drawImage(Img.EMEMYTANKUP, x, y, null);
		if (p == FinalData.DOWN&&isememy)
			g.drawImage(Img.EMEMYTANKDOWN, x, y, null);
		if (p == FinalData.LEFT&&isememy)
			g.drawImage(Img.EMEMYTANKLEFT, x, y, null);
		if (p == FinalData.RIGHT&&isememy)
			g.drawImage(Img.EMEMYTANKRIGHT, x, y, null);
	}

	public void judge() {
		if (p == FinalData.UP) {
			int px = (x + 5) / 50;
			int px2 = (x + 40) / 50;
			int py = (y) / 50;
			if (py < 10 && px < 10){				
				if (gp.brikpanel.map[py][px] == 1 || gp.brikpanel.map[py][px2] == 1) {
					y += 7;
					return;
				}
			}
		}
		if (p == FinalData.DOWN) {
			int px = (x + 5) / 50;
			int py = (y + 43) / 50;//因为巴达兽大小这里改过,改回了
			int px2 = (x + 40) / 50;
			if (py < 10 && px < 10) {
				if (gp.brikpanel.map[py][px] == 1 || gp.brikpanel.map[py][px2] == 1) {
					y -= 7;
					return;
				}
			}
		}
		if (p == FinalData.RIGHT) {
			int px = (x + 45) / 50;//因为巴达兽大小这里改过
			int py = (y + 5) / 50;
			int py2 = (y + 40) / 50;
			if (py < 10 && px < 10){
				if (gp.brikpanel.map[py][px] == 1 || gp.brikpanel.map[py2][px] == 1) {
					x -= 7;
					return;
				}
			}
		}
		if (p == FinalData.LEFT) {
			int px = (x) / 50;
			int py = (y + 5) / 50;
			int py2 = (y + 40) / 50;
			if (py < 10 && px < 10){
				if (gp.brikpanel.map[py][px] == 1 || gp.brikpanel.map[py2][px] == 1) {
					x += 7;
					return;
				}
			}
		}
	}

	public void setxyp(int x, int y, int p) {
		this.x = x;
		this.y = y;
		this.p = p;
	}
}
