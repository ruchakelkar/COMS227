package lab3;

import java.util.Random;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel4
{
	Random rand = new Random();
	
  private int pop;
  private int randNum;
  
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel4()
  {
    pop = 2;
    randNum = rand.nextInt(44);
  }  
 
  /**
   * Returns the current number of rabbits.
   * @return
   *   current rabbit population
   */
  public int getPopulation()
  {
    return pop;
  }
  
  /**
   * Updates the population to simulate the
   * passing of one year.
   */
  public void simulateYear()
  {
    pop = pop + randNum;
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    pop = 2;
    randNum = rand.nextInt(44);
  }
}