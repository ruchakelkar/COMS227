package hw4;

import api.IComponent;

/**
 * Representation of a CompoundComponent consisting of multiple components of an
 * identical type. Inputs are fed into the components with the first two inputs
 * going to the first component, next two inputs going to the next, etc. The
 * outputs list is made up of the output from each component within the
 * MultiComponent.
 * 
 * @author RuchaKelkar
 *
 */
public class MultiComponent extends CompoundComponent {
	/**
	 * Creates an Array that can reference the given IComponent Array in order
	 * to call stored components. The variable is initialized in the
	 * constructor.
	 */
	private IComponent[] comps;

	/**
	 * Constructs a MultiComponent and saves a reference to the given IComponent
	 * ArrayList of components. The number of inputs is determined by the number
	 * of components in the MultiComponent * the number of inputs for each
	 * component. The number of outputs is determined by the number of
	 * components in the MultiComponent. Inputs and outputs are intially
	 * invalid.
	 * 
	 * @param components
	 *            ArrayList of identical components which make up this
	 *            MultiComponent.
	 */
	public MultiComponent(IComponent[] components) {
		super(components.length * components[0].inputs().length, components.length);
		comps = components;
		int inputsNum = comps.length;
		for (IComponent c : comps) {
			addComponent(c);
		}

		if (components[0].inputs().length == 2) {
			for (int i = 0; i < inputsNum; i++) {
				inputs()[i * components[0].inputs().length].connectTo(components[i].inputs()[0]);
				inputs()[((i + 1) * components[0].inputs().length) - 1].connectTo(components[i].inputs()[1]);
			}
		} else if (components[0].inputs().length == 1) {
			for (int i = 0; i < inputsNum; i++) {
				inputs()[i * components[0].inputs().length].connectTo(components[i].inputs()[0]);
			}
		}
	}

	@Override
	public void propagate() {
		for (int i = 0; i < comps.length; i++) {
			comps[i].propagate();
			outputs()[i] = comps[i].outputs()[0];
		}
	}
}
