package com.ww.algorithm.Stack;

//定义一个栈接口，因为底层有多种实现方式
public interface Stack<E> {
    int getSize();
    boolean isEmpty();

    //入栈
    void push(E e);

    //出栈
    E pop();

    //查询栈顶元素
    E peek();
}
