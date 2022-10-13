package com.jby.algorithms.Q01_LinkedListAlgorithms;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

// leetcode 146
// 1. 基于 LinkedHashMap的实现
class LRUCache {
    private LinkedHashMap<Integer,Integer> lruCache;
    public LRUCache(int capacity) {
        lruCache = new LinkedHashMap<Integer, Integer>(capacity,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return super.size()>capacity;
            }
        };
    }

    public int get(int key) {
        return lruCache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        lruCache.put(key,value);
    }
}

// 2. 基于 HashMap 和 双向链表来实现， 本质与LinkedHashMap一致
class LRUCache2 {
    class Node {
        public int key;
        public int value;
        public Node prev;
        public Node next;

        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
        Node(){}
    }


    private int capacity;
    private HashMap<Integer, Node> map;

    private Node head; // dummy head of double linked list
    private Node tail;// dummy tail of double linked list

    public LRUCache2(int capacity) {
        this.capacity =capacity;
        map = new HashMap<Integer, Node>();

        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        remove(node); // remove from double linked list and hashmap
        addToLast(node); // add to list rear and hashmap
        return node.value;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node oldNode = map.get(key);
            oldNode.value = value;
            remove(oldNode);
            addToLast(oldNode);
            return ;
        }

        if(map.size()>=capacity){
            removeFirstNode();// remove first node from list and map
        }

        Node node = new Node(key,value);
        addToLast(node);
    }

    private void removeFirstNode(){
        if(head.next!=null){
            remove(head.next);
        }
    }

    // 从双向链表和 hashmap中移除该node
    private void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.prev =null;
        node.next =null;

        map.remove(node.key);
    }
    // 将一个Node添加到 双向链表的最后端，并添加到hashmap中
    private void addToLast(Node node){
        node.prev = tail.prev;
        tail.prev.next = node;
        node.next = tail;
        tail.prev =node;
        map.put(node.key, node);
    }
}

public class Question07_LRU {
}
