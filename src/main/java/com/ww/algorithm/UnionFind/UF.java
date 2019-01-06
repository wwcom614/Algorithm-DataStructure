package com.ww.algorithm.UnionFind;

//并查集
public interface UF {
    //两个元素是否属于同一个集合，是否连接
    boolean isConnected(int p, int q);

    //将2个元素合并到一个集合中
    void unionElements(int p, int q);

    int getSize();
}
