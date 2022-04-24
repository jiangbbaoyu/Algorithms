package com.jby.algorithms.TrieTreeAlgorithms;


import org.junit.Test;

// Trie tree 的节点
class TrieNode{
    public int end; //  以该节点作为结尾的字符串数
    public int pass; // 经过该节点的字符串数
    //字符集假设只有 a-z ;  如果字符集过大可以使用 HashMap<Char,TrieNode> 类型来作为nexts
    public TrieNode[] nexts;  // 0-> a;  1-> b; ...

    public TrieNode(){
        this.nexts =new TrieNode[26];
    }

}


public class TrieTree {
    public TrieNode root;
    public TrieTree(){
        this.root=new TrieNode();
    }
    /**
     * 向前缀树中插入一个字符串
     * @param word
     */
    public void insert(String word){
        if(word==null || word.length()==0){
            return;
        }

        root.pass++;

        char[] chars = word.toCharArray();
        TrieNode curr = root;
        for(int i=0;i<chars.length;i++){
            int index =chars[i]-'a';
            if(curr.nexts[index]==null){
                curr.nexts[index]=new TrieNode();
            }

            curr = curr.nexts[index];
            curr.pass++;
        }

        curr.end++;
    }

    /**
     * 搜索一个字符串出现的次数
     * @param word
     * @return
     */
    public int search(String word){
        if(word==null || word.length()==0){
            return 0;
        }

        char[] chars = word.toCharArray();
        TrieNode curr=root;
        for(int i=0;i<chars.length;i++){
            int index = chars[i]-'a';
            if(curr.nexts[index]==null){
                return 0;
            }
            curr =curr.nexts[index];
        }

        return curr.end;
    }

    /**
     * 一个字符串是否出现过
     */
    public boolean contains(String word){
        return search(word)>0;
    }

    /**
     * trie tree 中以prefix 为前缀的字符串的个数
     * @param prefix
     * @return
     */
    public int prefixNumber(String prefix){
        if(prefix==null || prefix.length()==0){
            return 0;
        }

        char[] chars = prefix.toCharArray();
        TrieNode curr=root;
        for(int i=0;i<chars.length;i++){
            int index = chars[i]-'a';
            if(curr.nexts[index]==null){
                return 0;
            }
            curr =curr.nexts[index];
        }

        return curr.pass;
    }

    /**
     * trie tree中删除一个字符串
     */

    public void delete(String word){
        if(!contains(word)){
            return;
        }
        if(word==null || word.length()==0){
            return;
        }

        char[] chars = word.toCharArray();
        TrieNode curr =root;
        curr.pass--;

        for(int i=0;i<chars.length;i++){
            int index =chars[i]-'a';
            if(curr.nexts[index].pass==1){
                // 如果当前node的下一个node的pass为1，且要删除的word经过该node,则直接将该node及其子nodes全部从树中删除掉
                curr.nexts[index]=null;
                return;
            }

            curr =curr.nexts[index];
            curr.pass--;
        }

        curr.end--;
    }

    @Test
    public void test1(){
        TrieTree trieTree = new TrieTree();
        trieTree.insert("jby");
        trieTree.insert("jbyf");
        trieTree.insert("jba");
        trieTree.insert("jba");

        System.out.println(trieTree.search("jba"));
        System.out.println(trieTree.search("jbaa"));
        System.out.println(trieTree.prefixNumber("jb"));
        trieTree.delete("jba");
        System.out.println(trieTree.search("jba"));
        trieTree.delete("jba");
        System.out.println(trieTree.search("jba"));

    }
}
