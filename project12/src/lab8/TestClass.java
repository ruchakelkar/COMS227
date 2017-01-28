package lab8;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

public class TestClass
{
  public static void main(String[] args) throws FileNotFoundException
  {
	File file = new File("../project11/src/lab7/Deck.java");
    Scanner scanner = new Scanner(file);
    File outFile = new File("mydocument2.txt");
    PrintWriter out = new PrintWriter(outFile);
    int lineCount = 1;

    while (scanner.hasNextLine())
    {
      String line = scanner.nextLine();
      out.print(lineCount + " ");
      out.println(line);
      lineCount += 1;
    }
    
    System.out.println("Done");
    out.close();
 
    System.out.println(file.exists());          // true if the file exists
    System.out.println(file.getName());         // name of the file 
    System.out.println(file.getAbsolutePath()); // absolute path to the file
    System.out.println(file.length());          // size of the file
    
    
    scanner.close();
  }
}