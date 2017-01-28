package hw4;

import api.IComponent;

/**
 * Representation of a CompoundComponent consisting of two HalfAdders and an
 * OrGate. A FullAdder has three inputs and two outputs.
 * 
 * @author RuchaKelkar
 *
 */
public class FullAdder extends CompoundComponent {
	/**
	 * Constructs a FullAdder consisting of three inputs and two outputs, inputs
	 * and outputs are intially invalid.
	 */
	public FullAdder() {
		super(3, 2);

		// create the contained components and add them to the list
		IComponent half = new HalfAdder();
		IComponent half2 = new HalfAdder();
		IComponent orGate = new OrGate();
		addComponent(half);
		addComponent(half2);
		addComponent(orGate);

		// wire inputs
		inputs()[0].connectTo(half.inputs()[0]);
		inputs()[1].connectTo(half.inputs()[1]);

		// wiring things to eachother
		half.outputs()[1].connectTo(orGate.inputs()[0]);
		half.outputs()[0].connectTo(half2.inputs()[1]);
		inputs()[2].connectTo(half2.inputs()[0]);
		half2.outputs()[1].connectTo(orGate.inputs()[1]);

		// wire outputs:
		// output[0] is the "sum", which is the output of second HalfAdder
		half2.outputs()[0].connectTo(super.outputs()[0]);

		// output[1] is the "carry", output of or-gate
		orGate.outputs()[0].connectTo(super.outputs()[1]);

	}
}
