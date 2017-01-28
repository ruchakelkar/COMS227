import java.util.Random;

public class Problem5 
{
	public Problem5()
	{
	}
	
	public static String createID(String firstName, String lastName)
	{
		Random rand = new Random();
		String tempFirstName = firstName.toLowerCase();
		char firstLetter = tempFirstName.charAt(0);
		String tempLastName = lastName.toLowerCase();
		int num = rand.nextInt(50) + 1;
		return (firstLetter + tempLastName + num);
				
		
		

		
		
	}
}
