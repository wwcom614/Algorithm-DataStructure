package com.ww.algorithm.Heap;

import com.ww.algorithm.Queue.Queue;

public class PriortyQueue<E extends Comparable<E>> implements Queue<E> {

    //基于最大堆做底层数据结构，实现优先队列
    private MaxHeap<E> maxHeap;

    //构造方法
    public PriortyQueue(){
        maxHeap = new MaxHeap<>();
    }


    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    //时间复杂度O(1)
    @Override
    public E getFront() {
        return maxHeap.getMax();
    }

    //时间复杂度O(logn)
    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    //时间复杂度O(logn)
    @Override
    public E dequeue() {
        return maxHeap.removeMax();
    }


}
