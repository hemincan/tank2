package com.can.panel;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.sun.org.glassfish.gmbal.GmbalException;

public class GameOverPanel implements Runnable,ActionListener{

	int y = 500;
    JButton restart;  //���¿�ʼ��ť
    JButton exit;     //�˳���Ϸ��ť
    GamePanel gamepanel;
	public GameOverPanel(GamePanel gamepane) {
		gamepanel=gamepane;
		Thread t = new Thread(this);
		t.start();
		restart=new JButton(Img.RESTART);
		restart.addActionListener(this);
		restart.setVisible(false);
		exit=new JButton(Img.EXIT);
		exit.setVisible(true);
	}
    
	public void paint(Graphics g) {
		g.drawImage(Img.GAMEOVER, 100, y, null);
	}

	@Override
	public void run() {
		
		while (y > 150) {
			y = y-10;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
		}
		restart.setVisible(true);
		exit.setVisible(true);
		restart.setBounds(100, 250, 111, 46);
		exit.setBounds(280, 250, 111, 46);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		gamepanel.gamejudge.gameisover=false;//���¿�ʼ��Ϸ������������Ϸ������Ϊfalae
		gamepanel.tankcount=0;//̹�˳��ִ���
		gamepanel.g1=null;    //��Ϸ����������Ϊ�գ��ٴ���Ϸ������ʱ�������������ʾ
		gamepanel.step=0;//���¿�ʼ��Ϸ������Ϊ0
		FinalData.FIRETIME=15;//��̹�˵���������
		gamepanel.tankpanel.setxyp(250,250, FinalData.UP);//���ҷ���̹��λ�����·�����ʼλ��
		gamepanel.brikpanel.map=gamepanel.brikpanel.updataMap().maplist.get(0);//���¼��ص�һ�صĵ�ͼ
		
		for (int i = 0; i < FinalData.TANKNUM; i++) {//�������̹�˵�λ��
			gamepanel.ememytank.ememytankpanel.get(i).iamalive = true;
			Random rand = new Random();
			int position = rand.nextInt(4);
			if (position == 0)//����������ĸ�����
				gamepanel.ememytank.ememytankpanel.get(i).setxyp(0, 450,
						FinalData.DOWN);
			if (position == 1)
				gamepanel.ememytank.ememytankpanel.get(i).setxyp(0, 0,
						FinalData.DOWN);
			if (position == 2)
				gamepanel.ememytank.ememytankpanel.get(i).setxyp(450, 0,
						FinalData.DOWN);
			if (position == 3)
				gamepanel.ememytank.ememytankpanel.get(i).setxyp(450, 450,
						FinalData.DOWN);
		}
		restart.setVisible(false);
		exit.setVisible(false);
		PlayMusic.playmusic(new File("sound/coming.wav"), 1);
		
	}
}
