package lab10;

import java.awt.Graphics;

public class Portrait4DMS extends Portrait{
	
	public Portrait4DMS()
	{
		super(.2);
	}
	
	 @Override
	  public void paintComponent(Graphics g)
	  {
		 super.paintComponent(g);

		    int midX = getWidth() / 2;

		    // his eyes bruh
		    int innerRadius = (int)(0.04 * SIZE);

		    g.drawOval(midX - innerRadius - 7, headRadius - innerRadius * 2, 2 * innerRadius,
			        2 * innerRadius);
		    g.drawOval(midX - innerRadius + 7, headRadius - innerRadius * 2, 2 * innerRadius,
			        2 * innerRadius);
		    // I guess he's got a mask
		    g.drawLine(midX, 0, midX, headRadius + 4);
		    g.drawLine(midX - headRadius, headRadius + 4, midX + headRadius, headRadius + 4);

		    int smileRadius = (int)(0.5 * headRadius);
		    g.drawArc(midX - smileRadius, (int)(0.8 * headRadius), smileRadius * 2,
		        smileRadius * 2, 0, -180);
	  }

}