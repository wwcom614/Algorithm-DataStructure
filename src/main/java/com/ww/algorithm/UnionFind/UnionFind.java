package com.ww.algorithm.UnionFind;

//场景：用于解决2个元素是否有联系的问题，例如迷宫两点之间是否有连接，社交网络中2个用户，是否通过朋友的朋友之间有联系。
//底层可基于数组实现，元素值作为底层数组的索引值，元素归属的集合作为数组该索引对应的值。此时find的时间复杂度是O(1)，但union时要遍历整个数组，时间复杂度是O(n)。
//为提升union的速度，数组该索引对应的值改为存储其父节点的索引，形成子节点指向父节点的树形结构。
//让树的高度尽可能低，这样find和unionElements方法的效率都会提升。
public class UnionFind implements UF {
    // rank[i]表示以i为根的集合所表示的树的层数的相对排名，不是树的height或者depth, 只是作为树高度比较的一个标准
    private int[] rank;
    // parent[i]表示第i个元素所指向的父节点
    private int[] parent;

    // 构造方法
    public UnionFind(int size){

        rank = new int[size];
        parent = new int[size];

        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
        for( int i = 0 ; i < size ; i ++ ){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    // 内部私有方法：查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    private int find(int p){
        if(p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");
        //路径压缩Path Compression：让树的高度尽可能低，这样find和union效率都会提升
        //路径压缩的时候不用维护rank：因为如果要维护树的层数精确值需要额外计算开销，
        //而UnionFind本身不需要精确知道树的层数值。有层数相对排名值，作为unionElements时比较的标准就可以了
        while( p != parent[p] ){
            //路径压缩方法1：子节点p指向(parent[p])父节点的父节点parent[parent[p]]，与父节点平级
            parent[p] = parent[parent[p]];
            //将p移动到父节点，也就是最开始的父节点的父节点位置，继续向根节点进发重复上述步骤，不断降低树的高度
            p = parent[p];
        }
        return p;
    }

    // 查找过程, 查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    private int find2(int p){
        if(p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        // 路径压缩方法2, 递归算法
        if(p != parent[p]){
            parent[p] = find2(parent[p]);
        }
        return parent[p];
    }

    // 判断元素p和元素q是否所属一个集合
    // O(h)复杂度, h为树的高度
    @Override
    public boolean isConnected( int p , int q ){
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    // O(h)复杂度, h为树的高度
    @Override
    public void unionElements(int p, int q){
        //分别找到元素p和元素q的根节点
        int pRoot = find(p);
        int qRoot = find(q);

        if( pRoot == qRoot )
            return;

        // 根据两个元素所在树的rank不同判断合并方向
        // 将rank低的集合合并到rank高的集合上，这样树的高度会更低
        if( rank[pRoot] < rank[qRoot] )
            parent[pRoot] = qRoot;
            //高度低的树合并到高度高的树上，合并后树的高度，还是高度高的树的高度不变
        else if( rank[qRoot] < rank[pRoot])
            parent[qRoot] = pRoot;
            //高度低的树合并到高度高的树上，合并后树的高度，还是高度高的树的高度不变
        else{ // rank[pRoot] == rank[qRoot]
            parent[pRoot] = qRoot;
            rank[qRoot]++;   // 高度相同的2个树合并到其中一个树上，合并后树的高度+1
        }
    }
}
