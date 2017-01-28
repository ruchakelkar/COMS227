import java.util.Scanner;

public class problem6 
{
	private String name;
	private String numString;
	private int[] numbers = new int[10];
	
	public static void main(String[] args)
	{
		problem6 test = new problem6("Rucha", "515-708-7730");
		System.out.println(test.getName());
		System.out.println(test.getPhoneNumber());
		System.out.print(test.getPhoneNumberArray());
		
	}
	
	public problem6(String aName, String aNumber)
	{
		name = aName;
		numString = aNumber;
		Scanner scan = new Scanner(numString);
		for(int k = 0; k < 10; k++)
		{
			int i = 0;
			char c = numString.charAt(i);
			if(c == '1') {numbers[i] = 1;}
			else if(c == '2') {numbers[i] = 2; i++;}
			else if(c == '3') {numbers[i] = 3; i++;}
			else if(c == '4') {numbers[i] = 4; i++;}
			else if(c == '5') {numbers[i] = 5; i++;}
			else if(c == '6') {numbers[i] = 6; i++;}
			else if(c == '7') {numbers[i] = 7; i++;}
			else if(c == '8') {numbers[i] = 8; i++;}
			else if(c == '9') {numbers[i] = 9; i++;}
			else if(c == '0') {numbers[i] = 0; i++;}
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPhoneNumber()
	{
		return numString;
	}
	
	public int[] getPhoneNumberArray()
	{
		for(int num : numbers) 
		{
			System.out.println(num);
		}
		return numbers;
	}
}
