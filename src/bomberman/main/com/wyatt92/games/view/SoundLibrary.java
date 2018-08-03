package com.wyatt92.games.view;


import javax.sound.sampled.*;
import java.io.IOException;

public class SoundLibrary
{
	private static Clip menu_pauseFX, menu_selectFX, menu_cursorMoveFX;
	static Clip[] menu_bgMusic,game_bgMusic, gameOver_bgMusic;

	static {
		//Sounds

		final int bgMusicCount, goMusicCount, gMusicCount;

		menu_selectFX = SoundLibrary.clipForLoopFactory("select.wav");
		menu_cursorMoveFX = SoundLibrary.clipForLoopFactory("cursor_move.wav");

		bgMusicCount = 2;
		menu_bgMusic = new Clip[bgMusicCount];
		for(int i = 0 ; i < bgMusicCount;i++)
			menu_bgMusic[i] = SoundLibrary.clipForLoopFactory("Undertale00"+ i +".wav");

		gMusicCount = 9;
		game_bgMusic = new Clip[gMusicCount];
		for(int i = 0 ; i < gMusicCount;i++)
			game_bgMusic[i] = SoundLibrary.clipForLoopFactory("music0"+ i +".wav");

		goMusicCount = 2;
		gameOver_bgMusic = new Clip[goMusicCount];
		for(int i = 0 ; i < goMusicCount;i++)
			gameOver_bgMusic[i]  = SoundLibrary.clipForLoopFactory("gomusic0"+ i +".wav");

	}

	//for individual wav sounds (not looped)
	//http://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
	public static synchronized void playSound(final String strPath) {
	    new Thread(() ->
		{
		  try {
			Clip clp = AudioSystem.getClip();

			AudioInputStream aisStream =
					AudioSystem.getAudioInputStream(SoundLibrary.class.getClassLoader().getResource("./sounds/fx/" + strPath));

			clp.open(aisStream);
			clp.start();
		  } catch (Exception e) {
			System.err.println(e.getMessage());
		  }
		}).start();
	  }
	
	
	//for looping wav clips
	//http://stackoverflow.com/questions/4875080/music-loop-in-java
	static Clip clipForLoopFactory(String strPath){
		
		Clip clp = null;
		
		// this line caused the original exceptions

        try
        {
            AudioInputStream aisStream =
                    AudioSystem.getAudioInputStream(SoundLibrary.class.getClassLoader().getResource("./sounds/" + strPath));
            clp = AudioSystem.getClip();
            clp.open(aisStream);

        } catch (LineUnavailableException exp)
        {

            exp.printStackTrace();

        } catch (IOException | UnsupportedAudioFileException exp)
        {
            System.out.println("error");
        }

		return clp;
		
	}
	
	


}
