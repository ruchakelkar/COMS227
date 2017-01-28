package hw4;

/**
 * Representation of a digital circuit component The output is selected from the
 * first 2^k inputs based on the value of the last k inputs, interpreted as a
 * binary number.
 * 
 * @author RuchaKelkar
 *
 */
public class Multiplexer extends AbstractComponent {
	/**
	 * Stores the number given in the constructor parameter.
	 */
	private int givenIn;

	/**
	 * Constructs a Multiplexer consisting of a total of 2^num + num inputs
	 * (when the value of num is given as the parameter) and one output. Saves
	 * the value of num. Inputs and outputs are initially invalid.
	 * 
	 * @param num
	 */
	public Multiplexer(int num) {
		super((int) (Math.pow(2, num) + num), 1);
		givenIn = num;
	}

	@Override
	public void propagate() {
		String temp = "";
		for (int i = inputs().length - 1; i >= inputs().length - givenIn; i--) {
			temp += inputs()[i];
		}

		int binary = (Integer.parseInt(temp, 2));
		outputs()[0].set(inputs()[(binary)].getValue());

	}
}
