package com.jby.algorithms.GraphAlgorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

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
        for(Map.Entry<GraphNode,Integer> e : distanceMap.entrySet()){
            if(!lockedNodes.contains(e.getKey()) && e.getValue()<minVal ){
                minDistanceNodeUnlocked =e.getKey();
                minVal= e.getValue();
            }
        }
        return minDistanceNodeUnlocked;
    }

    /**
     * 方法二 : 使用堆的改写来改进 distanceMap结构， 避免使用遍历的方式来找到 minDistanceNodeUnlocked
     */

    // 小根堆，每次O(logn)的复杂度找到当前距离最小的节点
    static class NodesHeap{

        public GraphNode[] nodes;
        public HashMap<GraphNode, Integer> nodesIdxMap;
        public HashMap<GraphNode,Integer> distanceMap;
        public int size;

        public NodesHeap(int size){
            nodes =new GraphNode[size];
            nodesIdxMap =new HashMap<>();
            distanceMap =new HashMap<>();
            size=0;
        }

        public boolean isEmpty(){
            return size==0;
        }

        public boolean isTraersed(GraphNode node){
            return nodesIdxMap.containsKey(node);
        }

        public boolean isInHeap(GraphNode node){
            return nodesIdxMap.containsKey(node) && nodesIdxMap.get(node)!=-1;
        }


        public void swap(int idx1,int idx2){
            nodesIdxMap.put(nodes[idx1],idx2);
            nodesIdxMap.put(nodes[idx2],idx1);

            GraphNode tmp = nodes[idx1];
            nodes[idx1]=nodes[idx2];
            nodes[idx2]=tmp;

        }

        public void addOrUpdateOrIgnore(GraphNode node, int newDistance){
            if(isInHeap(node)){
                Integer oldDistance = distanceMap.get(node);
                if(oldDistance>newDistance){
                    distanceMap.put(node,newDistance);
                    // 由于node的distance值是变小了，因此使用heapInsert方法将该node的值向上调整
                    heapInsert(node,nodesIdxMap.get(node));
                }
            }else if (!isTraersed(node)){
                nodes[size]=node;
                nodesIdxMap.put(node,size);
                distanceMap.put(node,newDistance);
                // 由于[0,size-1]已经是一个最小堆了，使用heapInsert方法将第size个node也加入到小根堆中
                heapInsert(node,size);
                size++;
            }
        }

        private void heapInsert(GraphNode node, Integer childIdx) {
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
            GraphNode nodePoped = nodes[size - 1];
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
        GraphNode node;
        int distance;
        public Record(GraphNode node,int distance){
            this.node=node;
            this.distance=distance;
        }
    }

    public Map<GraphNode,Integer> dijkstra2(GraphNode startNode,int n){

        HashMap<GraphNode, Integer> res = new HashMap<>();
        NodesHeap nodesHeap = new NodesHeap(n);
        nodesHeap.addOrUpdateOrIgnore(startNode,0);

        while(!nodesHeap.isEmpty()){
            Record record = nodesHeap.pop();
            res.put(record.node,record.distance);
            for (GraphEdge edge : record.node.edges) {
                nodesHeap.addOrUpdateOrIgnore(edge.to,edge.weight+record.distance);
            }
        }
        return res;
    }


}
