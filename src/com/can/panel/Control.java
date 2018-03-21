package com.can.panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class Control implements KeyListener {
	TankPanel tankpanel;
	BulletPanel bulletpanel;
	BulletMoveThread bt;

	public Control(TankPanel tankpanel,BulletPanel bulletpanel) {
		this.tankpanel = tankpanel;
		this.bulletpanel = bulletpanel;

	}


	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) {
			tankpanel.p = FinalData.UP;
			tankpanel.y -= 7;
		}
		if (key == KeyEvent.VK_RIGHT) {
			tankpanel.p = FinalData.RIGHT;
			tankpanel.x += 7;
		}
		if (key == KeyEvent.VK_LEFT) {
			tankpanel.p = FinalData.LEFT;
			tankpanel.x -= 7;
		}
		if (key == KeyEvent.VK_DOWN) {
			tankpanel.p = FinalData.DOWN;
			tankpanel.y += 7;
			
		}

		if (key == KeyEvent.VK_SPACE) {
			File file=new File("sound/fire.wav");
			if (bt == null) {
				bulletpanel.setxyp(tankpanel.x + 10, tankpanel.y + 10, tankpanel.p,FinalData.MYBULLET);
				bt = new BulletMoveThread(bulletpanel, tankpanel);
				PlayMusic.playmusic(file, 1);
			} else {
				if (bt.t.isAlive()) {
					return;
				}
				bulletpanel.setxyp(tankpanel.x + 10, tankpanel.y + 10, tankpanel.p,FinalData.MYBULLET);
				bt = new BulletMoveThread(bulletpanel, tankpanel);
				PlayMusic.playmusic(file, 1);
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
