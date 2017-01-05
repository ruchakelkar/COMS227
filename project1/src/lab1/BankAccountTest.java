package lab1;

/**
 *  A bank account has a balance that can be changed by deposits and withdrawals.
 * */

public class BankAccountTest {
	
	private double balance;
	
	/**
	 * Constructs a bank account with 0 balance. 
	 */
	
	public BankAccountTest()
	{
		balance = 0;
	}
	
	/**
	 * Constructs a bank account with a given balance.
	 * @param initialBalance the initial balance
	
	 */
	
	public BankAccountTest(double initialBalance) 
	{
		balance = initialBalance;
	}
	
	/**
	 * Deposits money into the bank account. 
	 * @param amt the amount to be deposited
	 */
	
	public void deposit(double amt)
	{
		balance = balance + amt;
	}
	
	/**
	 * Withdraws money from the bank account. 
	 * @param amt the amount to be withdrawn
	 */
	
	public void withdraw(double amt)
	{
		balance = balance - amt;
	}
	
	/**
	 * Returns the balance of the bank account. 
	 * @return the current balance
	 */
	
	public double getBalance()
	{
		return balance;
	}

}
