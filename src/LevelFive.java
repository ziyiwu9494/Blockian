import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class LevelFive
  extends Level
{
  GameObject e1 = new Enemy(700, 200, 50, Color.MAGENTA, 10, 0);
  GameObject e2 = new Enemy(300, 200, 50, Color.MAGENTA, 8, 0);
  GameObject e7 = new Enemy(700, 200, 50, Color.MAGENTA, 8, 0);
  GameObject e6 = new Enemy(300, 200, 50, Color.MAGENTA, 8, 0);
  GameObject e3 = new Enemy(400, 400, 50, Color.RED, 10, 1);
  GameObject e4 = new Enemy(400, 400, 50, Color.RED, 7, 1);
  GameObject e5 = new Enemy(400, 400, 50, Color.RED, 5, 1);
  Rectangle key = new Rectangle(500, 300, 20, 20);
  boolean leveled = false;
  boolean powerup = false;
  boolean x = false;
  int count = 300;
  Text t = new Text();
  
  public LevelFive()
  {
    super(5);
    this.players.add(this.e1);
    this.players.add(this.g);
    this.players.add(this.e2);
    this.players.add(this.e3);
    this.players.add(this.e4);
    this.players.add(this.e5);
    this.players.add(this.e6);
    this.players.add(this.e7);
    this.enemies.add(this.e1);
    this.enemies.add(this.e2);
    this.enemies.add(this.e3);
    this.enemies.add(this.e4);
    this.enemies.add(this.e5);
    this.enemies.add(this.e6);
    this.enemies.add(this.e7);
    
    this.control.create();
  }
  
  public void moveAndDraw(Graphics2D win)
  {
    if (this.setup)
    {
      for (int i = 0; i < 8; i++) {
        this.g.levelUp();
      }
      this.setup = false;
    }
    if ((!this.leveled) && 
      (this.g.intersects(this.key)))
    {
      this.g.levelUp();
      this.g.c = Color.YELLOW;
      this.leveled = true;
      this.x = true;
    }
    if (!this.x)
    {
      win.setColor(Color.WHITE);
      win.fill(this.bg);
      win.setColor(Color.BLACK);
      win.draw(this.border);
      if (!this.leveled)
      {
        this.t.rgbtime(win, 300, 0);
        win.fill(this.key);
      }
      this.control.moveAndDraw(win);
      super.moveAndDraw(win);
      if (this.enemies.size() == 0) {
        this.goToNext = true;
      }
    }
    if (this.x)
    {
      this.powerup = true;
      this.g.c = this.t.rgbtime2(win, 30, 0);
      this.count -= 1;
      if (this.count == 0)
      {
        this.x = false;
        this.powerup = false;
      }
    }
  }
}
