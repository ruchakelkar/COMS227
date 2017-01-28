package lab5;

public class Vowels 
{
	static int getVowel(String word)
	{
		for(int i = 0; i < word.length(); i++)
		{
			if("aeiouAEIOU".indexOf(word.charAt(i)) >= 0)
			{
				return i;
			}
		}
		
		return -1;
	}
}
