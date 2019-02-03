import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Victory
{
  GameObject g;
  ArrayList<Effect> rad;
  SplashScreen end;
  Text colorController;
  int timer = 0;
  Effect e;
  boolean effect = false;
  boolean reset = false;
  Rectangle bg = new Rectangle(0, 0, 1300, 700);
  Rectangle border = new Rectangle(100, 100, 1000, 400);
  ArrayList<GameObject> a = new ArrayList();
  
  public Victory()
  {
    this.g = new GameObject(110, 300, 10, Color.YELLOW, 1);
    this.end = new SplashScreen();
    this.rad = new ArrayList();
    this.colorController = new Text();
    this.e = new Effect();
  }
  
  public void moveAndDraw(Graphics2D win, int x, int y, int score)
  {
    win.setColor(Color.WHITE);
    win.fill(this.bg);
    win.setColor(Color.BLACK);
    win.draw(this.border);
    this.g.setLocation(x, y);
    this.g.c = this.colorController.rgbtime2(win, 30, 50);
    this.g.aim(true);
    this.g.draw(win, this.a);
    if (this.timer % 20 == 0)
    {
      this.e.effect(this.colorController.rgbtime2(win, 30, 50), x, y, 5, 0.0D);
      this.effect = true;
    }
    if (this.timer > 200) {
      this.end.moveAndDraw3(win, score);
    }
    if (this.timer > 600) {
      this.reset = true;
    }
    if (this.effect) {
      for (Bullet b : this.e.effect) {
        b.moveAndDraw(win);
      }
    }
    this.timer += 1;
  }
}
