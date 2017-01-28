
public class Problem3Test 
{
	public static void main(String[] args)
	{
		boolean isOdd = (4 % 2 != 0);
		System.out.println(isOdd);
		
		
		int eggCount = 30;
		int fullCartons = eggCount % 12;
		System.out.println(fullCartons);
		int leftOver = eggCount % 12;
		System.out.println(leftOver);
		
		String str = "¿qué pasa?";
		char lastChar = str.charAt(str.length()-1);
		System.out.println(lastChar);
		
		String s1 = "hello";
		String s2 = "Ruch";
		boolean areTheSameString = s1.equals(s2);
		System.out.println(areTheSameString);
		
		String s = "Rucha";
		boolean hasAtLeastFour = s.length() >= 4;
		System.out.println(hasAtLeastFour);
		
		int d = 5;
		int c = 63;
		double totalValue = (double) d + (double) c/100;
		System.out.println(totalValue);
		
		int i3 = 7;
		String s4 = i3 + "";
		System.out.println(s4);
		
		double d1 = 105.1;
		int i = (int) Math.floor(d1);
		int i2 = (int) d1;
		System.out.println(i);
		System.out.println(i2);
		
		String str1 = "Rucha";
		char lastChar1 = str1.charAt(str1.length() -1);
		System.out.println(lastChar1);
		
		double d16 = 1.234;
		int i7 = (int) d16;
		System.out.println(i7);
	}
	
		
		
}
