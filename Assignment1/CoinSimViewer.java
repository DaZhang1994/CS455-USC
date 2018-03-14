// Name: Da Zhang
// USC NetID: zhan234
// CS 455 PA1
// Fall 2017

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

import javax.swing.JFrame;
/**
 * class CoinSimViewer
 * Prompts for the number of trials.
 * Constructs JFrame, that is, the upper frame.
 * Adds coin simulator component to frame.
 * Adds window listener to monitor the frame close event.
 * Input trial numbers and run toss simulator.
 * If trial numbers <= 0, go back to input again.
 * 
 * NOTE: Doesn't handle the situation which the input is not a number or a decimal.
 * 		 If overflowed, the application will crash.
 *       No exception handling here.
 */
public class CoinSimViewer
{
    /**
     * Main method, the entrance of application.
     * 
     * @param args	argument list (mostly for "java" commend line).
     */
    public static void main(String args[])
    {
	JFrame jFrame = new JFrame();
	jFrame.addWindowListener(new WindowAdapter()
	{
	    public void windowClosing(WindowEvent e) 
            {
                System.exit(0);
            }
	});
	jFrame.setSize(800, 500);	
	Scanner in = new Scanner(System.in);
	int numTrials;
	while(true)
	{
	    System.out.println("Enter number of trials: ");
	    numTrials = in.nextInt();	
	    if(numTrials > 0)
		break;
	    System.out.println("ERROR: Number entered must be greater than 0.");
	}
	in.close();
	CoinSimComponent coinSimComponent = new CoinSimComponent(numTrials);
	jFrame.add(coinSimComponent);
	jFrame.setVisible(true);
    }
}
