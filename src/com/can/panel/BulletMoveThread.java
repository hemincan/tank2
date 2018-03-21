package com.can.panel;

public class BulletMoveThread implements Runnable {
	BulletPanel bulletpanel;
	TankPanel tankpanel;
	Thread t = null;
	public BulletMoveThread(BulletPanel bulletpanel, TankPanel tankpanel) {
		this.bulletpanel = bulletpanel;
		this.tankpanel = tankpanel;
		 t = new Thread(this);
		 t.start();
	}

	public void run() {
		while (true) {
			bulletpanel.change();
			if (bulletpanel.x < -30|| bulletpanel.x > 500 || bulletpanel.y < -30 || bulletpanel.y > 500) {
			     return;
			}
		}
	}

}
