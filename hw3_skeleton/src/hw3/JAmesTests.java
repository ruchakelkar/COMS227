package hw3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import api.Descriptor;
import api.Dot;
import api.Util;
import hw3.DotsGame;
import hw3.Generator;

/**
 * Lots of tests for the DogsGame and Generator.
 * 
 * @author zzz
 */
public class JAmesTests {

	private static final String[] testgridrandom = { "12 0 1 2", "0 5 3 4", "1 5 5 13" };

	private static final String[] testgridsame = { "7 7 7", "7 7 7", "7 7 7", "7 7 7" };

	private DotsGame game;

	private DotsGame gameSame;

	/**
	 * Helper method checks whether the given game cells match the given grid.
	 * 
	 * @param expectedGrid
	 * @param g
	 */
	private void checkGrid(String msg, String[] expectedGrid, DotsGame g) {
		Dot[][] expected = Util.createGridFromString(expectedGrid);
		for (int row = 0; row < expected.length; ++row) {
			for (int col = 0; col < expected[0].length; ++col) {
				// if ours is null, theirs should be null too
				if (expected[row][col] == null) {
					if (g.getDot(row, col) != null) {
						fail(msg + "Dot at row " + row + ", column " + col + " should be null");
					}
				} else {
					if (!expected[row][col].equals(g.getDot(row, col))) {
						fail(msg + "Dot at row " + row + ", column " + col + " should have type "
								+ expected[row][col].getType());
					}
				}
			}
		}
	}

	/**
	 * Helper method checks whether the two lists contain the same descriptors,
	 * regardless of ordering.
	 * 
	 * @param msg
	 * @param expected
	 * @param actual
	 */
	private void checkDescriptorList(String msg, ArrayList<Descriptor> expected, ArrayList<Descriptor> actual) {
		// for this list the order is not specified so we can't use assertEquals
		ArrayList<Descriptor> actualTemp = new ArrayList<>(actual);
		ArrayList<Descriptor> expectedTemp = new ArrayList<>(expected);

		for (Descriptor d : expected) {
			if (actualTemp.indexOf(d) != -1) {
				actualTemp.remove(actualTemp.indexOf(d));
			} else {
				fail(msg + " -Missing descriptor " + d);
			}
		}
		for (Descriptor d : actual) {
			if (expectedTemp.indexOf(d) != -1) {
				expectedTemp.remove(expectedTemp.indexOf(d));
			} else {
				fail(msg + " -Incorrect descriptor " + d);
			}
		}
		// also check length
		if (expected.size() != actual.size()) {
			fail(msg + " -Expected length " + expected.size() + " actual length " + actual.size());
		}
	}

	@Before
	public void setup() {
		Generator g = new Generator(5);
		game = new DotsGame(testgridrandom, g);
		gameSame = new DotsGame(testgridsame, g);
	}

	@Test
	public void testGetDot1() {
		String msg = "Getting dot from (0,0) should return a dot.";
		Dot actual = game.getDot(0, 0);
		Dot expected = new Dot(12);
		assertEquals(msg, expected, actual);
	}

	@Test
	public void testGetDot2() {
		String msg = "Getting dot from (rowMax,colMax) should return a dot.";
		Dot actual = game.getDot(2, 3);
		Dot expected = new Dot(13);
		assertEquals(msg, expected, actual);
	}

	@Test
	public void testSetDot() {
		String msg = "Setting (2,3) to new dot type: 100";
		Dot expected = new Dot(100);
		game.setDot(2, 3, expected);
		Dot actual = game.getDot(2, 3);
		assertEquals(msg, expected, actual);
	}

	@Test
	public void testGetWidth() {
		String msg = "Seeing if getWidth() works.";
		int actual = game.getWidth();
		int expected = 4;
		assertEquals(msg, expected, actual);
	}

	@Test
	public void testGetHeight() {
		String msg = "Checking if getHeight works.";
		int actual = game.getHeight();
		int expected = 3;
		assertEquals(msg, expected, actual);
	}

	@Test
	public void testGetSelectionList1() {
		String msg = "When game selects (0,0), is (0,0) recieved when getSelectionList is called.";
		game.select(0, 0);
		ArrayList<Descriptor> actual = game.getSelectionList();
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(0, 0, new Dot(12)));
		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testSelect1() {
		String msg = "When game selects (rowMax,colMax), is the correct dot selected.";
		game.select(2, 3);
		ArrayList<Descriptor> actual = game.getSelectionList();
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(2, 3, new Dot(13)));
		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testSelect2() {
		String msg = "Select multiple dots in a col";
		game.select(2, 3);
		game.setDot(1, 3, new Dot(13));
		game.select(1, 3);
		ArrayList<Descriptor> actual = game.getSelectionList();
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(2, 3, new Dot(13)));
		expected.add(new Descriptor(1, 3, new Dot(13)));
		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testSelect3() {
		String msg = "Select multiple dots in a row";
		game.select(2, 3);
		game.setDot(2, 2, new Dot(13));
		game.select(2, 2);
		ArrayList<Descriptor> actual = game.getSelectionList();
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(2, 3, new Dot(13)));
		expected.add(new Descriptor(2, 2, new Dot(13)));
		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testSelect4() {
		String msg = "Try to select a dot diaganal";
		game.select(2, 3);
		game.setDot(1, 2, new Dot(13));
		game.select(1, 2);
		ArrayList<Descriptor> actual = game.getSelectionList();
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(2, 3, new Dot(13)));
		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testSelect5() {
		String msg = "Try to select a dot that is a different type";
		game.select(2, 3);
		game.select(2, 2);
		ArrayList<Descriptor> actual = game.getSelectionList();
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(2, 3, new Dot(13)));
		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testSelect6() {
		String msg = "Try to select the same dot";
		game.select(2, 3);
		game.select(2, 3);
		ArrayList<Descriptor> actual = game.getSelectionList();
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(2, 3, new Dot(13)));
		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testSelect7() {
		String msg = "Try to select the same dot after selecting 3 other dots";
		gameSame.select(2, 2);
		gameSame.select(2, 1);
		gameSame.select(1, 1);
		gameSame.select(1, 2);
		gameSame.select(2, 2);
		ArrayList<Descriptor> actual = gameSame.getSelectionList();
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(2, 2, new Dot(7)));
		expected.add(new Descriptor(2, 1, new Dot(7)));
		expected.add(new Descriptor(1, 1, new Dot(7)));
		expected.add(new Descriptor(1, 2, new Dot(7)));
		expected.add(new Descriptor(2, 2, new Dot(7)));
		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testSelect8() {
		String msg = "Try to select a different dot after creating a loop";
		gameSame.select(2, 2);
		gameSame.select(2, 1);
		gameSame.select(1, 1);
		gameSame.select(1, 2);
		gameSame.select(2, 2);
		gameSame.select(3, 2);
		ArrayList<Descriptor> actual = gameSame.getSelectionList();
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(2, 2, new Dot(7)));
		expected.add(new Descriptor(2, 1, new Dot(7)));
		expected.add(new Descriptor(1, 1, new Dot(7)));
		expected.add(new Descriptor(1, 2, new Dot(7)));
		expected.add(new Descriptor(2, 2, new Dot(7)));
		expected.add(new Descriptor(3, 2, new Dot(7)));
		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testSelect9() {
		String msg = "Select a loop then try and repeat the loop";
		gameSame.select(2, 2);
		gameSame.select(2, 1);
		gameSame.select(1, 1);
		gameSame.select(1, 2);
		gameSame.select(2, 2);
		gameSame.select(2, 1);
		ArrayList<Descriptor> actual = gameSame.getSelectionList();
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(2, 2, new Dot(7)));
		expected.add(new Descriptor(2, 1, new Dot(7)));
		expected.add(new Descriptor(1, 1, new Dot(7)));
		expected.add(new Descriptor(1, 2, new Dot(7)));
		expected.add(new Descriptor(2, 2, new Dot(7)));
		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testSelect10() {
		String msg = "Select a loop, then try creating a new loop to link back with beginning again";
		gameSame.select(1, 1);
		gameSame.select(1, 0);
		gameSame.select(0, 0);
		gameSame.select(0, 1);
		gameSame.select(1, 1);
		gameSame.select(2, 1);
		gameSame.select(2, 2);
		gameSame.select(1, 2);
		gameSame.select(1, 1);

		ArrayList<Descriptor> actual = gameSame.getSelectionList();
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(1, 1, new Dot(7)));
		expected.add(new Descriptor(1, 0, new Dot(7)));
		expected.add(new Descriptor(0, 0, new Dot(7)));
		expected.add(new Descriptor(0, 1, new Dot(7)));
		expected.add(new Descriptor(1, 1, new Dot(7)));
		expected.add(new Descriptor(2, 1, new Dot(7)));
		expected.add(new Descriptor(2, 2, new Dot(7)));
		expected.add(new Descriptor(1, 2, new Dot(7)));

		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testRelease1() {
		String msg = "Select only one dot and see if it is returned";
		game.select(1, 1);
		ArrayList<Descriptor> actual = game.release();
		ArrayList<Descriptor> expected = new ArrayList<>();
		assertEquals(msg, expected, actual);
	}

	@Test
	public void testGetSelectionList2() {
		String msg = "Selects one dot and releases it";
		game.select(0, 0);
		ArrayList<Descriptor> nullValues = game.release();
		ArrayList<Descriptor> actual = game.getSelectionList();
		ArrayList<Descriptor> expected = new ArrayList<>();
		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testRelease2() {
		String msg = "Select 2 dots and check if changed values are returned";
		gameSame.select(1, 1);
		gameSame.select(1, 0);
		ArrayList<Descriptor> actual = gameSame.release();
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(1, 1, new Dot(7)));
		expected.add(new Descriptor(1, 0, new Dot(7)));

		assertEquals(msg, expected, actual);
	}

	@Test
	public void testRelease3() {
		String msg = "Select 1 dot and check if grid stays the same";
		gameSame.select(1, 1);
		ArrayList<Descriptor> actual = gameSame.release();
		String[] expected = testgridsame;

		checkGrid(msg, expected, gameSame);
	}

	@Test
	public void testRelease4() {
		String msg = "Select 2 dots and check if grid nulls out values correctly";
		gameSame.select(1, 1);
		gameSame.select(1, 0);
		ArrayList<Descriptor> actual = gameSame.release();
		String[] expected = { "7 7 7", "* * 7", "7 7 7", "7 7 7" };

		checkGrid(msg, expected, gameSame);
	}

	@Test
	public void testRelease5() {
		String msg = "Select a loop and check if grid nulls out values correctly";
		gameSame.select(1, 1);
		gameSame.select(1, 0);
		gameSame.select(0, 0);
		gameSame.select(0, 1);
		gameSame.select(1, 1);
		gameSame.setDot(3, 2, new Dot(10));
		ArrayList<Descriptor> actual = gameSame.release();
		String[] expected = { "* * *", "* * *", "* * *", "* * 10" };

		checkGrid(msg, expected, gameSame);
	}

	@Test
	public void testRelease6() {
		String msg = "Select a loop and check if returned nulled values are correct";
		gameSame.select(1, 1);
		gameSame.select(1, 0);
		gameSame.select(0, 0);
		gameSame.select(0, 1);
		gameSame.select(1, 1);
		gameSame.setDot(3, 2, new Dot(10));
		ArrayList<Descriptor> actual = gameSame.release();
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(0, 0, new Dot(7)));
		expected.add(new Descriptor(0, 1, new Dot(7)));
		expected.add(new Descriptor(0, 2, new Dot(7)));
		expected.add(new Descriptor(1, 0, new Dot(7)));
		expected.add(new Descriptor(1, 1, new Dot(7)));
		expected.add(new Descriptor(1, 2, new Dot(7)));
		expected.add(new Descriptor(2, 0, new Dot(7)));
		expected.add(new Descriptor(2, 1, new Dot(7)));
		expected.add(new Descriptor(2, 2, new Dot(7)));
		expected.add(new Descriptor(3, 0, new Dot(7)));
		expected.add(new Descriptor(3, 1, new Dot(7)));

		assertEquals(msg, expected, actual);
	}

	@Test
	public void testRelease7() {
		String msg = "Select a larger loop and check if grid returns nulled values and if grid has correct null values";
		gameSame.select(1, 2);
		gameSame.select(1, 1);
		gameSame.select(1, 0);
		gameSame.select(0, 0);
		gameSame.select(0, 1);
		gameSame.select(0, 2);
		gameSame.select(1, 2);

		gameSame.setDot(3, 2, new Dot(10));
		ArrayList<Descriptor> actual = gameSame.release();
		String[] expected = { "* * *", "* * *", "* * *", "* * 10" };

		checkGrid(msg, expected, gameSame);
	}

	@Test
	public void testGetScore1() {
		String msg = "Select only one dot and see if score is correct";
		game.select(1, 1);
		ArrayList<Descriptor> nulledValues = game.release();
		int actual = game.getScore();
		int expected = 0;
		assertEquals(msg, expected, actual);
	}

	@Test
	public void testGetScore2() {
		String msg = "Select 2 dots and see if score is correct";
		gameSame.select(1, 1);
		gameSame.select(1, 0);
		ArrayList<Descriptor> nulledValues = gameSame.release();
		int actual = gameSame.getScore();
		int expected = 2;
		assertEquals(msg, expected, actual);
	}

	@Test
	public void testGetScore3() {
		String msg = "Select a loop and see if score is correct";
		gameSame.select(1, 1);
		gameSame.select(1, 0);
		gameSame.select(0, 0);
		gameSame.select(0, 1);
		gameSame.select(1, 1);
		gameSame.setDot(3, 2, new Dot(10));
		ArrayList<Descriptor> nulledValues = gameSame.release();
		int actual = gameSame.getScore();
		int expected = 11;
		assertEquals(msg, expected, actual);
	}

	@Test
	public void testCollapse1() {
		String msg = "Try to collapse array with no null";
		Generator g = new Generator(5);
		String[] collapseArray = { "1", "2", "3", "4", "5" };
		DotsGame funGame = new DotsGame(collapseArray, g);
		funGame.collapseColumn(0);

		checkGrid(msg, collapseArray, funGame);
	}

	@Test
	public void testCollapse2() {
		String msg = "Try to collapse array with only nulls";
		Generator g = new Generator(5);
		String[] collapseArray = { "*", "*", "*", "*", "*" };
		DotsGame funGame = new DotsGame(collapseArray, g);
		funGame.collapseColumn(0);

		checkGrid(msg, collapseArray, funGame);
	}

	@Test
	public void testCollapse3() {
		String msg = "Try to collapse array with only a null at the top";
		Generator g = new Generator(5);
		String[] collapseArray = { "*", "1", "2", "3", "4" };
		DotsGame funGame = new DotsGame(collapseArray, g);
		funGame.collapseColumn(0);

		checkGrid(msg, collapseArray, funGame);
	}

	@Test
	public void testCollapse4() {
		String msg = "Try to collapse array with a null in middle of array";
		Generator g = new Generator(5);
		String[] collapseArray = { "1", "2", "*", "3", "4" };
		DotsGame funGame = new DotsGame(collapseArray, g);
		collapseArray[0] = "*";
		collapseArray[1] = "1";
		collapseArray[2] = "2";
		funGame.collapseColumn(0);

		checkGrid(msg, collapseArray, funGame);
	}

	@Test
	public void testCollapse5() {
		String msg = "Try to collapse array with null at bottom of array";
		Generator g = new Generator(5);
		String[] collapseArray = { "1", "2", "3", "4", "*" };
		DotsGame funGame = new DotsGame(collapseArray, g);
		String[] expected = { "*", "1", "2", "3", "4" };
		funGame.collapseColumn(0);

		checkGrid(msg, expected, funGame);
	}

	@Test
	public void testCollapse6() {
		String msg = "Try to collapse array with 2 null";
		Generator g = new Generator(5);
		String[] collapseArray = { "0 1", "1 2", "2 *", "3 3", "4 *" };
		DotsGame funGame = new DotsGame(collapseArray, g);
		collapseArray[0] = "0 *";
		collapseArray[4] = "4 3";
		collapseArray[1] = "1 *";
		collapseArray[2] = "2 1";
		collapseArray[3] = "3 2";
		funGame.collapseColumn(1);

		checkGrid(msg, collapseArray, funGame);
	}

	@Test
	public void testCollapse7() {
		String msg = "Try to collapse long array lots of nulls";
		Generator g = new Generator(5);
		String[] collapseArray = { "1", "1", "*", "1", "*", "1", "1", "*", "1", "*", "1", "1", "*", "1", "*", "1", "1",
				"*", "*", "*", "1", "1", "*", "*", "*", "1", "1", "*", "1", "*", "1", "1", "*", "1", "*", "1", "1", "*",
				"1", "*", "1", "1", "*", "1", "*", "1", "1", "*", "*", "*", "1", "1", "*", "1", "*", "1", "1", "*", "1",
				"*", };
		DotsGame funGame = new DotsGame(collapseArray, g);
		String[] expected = { "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*",
				"*", "*", "*", "*", "*", "*", "*", "*", "*", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1" };
		funGame.collapseColumn(0);

		checkGrid(msg, expected, funGame);
	}

	@Test
	public void testCollapseReturn1() {
		String msg = "Try to collapse array with no null";
		Generator g = new Generator(5);
		String[] collapseArray = { "1", "1", "1", "1", "1" };
		DotsGame funGame = new DotsGame(collapseArray, g);
		ArrayList<Descriptor> actual = funGame.collapseColumn(0);
		ArrayList<Descriptor> expected = new ArrayList<>();

		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testCollapseReturn2() {
		String msg = "Try to collapse array with only nulls";
		Generator g = new Generator(5);
		String[] collapseArray = { "*", "*", "*", "*", "*" };
		DotsGame funGame = new DotsGame(collapseArray, g);
		ArrayList<Descriptor> actual = funGame.collapseColumn(0);
		ArrayList<Descriptor> expected = new ArrayList<>();

		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testCollapseReturn3() {
		String msg = "Try to collapse array with only a null at the top";
		Generator g = new Generator(5);
		String[] collapseArray = { "*", "1", "1", "1", "1" };
		DotsGame funGame = new DotsGame(collapseArray, g);
		ArrayList<Descriptor> actual = funGame.collapseColumn(0);
		ArrayList<Descriptor> expected = new ArrayList<>();

		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testCollapseReturn4() {
		String msg = "Try to collapse array with a null in middle of array (there will be an error if null values are included)";
		Generator g = new Generator(5);
		String[] collapseArray = { "1", "1", "*", "1", "1" };
		DotsGame funGame = new DotsGame(collapseArray, g);
		ArrayList<Descriptor> actual = funGame.collapseColumn(0);
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(1, 0, new Dot(1)));
		expected.get(0).setPreviousRow(0);
		expected.add(new Descriptor(2, 0, new Dot(1)));
		expected.get(1).setPreviousRow(1);

		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testCollapseReturn5() {
		String msg = "Try to collapse array with null at bottom of array";
		Generator g = new Generator(5);
		String[] collapseArray = { "1", "1", "1", "1", "*" };
		DotsGame funGame = new DotsGame(collapseArray, g);
		ArrayList<Descriptor> actual = funGame.collapseColumn(0);
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(1, 0, new Dot(1)));
		expected.get(0).setPreviousRow(0);
		expected.add(new Descriptor(2, 0, new Dot(1)));
		expected.get(1).setPreviousRow(1);
		expected.add(new Descriptor(3, 0, new Dot(1)));
		expected.get(2).setPreviousRow(2);
		expected.add(new Descriptor(4, 0, new Dot(1)));
		expected.get(3).setPreviousRow(3);

		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testCollapseReturn6() {
		String msg = "Try to collapse array with 2 null";
		Generator g = new Generator(5);
		String[] collapseArray = { "0 1", "0 1", "0 *", "0 1", "0 *" };
		DotsGame funGame = new DotsGame(collapseArray, g);
		ArrayList<Descriptor> actual = funGame.collapseColumn(1);
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(2, 1, new Dot(1)));
		expected.get(0).setPreviousRow(0);
		expected.add(new Descriptor(3, 1, new Dot(1)));
		expected.get(1).setPreviousRow(1);
		expected.add(new Descriptor(4, 1, new Dot(1)));
		expected.get(2).setPreviousRow(3);

		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testCollapseReturn7() {
		String msg = "Try to collapse long array lots of nulls";
		Generator g = new Generator(5);
		String[] collapseArray = { "1", "1", "*", "1", "*", "1", "1", "*", "1", "*", "1", "1", "*", "1", "*", "1", "1",
				"*", "*", "*", "1", "1", "*", "*", "*", "1", "1", "*", "1", "*", "1", "1", "*", "1", "*", "1", "1", "*",
				"1", "*", "1", "1", "*", "1", "*", "1", "1", "*", "*", "*", "1", "1", "*", "1", "*", "1", "1", "*", "1",
				"*", };
		DotsGame funGame = new DotsGame(collapseArray, g);
		ArrayList<Descriptor> actual = funGame.collapseColumn(0);
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(27, 0, new Dot(1)));
		expected.get(0).setPreviousRow(0);
		expected.add(new Descriptor(28, 0, new Dot(1)));
		expected.get(1).setPreviousRow(1);
		expected.add(new Descriptor(29, 0, new Dot(1)));
		expected.get(2).setPreviousRow(3);
		expected.add(new Descriptor(30, 0, new Dot(1)));
		expected.get(3).setPreviousRow(5);
		expected.add(new Descriptor(31, 0, new Dot(1)));
		expected.get(4).setPreviousRow(6);
		expected.add(new Descriptor(32, 0, new Dot(1)));
		expected.get(5).setPreviousRow(8);
		expected.add(new Descriptor(33, 0, new Dot(1)));
		expected.get(6).setPreviousRow(10);
		expected.add(new Descriptor(34, 0, new Dot(1)));
		expected.get(7).setPreviousRow(11);
		expected.add(new Descriptor(35, 0, new Dot(1)));
		expected.get(8).setPreviousRow(13);
		expected.add(new Descriptor(36, 0, new Dot(1)));
		expected.get(9).setPreviousRow(15);
		expected.add(new Descriptor(37, 0, new Dot(1)));
		expected.get(10).setPreviousRow(16);
		expected.add(new Descriptor(38, 0, new Dot(1)));
		expected.get(11).setPreviousRow(20);
		expected.add(new Descriptor(39, 0, new Dot(1)));
		expected.get(12).setPreviousRow(21);
		expected.add(new Descriptor(40, 0, new Dot(1)));
		expected.get(13).setPreviousRow(25);
		expected.add(new Descriptor(41, 0, new Dot(1)));
		expected.get(14).setPreviousRow(26);
		expected.add(new Descriptor(42, 0, new Dot(1)));
		expected.get(15).setPreviousRow(28);
		expected.add(new Descriptor(43, 0, new Dot(1)));
		expected.get(16).setPreviousRow(30);
		expected.add(new Descriptor(44, 0, new Dot(1)));
		expected.get(17).setPreviousRow(31);
		expected.add(new Descriptor(45, 0, new Dot(1)));
		expected.get(18).setPreviousRow(33);
		expected.add(new Descriptor(46, 0, new Dot(1)));
		expected.get(19).setPreviousRow(35);
		expected.add(new Descriptor(47, 0, new Dot(1)));
		expected.get(20).setPreviousRow(36);
		expected.add(new Descriptor(48, 0, new Dot(1)));
		expected.get(21).setPreviousRow(38);
		expected.add(new Descriptor(49, 0, new Dot(1)));
		expected.get(22).setPreviousRow(40);
		expected.add(new Descriptor(50, 0, new Dot(1)));
		expected.get(23).setPreviousRow(41);
		expected.add(new Descriptor(51, 0, new Dot(1)));
		expected.get(24).setPreviousRow(43);
		expected.add(new Descriptor(52, 0, new Dot(1)));
		expected.get(25).setPreviousRow(45);
		expected.add(new Descriptor(53, 0, new Dot(1)));
		expected.get(26).setPreviousRow(46);
		expected.add(new Descriptor(54, 0, new Dot(1)));
		expected.get(27).setPreviousRow(50);
		expected.add(new Descriptor(55, 0, new Dot(1)));
		expected.get(28).setPreviousRow(51);
		expected.add(new Descriptor(56, 0, new Dot(1)));
		expected.get(29).setPreviousRow(53);
		expected.add(new Descriptor(57, 0, new Dot(1)));
		expected.get(30).setPreviousRow(55);
		expected.add(new Descriptor(58, 0, new Dot(1)));
		expected.get(31).setPreviousRow(56);
		expected.add(new Descriptor(59, 0, new Dot(1)));
		expected.get(32).setPreviousRow(58);

		checkDescriptorList(msg, expected, actual);
	}

	@Test
	public void testGenerator1() {
		String msg = "Tests functionality of a generator with a fixed type";
		Generator g = new Generator(6);
		for (int i = 0; i < 1000; i++) {
			int temp = g.generate().getType();
			if (temp != 6)
				fail(msg + " -Expected type: 6, actual type: " + temp);
		}

	}

	@Test
	public void testGenerator2() {
		String msg = "Tests functionality of a generator with a fixed type";
		Random rand = new Random(666);
		Generator g = new Generator(100, rand);
		for (int i = 0; i < 10000; i++) {
			int temp = g.generate().getType();
			if (temp > 99 || temp < 0)
				fail(msg + " -Number is out of bounds: " + temp);
		}
		if (g.generate().getType() != 33 || g.generate().getType() != 12 || g.generate().getType() != 31)
			fail(msg + " -Generator generated wrong \"random\" number (number 10000 should be 33, etc) hint: check seed");
	}

	@Test
	public void testFillColumn1() {
		String msg = "Fill a column with no null at the top";
		Generator g = new Generator(5);
		String[] board = { "1", "1", "1", "1", "1" };
		DotsGame actual = new DotsGame(board, g);
		String[] expected = { "1", "1", "1", "1", "1" };
		actual.fillColumn(0);

		checkGrid(msg, expected, actual);
	}

	@Test
	public void testFillColumn2() {
		String msg = "Fill a column with a null in the middle";
		Generator g = new Generator(5);
		String[] collapseArray = { "1", "1", "*", "1", "1" };
		DotsGame actual = new DotsGame(collapseArray, g);
		String[] expected = { "1", "1", "*", "1", "1" };
		actual.fillColumn(0);

		checkGrid(msg, expected, actual);
	}

	@Test
	public void testFillColumn3() {
		String msg = "Fill a column 1 null on top";
		Generator g = new Generator(5);
		String[] collapseArray = { "*", "1", "1", "1", "1" };
		DotsGame actual = new DotsGame(collapseArray, g);
		String[] expected = { "5", "1", "1", "1", "1" };
		actual.fillColumn(0);

		checkGrid(msg, expected, actual);
	}

	@Test
	public void testFillColumn4() {
		String msg = "Fill a column some null on top and some in middle";
		Generator g = new Generator(5);
		String[] collapseArray = { "*", "*", "1", "*", "1", "*" };
		DotsGame actual = new DotsGame(collapseArray, g);
		String[] expected = { "5", "5", "1", "*", "1", "*" };
		actual.fillColumn(0);

		checkGrid(msg, expected, actual);
	}

	@Test
	public void testFillColumn5() {
		String msg = "Fill a column based on random number generator given";
		Generator g = new Generator(10, new Random(666));
		String[] collapseArray = { "* *", "1 *", "1 *", "1 *", "1 1" };
		DotsGame actual = new DotsGame(collapseArray, g);
		String[] expected = { "* 5", "1 4", "1 7", "1 3", "1 1" };
		actual.fillColumn(1);

		checkGrid(msg, expected, actual);
	}

	@Test
	public void testFillColumn6() {
		String msg = "Fill a column and check returned values";
		Generator g = new Generator(10, new Random(666));
		String[] collapseArray = { "* *", "1 *", "1 2", "1 *", "1 1" };
		DotsGame theGame = new DotsGame(collapseArray, g);
		ArrayList<Descriptor> expected = new ArrayList<>();
		expected.add(new Descriptor(0, 1, new Dot(5)));
		expected.get(0).setPreviousRow(-1);
		expected.add(new Descriptor(1, 1, new Dot(4)));
		expected.get(0).setPreviousRow(-1);
		ArrayList<Descriptor> actual = theGame.fillColumn(1);

		checkDescriptorList(msg, expected, actual);
	}
}
