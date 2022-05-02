package com.jby.algorithms.GraphAlgorithms;

import org.junit.Test;

import java.util.*;

/**
 * 单源最短路径问题
 * 给定图中的一个节点，求该节点到图中其他所有节点的最短路径（该节点到其他节点的路径上的所有边的权值最小）
 */
public class Question03 {

    /**
     * Dijkstra 算法 （使用范围没有权值为负数的边）
     *
     */

    public Map<GraphNode,Integer> dijkstra1(GraphNode startNode){
        // distanceMap 中保存了 图中所有节点到startNode的距离
        // 初始时只包含到startNode自己的距离为0， 其他节点不存在该map中默认表示到startNode的距离为负无穷
        HashMap<GraphNode, Integer> distanceMap = new HashMap<>();
        distanceMap.put(startNode,0);

        HashSet<GraphNode> lockedNodes = new HashSet<>();


        // 每次循环从distanceMap中选择距离startNode距离最近的一个节点 （curNode），
        GraphNode curNode = startNode; // 距离startNode最近的节点初始化为startNode节点本身
        while(curNode!=null){
            int minDistance = distanceMap.get(curNode);
            // 然后遍历该节点的所有边和这些边的toNode,
            for(GraphEdge edge:curNode.edges){
                GraphNode toNode = edge.to;
                // 更新这些toNode 到startNode的最小距离
                if(!distanceMap.containsKey(toNode)){
                    distanceMap.put(toNode,minDistance+edge.weight);
                }else{
                    distanceMap.put(toNode,Math.min(distanceMap.get(toNode),minDistance+edge.weight));
                }

            }
            // 与该节点相连的toNode到startNode的最小距离更新后，将该node加入到lockedNodes，后序循环中不再使用该node
            lockedNodes.add(curNode);

            // 更新当前距离startNode 距离最小的curNode ，以及这个最小距离
            curNode = findMinDistanceNodeUnlocked(distanceMap, lockedNodes);
        }

        return distanceMap;
    }

    private GraphNode findMinDistanceNodeUnlocked(HashMap<GraphNode, Integer> distanceMap, HashSet<GraphNode> lockedNodes) {

        GraphNode minDistanceNodeUnlocked=null;
        int minVal=Integer.MAX_VALUE;
        for(Map.Entry<GraphNode,Integer> e : distanceMap.entrySet()){ // TODO 使用堆的改写来改进 distanceMap结构， 避免使用遍历的方式来找到 minDistanceNodeUnlocked
            if(!lockedNodes.contains(e.getKey()) && e.getValue()<minVal ){
                minDistanceNodeUnlocked =e.getKey();
                minVal= e.getValue();
            }
        }
        return minDistanceNodeUnlocked;
    }


    /**
     * nowcoder NC158  单源最短路问题
     */

    static class Node{
        int val;
        List<Edge> edges;

        public Node(int val){
            this.val=val;
            this.edges =new ArrayList<Edge>();
        }
    }

    static class Edge{
        Node from;
        Node to;
        int weight;
        public Edge(Node fromNode,Node toNode,int weight){
            this.from = fromNode;
            this.to =toNode;
            this.weight=weight;
        }
    }

    public int findShortestPath (int n, int m, int[][] graph) {
        // 二维数组转图结构
        ArrayList<Node> graphNodes = new ArrayList<Node>();
        for(int i=1;i<=n;i++){
            graphNodes.add(new Node(i));
        }

        for(int i=0;i<graph.length;i++){
            int from = graph[i][0];
            int to = graph[i][1];
            int weight = graph[i][2];

            Node fromNode = graphNodes.get(from - 1);
            Node toNode = graphNodes.get(to - 1);

            Edge edge = new Edge(fromNode,toNode,weight);
            fromNode.edges.add(edge);
        }

        // dijkstra 算法
        HashMap<Node,Integer> distanceMap = new HashMap<Node,Integer>();
        distanceMap.put(graphNodes.get(0),0);

        HashSet<Node> lockedNodes = new HashSet<Node>();

        Node curNode=graphNodes.get(0);
        while(curNode!=null){
            int  minDistance=distanceMap.get(curNode);
            for(Edge e:curNode.edges){

                if(!distanceMap.containsKey(e.to)){
                    distanceMap.put(e.to,minDistance+e.weight);
                }else{
                    distanceMap.put(e.to,Math.min(minDistance+e.weight,distanceMap.get(e.to)));
                }
            }

            lockedNodes.add(curNode);

            curNode = findMinDistanceUnlockedNode(distanceMap,lockedNodes);
        }
        Node lastNode = graphNodes.get(n - 1);
        return distanceMap.get(lastNode);
    }

    private Node findMinDistanceUnlockedNode(HashMap<Node,Integer> distanceMap,HashSet<Node> lockedNodes){
        Node minDistanceUnlockedNode=null;
        int minDistance = Integer.MAX_VALUE;
        for(Map.Entry<Node,Integer> e:distanceMap.entrySet()){
            if(!lockedNodes.contains(e.getKey()) && minDistance>e.getValue() ){
                minDistance =e.getValue();
                minDistanceUnlockedNode =e.getKey();
            }
        }
        return minDistanceUnlockedNode;
    }

    @Test
    public void test1(){

        int[][] graph = new int[1][3];
        graph[0]=new int[]{1,2,4};

        int shortestPath = findShortestPath(2, 1, graph);
        System.out.println(shortestPath);
    }


}
