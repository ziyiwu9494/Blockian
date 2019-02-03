import java.awt.Color;
import java.awt.Graphics2D;

public class Text
{
  int timer = 0;
  int timerDelay = 100;
  boolean dir = true;
  
  public void flashing(Graphics2D win, String type, float h)
  {
    if (type == "Breathing")
    {
      if (this.dir) {
        this.timer += 1;
      }
      if (!this.dir) {
        this.timer -= 1;
      }
      if (this.timer == this.timerDelay) {
        this.dir = false;
      }
      if (this.timer == 0) {
        this.dir = true;
      }
      win.setColor(Color.getHSBColor(h, this.timer / 100.0F, 1.0F));
    }
  }
  
  public void rgbtime(Graphics2D win, int timerDelay, int start)
  {
    if (this.dir) {
      this.timer += 1;
    } else {
      this.timer -= 1;
    }
    if (this.timer > timerDelay) {
      this.dir = false;
    }
    if (this.timer == 0) {
      this.dir = true;
    }
    win.setColor(Color.getHSBColor((this.timer + start) / 1000.0F, 1.0F, 1.0F));
  }
  
  public Color rgbtime2(Graphics2D win, int timerDelay, int start)
  {
    if (this.dir) {
      this.timer += 1;
    } else {
      this.timer -= 1;
    }
    if (this.timer > timerDelay) {
      this.dir = false;
    }
    if (this.timer == 0) {
      this.dir = true;
    }
    return Color.getHSBColor((this.timer + start) / 100.0F, 1.0F, 1.0F);
  }
}
