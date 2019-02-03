import java.awt.Graphics2D;
import java.util.ArrayList;

public class Game
  extends GameDriverV2
{
  SoundDriver s1;
  int currentLevel = -2;
  int counter;
  SplashScreen s;
  int score;
  Victory end = new Victory();
  int deathTimer;
  int finalXPosition;
  int finalYPosition;
  ArrayList<Level> levels;
  Level zero = new SandBox();
  Level one = new LevelOne();
  Level two = new LevelTwo();
  Level three = new LevelThree();
  Level four = new LevelFour();
  Level five = new LevelFive();
  
  public Game()
  {
    this.s = new SplashScreen();
    this.levels = new ArrayList();
    this.levels.add(this.zero);
    this.levels.add(this.one);
    this.levels.add(this.two);
    this.levels.add(this.three);
    this.levels.add(this.four);
    this.levels.add(this.five);
    for (Level x : this.levels)
    {
      addMouseListener(x);
      addKeyListener(x);
      addKeyListener(x.g);
      addMouseListener(x.control);
    }
    addMouseListener(this.s.splashControl);
    String[] sound = new String[4];
    sound[0] = "Intro.wav";
    sound[1] = "Laser.wav";
    sound[2] = "FinalTheme.wav";
    sound[3] = "powerup.wav";
    this.s1 = new SoundDriver(sound);
  }
  
  public void draw(Graphics2D win)
  {
    if (!this.s1.isPlaying(2)) {
      this.s1.play(2);
    }
    for (int i = 0; i < 4; i++) {
      if (this.score < this.counter) {
        this.score += 1;
      }
    }
    for (int i = 0; i < 4; i++) {
      if (this.score > this.counter) {
        this.score -= 1;
      }
    }
    if (this.currentLevel == -2)
    {
      this.s.moveAndDraw(win);
      if (this.s.splashControl.control() == "Start")
      {
        this.currentLevel = -1;
        this.s1.play(0);
      }
    }
    if (this.currentLevel == -1)
    {
      this.s.moveAndDraw2(win);
      if (this.s.splashControl.control() == "Next") {
        this.currentLevel = 1;
      }
    }
    else if ((this.currentLevel > 0) && (this.currentLevel < 6))
    {
      ((Level)this.levels.get(this.currentLevel)).moveAndDraw(win);
      win.drawString("Score: " + this.score, 50, 50);
      for (GameObject x : ((Level)this.levels.get(this.currentLevel)).players) {
        if (x.laser) {
          this.s1.play(1);
        }
      }
      if ((this.currentLevel == 5) && 
        (((LevelFive)this.levels.get(this.currentLevel)).powerup) && 
        (!this.s1.isPlaying(3))) {
        this.s1.play(3);
      }
      if (((Level)this.levels.get(this.currentLevel)).goToNext)
      {
        this.counter += ((Level)this.levels.get(this.currentLevel)).levelScore;
        if (this.currentLevel == 5)
        {
          this.finalXPosition = ((int)((Level)this.levels.get(this.currentLevel)).g.getX());
          this.finalYPosition = ((int)((Level)this.levels.get(this.currentLevel)).g.getY());
        }
        this.currentLevel += 1;
      }
      if (this.currentLevel != 6)
      {
        if (((Level)this.levels.get(this.currentLevel)).death)
        {
          this.counter += ((Level)this.levels.get(this.currentLevel)).levelScore;
          this.counter -= 1000;
          this.currentLevel = 7;
        }
        if (((Level)this.levels.get(this.currentLevel)).control.control() == "Reset")
        {
          this.s1.play(1);
          reset();
        }
      }
    }
    if (this.currentLevel == 6)
    {
      this.end.moveAndDraw(win, this.finalXPosition, this.finalYPosition, this.score);
      if (!this.s1.isPlaying(3)) {
        this.s1.play(3);
      }
      if (this.end.reset) {
        reset();
      }
    }
    if (this.currentLevel == 7)
    {
      this.s.moveAndDraw4(win, this.score);
      this.deathTimer += 1;
      if (this.deathTimer > 300) {
        reset();
      }
    }
  }
  
  public void reset()
  {
    this.currentLevel = -2;
    this.counter = 0;
    this.score = 0;
    this.s = new SplashScreen();
    this.end = new Victory();
    this.zero = new SandBox();
    this.one = new LevelOne();
    this.two = new LevelTwo();
    this.three = new LevelThree();
    this.four = new LevelFour();
    this.five = new LevelFive();
    this.levels.clear();
    this.levels.add(this.zero);
    this.levels.add(this.one);
    this.levels.add(this.two);
    this.levels.add(this.three);
    this.levels.add(this.four);
    this.levels.add(this.five);
    for (Level x : this.levels)
    {
      addMouseListener(x);
      addKeyListener(x);
      addKeyListener(x.g);
      addMouseListener(x.control);
    }
    addMouseListener(this.s.splashControl);
  }
}