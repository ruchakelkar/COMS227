package lab7;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ArrayExample3
{
  public static void main(String[] args)
  {
    /*String s = "3 5 7 9 12";
    int[] result = readNumbers(s);
    System.out.println(Arrays.toString(result));
    int[] test = {-7, 8, 14, -2, 0};
    int[] result2 = getPositiveNumbers(test);
    System.out.println(Arrays.toString(result2));*/
    int[] result3 = ArrayExample3.randomExperiment(10, 1000);
    System.out.println(Arrays.toString(result3));
    
  }
  
  public static int[] readNumbers(String text)
  {
    Scanner scanner = new Scanner(text);
    int count = 0;
    while (scanner.hasNextInt())
    {
      scanner.nextInt();
      count +=1;
    }
    
    int[] nums = new int[count];
    scanner = new Scanner(text);
    int index = 0;
    while (scanner.hasNextInt())
    {
      int num = scanner.nextInt();
      nums[index] = num;
      index += 1;
    }
    return nums;  
  }
  
  public static int[] getPositiveNumbers(int[] numbers)
  {
	 int count = 0;
	 for(int number : numbers)
	 {
		 if(number > 0)
		 {
			 count +=1;
		 }
	 }
	int[] nums = new int[count];
	int index = 0;
	for(int number : numbers)
	{
		if(number > 0)
		{
			nums[index] = number;
			index++;
		}
	
	}
	return nums;
  }
  
  public static int[] randomExperiment(int max, int iters)
  {
	  Random rand = new Random();
	  int[] counts = new int[max];
	  for(int i = 0; i < iters; i++)
	  {
		  int number = rand.nextInt(max);
		  int index = 0;
		  for(int num : counts)
		  {
			  if (number == index)
			  {
				  counts[index] = counts[index] + 1;
			  }
			  index++;
		  }
	  }
	  return counts;
	
	  
  }

}