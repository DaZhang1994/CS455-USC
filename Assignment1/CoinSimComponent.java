// Name: Da Zhang
// USC NetID: zhan234
// CS 455 PA1
// Fall 2017

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

/**
 * class CoinSimComponent
 * Initializes data and run toss simulator.
 * Uses the simulator to construct the GUI result.
 */
public class CoinSimComponent extends JComponent
{
    private static final long serialVersionUID = 1L;
    private static final int BAR_WIDTH = 50;
    private static final int BOTTOM = 20;
    
    private Bar twoHeadsBar;
    private Bar headTailsBar;
    private Bar twoTailsBar;
    private CoinTossSimulator coinTossSimulator;
    
    /**
     * Creates the coin simulator component with trial number.
     */
    public CoinSimComponent(int numTrials)
    {
	coinTossSimulator = new CoinTossSimulator();
	coinTossSimulator.run(numTrials);
    }
    
    /**
     * Paints coin simulator component.
     * This method is a callback method, i.e., it will be called by system rather than than the user.
     * Called when the component is placed in a container or the container is being resized.
     * 
     * @param g	the container/canvas/context/super layer(Besides Panel..., not just the Frame)
     */
    protected void paintComponent(Graphics g)
    {
	int left = (int) ((g.getClipBounds().getMaxX() - 3 * BAR_WIDTH) / 4);
	Rectangle2D labelBounds = g.getFont().getStringBounds(" ", ((Graphics2D) g).getFontRenderContext());
	int labelHeight = (int) labelBounds.getHeight();
	double scale = (g.getClipBounds().getMaxY() - 2 * BOTTOM - labelHeight) / coinTossSimulator.getNumTrials();
	twoHeadsBar = new Bar(BOTTOM, 
			      left, 
			      BAR_WIDTH, 
			      coinTossSimulator.getTwoHeads(), 
			      scale, 
			      Color.RED, 
			      "Two Heads: " + coinTossSimulator.getTwoHeads() + "(" + 
			      Math.round((float) coinTossSimulator.getTwoHeads() / coinTossSimulator.getNumTrials() * 100) + 
			      "%)");
	headTailsBar = new Bar(BOTTOM, 
			       2 * left + BAR_WIDTH,
			       BAR_WIDTH, 
			       coinTossSimulator.getHeadTails(), 
			       scale, 
			       Color.GREEN,
			       "A Head and a Tail: " + coinTossSimulator.getHeadTails() + "(" +
			       Math.round((float) coinTossSimulator.getHeadTails() / coinTossSimulator.getNumTrials() * 100) + 
			       "%)");
	twoTailsBar = new Bar(BOTTOM, 
			      3 * left + 2 * BAR_WIDTH, 
			      BAR_WIDTH, 
			      coinTossSimulator.getTwoTails(), 
			      scale, 
			      Color.BLUE, 
			      "Two Tails: " + coinTossSimulator.getTwoTails() + "(" +
			      Math.round((float) coinTossSimulator.getTwoTails() / coinTossSimulator.getNumTrials() * 100) + 
			      "%)");
	twoHeadsBar.draw((Graphics2D) g);
	headTailsBar.draw((Graphics2D) g);
	twoTailsBar.draw((Graphics2D) g);
    }   
}
