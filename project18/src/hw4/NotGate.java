package hw4;

/**
 * Representation of a digital circuit component which has one input and one
 * output whose value is the logical "not" of the input.
 * 
 * @author RuchaKelkar
 */
public class NotGate extends AbstractComponent {
	/**
	 * Constructs a NotGate consisting of one input and one output, inputs and
	 * outputs are intially invalid.
	 */
	public NotGate() {
		super(1, 1);
	}

	@Override
	public void propagate() {
		if (inputsValid()) {
			int newValue = 0;
			if (inputs()[0].getValue() != 1) {
				newValue = 1;
			}
			outputs()[0].set(newValue);
		}
	}
}
