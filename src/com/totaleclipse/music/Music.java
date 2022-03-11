package com.totaleclipse.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    public static boolean playing;
    static Clip clip;

    public static void playMusic() {
        try {
            AudioInputStream MUSIC =
                    AudioSystem.getAudioInputStream(
                            new File("src/com/totaleclipse/music/from-the-dust-cosmos.wav")
                                    .getAbsoluteFile());

            clip = AudioSystem.getClip();
            clip.open(MUSIC);
            clip.start();
            playing=true;
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void stopMusic(){
        clip.stop();
        playing=false;
    }
}
