package lab5;

import java.util.Scanner;

public class Initials 
{
	static String createInitials(String name)
	{
		Scanner scanner = new Scanner (name);
		String initials = "";
		
		while (scanner.hasNext())
		{
			String temp = scanner.next();
			char tempInitial = temp.charAt(0);
			initials += tempInitial;
		}
		
		
		return initials;
	}
}
