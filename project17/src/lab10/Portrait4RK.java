package lab10;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Portrait4RK extends Portrait
{
	public Portrait4RK()
	{
		super(0.32);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		int midX=getWidth()/2;
		
		//eyes!
		int eyeRadius=(int)(0.05*SIZE);
		g.fillOval(midX-eyeRadius-13, headRadius-eyeRadius*2, 2*eyeRadius, 2*eyeRadius);
		g.fillOval(midX-eyeRadius+13, headRadius-eyeRadius*2, 2*eyeRadius, 2*eyeRadius);
		
		//smile!
		int smileRadius=(int)(0.5*headRadius);
		g.drawArc(midX - smileRadius, (int)(1.25 * headRadius), smileRadius * 2,
		        smileRadius , 0, -180);
	}	
}


