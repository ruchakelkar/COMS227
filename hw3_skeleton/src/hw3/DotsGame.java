
package hw3;

import java.util.ArrayList;
import api.Descriptor;
import api.Dot;
import api.Util;

/**
 * This class encapsulates the game logic for a video game called Dots.
 * The game consists of a 2D array or grid of colored icons, called dots, 
 * along with an ordered list that we will call the selection list.  Intuitively, 
 * the selection list represents a set of adjacent dots, all of the same color, 
 * that have been selected by the player.  When selection is complete (e.g. the 
 * mouse is released), the selected dots disappear from the grid, and the dots 
 * above shift down  to take their places.  Then new dots fill in each column from the top.
 * A point is scored for each dot that disappears from the grid.  There
 * is a special rule for the case that the selected dots form a loop;
 * then all dots in the grid of the same color disappear too.
 * @author RuchaKelkar
 */
public class DotsGame
{
	/**
	 * Constructs a new 2D array to reference the one given by the DotsGame constructor with the String[] parameter.
	 */	
	private Dot[][] grid;

	/**
	 * Constructs an array in which to store selected Dots. 
	 */
	private ArrayList<Descriptor> selectionList = new ArrayList<>();
	
	/**
	 * Constructs a new generator to reference the one given by the DotsGame constructors; used to generate new dots. 
	 */
	private Generator dotsGenerator;
	
	/**
	 * Stores the score of the game.
	 */
	private int score = 0;
	
	/**
	 * Constructs a game with the given number of columns and rows that will use
	 * the given Generator instance to create new icons.  The dots
	 * in the initial grid are produced by the generator.
	 * 
	 * @param width
	 *          number of columns
	 * @param height
	 *          number of rows
	 * @param generator
	 *          generator for new icons
	 */
	public DotsGame(int width, int height, Generator generator)
	{
		grid = new Dot[height][width];
		dotsGenerator = generator;
		dotsGenerator.initialize(grid);
	}

	/**
	 * Constructs a game based on the given string array according to
	 * the conventions of Util.createGridFromString. The given Generator 
	 * instance is used to create new dots.  
	 * 
	 * @param data
	 *          string indicating initial configuration of grid
	 * @param generator
	 *          generator for new icons
	 */
	public DotsGame(String[] data, Generator generator)
	{
		grid = Util.createGridFromString(data);
		dotsGenerator = generator;
	} 

	/**
	 * Returns the Dot object at the given row and column.
	 * @param row
	 *   row within the grid
	 * @param col
	 *   column within the grid
	 * @return
	 *   Dot object at the given row and column
	 */
	public Dot getDot(int row, int col)
	{
		return grid[row][col];
	}

	/**
	 * Sets the Dot object at the given row and column.
	 * @param row
	 *   row of the grid to be modified
	 * @param col
	 *   column of the grid to be modified
	 * @param dot
	 *   the given Dot object to set
	 */
	public void setDot(int row, int col, Dot dot)
	{
		grid[row][col] = dot;
	}

	/**
	 * Returns the number of columns in this game.
	 * @return
	 *   number of columns
	 */
	public int getWidth()
	{
		return grid[0].length;
	}

	/**
	 * Returns the number of rows in this game.
	 * @return
	 *   number of rows
	 */
	public int getHeight()
	{
		return grid.length;
	}

	/**
	 * Returns the current score for this game.
	 * @return
	 *   score for this game
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 * Attempts to select the dot at given position. A descriptor for the dot is
	 * added to the selection list provided that a) the given position is
	 * adjacent to the last one added to the selection list, and b) its type matches
	 * the type of those already in the selection list, and c) the given position
	 * is not already in the selection list OR it completes a loop.
	 * Completing a loop means that the given position matches the first one in
	 * the selection list, the list has length at least 3, and the given position does
	 * not already occur twice in the list.
	 * @param row
	 *   row of the dot to be selected
	 * @param col
	 *   column of the dot to be selected
	 */
	public void select(int row, int col)
	{
		Dot dot = grid[row][col];
		if(selectionList.size() != 0)
		{
			Descriptor prevDotDescriptor = selectionList.get(selectionList.size()-1);
			if(((prevDotDescriptor.row() == row) && Math.abs(prevDotDescriptor.col() - col) == 1 || (prevDotDescriptor.col() == col && Math.abs(prevDotDescriptor.row() - row) == 1)) && dot.getType() == prevDotDescriptor.getDot().getType() )
			{
				Descriptor currentDot = new Descriptor(row, col, dot); 
				Descriptor firstDot = selectionList.get(0);
				ArrayList<Descriptor> temp = new ArrayList<>(selectionList);
				temp.remove(0);
				if(!(selectionList.contains(currentDot)) || (selectionList.size() >= 3 && firstDot.samePosition(currentDot) && !(temp.contains(currentDot))))
				{
					selectionList.add(currentDot);
				}
			} 
		}
		else
		{
			Descriptor descriptor = new Descriptor(row, col, dot);
			selectionList.add(descriptor);
		}
	}



	/**
	 * Returns a list of descriptors for currently selected dots.
	 * @return
	 *   the selection list
	 */
	public ArrayList<Descriptor> getSelectionList()
	{

		return selectionList;
	}

	/**
	 * If the selection list has at least two elements, replaces all selected positions 
	 * with null, clears the selection list, and updates the score.  If the selection 
	 * list does not contain at least two elements, no positions are nulled but the
	 * selection list is still cleared.  If the selection list includes a completed loop,
	 * then all dots of matching type are also nulled and the score is updated accordingly.  
	 * The method returns a list containing all nulled positions.  (The list is in
	 * no particular order but should not contain duplicates.)
	 * @return
	 *   list of descriptors for cells that are nulled as a result of this operation
	 */
	public ArrayList<Descriptor> release()
	{
		ArrayList<Descriptor> releaseList = new ArrayList<>(selectionList);
		if(selectionList.size() >= 2)
		{
			Descriptor firstDot = selectionList.get(0);
			Descriptor lastDot = selectionList.get(selectionList.size()-1);
			if(firstDot.samePosition(lastDot))
			{
				for(int i = 0; i < grid.length; i++)
				{
					Dot[] temp = grid[i];
					for(int j = 0; j < temp.length; j++)
					{
						Descriptor descriptor = new Descriptor(i, j, grid[i][j]);
						if(!(releaseList.contains(descriptor)) && descriptor.getDot().getType() == firstDot.getDot().getType())
						{
							releaseList.add(descriptor);
						}
					}
				}
				releaseList.remove(0);				
			}
			for(Descriptor dotDescriptor : releaseList)
			{
				int row = dotDescriptor.row();
				int col = dotDescriptor.col();
				grid[row][col] = null;
			}
		}
		else
		{
			releaseList.clear();
		}
		score += releaseList.size();
		selectionList.clear(); 
		return releaseList;
	}


	/**
	 * Collapses the dots in the given column of the current game grid such 
	 * that all null dots, if any, are at the top of the column 
	 * and non-null dots are shifted toward the bottom (i.e., as if by gravity).  
	 * The returned list contains Descriptors representing dots that were moved (if any)
	 * with their new row and column; moreover, each Descriptor's <code>getPreviousRow</code>
	 * method returns the original row of the dot.  The returned list is 
	 * in no particular order.
	 * @param col
	 *   column to be collapsed
	 * @return
	 *   list of descriptors for moved dots
	 */
	public ArrayList<Descriptor> collapseColumn(int col)
	{	
		ArrayList<Descriptor> updatedRows = new ArrayList<>();
		int index = grid.length - 1;
		for(int row = index; row >= 0; row--)
		{
			Dot dot = grid[row][col];
			int previousRow = row;
			if(dot != null)
			{	
				Dot tempDot = grid[index][col];
				grid[index][col] = grid[row][col];
				grid[row][col] = tempDot;
				Descriptor descriptor = new Descriptor(index, col, dot);
				descriptor.setPreviousRow(previousRow);
				index--;
				
				if(descriptor.getPreviousRow() != descriptor.row())
				{
					updatedRows.add(descriptor);
				}
			}
			
		}
		return updatedRows;
	}


	/**
	 * Fills the null grid positions (if any) at the top of the given column in the
	 * current game grid.  The returned list contains Descriptors representing new
	 * dots added to the column with their new row and column. The previous row
	 * for all descriptors is set to -1. The new dots are
	 * produced by the generator's <code>generate</code> method. The list is 
	 * in no particular order.
	 * 
	 * @param col
	 *   column to be filled
	 * @return
	 *   list of new descriptors for dots added to the column
	 */
	public ArrayList<Descriptor> fillColumn(int col)
	{
		ArrayList<Descriptor> fillList = new ArrayList<>();
		
		for(int i = 0; i < grid.length; i++)
		{
			if(grid[i][col] == null)
			{
				grid[i][col] = dotsGenerator.generate();
				Descriptor descriptor = new Descriptor(i, col, grid[i][col]);
				descriptor.setPreviousRow(-1);
				fillList.add(descriptor);
			}
		}
		return fillList;
	}
}
