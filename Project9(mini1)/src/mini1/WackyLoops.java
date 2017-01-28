package mini1;

import java.util.Scanner;

public class WackyLoops 
{
	public static int countMatches(String s, String t)
	{
		int matches = 0;
		int shorterLength = Math.min(s.length(), t.length());
		for (int i = 0; i < shorterLength; i++)
		{
			if(s.charAt(i) == t.charAt(i))
			{
				matches ++;
			}
		}
		return matches;
	}
	
	public static boolean isPermutation(String s, String t)
	{
		for (int i = 0; i <= s.length()-1; i++)
		{
			if (t.indexOf(s.charAt(i)) == -1)
			{
				return false;
			}
			
			
			String temp1 = t.substring(0, t.indexOf(s.charAt(i)));
			String temp2 = t.substring(t.indexOf(s.charAt(i)) +1);
			t = temp1 + temp2;
		
		}
		
		if (!t.equals(""))
		{
			return false;
		}
		
		return true;
	}
	
	public static String doubleConsonants(String s)
	{
		String n = ""; 
		for(int i = 0; i < s.length() - 1; i++)
		{
			if("aeiouAEIOU".indexOf(s.charAt(i)) >= 0)
			{
				n += s.charAt(i);
			}
			else if(s.charAt(i) != s.charAt(i + 1))
			{
				n += s.charAt(i);
				n += s.charAt(i);
			}
			
		}
		n += s.charAt(s.length()-1);
		if(!("aeiouAEIOU".indexOf(s.charAt(s.length()-1)) >= 0))
		{
			n += s.charAt(s.length()-1);
		}
		return n;
	}
	
	public static int findSecondLargest(String nums)
	{
		Scanner scanner = new Scanner(nums);
		
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int largest = Math.max(a, b);
		int secondLargest = Math.min(a, b);
		
		while(scanner.hasNextInt())
		{
			int c = scanner.nextInt();
			
			if(c > largest)
			{
				secondLargest = largest;
				largest = c;
			}
			else
			{
				secondLargest = Math.max(secondLargest, c);
			}
			
		}
		
		return secondLargest;
	}
	
	public static boolean substringWithGaps(String source, String target)
	{
		
		for(int i = 0; i < source.length(); i++)
		{
			if(target.indexOf(source.charAt(i)) >= 0)
			{
				if((target.indexOf(source.charAt(i)) != 0 && target.indexOf(source.charAt(i)) != -1))
				{
					return false;
				}
				
				target = target.substring(1);

			}
		}
		
		if(!(target.equals("")))
		{
			return false;
		}
		
		return true;
	}
	
	public static int findEscapeCount(double x, double y,int maxIterations)
	{
		double a = 0;
		double b = 0;
		int count = 0; 
		
		while((a * a + b * b) <= 4)
		{
			if(count > maxIterations)
			{
				return maxIterations;
			}
			
			double newA = a * a - b * b + x;
			double newB = 2 * a * b + y;
			a = newA;
			b = newB;
			count++;
		}
		
		return count; 
	}
	
}
