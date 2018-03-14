import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Name: Da Zhang
// USC NetID: zhan234
// CS 455 PA4
// Fall 2017

/**
 * A score table mapping the value to each letter
 * 
 */
public class ScoreTable
{
	private static final int[] SCORE_TABLE = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
	private static final char LOWER_A = 'a';
	private static final char LOWER_Z = 'z';
	private static final char UPPER_A = 'A';
	private static final char UPPER_Z = 'Z';
	
	private Map<String, Integer> wordMap;
	
	/**
	 * Calculates the value of each word
	 * Sets the word itself and its value to a Treemap
	 * 
	 * @param words
	 * 				the word to be iterated and processed
	 */
	public ScoreTable(List<String> words)
	{
		wordMap = new TreeMap<String, Integer>();
		
		for(String word : words)
		{
			char[] arr = word.toCharArray();
			int value = 0;
			for(char c : arr)
			{
				if(c >= UPPER_A && c <= UPPER_Z)
				{
					value += SCORE_TABLE[c - UPPER_A];
				}
				else if(c >= LOWER_A && c <= LOWER_Z)
				{
					value += SCORE_TABLE[c - LOWER_A];
				}
			}
			wordMap.put(word, value);
		}
	}
	
	/**
	 * Getter method of wordMap
	 * 
	 * @return
	 * 			a copy of wordMap
	 */
	public Map<String, Integer> getWordMap()
	{
		return new TreeMap<String, Integer>(wordMap);
	}
	
}
