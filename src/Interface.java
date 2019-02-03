import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Interface
  implements MouseListener
{
  SoundDriver s1;
  ArrayList<Button> controls = new ArrayList();
  Point mouse = new Point();
  
  public Interface()
  {
    String[] sound = new String[1];
    sound[0] = "Click.wav";
    this.s1 = new SoundDriver(sound);
  }
  
  public void moveAndDraw(Graphics2D win)
  {
    for (Button x : this.controls) {
      x.moveAndDraw(win);
    }
  }
  
  public void create()
  {
    this.controls.add(new Button(900, 550, 100, 60, "Reset", Color.getHSBColor(0.1F, 1.0F, 1.0F)));
  }
  
  public void sandBoxControl()
  {
    this.controls.add(new Button(300, 550, 100, 60, "Enemy 1 Levels", Color.MAGENTA));
    this.controls.add(new Button(500, 550, 100, 60, "Enemy 2 Levels", Color.MAGENTA));
  }
  
  public void splashControl()
  {
    this.controls.add(new Button(900, 500, 150, 100, "Start", Color.CYAN));
  }
  
  public void advancement()
  {
    this.controls.add(new Button(1100, 550, 100, 60, "Next", Color.BLUE));
  }
  
  public void playerLevelControl() {}
  
  public String control()
  {
    for (int i = 0; i < this.controls.size(); i++) {
      if ((this.mouse != null) && 
        (((Button)this.controls.get(i)).contains(this.mouse)))
      {
        if (!this.s1.isPlaying(0)) {
          this.s1.play(0);
        }
        return ((Button)this.controls.get(i)).getName();
      }
    }
    return null;
  }
  
  public void mouseClicked(MouseEvent arg0) {}
  
  public void mouseEntered(MouseEvent arg0) {}
  
  public void mouseExited(MouseEvent arg0) {}
  
  public void mousePressed(MouseEvent arg0)
  {
    this.mouse = arg0.getPoint();
  }
  
  public void mouseReleased(MouseEvent arg0)
  {
    this.mouse = null;
  }
}