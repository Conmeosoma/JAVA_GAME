package Entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple localization manager. Game texts are looked up by key and can be
 * switched between different languages. Currently only English and Spanish are
 * provided as examples; add more entries or load from files as needed.
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
        map.put("title.gameName", "HIT Phiêu Lưu");
        map.put("menu.newGame", "TRÒ CHƠI MỚI");
        map.put("menu.loadGame", "TẢI TRÒ CHƠI");
        map.put("menu.quit", "THOÁT");
        map.put("menu.class.select", "Chọn lớp nhân vật!");
        map.put("menu.class.fighter", "Chiến Binh");
        map.put("menu.class.thief", "Đạo Tặc");
        map.put("menu.class.sorcerer", "Pháp Sư");
        map.put("menu.back", "Quay Lại");

        map.put("pause.gamePaused", "TẠM DỪNG");
        map.put("gameover.title", "Trò Chơi Kết Thúc");
        map.put("gameover.retry", "Chơi Lại");
        map.put("gameover.quit", "Thoát");

        map.put("options.title", "Tùy Chọn");
        map.put("options.fullScreen", "Toàn Màn Hình");
        map.put("options.music", "Nhạc");
        map.put("options.se", "Âm Hiệu");
        map.put("options.language", "Ngôn Ngữ");
        map.put("options.controls", "Điều Khiển");
        map.put("options.endGame", "Kết Thúc Trò Chơi");
        map.put("options.saveMessage", "Thay đổi sẽ có hiệu lực\nsau khi khởi động lại trò chơi.");
        map.put("options.quitConfirm", "Thoát trò chơi và\nquay về màn hình chính?");
        map.put("dialog.yes", "Có");
        map.put("dialog.no", "Không");
        map.put("options.escBack", "[ESC] Quay Lại");

        map.put("status.level", "Cấp");
        map.put("status.life", "Sinh Lực");
        map.put("status.mana", "Nội Lực");
        map.put("status.strength", "Sức Mạnh");
        map.put("status.dexterity", "Nhanh Nhẹn");
        map.put("status.attack", "Tấn Công");
        map.put("status.defence", "Phòng Ngự");
        map.put("status.exp", "Kinh Nghiệm");
        map.put("status.nextLevel", "Cấp Tiếp Theo");
        map.put("status.coin", "Tiền");
        map.put("status.weapon", "Vũ Khí");
        map.put("status.shield", "Khiên");

        map.put("trade.buy", "Mua");
        map.put("trade.sell", "Bán");
        map.put("trade.leave", "Rời");
        map.put("trade.yourCoin", "Tiền của bạn: ");

        map.put("controls.move", "Di chuyển");
        map.put("controls.confirmAttack", "Xác nhận/Tấn công");
        map.put("controls.shootCast", "Bắn/Phép");
        map.put("controls.character", "Màn hình nhân vật");
        map.put("controls.pause", "Tạm dừng");
        map.put("controls.options", "Tùy Chọn");
        break;
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
