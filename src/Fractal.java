import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;

public class Fractal
{
  Line2D.Double l3 = new Line2D.Double(400.0D, 550.0D, 400.0D, 400.0D);
  double angle = 0.0D;
  int timer = 1;
  int timerDelay = 100;
  
  public void moveAndDraw(Graphics2D win)
  {
    rgbtime(win, 200, 500);
    tree(win, 12, this.l3, this.angle);
    this.angle += 0.01D;
  }
  
  public void tree(Graphics2D win, int n, Line2D.Double l, double angle)
  {
    win.draw(l);
    double theta = 0.0D;
    if (n >= 0)
    {
      double x1 = l.x1;double y1 = l.y1;double x2 = l.x2;double y2 = l.y2;
      double length = 2.0D * Math.sqrt(Math.pow(x2 - x1, 2.0D) + Math.pow(y2 - y1, 2.0D)) / 3.0D;
      if (x1 != x2)
      {
        theta = Math.atan((l.getX2() - l.getX1()) / (l.getY2() - l.getY1())) + 1.5707963267948966D;
        if (y2 >= y1) {
          theta += 3.141592653589793D;
        }
      }
      else if (y1 > y2)
      {
        theta = 1.5707963267948966D;
      }
      else if (y1 < y2)
      {
        theta = -1.5707963267948966D;
      }
      Line2D.Double l1 = new Line2D.Double(x2, y2, x2 + length * Math.cos(angle + theta), y2 - length * Math.sin(angle + theta));
      Line2D.Double l2 = new Line2D.Double(x2, y2, x2 + length * Math.cos(angle - theta), y2 + length * Math.sin(angle - theta));
      tree(win, n - 1, l1, angle);
      tree(win, n - 1, l2, angle);
    }
  }
  
  public void rgbtime(Graphics2D win, int timerDelay, int start)
  {
    boolean dir = true;
    if (dir) {
      this.timer += 1;
    } else {
      this.timer -= 1;
    }
    if (this.timer > timerDelay) {
      dir = false;
    }
    if (this.timer == 0) {
      dir = true;
    }
    win.setColor(Color.getHSBColor((this.timer + start) / 1000.0F, 1.0F, 1.0F));
  }
}
