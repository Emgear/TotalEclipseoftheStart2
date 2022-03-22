package com.totaleclipse.music;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * This enum encapsulates all the sound effects of a game, so as to separate the sound playing
 * codes from the game codes.
 * 1. Define all your sound effect names and the associated wave file.
 * 2. To play a specific sound, simply invoke SoundFx.SOUND_NAME.play().
 * 3. You might optionally invoke the static method SoundFx.init() to pre-load all the
 * sound files, so that the play is not paused while loading the file for the first time.
 * 4. You can use the static variable SoundFx.volume to mute the sound.
 */
public enum SoundFx {
    WALK("src/com/totaleclipse/music/walk.wav"),   // walk
    MUSIC("src/com/totaleclipse/music/from-the-dust-cosmos.wav"),
    TOTALECLIPSE("src/com/totaleclipse/music/totaleclipse.wav");
    // Nested class for specifying volume
    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.LOW;


    // Each sound effect has its own clip, loaded with its own sound file.
    private Clip clip;
    public static boolean playing;
    public static boolean sound = true;
    // Constructor to construct each element of the enum with its own sound file.
    SoundFx(String soundFileName) {
        try {
            // Use URL (instead of File) to read from disk and JAR.
            URL url = this.getClass().getClassLoader().getResource(soundFileName);
            // Set up an audio input stream piped from the sound file.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFileName).getAbsoluteFile());
            // Get a clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Play or Re-play the sound effect from the beginning, by rewinding.
    public void play() {
        boolean gameStartNotPlayed = true;
        if (volume != Volume.MUTE) {
            if (clip.isRunning())
                clip.stop();   // Stop the player if it is still running
            clip.setFramePosition(0); // rewind to the beginning
            if(gameStartNotPlayed){
                clip.setFramePosition(10465000);
                gameStartNotPlayed = false;
            }
            clip.start();     // Start playing
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if (volume == Volume.LOW) {
                gainControl.setValue(-30.0f);
            } else if (volume == Volume.MEDIUM) {
                gainControl.setValue(-20.0f);
            } else if (volume == Volume.HIGH) {
                gainControl.setValue(-10.0f);
            }
            if (this == MUSIC) {
                playing = true;
            }
        }
    }
    public void stop(){
        clip.stop();
        playing = false;
    }

    // Optional static method to pre-load all the sound files.
    static void init() {
        values(); // calls the constructor for all the elements
    }


//    public void playMusic() {
//        try {
//            AudioInputStream MUSIC =
//                    AudioSystem.getAudioInputStream(
//                            new File(soundFileName)
//                                    .getAbsoluteFile());
//
//            clip = AudioSystem.getClip();
//            clip.open(MUSIC);
//            clip.start();
//            playing = true;
//        } catch (UnsupportedAudioFileException e) {
//            e.printStackTrace();
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void stopMusic() {
//        clip.stop();
//        playing = false;
//    }
}