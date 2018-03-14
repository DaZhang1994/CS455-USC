// Name: Da Zhang
// USC NetID: zhan234
// CSCI455 PA2
// Fall 2017

import java.util.ArrayList;
import java.util.Random;
/**
 * class SolitaireBoard
 * The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
 * by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
 * for CARD_TOTAL that result in a game that terminates.
 * (See comments below next to named constant declarations for more details on this.)
 */

public class SolitaireBoard 
{
       /**
        * Representation invariant:
        * Uses NUM_FINAL_PILES to indicate the pile number
        * CARD_TOTAL will be calculated by the rules NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) /2
        * The sum of every element in piles should be CARD_TOTAL
        * Every valid element (the element which will be displayed in every single round) should be above 0 and below CARD_TOTAL
        */
        // number of piles in a final configuration
        // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
        public static final int NUM_FINAL_PILES = 9;

        // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
        // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
        // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES
        public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
	
        private int piles[] = new int[CARD_TOTAL];

        private int pileNumber;
	
	private Random r;
 
        /**
         * Creates a solitaire board with the configuration specified in piles.
         * piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
         * PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
         */
	public SolitaireBoard(ArrayList<Integer> piles) 
	{
		pileNumber = piles.size();
		for(int i = 0; i < piles.size(); i++)
		{
			this.piles[i] = piles.get(i);	
		}
		assert isValidSolitaireBoard();   // sample assert statement (you will be adding more of these calls)
	}
 
   
	/**
         * Creates a solitaire board with a random initial configuration.
	 */
        public SolitaireBoard() 
        {
		r = new Random();
		int sum = 0;
		int num = 0;
		for(int i = 0; ; i++)
		{
			num = r.nextInt(SolitaireBoard.CARD_TOTAL - sum) + 1;
			piles[i] = num;
			sum += num;
			if(sum  == SolitaireBoard.CARD_TOTAL)
			{
				break;
			}
		}
		pileNumber = piles.length;
		assert isValidSolitaireBoard();
	}
  
   
	/**
         * Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
         * of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
         * The old piles that are left will be in the same relative order as before, 
         * and the new pile will be at the end.
	 */
        public void playRound() 
        {
		boolean isZero = false;
		int newPile = 0;
		int j = 0;
		for(int i = 0; i < pileNumber; i++)
		{
			if(piles[i] > 0)
			{
				piles[i]--;
				newPile++;
			}
			if(piles[i] == 0)
			{
				isZero = true;
				j = i;
			}	
		}
		if(!isZero)
		{
			piles[pileNumber] = newPile;
			pileNumber++;	
		}
		else
		{
			piles[j] = newPile;
		}
		assert isValidSolitaireBoard();
        }
   
	/**
         * Returns true if the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
         * piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
	 */
   
        public boolean isDone() 
        {
		boolean[] flags = new boolean[CARD_TOTAL + 1];
		boolean flag = true;
		for(int i = 0; i < piles.length; i++)
		{
			if(piles[i] != 0) 
			{
				flags[piles[i]] = true;
			}
		}
		for(int i = 1; i <= NUM_FINAL_PILES; i++)
		{
			if(flags[i] == false)
			{
				flag = false;
			}
		}
		assert isValidSolitaireBoard();
		return flag;
        }

   
	/**
         * Returns current board configuration as a string with the format of
         * a space-separated list of numbers with no leading or trailing spaces.
         * The numbers represent the number of cards in each non-empty pile.
	 */
        public String configString() 
        {
		String s = "";
		for(int i : piles)
		{
			if(i != 0)
			{
				s += i + " ";
			}
		}
		assert isValidSolitaireBoard();
		return s.substring(0, s.length()-1);  
        }
   
   
	/**
         * Returns true if the solitaire board data is in a valid state
         * (See representation invariant comment for more details.)
	 */
	private boolean isValidSolitaireBoard()
	{
		int sum = 0;
		for(int i : piles)
		{
			if(i < 0 || i > SolitaireBoard.CARD_TOTAL)
			{
				return false;
			}
			sum += i;
		}
		if(sum == SolitaireBoard.CARD_TOTAL)
		{
			return true;  
		}
		else
		{
			return false;
		}
	}
   

        // <add any additional private methods here>


}
