package com.ww.algorithm.Queue;

import com.ww.algorithm.Array.Array;

//底层基于自定义Array类来实现队列
public class ArrayQueue<E> implements Queue<E> {
    Array<E> array;

    //自定义有参构造方法
    public ArrayQueue(int capacity){
        array = new Array<>(capacity);
    }

    //自定义无参构造方法
    public ArrayQueue(){
        array = new Array<>();
    }


    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    //底层基于动态数组实现，提供特有的方法
    public int getCapacity(){
        return array.getCapacity();
    }

    //均摊时间复杂度O(1)
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    //时间复杂度O(n)
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    //时间复杂度O(1)
    @Override
    public E getFront() {
        return array.getFirst();
    }

    //重写toString方法，打印结果便于测试验证
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue: size=%d, capacity=%d, Front[", getSize(), getCapacity()));
        for (int i=0; i<array.getSize(); i++) {
            sb.append(array.get(i));
            //如果不是最后一个元素，用逗号空格隔开
            if(i<array.getSize()-1){
                sb.append(", ");
            }
        }
        //标识这端是栈顶
        sb.append("]Tail");
        return sb.toString();
    }
}
