
// Name: Da Zhang
// USC NetID: zhan234
// CS 455 PA4
// Fall 2017

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A Rack of Scrabble tiles
 */

public class Rack
{
	private String unique = "";
	private int[] mult;
	private String letters;
	
	/**
	 * Creates a new Rack instance using the given letters.
	 * Extracts the unique letters and the multiplicity of each letter.
	 * 
	 * @param letters
	 *                 String to process
	 */
	public Rack(String letters)
	{
		this.letters = new String(letters);
		extractUnique();
	}
	
	/**
	 * Invokes the helper method allSubsets() to find all subsets.
	 * public method to be provoked, as the helper function is private.
	 * 
	 * @return	all subsets of the indicated multiset (except the "", nil string)
	 */
	public ArrayList<String> findAllSubsets()
	{
		ArrayList<String> list = allSubsets(unique, mult, 0);
		for(int i = 0; i < list.size(); i++)
		{
			if("".equals(list.get(i)))
			{
				list.remove(i);
			}
		}
		return list;
	}
	
	/**
	 * Finds all subsets of the multiset starting at position k in unique and mult.
	 * unique and mult describe a multiset such that mult[i] is the multiplicity of
	 * the char unique.charAt(i). PRE: mult.length must be at least as big as
	 * unique.length() 0 <= k <= unique.length()
	 * 
	 * @param unique
	 *            a string of unique letters
	 * @param mult
	 *            the multiplicity of each letter from unique.
	 * @param k
	 *            the smallest index of unique and mult to consider.
	 * @return all subsets of the indicated multiset
	 * @author Claire Bono
	 */
	private static ArrayList<String> allSubsets(String unique, int[] mult, int k)
	{
		ArrayList<String> allCombos = new ArrayList<>();

		if (k == unique.length())
		{ // multiset is empty
			allCombos.add("");
			return allCombos;
		}

		// get all subsets of the multiset without the first unique char
		ArrayList<String> restCombos = allSubsets(unique, mult, k + 1);

		// prepend all possible numbers of the first char (i.e., the one at position k)
		// to the front of each string in restCombos. Suppose that char is 'a'...

		String firstPart = ""; // in outer loop firstPart takes on the values: "", "a", "aa", ...
		for (int n = 0; n <= mult[k]; n++)
		{
			for (int i = 0; i < restCombos.size(); i++)
			{ // for each of the subsets
				// we found in the recursive call
				// create and add a new string with n 'a's in front of that subset
				allCombos.add(firstPart + restCombos.get(i));
			}
			firstPart += unique.charAt(k); // append another instance of 'a' to the first part
		}

		return allCombos;
	}
	
	/**
	 * Extracts all unique letter and their multiplicity from the string.
	 * invoked by constructor, affects the private variable, so it does not
	 * have a return value.
	 * 
	 */
	private void extractUnique()
	{
		Map<String, Integer> letterMap = new HashMap<String, Integer>();
		for(int i = 0; i < letters.length(); i++)
		{
			String s = String.valueOf(letters.charAt(i));
			if(letterMap.containsKey(s))
			{
				Integer mult = letterMap.get(s);
				mult++;
				letterMap.put(s, mult++);
			}
			else
			{
				letterMap.put(s, 1);
			}
		}
		mult = new int[letterMap.keySet().size()];
		int i = 0;
		for(Map.Entry<String, Integer> entry : letterMap.entrySet())
		{
			unique += entry.getKey();
			mult[i] = entry.getValue();
			i++;
		}
	}
}
