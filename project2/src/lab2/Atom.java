package lab2;
/**
 * Model of an atom.
 * @author RuchaKelkar
 *
 */

public class Atom 
{
	/**
	 * Number of protons in the atom.
	 */
	private int protons;
	
	/**
	 * Number of neutrons in the atom.
	 */
	private int neutrons;
	
	/**
	 * Number of electrons in the atom.
	 */
	private int electrons;
	
	/**
	 * Constructs an atom with given quantities of protons, neutrons, and electrons. 
	 * @param givenProtons 
	 * 		the number of protons for this atom
	 * @param givenNeutrons 
	 * 		the number of neutrons for this atom
	 * @param givenElectrons
	 * 		the number of electrons for this atom
	 */
	public Atom(int givenProtons, int givenNeutrons, int givenElectrons)
	{
		protons = givenProtons;
		neutrons = givenNeutrons;
		electrons = givenElectrons;
	}
	
	/**
	 * Returns the atomic mass of this atom.
	 * @return
	 * 		the atomic mass
	 */		
	public int getAtomicMass()
	{
		int atomicMass = protons + neutrons;
		return atomicMass;
	}
	
	/**
	 * Returns the atomic charge of this atom.
	 * @return
	 * 		atomic charge of this atom
	 */
	public int getAtomicCharge()
	{
		int atomicCharge = protons - electrons;
		return atomicCharge;
	}

	/**
	 * Decays this atom.
	 */
	public void decay()
	{
		protons = protons - 2;
		neutrons = neutrons - 2;
	}
}
