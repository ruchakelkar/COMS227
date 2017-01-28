package lab7;

import java.util.ArrayList;

public class ArrayListExample 
{
	public static void removeDuplicates(ArrayList<String> words)
	{
		ArrayList<String> temp = new ArrayList<>();
		for(String word : words)
		{
			if(!(temp.contains(word)))
			{
				temp.add(word);
			}
		}
		words.clear();
		words.addAll(temp);
	}
}
