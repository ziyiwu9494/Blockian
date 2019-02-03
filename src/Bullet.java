import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends GameObject
{
  SoundDriver s1;
  int range;
  double direction;
  int distancetraveled;
  int speed;
  int damage;
  int delay;
  boolean hit;
  
  public Bullet(int x, int y, int xsize, int ysize, Color c, int speed, double direction, int damage)
  {
    super(x, y, 10, c, 10);
    super.setSize(xsize, ysize);
    this.speedLevel = speed;
    this.xspeed = ((int)(speed * Math.cos(direction)));
    this.yspeed = ((int)(speed * Math.sin(direction)));
    this.direction = direction;
    this.speed = speed;
    this.damage = damage;
  }
  
  public void moveAndDraw(Graphics2D win)
  {
    move();
    win.setColor(this.c);
    win.fill(this);
    this.distancetraveled += this.speed;
  }
  
  public int damage(GameObject x)
  {
    this.delay += 1;
    if ((!this.hit) && 
      (intersects(x)) && 
      (this.delay >= 20))
    {
      x.effect.effect(Color.RED, x.x, x.y, this.damage / 2, this.direction);
      if (x.shield > 0) {
        x.shield -= this.damage;
      } else {
        x.health -= this.damage;
      }
      this.hit = true;
      x.yspeed += this.yspeed * 0.5D * this.damage / x.healthLevel;
      x.xspeed += this.xspeed * 0.5D * this.damage / x.healthLevel;
      return this.damage;
    }
    return 0;
  }
}
