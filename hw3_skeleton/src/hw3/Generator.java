package hw3;
import java.util.Random;

import api.Dot;

/**
 * Class encapsulating a mechanism for producing new Dot objects
 * for a game.
 * @author RuchaKelkar
 */
public class Generator
{
	/**
	 * Stores the dot type of the dot to be generated in generate(); type is set by the constructor with givenType. If the new dots are being generated with a random type, type will remain -1.
	 */
	private int type = -1;
	
	/**
	 * Stores the random object that uses the seeded value in the constructor to generate dots.
	 */
	private Random random = new Random();

	/**
	 * Creates a variable to store the number of types of dots there are.
	 */
	private int numOfTypes = 0;
	
  /**
   * Constructs a Generator whose generate() method always
   * returns a Dot of the given type.  (This method is intended
   * for testing.)
   * @param givenType
   *   type of Dot objects to be generated
   */
  public Generator(int givenType)
  {
	  type = givenType;
  }
 
  /**
   * Constructs a Generator that will create
   * random Dots with types 0 through numTypes - 1,
   * using the given Random instance.
   * @param numTypes
   *   number of types of dots
   * @param rand
   *   random number generator to use
   */
  public Generator(int numTypes, Random rand)
  {
	  random = rand;
	  numOfTypes = numTypes;
  }
  
  /**
   * Returns an instance of Dot according to this generator's rules
   * (Random or fixed value).
   * @return
   *   a new Dot object
   */
  public Dot generate()
  {
    if(type != -1)
    {
    	return new Dot(type);
    }
    else
    {
    	return new Dot(random.nextInt(numOfTypes));
    }
  }

  /**
   * Initializes the given 2D array of Dot objects
   * with values produced by this generator.
   * @param grid
   *   a 2D array to be initialized
   */
  public void initialize(Dot[][] grid)
  { 
    for (int row = 0; row < grid.length; ++row)
    {
      for (int col = 0; col < grid[0].length; ++col)
      {
        grid[row][col] = generate();
      }
    }
  }
}
