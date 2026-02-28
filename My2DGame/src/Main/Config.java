package Main;

import java.io.*;
import Entity.Language;

public class Config {
  GamePanel gp;

  public Config(GamePanel gp) {
    this.gp = gp;
  }

  public void saveConfig() {
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

      // Full Screen
      if (gp.fullScreenOn == true) {
        bw.write("On");
      }
      if (gp.fullScreenOn == false) {
        bw.write("Off");
      }
      bw.newLine();

      // Music Volume
      bw.write(String.valueOf(gp.music.volumeScale));
      bw.newLine();

      // SE Volume
      bw.write(String.valueOf(gp.se.volumeScale));
      bw.newLine();

      // Language
      bw.write(gp.language.getLanguage().name());
      bw.newLine();

      bw.close();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void loadConfig() {
    try {
      BufferedReader br = new BufferedReader(new FileReader("config.txt"));

      String s = br.readLine();

      // Full Screen
      if (s != null && s.equals("On")) {
        gp.fullScreenOn = true;
      }
      if (s != null && s.equals("Off")) {
        gp.fullScreenOn = false;
      }

      // Music Volume
      s = br.readLine();
      if (s != null) {
        gp.music.volumeScale = Integer.parseInt(s);
      }

      // SE Volume
      s = br.readLine();
      if (s != null) {
        gp.se.volumeScale = Integer.parseInt(s);
      }

      // Language
      s = br.readLine();
      if (s != null) {
        try {
          gp.language.setLanguage(Language.Lang.valueOf(s));
        } catch (IllegalArgumentException e) {
          // unknown entry, ignore
        }
      }

      br.close();

    } catch (FileNotFoundException e) {
      // config.txt not found, use default values — this is normal on first run
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
