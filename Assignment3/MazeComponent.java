
// Name: Da Zhang
// USC loginid: zhan234
// CS 455 PA3
// Fall 2017

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

/**
 * MazeComponent class
 * 
 * A component that displays the maze and path through it if one has been found.
 */
public class MazeComponent extends JComponent
{
	private static final int START_X = 10; // top left of corner of maze in frame
	private static final int START_Y = 10;
	private static final int BOX_WIDTH = 20; // width and height of one maze "location"
	private static final int BOX_HEIGHT = 20;
	private static final int INSET = 2; // how much smaller on each side to make entry/exit inner box
	private Maze maze; //MazeComponent depends on Maze
	
	/**
	 * Constructs the component.
	 * 
	 * @param maze
	 *            the maze to display
	 */
	public MazeComponent(Maze maze)
	{
		this.maze = maze;
	}

	/**
	 * Draws the current state of maze including the path through it if one has been
	 * found.
	 * 
	 * @param g
	 *            the graphics context
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		paintNodes(g2);
		paintEdge(g2);
		if(maze.getPath().size() > 0)
		{
			paintPath(g2);
		}
	}
	
	/**
	 * Draws the maze edges(a rectangle)
	 * 
	 * @param g2
	 * 	      the graphics context
	 */
	private void paintEdge(Graphics2D g2)
	{
		Rectangle edge = new Rectangle(START_X, START_Y, BOX_WIDTH * maze.numCols(), BOX_HEIGHT * maze.numRows());	
		g2.setColor(Color.BLACK);
		g2.draw(edge);
	}
	
	/**
	 * Draws nodes, entry point and exit point
	 * 
	 * @param g2
	 * 	       the graphics context
	 */
	private void paintNodes(Graphics2D g2)
	{
		for(int i = 0; i < maze.numRows(); i++)
		{
			for(int j = 0; j <maze.numCols(); j++)
			{
				int startPointX = START_X + BOX_WIDTH * j;
				int startPointY = START_Y + BOX_HEIGHT * i;
				MazeCoord mazeCoord = new MazeCoord(i, j);
				if(maze.hasWallAt(mazeCoord))
				{
					Rectangle wall = new Rectangle(startPointX, 
								       startPointY, 
								       BOX_WIDTH, 
								       BOX_HEIGHT); 
					g2.setColor(Color.BLACK);
					g2.fill(wall);
				}
				else
				{
					Rectangle wall = new Rectangle(startPointX, 
								       startPointY, 
								       BOX_WIDTH, 
								       BOX_HEIGHT); 
					g2.setColor(Color.WHITE);
					g2.fill(wall);
				}
				if(maze.getEntryLoc().equals(mazeCoord))
				{
					Rectangle entry = new Rectangle(startPointX + INSET / 2, 
									startPointY + INSET / 2, 
									BOX_WIDTH - INSET, 
									BOX_HEIGHT - INSET); 
					g2.setColor(Color.YELLOW);
					g2.fill(entry);
				}
				if(maze.getExitLoc().equals(mazeCoord))
				{
					Rectangle exit = new Rectangle(startPointX + INSET / 2, 
								       startPointY + INSET / 2, 
								       BOX_WIDTH - INSET, 
								       BOX_HEIGHT - INSET); 
					g2.setColor(Color.GREEN);
					g2.fill(exit);
				}
			}
		}
	}
	
	/**
	 * Draws the path from entryLoc to exitLoc if there is one
	 * Uses a MazeCoord object to record previous node
	 * 
	 * @param g2
	 * 	       the graphics context
	 */
	private void paintPath(Graphics2D g2)
	{
		g2.setColor(Color.BLUE);
		MazeCoord prevNode = new MazeCoord(maze.getPath().get(0).getRow(), maze.getPath().get(0).getCol());
		for(MazeCoord path : maze.getPath())
		{	
			int startPointX = START_X + BOX_WIDTH * prevNode.getCol() + BOX_WIDTH / 2;
			int startPointY = START_Y + BOX_HEIGHT * prevNode.getRow() + BOX_HEIGHT / 2;
			if(path.getRow() == prevNode.getRow() - 1)
			{
				g2.drawLine(startPointX, 
					    startPointY, 
				            startPointX, 
					    startPointY - BOX_HEIGHT);
			}
			if(path.getCol() == prevNode.getCol() + 1)
			{
				g2.drawLine(startPointX, 
					    startPointY, 
					    startPointX + BOX_WIDTH, 
					    startPointY);
			}
			if(path.getRow() == prevNode.getRow() + 1)
			{
				g2.drawLine(startPointX, 
					    startPointY, 
					    startPointX, 
					    startPointY + BOX_HEIGHT);
			}
			if(path.getCol() == prevNode.getCol() - 1)
			{
				g2.drawLine(startPointX, 
					    startPointY, 
					    startPointX - BOX_WIDTH, 
					    startPointY);
			}
			prevNode = new MazeCoord(path.getRow(), path.getCol());
		}
	}
}
