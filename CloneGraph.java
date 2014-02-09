import java.util.ArrayList;
import java.util.HashMap;

class UndirectedGraphNode {
    int label;
    ArrayList<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
};

class Solution {
    private UndirectedGraphNode cloneGraph(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> nodeMap) {
        if(nodeMap.containsKey(node)) return nodeMap.get(node);
        UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
        for(UndirectedGraphNode n : node.neighbors) {
            if(n == node) {
                cloneNode.neighbors.add(cloneNode);
            }
            else {
                cloneNode.neighbors.add(cloneGraph(n, nodeMap));
            }
        }
        nodeMap.put(node, cloneNode);
        return cloneNode;
    }
    
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null;
        HashMap<UndirectedGraphNode, UndirectedGraphNode> nodeMap = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        return cloneGraph(node, nodeMap);
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        UndirectedGraphNode node0 = new UndirectedGraphNode(0);
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        node0.neighbors.add(node1); node0.neighbors.add(node2);
        node1.neighbors.add(node2);
        node2.neighbors.add(node2);
        solution.cloneGraph(node0);
    }
}