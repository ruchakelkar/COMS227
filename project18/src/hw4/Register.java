package hw4;

import api.Endpoint;
import api.IStatefulComponent;

/**
 * A Register is a component representing a value that might be set by setting
 * switches or reading external data. It is a "stateful" component whose outputs
 * are always valid and equal to the state.
 * 
 * @author RuchaKelkar
 */
public class Register extends AbstractComponent implements IStatefulComponent {
	/**
	 * Stores whether the Register is enabled or disabled. The tick() method
	 * will only work when the Register is enabled.
	 */
	private boolean isEnabled = false;

	/**
	 * Constructs a Register of the given number of bits.
	 * 
	 * @param bitsNum
	 *            number of bits for the value represented by this component
	 */
	public Register(int bitsNum) {
		super(bitsNum, bitsNum);
		for (int i = 0; i < bitsNum; i += 1) {
			inputs()[i] = new Endpoint(this);
			outputs()[i] = new Endpoint(this);
		}
		clear();
	}

	@Override
	public void tick() {
		if (isEnabled) {
			for (int i = 0; i < outputs().length; i++) {
				Endpoint e = outputs()[i];
				e.set(inputs()[i].getValue());
			}
		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	@Override
	public void invalidateOutputs() {
		//
	}

	@Override
	public void clear() {
		for (Endpoint e : outputs()) {
			e.set(0);
		}
	}

	@Override
	public void propagate() {
		for (Endpoint e : outputs()) {
			e.set(e.getValue());
			System.out.println(e.getConnections());
		}
	}
}
