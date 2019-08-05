/**
 * @author UC San Diego Intermediate MOOC team and Margarita Ostrovskaia
 * date 04/02/2019 
 */
package spelling;

import java.util.HashSet;

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