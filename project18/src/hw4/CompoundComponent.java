package hw4;

import java.util.ArrayList;

import api.Endpoint;
import api.IComponent;

/**
 * Representation of a type of component that is a container for other
 * components that are connected together in various ways.
 * 
 * @author RuchaKelkar
 */
public class CompoundComponent extends AbstractComponent {
	/**
	 * Constructs an ArrayList to contain the various connected components in
	 * the CompoundComponent container.
	 */
	private ArrayList<IComponent> components;

	/**
	 * Constructs a container component with the given number of input and
	 * outputs.
	 * 
	 * @param in
	 *            number of inputs
	 * @param out
	 *            number of outputs
	 */
	public CompoundComponent(int in, int out) {
		super(in, out);
		components = new ArrayList<>();
	}

	/**
	 * Adds a component to the ArrayList of components in this container.
	 * 
	 * @param c
	 *            Component to be added to the container's component list.
	 */
	public void addComponent(IComponent c) {
		components.add(c);
	}

	/**
	 * Returns the ArrayList of components representing this container's
	 * component makeup.
	 * 
	 * @return ArrayList of components.
	 */
	public ArrayList<IComponent> getComponents() {
		return components;
	}

	@Override
	public void propagate() {
		this.invalidateOutputs();
		for (IComponent comp : components) {
			comp.invalidateInputs();
			comp.invalidateOutputs();
		}

		for (Endpoint e : inputs()) {
			e.set(e.getValue());
		}

		while (!outputsValid()) {
			for (IComponent comp : components) {
				if (comp.inputsValid() && !comp.outputsValid()) {
					comp.propagate();
				}
			}
		}
	}
}
