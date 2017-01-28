package mini2;


import java.util.ArrayList;

/**
 * Implementation of a version of the "twenty-four" game.
 */
public class NumberGame
{

  /**
   * Lists all ways to obtain the given target number using arithmetic operations
   * on the values in the given IntExpression list.  Results in string form are added to the given list,
   * where the string form of a value is obtained from the toString() of the Value object.
   * Special rules are: 1) you are not required to use all given numbers, and 2) division 
   * is integer division and is only allowed when remainder is zero.  For addition 
   * and multiplication, a + b and b + a are considered to be distinct solutions, and
   * likewise a * b and b * a are considered as different solutions.  See the
   * pdf for detailed examples.
   * @param list
   *   the values to be used
   * @param target
   *   the target number to be obtained from the values in the list
   * @param results
   *   list of all results, as strings
   */
  public static void findSolution(ArrayList<IntExpression> list, int target, ArrayList<String> results)
  {
    if(list.size() == 1)
    {
    	if(list.get(0).getIntValue() == target)
    	{
    		results.add(list.get(0).toString());
    	}
    }
    else
    {
    	for(IntExpression i : list)
		{
			ArrayList<IntExpression> newList = new ArrayList<>(list);
			newList.remove(i);
			findSolution(newList, target, results);
		}
    	for(int i = 0; i < list.size(); i++)
		{
			IntExpression v1 = list.get(i);
			ArrayList<IntExpression> tempList = new ArrayList<>(list);
			tempList.remove(i); 
			for(int j = 0; j < tempList.size(); j++)
			{
				IntExpression v2 = tempList.get(j);
				ArrayList<IntExpression> tempList2 = new ArrayList<>(tempList);
    			tempList2.remove(j);
    			
    			IntExpression v3 = new IntExpression(v1, v2, '+');
    			tempList2.add(v3);
    			findSolution(tempList2, target, results);
    			
    			IntExpression v4 = new IntExpression(v1, v2, '-');
    			tempList2.set(tempList2.size() - 1, v4);
    			findSolution(tempList2, target, results);
    			
    			IntExpression v5 = new IntExpression(v1, v2, '*');
    			tempList2.set(tempList2.size() - 1, v5);
    			findSolution(tempList2, target, results);
    			
    			if(v2.getIntValue() != 0 && v1.getIntValue() % v2.getIntValue() == 0)
    			{
    				IntExpression v6 = new IntExpression(v1, v2, '/');
    				tempList2.set(tempList2.size() - 1, v6);
        			findSolution(tempList2, target, results);
    			}
			}
		}
    }
  } 



}