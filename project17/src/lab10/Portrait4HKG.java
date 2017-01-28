package lab10;
import java.awt.Graphics;

public class Portrait4HKG extends Portrait
{

  public Portrait4HKG()
  {
    super(0.2);
  }

  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    int midX = getWidth() / 2;

    int eyeRadius = (int)(0.03 * SIZE);
    g.fillOval(midX +6, headRadius - eyeRadius * 2, 2 * eyeRadius,
        2 * eyeRadius);
    g.fillOval(midX - 6, headRadius - eyeRadius * 2, 2 * eyeRadius,
            2 * eyeRadius);

    int smileRadius = (int)(0.7 * headRadius);
    g.drawArc(midX - smileRadius, (int)(0.8 * headRadius), smileRadius * 2,
        smileRadius, 0, -180);
  }
}