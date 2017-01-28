package lab2;

public class AtomTest 
{
	public static void main(String[] args)
	{
		Atom uranium238 = new Atom(92, 146, 92);
		
		System.out.println(uranium238.getAtomicMass());
		System.out.println("Expected: 238");
		
		System.out.println(uranium238.getAtomicCharge());
		System.out.println("Expected: 0");
		
		uranium238.decay();
		
		System.out.println(uranium238.getAtomicMass());
		System.out.println("Expected: 234");
		
		System.out.println(uranium238.getAtomicCharge());
		System.out.println("Expected: -2");
		
		
	}
}
