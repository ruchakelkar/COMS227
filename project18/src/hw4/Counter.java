package hw4;

import api.Endpoint;

import api.IStatefulComponent;

/**
 * A Counter is a component representing a value that might be set by setting
 * switches or reading external data. It is a "stateful" component whose outputs
 * are always valid and equal to the state.
 * 
 * @author RuchaKelkar
 */
public class Counter extends AbstractComponent implements IStatefulComponent {
	/**
	 * Stores whether the Counter is enabled or disabled. The tick() method will
	 * only work when the Counter is enabled.
	 */
	private boolean isEnabled = false;

	/**
	 * Stores the current state of the Counter as an integer (base 10).
	 */
	private int state = 0;

	/**
	 * Stores the number of bits this Counter consists of.
	 */
	private int bits;

	/**
	 * Constructs a Counter of the given number of bits.
	 * 
	 * @param bitsNum
	 *            number of bits for the value represented by this component
	 */
	public Counter(int bitsNum) {
		super(0, bitsNum);
		bits = bitsNum;
		for (int i = 0; i < bitsNum; i += 1) {
			outputs()[i] = new Endpoint(this);
		}
		clear();
	}

	@Override
	public void invalidateOutputs() {
		//
	}

	@Override
	public void tick() {
		if (isEnabled) {
			state++;
			String binary = Integer.toBinaryString(state);
			if (binary.length() > bits) {
				state = 0;
				clear();
			} else {
				updateOuts(binary);
			}
		}

	}

	/**
	 * Takes a given binary string and updates outputs() for the Counter based
	 * on the binary string.
	 * 
	 * @param binary
	 *            binary string to fill the outputs with
	 */
	private void updateOuts(String binary) {
		int index = 0;
		for (int i = binary.length() - 1; i >= 0; i--) {
			int binaryInt;
			if (i > 0) {
				binaryInt = Integer.parseInt(binary.substring(binary.length() - i, binary.length() - i + 1));
			} else {
				String temp = binary.substring(0, 1);
				binaryInt = Integer.parseInt(temp);
			}

			outputs()[index].set(binaryInt);
			index++;

		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
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
		}
	}
}
