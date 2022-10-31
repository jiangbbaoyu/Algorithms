package com.jby.algorithms.Question10_unionFindSet;

/**
 * leetcode 547. 省份数量
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。
 * 如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，
 * 其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
 * 而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 *
 * 思路： 并查集
 */
public class Question01_numOfProvince {

    class UnionFindSet{
        public int[] father;
        public UnionFindSet(int n){
            father = new int[n];
            for(int i=0;i<n;i++){ // 把每一个元素初始化为一个集合,初始化后每一个元素的父亲节点是它本身，每一个元素的祖先节点也是它本身
                father[i]=i;
            }
        }

        // 查找一个元素所在的集合，其精髓是找到这个元素所在集合的祖先
        public int find(int i){
            if(father[i]==i){
                return i; // 返回set 的root 节点
            }

            int setRoot = find(father[i]);
            father[i] = setRoot; // 查找到root后， 将 i节点的 parent 直接设置为 root, 提升下次查找时的效率 （路径压缩）
            return setRoot;
        }
        // 合并两个集合，使一个集合的祖先成为另一个集合的祖先
        public void union(int i,int j){
            int setRoot1 = find(i);
            int setRoot2 = find(j);
            if(setRoot1==setRoot2) return; // 两个元素在同一个集合中

            father[setRoot1] = setRoot2; // 两个元素不在同一个集合中，将以setRoot1为根的set中的所有元素 加入到 以setRoot2为根的set中
        }

        // 判断两个元素是否属于同一集合，只要看他们所在集合的祖先是否相同即可
        public boolean inSameSet(int i,int j){
            return  find(i) == find(j);
        }

    }

    public int findCircleNum(int[][] isConnected) {
        UnionFindSet unionFindSet =new UnionFindSet(isConnected.length);
        for(int i=0;i<isConnected.length;i++){
            for(int j=0;j<isConnected[0].length;j++){
                if(isConnected[i][j]==1){
                    unionFindSet.union(i,j);
                }

            }
        }
        int provinces =0;
        for(int i=0;i<isConnected.length;i++){
            if(unionFindSet.find(i)==i){ // 统计 setRoot的个数，即set的个数，即省份的个数
                provinces++;
            }
        }
        return provinces;
    }
}
