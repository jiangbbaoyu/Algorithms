package com.jby.algorithms.GraphAlgorithms;

import java.util.*;

/**
 * 图的最小生成树问题
 * 最小生成树： 在满足图中所有节点的联通性的同时，使得联通所有节点的边上的权值之和最小。 此时图中所有点和边构成一棵最小生成树
 */
public class Question02 {

    // TODO 并查集结构改造 , 使得查 和 并  的操作为常数项
    static class UnionFoundSet1{
        public HashMap<GraphNode,List<GraphNode>> node2Set; //保存节点和该节点所在集合的对应关系
        public UnionFoundSet1(List<GraphNode> nodes){
            node2Set = new HashMap<>();
            for(GraphNode node:nodes){
                ArrayList<GraphNode> nodeSet = new ArrayList<>();
                nodeSet.add(node);
                node2Set.put(node,nodeSet);
            }
        }

        public boolean isInSameSet(GraphNode from,GraphNode to){
            return node2Set.get(from)==node2Set.get(to);
        }

        // 将from 和 to两个节点所在的集合进行合并
        public void union(GraphNode from,GraphNode to){
            List<GraphNode> fromNodeSets = node2Set.get(from);
            List<GraphNode> toNodeSets = node2Set.get(to);
            for (GraphNode node:fromNodeSets){
                toNodeSets.add(node); // from所在集合中的节点加入到to所在的集合中
                node2Set.put(node,toNodeSets); // 更新from集合中节点的所属集合
            }
        }
    }

    /**
     * kruskal 算法：
     *        初始时图中每个点分别属于一个集合，
     *        依次选择图中权值最小的边， 并判断该边两头的节点是否属于同一个集合，
     *              如果不属于同一个集合，则将这两个节点所在的集合合并为一个，并将该边纳入最小生成树的一部分。
     *              如果属于同一个集合（此时如果再加入该边，则节点集合中就产生了环），则该边不属于最小生成树的一部分
     */
    public Set<GraphEdge> kruskalMinistTree(Graph graph){
        ArrayList<GraphNode> nodes = new ArrayList<>();
        nodes.addAll(graph.nodes.values());
        UnionFoundSet1 unionFoundSet1 = new UnionFoundSet1(nodes);

        PriorityQueue<GraphEdge> graphEdges = new PriorityQueue<>(new Comparator<GraphEdge>() {
            @Override
            public int compare(GraphEdge o1, GraphEdge o2) {
                return o1.weight-o2.weight;
            }
        });
        for(GraphEdge edge: graph.edges){
            graphEdges.add(edge);
        }

        HashSet<GraphEdge> res = new HashSet<>();
        while(!graphEdges.isEmpty()){
            GraphEdge minWeightedEdge = graphEdges.poll();
            if (!unionFoundSet1.isInSameSet(minWeightedEdge.from,minWeightedEdge.to)){
                // 一条边的from节点所在的集合和to节点所在的集合不是同一个
                res.add(minWeightedEdge);
                unionFoundSet1.union(minWeightedEdge.from,minWeightedEdge.to);// 将这两个节点所在的集合合并为一个
            }
        }

        return res;
    }

    /**
     * prim 算法
     * 从图中的任意一个节点出发，将该节点加入到访问过的节点列表中。同时将该节点相关的边加入到优先队列中。
     * 从优先队列中取出权值最小的边，如果边的to端的节点没有访问过，
     *     则将该边作为最小生成树中的一条边，将to端的节点加入到访问过的节点列表中， 同时将to端节点关联的边加入到优先队列中
     *     否则跳过该边，
     *  继续从优先队列中取出权值最小的边进行处理
     *
     */

    public Set<GraphEdge> primMinistTree(Graph graph){

        HashSet<GraphEdge> res = new HashSet<>();
        HashSet<GraphNode> nodesTraversed = new HashSet<>();
        PriorityQueue<GraphEdge> queue = new PriorityQueue<>(new Comparator<GraphEdge>() {
            public int compare(GraphEdge e1, GraphEdge e2) {
                return e1.weight - e2.weight;   // 根据边的权值从小到大排序
            }
        });

        for(GraphNode graphNode : graph.nodes.values()){ // 考虑图中所有节点不是联通的，结果会生成多个最小生成树构成的森林的情况

            if(!nodesTraversed.contains(graphNode)){
                nodesTraversed.add(graphNode);
            }
            for(GraphEdge edge:graphNode.edges){
                queue.add(edge);
            }

            while(!queue.isEmpty()){
                GraphEdge edge = queue.poll();
                if(!nodesTraversed.contains(edge.to)){
                    nodesTraversed.add(edge.to);
                    res.add(edge);
                    for (GraphEdge e:edge.to.edges){
                        queue.add(e);
                    }
                }
            }
        }
        return res;
    }

}
