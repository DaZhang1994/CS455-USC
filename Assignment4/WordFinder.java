import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

// Name: Da Zhang
// USC NetID: zhan234
// CS 455 PA4
// Fall 2017

/**
 * Finds all the words which could be constructed by the letter in rack.
 * Entrance of the application, commend line user interface.
 * User could use a parameter as the path of dictionary.
 */
public class WordFinder
{
	private static final String DEFAULT_DICTIONARY = "sowpods.txt";
	
	/**
	 * Main method, app entrance.
	 * Prompts user to input.
	 * Processes user's input, return the outputs.
	 * @param args
	 * 	 	the dictionary path (could be omitted).
	 */
	public static void main(String[] args)
	{
		AnagramDictionary anagramDictionary = null;
		try
		{
			anagramDictionary = new AnagramDictionary(args.length == 0 ? DEFAULT_DICTIONARY : args[0]);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File does NOT exist!");
			System.exit(0);
		}
		System.out.println("Type . to quit.");
		Scanner in = new Scanner(System.in);
		while(true)
		{
			System.out.print("Rack? ");
			String letters = in.nextLine();
			if(".".equals(letters))
			{
				break;
			}
			Rack rack = new Rack(letters);
			ArrayList<String> subsets = rack.findAllSubsets();
			ArrayList<String> results = new ArrayList<String>();
			for(String s : subsets)
			{
				if(null != anagramDictionary.getAnagramsOf(s))
				{
					results.addAll(anagramDictionary.getAnagramsOf(s));
				}
			}
			ScoreTable scoreTable = new ScoreTable(results);
			formatOutput(scoreTable.getWordMap(), letters);
		}
	}
	
	/**
	 * Formats the output, that is, sort them by their keys and values,
	 * using Collections.sort() and Comparator.
	 * Print the outputs.
	 * 
	 * @param wordMap
	 * 	         	the map to be sorted.
	 * @param letters
	 * 	        	letters to be processed and sorted.
	 */
	private static void formatOutput(Map<String, Integer> wordMap, String letters)
	{
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(wordMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
		{
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2)
			{
				if(o1.getValue() > o2.getValue())
				{
					return -1;
				}
				else if(o1.getValue() < o2.getValue())
				{
					return 1;
				}
				else
				{
					return o1.getKey().compareTo(o2.getKey());
				}
			}
		});
		char[] charArr = letters.toCharArray();
		Arrays.sort(charArr);
		System.out.println("We can make " + wordMap.size() +" words from \"" + String.valueOf(charArr) + "\"");
		if(wordMap.size() > 0)
		{
			System.out.println("All of the words with their scores (sorted by score):");
		}
		for(Map.Entry<String, Integer> entry : list)
		{
			System.out.println(entry.getValue() + ": " + entry.getKey());
		}
	}
}
