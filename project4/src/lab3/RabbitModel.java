package lab3;

/**
 * A RabbitModel is used to simulate the growth
 * of a population of rabbits. 
 */
public class RabbitModel
{
  private int pop;
  private int lastYear;
  private int yearBefore;
  
  /**
   * Constructs a new RabbitModel.
   */
  public RabbitModel()
  {
    pop = 0;
    lastYear = 1;
    yearBefore = 0;
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
    pop = lastYear + yearBefore;
    yearBefore = lastYear;
    lastYear = lastYear + 1;
    
  }
  
  /**
   * Sets or resets the state of the model to the 
   * initial conditions.
   */
  public void reset()
  {
    pop = 0;
  }
}