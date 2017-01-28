package lab8;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import plotter.Plotter;
import plotter.Polyline;

public class Checkpoint2 
{
	private static ArrayList<Polyline> readFile(String filename)
		      throws FileNotFoundException
	{
	    File file = new File(filename);    
	    Scanner scanner = new Scanner(file);
	    ArrayList<Polyline> polylines = new ArrayList<>();
	    
	    while (scanner.hasNextLine())
	    {
	    	
	      String line = scanner.nextLine();
	      line = line.trim();
	      if(!line.startsWith("#") && line.length() != 0)
	      {
	    	  polylines.add(printOnePolyline(line));
	      }
	     
	    }
	   
	    Plotter plotter = new Plotter();
	    
	    for(Polyline polyline : polylines)
	    {
	    	plotter.plot(polyline);
	    }
	   
	    

	    scanner.close();
	    return polylines;
	}
	
	private static Polyline printOnePolyline(String line)
	  {   
	    Scanner temp = new Scanner(line);
	    Polyline poly;
	    System.out.println(line);

	    if(temp.hasNextInt())
	    {
	    	int width = temp.nextInt();
	    	String color = temp.next();
	    	poly = new Polyline(color, width);
	    }
	    else
	    {
	    	String color = temp.next();
	    	poly = new Polyline(color);
	    }
	    
	    while (temp.hasNextInt())
	    {
	      int xValue = temp.nextInt();
	      int yValue = temp.nextInt();
	      Point point = new Point(xValue, yValue);
	      poly.addPoint(point);
	    }
		return poly;
	    
	  }
	
	
	public static void main(String[] args) throws FileNotFoundException
	{
		ArrayList<Polyline> p = readFile("hello.txt");
		
	}
}
