/*
Given a list of millions of words, design an algorithm to create the largest possible rectangle of letters such 
that every row forms a word (reading left to right) and every column forms a word (reading top to bottom). 
The words need not be chosen consecutively from the list, but all rows must be the same length and all columns 
must be the same height.
*/

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;
import CtCILibrary.AssortedMethods;

/* A container for a group of words of the same length. */
class WordGroup {
	private HashSet<String> lookup = new HashSet<String>();
	private ArrayList<String> group = new ArrayList<String>();

	public boolean containsWord(String s) {
    	return lookup.contains(s);
    }

    public void addWord (String s) {
    	group.add(s);
    	lookup.add(s);
    }

    public int length() {
        return group.size();
    }

    public String getWord(int i) {
        return group.get(i);
    }

    public ArrayList<String> getWords(){ 
        return group;
    }

    public static WordGroup[] createWordGroups(String[] list) {
    	WordGroup[] groupList;
    	int maxWordLength = 0;
		// Find out the length of the longest word
		for (int i = 0; i < list.length; i++) {
			if (list[i].length() > maxWordLength) {
				maxWordLength = list[i].length();
			}
		}

		/* Group the words in the dictionary into lists of words of 
		 * same length.groupList[i] will contain a list of words, each 
		 * of length (i+1). */
		groupList = new WordGroup[maxWordLength];
		for (int i = 0; i < list.length; i++) {
			/* We do wordLength - 1 instead of just wordLength since this is used as
			 * an index and no words are of length 0 */
			int wordLength = list[i].length() - 1; 
			if (groupList[wordLength] == null) {
				groupList[wordLength] = new WordGroup();
			}
			groupList[wordLength].addWord(list[i]);
		}
		return groupList;
    }
}

/* A class that represents a rectangular array of letters. */
class Rectangle {
    // Rectangle data.
    public int height;
    public int length; 
    public char [][] matrix;

    public Rectangle(int len) {
        this.length = len;
    }

    /* Construct a rectangular array of letters of the specified length
     * and height, and backed by the specified matrix of letters. (It is
     * assumed that the length and height specified as arguments are 
     * consistent with the array argument's dimensions.)
     */
    public Rectangle(int length, int height, char[][] letters) {
        this.height = letters.length;
        this.length = letters[0].length;
        matrix = letters;
    }

    /* Return the letter present at the specified location in the array.
     */
    public char getLetter (int i, int j) {
        return matrix[i][j];
    }
    
    public String getColumn(int i) {
		char[] column = new char[height];
		for (int j = 0; j < height; j++) {
			column[j] = getLetter(j, i);
		}
		return new String(column);
    }
    
    public boolean isComplete(int l, int h, WordGroup groupList) {
		// Check if we have formed a complete rectangle.
		if (height == h) {
			// Check if each column is a word in the dictionary.
			for (int i = 0; i < l; i++) {
				String col = getColumn(i);
				if (!groupList.containsWord(col)) {
					return false; // Invalid rectangle.
				}
			}
			return true; // Valid Rectangle!
		}
		return false;
    }
    
    public boolean isPartialOK(int l, Trie trie) {
    	if (height == 0) {
    		return true;
    	}
		for (int i = 0; i < l ; i++ ) {
			String col = getColumn(i);
			if (!trie.contains(col)) {
				return false; // Invalid rectangle.
			}
		}
		return true;
    }

    /* If the length of the argument s is consistent with that of this
     * Rectangle object, then return a Rectangle whose matrix is constructed by
     * appending s to the underlying matrix. Otherwise, return null. The
     * underlying matrix of this Rectangle object is /not/ modified.
     */
    public Rectangle append(String s) {
        if (s.length() == length) {
            char temp[][] = new char[height + 1][length];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < length; j++) {
                    temp[i][j] = matrix[i][j];
                }
            }
            s.getChars(0, length, temp[height], 0);

            return new Rectangle(length, height + 1, temp);
        }
        return null;
    }

    /* Print the rectangle out, row by row. */
    public void print() {
        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println(" ");
        }
    }
}

/* One node in the trie. Most of the logic of the trie is implemented
 * in this class.
 */
class TrieNode {
	// The children of this node in the trie.
    private LinkedList<TrieNode> children;
    private boolean terminates = false;

    // The character stored in this node as data.
    private char character;	

    /* Constructs an empty trie node and initializes the list of its children to an empty list. Used
     * only to construct the root node of the trie. 
     * 
     * Using a LinkedList will take less space than an array, since we allocate no more space than
     * necessary. Another good option would be a hash table, to map from character to the TrieNode.
     * This is faster, but will require more space.
     */
    public TrieNode() {
    	children = new LinkedList<TrieNode>();
    }

    /* Constructs a trie node and stores in the node the char passed in
     * as the argument. Initializes the list of child nodes of this
     * node to an empty list.
     */
    public TrieNode(char character) {
        this();
        this.character = character;
    }

    /* Getter for the character data stored in this node.
     */
    public char getChar() {
        return character;
    }

    /* Add the String passed in as argument to the trie, starting at a
     * child node of this node. If any prefix of this String is already
     * present in the trie starting from a child node of this node, only
     * add the remaining part of the String to the trie, at the
     * appropriate position in the trie.
     */
    public void addWord(String word) {
    	if (word == null || word.isEmpty()) {
    		return;
    	}
    	
        TrieNode child;
        char firstChar = word.charAt(0);

        TrieNode t = getChild(firstChar);

        if (t == null) {
            child = new TrieNode(firstChar);
            children.add(child);
        } else {
            child = t;
        }

        if (word.length() > 1) {
            child.addWord(word.substring(1));
        } else {
        	child.setTerminates(true);
        }
    }

    /* Find a child node of this node that has the char argument as its
     * data. Return null if no such child node is present in the trie.
     */
    TrieNode getChild(char c) {
        for (TrieNode t : children) {
            if (t.getChar() == c) {
                return t;
            }
        }
        return null;
    }

    public boolean terminates() {
    	return terminates;
    }
    
    public void setTerminates(boolean t) {
    	terminates = t;
    }
}

/* Implements a trie. We store the input list of words in tries so
 * that we can efficiently find words with a given prefix. 
 */ 
class Trie {
	// The root of this trie.
    private TrieNode root;

    /* Takes a list of strings as an argument, and constructs a trie that stores these strings. */
    public Trie(ArrayList<String> list) {
        root = new TrieNode();
        for (String word : list) {
            root.addWord(word);
        }
    }

    /* Takes a list of strings as an argument, and constructs a trie that stores these strings. */    
    public Trie(String[] list) {
        root = new TrieNode();
        for (String word : list) {
            root.addWord(word);
        }
    }

    /* Checks whether this trie contains a string with the prefix passed
     * in as argument.
     */
    public boolean contains(String prefix, boolean exact) {
        TrieNode lastNode = root;
        int i = 0;
        for (i = 0; i < prefix.length(); i++) {
            lastNode = lastNode.getChild(prefix.charAt(i));
            if (lastNode == null) {
                return false;	 
            }
        }
        return !exact || lastNode.terminates();
    }
    
    public boolean contains(String prefix) {
    	return contains(prefix, false);
    }
}

class WordDict {
	private int maxWordLength;
	private WordGroup[] groupList ;
	private Trie trieList[];

	public WordDict(String[] list) {
		groupList = WordGroup.createWordGroups(list);
		maxWordLength = groupList.length;
		// Initialize trieList to store trie of groupList[i] at ith position.
		trieList = new Trie[maxWordLength];
	}

	/* This function finds a rectangle of letters of the largest 
	 * possible area (length x breadth) such that every row forms a 
	 * word (reading left to right) from the list and every column 
	 * forms a word (reading top to bottom) from the list. 
	 */
	public Rectangle maxRectangle() {
		// The dimensions of the largest possible rectangle.
		int maxSize = maxWordLength * maxWordLength; 

		for (int z = maxSize; z > 0; z--) {
			// Find out all pairs i,j less than maxWordLength 
			// such that i * j = z
			for (int i = 1; i <= maxWordLength; i ++ ) {
				if (z % i == 0) {
					int j = z / i;
					if (j <= maxWordLength) {
						// Check if a Rectangle of length i and height 
						// j can be created. 
						Rectangle rectangle = makeRectangle(i,j);
						if (rectangle != null) {
							return rectangle;
						}
					}
				}
			}
		}
		return null;
	}

	/* This function takes the length and height of a rectangle as
	 * arguments. It tries to form a rectangle of the given length and 
	 * height using words of the specified length as its rows, in which 
	 * words whose length is the specified height form the columns. It 
	 * returns the rectangle so formed, and null if such a rectangle 
	 * cannot be formed.
	 */
	private Rectangle makeRectangle(int length, int height) {
		if (groupList[length - 1] == null || groupList[height - 1] == null) {
			return null;
		}
		if (trieList[height - 1] == null) {
			ArrayList<String> words = groupList[height - 1].getWords();
			trieList[height - 1] = new Trie(words); 
		}
		return makePartialRectangle(length, height, new Rectangle(length));
	}

	/* This function recursively tries to form a rectangle with words
	 * of length l from the dictionary as rows and words of length h
	 * from the dictionary as columns. To do so, we start with an empty
	 * rectangle and add in a word with length l as the first row. We
	 * then check the trie of words of length h to see if each partial
	 * column is a prefix of a word with length h. If so we branch
	 * recursively and check the next word till we've formed a complete
	 * rectangle. When we have a complete rectangle check if every
	 * column is a word in the dictionary.
	 */
	private Rectangle makePartialRectangle(int l, int h, Rectangle rectangle) {

		// Check if we have formed a complete rectangle by seeing if each column
		// is in the dictionary
		if (rectangle.height == h) {
			if (rectangle.isComplete(l, h, groupList[h - 1])) {
				return rectangle;
			} else {
				return null;
			}
		}

		// If the rectangle is not empty, validate that each column is a
		// substring of a word of length h in the dictionary using the
		// trie of words of length h.
		if (!rectangle.isPartialOK(l, trieList[h - 1])) {
			return null;
		}

		// For each word of length l, try to make a new rectangle by adding
		// the word to the existing rectangle.
		for (int i = 0; i < groupList[l-1].length(); i++) {
			Rectangle orgPlus = rectangle.append(groupList[l-1].getWord(i));
			Rectangle rect = makePartialRectangle(l, h, orgPlus);
			if (rect != null) {
				return rect;
			}
		}
		return null;
	}
}

class Solution {
	public static void main(String[] args) {
		WordDict dict = new WordDict(AssortedMethods.getListOfWords());
		Rectangle rect = dict.maxRectangle();
		if (rect != null) {
			rect.print();
		} else {
			System.out.println ("No rectangle exists");
		}
	}
}