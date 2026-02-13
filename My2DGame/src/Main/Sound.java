/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author dieu hoang
 */
public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        // soundURL[0] = getClass().getResource("/res/Sound/HITAdventure.wav");
        soundURL[0] = getClass().getResource("/res/Sound/Interstellar – Hans Zimmer (mp3cut.net).wav");
        soundURL[1] = getClass().getResource("/res/Sound/coin.wav");
        soundURL[2] = getClass().getResource("/res/Sound/powerup.wav");
        soundURL[3] = getClass().getResource("/res/Sound/unlock.wav");
        soundURL[4] = getClass().getResource("/res/Sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/res/Sound/hitmonster.wav");
        soundURL[6] = getClass().getResource("/res/Sound/receivedamage.wav");
        soundURL[7] = getClass().getResource("/res/Sound/swingweapon.wav");
        soundURL[8] = getClass().getResource("/res/Sound/levelup.wav");

    }
    // chu y vi tri luu file res

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void pause() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void resume() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }
}
