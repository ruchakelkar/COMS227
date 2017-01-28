package hw2;

/**
 * This is a "utility" class that contains the exit time grace period, and a method for calculating payment based on time. 
 * @author RuchaKelkar
 */

public class ParkingRateUtil 
{
	/**
	 * Time limit of the grace period given between paying the parking fee and exiting the garage.
	 */
	public static final int EXIT_TIME_LIMIT = 15;
	
	/**
	 * Empty constructor which should never be instantiated since ParkingRateUtil is a "utility" class. 
	 */
	
	private ParkingRateUtil()
	{
	}
	
	/**
	 * Returns the cost of parking for the given total number of minutes, based on the pdf rates for the MU garage. 
	 * @param minutes
	 * 		minutes the car has been parked in the garage for until payment time
	 * @return
	 * 		cost of the parking fee 
	 */
	public static double calculateCost(int minutes)
	{
		double cost = 0;
		if (minutes > 24 * 60)
		{
			cost = 11.50 * (minutes / (24 * 60)); 
			minutes = (int) Math.ceil(minutes % (24 * 60));
		}
		
		if (minutes > 9 * 60)
		{
			cost += 11.50;
		}
		else if (minutes > 8 * 60)
		{
			cost += 11.00;
		}
		else if (minutes > 7 * 60)
		{
			cost += 10.25;
		}
		else if (minutes > 0.5 * 60)
		{
			cost += 2.00 + (Math.ceil((double) minutes/60 - 1)) * 1.25; 
		}
		else
		{
			cost += 1.00;
		}
		
		return cost;
	}
	
}
