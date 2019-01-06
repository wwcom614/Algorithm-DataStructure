package com.ww.algorithm.Stack;

import com.ww.algorithm.Array.Array;

//底层基于自定义Array类来实现栈
public class ArrayStack<E> implements Stack<E> {
    Array<E> array;

    //自定义有参构造方法
    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }

    //自定义无参构造方法
    public ArrayStack(){
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

    //由于底层是基于动态数组实现，这是特有的方法
    public int getCapacity(){
        return array.getCapacity();
    }

    //均摊时间复杂度O(1)
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    //均摊时间复杂度O(1)
    @Override
    public E pop() {
        return array.removeLast();
    }

    //时间复杂度O(1)
    @Override
    public E peek() {
        return array.getLast();
    }

    //重写toString方法，打印结果便于测试验证
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue: size=%d, capacity=%d, Bottom[", getSize(), getCapacity()));
        for (int i=0; i<array.getSize(); i++) {
            sb.append(array.get(i));
            //如果不是最后一个元素，用逗号空格隔开
            if(i<array.getSize()-1){
                sb.append(", ");
            }
        }
        //标识这端是栈顶
        sb.append("]Top");
        return sb.toString();
    }
}
