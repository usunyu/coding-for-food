package LCLibrary;

public class TreeLinkNode {
    public int val;
    public TreeLinkNode left, right, next;
    public TreeLinkNode(int x) { val = x; }

    public String toString() {
    	return "[" + val + "]";
    }
}