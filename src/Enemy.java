import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
import java.util.Random;

public class Enemy
  extends GameObject
{
  Random r = new Random();
  int aiLevel;
  
  public Enemy(int x, int y, int size, Color c, int level, int ai)
  {
    super(x, y, size, c, level);
    this.aiLevel = ai;
    this.isEnemy = true;
  }
  
  public void draw(Graphics2D win, GameObject enemy, ArrayList a, ArrayList<GameObject> blocks)
  {
    ai(this.healthLevel, enemy, blocks);
    super.draw(win, a);
    sight1();
    win.setColor(Color.RED);
    win.draw(this.sight);
  }
  
  public void ai(int level, GameObject enemy, ArrayList<GameObject> blocks)
  {
    if (level > 0)
    {
      if (this.sight.intersects(enemy)) {
        fire(10, Color.RED);
      }
      int dir = this.r.nextInt(4);
      if (enemy.sight.intersects(this)) {
        for (int i = 0; i < level; i++) {
          accelerate(dir);
        }
      }
    }
    if (this.aiLevel == 1)
    {
      double dydx = (getCenterY() - enemy.getCenterY()) / (getCenterX() - enemy.getCenterX());
      if (getCenterX() - enemy.getCenterX() > 0.0D) {
        this.direction = (3.141592653589793D + Math.atan(dydx));
      }
      if (getCenterX() - enemy.getCenterX() < 0.0D) {
        this.direction = Math.atan(dydx);
      }
    }
    else
    {
      aim(true);
    }
  }
}
