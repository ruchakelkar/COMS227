package hw2;

/**
 * In the ExitMachine, you insert your ticket upon leaving and the machine determines if the length of time passed from paying the parking fee till leaving the parking garage exceeds the grace period time limit.
 * @author RuchaKelkar
 *  
 */

public class ExitMachine 
{
	
	/**
	 *  A SimpleClock object that can reference the given SimpleClock object in order to find the time. 
	 */
	
	private SimpleClock clock;
	
	/**
	 * Total number of successful exits. 
	 */
	
	private int exitCount;
	
	/**
	 * Constructs an ExitMachine that uses the given clock and has an initial count of zero. 
	 * @param givenClock
	 * 		gives a temporary SimpleClock object that the ExitMachine can use to find the time
	 */
	
	public ExitMachine(SimpleClock givenClock)
	{
		exitCount = 0;	
		clock = givenClock;
	}
	
	
	/**
	 * Stimulates inserting a ticket into this machine.
	 * @param t
	 * 		ticket inserted into ExitMachine
	 * @return
	 * 		whether or not the ticket is still within the 15 minute grace period after payment has been made
	 */
	
	public boolean insertTicket(Ticket t)
	{		
		if ((clock.getTime() - t.getPaymentTime()) > 0 && t.getPaymentTime() != 0 && (clock.getTime() - t.getPaymentTime()) <= ParkingRateUtil.EXIT_TIME_LIMIT)
		{
			exitCount ++;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Returns a count of the total number of successful exits. A "successful exit" is defined to be a call to insertTicket() that returns true.
	 * @return
	 * 		count of the total number of successful exits
	 */
	
	public int getExitCount()
	{
		return exitCount;
	}
	
}