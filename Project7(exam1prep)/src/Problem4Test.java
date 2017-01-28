import java.awt.Rectangle;

public class Problem4Test 
{
	public static void main(String[] args)
	{
		int a = 42;
	      Rectangle rect = new Rectangle(10, 20, 30, 40);
	      Rectangle rect2 = new Rectangle(2, 4, 6, 8);
	      int b = a;
	      Rectangle rect3 = rect;
	      rect3.setWidth(99);
	      rect2.setX(137);
	      b = b + 5;
	      System.out.println(a);
	      System.out.println(b);
	      System.out.println(rect.getWidth());
	}

}
