import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Name: Da Zhang
// USC NetID: zhan234
// CSCI455 PA2
// Fall 2017


/**
 * Class BulgarianSolitaireSimulator
 * 
 * Simulates Bulgarian Solitaire game and displays the result in each round.
 * User can input the initial configuration or the program will construct a random one.
 * User can control the display mode-- single step or full speed.
 */

public class BulgarianSolitaireSimulator 
{
	/**
	 * Main method, the entrance of application.
	 * 
	 * @param args	argument list (for java execution configuration).
	 * 		-u Prompts for the initial configuration from the user, instead of generating a random configuration.
	 * 		-s Stops between every round of the game. The game only continues when the user hits enter (a.k.a., return).
	 */
	public static void main(String[] args) 
	{	
		boolean singleStep = false;
		boolean userConfig = false;
		
		for (int i = 0; i < args.length; i++) 
		{
			if (args[i].equals("-u")) 
			{
				userConfig = true;
			}
			else if (args[i].equals("-s")) 
			{
				singleStep = true;
			}
		}
		SolitaireBoard solitaireBoard = createSolitaireBoard(userConfig);
		play(solitaireBoard, singleStep);
	}
	
	/**
	 * Creates the Solitaire Board by default mode or using user's configuration.
	 * Uses Regular Expression to limit the user's input in user configuration mode.
	 * Uses default SolitaireBoard constructor to construct a random initial configuration.
	 * 
	 * @param userConfig	uses user configuration or not.
	 * 			true-- uses user configuration(input the initial configuration).
	 * 		        false-- uses random configuration.
	 */
	private static SolitaireBoard createSolitaireBoard(Boolean userConfig)
	{
		if(userConfig)
		{
			List<Integer> configList = new ArrayList<Integer>();
			Scanner in = new Scanner(System.in);
			printAlert();
			boolean flag = false;
			int sum = 0;
			while(true)
			{
				sum = 0;
				flag = false;
				String config = in.nextLine();
				if(config.matches("^[\\s0-9]+$"))
				{
					for(String s : config.split("\\s"))
					{
						if("".equals(s))
						{
							continue;
						}
						else if(Integer.parseInt(s) == 0)
						{
							configList.clear();
							flag = false;
							break;
						}
						else
						{
							int num = Integer.parseInt(s);
							sum += num;
							configList.add(num);
							flag = true;
						}
					}
					if(sum != SolitaireBoard.CARD_TOTAL)
					{
						configList.clear();
						flag = false;
						printError();
					}
					if(flag)
					{
						break;
					}
				}
				else
				{
					printError();
				}
			}
			
			return new SolitaireBoard((ArrayList<Integer>) configList);
		}
		else
		{
			return new SolitaireBoard();
		}
	}
	
	/**
	 * Tell the user the total amount of card, and the rules to set configuration.
	 */
	private static void printAlert()
	{
		System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
		System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
		printInput();
	}
	
	/**
	 * Alert if the user's configuration is wrong.
	 */
	private static void printError()
	{
		System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be " + SolitaireBoard.CARD_TOTAL);
		printInput();
	}
	
	/**
	 * Remind the user to input the initial configuration, and the input rules.
	 */
	private static void printInput()
	{
		System.out.println("Please enter a space-separated list of positive integers followed by newline:");
	}
	
	/**
	 * Play the Bulgarian Solitaire game, and print every single round of the game.
	 * Uses an index to indicate the index of round.
	 * Uses "Initial configuration" to indicate the initial configuration.
	 * 
	 * @param solitaireBoard	Indicates which solitaire board will be played, more exactly,
	 * 							a random one or a solitaire board configured by user.
	 * @param singleStep		Uses single step mode or not.
	 * 							true-- uses single step mode.
	 * 							false-- uses full speed mode.
	 */
	private static void play(SolitaireBoard solitaireBoard, Boolean singleStep)
	{
		int index = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("Initial configuration: " + solitaireBoard.configString());
		while(!solitaireBoard.isDone())
		{
			solitaireBoard.playRound();
			index++;
		       
			if(singleStep)
			{
				System.out.print("<Type return to continue>");
				in.nextLine();
			}
			System.out.println("[" + index + "] Current configuration: " + solitaireBoard.configString());
		}

		System.out.println("Done!");
	}
}
