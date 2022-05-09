package com.jby.algorithms.GraphAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Question03_1 {

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

    static class NodesHeap{

        public Node[] nodes;
        public HashMap<Node, Integer> nodesIdxMap;
        public HashMap<Node,Integer> distanceMap;
        public int size;

        public NodesHeap(int size){
            nodes =new Node[size];
            nodesIdxMap =new HashMap<>();
            distanceMap =new HashMap<>();
            size=0;
        }

        public boolean isEmpty(){
            return size==0;
        }

        public boolean isTraversed(Node node){
            return nodesIdxMap.containsKey(node);
        }

        public boolean isInHeap(Node node){
            return nodesIdxMap.containsKey(node) && nodesIdxMap.get(node)!=-1;
        }


        public void swap(int idx1,int idx2){
            nodesIdxMap.put(nodes[idx1],idx2);
            nodesIdxMap.put(nodes[idx2],idx1);

            Node tmp = nodes[idx1];
            nodes[idx1]=nodes[idx2];
            nodes[idx2]=tmp;

        }

        public void addOrUpdateOrIgnore(Node node, int newDistance){
            if(isInHeap(node)){
                Integer oldDistance = distanceMap.get(node);
                if(oldDistance>newDistance){
                    distanceMap.put(node,newDistance);
                    // 由于node的distance值是变小了，因此使用heapInsert方法将该node的值向上调整
                    heapInsert(node,nodesIdxMap.get(node));
                }
            }else if (!isTraversed(node)){
                nodes[size]=node;
                nodesIdxMap.put(node,size);
                distanceMap.put(node,newDistance);
                // 由于[0,size-1]已经是一个最小堆了，使用heapInsert方法将第size个node也加入到小根堆中
                heapInsert(node,size);
                size++;
            }
        }

        private void heapInsert(Node node, Integer childIdx) {
            int parentIdx = (childIdx-1)/2;
            while(parentIdx>0&&distanceMap.get(nodes[parentIdx])>distanceMap.get(nodes[childIdx])){
                swap(parentIdx,childIdx);
                childIdx=parentIdx;
                parentIdx= (parentIdx-1)/2;
            }
        }

        public Record pop(){
            if(isEmpty()){
                return null;
            }

            swap(0,size-1);
            Node nodePoped = nodes[size - 1];
            Record res = new Record(nodePoped, distanceMap.get(nodePoped));
            distanceMap.remove(nodePoped);
            nodesIdxMap.put(nodePoped,-1);
            nodes[size-1]=null;// gc help
            size--;
            // 由于[1,size]已经是一个小根堆了，使用heapify方法将第0个node 加入到小根堆中
            heapify(0);
            return res;
        }


        private void heapify(int parentIdx) {

            int leftChildIdx = parentIdx*2+1;
            while(leftChildIdx<this.size){
                int minChildIdx = leftChildIdx+1>=size?leftChildIdx:
                        distanceMap.get(nodes[leftChildIdx])<distanceMap.get(nodes[leftChildIdx+1])?leftChildIdx:leftChildIdx+1;
                if(distanceMap.get(nodes[minChildIdx])>=distanceMap.get(nodes[parentIdx])){
                    break;
                }
                swap(parentIdx,minChildIdx);
                parentIdx=minChildIdx;
                leftChildIdx=minChildIdx*2+1;
            }
        }
    }

    static class Record{
        Node node;
        int distance;
        public Record(Node node,int distance){
            this.node=node;
            this.distance=distance;
        }
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param n int 顶点数
     * @param m int 边数
     * @param graph int二维数组 一维3个数据，表示顶点到另外一个顶点的边长度是多少​
     * @return int
     */
    public int findShortestPath (int n, int m, int[][] graph) {
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


        HashMap<Node, Integer> res = new HashMap<>();
        NodesHeap nodesHeap = new NodesHeap(n);
        nodesHeap.addOrUpdateOrIgnore(graphNodes.get(0),0);

        while(!nodesHeap.isEmpty()){
            Record record = nodesHeap.pop();
            res.put(record.node,record.distance);
            for (Edge edge : record.node.edges) {
                nodesHeap.addOrUpdateOrIgnore(edge.to,edge.weight+record.distance);
            }
        }
        return res.get(graphNodes.get(n-1));
    }

    @Test
    public void test1(){

//        int[][] graph = new int[][]{{1,2,358},{2,3,106},{2,4,725},{4,5,650},{1,6,780},{6,7,587},{3,8,804},{1,9,252},{3,10,313},{5,3,977},{9,9,132},{7,5,36},{4,5,830},{4,1,713},{2,5,938},{7,5,690},{4,3,896},{9,2,481},{4,6,448},{4,7,888}};
//        int shortestPath = findShortestPath(10, 20, graph);
//        System.out.println(shortestPath);

    }
}
