/** 
 * @author UC San Diego Intermediate MOOC team and Margarita Ostrovskaia
 * date 04/02/2019
 */
package spelling;

import java.util.TreeSet;

public class DictionaryBST implements Dictionary {
	
   private TreeSet<String> dict;
	
   public DictionaryBST() {
	   dict = new TreeSet<String>();
   }
    
    /** Add this word to the dictionary.
     * 
     * @param word The word to add
     * @return true if the word was added to the dictionary (it wasn't already there). */
    public boolean addWord(String word) { // TODO
		word = word.toLowerCase();
		if(dict.contains(word))
			return false;
		
		dict.add(word);
		return true;
    }

    /** Return the number of words in the dictionary */
    public int size() {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        return dict.contains(s.toLowerCase());
    }
}