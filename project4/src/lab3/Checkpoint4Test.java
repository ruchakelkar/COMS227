package lab3;

public class Checkpoint4Test {

	public static void main(String[] args) 
	{
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		
		int temp = Integer.MAX_VALUE + 3; //it wraps around
		System.out.println(temp);
		
		int temp2 = Integer.MAX_VALUE + Integer.MIN_VALUE;
		System.out.println(temp2);

	}

}
