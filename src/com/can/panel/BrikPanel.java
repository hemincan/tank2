package com.can.panel;

import java.awt.Graphics;
import java.io.File;

public class BrikPanel {
	public int x;
	public int y;
	public int p;// 方向
	GamePanel gp;
	LoadMap loadmap;
	int map[][];
	public BrikPanel(GamePanel gp) {
		this.gp=gp;
		loadmap=new LoadMap();
	    map=loadmap.maplist.get(0);
		Thread t=new Thread(new ememyremoveBirkThread());
		t.start();
	}
	public LoadMap updataMap(){
		loadmap=new LoadMap();
		return loadmap;
		
	}
			
	 /*int map[][]= {{0,0,0,0,0,0,0,0,0,0},   
			 {0,0,1,0,0,0,0,1,0,0},
		     {0,1,0,1,0,0,1,0,1,0},
		     {1,0,0,0,1,1,0,0,0,1},
		     {0,0,0,0,0,0,0,0,0,0},
		     {0,1,0,0,0,0,0,0,1,0},
		     {0,0,1,0,0,0,0,1,0,0},
		     {0,0,0,1,0,0,1,0,0,0},
		     {0,0,0,0,1,1,0,0,0,0},
		     {0,0,0,0,0,0,0,0,0,0}};*/
	void paint(Graphics g){
		removeBrik();
		for(int i = 0;i<10;i++)
			for(int j = 0;j<10;j++){
				if(map[i][j]==1)
		          g.drawImage(Img.BRIK, j*50,i*50,null);
			}     
	}
	
	public void removeBrik(){
		int x=(gp.bulletpanel.x+15)/50;
		int y=(gp.bulletpanel.y+15)/50;
		if(x<10&&y<10){
		   if(map[y][x]==1){
			map[y][x]=0;
			gp.boompanel.setxywh(gp.bulletpanel.x,gp.bulletpanel.y,10,10);
			Thread boomthread=new Thread(gp.boompanel);
			boomthread.start();
			PlayMusic.playmusic(new File("sound/boom.wav"), 1);
			gp.c.bt.t.stop();
			gp.bulletpanel.setxyp(-30, -30, FinalData.UP,FinalData.MYBULLET );
		   }
		}
	}
	public void ememyremoveBrik(){    //和GamePanel共用了资源，就算判断为空不能去执行也会出现空指针 
		int x,y;
		//System.out.println(gp.ememytank.ememybullet.size());
		if(gp.ememytank.ememybullet.size()>0){
		      for(int i=0;i<gp.ememytank.ememybullet.size();i++){
		     	x=(gp.ememytank.ememybullet.get(i).x+5)/50;
		     	y=(gp.ememytank.ememybullet.get(i).y+5)/50;
		     	if(x<10&&y<10&&x>=0&&y>=0){
				   if(map[y][x]==1){
					   map[y][x]=0; 
					   gp.ememytank.ememybullet.get(i).setxyp(600, 600,FinalData.DOWN , FinalData.EMEMYBULLET);
				   }
	   	         } 
		     	
		     	//超出范围移除敌方子弹
				if(gp.ememytank.ememybullet.get(i).x>500||gp.ememytank.ememybullet.get(i).y>500||gp.ememytank.ememybullet.get(i).y<-30||gp.ememytank.ememybullet.get(i).x<-30){
					gp.ememytank.ememybullet.remove(i);
				}
	         }
		}
  }
	class ememyremoveBirkThread implements Runnable{
		@Override
		public void run() {
			while(true){
				ememyremoveBrik();
				try {
					Thread.sleep(80);
				} catch (InterruptedException e) {
				}
			}
		}
		
	}
}
