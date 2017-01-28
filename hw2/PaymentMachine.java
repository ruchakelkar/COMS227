package hw2;

/**
 * A PaymentMachine object has methods allowing a Ticket object to be updated to show when payment is made and the amount of payment due, and the machine also has methods to show if a transaction is in progress. 
 * @author RuchaKelkar
 *
 */

public class PaymentMachine 
{	
	/**
	 * A SimpleClock object that can reference the given SimpleClock object in order to find the time. 
	 */
	
	private SimpleClock clock;
	
	/**
	 * A variable that can keep track of the total money the Payment Machine has accumulated.
	 */
	
	private double accumulator; 
	
	/**
	 * A value that keeps track of whether a transaction is in progress or not. 
	 */
	
	private boolean isInProgress = false;
	
	/**
	 * A Ticket object that creates a ticket to reference the given ticket in order to find the ticket's start time and payment time.
	 */
	
	private Ticket ticket;
	
	/**
	 * The amount of payment that the patron has to pay for parking since the last payment time.
	 */
	
	private double paymentDue;
	
	/**
	 * Constructs a PaymentMachine that uses the given clock. Initially, total payments are 0.0.
	 * @param givenClock
	 * 		gives a temporary SimpleClock object that the PaymentMachine can use to reference time from
	 */
	
	public PaymentMachine(SimpleClock givenClock) 
	{
		clock = givenClock;
		accumulator = 0.0;
	}
	
	/**
	 *  Simulates inserting a ticket into the machine. After calling this method, the inProgress() method returns true until a subsequent call to ejectTicket(). Calling insertTicket() while a transaction is in progress has no effect. 
	 * @param t
	 * 		ticket inserted into the machine 
	 */

	public void insertTicket(Ticket t) 
	{
		ticket = t;
		isInProgress = true;
	}
	
	/**
	 * Returns a reference to the ticket currently in this machine, or null if no transaction is in progress.
	 * @return
	 * 		ticket if one is in the machine, null if no transaction is in progress
	 */

	public Ticket getCurrentTicket() 
	{
		if (isInProgress)
		{
			return ticket;
		}
		
		return null;
	}
	
	/**
	 * Returns true if there is currently a ticket in this machine, false otherwise.
	 * @return
	 * 		whether or not there is currently a ticket in the machine
	 */

	public boolean inProgress() 
	{
		return isInProgress;
	}
	
	/**
	 * Returns the payment due for the ticket currently in the machine, or 0.0 if no transaction is in progress. The payment due is based the current time (according to this machine's clock) and on the start time and payment time for the current ticket. 
	 * @return
	 * 		payment due for the ticket currently in the machine
	 */

	public double getPaymentDue() 
	{
		if (isInProgress)
		{
			paymentDue = ParkingRateUtil.calculateCost(clock.getTime() - ticket.getStartTime()); 
			
			if (ticket.getPaymentTime() != 0)
			{
				 paymentDue = paymentDue - ParkingRateUtil.calculateCost(ticket.getPaymentTime() - ticket.getStartTime()); 
			}
			return paymentDue;
		}
		else
		{
			return 0.0;
		}	
	}
	
	/**
	 * Updates the current ticket with the payment time and adds the payment amount to this machine's total. Also simulates a payment being made. If there is no transaction in progress, this method has no effect.
	 */

	public void makePayment() 
	{	
		if (ticket.getPaymentTime() != 0)
		{
			paymentDue = ParkingRateUtil.calculateCost(clock.getTime() - ticket.getStartTime()) - ParkingRateUtil.calculateCost(ticket.getPaymentTime() - ticket.getStartTime());
		}
		else
		{
			paymentDue = ParkingRateUtil.calculateCost(clock.getTime() - ticket.getStartTime());
		}
		
		ticket.setPaymentTime(clock.getTime());
		accumulator += paymentDue;
	}
	
	/**
	 * Simulates ejecting a ticket from this machine, after which another ticket can be inserted. If there is no transaction in progress, this method has no effect.
	 */

	public void ejectTicket() 
	{
		isInProgress = false;
	}
	
	/**
	 * Returns the total payments that have been made at this machine.
	 * @return
	 * 		number of total payments made at the machine
	 */

	public double getTotalPayments() 
	{
		return accumulator;
	}
}
