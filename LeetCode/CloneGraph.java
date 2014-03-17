/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
*/

import java.util.ArrayList;
import java.util.HashMap;
import LCLibrary.*;

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

    private UndirectedGraphNode cloneGraph2(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
        if(map.containsKey(node)) {
            return map.get(node);
        }
        else {
            UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
            map.put(node, cloneNode);
            for(UndirectedGraphNode neighbor : node.neighbors) {
                cloneNode.neighbors.add(cloneGraph2(neighbor, map));
            }
            return cloneNode;
        }
    }
    
    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        if(node == null) return null;
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        return cloneGraph2(node, map);
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        UndirectedGraphNode node = Input.buildExampleUndirectedGraph();
        solution.cloneGraph(node);
    }
}