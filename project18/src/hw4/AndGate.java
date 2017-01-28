package hw4;

/**
 * Representation of a digital circuit component which has two inputs and one
 * output whose value is the logical "and" of the inputs.
 * 
 * @author RuchaKelkar
 */
public class AndGate extends AbstractComponent {
	/**
	 * Constructs an AndGate consisting of two inputs and one output, inputs and
	 * outputs are intially invalid.
	 */
	public AndGate() {
		super(2, 1);
	}

	@Override
	public void propagate() {
		if (inputsValid()) {
			int newValue = 0;
			if (inputs()[0].getValue() == 1 && inputs()[1].getValue() == 1) {
				newValue = 1;
			}
			outputs()[0].set(newValue);
		}
	}
}
