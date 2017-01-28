package hw4Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import api.Endpoint;
import api.IComponent;
import api.IStatefulComponent;
import api.Util;
import hw4.AndGate;
import hw4.CompoundComponent;
import hw4.Counter;
import hw4.FullAdder;
import hw4.HalfAdder;
import hw4.MultiComponent;
import hw4.Multiplexer;
import hw4.NotGate;
import hw4.OrGate;
import hw4.Register;

/**
 * Tests, tests, tests! For homework 4 in Com S 227. Tests Included:
 * 
 * AndGate: Constructor, return inputs, return outputs, check if inputs are
 * valid, check if outputs are valid, test invalidate inputs, test invalidate
 * outputs, test propagate (4 scenarios).
 * 
 * OrGate tests: Constructor, return inputs, return outputs, test propagate (4
 * scenarios).
 * 
 * NotGate tests: Constructor, return inputs, return outputs, test propagate (2
 * scenarios).
 * 
 * CompoundComponent tests: Constructor, addComponent, getComponents. (Propagate
 * is tested in halfadder and fulladder).
 * 
 * HalfAdder tests: Constructor, inputs, outputs, propagate (4 scenarios).
 * 
 * FullAdder tests: Constructor, inputs, outputs, propagate (8 scenarios).
 * 
 * Multiplexer tests: Constructor inputs, outputs, propagate.
 * 
 * MultiComponent tests: Constructor, inputs, outputs, propagate.
 * 
 * Register tests: Constructor, IStatefulComponent, propagate, tick, outputs
 * valid.
 * 
 * Counter tests: Constructor, inputs, outputs, tic, setEnabled, clear,
 * propagate.
 * 
 * Plus some random tests that shouldn't fail (would [probably] fail in an
 * earlier test).
 * 
 * Not comprehensive, just more tests to check your classes with. There are 145
 * assertions used, and 26 ones in "misc" (total of 171 assertions). - check out
 * other junit tests posted.
 * 
 * @author ToastedPotato
 *
 */
public class JamesTests {

	/**
	 * Tests propagating a IComponent by setting inputs and testing outputs.
	 * Won't work if input and output length don't match.
	 * 
	 * @param c
	 *            IComponent to test
	 * @param msg
	 *            message to display if they don't equal
	 * @param inputs
	 *            array of inputs (as strings) to test
	 * @param outputs
	 *            array of outputs (as strings) to test
	 * 
	 */
	private void propagation(IComponent c, String msg, String[] inputs, String[] outputs) {
		if (inputs.length != outputs.length)
			System.out.println("[propagation()]: Inputs length must match outputs length!");
		else {
			for (int i = 0; i < inputs.length; i++) {
				Util.setInputs(c, inputs[i]);
				c.propagate();
				String expected = outputs[i];
				String actual = Util.toString(c.outputs());
				msg += " Input: ";
				assertEquals(msg + inputs[i], expected, actual);
			}
		}
	}

	/**
	 * Tests components that implement the IStatefulComponent interface. Checks
	 * they have tick, clear method works, and if enabled is properly
	 * implemented.
	 * 
	 * @param c
	 *            component to test
	 * @param name
	 *            name of this component
	 * @param bits
	 *            number of bits this object has
	 */
	private void iStatefulComponent(IStatefulComponent c, String name, int bits) {
		String msg = "Test " + name + "'s clear method.";
		String expected = "";
		for (int i = 0; i < bits; i++) {
			expected += "0";
		}
		for (Endpoint e : c.outputs()) {
			e.set(1);
		}
		c.clear();
		String actual = Util.toString(c.outputs());
		assertEquals(msg, expected, actual);

		msg = "Test " + name + "'s ";
	}

	/**
	 * Helper method for abstractComponent
	 * 
	 * @param c
	 *            component to test
	 * @param name
	 *            name of the component
	 * @param in
	 *            number of inputs
	 * @param out
	 *            number of outputs
	 */
	private void abstractComponent(IComponent c, String name, int in, int out) {
		abstractComponent(c, name, in, out, true);
	}

	/**
	 * AbstractComponent tests: tests all functions within abstract component
	 * (constructor, inputs, outputs, inputs valid, outputs valid, invalidate
	 * inputs, invalidate outputs, propagate)
	 * 
	 * @param c
	 *            component to tests that's a subclass of abstract component
	 *            that's just been constructed
	 * @param name
	 *            name of this component
	 * @param in
	 *            number of inputs this should have
	 * @param out
	 *            number of outputs this should have
	 * @param invalidOutputs
	 *            whether or not this function is testing a class that should
	 *            never have invalid outputs
	 */
	private void abstractComponent(IComponent c, String name, int in, int out, boolean invalidOutputs) {
		String msg = "Test " + name + "'s constructor for correct number of inputs.";
		int intExpected = in;
		int intActual = c.inputs().length;
		assertEquals(msg, intExpected, intActual);

		msg = "Test " + name + "'s constructor for correct number of outputs.";
		intExpected = out;
		intActual = c.outputs().length;
		assertEquals(msg, intExpected, intActual);

		boolean boolExpected;
		boolean boolActual;
		String test;
		if (c.inputs().length != 0) {
			msg = "Test " + name + "'s constructor for inputs invalid.";
			boolExpected = false;
			boolActual = c.inputsValid();
			test = " Inputs should be invalid after first constructed.";
			assertEquals(msg + test, boolExpected, boolActual);
			for (Endpoint e : c.inputs()) {
				e.set(0);
			}
			if (c.inputs().length > 1) {
				c.inputs()[c.inputs().length - 1].invalidate();
				boolActual = c.inputsValid();
				test = " Inputs should be invalid if any of the endpoints are invalid.";
				assertEquals(msg + test, boolExpected, boolActual);
				c.inputs()[c.inputs().length - 1].set(0);
			}
			boolExpected = true;
			boolActual = c.inputsValid();
			test = " Inputs should be valid when all inputs are set.";
			assertEquals(msg + test, boolExpected, boolActual);
		}

		if (invalidOutputs) {
			msg = "Test " + name + "'s constructor for outputs invalid.";
			boolExpected = false;
			boolActual = c.outputsValid();
			test = " Outputs should be invalid after first constructed.";
			assertEquals(msg + test, boolExpected, boolActual);
			for (Endpoint e : c.outputs()) {
				e.set(0);
			}
			if (c.outputs().length > 1) {
				c.outputs()[c.outputs().length - 1].invalidate();
				boolActual = c.outputsValid();
				test = " Outputs should be invalid if any of the endpoints are invalid.";
				assertEquals(msg + test, boolExpected, boolActual);
				c.outputs()[c.outputs().length - 1].set(0);
			}
			boolExpected = true;
			boolActual = c.outputsValid();
			test = " Outputs should be valid when all inputs are set.";
			assertEquals(msg + test, boolExpected, boolActual);

			msg = "Test " + name + "'s constructor for invalidate inputs.";
			c.invalidateInputs();
			boolExpected = true;
			boolActual = true;
			for (Endpoint e : c.inputs()) {
				if (e.isValid())
					boolActual = false;
			}
			test = " InvalidateInputs method doesn't invalidate all inputs.";
			assertEquals(msg + test, boolExpected, boolActual);
		}
		if (invalidOutputs) {
			msg = "Test " + name + "'s constructor for invalidate outputs.";
			c.invalidateOutputs();
			boolExpected = true;
			boolActual = true;
			for (Endpoint e : c.outputs()) {
				if (e.isValid())
					boolActual = false;
			}
			test = " InvalidateOutputs method doesn't invalidate all inputs.";
			assertEquals(msg + test, boolExpected, boolActual);
		}
	}

	/**
	 * A private static variable that's an integer and is named count. Is also
	 * set to 0 initially.
	 */
	private static int count = 0;

	@After
	public void tearDown() {
		if (count >= 44) {
			// CONGRADULATIONS, ALL YOUR TESTS PASSED WITHOUT ERRORS
			// (comment out following line for future use :p)
			// assertEquals("Error in propagate method.", "01101", "Day 7, am
			// stuck in a propagation method. Not sure where these messages will
			// end up, but I hope some of them get passed the garbage
			// collector...");
		}
	}

	/**
	 * AndGate tests: Constructor, return inputs, return outputs, check if
	 * inputs are valid, check if outputs are valid, test invalidate inputs,
	 * test invalidate outputs, test propagate (4 scenarios).
	 */

	@Test
	public void testAndGate_AbstractComponent() {
		AndGate a = new AndGate();
		abstractComponent(a, "AndGate", 2, 1);
		count++;
	}

	@Test
	public void testAndGate_Propagate() {
		String msg = "Constructs AndGate then checks Propagate has correct outputs for given inputs.";
		AndGate a = new AndGate();

		String[] inputs = { "00", "01", "10", "11" };
		String[] outputs = { "0", "0", "0", "1" };
		propagation(a, msg, inputs, outputs);
		count++;
	}

	/**
	 * OrGate tests: Constructor, return inputs, return outputs, test propagate
	 * (4 scenarios).
	 */

	@Test
	public void testOrGate_AbstractComponent() {
		OrGate a = new OrGate();
		abstractComponent(a, "OrGate", 2, 1);
		count++;
	}

	@Test
	public void testOrGate_Propagate() {
		String msg = "Constructs OrGate then checks Propagate has correct outputs for given inputs.";
		OrGate a = new OrGate();

		String[] inputs = { "00", "01", "10", "11" };
		String[] outputs = { "0", "1", "1", "1" };
		propagation(a, msg, inputs, outputs);
		count++;
	}

	/**
	 * NotGate tests: Constructor, return inputs, return outputs, test propagate
	 * (2 scenarios).
	 */

	@Test
	public void testNotGate_AbstractComponent() {
		NotGate a = new NotGate();
		abstractComponent(a, "NotGate", 1, 1);
		count++;
	}

	@Test
	public void testNotGate_PropagateScenarios() {
		String msg = "Constructs AndGate then checks Propagate has correct outputs for given inputs.";
		NotGate a = new NotGate();

		String[] inputs = { "0", "1" };
		String[] outputs = { "1", "0" };
		propagation(a, msg, inputs, outputs);
		count++;
	}

	/**
	 * CompoundComponent tests: Constructor, addComponent, getComponents.
	 * (Propagate is tested in halfadder and fulladder).
	 */

	@Test
	public void testCompoundComponent_AbstractComponent() {
		CompoundComponent a = new CompoundComponent(3, 4);
		abstractComponent(a, "CompoundComponent", 3, 4);
		count++;
	}

	@Test
	public void testCompoundComponent_GetComponents() {
		String msg = "Constructs a new CompoundComponent and tries to add / get components.";
		CompoundComponent a = new CompoundComponent(666, 665);

		int expected = 0;
		int actual = a.getComponents().size();
		assertEquals(msg, expected, actual);

		IComponent[] c = new IComponent[3];
		c[0] = new AndGate();
		c[1] = new OrGate();
		c[2] = new NotGate();
		for (IComponent comp : c) {
			a.addComponent(comp);
		}
		expected = 3;
		actual = a.getComponents().size();
		assertEquals(msg, expected, actual);

		for (int i = 0; i < 3; i++) {
			IComponent expectedC = c[i];
			IComponent actualC = a.getComponents().get(i);
			assertEquals(msg, expectedC, actualC);
		}
		count++;
	}

	/**
	 * HalfAdder tests: Constructor, inputs, outputs, propagate (4 scenarios).
	 */

	@Test
	public void testHalfAdder_AbstractComponent() {
		HalfAdder a = new HalfAdder();
		abstractComponent(a, "HalfAdder", 2, 2);
		count++;
	}

	@Test
	public void testHalfAdder_Propagate() {
		String msg = "Constructs new HalfAdder and tests propagate function. Input: ";
		HalfAdder a = new HalfAdder();

		String[] inputs = { "00", "01", "10", "11" };
		String[] outputs = { "00", "01", "01", "10" };
		propagation(a, msg, inputs, outputs);
		count++;
	}

	/**
	 * FullAdder tests: Constructor, inputs, outputs, propagate (8 scenarios).
	 */

	@Test
	public void testFullAdder_AbstractComponent() {
		FullAdder a = new FullAdder();
		abstractComponent(a, "FullAdder", 3, 2);
		count++;
	}

	@Test
	public void testFullAdder_Propagate() {
		String msg = "Constructs new FullAdder and tests propagate function.";
		FullAdder a = new FullAdder();

		String[] inputs = { "000", "001", "010", "011", "100", "101", "110", "111" };
		String[] outputs = { "00", "01", "01", "10", "01", "10", "10", "11" };
		propagation(a, msg, inputs, outputs);
		count++;
	}

	/**
	 * Multiplexer tests: Constructor inputs, outputs, propagate.
	 */

	@Test
	public void testMultiplexer_AbstractComponent() {
		Multiplexer a = new Multiplexer(4);
		abstractComponent(a, "Multiplexer", 20, 1);
		count++;
	}

	@Test
	public void testMultiplexer_Propagate() {
		String msg = "Constructs new Multiplexer and tests propagate function.";
		Multiplexer a = new Multiplexer(2);

		String[] inputs = { "000001", "010010", "100100", "111000", "001110", "011101", "101011", "110111" };
		String[] outputs = { "1", "1", "1", "1", "0", "0", "0", "0" };
		propagation(a, msg, inputs, outputs);
		count++;
	}

	/**
	 * MultiComponent tests: Constructor, inputs, outputs, propagate.
	 */

	@Test
	public void testMultiComponent_AbstractComponent() {
		IComponent[] c = new IComponent[4];
		for (int i = 0; i < c.length; i++) {
			c[i] = new AndGate();
		}
		MultiComponent a = new MultiComponent(c);
		abstractComponent(a, "MultiComponent", 8, 4);
		count++;
	}

	@Test
	public void testMultiComponent_propagate() {
		String msg = "Constructs new MultiComponent and tests propagate function.";
		IComponent[] c = new IComponent[4];
		for (int i = 0; i < c.length; i++) {
			c[i] = new AndGate();
		}
		MultiComponent a = new MultiComponent(c);

		String[] inputs = { "00000000", "11000000", "00000011", "01100110", "00111100", "11111111", "01001100",
				"10000000" };
		String[] outputs = { "0000", "1000", "0001", "0000", "0110", "1111", "0010", "0000" };
		propagation(a, msg, inputs, outputs);
		count++;
	}

	/**
	 * Register tests: Constructor, IStatefulComponent, propagate, tick, outputs
	 * valid.
	 */

	@Test
	public void testRegister_AbstractComponent() {
		Register a = new Register(4);
		abstractComponent(a, "Register", 4, 4, false);
		count++;
	}

	@Test
	public void testRegister_IStatefulComponent() {
		Register a = new Register(4);
		iStatefulComponent(a, "Register", 4);
		count++;
	}

	@Test
	public void testRegister_TickAndEnabled() {
		String msg = "Tests register's tick method";
		Register a = new Register(4);

		String test = " right after construction (assumes enabled is, by default set to true).";
		Util.setInputs(a, "0000");
		a.tick();
		String expected = "0000";
		String actual = Util.toString(a.outputs());
		// assertEquals(msg + test, expected, actual);

		test = " when enabled is true.";
		Util.setInputs(a, "0101");
		a.setEnabled(true);
		a.tick();
		expected = "0101";
		actual = Util.toString(a.outputs());
		assertEquals(msg + test, expected, actual);

		test = "when disabled is false.";
		Util.setInputs(a, "1001");
		a.setEnabled(false);
		a.tick();
		actual = Util.toString(a.outputs());
		assertEquals(msg + test, expected, actual);
		count++;
	}

	@Test
	public void testRegister_propagate() {
		String msg = "Tests the propagate function for registers (all outputs should be valid)";
		Register a = new Register(4);

		a.setEnabled(true);
		a.propagate();
		boolean expected = true;
		boolean actual = true;
		for (Endpoint e : a.outputs()) {
			if (!e.isValid()) {
				actual = false;
			}
		}
		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void testRegister_outputsValid() {
		String msg = "Tests the outputsValid function for registers after invalidate method has been used.";
		Register a = new Register(4);
		Util.setInputs(a, "0110");
		a.tick();
		a.invalidateOutputs();
		boolean expected = true;
		boolean actual = a.outputsValid();
		assertEquals(msg, expected, actual);
		count++;
	}

	/**
	 * Counter tests: Constructor, inputs, outputs, tic, setEnabled, clear,
	 * propagate.
	 */

	@Test
	public void testCounter_AbstractComponent() {
		Counter a = new Counter(4);
		abstractComponent(a, "Counter", 0, 4, false);
		count++;
	}

	@Test
	public void testCounter_IStatefulComponent() {
		Counter a = new Counter(4);
		iStatefulComponent(a, "Counter", 4);
		count++;
	}

	@Test
	public void testCounter_TickAndEnabled() {
		String msg = "Tests counter's tick method";
		Counter a = new Counter(2);

		String test = " right after construction (assumes enabled is, by default set to true).";
		String expected = "00";
		String actual = Util.toString(a.outputs());
		// assertEquals(msg + test, expected, actual);

		test = " when enabled is true.";
		a.setEnabled(true);
		a.tick();
		expected = "01";
		actual = Util.toString(a.outputs());
		assertEquals(msg + test, expected, actual);

		test = "when disabled is false.";
		a.setEnabled(false);
		a.tick();
		actual = Util.toString(a.outputs());
		assertEquals(msg + test, expected, actual);

		test = " for overflow (counts past max value able to store / output)";
		a.setEnabled(true);
		a.tick();
		a.tick();
		a.tick();
		expected = "00";
		actual = Util.toString(a.outputs());
		assertEquals(msg + test, expected, actual);
		count++;
	}

	@Test
	public void testCounter_propagate() {
		String msg = "Tests the propagate function for counters (all outputs should be valid)";
		Counter a = new Counter(4);

		a.setEnabled(true);
		a.propagate();
		boolean expected = true;
		boolean actual = true;
		for (Endpoint e : a.outputs()) {
			if (!e.isValid()) {
				actual = false;
			}
		}
		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void testCounter_outputsValid() {
		String msg = "Tests the outputsValid function for counters after invalidate method has been used.";
		Counter a = new Counter(4);
		Util.setInputs(a, "0110");
		a.tick();
		a.invalidateOutputs();
		boolean expected = true;
		boolean actual = a.outputsValid();
		assertEquals(msg, expected, actual);
		count++;
	}

	/**
	 * Misc tests: just miscellaneous tests.
	 */

	@Test
	public void misctestAndGate_ReturnInputsArrayLength() {
		String msg = "Constructs new AndGate then checks if inputs array is the correct length.";
		AndGate a = new AndGate();

		int expected = 2;
		int actual = a.inputs().length;

		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void misctestAndGate_ReturnInputsParent() {
		String msg = "Constructs new AndGate then checks if inputs array have correct parent.";
		AndGate a = new AndGate();

		AndGate expected = a;
		AndGate actual = (AndGate) a.inputs()[0].getParent();
		assertEquals(msg, expected, actual);

		actual = ((AndGate) a.inputs()[1].getParent());
		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void misctestMultipleAndGate_ReturnDifferentInputs() {
		String msg = "Constructs 2 AndGates then checks they have separate inputs.";
		AndGate a = new AndGate();
		AndGate b = new AndGate();

		AndGate first = (AndGate) a.inputs()[0].getParent();
		AndGate second = (AndGate) b.inputs()[0].getParent();
		assertFalse(msg, first == second);
		count++;
	}

	@Test
	public void misctestAndGate_ReturnOutputsArrayLength() {
		String msg = "Constructs new AndGate then checks if output array is the correct length.";
		AndGate a = new AndGate();

		int expected = 1;
		int actual = a.outputs().length;

		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void misctestAndGate_ReturnOutputsParent() {
		String msg = "Constructs new AndGate then checks if output array have correct parent.";
		AndGate a = new AndGate();

		AndGate expected = a;
		AndGate actual = (AndGate) a.outputs()[0].getParent();
		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void misctestAndGateMultiple_ReturnDifferentOutputs() {
		String msg = "Constructs 2 AndGates then checks they have separate outputs.";
		AndGate a = new AndGate();
		AndGate b = new AndGate();

		AndGate first = (AndGate) a.outputs()[0].getParent();
		AndGate second = (AndGate) b.outputs()[0].getParent();
		assertFalse(msg, first == second);
		count++;
	}

	@Test
	public void misctestAndGate_InputsValidConstruction() {
		String msg = "Constructs AndGate then checks InputsValid right after construction.";
		AndGate a = new AndGate();

		boolean expected = false;
		boolean actual = a.inputsValid();
		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void misctestAndGate_InputsValidOneSet() {
		String msg = "Constructs AndGate then checks InputsValid after one input endpoint has been set.";
		AndGate a = new AndGate();

		a.inputs()[0].set(0);
		boolean expected = false;
		boolean actual = a.inputsValid();
		assertEquals(msg, expected, actual);

		a.inputs()[0].invalidate();
		a.inputs()[1].set(0);
		actual = a.inputsValid();
		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void misctestAndGate_InputsValidTwoSet() {
		String msg = "Constructs AndGate then checks InputsValid after two input endpoint have been set.";
		AndGate a = new AndGate();

		Util.setInputs(a, "00");
		boolean expected = true;
		boolean actual = a.inputsValid();
		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void misctestAndGate_OutputsValidConstruction() {
		String msg = "Constructs AndGate then checks OutputsValid right after construction.";
		AndGate a = new AndGate();

		boolean expected = false;
		boolean actual = a.outputsValid();
		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void misctestAndGate_OutputsValidSet() {
		String msg = "Constructs AndGate then checks OutputsValid after one output endpoint has been set.";
		AndGate a = new AndGate();

		a.outputs()[0].set(0);
		boolean expected = true;
		boolean actual = a.outputsValid();
		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void misctestAndGate_InvalidateInputs() {
		String msg = "Constructs AndGate then checks InvalidateInputs after both inputs have been set.";
		AndGate a = new AndGate();

		Util.setInputs(a, "00");
		a.invalidateInputs();
		boolean expected = false;
		boolean actual = a.inputs()[0].isValid();
		assertEquals(msg, expected, actual);

		actual = a.inputs()[1].isValid();
		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void misctestAndGate_InvalidateOutputs() {
		String msg = "Constructs AndGate then checks InvalidateOutputs after output has been set.";
		AndGate a = new AndGate();

		a.outputs()[0].set(0);
		a.invalidateOutputs();
		boolean expected = false;
		boolean actual = a.outputs()[0].isValid();
		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void misctestOrGate_ReturnOutputsArrayLength() {
		String msg = "Constructs new OrGate then checks if output array is the correct length.";
		OrGate a = new OrGate();

		int expected = 1;
		int actual = a.outputs().length;

		assertEquals(msg, expected, actual);
		count++;
	}

	public void misctestOrGate_ReturnInputsArrayLength() {
		String msg = "Constructs new OrGate then checks if inputs array is the correct length.";
		OrGate a = new OrGate();

		int expected = 2;
		int actual = a.inputs().length;

		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void misctestNotGate_ReturnOutputsArrayLength() {
		String msg = "Constructs new NotGate then checks if output array is the correct length.";
		NotGate a = new NotGate();

		int expected = 1;
		int actual = a.outputs().length;

		assertEquals(msg, expected, actual);
		count++;
	}

	public void misctestNotGate_ReturnInputsArrayLength() {
		String msg = "Constructs new AndGate then checks if inputs array is the correct length.";
		NotGate a = new NotGate();

		int expected = 1;
		int actual = a.inputs().length;

		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void misctestCompoundComponent_ConstructorInputsOutputs() {
		String msg = "Constructs a new CompoundComponent and checks number of inputs and outputs.";
		CompoundComponent a = new CompoundComponent(666, 665);

		int expected = 666;
		int actual = a.inputs().length;
		assertEquals(msg, expected, actual);

		expected = 665;
		actual = a.outputs().length;
		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void misctestHalfAdder_ConstructorInputsOutputs() {
		String msg = "Constructs a new HalfAdder and checks number of inputs and outputs.";
		CompoundComponent a = new HalfAdder();

		int expected = 2;
		int actual = a.inputs().length;
		assertEquals(msg, expected, actual);

		expected = 2;
		actual = a.outputs().length;
		assertEquals(msg, expected, actual);
		count++;
	}

	@Test
	public void misctestFullAdder_ConstructorInputsOutputs() {
		String msg = "Constructs a new HalfAdder and checks number of inputs and outputs.";
		CompoundComponent a = new FullAdder();

		int expected = 3;
		int actual = a.inputs().length;
		assertEquals(msg, expected, actual);

		expected = 2;
		actual = a.outputs().length;
		assertEquals(msg, expected, actual);
		count++;
	}
}