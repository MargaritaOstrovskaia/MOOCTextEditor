/** 
 * @author UC San Diego Intermediate Programming MOOC team and Margarita Ostrovskaia
 * date 03/29/2019
 */
package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/** An implementation of the MTG interface that uses a list of lists. */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText) { // TODO
		if (sourceText == "")
			return;
		
		String[] splitedText = sourceText.split("\\s+");
		starter = splitedText[0];
		
		for(int i=0; i<splitedText.length; i++) {
			String word = splitedText[i];
			
			// word 'splitedText[i]' contains in wordList
			boolean isContains = false;
			for(ListNode wn : wordList) {
				isContains = wn.getWord().equals(word);
				if (isContains) {
					if (i < splitedText.length-1) // add next word to node
						wn.addNextWord(splitedText[i+1]);
					
					break;
				}
			}
			
			// word 'splitedText[i]' not contains in wordList
			if (!isContains) {
				ListNode node = new ListNode(word);
				if (i < splitedText.length-1)
					node.addNextWord(splitedText[i+1]);
				wordList.add(node);
			}
		}
	}
	
	/** Generate the number of words requested. */
	@Override
	public String generateText(int numWords) { // TODO
		String genText = "";
		
		if (wordList.size() < 1 || numWords < 1)
			return genText;

		ListNode target;
		String nextWord = starter;
		genText += starter;
				
		for (int i=1; i<numWords; i++) {
			for(ListNode wn : wordList) {
				if (wn.getWord().equals(nextWord)) {
					target = wn;
					
					nextWord = target.getRandomNextWord(rnGenerator);
					if (nextWord == null)
						nextWord = wordList.get(0).getWord();
						
					genText = genText + " " + nextWord;
					starter = nextWord;
					break;
				}
			}
		}
		
		return genText;
	}
	
	// Can be helpful for debugging
	@Override
	public String toString() {
		String toReturn = "";
		for (ListNode n : wordList)
			toReturn += n.toString();
		
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText) { // TODO
		wordList.clear();
		starter = "";
		train(sourceText);
	}
	
	/** Random function */
	private int rand(int min, int max) {
		if (min > max || (max - min + 1 > Integer.MAX_VALUE))
			throw new IllegalArgumentException();
		
		return new Random().nextInt(max - min + 1) + min;
	}

	/*
	// This is a minimal set of tests.  Note that it can be difficult to test methods/classes with randomized behavior.
	public static void main(String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}
		*/
}

/** Links a word to the next words in the list 
 *  You should use this class in your implementation. */
class ListNode {
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word) {
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord() {
		return word;
	}

	public void addNextWord(String nextWord) {
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator) { // TODO
		int len = nextWords.size();
		if(len == 0)
			return null;

		int rand = generator.nextInt(len);
		String newWord = nextWords.get(rand);
		
	    return newWord;
	}

	public String toString() {
		String toReturn = word + ": ";
		for (String s : nextWords)
			toReturn += s + "->";
		toReturn += "\n";
		
		return toReturn;
	}	
}