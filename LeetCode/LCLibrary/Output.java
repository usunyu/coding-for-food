package LCLibrary;

public class Output {
	public static void printList(ListNode node) {
        while(node != null) {
        	if(node.next != null)
            	System.out.print(node.val + " -> ");
            else
            	System.out.print(node.val);
            node = node.next;
        }
        System.out.println();
    }
}