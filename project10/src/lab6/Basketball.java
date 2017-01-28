package lab6;

public class Basketball
{
	private boolean inflated;
	private double diameter;
	
	public Basketball(double givenDiameter)
	{
		inflated = false;
		diameter = givenDiameter;
	}

	public boolean isDribbleable()
	{
		return inflated;
	}

	public double getDiameter()
	{
		return diameter;		
	}

	public double getCircumference()
	{
		return 0;
	}

	public void inflate()
	{
		inflated = true;
	}
}
