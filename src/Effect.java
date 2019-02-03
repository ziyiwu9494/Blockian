import java.awt.Color;
import java.util.ArrayList;

public class Effect
{
  ArrayList<Bullet> effect;
  
  public Effect()
  {
    this.effect = new ArrayList();
  }
  
  public void effect(Color c, int x, int y, int size, double direction)
  {
    for (int i = 0; i < 30; i++) {
      this.effect.add(new Bullet(x, y, size, size, c, 20, direction - 15.0D + i, 0));
    }
  }
}
