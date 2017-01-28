package exam2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class problem2 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		problem2.updatedFile();
	}
	public static void updatedFile()
			throws FileNotFoundException
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter file name: ");
		String fileName = in.next();
		File inputFile = new File(fileName);
		String updatedFileName = fileName.substring(0, fileName.length() - 4);
		String outFile = updatedFileName + "out";
		File file = new File(outFile);
		PrintWriter out = new PrintWriter(file);
		Scanner scan = new Scanner(inputFile);
		while(scan.hasNextLine())
		{
			String line = scan.nextLine();
			line = line.trim();
			if(line.length() >= 2)
			{
				if(!(line.charAt(0) == '/' && line.charAt(1) == '/'))
				{
					out.println(line);
				}
			}	
		}
		scan.close();
		out.close();
	}
	

}
