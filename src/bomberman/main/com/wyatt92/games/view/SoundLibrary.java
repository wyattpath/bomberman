package com.wyatt92.games.view;


import javax.sound.sampled.*;
import java.io.IOException;

public class SoundLibrary
{

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
