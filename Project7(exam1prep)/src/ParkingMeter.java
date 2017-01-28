
public class ParkingMeter 
{
	private double total;
	private int minPerQ;
	private int maxTime;
	private int minLeft;
	
	public ParkingMeter(int minutesPerQuarter, int maximumTime)
	{
		minPerQ = minutesPerQuarter;
		maxTime = maximumTime;
		total = 0.0;
	}
	
	public void insertCoin(int howMany)
	{
		minLeft = Math.min(howMany * minPerQ, maxTime);
		total += howMany * 0.25;
	}
	public int getTimeRemaning()
	{
		return minLeft;
	}
	public void passTime(int minutes)
	{
		minLeft = Math.max(minLeft - minutes, 0);
	}
	public double getTotal()
	{
		return total;
	}
}
