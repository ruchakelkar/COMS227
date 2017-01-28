package hw2;

/**
 * Simulates a ticket dispenser which dispenses a ticket to those who enter the parking garage. The ticket contains time stamps to record payment and exit counts.
 * @author RuchaKelkar
 * 
 */

public class TicketDispenser 
{

	/**
	 * A SimpleClock object that can reference the given SimpleClock object in order to find the time. 
	 */
	
	private SimpleClock clock;
	
	/**
	 * Constructs a TicketDispenser that uses the given clock. 
	 * @param givenClock
	 * 		gives a temporary SimpleClock object that the TicketDispenser can reference time from
	 */
	
	public TicketDispenser(SimpleClock givenClock)
	{
		clock = givenClock;
		takeTicket();
	}
	
	/**
	 * Constructs and returns a new Ticket object. The constructed ticket will have a start time based on the current value of the ticket dispenser's clock and a payment time of zero.
	 * @return
	 * 		new Ticket object
	 */
	
	public Ticket takeTicket()
	{
		/**
		 * A new Ticket object used to create a ticket to dispense, with the correct start time, and a payment time of zero.
		 */
		
		Ticket t = new Ticket(clock.getTime()); 
		t.setPaymentTime(0);
		return t;
	}
}
