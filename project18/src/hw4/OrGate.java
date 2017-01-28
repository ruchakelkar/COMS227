package hw4;

/**
 * Representation of a digital circuit component which has two inputs and one
 * output whose value is the logical "or" of the inputs.
 * 
 * @author RuchaKelkar
 */
public class OrGate extends AbstractComponent {
	/**
	 * Constructs an OrGate consisting of two inputs and one output, inputs and
	 * outputs are intially invalid.
	 */
	public OrGate() {
		super(2, 1);
	}

	@Override
	public void propagate() {
		if (inputsValid()) {
			int newValue = 0;
			if (inputs()[0].getValue() == 1 || inputs()[1].getValue() == 1) {
				newValue = 1;
			}
			outputs()[0].set(newValue);
		}
	}
}
