package Object;

import Enity.Projectile;
import Main.GamePanel;

public class OBJ_FireBall extends Projectile {
  GamePanel gp;

  public OBJ_FireBall(GamePanel gp) {
    super(gp);
    this.gp = gp;
    name = "Fire Ball";
    speed = 5;
    maxLife = 80;
    life = maxLife;
    attack = 2;
    useCost = 1;
    alive = false;
    getImage();
  }

  public void getImage() {

  }

}
