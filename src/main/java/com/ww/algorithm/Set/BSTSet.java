package com.ww.algorithm.Set;

import com.ww.algorithm.BST.BSTTree;

public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BSTTree<E> bstTree;

    //构造方法
    public BSTSet(){
        bstTree = new BSTTree<>();
    }

    //平均时间复杂度O(logn)
    @Override
    public void add(E e) {
        bstTree.add(e);
    }

    //平均时间复杂度O(logn)
    @Override
    public void remove(E e) {
        bstTree.remove(e);
    }

    //平均时间复杂度O(logn)
    @Override
    public boolean contains(E e) {
        return bstTree.contains(e);
    }

    @Override
    public int getSize() {
        return bstTree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bstTree.isEmpty();
    }
}
