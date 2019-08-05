/** 
 * @author UC San Diego Intermediate Programming MOOC Team and Margarita Ostrovskaia 
 * */
package spelling;

import java.util.HashMap;
import java.util.Set;

/** Represents a node in a Trie */
class TrieNode {
	private HashMap<Character, TrieNode> children; 
	private String text;
	private boolean isWord;
	
	/** Create a new TrieNode */
	public TrieNode() {
		children = new HashMap<Character, TrieNode>();
		text = "";
		isWord = false;
	}
	
	/** Create a new TrieNode given a text String to store in it */
	public TrieNode(String text) {
		this();
		this.text = text;
	}
	
	/** Return the TrieNode that is the child when you follow the link from the given Character 
	 * 
	 * @param c The next character in the key
	 * @return The TrieNode that character links to, or null if that link is not in the trie. */
	public TrieNode getChild(Character c) {
		return children.get(c);
	}
	
	/** Inserts this character at this node.
	 * Returns the newly created node, if c wasn't already in the trie.
	 * If it was, it does not modify the trie and returns null.
	 * 
	 * @param c The character that will link to the new node
	 * @return The newly created TrieNode, or null if the node is already in the trie. */
	public TrieNode insert(Character c) {
		if (children.containsKey(c)) {
			return null;
		}
		
		TrieNode next = new TrieNode(text + c.toString());
		children.put(c, next);
		return next;
	}
	
	/** Return the text string at this node */
    public String getText() {
		return text;
	}
    
    // -1 - all lower / 0 - all upper / 1 - first upper 
    public String getText(int state) {
    		if(state == -1 || text.length() == 0)
    			return text;
    		
    		char[] chs = text.toLowerCase().toCharArray();
    		if(state == 1)
    			chs[0] = Character.toUpperCase(chs[0]);
    		else if(state == 0)
    			for(int i=0; i<chs.length; i++)
    				chs[i] = Character.toUpperCase(chs[i]);
    		
    		String str = new String(chs);
		return str;
	}
	
    /** Set whether or not this node ends a word in the trie. */
	public void setEndsWord(boolean b) {
		isWord = b;
	}
	
	/** Return whether or not this node ends a word in the trie. */
	public boolean endsWord() {
		return isWord;
	}
	
	/** Return the set of characters that have links from this node */
	public Set<Character> getValidNextCharacters() {
		return children.keySet();
	}
}