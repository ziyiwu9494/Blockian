import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.io.PrintStream;
import java.util.ArrayList;

public class GameObject
  extends Rectangle
  implements KeyListener, MouseListener
{
  Line2D.Double sight = new Line2D.Double();
  Color c;
  int[] levels;
  int healthLevel;
  int shieldLevel;
  int shootSpeedLevel;
  int bulletLevel;
  int sizeLevel;
  int speedLevel;
  int shottimer;
  int shield;
  int health;
  int timer;
  double xspeed;
  double yspeed;
  double targetXSpeed;
  double targetYSpeed;
  ArrayList<Bullet> shotsFired;
  double direction;
  boolean isEnemy;
  boolean mouseMode;
  boolean laser;
  double previousXspeed;
  double previousYspeed;
  Rectangle shieldEffect;
  boolean[] movedirection = new boolean[4];
  boolean[] targetMoved = new boolean[4];
  boolean fire;
  boolean up;
  boolean down;
  Effect effect = new Effect();
  Rectangle healthBar;
  Rectangle target;
  
  public GameObject(int x, int y, int size, Color c, int level)
  {
    super(x, y, size, size);
    this.levels = new int[6];
    this.c = c;
    this.shotsFired = new ArrayList();
    
    this.levels[0] = this.healthLevel;
    this.levels[1] = this.shieldLevel;
    this.levels[2] = this.shootSpeedLevel;
    this.levels[3] = this.bulletLevel;
    this.levels[4] = this.sizeLevel;
    this.levels[5] = this.speedLevel;
    this.speedLevel = level;
    this.sizeLevel = level;
    this.shootSpeedLevel = level;
    this.shottimer = 30;
    this.healthLevel = level;
    this.bulletLevel = level;
    this.isEnemy = false;
    this.shieldLevel = level;
    this.shield = (this.shieldLevel * 10);
    this.health = (this.healthLevel * 10);
    this.shieldEffect = new Rectangle((int)(getX() + this.width / 2), (int)(getY() + this.height / 2), this.shield / this.sizeLevel, this.shield / this.sizeLevel);
    Rectangle healthBar = new Rectangle();
  }
  
  public void draw(Graphics2D win, ArrayList<GameObject> a)
  {
    if (this.laser) {
      this.laser = false;
    }
    for (int i = 0; i < this.movedirection.length; i++) {
      if (this.movedirection[i]) {
        accelerate(i);
      }
    }
    win.setColor(Color.CYAN);
    sight1();
    if (!this.isEnemy) {
      win.draw(this.sight);
    }
    this.healthBar = new Rectangle(this.x, this.y - 8, this.width * this.health / (this.healthLevel * 10), 5);
    win.setColor(Color.GREEN);
    win.fill(this.healthBar);
    for (int i = 0; i < this.effect.effect.size(); i++) {
      if (((Bullet)this.effect.effect.get(i)).distancetraveled > 200) {
        this.effect.effect.remove(i);
      } else {
        ((Bullet)this.effect.effect.get(i)).moveAndDraw(win);
      }
    }
    move();
    universalBounce(a);
    this.shieldEffect = new Rectangle((int)(getX() - this.shield / 2), (int)(getY() - this.shield / 2), this.shield + this.height, this.shield + this.width);
    this.shottimer += 1;
    setSize(50 - 4 * this.sizeLevel, 50 - 4 * this.sizeLevel);
    
    win.setColor(this.c);
    win.fill(this);
    for (int i = 0; i < this.shotsFired.size(); i++) {
      if ((((Bullet)this.shotsFired.get(i)).distancetraveled > 2000) || (((Bullet)this.shotsFired.get(i)).hit)) {
        this.shotsFired.remove(i);
      } else {
        ((Bullet)this.shotsFired.get(i)).moveAndDraw(win);
      }
    }
    if (this.shield > this.shieldLevel * 5)
    {
      win.setColor(Color.GREEN);
      win.draw(this.shieldEffect);
    }
    if ((this.shield <= this.shieldLevel * 5) && (this.shield > 0))
    {
      win.setColor(Color.RED);
      win.draw(this.shieldEffect);
    }
    friction(0.25D);
    for (int i = 0; i < this.movedirection.length; i++) {
      if (this.movedirection[i]) {
        accelerate(i);
      }
    }
    if (this.targetMoved[2]) {
      aim(true);
    }
    if (this.targetMoved[0])
    {
      aim(false);
      System.out.println("x");
    }
    if (this.fire) {
      fire(20, Color.BLUE);
    }
  }
  
  public void accelerate(int a)
  {
    if (a == 0) {
      this.xspeed += Math.sqrt(this.speedLevel) * 0.2D;
    }
    if (a == 1) {
      this.yspeed += Math.sqrt(this.speedLevel) * 0.2D;
    }
    if (a == 2) {
      this.xspeed -= Math.sqrt(this.speedLevel) * 0.2D;
    }
    if (a == 3) {
      this.yspeed -= Math.sqrt(this.speedLevel) * 0.2D;
    }
  }
  
  public void move()
  {
    if (getX() + this.xspeed > 1100.0D - super.getWidth())
    {
      this.x = (1100 - (int)super.getWidth());
      this.xspeed = (-1.0D * this.xspeed);
    }
    else if (getX() + this.xspeed < 100.0D)
    {
      this.x = 100;
      this.xspeed = (-1.0D * this.xspeed);
    }
    if (getY() + this.yspeed > 500.0D - super.getHeight())
    {
      this.y = (500 - (int)super.getHeight());
      this.yspeed = (-1.0D * this.yspeed);
    }
    else if (getY() + this.yspeed < 100.0D)
    {
      this.y = 100;
      this.yspeed = (-1.0D * this.yspeed);
    }
    else
    {
      translate((int)this.xspeed, 0);
    }
    translate(0, (int)this.yspeed);
    this.previousXspeed = this.xspeed;
    this.previousYspeed = this.yspeed;
  }
  
  public void targetMoved(int a)
  {
    if (a == 0) {
      this.targetXSpeed = 10.0D;
    } else if (a == 2) {
      this.targetXSpeed = -10.0D;
    } else {
      this.targetXSpeed = 0.0D;
    }
    if (a == 1) {
      this.targetYSpeed = 10.0D;
    } else if (a == 3) {
      this.targetYSpeed = -10.0D;
    } else {
      this.targetYSpeed = 0.0D;
    }
    this.target.translate((int)this.targetXSpeed, (int)this.targetYSpeed);
  }
  
  public void leveling(int a)
  {
    this.levels[a] += 1;
  }
  
  public void levelUp()
  {
    this.speedLevel += 1;
    this.sizeLevel += 1;
    this.shootSpeedLevel += 1;
    this.healthLevel += 1;
    this.bulletLevel += 1;
    this.shieldLevel += 1;
    this.shield = (this.shieldLevel * 10);
    this.health = (this.healthLevel * 10);
  }
  
  public void friction(double resistance)
  {
    if (this.xspeed != 0.0D) {
      if (this.xspeed > 0.0D) {
        this.xspeed -= resistance;
      } else {
        this.xspeed += resistance;
      }
    }
    if (this.yspeed != 0.0D) {
      if (this.yspeed > 0.0D) {
        this.yspeed -= resistance;
      } else {
        this.yspeed += resistance;
      }
    }
  }
  
  public void aim(boolean a)
  {
    if (this.isEnemy)
    {
      if (!a) {
        this.direction += 0.01D * this.speedLevel;
      }
      if (a) {
        this.direction -= 0.01D * this.speedLevel;
      }
    }
    else
    {
      if (!a) {
        this.direction += 0.01D * Math.sqrt(this.speedLevel);
      }
      if (a) {
        this.direction -= 0.01D * Math.sqrt(this.speedLevel);
      }
    }
  }
  
  public void fire(int speed, Color color)
  {
    if (this.shottimer > 60 - 6 * this.shootSpeedLevel)
    {
      this.laser = true;
      
      this.shotsFired.add(new Bullet((int)getX() + this.width / 2 - 5, (int)getY() + this.height / 2 - 5, (int)(4.0D * Math.sqrt(this.bulletLevel)), (int)(4.0D * Math.sqrt(this.bulletLevel)), color, speed, this.direction, this.bulletLevel));
      this.shottimer = 1;
      this.xspeed -= 25.0D / (this.bulletLevel + 3.0D) * Math.cos(this.direction);
      this.yspeed -= 25.0D / (this.bulletLevel + 3.0D) * Math.sin(this.direction);
    }
  }
  
  public void sightline() {}
  
  public void sight()
  {
    if (!this.isEnemy)
    {
      double dy = getCenterY() - this.target.getCenterY();
      double dx = getCenterX() - this.target.getCenterX();
      double m = dy / dx;
      if (dx < 0.0D) {
        this.sight.setLine(getCenterX(), getCenterY(), getCenterX() + 1000.0D, getCenterY() + m * 1000.0D);
      }
      if (dx > 0.0D) {
        this.sight.setLine(getCenterX(), getCenterY(), getCenterX() - 1000.0D, getCenterY() - m * 1000.0D);
      }
    }
  }
  
  public void sight1()
  {
    this.sight.setLine(getCenterX(), getCenterY(), getCenterX() + 1000.0D * Math.cos(this.direction), getCenterY() + 1000.0D * Math.sin(this.direction));
  }
  
  public int getPerimeter()
  {
    return 2 * (this.width + this.height);
  }
  
  public boolean isDead()
  {
    if (this.health <= 0) {
      return true;
    }
    return false;
  }
  
  public int collisionDirection(Rectangle stationary, GameObject projectile)
  {
    int previousXPos = (int)(projectile.getX() - projectile.previousXspeed);
    int previousYPos = (int)(projectile.getY() - projectile.previousYspeed);
    int height = (int)projectile.getHeight();
    int width = (int)projectile.getWidth();
    int result = 0;
    if ((previousYPos + height <= stationary.getY()) && (projectile.getY() + height >= stationary.getY())) {
      result = 1;
    } else if ((previousXPos + width <= stationary.getX()) && (projectile.getX() + width >= stationary.getX())) {
      result = 2;
    } else if ((previousYPos >= stationary.getY() + stationary.height) && (projectile.getY() <= stationary.getY() + stationary.height)) {
      result = 3;
    }
    return result;
  }
  
  public void objectBounce(GameObject A, GameObject B)
  {
    if (A.intersects(B))
    {
      if ((collisionDirection(A, B) == 0) || (collisionDirection(A, B) == 2))
      {
        double temp = A.xspeed;
        A.xspeed = B.xspeed;
        B.xspeed = temp;
      }
      if ((collisionDirection(A, B) == 1) || (collisionDirection(A, B) == 3))
      {
        double temp = A.yspeed;
        A.yspeed = B.yspeed;
        B.yspeed = temp;
      }
    }
  }
  
  public void universalBounce(ArrayList<GameObject> a)
  {
    for (GameObject x : a) {
      objectBounce(this, x);
    }
  }
  
  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == 87) {
      this.movedirection[3] = true;
    }
    if (e.getKeyCode() == 65) {
      this.movedirection[2] = true;
    }
    if (e.getKeyCode() == 83) {
      this.movedirection[1] = true;
    }
    if (e.getKeyCode() == 68) {
      this.movedirection[0] = true;
    }
    if (e.getKeyCode() == 32) {
      this.fire = true;
    }
    if (e.getKeyCode() == 37) {
      this.targetMoved[2] = true;
    }
    if (e.getKeyCode() == 39) {
      this.targetMoved[0] = true;
    }
    if (e.getKeyCode() == 38) {
      this.targetMoved[3] = true;
    }
    if (e.getKeyCode() == 40) {
      this.targetMoved[1] = true;
    }
  }
  
  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == 87) {
      this.movedirection[3] = false;
    }
    if (e.getKeyCode() == 65) {
      this.movedirection[2] = false;
    }
    if (e.getKeyCode() == 83) {
      this.movedirection[1] = false;
    }
    if (e.getKeyCode() == 68) {
      this.movedirection[0] = false;
    }
    if (e.getKeyCode() == 32) {
      this.fire = false;
    }
    if (e.getKeyCode() == 37) {
      this.targetMoved[2] = false;
    }
    if (e.getKeyCode() == 39) {
      this.targetMoved[0] = false;
    }
    if (e.getKeyCode() == 38) {
      this.targetMoved[3] = false;
    }
    if (e.getKeyCode() == 40) {
      this.targetMoved[1] = false;
    }
  }
  
  public void keyTyped(KeyEvent e) {}
  
  public void mouseClicked(MouseEvent arg0) {}
  
  public void mouseEntered(MouseEvent arg0) {}
  
  public void mouseExited(MouseEvent arg0) {}
  
  public void mousePressed(MouseEvent arg0) {}
  
  public void mouseReleased(MouseEvent arg0) {}
}

