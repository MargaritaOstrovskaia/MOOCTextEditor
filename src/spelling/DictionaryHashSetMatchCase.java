package spelling;

import java.util.HashSet;

/**@author Margarita Ostrovskaia
 * date 04/02/2019 */
public class DictionaryHashSetMatchCase implements Dictionary 
{
    private HashSet<String> words;
	
	public DictionaryHashSetMatchCase() {
	    words = new HashSet<String>();
	}
	
	@Override
	public boolean addWord(String word) {
		return words.add(word.toLowerCase());
	}

    @Override
	public int size() {
    		return words.size();
	}
	
    @Override
	public boolean isWord(String s) {
    		return words.contains(s.toLowerCase());
	} 
}