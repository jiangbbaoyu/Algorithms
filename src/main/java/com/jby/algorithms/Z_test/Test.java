package com.jby.algorithms.Z_test;

import java.util.HashMap;
import java.util.LinkedList;

public class Test {

    public static void topoSort(Graph graph){

        HashMap<GraphNode, Integer> nodesInDegreeMap = new HashMap<>();
        LinkedList<GraphNode> queue = new LinkedList<>();
        for(GraphNode node:graph.nodes){
            nodesInDegreeMap.put(node,node.inDegree);
            if(node.inDegree==0){
                queue.addFirst(node);
            }
        }

        while(!queue.isEmpty()){

            GraphNode node = queue.removeLast();
            System.out.println(node.value);

            for(GraphNode toNode : node.toNodes){

                if(!nodesInDegreeMap.containsKey(toNode)){
                    continue;
                }
                nodesInDegreeMap.put(toNode,nodesInDegreeMap.get(toNode)-1);
                if(nodesInDegreeMap.get(toNode)-1==0){
                    queue.addFirst(toNode);
                }
            }
        }

    }

}
