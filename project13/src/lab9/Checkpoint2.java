package lab9;

import java.io.File;
import java.util.ArrayList;

public class Checkpoint2 
{
	public static void main(String[] args)
	{
		 String rootDirectory = "."; 
		 System.out.println(countFiles(new File(rootDirectory)));		 
	}
	
	private static int countFiles(File f)
	{
		ArrayList<String> arr = new ArrayList<>();
		return Checkpoint2.countFiles(f, arr);
	}
	
	private static int countFiles(File f, ArrayList<String> arr)
	{
		if(!f.isDirectory())
		{
			arr.add(f.getName());
		}
		else
		{
			 arr.add(f.getName());
		     File[] files = f.listFiles();
		     for (int i = 0; i < files.length; ++i)
		     {
		       countFiles(files[i], arr);
		     }
		}
		return arr.size();
	}
}
