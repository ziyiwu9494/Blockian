import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SandBox
  extends Level
{
  public SandBox()
  {
    super(0);
    this.players.add(this.g);
    this.control.create();
  }
  
  public void mouseClicked(MouseEvent arg0) {}
  
  public void mouseEntered(MouseEvent arg0) {}
  
  public void mouseExited(MouseEvent arg0) {}
  
  public void mousePressed(MouseEvent arg0) {}
  
  public void mouseReleased(MouseEvent arg0) {}
  
  public void moveAndDraw(Graphics2D win)
  {
    win.setColor(Color.WHITE);
    win.fill(this.bg);
    win.setColor(Color.BLACK);
    win.draw(this.border);
    this.control.moveAndDraw(win);
    if (this.control.control() == "Start") {
      this.gameState = 1;
    }
    super.moveAndDraw(win);
  }
  
  public void createInterface() {}
}
