import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Button
  extends Rectangle
{
  private String name;
  boolean active;
  private Color c;
  private Font f = new Font("Consolas", 1, 20);
  
  public Button(int x, int y, int width, int height, String aname, Color c)
  {
    super(x, y, width, height);
    this.name = aname;
    this.c = c;
  }
  
  public void moveAndDraw(Graphics2D win)
  {
    if (this.active) {
      win.setColor(this.c.brighter());
    }
    if (this.active) {
      win.setColor(this.c.darker());
    }
    win.fill(this);
    win.setColor(Color.WHITE);
    win.setFont(this.f);
    if (this.name.length() < 7)
    {
      win.drawString(this.name, (int)(getX() + getWidth() / 12.0D), (int)(getY() + getHeight() / 2.0D));
    }
    else
    {
      int x = 0;
      for (int i = 0; i < this.name.length(); i++)
      {
        x++;
        if (this.name.substring(i, i + 1).equals(" ")) {
          break;
        }
      }
      win.drawString(this.name.substring(0, x), (int)(getX() + getWidth() / 12.0D), (int)(getY() + getHeight() / 2.0D));
      win.drawString(this.name.substring(x), (int)(getX() + getWidth() / 12.0D), (int)(getY() + getHeight() / 2.0D) + 23);
    }
  }
  
  public void activate()
  {
    this.active = true;
  }
  
  public void deactivate()
  {
    this.active = false;
  }
  
  public String getName()
  {
    return this.name;
  }
}
