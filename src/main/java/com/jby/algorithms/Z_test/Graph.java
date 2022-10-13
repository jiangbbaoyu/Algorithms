package com.jby.algorithms.Z_test;

import java.util.List;

public class Graph {

    List<GraphNode> nodes;
    List<GraphEdge> edges;
}

class GraphNode{
    public int value;
    public int inDegree;
    public int outDegree;

    List<GraphNode> toNodes;
    List<GraphEdge> edges;

}

class GraphEdge{
    int weight;
    GraphNode fromNode;
    GraphNode toNode;
}
