
public class MeterTest 
{
	public static void main(String[] args)
	{
		ParkingMeter meter1 = new ParkingMeter(15, 60);
		meter1.insertCoin (3);
		meter1.passTime(20);;
		System.out.println(meter1.getTimeRemaning());
		System.out.println("Expected: 25");
		System.out.println(meter1.getTotal());
		System.out.println("Expected: 0.75");
		meter1.insertCoin(4);
		meter1.passTime(90);
		System.out.println(meter1.getTimeRemaning());
		System.out.println("Expected: 0");
		System.out.println(meter1.getTotal());
		System.out.println("Expected: 1.75");
	}
}
