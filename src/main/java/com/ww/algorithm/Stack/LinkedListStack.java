package com.ww.algorithm.Stack;

import com.ww.algorithm.LinkedList.LinkedList;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack(){
        linkedList = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void push(E e) {
        //链表增加头部元素，时间复杂度为O(1)
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        //链表删除头部元素，时间复杂度为O(1)
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        //链表获取头部元素，时间复杂度为O(1)
        return linkedList.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(linkedList);
        return sb.toString();
    }
}
