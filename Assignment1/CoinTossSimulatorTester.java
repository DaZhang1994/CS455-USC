// Name: Da Zhang
// USC NetID: zhan234
// CS 455 PA1
// Fall 2017

/**
 * class CoinTossSimulatorTester
 * Unit tester for coin toss simulator.
 * So if you run it on a buggy CoinTossSimulator, it might result in false.
 */
public class CoinTossSimulatorTester 
{
    /**
     * Main method, the entrance of application.
     * 
     * @param args	argument list (mostly for "java" commend line).
     */
    public static void main(String args[])
    {
	CoinTossSimulator coinTossSimulator = new CoinTossSimulator();
	System.out.println("After constructor:");
	System.out.println("Number of trials [exp:0]: " + coinTossSimulator.getNumTrials());
	System.out.println("Two-head tosses: " + coinTossSimulator.getTwoHeads());
	System.out.println("Two-tail tosses: " + coinTossSimulator.getTwoTails());
	System.out.println("One-head one-tail tosses: " + coinTossSimulator.getHeadTails());
	System.out.println("Tosses add up correctly? " + 
			   (coinTossSimulator.getTwoHeads() + coinTossSimulator.getTwoTails() + coinTossSimulator.getHeadTails() == 
			    coinTossSimulator.getNumTrials() ? true : false) +
			   "\n");
	
	coinTossSimulator.run(1);
	System.out.println("After run(1):");
	System.out.println("Number of trials [exp:1]: " + coinTossSimulator.getNumTrials());
	System.out.println("Two-head tosses: " + coinTossSimulator.getTwoHeads());
	System.out.println("Two-tail tosses: " + coinTossSimulator.getTwoTails());
	System.out.println("One-head one-tail tosses: " + coinTossSimulator.getHeadTails());
	System.out.println("Tosses add up correctly? " + 
			   (coinTossSimulator.getTwoHeads() + coinTossSimulator.getTwoTails() + coinTossSimulator.getHeadTails() == 
			    coinTossSimulator.getNumTrials() ? true : false) +
			   "\n");
	
	coinTossSimulator.run(10);
	System.out.println("After run(10):");
	System.out.println("Number of trials [exp:11]: " + coinTossSimulator.getNumTrials());
	System.out.println("Two-head tosses: " + coinTossSimulator.getTwoHeads());
	System.out.println("Two-tail tosses: " + coinTossSimulator.getTwoTails());
	System.out.println("One-head one-tail tosses: " + coinTossSimulator.getHeadTails());
	System.out.println("Tosses add up correctly? " + 
			   (coinTossSimulator.getTwoHeads() + coinTossSimulator.getTwoTails() + coinTossSimulator.getHeadTails() == 
			    coinTossSimulator.getNumTrials() ? true : false) +
			   "\n");
	
	coinTossSimulator.run(100);
	System.out.println("After run(100):");
	System.out.println("Number of trials [exp:111]: " + coinTossSimulator.getNumTrials());
	System.out.println("Two-head tosses: " + coinTossSimulator.getTwoHeads());
	System.out.println("Two-tail tosses: " + coinTossSimulator.getTwoTails());
	System.out.println("One-head one-tail tosses: " + coinTossSimulator.getHeadTails());
	System.out.println("Tosses add up correctly? " + 
			   (coinTossSimulator.getTwoHeads() + coinTossSimulator.getTwoTails() + coinTossSimulator.getHeadTails() == 
			    coinTossSimulator.getNumTrials() ? true : false) +
			   "\n");
	
	coinTossSimulator.reset();
	System.out.println("After reset:");
	System.out.println("Number of trials [exp:0]: " + coinTossSimulator.getNumTrials());
	System.out.println("Two-head tosses: " + coinTossSimulator.getTwoHeads());
	System.out.println("Two-tail tosses: " + coinTossSimulator.getTwoTails());
	System.out.println("One-head one-tail tosses: " + coinTossSimulator.getHeadTails());
	System.out.println("Tosses add up correctly? " + 
			   (coinTossSimulator.getTwoHeads() + coinTossSimulator.getTwoTails() + coinTossSimulator.getHeadTails() == 
			    coinTossSimulator.getNumTrials() ? true : false) +
			   "\n");
	
	coinTossSimulator.run(1000);
	System.out.println("After run(1000):");
	System.out.println("Number of trials [exp:1000]: " + coinTossSimulator.getNumTrials());
	System.out.println("Two-head tosses: " + coinTossSimulator.getTwoHeads());
	System.out.println("Two-tail tosses: " + coinTossSimulator.getTwoTails());
	System.out.println("One-head one-tail tosses: " + coinTossSimulator.getHeadTails());
	System.out.println("Tosses add up correctly? " + 
			   (coinTossSimulator.getTwoHeads() + coinTossSimulator.getTwoTails() + coinTossSimulator.getHeadTails() == 
			    coinTossSimulator.getNumTrials() ? true : false) +
			   "\n");
    }
}
