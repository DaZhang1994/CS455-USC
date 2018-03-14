
// Name: Da Zhang
// USC loginid: zhan234
// CS 455 PA3
// Fall 2017

import java.util.LinkedList;

/**
 * Maze class
 * 
 * Stores information about a maze and can find a path through the maze (if
 * there is one).
 * 
 * Assumptions about structure of the maze, as given in mazeData, startLoc, and
 * endLoc (parameters to constructor), and the path: -- no outer walls given in
 * mazeData -- search assumes there is a virtual border around the maze (i.e.,
 * the maze path can't go outside of the maze boundaries) -- start location for
 * a path is maze coordinate startLoc -- exit location is maze coordinate
 * exitLoc -- mazeData input is a 2D array of booleans, where true means there
 * is a wall at that location, and false means there isn't (see public FREE /
 * WALL constants below) -- in mazeData the first index indicates the row. e.g.,
 * mazeData[row][col] -- only travel in 4 compass directions (no diagonal paths)
 * -- can't travel through walls
 * 
 */
public class Maze
{
	public static final boolean FREE = false;
	public static final boolean WALL = true;
	
	private boolean[][] mazeData;
	private boolean[][] visited;
	private MazeCoord entryLoc;
	private MazeCoord exitLoc;
	private LinkedList<MazeCoord> path = new LinkedList<MazeCoord>();
	
	/**
	 * Constructs a maze.
	 * Assigns the rows and columns for symbol boolean array -- visited
	 * 
	 * @param mazeData
	 *            the maze to search. See general Maze comments above for what goes
	 *            in this array.
	 * @param startLoc
	 *            the location in maze to start the search (not necessarily on an
	 *            edge)
	 * @param exitLoc
	 *            the "exit" location of the maze (not necessarily on an edge) PRE:
	 *            0 <= startLoc.getRow() < mazeData.length and 0 <=
	 *            startLoc.getCol() < mazeData[0].length and 0 <= endLoc.getRow() <
	 *            mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length
	 * 
	 */
	public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord exitLoc)
	{
		this.mazeData = mazeData;
		this.entryLoc = startLoc;
		this.exitLoc = exitLoc;
		visited = new boolean[numRows()][numCols()];
	}

	/**
	 * Returns the number of rows in the maze
	 * 
	 * @return number of rows
	 */
	public int numRows()
	{
		return mazeData.length; 
	}

	/**
	 * Returns the number of columns in the maze
	 * 
	 * @return number of columns
	 */
	public int numCols()
	{
		if(mazeData.length == 0)
		{
			return 0;
		}
		else
		{
			return mazeData[0].length; 
		}
	}

	/**
	 * Returns true iff there is a wall at this location
	 * 
	 * @param loc
	 *            the location in maze coordinates
	 * @return whether there is a wall here PRE: 0 <= loc.getRow() < numRows() and 0
	 *         <= loc.getCol() < numCols()
	 */
	public boolean hasWallAt(MazeCoord loc)
	{
		if(mazeData[loc.getRow()][loc.getCol()])
		{
			return WALL;
		}
		else
		{
			return FREE; 
		}
	}

	/**
	 * Returns the entry location of this maze.
	 */
	public MazeCoord getEntryLoc()
	{
		return entryLoc; 
	}

	/**
	 * Returns the exit location of this maze.
	 */
	public MazeCoord getExitLoc()
	{
		return exitLoc; 
	}

	/**
	 * Returns the path through the maze. First element is start location, and last
	 * element is exit location. If there was not path, or if this is called before
	 * a call to search, returns empty list.
	 * 
	 * @return the maze path
	 */
	public LinkedList<MazeCoord> getPath()
	{
		return path; 
	}

	/**
	 * Find a path from start location to the exit location (see Maze constructor
	 * parameters, startLoc and exitLoc) if there is one. Client can access the path
	 * found via getPath method.
	 * 
	 * @return whether a path was found.
	 */
	public boolean search()
	{
		if(hasWallAt(entryLoc) || hasWallAt(exitLoc))
		{
			return false; 
		}
		return recursiveSearch(entryLoc);
	}
	
	/**
	 * Helper method of search().
	 * finds out if there is a path recursively.
	 * finds a path from entryLoc to exitLoc if the maze has one, stores the path in an array list.
	 * the path nodes in the array list are from exitLoc to entryLoc, i.e., they are reversed.
	 * 
	 * @param node   
	 *            the current maze node, which is under manipulation
	 * @return whether there is a path from entryLoc to exitLoc
	 */
	private boolean recursiveSearch(MazeCoord node)
	{
		if(hasWallAt(node))
		{
			return false;
		}
		if(visited[node.getRow()][node.getCol()] == true)
		{
			return false;
		}
		if(exitLoc.equals(node))
		{	
		        path.clear();
			visited = new boolean[numRows()][numCols()];
			path.add(node);
			return true;
		}
		visited[node.getRow()][node.getCol()] = true;
		if (node.getRow() > 0)
		{
			if(recursiveSearch(new MazeCoord(node.getRow() - 1, node.getCol())))
			{
				path.add(node);
				return true;
			}
		}
		if (node.getCol() < numCols() - 1)
		{
			if(recursiveSearch(new MazeCoord(node.getRow(), node.getCol() + 1)))
			{
				path.add(node);
				return true;
			}
		}
		if(node.getRow() < numRows() - 1)
		{
			if(recursiveSearch(new MazeCoord(node.getRow() + 1, node.getCol()))) 
			{
				path.add(node);
				return true;
			}
		}
		if(node.getCol() > 0)
		{
			if(recursiveSearch(new MazeCoord(node.getRow(), node.getCol() - 1)))
			{
				path.add(node);
				return true;
			}
		}
		return false;
	}
}
