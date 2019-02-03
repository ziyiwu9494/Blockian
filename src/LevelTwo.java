import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class LevelTwo
  extends Level
{
  GameObject e1 = new Enemy(700, 200, 50, Color.MAGENTA, 3, 0);
  GameObject e2 = new Enemy(300, 200, 50, Color.MAGENTA, 3, 0);
  
  public LevelTwo()
  {
    super(2);
    this.players.add(this.e1);
    this.players.add(this.g);
    this.players.add(this.e2);
    this.enemies.add(this.e1);
    this.enemies.add(this.e2);
    this.control.create();
  }
  
  public void moveAndDraw(Graphics2D win)
  {
    if (this.setup)
    {
      for (int i = 0; i < 5; i++) {
        this.g.levelUp();
      }
      this.setup = false;
    }
    win.setColor(Color.WHITE);
    win.fill(this.bg);
    win.setColor(Color.BLACK);
    win.draw(this.border);
    this.control.moveAndDraw(win);
    super.moveAndDraw(win);
  }
}
