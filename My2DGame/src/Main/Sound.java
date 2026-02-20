package Main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author dieu hoang
 */
public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    int volumeScale = 3; // 0. Mute, 1. Low, 2. Medium, 3. High
    float volume;

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
        soundURL[9] = getClass().getResource("/res/Sound/cursor.wav");
        soundURL[10] = getClass().getResource("/res/Sound/burning.wav");
        soundURL[11] = getClass().getResource("/res/Sound/cuttree.wav");
        soundURL[12] = getClass().getResource("/res/Sound/gameover.wav");

    }
    // chu y vi tri luu file res

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
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

    public void checkVolume() {
        switch (volumeScale) {
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -20f;
                break;
            case 2:
                volume = -12f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5:
                volume = 6f;
                break;
        }
        if (clip != null) {
            // Apply gain if available
            if (fc != null) {
                try {
                    fc.setValue(volume);
                } catch (Exception e) {
                    // Ignore if setting volume fails for current clip
                }
            }

            // If volumeScale set to 0, stop playback to fully mute
            if (volumeScale == 0) {
                try {
                    if (clip.isRunning()) {
                        clip.stop();
                    }
                } catch (Exception e) {
                    // ignore
                }
            } else {
                // If previously stopped and volume > 0, resume playback
                try {
                    if (!clip.isRunning()) {
                        clip.start();
                    }
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }
}
