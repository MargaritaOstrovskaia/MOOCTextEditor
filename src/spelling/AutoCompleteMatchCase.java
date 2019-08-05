package spelling;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class AutoCompleteMatchCase implements  Dictionary, AutoComplete {
    private TrieNode root;
    private int size;
    
    public AutoCompleteMatchCase() {
		root = new TrieNode();
		size = 0;
	}
	
	public boolean addWord(String word) {
		boolean isAdded = false;
		word = word.toLowerCase();

		TrieNode targetNode = root;
		char[] words =  word.toCharArray();
		for (int i=0; i<words.length; i++) {
			char ch = words[i];
			TrieNode currentNode = targetNode.getChild(ch);
			if(currentNode == null) {
				targetNode = targetNode.insert(ch);
				
				if (i == words.length-1) {
					targetNode.setEndsWord(true);
					size++;
					isAdded = true;
				}
			}
			else {
				targetNode = currentNode;
				
				if (i == words.length-1)
					if (!targetNode.endsWord()) {
						targetNode.setEndsWord(true);
						size++;
						isAdded = true;
					}
			}
		}
		
	    return isAdded;
	}
	
	public int size() {
	    return size;
	}
	
	@Override
	public boolean isWord(String s) {
		s = s.toLowerCase();
		
		TrieNode targetNode = root;
		char[] words =  s.toCharArray();
		for (int i=0; i<words.length; i++) {
			char ch = words[i];
			targetNode = targetNode.getChild(ch);
			
			if(targetNode == null)
				return false;
			
			if(targetNode.getText().equals(s) && targetNode.endsWord())
				return true;
		}
		
		return false;
	}
	
	private int getStringState(String str) {
		// -1 - all lower / 0 - all upper / 1 - first upper
		int state = -1;
		
		if(str.length() == 0)
			return state;
		else {
			char[] chs = str.toCharArray();
			
			if( Character.isUpperCase(chs[0]) )
					state = 1;
			
			if(chs.length > 1 && state == 1)
				if( Character.isUpperCase(chs[1]) )
					state = 0;
		}
		
		return state;
	}

	@Override
	public List<String> predictCompletions(String prefix, int numCompletions) {
		List<String> words = new ArrayList<String>();
		
		if (numCompletions < 1)
			return words;
		
		// -1 - all lower / 0 - all upper / 1 - first upper 
		int state = getStringState(prefix);
		
		TrieNode prefNode = root;
		if (prefix.length() > 0) {
			char[] prefWords =  prefix.toLowerCase().toCharArray();
			for (int i=0; i<prefWords.length; i++) {
				prefNode = prefNode.getChild(prefWords[i]);
				if(prefNode == null)
					return words;
			}
		}
		
        Queue<TrieNode> queue = new LinkedList<TrieNode>();
        queue.add(prefNode);
        
        while (!queue.isEmpty()) {
        		TrieNode node = queue.remove();
            if(node != null) {
            		if(node.endsWord()) {
            			words.add(node.getText(state));
            			
            			if(words.size() == numCompletions)
            				return words;
            		}
            		
            		for(Character ch : node.getValidNextCharacters())
            			queue.add(node.getChild(ch));
            }
        }
    	 
         return words;
     }

 	public void printTree() {
 		printNode(root);
 	}
 	
 	public void printNode(TrieNode curr) {
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}	
}