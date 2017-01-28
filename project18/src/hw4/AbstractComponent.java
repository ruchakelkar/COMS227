package hw4;

import api.Endpoint;
import api.IComponent;

/**
 * Abstract class representing a component in a simulation of digital circuits.
 * 
 * @author RuchaKelkar
 */
public abstract class AbstractComponent implements IComponent {
	/**
	 * Constructs an array to contain the given input values for the component
	 * as Endpoints.
	 */
	private Endpoint[] inputs;

	/**
	 * Constructs an array to contain the given output values for the component
	 * as Endpoints.
	 */
	private Endpoint[] outputs;

	/**
	 * Constructs a component with the given number of input and output values,
	 * and constructs Endpoints for each array value.
	 * 
	 * @param number
	 *            of inputs
	 * @param number
	 *            of outputs
	 */
	protected AbstractComponent(int in, int out) {
		inputs = new Endpoint[in];
		outputs = new Endpoint[out];
		for (int i = 0; i < inputs().length; i++) {
			inputs()[i] = new Endpoint(this);
		}
		for (int i = 0; i < outputs().length; i++) {
			outputs()[i] = new Endpoint(this);
		}
	}

	@Override
	public Endpoint[] inputs() {
		return inputs;
	}

	@Override
	public Endpoint[] outputs() {
		return outputs;
	}

	@Override
	public boolean inputsValid() {
		for (Endpoint e : inputs) {
			if (!e.isValid())
				return false;
		}
		return true;
	}

	@Override
	public boolean outputsValid() {
		for (Endpoint e : outputs) {
			if (!e.isValid())
				return false;
		}
		return true;
	}

	@Override
	public void invalidateInputs() {
		for (Endpoint e : inputs)
			e.invalidate();
	}

	@Override
	public void invalidateOutputs() {
		for (Endpoint e : outputs)
			e.invalidate();
	}

	@Override
	public abstract void propagate();
}