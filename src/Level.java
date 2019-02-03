import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JFrame;

public class Level
  implements KeyListener, MouseListener
{
  int gameState = 1;
  int levelScore = 100;
  int timer = 0;
  int scoreDelay = 100;
  int level;
  Rectangle bg = new Rectangle(0, 0, 1300, 700);
  Rectangle border = new Rectangle(100, 100, 1000, 400);
  Rectangle endingSquare = new Rectangle(1080, 250, 20, 100);
  ArrayList<GameObject> enemies = new ArrayList();
  ArrayList<GameObject> players = new ArrayList();
  Random R = new Random();
  GameObject g = new GameObject(110, 300, 10, Color.CYAN, 1);
  Interface control = new Interface();
  boolean goToNext = false;
  boolean death = false;
  boolean setup = true;
  boolean i = true;
  boolean fin = false;
  
  public Level(int l)
  {
    this.gameState = 0;
    this.level = l;
  }
  
  public static void main(String[] args)
  {
    JFrame j1 = new JFrame();
    j1.setDefaultCloseOperation(3);
    j1.setSize(1300, 700);
    j1.setTitle("Blockian");
    j1.add(new Game());
    j1.setVisible(true);
  }
  
  public void mouseClicked(MouseEvent arg0) {}
  
  public void mouseEntered(MouseEvent arg0) {}
  
  public void mouseExited(MouseEvent arg0) {}
  
  public void mousePressed(MouseEvent arg0) {}
  
  public void mouseReleased(MouseEvent arg0) {}
  
  public void keyPressed(KeyEvent arg0) {}
  
  public void keyReleased(KeyEvent arg0) {}
  
  public void keyTyped(KeyEvent arg0) {}
  
  public void moveAndDraw(Graphics2D win)
  {
    if (this.control.control() == "Mode")
    {
      if (this.g.mouseMode) {
        this.g.mouseMode = false;
      }
      if (!this.g.mouseMode) {
        this.g.mouseMode = true;
      }
    }
    win.setColor(Color.ORANGE);
    
    win.setColor(Color.BLACK);
    
    this.timer += 1;
    if (this.timer == this.scoreDelay)
    {
      this.levelScore -= 1;
      this.timer = 0;
    }
    win.setColor(Color.CYAN);
    win.drawString("Level Score: " + this.levelScore, 1000, 50);
    if (this.g.isDead()) {
      this.death = true;
    }
    for (GameObject y : this.enemies)
    {
	      for(Bullet x : y.shotsFired) {
		      for (GameObject z : this.enemies) {
		        if (z.shootSpeedLevel < 10) {
		          x.damage(z);
		        }
	      }
	      this.levelScore -= x.damage(this.g);
	    }
    }
    for (GameObject y : this.players)
    {
      for (Bullet x : this.g.shotsFired)
      {
        for (GameObject z : this.enemies) {
          this.levelScore += x.damage(z);
        }
        if (this.g.shootSpeedLevel != 10) {
          this.levelScore -= x.damage(this.g);
        }
      }
      if (y.isEnemy)
      {
        y.move();
        y.friction(0.25D);
        ((Enemy)y).draw(win, this.g, this.players, this.players);
        y.isDead();
      }
      if (!y.isEnemy) {
        y.draw(win, this.players);
      }
    }
    for (int i = 0; i < this.players.size(); i++) {
      if (((GameObject)this.players.get(i)).isDead()) {
        this.players.remove(i);
      }
    }
    for (int i = 0; i < this.enemies.size(); i++) {
      if (((GameObject)this.enemies.get(i)).isDead()) {
        this.enemies.remove(i);
      }
    }
    if ((this.enemies.size() == 0) && 
      (this.level != 5) && (this.level != 0)) {
      win.draw(this.endingSquare);
    }
    if ((this.enemies.size() == 0) && (this.g.intersects(this.endingSquare)) && 
      (this.level != 5) && (this.level != 0)) {
      this.goToNext = true;
    }
  }
}
