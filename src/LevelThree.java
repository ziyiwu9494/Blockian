import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class LevelThree
  extends Level
{
  GameObject e1 = new Enemy(700, 200, 50, Color.MAGENTA, 5, 0);
  GameObject e2 = new Enemy(300, 200, 50, Color.MAGENTA, 4, 1);
  
  public LevelThree()
  {
    super(3);
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
      for (int i = 0; i < 6; i++) {
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
