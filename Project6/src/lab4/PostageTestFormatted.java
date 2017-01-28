package lab4;

import java.util.Scanner;

/**
 * Edit this import to test a different version
 */
import postage1.PostageUtil;

public class PostageTestFormatted
{
  /**
   * Tests the postage calculation utility.  This version uses the printf method
   * for formatting the output to show two decimal places.
   * @param args
   */
  public static void main(String[] args)
  {
    System.out.printf("0.5 ounces: %1.2f\n" , PostageUtil.computePostage(0.5));
    System.out.println("Expected .47");
    System.out.printf("1.0 ounces: %1.2f\n" , PostageUtil.computePostage(1.0));
    System.out.println("Expected .47");
    System.out.printf("3.0 ounces: %1.2f\n" , PostageUtil.computePostage(3.0));
    System.out.println("Expected .89");
    System.out.printf("2.3 ounces: %1.2f\n" , PostageUtil.computePostage(2.3));
    System.out.println("Expected .89");
    System.out.printf("3.1 ounces: %1.2f\n" , PostageUtil.computePostage(3.1));
    System.out.println("Expected 1.10");
    System.out.printf("3.5 ounces: %1.2f\n" , PostageUtil.computePostage(3.5));
    System.out.println("Expected 1.10");
    System.out.printf("3.8 ounces: %1.2f\n" , PostageUtil.computePostage(3.8));
    System.out.println("Expected .1.57");
    System.out.printf("10.0 ounces: %1.2f\n" , PostageUtil.computePostage(10));
    System.out.println("Expected 2.83");
    
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the package weight in ounces: ");
    double first = scanner.nextDouble();
    System.out.println("Postage cost is $ " + PostageUtil.computePostage(first));
    
  }

}
