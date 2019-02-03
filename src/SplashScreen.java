import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class SplashScreen
{
  Fractal loading = new Fractal();
  Interface splashControl;
  Rectangle bg = new Rectangle(0, 0, 1300, 700);
  Text colorControl = new Text();
  boolean createSplashControl = true;
  boolean createAdvancement = true;
  private Font f1 = new Font("OCR A Extended", 0, 100);
  private Font f2 = new Font("OCR A Extended", 2, 30);
  private Font f3 = new Font("OCR A Extended", 2, 20);
  
  public SplashScreen()
  {
    this.splashControl = new Interface();
  }
  
  public void moveAndDraw(Graphics2D win)
  {
    if (this.createSplashControl)
    {
      this.splashControl.splashControl();
      this.createSplashControl = false;
    }
    win.setColor(Color.WHITE);
    win.fill(this.bg);
    this.loading.moveAndDraw(win);
    this.splashControl.moveAndDraw(win);
    this.loading.rgbtime(win, 300, 0);
    win.setFont(this.f1);
    win.drawString("BLOCKIAN", 640, 200);
    win.setFont(this.f2);
    win.drawString("Ziyi Wu", 700, 300);
  }
  
  public void moveAndDraw2(Graphics2D win)
  {
    if (this.createAdvancement)
    {
      this.splashControl.advancement();
      this.splashControl.controls.remove(0);
    }
    win.setColor(Color.WHITE);
    win.fill(this.bg);
    this.loading.moveAndDraw(win);
    this.splashControl.moveAndDraw(win);
    this.colorControl.flashing(win, "Breathing", 0.9F);
    win.setFont(this.f2);
    win.drawString("WASD to accelerate", 700, 100);
    win.drawString("RIGHT to aim clockwise ", 700, 170);
    win.drawString("LEFT to aim counterclockwise", 700, 220);
    win.drawString("SPACE to shoot", 700, 290);
    win.setFont(this.f3);
    win.drawString("Shoot the magenta ENEMIES", 700, 370);
    win.drawString("When all enemies are dead", 700, 400);
    win.drawString("the door to the next level appears", 700, 430);
    win.drawString("You gain points by hitting the enemy", 700, 480);
    win.drawString("You lose points over time or when you are hit", 700, 510);
    win.drawString("Friendly fire enabled, but acquring THE KEY TO INFINITE POWER disables friendly fire", 30, 600);
  }
  
  public void moveAndDraw3(Graphics2D win, int score)
  {
    this.colorControl.flashing(win, "Breathing", 0.3F);
    win.setFont(this.f1.deriveFont(2));
    win.drawString("FIN.", 400, 300);
    win.drawString("Score: " + score, 550, 400);
  }
  
  public void moveAndDraw4(Graphics2D win, int score)
  {
    win.setColor(Color.BLACK);
    win.fill(this.bg);
    this.colorControl.flashing(win, "Breathing", 0.1F);
    win.setFont(this.f1.deriveFont(2));
    win.drawString("YOU FAILED", 400, 300);
    win.drawString("Score: " + score, 550, 400);
  }
}
