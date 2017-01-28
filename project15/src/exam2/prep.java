package exam2;

import java.util.Scanner;

public class prep 
{
	public static void main(String[] args)
	{
		double[] nums = {1.2, 2.3, 3.4, 4.5, 5.6, 6.7};
		System.out.println(average(nums));
		String text = "Hi, my name is Rucha Kelkar";
		System.out.println(findLongest(text));
		System.out.println(replace(text));
		System.out.println(1%2);
		mystery(12,0);
		int[] test = {6, 7, 4, 3, 5, 2, 7, 9, 8};
		enigma(test);
		System.out.println(getPassword());
	}
	
	public static double average(double[] arr)
	{
		double total = 0;
		for(double num : arr)
		{
			total += num;
		}
		return total/arr.length;
	}
	
	public static String findLongest(String text)
	{
		Scanner in = new Scanner(text);
		String longest = in.next();
		while(in.hasNext())
		{
			String temp = in.next();
			if(longest.length() < temp.length())
			{
				longest = temp;
			}
		}
		return longest;
	}
	
	public static String replace(String text)
	{
		Scanner in = new Scanner(text);
		String updated = text;
		for(int i = 0; i < updated.length(); i++)
		{
			char ch = updated.charAt(i);
			if(!Character.isAlphabetic(ch))
			{
				String temp = updated.substring(0, i) + "#";
				if(i < updated.length())
				{
					temp = temp + updated.substring(i+1);
				}
				updated = temp;
			}
		}
		return updated;
	}
	
	 public static void mystery(int x, int y)
	 {
	 while (x > 0)
	 {
	 if (x % 2 == 0)
	 {
	 y = y + 1;
	 }
	 else
	 {
	 x = x + 2;
	 }
	 x = x - y;
	 System.out.println(x + ", " + y);
	 }
	 }
	 
	 public static void enigma (int[] arr)
	 {
	 int i = 0;
	 for (int count = 0; count < arr.length; count += 1)
	 {
	 if (arr[i] % 2 != 0)
	 {
	 for (int j = i; j < arr.length - 1; j += 1)
	 {
	 arr[j] = arr[j + 1];
	 }
	 arr[arr.length - 1] = 0;
	 }
	 else
	 {
	 i += 1;
	 }
	 }
	 for(int num : arr)
	 {
		 System.out.println(num); 
	 }
	 }
	 
	 public static String getPassword()
	 {
		 Scanner in = new Scanner(System.in);
		 boolean verified = false;
		 while(!verified)
		 {
			 System.out.println("Enter a password: ");
			 String first = in.next();
			 System.out.println("Reenter the password for verification: ");
			 String second = in.next();
			 if(first.equals(second))
			 {
				 verified = false;
				 return first;
			 }
		 }
		 return null;
	 }
}
