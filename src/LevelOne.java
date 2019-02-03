import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class LevelOne
  extends Level
{
  GameObject e1 = new Enemy(700, 200, 50, Color.MAGENTA, 1, 0);
  
  public LevelOne()
  {
    super(1);
    
    this.players.add(this.e1);
    this.players.add(this.g);
    this.enemies.add(this.e1);
    this.control.create();
  }
  
  public void moveAndDraw(Graphics2D win)
  {
    win.setColor(Color.WHITE);
    win.fill(this.bg);
    win.setColor(Color.BLACK);
    win.draw(this.border);
    this.control.moveAndDraw(win);
    super.moveAndDraw(win);
  }
}
