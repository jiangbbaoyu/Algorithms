package com.jby.algorithms.GraphAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Question01 {

    /**
     * 图的宽度优先遍历
     */
    public void bfs(GraphNode root){
        if (root==null){
            return;
        }

        LinkedList<GraphNode> queue = new LinkedList<GraphNode>(); // 使用队列实现
        HashSet<GraphNode> nodesTraversed = new HashSet<GraphNode>();
        // 记录已经遍历过的节点，对于无向图或有环的有向图，用来解决循环遍历问题
        queue.addFirst(root);
        nodesTraversed.add(root);

        while(!queue.isEmpty()){
            GraphNode node = queue.removeLast();
            System.out.println(node.value);
            node.nexts.forEach(n->{
                if(!nodesTraversed.contains(n)){
                    nodesTraversed.add(n);
                    queue.addFirst(n);
                }
            });
        }
    }

    /**
     * 图的深度优先遍历
     */
    public void dfs(GraphNode root){
        if(root==null){
            return;
        }
        LinkedList<GraphNode> stack = new LinkedList<>();
        HashSet<GraphNode> nodesTraversed = new HashSet<>();
        stack.addFirst(root);
        nodesTraversed.add(root);

        System.out.println(root.value); //  在节点加入到stack 中的时候访问其中的数据

        while(!stack.isEmpty()){
            // 先将栈顶的节点移除，如果该节点存在没有访问的相邻节点，则将该节点和相邻节点压入栈中
            // 栈中始终存放着 根节点到当前正在遍历节点路径上的所有节点
            GraphNode node = stack.removeFirst();
            for(GraphNode n:node.nexts){
                if(!nodesTraversed.contains(n)){
                    nodesTraversed.add(n);
                    stack.addFirst(node);// 将node重新加入到stack中
                    stack.addFirst(n);   // 同时将其没有访问的相邻节点加入到stack中
                    System.out.println(n.value);
                    break; // 从该相邻的节点开始新的深度优先遍历流程
                }
            }
        }
    }

    /**
     * 图结构的拓扑排序
     * 首先遍历入度为0的节点，然后将与该节点相邻的节点的入度值减一，
     * 如果与该节点相邻的节点的入度值减一后为0，则将节点加入遍历队列，作为下一轮待遍历的节点
     */

    public void topologySort(Graph graph){

        if(graph==null){
            return ;
        }

        LinkedList<GraphNode> queue = new LinkedList<>();
        HashMap<GraphNode, Integer> nodesInDegreeMap = new HashMap<>();
        for(GraphNode node : graph.nodes.values()){
            nodesInDegreeMap.put(node,node.inDegree);
            if(node.inDegree==0){
                queue.addFirst(node);
            }
        }

        while (!queue.isEmpty()){
            GraphNode node = queue.removeLast();
            System.out.println(node.value);


             // 通过图中的edges变量找到相邻的节点
//            for(GraphEdge edge:node.edges){
//                GraphNode relatedNodes = edge.to;
//                int newInDegree = nodesInDegreeMap.get(relatedNodes) - 1;
//                nodesInDegreeMap.put(relatedNodes,newInDegree);
//                if (newInDegree==0){
//                    queue.addFirst(relatedNodes);
//                }
//            }n

            // 通过图中的nexts变量找到相邻的节点
            for(GraphNode n:node.nexts){
                int newInDegree = nodesInDegreeMap.get(n) - 1;
                nodesInDegreeMap.put(n,newInDegree);
                if (newInDegree==0){
                    queue.addFirst(n);
                }
            }
        }
    }


    static class Node {
        int value; // 课程编号
        int inDegree; //依赖课程的个数
        ArrayList<Node> nexts;  // 依赖该课程的课程集合  （出度对应的节点）
        public Node(){
            nexts= new ArrayList<>();
        }
        public Node(int val){
            nexts= new ArrayList<>();
            value=val;
        }
    }

    /**
     *  nowcoder  NC218  检测图的循环依赖问题
     * @param prerequisites
     * @param n
     * @return
     */
    public int[] findOrder (int[][] prerequisites, int n) {

        ArrayList<Node> nodes=new ArrayList<Node>(n); // 图中的全部节点，每个课程代表一个节点
        for(int i=0;i<n;i++){
            nodes.add(new Node(i));
        }

        for(int i=0;i<prerequisites.length;i++){
            int course =  prerequisites[i][0];
            int dep =  prerequisites[i][1];

            Node courseNode=nodes.get(course);
            courseNode.inDegree+=1;


            Node depNode=nodes.get(dep);
            depNode.nexts.add(courseNode);  // 构建各个课程间的依赖关系
        }

        // 从入度为0 （不依赖任何其他课程的课程）的节点开始，依次遍历图中的所有节点
        LinkedList<Node> queue =new LinkedList<Node>();
        for(Node node:nodes){
            if(node.inDegree==0){
                queue.addFirst(node);
            }

        }

        ArrayList<Integer> resTmp= new ArrayList<Integer>();
        while(!queue.isEmpty()){
            Node courseNode =queue.removeLast();
            resTmp.add(courseNode.value);
            for(Node next :courseNode.nexts){
                if(next.inDegree-1==0){
                    queue.addFirst(next);// 如果一个节点的所有依赖的节点都遍历过了，则将该节点加入到遍历队列中
                }
                next.inDegree-=1;
            }
        }

        int[] res=new int[resTmp.size()];
        for(int i=0;i<resTmp.size();i++){
            res[i]=resTmp.get(i);
        }

        return res;


    }

}
