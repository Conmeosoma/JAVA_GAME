package Entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple localization manager. Game texts are looked up by key and can be
 * switched between different languages. Currently only English and Spanish
 * are provided as examples; add more entries or load from files as needed.
 */
public class Language {
  public enum Lang {
    ENGLISH,
    SPANISH,
    VIETNAMESE

  }

  private Lang currentLang;
  private Map<String, String> map; // khai báo map để lưu trữ các cặp key-value cho localization

  public Language() {
    map = new HashMap<>();
    currentLang = Lang.ENGLISH;
    loadLanguage(currentLang);
  }

  public Lang getLanguage() {
    return currentLang;
  }

  public void setLanguage(Lang lang) {
    if (lang != null && lang != currentLang) {
      currentLang = lang;
      loadLanguage(lang);
    }
  }

  private void loadLanguage(Lang lang) {
    map.clear();
    switch (lang) {
      case ENGLISH:
        // title screen
        map.put("title.gameName", "Blue Boy Adventure");
        map.put("menu.newGame", "NEW GAME");
        map.put("menu.loadGame", "LOAD GAME");
        map.put("menu.quit", "QUIT");
        map.put("menu.class.select", "Select your class!");
        map.put("menu.class.fighter", "Fighter");
        map.put("menu.class.thief", "Thief");
        map.put("menu.class.sorcerer", "Sorcerer");
        map.put("menu.back", "Back");

        // pause / game over
        map.put("pause.gamePaused", "GAME PAUSED");
        map.put("gameover.title", "Game Over");
        map.put("gameover.retry", "Retry");
        map.put("gameover.quit", "Quit");

        // options
        map.put("options.title", "Options");
        map.put("options.fullScreen", "Full Screen");
        map.put("options.music", "Music");
        map.put("options.se", "SE");
        map.put("options.language", "Language");
        map.put("options.controls", "Controls");
        map.put("options.endGame", "End Game");
        map.put("options.saveMessage", "The change will take \neffect after restarting \nthe game.");
        map.put("options.quitConfirm", "Quit the game and \nreturn to the title screen?");
        map.put("dialog.yes", "Yes");
        map.put("dialog.no", "No");
        map.put("options.escBack", "[ESC] Back");

        // status screen labels
        map.put("status.level", "Level");
        map.put("status.life", "Life");
        map.put("status.mana", "Mana");
        map.put("status.strength", "Strength");
        map.put("status.dexterity", "Dexterity");
        map.put("status.attack", "Attack");
        map.put("status.defence", "Defence");
        map.put("status.exp", "Exp");
        map.put("status.nextLevel", "Next Level");
        map.put("status.coin", "Coin");
        map.put("status.weapon", "Weapon");
        map.put("status.shield", "Shield");

        // trade
        map.put("trade.buy", "Buy");
        map.put("trade.sell", "Sell");
        map.put("trade.leave", "Leave");
        map.put("trade.yourCoin", "Your Coin: ");

        // controls screen
        map.put("controls.move", "Move");
        map.put("controls.confirmAttack", "Confirm/Attack");
        map.put("controls.shootCast", "Shoot/Cast");
        map.put("controls.character", "Character Screen");
        map.put("controls.pause", "Pause");
        map.put("controls.options", "Options");

        break;
      case SPANISH:
        map.put("title.gameName", "Aventura del Chico Azul");
        map.put("menu.newGame", "NUEVO JUEGO");
        map.put("menu.loadGame", "CARGAR JUEGO");
        map.put("menu.quit", "SALIR");
        map.put("menu.class.select", "¡Selecciona tu clase!");
        map.put("menu.class.fighter", "Luchador");
        map.put("menu.class.thief", "Ladrón");
        map.put("menu.class.sorcerer", "Hechicero");
        map.put("menu.back", "Atrás");

        map.put("pause.gamePaused", "JUEGO PAUSADO");
        map.put("gameover.title", "Fin del Juego");
        map.put("gameover.retry", "Reintentar");
        map.put("gameover.quit", "Salir");

        map.put("options.title", "Opciones");
        map.put("options.fullScreen", "Pantalla Completa");
        map.put("options.music", "Música");
        map.put("options.se", "EFX");
        map.put("options.language", "Idioma");
        map.put("options.controls", "Controles");
        map.put("options.endGame", "Terminar Juego");
        map.put("options.saveMessage", "El cambio surtirá \nefecto después de \nreiniciar el juego.");
        map.put("options.quitConfirm", "¿Salir del juego y \nvolver a la pantalla \ntítulo?");
        map.put("dialog.yes", "Sí");
        map.put("dialog.no", "No");
        map.put("options.escBack", "[ESC] Atrás");

        map.put("status.level", "Nivel");
        map.put("status.life", "Vida");
        map.put("status.mana", "Maná");
        map.put("status.strength", "Fuerza");
        map.put("status.dexterity", "Destreza");
        map.put("status.attack", "Ataque");
        map.put("status.defence", "Defensa");
        map.put("status.exp", "Exp");
        map.put("status.nextLevel", "Siguiente Nivel");
        map.put("status.coin", "Moneda");
        map.put("status.weapon", "Arma");
        map.put("status.shield", "Escudo");

        map.put("trade.buy", "Comprar");
        map.put("trade.sell", "Vender");
        map.put("trade.leave", "Salir");
        map.put("trade.yourCoin", "Tu Moneda: ");

        map.put("controls.move", "Mover");
        map.put("controls.confirmAttack", "Confirmar/Atacar");
        map.put("controls.shootCast", "Disparar/Lanzar");
        map.put("controls.character", "Pantalla de Personaje");
        map.put("controls.pause", "Pausa");
        map.put("controls.options", "Opciones");
        break;
      case VIETNAMESE:
        map.put("title.gameName", "HIT Phieu Luu");
        map.put("menu.newGame", "TRO CHOI MOI");
        map.put("menu.loadGame", "TAI TRO CHOI");
        map.put("menu.quit", "THOAT");
        map.put("menu.class.select", "Chon lop nhan vat!");
        map.put("menu.class.fighter", "Chien Binh");
        map.put("menu.class.thief", "Dao Tac");
        map.put("menu.class.sorcerer", "Phap Su");
        map.put("menu.back", "Quay Lai");

        map.put("pause.gamePaused", "TAM DUNG");
        map.put("gameover.title", "Tro Choi Ket Thuc");
        map.put("gameover.retry", "Choi Lai");
        map.put("gameover.quit", "Thoat");

        map.put("options.title", "Tuy Chon");
        map.put("options.fullScreen", "Toan Man Hinh");
        map.put("options.music", "Nhac");
        map.put("options.se", "Am Hieu");
        map.put("options.language", "Ngon Ngu");
        map.put("options.controls", "Dieu Khien");
        map.put("options.endGame", "Ket Thuc Tro Choi");
        map.put("options.saveMessage", "Thay doi se co hieu luc\nsau khi khoi dong lai tro choi.");
        map.put("options.quitConfirm", "Thoat tro choi va\nquay ve man hin chinh?");
        map.put("dialog.yes", "Co");
        map.put("dialog.no", "Khong");
        map.put("options.escBack", "[ESC] Quay Lai");

        map.put("status.level", "Cap");
        map.put("status.life", "Sinh Luc");
        map.put("status.mana", "Noi Luc");
        map.put("status.strength", "Suc Manh");
        map.put("status.dexterity", "Nhanh Nhen");
        map.put("status.attack", "Tan Cong");
        map.put("status.defence", "Phong Ngu");
        map.put("status.exp", "Kinh Nghiem");
        map.put("status.nextLevel", "Cap Tiep Theo");
        map.put("status.coin", "Tien");
        map.put("status.weapon", "Vu Khi");
        map.put("status.shield", "Khien");

        map.put("trade.buy", "Mua");
        map.put("trade.sell", "Ban");
        map.put("trade.leave", "Roi");
        map.put("trade.yourCoin", "Tien cua ban: ");

        map.put("controls.move", "Di chuyen");
        map.put("controls.confirmAttack", "Xac nhan/Tan cong");
        map.put("controls.shootCast", "Ban/Phep");
        map.put("controls.character", "Man hinh nhan vat");
        map.put("controls.pause", "Tam dung");
        map.put("controls.options", "Tuy Chon");
        break;
    }
  }

  // helper keys for language list display
  public String languageDisplayName(Lang lang) {
    switch (lang) {
      case ENGLISH:
        return "English";
      case SPANISH:
        return "Español";
      case VIETNAMESE:
        return "Tiếng Việt";
      default:
        return lang.name();
    }
  }

  public String get(String key) {
    return map.getOrDefault(key, key);
  }
}
