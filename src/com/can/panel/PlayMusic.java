package com.can.panel;

import java.io.File;
import java.nio.file.StandardCopyOption;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class PlayMusic {
	private SourceDataLine sdl = null;
	static PlayTread pt;
	public PlayMusic() {

	}
	/**
	 * 
	 * @param file
	 *            要播放的音频文件
	 * 
	 */
	public void play(File file, int playTimes) {

		while (playTimes > 0) {
			playTimes--;
			try {
				AudioInputStream ais = AudioSystem.getAudioInputStream(file);
				AudioFormat af = ais.getFormat();
				DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
				sdl = (SourceDataLine) AudioSystem.getLine(info);
				sdl.open(af);
				sdl.start();
				int nByte = 0;
				byte[] buffer = new byte[128];
				while (nByte != -1) {
					nByte = ais.read(buffer, 0, 128);
					if (nByte >= 0) {
						sdl.write(buffer, 0, nByte);
					}
					else{
						break;
					}
				}
			} catch (Exception e) {
				return;
			}
		}

	}

	public void stop() {
		sdl = null;
	}
	public static void playmusic(File file,int playtime){
		pt=null;
		pt=new PlayTread(file, playtime);
	}
}
class PlayTread implements Runnable{

	PlayMusic p=null;
	File file;
	int playtime;
	public PlayTread(File file,int playtime) {
		p=new PlayMusic();
		Thread play=new Thread(this);
		play.start();
		this.file=file;
		this.playtime=playtime;
	}
	@Override
	public void run() {
		p.play(file, playtime);
	}
	public void stop(){
		p.stop();
	}
}