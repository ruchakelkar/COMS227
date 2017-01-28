package lab9;

public class Checkpoint1part1 
{
	 public static void main(String[] args)
	  {
	    int[] test = {3, 4, 5, 1, 7, 9, 2}; // mX should be 5
	    int result = findMax(test);
	    System.out.println(result);
	    
	    int levels = 7;
	    System.out.println(getPyramidCount(levels));
	  }
	 
	private static int findMax(int[] arr)
	{
		return findMaxRec(arr, 0, arr.length - 1);	
	}
	
	private static int findMaxRec(int[] arr, int start, int end)
	{
		if(start == end)
		{
			return arr[start];
		}
		else
		{
			int mid = (start + end) / 2;
		    int leftMax = findMaxRec(arr, start, mid);
		    int rightMax = findMaxRec(arr, mid + 1, end);
		    if(leftMax > rightMax)
		    {
		    	return leftMax;
		    }
		    else
		    {
		    	return rightMax;
		    }
		}
	}
	
	private static int getPyramidCount(int levels)
	{
		if(levels == 1)
		{
			return 1;
		}
		else
		{
			return (levels * levels) + getPyramidCount(levels - 1);
		}
	}
}
