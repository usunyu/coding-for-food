// Accept a text message, possibly of more than one line.
// Create a Huffman tree for this message.
// Create a code table.
// Encode this message into binary.
// Decode the message from binary back to text.
import java.io.*;
import java.util.*;

class Node {
	char ch;
	int frequency;
	Node leftChild;
	Node rightChild;

	public Node(char ch, int frequency) {
		this.ch = ch;
		this.frequency = frequency;
		leftChild = null;
		rightChild = null;
	}

	public int getFrequency() {
		return frequency;
	}

	public char getChar() {
		return ch;
	}

	public void displayNode() {
		System.out.print(ch);
	}
}

class HuffmanTree {
	private Node root;

	public HuffmanTree(Node node) {
		root = node;
	}

	public HuffmanTree(HuffmanTree t1, HuffmanTree t2) {
		Node n1 = t1.getRoot();
		Node n2 = t2.getRoot();
		int f1 = n1.getFrequency();
		int f2 = n2.getFrequency();
		int f3 = f1 + f2;
		root = new Node('+', f3);
		root.leftChild = n1;
		root.rightChild = n2;
	}

	public Node getRoot() {
		return root;
	}

	public void displayTree() {
		Stack<Node> globalStack = new Stack<Node>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("......................................................");
		while(isRowEmpty==false) {
			Stack<Node> localStack = new Stack<Node>();
			isRowEmpty = true;

			for(int j=0; j<nBlanks; j++)
				System.out.print(' ');

			while(globalStack.isEmpty()==false) {
				Node temp = (Node)globalStack.pop();
				if(temp != null) {
					temp.displayNode();
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);

					if(temp.leftChild != null || temp.rightChild != null)
						isRowEmpty = false;
				}
				else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for(int j=0; j<nBlanks*2-2; j++)
					System.out.print(' ');
			}  // end while globalStack not empty

			System.out.println();
			nBlanks /= 2;
			while(localStack.isEmpty()==false)
				globalStack.push( localStack.pop() );
		}  // end while isRowEmpty is false
		System.out.println("......................................................");
	}
}

class HuffmanTreeApp {
	static Hashtable<Character, Integer> frequencyTable = new Hashtable<Character, Integer>();
	static ArrayList<Character> charList = new ArrayList<Character>();
	static Queue<HuffmanTree> priorityQueue;
	static Hashtable<String, Character> decodeTable = new Hashtable<String, Character>();
	static Hashtable<Character, String> encodeTable = new Hashtable<Character, String>();

	public static void main(String[] args) throws Exception {
		String input;
		String output = "huffman.txt";
		while(true) {
			System.out.print("Please input the file name: ");
			System.out.flush();
			input = getString();

			if(input.equals(""))
				break;

			BufferedReader br = getFile(input);
			String line = "";
			String text = "";
			while ((line = br.readLine()) != null)
				text = text + line;
			System.out.println("Text: " + text);
			br.close();

			buildFrequencyTable(text);
			// displayFrequencyTable();

			Comparator<HuffmanTree> comparator = getComparator();
			priorityQueue =  new PriorityQueue<HuffmanTree>(11, comparator);
			initialQueue();

			HuffmanTree huffmanTree = constructHuffmanTree();
			huffmanTree.displayTree();

			buildCodeTable(huffmanTree);

			displayCodeTable();

			BufferedWriter out = writeFile(output);
			String huffmanCode = encodeText(text, out);
			System.out.println("Huffman Code: " + huffmanCode);
			String decodeText = decodeText(huffmanCode);
			System.out.println("Text: " + decodeText);
		}
	}

	private static String decodeText(String huffmanCode) {
		String temp = "";
		String text = "";
		for(int i = 0; i < huffmanCode.length(); i++) {
			temp += huffmanCode.charAt(i);
			if(decodeTable.containsKey(temp)) {
				char ch = decodeTable.get(temp);
				text += ch;
				temp = "";
			}
		}
		return text;
	}

	private static String encodeText(String text, BufferedWriter out) throws Exception {
		String huffmanCode = "";
		for(int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			String code = encodeTable.get(ch);
			huffmanCode += code;
		}
		out.write(huffmanCode);
		out.close();
		return huffmanCode;
	}

	private static void displayCodeTable() {
		System.out.println("Code Table:");
		for(int i = 0; i < charList.size(); i++) {
			char ch = charList.get(i);
			System.out.println(ch + "\t==>\t" + encodeTable.get(ch));
		}
	}

	private static void buildCodeTable(HuffmanTree huffmanTree) {
		buildCodeTableRec(huffmanTree.getRoot(), "");
	}

	private static void buildCodeTableRec(Node node, String code) {
		if(node.leftChild != null) {
			Node lChild = node.leftChild;
			String newCode = code + "1";
			char ch = lChild.getChar();
			if(ch == '+') {
				buildCodeTableRec(lChild, newCode);
			}
			else {
				decodeTable.put(newCode, ch);
				encodeTable.put(ch, newCode);
			}
		}
		if(node.rightChild != null) {
			Node rChild = node.rightChild;
			String newCode = code + "0";
			char ch = rChild.getChar();
			if(ch == '+') {
				buildCodeTableRec(rChild, newCode);
			}
			else {
				decodeTable.put(newCode, ch);
				encodeTable.put(ch, newCode);
			}
		}
	}

	private static HuffmanTree constructHuffmanTree() {
		while(!priorityQueue.isEmpty()) {
			if(priorityQueue.size() == 1)
				break;
			HuffmanTree ht1 = priorityQueue.poll();
			HuffmanTree ht2 = priorityQueue.poll();
			HuffmanTree ht3 = new HuffmanTree(ht1, ht2);
			priorityQueue.add(ht3);
		}
		return priorityQueue.poll();
	}

	private static void initialQueue() {
		for(int i = 0; i < charList.size(); i++) {
			char ch = charList.get(i);
			int f = frequencyTable.get(ch);
			Node node = new Node(ch, f);
			HuffmanTree tree = new HuffmanTree(node);
			priorityQueue.add(tree);
		}
	}

	private static void buildFrequencyTable(String text) {
		for(int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if(frequencyTable.containsKey(ch)) {
				int f = frequencyTable.get(ch);
				frequencyTable.put(ch, ++f);
			}
			else {
				frequencyTable.put(ch, 1);
				charList.add(ch);
			}
		}
	}

	private static void displayFrequencyTable() {
		for(int i = 0; i < charList.size(); i++) {
			char ch = charList.get(i);
			System.out.println(ch + "\t==>\t" + frequencyTable.get(ch));
		}
	}

	public static Comparator<HuffmanTree> getComparator() {
		Comparator<HuffmanTree> comparator =  new Comparator<HuffmanTree>() {   
            public int compare(HuffmanTree t1, HuffmanTree t2) {   
            	int f1 = t1.getRoot().getFrequency();
                int f2 = t2.getRoot().getFrequency();
                if(f2 > f1)   
                    return -1;
                else if(f2 < f1)
                    return 1;
                else
                    return 0;
            }    
        };
        return comparator;
	}

	public static BufferedWriter writeFile(String fileName) throws Exception {
		FileWriter fstream = new FileWriter(fileName);
		BufferedWriter out = new BufferedWriter(fstream);
		return out;
	}

	public static BufferedReader getFile(String fileName) throws Exception {
		FileInputStream fstream = new FileInputStream(fileName);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		return br;
	}

	public static String getString() throws Exception {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}


