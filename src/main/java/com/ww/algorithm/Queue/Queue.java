package com.ww.algorithm.Queue;

//定义一个栈接口，因为底层有多种实现方式
public interface Queue<E> {
    int getSize();
    boolean isEmpty();

    //队尾入队列
    void enqueue(E e);

    //队首出队列
    E dequeue();

    //查询队首元素
    E getFront();
}
