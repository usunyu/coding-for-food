import java.util.HashMap;

class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
};

class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        HashMap<RandomListNode, RandomListNode> nodeMap = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode current = head;
        RandomListNode cloneHead = null, clonePrev = null;
        // copy node
        while(current != null) {
            RandomListNode clone = new RandomListNode(current.label);
            nodeMap.put(current, clone);
            if(cloneHead == null) {
                cloneHead = clone;
                clonePrev = clone;
            }
            else {
                clonePrev.next = clone;
                clonePrev = clone;
            }
            current = current.next;
        }
        // copy random
        current = head;
        RandomListNode cloneCurrent = cloneHead;
        while(current != null) {
            cloneCurrent.random = nodeMap.get(current.random);
            current = current.next;
            cloneCurrent = cloneCurrent.next;
        }
        return cloneHead;
    }

    // reference : http://fisherlei.blogspot.com/2013/11/leetcode-copy-list-with-random-pointer.html
    // space complexity : O(1)
    public RandomListNode copyRandomList2(RandomListNode head) {
        if(head == null) return null;
        // insert nodes
        RandomListNode current = head, clone;
        while(current != null) {
            clone = new RandomListNode(current.label);
            RandomListNode tmp = current.next;
            current.next = clone;
            clone.next = tmp;
            current = tmp;
        }
        // copy random pointer
        current = head;
        while(current != null) {
            clone = current.next;
            if(current.random == null) clone.random = null;
            else clone.random = current.random.next;
            current = current.next.next;
        }
        // decouple two links
        current = head;
        RandomListNode cloneHead = null, clonePrev = null;
        while(current != null) {
            clone = current.next;
            if(cloneHead == null) {
                cloneHead = clone;
                clonePrev = clone;
            }
            else {
                clonePrev.next = clone;
                clonePrev = clone;
            }
            current.next = current.next.next;
            current = current.next;
        }
        return cloneHead;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        RandomListNode node = new RandomListNode(-1);
        node.random = node;
        solution.copyRandomList2(node);
    }
}