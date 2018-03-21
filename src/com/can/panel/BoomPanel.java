package com.can.panel;

import java.awt.Graphics;

public class BoomPanel implements Runnable {

	int x, y, w, h;

	public BoomPanel(int x, int y, int w, int h) {
		setxywh(x, y, w, h);
	}

	void paint(Graphics g) {
		g.drawImage(Img.BOOM, x, y, w, h, null);
	}

	void setxywh(int x, int y, int w, int h) {
		this.w = w;
		this.h = h;
		this.x = x;
		this.y = y-10;
	}

	void change() {
		while (true) {
			w += 5;
			h += 5;
			x -= 2;
			y -= 2;
			if (w >= 100) {
				try {
					Thread.sleep(120);
				} catch (InterruptedException e) {
				}
				setxywh(-700, -700, 60, 60);
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void run() {
		change();
	}
}
