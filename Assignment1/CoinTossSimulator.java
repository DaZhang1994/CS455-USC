//Name: Da Zhang
//USC NetID: zhan234
//CS 455 PA1
//Fall 2017

import java.util.Random;

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
public class CoinTossSimulator 
{
    private int twoHeads;
    private int twoTails;
    private int headTails;
    
    /**
     * Creates a coin toss simulator with no trials done yet.
     */
    public CoinTossSimulator() 
    {
	
    }
    
    /**
     * Runs the simulation for numTrials more trials. Multiple calls to this method
     * without a reset() between them *add* these trials to the current simulation. 
     * 
     * @param numTrials  number of trials to for simulation; must be >= 1
     */
     public void run(int numTrials)
     {
         Random coin1 = new Random();
	 Random coin2 = new Random();
	 for(int i = 0; i < numTrials; i++)
	 {
	     if(coin1.nextBoolean())
		 if(coin2.nextBoolean())
		     twoHeads++;
		 else
		     headTails++;
	     else
		 if(coin2.nextBoolean())
		     headTails++;
		 else
		     twoTails++;
	 }
     }
    
    /**
     * Get number of trials performed since last reset.
     */
    public int getNumTrials() 
    {
	return getTwoHeads() + getTwoTails() + getHeadTails();
    }
    
    /**
     * Get number of trials that came up two heads since last reset.
     */
    public int getTwoHeads()
    {   
	return twoHeads;
    }
    
    /**
     * Get number of trials that came up two tails since last reset.
     */  
    public int getTwoTails()
    {
	return twoTails;
    }
    
    /**
     * Get number of trials that came up one head and one tail since last reset.
     */
    public int getHeadTails() 
    {  
	return headTails;
    }
    
    /**
     * Resets the simulation, so that subsequent runs start from 0 trials done.
     */
    public void reset() 
    {
	twoHeads = 0;
	twoTails = 0;
	headTails = 0;
    }
}
