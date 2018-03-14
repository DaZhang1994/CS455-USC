
// Name: Da Zhang
// USC NetID: zhan234
// CS 455 PA4
// Fall 2017

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A dictionary of all anagram sets. Note: the processing is case-sensitive; so
 * if the dictionary has all lower case words, you will likely want any string
 * you test to have all lower case letters too, and likewise if the dictionary
 * words are all upper case.
 */

public class AnagramDictionary
{
	Map<String, ArrayList<String>> words;

	/**
	 * Create an anagram dictionary from the list of words given in the file
	 * indicated by fileName. PRE: The strings in the file are unique.
	 * 
	 * @param fileName
	 *            the name of the file to read from
	 * @throws FileNotFoundException
	 *             if the file is not found
	 */
	public AnagramDictionary(String fileName) throws FileNotFoundException
	{
		words = new HashMap<String, ArrayList<String>>();

		File file = new File(fileName);
		Scanner in = new Scanner(file);
		while (in.hasNextLine())
		{
			String word = in.nextLine();
			char[] charArr = word.toCharArray();
			Arrays.sort(charArr);
			String anagramWord = String.valueOf(charArr);
			if (!words.containsKey(anagramWord))
			{
				ArrayList<String> wordList = new ArrayList<String>();
				wordList.add(word);
				words.put(anagramWord, wordList);
			}
			else
			{
				words.get(anagramWord).add(word);
			}
		}
		in.close();
	}

	/**
	 * Get all anagrams of the given string. This method is case-sensitive. E.g. 
	 * "CARE" and "race" would not be recognized as anagrams.
	 * 
	 * @param s
	 *            string to process
	 * @return a list of the anagrams of s
	 * 
	 */
	public ArrayList<String> getAnagramsOf(String s)
	{
		char[] charArr = s.toCharArray();
		Arrays.sort(charArr);
		String anagramWord = String.valueOf(charArr);
		if(null == words.get(anagramWord))
		{
			return null;
		}
		else
		{
			return new ArrayList<String>(words.get(anagramWord)); 
		}
	}

}
