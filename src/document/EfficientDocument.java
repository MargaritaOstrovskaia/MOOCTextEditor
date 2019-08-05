/**
 * @author UC San Diego Intermediate Programming MOOC team
 * @author Margarita Ostrovskaia
 * date 03/23/2019 
 */
package document;

import java.util.List;

/** A class that represents a text document
 *  It does one pass through the document to count the number of syllables, words, and sentences and then stores those values. */
public class EfficientDocument extends Document {

	private int numWords;  		// The number of words in the document
	private int numSentences;  	// The number of sentences in the document
	private int numSyllables;  	// The number of syllables in the document
	
	public EfficientDocument(String text) {
		super(text);
		processText();
	}
	
	/** Take a string that either contains only alphabetic characters, or only sentence-ending punctuation.  
	 *  Return true if the string contains only alphabetic characters, and false if it contains end of sentence punctuation.  
	 * @param tok The string to check
	 * @return true if tok is a word, false if it is punctuation. */
	private boolean isWord(String tok) {
		return !(tok.indexOf("!") >=0 || tok.indexOf(".") >=0 || tok.indexOf("?")>=0);
	}
	
    /** Passes through the text one time to count the number of words, syllables and sentences, and set the member variables appropriately.
     *  Words, sentences and syllables are defined as described below. */
	private void processText()
	{
		List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");
		
		numSentences = 0;
		numWords = 0;
		numSyllables = 0;
		
		int len = tokens.size();
		for(int i=0; i<len; i++) {
			if (isWord(tokens.get(i))) {
				numWords += 1;
				numSyllables += countSyllables(tokens.get(i));
				
				if (i == len-1)
					numSentences += 1;
			}
			else {
				if (numWords > 0)
					numSentences += 1;
			}
		}
	}
	
	/** Get the number of sentences in the document.
	 * @return The number of sentences in the document. */
	@Override
	public int getNumSentences() {
		return this.numSentences;
	}
	
	/** Get the number of words in the document.
	 * @return The number of words in the document. */
	@Override
	public int getNumWords() {
		return this.numWords;
	}

	/** Get the total number of syllables in the document (the stored text). 
	 * @return The number of syllables in the document. */
	@Override
	public int getNumSyllables() {
        return this.numSyllables;
	}
}